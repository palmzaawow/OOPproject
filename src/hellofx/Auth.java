package hellofx;

import java.util.HashMap;

public class Auth {

    DB data;

    public boolean register(String email, String user, String password)
    {
        HashMap<String, String> anonymous = data.select("username" , user);
        if (anonymous == null && passIsValid(password))
        {
            try {
                HashMap<String, String> userHashMap = new HashMap<>();
                userHashMap.put("username", user);
                userHashMap.put("password", password);
                userHashMap.put("email", email);
                data.insert(userHashMap);
                return true;
            }            
            catch(Exception e)
            {
                System.out.println(e);
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public boolean passIsValid(String password)
    {
        if (password.length() >= 6 && password.matches("[a-zA-Z0-9]+") )
        {
            return true;
        }        
        return false;
    }

    public int login(String user, String password)
    {
        try{
            HashMap<String, String> anonymous = data.select("username" , user);
            if (anonymous != null)
            {
                int role = (anonymous.get("password").equals(password)) ? Integer.parseInt(anonymous.get("role")):0;
                return role;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception e)
        {
            return 0;
        }

    }

    public boolean repeatCheck(String password,String repeat)
    {
        if (password.equals(repeat))
        {
            return true;
        }
        return false;
    }

    public Auth() {
        this.data = new User_db();
    }
}
