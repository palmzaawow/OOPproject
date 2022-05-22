package hellofx;

import java.util.Comparator;
import java.util.HashMap;

public class Mysort implements Comparator<HashMap<String,String>>{
    @Override
    public int compare(HashMap<String,String> map1, HashMap<String,String> map2) {
        return (int) (Integer.parseInt(map1.get("new_case")) - Integer.parseInt(map2.get("new_case")));
    }
   
}