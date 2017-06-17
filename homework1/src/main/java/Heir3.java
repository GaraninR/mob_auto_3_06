import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by garanin.r on 09.06.17.
 */
public class Heir3 extends BaseScreen {

    private void myPrivateMethodHashMap() {


        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>();

        myHashMap.put("Windows", 8);
        myHashMap.put("Windows", 10);
        myHashMap.put("Android", 4);
        myHashMap.put("Android", 5);
        myHashMap.put("iOS", 6);
        myHashMap.put("iOS", 7);

        Set set = myHashMap.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.println("For "+ mentry.getKey() + " verion is " + mentry.getValue());
        }

    }

    public void runPrivateMethod() {
        myPrivateMethodHashMap();
    }


    @Override
    public void goTitle() {
        System.out.println("Go Title for Heir3");
    }
}
