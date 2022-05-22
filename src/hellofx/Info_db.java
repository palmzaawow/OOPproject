package hellofx;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import java.net.*;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Info_db extends DB implements Api{
    
    public HashMap<String,String> getProvine(String province)
    {
        return super.select("province", province);
    }

    public ArrayList<HashMap> getAll()
    {
        try{    
            URL url=new URL("https://covid19.ddc.moph.go.th/api/Cases/today-cases-by-provinces");    
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            
            con.setRequestMethod("GET");
            con.setRequestProperty("accept", "application/json");
        
            BufferedReader in = new BufferedReader(
                     new InputStreamReader(con.getInputStream()));
             String inputLine;
             StringBuffer response = new StringBuffer();
             while ((inputLine = in.readLine()) != null) {
                 response.append(inputLine);
             }
             in.close();
        
             JSONArray myResponse = new JSONArray(response.toString());
             ArrayList<HashMap> dataset = new ArrayList<>();
             for(int i=0;i<myResponse.length();i++)
             {
                 JSONObject thisprovince = myResponse.getJSONObject(i);
                 JSONArray keys = thisprovince.names();
                 HashMap<String,String> data = new HashMap<String,String>();
                 for (int j=0;j<keys.length();j++){
                    String key = keys.getString(j);
                    Object value = thisprovince.get(key);
                    data.put(key, value.toString());
                }
                 dataset.add(data);
             }
             return dataset;
            
            }catch(Exception e){
                System.out.println(e);
                return null;
            }    
    }

    public boolean updateInfo(String province, String col, String input)
    {
        return super.update("province", province, col, input);
    }

    @Override
    public boolean insert(HashMap<String, String> row) {
        try{
            Statement stmt=super.getCon().createStatement();  
            String insert = "'"+row.get("province")+"','"+row.get("new_death")+"','"+row.get("new_cases")+"'";
            int rs = stmt.executeUpdate("insert into info(province,new_death,new_cases) values("+insert+")");
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

    

    @Override
    public HashMap<String, String> getApiProvince(String province) {
        String link;
        if(province == null){
            link = Allurl;
        }
        else{
            link = url;
        }

        HashMap<String,String> data = new HashMap<String,String>();
        try{    
            URL url=new URL(link);    
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            
            con.setRequestMethod("GET");
            con.setRequestProperty("accept", "application/json");
        
            BufferedReader in = new BufferedReader(
                     new InputStreamReader(con.getInputStream()));
             String inputLine;
             StringBuffer response = new StringBuffer();
             while ((inputLine = in.readLine()) != null) {
                 response.append(inputLine);
             }
             in.close();
        
             JSONArray myResponse = new JSONArray(response.toString());

             if(link.equals(Allurl)){
                JSONObject thisprovince = myResponse.getJSONObject(0);
                JSONArray keys = thisprovince.names();
                for (int j=0;j<keys.length();j++)
                        {
                        String key = keys.getString(j);
    
                        Object value = thisprovince.get(key);
                        if(!key.equals("new_recovered") && !key.equals("total_recovered"))
                            data.put(key, value.toString());
                        }
             }
             else{
                for(int i=0;i<myResponse.length();i++)
             {
                 JSONObject thisprovince = myResponse.getJSONObject(i);
                    if(thisprovince.getString("province").equals(province)){
                        JSONArray keys = thisprovince.names();
                     
                        for (int j=0;j<keys.length();j++)
                        {
                        String key = keys.getString(j);
    
                        Object value = thisprovince.get(key);
                        if(!key.equals("province"))
                            data.put(key, value.toString());
                        }
                        break;
                 }
                 
             }
             }

             
             return data;
            
            }catch(Exception e){
                System.out.println(e);
                return null;
            }  
    }

    public Info_db() {
        super("info");
    }
}
