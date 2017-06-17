/**
 * Created by garanin.r on 09.06.17.
 */

public abstract class BaseScreen {

    private String username;

    public static final String NAME_OF_APP = "mob_auto_3_06_homework1";

    void goBack(){
        System.out.println("Go back!");

    }

    public void setUsername(String username) {
        this.username = username;

    }

    abstract void goTitle();


}
