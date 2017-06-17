import java.util.ArrayList;

/**
 * Created by garanin.r on 09.06.17.
 */
public class Heir1 extends BaseScreen {

    private void myPrivateMethodArrayList() {

        ArrayList<String> myArrayList = new ArrayList<String>();

        myArrayList.add("Linux");
        myArrayList.add("MacOS");
        myArrayList.add("Windows");
        myArrayList.add("FreeBSD");
        myArrayList.add("OS/2");

        for(String item : myArrayList) {

            if (item == "Windows") {
                System.out.println("It's Windows");
            } else {
                System.out.println("It's not Windows! It's " + item);
            }

        }
    }

    public void runPrivateMethod() {
        myPrivateMethodArrayList();
    }

    @Override
    public void goTitle() {
        System.out.println("Go Title for Heir1");
    }
}
