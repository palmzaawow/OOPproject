package hellofx;

import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Img_db extends DB{

    @Override
    public boolean insert(HashMap<String, String> row) {
        try{
            
            String query = "insert into img(name,image) value(?,?)";
            PreparedStatement stmt=super.getCon().prepareStatement(query);
            File file = new File(row.get("path"));
            FileInputStream fis=new FileInputStream(file);
            stmt.setString(1, row.get("name"));
            stmt.setBinaryStream(2,fis,(int)file.length());
            int rs = stmt.executeUpdate();
            if(rs > 0)
            {
                return true;
            }
            else{
                return false;
            }
            
        }catch(Exception e){
            return false;
        }
    }

  
    public boolean getImg(String name){
        try{
            String path = System.getProperty("user.dir") + File.separator + "img";
            File file=new File(path);
            file.mkdirs();
            FileOutputStream fos=new FileOutputStream(new File(file, name+".jpg"));
            byte b[];
            Blob blob;
            PreparedStatement ps=super.getCon().prepareStatement("select * from img where name='" + name + "'");
            ResultSet rs=ps.executeQuery();
            if(!rs.next()){
                return false;
            }else{
                do{
                    blob=rs.getBlob("image");
                    b=blob.getBytes(1,(int)blob.length());
                    fos.write(b);    
                }while(rs.next());
            }
            return true;

        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean deleteImg(String name)
    {
        try{

            delete("name", name);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public boolean upload(String path, String filename)
    {
        try {
            HashMap<String , String> meta = new HashMap<>();
            deleteImg(filename);
            meta.put("name", filename);
            meta.put("path", path);
            insert(meta);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public Img_db() {
        super("img");
    }

    


    
    
}
