import java.util.HashSet;

/**
 * Created by garanin.r on 09.06.17.
 */
public class Heir2 extends BaseScreen {

    private void myPrivateMethodHashSet() {

        HashSet<String> myHashSet = new HashSet<String>();

        myHashSet.add("Android");
        myHashSet.add("iOS");
        myHashSet.add("Windows Phone");

        myHashSet.add("Tizen");
        myHashSet.add("Asha");
        myHashSet.add("BlackBarry");

        myHashSet.add("Android"); // duplicate
        myHashSet.add("iOS");
        myHashSet.add("Ubuntu Phone"); // duplicate
        myHashSet.add("MeeGo");
        myHashSet.add("FirefoxOS");

        for(String item : myHashSet) {

            if ((item == "Ubuntu Phone") || (item == "FirefoxOS") || (item == "iOS")) {
                System.out.println(item + " is dead!");
            } else {
                System.out.println(item);
            }
        }

    }

    public void runPrivateMethod() {
        myPrivateMethodHashSet();
    }

    @Override
    public void goTitle() {
        System.out.println("Go Title for Heir2");
    }

}
