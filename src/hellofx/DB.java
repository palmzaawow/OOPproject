package hellofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;  

 import org.json.*;

abstract public class DB
{
    String table;
    Connection connection;
    

    public boolean init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection=DriverManager.getConnection(  
            "jdbc:mysql://db4free.net:3306/dandogdb","dandog","daniel2546");  
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    public HashMap<String,String> select(String col,String keyword){
        HashMap<String,String> data = new HashMap<>();
        try{
            Statement stmt = this.connection.createStatement();
            String query =  "select * from " + this.table + " where " + col + "='"+keyword+"'";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData column = (ResultSetMetaData) rs.getMetaData();
            rs.next();
            for(int i=1;i<=column.getColumnCount();i++)
                data.put(column.getColumnName(i),rs.getString(i));    
            return data;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<HashMap> selectAll(String col){
        try{

            Statement stmt = this.connection.createStatement();
            String query = "select * from " + this.table;
            ResultSet rs = stmt.executeQuery(query);

            Statement stmt2 = this.connection.createStatement();
            String query2 = "select count(*) from " + this.table;
            ResultSet rs2 = stmt2.executeQuery(query2);
            rs2.next();
            int numRow = rs2.getInt(1);

            ArrayList<HashMap> dataset = new ArrayList<>();

            int i = 0;
            while(rs.next()){
                String data = rs.getString(col);
                dataset.add(select(col, data));
            }
            return dataset;

            
        }catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public boolean delete(String col,String keyword){
        try{
            Statement stmt = this.connection.createStatement();
            String query =  "delete from " + this.table + " where " + col + "='"+keyword+"'";
            int rs = stmt.executeUpdate(query);
            if(rs > 0){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    public boolean update(String colkey,String keyword,String colin,String input){
        try{
            Statement stmt = this.connection.createStatement();
            if(input == null)
                {
                    throw new Exception("Can't be null");
                }
            String query =  "update " + this.table + " set " + colin + " = '" + input + "' where " + colkey + " = '" + keyword + "'" ;
            int rs = stmt.executeUpdate(query);
            if(rs > 0){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    public abstract boolean insert(HashMap<String,String> row);

    public Connection getCon()
    {
        return this.connection;
    }

    public DB() {
        this("people");
    }

    public DB(String table){
        this.init();
        this.table = table;
    }

    
} 