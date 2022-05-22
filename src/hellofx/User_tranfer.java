package hellofx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class User_tranfer extends Tranfer{
    
    @Override
    public Information tranfer(String keyword)
    {
       try{
           Information temp = new Information(keyword);
           DB data = new User_db();
           HashMap<String , String> recieve =  ((User_db) data).getUser(keyword);
           recieve.forEach((key, value) -> {
               temp.addLabel(new Data(key , value));
           });

           return temp;
       }
       catch (Exception e)
       {
           return null;
       }
        
    }

    public ArrayList<Information> head() {
        ArrayList<Information> result = new ArrayList<>();
        DB data = new User_db();
        ArrayList<HashMap> t = ((User_db) data).getAll();

        Collections.sort((ArrayList)t ,new Mysort());

        Iterator<HashMap> iterator = t.listIterator(t.size() - 10);

        while(iterator.hasNext())
        {
            HashMap<String, String> i = iterator.next();  
            System.out.println(i);  
            Information temp = new Information(i.get("province"));
            i.forEach((key, value) -> {
                temp.addLabel(new Data(key , value));
            });

            result.add(temp);
        }

        return result;
    }

    
    
}
