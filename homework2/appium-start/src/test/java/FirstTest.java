import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstTest {

    private static AndroidDriver driver;

    public static void main(String[] args) throws InterruptedException {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/app/Android");
        File app = new File(appDir, "Contacts.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus 4");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.jayway.contacts");
        capabilities.setCapability("appActivity", "com.jayway.contacts.MainActivity");
        try {
             driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        ArrayList<MobileElement> elements = (ArrayList<MobileElement>) driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.jayway.contacts:id/name']"));

        // 1. проходим по первым 5-и элементам
        int i = 0;
        for (MobileElement element : elements) {
            if (i > 4) break;
            String value = element.getText();
            element.click();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            String message = "For "  + value + " data is ";
            // 2. проверяем, что информация указана корректно
            // В данном случае под корректностью подразумевается:
            // 2.1. Все поля заполнены
            // 2.2. Имя в карточке записи соответствует имени в списке
            // 2.3. E-mail валиден

            if (contactDataIsCorrect(value)) {
                message += "correct!";
            }
            else
            {
                message += "incorrect";
            }

            System.out.println(message);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.navigate().back();
            i++;
        }

        // 3. Поиск - позитивный тест
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        MobileElement mainSearch = (MobileElement) driver.findElementById("main_search");

        // FIXME: Ума не приложу, почему sendKeys в этом случае отрабатывает 2 раза!
        mainSearch.sendKeys("madison");

        elements = (ArrayList<MobileElement>) driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.jayway.contacts:id/name']"));
        String searchName = elements.get(0).getText();

        if ((elements.size() == 1) && searchName.equals("Madison Gentile")) {
            System.out.println("Positive test is passed!");
        }

        for (int k = 6; k >= 0; k--) {
            driver.sendKeyEvent(AndroidKeyCode.BACKSPACE);
        }

        // 4. Поиск - негативный тест
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // FIXME: Аналогично
        mainSearch.sendKeys("aaaaaaaa");
        elements = (ArrayList<MobileElement>) driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.jayway.contacts:id/main_text']"));
        String notFoundMessage = elements.get(0).getText();

        if (notFoundMessage.length() > 0) {
            System.out.println("Negative test is passed!");
        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }


    private static boolean contactDataIsCorrect(String name) {

        boolean dataIsValid = true;

        MobileElement mobileElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.jayway.contacts:id/detail_name']"));
        String detailName = mobileElement.getText();

        boolean phoneIsValid = false;
        mobileElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.jayway.contacts:id/phonenumber']"));
        String phoneNumber = mobileElement.getText();
        phoneIsValid = phoneIsValid(phoneNumber);

        boolean emailIsValid = false;
        mobileElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.jayway.contacts:id/email']"));
        String email = mobileElement.getText();
        emailIsValid = emailIsValid(email);

        mobileElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.jayway.contacts:id/street1']"));
        String street1 = mobileElement.getText();

        mobileElement = (MobileElement) driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.jayway.contacts:id/street2']"));
        String street2 = mobileElement.getText();


        if (!name.equals(detailName)) {
            dataIsValid = false;
            System.out.println(name);
            System.out.println(detailName);
            System.out.println("Name is incorrect!");
        }

        if (!phoneIsValid) {
            dataIsValid = false;
            System.out.println("Phone is incorrect!");
        }

        if (!emailIsValid) {
            dataIsValid = false;
            System.out.println("Email is incorrect!");
        }

        if (street1.length() == 0) {
            dataIsValid = false;
            System.out.println("Street1 is incorrect!");
        }

        if (street2.length() == 0) {
            dataIsValid = false;
            System.out.println("Street2 is incorrect!");
        }

        return dataIsValid;

    }

    private static boolean phoneIsValid(String phone) {
        // TODO: ниасилил
        return true;
    }

    private static boolean emailIsValid(String email) {

        Pattern pattern;
        Matcher matcher;

        String emailPattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(emailPattern);

        // TODO: специально добавлено для проверки паттерна email, проверяем некорректный e-mail
        if (email.equals("eekrantz4@yopmail.com"))
        {
            System.out.println("Специально проверяем подставной некорректный email!");
            matcher = pattern.matcher("sdfsdf");
        }
        else {
            matcher = pattern.matcher(email);
        }

        return matcher.matches();

    }
}