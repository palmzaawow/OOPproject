package hellofx;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class User_db extends DB{

    public HashMap<String,String> getUser(String username)
    {
        return super.select("username", username);
    }

    public ArrayList<HashMap> getAll()
    {
        return super.selectAll("username");
    }

    public boolean deleteUser(String username)
    {
        return super.delete("username", username);
    }

    public boolean updateUser(String username, String col, String input)
    {
        return super.update("username", username, col, input);
    }

    @Override
    public boolean insert(HashMap<String, String> row) {
        
        try{
            Statement stmt=super.getCon().createStatement();  
            String insert = "'"+row.get("username")+"','"+row.get("password")+"',1,'"+row.get("email")+"'";
            int rs = stmt.executeUpdate("insert into people(username,password,role,email) values("+insert+")");
            if(rs > 0)
            {
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean insertdata(HashMap<String,String> data){
        try{
            Statement stmt=super.getCon().createStatement();  
            String query = "update people set firstname='" + data.get("firstname") + "',surname='" + data.get("surname") + "',sex='" + data.get("sex") + "',phone='" + data.get("phone") + "',address='" + data.get("address") + "',zipcode='" + data.get("zipcode") + "',province='" + data.get("province") + "' where username='" + data.get("username") + "'"; 
            int rs = stmt.executeUpdate(query);
            if(rs > 0)
            {
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    

    public User_db() {
        super("people");
    }
    
    
}
