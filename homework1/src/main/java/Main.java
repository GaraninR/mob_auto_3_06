/**
 * Created by raiserzx on 09.06.17.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to " + BaseScreen.NAME_OF_APP);

        Heir1 heir1 = new Heir1();
        heir1.goTitle();
        heir1.runPrivateMethod();
        heir1.goBack();

        Heir2 heir2 = new Heir2();
        heir2.goTitle();
        heir2.runPrivateMethod();
        heir2.goBack();

        Heir3 heir3 = new Heir3();
        heir3.goTitle();
        heir3.runPrivateMethod();
        heir3.goBack();



    }
}
