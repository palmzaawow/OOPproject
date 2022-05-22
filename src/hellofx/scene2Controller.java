package hellofx;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


import javax.imageio.ImageIO;

import com.mysql.cj.exceptions.DataTruncationException;

//import com.gluonhq.charm.glisten.control.DatePicker;
import java.time.LocalDate;
//import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.VideoTrack;
import javafx.stage.FileChooser;
//import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;

public class scene2Controller  {

    private Stage stage;
    private Scene scene;
    public String filesend;
    public String fileatksend;
    public String gender;
    public String userfromc1;
    //private Parent root;
  

    @FXML
    private Pane overviewpane;

    @FXML
    private Button overviewbtn;

    @FXML
    private Button profilebtn;

    @FXML
    private Button reportbtn;

    @FXML
    private Button settingbtn;

    @FXML
    private Button signoutbtn;

    @FXML
    private StackedBarChart<String, Integer> barchart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private ImageView inforlong;

    @FXML
    private ImageView inforshort;

    @FXML
    private ImageView showprofile;

    @FXML
    private Pane profilepane;

    @FXML
    private Pane reportpane;

    @FXML
    private Button uploadprofilebtn;

    @FXML
    private Button saveprofilebtn;

    @FXML
    private Button uploadATK;

    @FXML
    private Button senddata;

    @FXML
    private ImageView showatk;

    @FXML
    private DatePicker dateatk;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField tel;

    @FXML
    private TextField zipcode;

    @FXML
    private TextField address;

    @FXML
    private Button submitprofile;

    @FXML
    private CheckBox male;

    @FXML
    private CheckBox female;

    @FXML
    private CheckBox lgbtq;
    
    @FXML
    private ComboBox<String> province;

    @FXML
    private Label errordatainsert;

    @FXML
    private Label newdie;

    @FXML
    private Label newthai;

    @FXML
    private Label newtotal;

    @FXML
    private Label thai;

    @FXML
    private Label total;

    @FXML
    private Label die;

    @FXML
    private Label dateupdate;

    public String datenowatk;

    @FXML
    private Label realname;

    @FXML
    private ImageView realprofile;



    @FXML
    private Label showgender;

    @FXML
    private Label showname;

    @FXML
    private Label showprovice;

    @FXML
    private Label showaddress;

    @FXML
    private Label showsurname;

    @FXML
    private Label showtel;

    @FXML
    private Label showzipcode;

    @FXML
    private VBox changepane;

    @FXML
    private VBox showpane;






    private User_db db = new User_db();

   
    //@FXML
    //AnchorPane 
   
    public void setLabelText(String text){
        userfromc1 = text;
        System.out.println(userfromc1);
        beforeredata();
        //System.out.println(userfromc1);
        //this.textfield = textfield;
    }

    public void setdataforshow(){

        DB user = new User_db();
        HashMap<String,String> userInfo = ((User_db) user).getUser(userfromc1);

        showname.setText("Name: "+userInfo.get("firstname"));
        showsurname.setText("Surname: "+userInfo.get("surname"));
        showgender.setText("Gender: "+userInfo.get("sex"));
        showtel.setText("Tel: "+userInfo.get("phone"));
        showaddress.setText("ADDY: "+userInfo.get("address"));
        showzipcode.setText("Code: "+userInfo.get("zipcode"));
        showprovice.setText("Province: "+userInfo.get("province"));
    }

    public void setcalling(){
        DB user = new User_db();
        HashMap<String,String> userInfo = ((User_db) user).getUser(userfromc1);
        if(userInfo.get("firstname") != null && userInfo.get("surname") != null)
        {
            realname.setText(userInfo.get("firstname") + " " + userInfo.get("surname"));
        }
        else{
            realname.setText(userfromc1);
        }
    }

    public void setpro(){
        Img_db img = new Img_db();
        
        //File files1 = new File( System.getProperty("user.dir") + File.separator + "img" + File.separator + userfromc1+".jpg");
       // Image imaget = new Image(files1.toURI().toString());
        
        //File files2 = new File("C:/Users/DELL/OneDrive - KMITL/Documents/javatest/test01/HelloFX/profile.png");
        //Image imagef = new Image(files2.toURI().toString());
       
        if(img.getImg(userfromc1)){
            File filess1 = new File( System.getProperty("user.dir") + File.separator + "img" + File.separator + userfromc1+".jpg");
            Image imaget = new Image(filess1.toURI().toString());
            realprofile.setImage(imaget);

          /*  File doi = new File("C:/Users/DELL/OneDrive - KMITL/Documents/javatest/test01/HelloFX/profile.png");
            Image imagef = new Image(doi.toURI().toString());
            realprofile.setImage(imagef);*/
        }
        else{
            File filess2 = new File(Paths.get("user.png").toString());
            Image imagef = new Image(filess2.toURI().toString());
            realprofile.setImage(imagef);
            

           /* File dop = new File( System.getProperty("user.dir") + File.separator + "img" + File.separator + userfromc1+".jpg");
            Image imaget = new Image(dop.toURI().toString());
            realprofile.setImage(imaget);*/
        }
    }
    
    public void beforeredata(){
        DB user = new User_db();
        DB info = new Info_db();

        String province = (((User_db) user).getUser(userfromc1)).get("province");

        System.out.println(userfromc1+"1");

        HashMap<String, String> speficInfo = ((Info_db) info).getApiProvince(province);
        System.out.println(province);
        total.setText(speficInfo.get("total_case"));
        newtotal.setText("[เพิ่มขึ้น "+speficInfo.get("new_case")+" ราย]");
        dateupdate.setText("ข้อมูล ณ วันที่ "+speficInfo.get("update_date")+" น.");
        thai.setText(speficInfo.get("total_case_excludeabroad"));
        newthai.setText("[เพิ่มขึ้น "+speficInfo.get("new_case_excludeabroad")+" ราย]");
        die.setText(speficInfo.get("total_death"));
        newdie.setText("[เพิ่มขึ้น "+speficInfo.get("new_death")+" ราย]");

        setpro();
        setcalling();
        setdataforshow();

       /* HashMap<String,String> userInfo = ((User_db) user).getUser(userfromc1);
        name.setText(userInfo.get("firstname"));
        surname.setText(userInfo.get("surname"));
        tel.setText(userInfo.get("phone"));
        zipcode.setText(userInfo.get("zipcode"));
        address.setText(userInfo.get("address"));
        province.setValue(userInfo.get("province"));
        ไว้ค่อยมาทำนะ 
      */

    }

    @FXML
    void initialize()  {

        /*
        total.setText(arg0);
        */
        
        

        province.setVisibleRowCount(7);
        province.getItems().addAll(
            "เชียงราย" ,"เชียงใหม่","น่าน","พะเยา" ,"แพร่" ,"แม่ฮ่องสอน","ลำปาง","ลำพูน","อุตรดิตถ์"
            ,"กาฬสินธุ์" ,"ขอนแก่น","ชัยภูมิ","นครพนม" ,"นครราชสีมา" ,"บึงกาฬ","บุรีรัมย์","มหาสารคาม" ,"มุกดาหาร","ยโสธร","ร้อยเอ็ด"
            ,"เลย"
            ,"สกลนคร","สุรินทร์","ศรีสะเกษ" ,"หนองคาย","หนองบัวลำภู","อุดรธานี","อุบลราชธานี" ,"อำนาจเจริญ"
            ,"กรุงเทพมหานคร","กำแพงเพชร ","ชัยนาท ","นครนายก" ,"นครปฐม ","นครสวรรค์" ,"นนทบุรี ","ปทุมธานี ","พระนครศรีอยุธยา"
             ,"พิจิตร " ,"พิษณุโลก" 
            ,"เพชรบูรณ์" ,"ลพบุรี","สมุทรปราการ" ,"สมุทรสงคราม" ,"สมุทรสาคร","สิงห์บุรี" ,"สุโขทัย"  ,"สุพรรณบุรี"  ,"สระบุรี"  ,"อ่างทอง" 
            ,"อุทัยธานี" ,"จันทบุรี","ฉะเชิงเทรา","ชลบุรี","ตราด","ปราจีนบุรี","ระยอง","สระแก้ว","กาญจนบุรี"
            ,"ตาก","ประจวบคีรีขันธ์","เพชรบุรี","ราชบุรีกระบี่","ชุมพร","ตรัง","นครศรีธรรมราช","นราธิวาส","ปัตตานี","พังงา"
            ,"พัทลุง","ภูเก็ต","ระนอง"
            ,"สตูล","สงขลา","สุราษฎร์ธานี","ยะลา"
            );
        
        x.setLabel("จังหวัด");
        x.setStyle("-fx-font-size: " + 15 + "px;");
        y.setLabel("ยอดผู้ติดเชื้อ");  
        y.setStyle("-fx-font-size: " + 15 + "px;");

    
      /*  Info_db db = new Info_db();

        DB user = new User_db();
        String 

        if(provicedb == null){
            db.getApiProvince();
        }
        else{

        }
        */
/*
        DB user = new User_db();
        DB info = new Info_db();

        String province = (((User_db) user).getUser("username")).get("province");

        HashMap<String, String> speficInfo = ((Info_db) info).getApiProvince(province); 
*/
        
       


        Tranfer tranfer = new Info_tranfer();

        ArrayList<Information> topten = tranfer.head();

        
        XYChart.Series pro1 = new XYChart.Series();
        pro1.setName("อันดับ 1");
        pro1.getData().add(new XYChart.Data(topten.get(9).getLabel("province")+"\n"+topten.get(9).getLabel("new_case"), Integer.parseInt(topten.get(9).getLabel("new_case"))));

        XYChart.Series pro2 = new XYChart.Series();
        pro2.setName("อันดับ 2");
        pro2.getData().add(new XYChart.Data(topten.get(8).getLabel("province")+"\n"+topten.get(8).getLabel("new_case"), Integer.parseInt(topten.get(8).getLabel("new_case"))));

        XYChart.Series pro3 = new XYChart.Series();
        pro3.setName("อันดับ 3");
        pro3.getData().add(new XYChart.Data(topten.get(7).getLabel("province")+"\n"+topten.get(7).getLabel("new_case"), Integer.parseInt(topten.get(7).getLabel("new_case"))));

        XYChart.Series pro4 = new XYChart.Series();
        pro4.setName("อันดับ 4");
        pro4.getData().add(new XYChart.Data(topten.get(6).getLabel("province")+"\n"+topten.get(6).getLabel("new_case"), Integer.parseInt(topten.get(6).getLabel("new_case"))));

        XYChart.Series pro5 = new XYChart.Series();
        pro5.setName("อันดับ 5");
        pro5.getData().add(new XYChart.Data(topten.get(5).getLabel("province")+"\n"+topten.get(5).getLabel("new_case"), Integer.parseInt(topten.get(5).getLabel("new_case"))));

        XYChart.Series pro6 = new XYChart.Series();
        pro6.setName("อันดับ 6");
        pro6.getData().add(new XYChart.Data(topten.get(4).getLabel("province")+"\n"+topten.get(4).getLabel("new_case"), Integer.parseInt(topten.get(4).getLabel("new_case"))));

        XYChart.Series pro7 = new XYChart.Series();
        pro7.setName("อันดับ 7");
        pro7.getData().add(new XYChart.Data(topten.get(3).getLabel("province")+"\n"+topten.get(3).getLabel("new_case"), Integer.parseInt(topten.get(3).getLabel("new_case"))));

        XYChart.Series pro8 = new XYChart.Series();
        pro8.setName("อันดับ 8");
        pro8.getData().add(new XYChart.Data(topten.get(2).getLabel("province")+"\n"+topten.get(2).getLabel("new_case"), Integer.parseInt(topten.get(2).getLabel("new_case"))));

        XYChart.Series pro9 = new XYChart.Series();
        pro9.setName("อันดับ 9");
        pro9.getData().add(new XYChart.Data(topten.get(1).getLabel("province")+"\n"+topten.get(1).getLabel("new_case"), Integer.parseInt(topten.get(1).getLabel("new_case"))));

        XYChart.Series pro10 = new XYChart.Series();
        pro10.setName("อันดับ 10");
        pro10.getData().add(new XYChart.Data(topten.get(0).getLabel("province")+"\n"+topten.get(0).getLabel("new_case"), Integer.parseInt(topten.get(0).getLabel("new_case"))));

        barchart.setLegendVisible(false);
        barchart.getData().addAll(pro1,pro2,pro3,pro4,pro5,pro6,pro7,pro8,pro9,pro10);

     
        //barchart.getData().add(python);  
        //XYChart.Series java = new XYChart.Series<>();  
         //java.getData().add(new XYChart.Data<>("Jan",10000));  
        

        File filel1 = new File(Paths.get("l1.jpg").toString());
        Image imagel1 = new Image(filel1.toURI().toString());
        File filel2 = new File(Paths.get("l2.jpg").toString());
        Image imagel2 = new Image(filel2.toURI().toString());
        File filel3 = new File(Paths.get("l3.jpg").toString());
        Image imagel3 = new Image(filel3.toURI().toString());

        File files1 = new File( Paths.get("s1.jpg").toString());
        Image images1 = new Image(files1.toURI().toString());
        File files2 = new File( Paths.get("s2.jpg").toString());
        Image images2 = new Image(files2.toURI().toString());
        File files3 = new File( Paths.get("s3.jpg").toString());
        Image images3 = new Image(files3.toURI().toString());

        Timer timer = new Timer();

        timer.schedule( new TimerTask() {
            public void run() {
                Thread t=new Thread(new Runnable() {
                    public void run(){
                    try
                    {
                    Thread.sleep(10000);
                    inforlong.setImage(imagel1);
                    inforshort.setImage(images1);
                    Thread.sleep(10000);
                    inforlong.setImage(imagel2);
                    inforshort.setImage(images2);
                    Thread.sleep(10000);
                    inforlong.setImage(imagel3);
                    inforshort.setImage(images3);
                    }
                    catch(Exception e)
                    {  }
                    }
                });
                t.start();
            }
         }, 0, 30000);
           
    }

    
   
    
    public void switchToScene1(ActionEvent event) throws IOException {
             
        Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void savedataprofile(ActionEvent event) {
        // เอาข้อมูลที่กรอกในtext มาแอดเข้า db   
        //Controller d = new Controller();
        db.update("username", userfromc1, "firstname", name.getText());
        db.update("username", userfromc1, "surname", surname.getText());
        db.update("username", userfromc1, "sex", gender);
        db.update("username", userfromc1, "phone", tel.getText());
        db.update("username", userfromc1, "zipcode", zipcode.getText());
        db.update("username", userfromc1, "address", address.getText());
        db.update("username", userfromc1, "province", province.getValue());

        setcalling();
        System.out.println(userfromc1);
        System.out.println(province.getValue());
        setdataforshow();
        changepane.toFront();

       // showaddress.setText("ssss");
        /*  name.getText()
            surname.getText()
            tel.getText()
            zipcode.getText()
            address.getText()
            province.getValue()
            gender เป็น string อยู่แล้ว


        */ 
    }

    @FXML
    public void getDate(ActionEvent event) {
        LocalDate atk = dateatk.getValue();
        datenowatk = atk.toString();
        System.out.println(datenowatk); // printdate เฉยๆ
    }

 //   final FileChooser fileChooser = new FileChooser();
    
    public void uploadimage(final ActionEvent e){
        FileChooser fileChooser = new FileChooser();
            
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
         
        //Show open file dialog
        
        File file = fileChooser.showOpenDialog(null);
                  
        try {
            filesend = file.getPath().toString();
            java.awt.image.BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            showprofile.setImage(image);
            
             
        } catch (IOException ex) {
           // Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void uploadatk(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
            
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
         
        //Show open file dialog
        
        File file = fileChooser.showOpenDialog(null);
                  
        try {
            fileatksend = file.getPath().toString();
            java.awt.image.BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            showatk.setImage(image);
            
             
        } catch (IOException ex) {
           // Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void sendatk(ActionEvent event) {
        Img_db upload = new Img_db();
        User_db user = new User_db();
        user.update("username",userfromc1,"status", "positive");
        user.update("username",userfromc1,"date",  datenowatk);
        System.out.println(upload.upload(filesend,userfromc1+"ATK"));

    }



    public void saveimage(final ActionEvent e){
        Img_db upload = new Img_db();
        //User_db user = new User_db();
        //user.update("status", "positive");
        System.out.println(upload.upload(filesend,userfromc1));

        setpro();
        

    }
    @FXML
    void woman(ActionEvent event) {
        male.setSelected(false);
        lgbtq.setSelected(false);
        gender = "female";
    }

    @FXML
    void lgbtq(ActionEvent event) {
        female.setSelected(false);
        male.setSelected(false);
        gender = "lgbtq+";
    }

    @FXML
    void man(ActionEvent event) {
        female.setSelected(false);
        lgbtq.setSelected(false);
        gender = "male";
    }


    public void profile(){
        profilepane.toFront();

    }

    public void report(){
        reportpane.toFront();
    }

    
    public void change(ActionEvent event) {
        showpane.toFront();
    }


    
    public void setting(ActionEvent e)  throws IOException  {
       /* if(//เช็คroleว่าเป็นใครถ้าmemberเข้าif){
            //
        }
        else{
            switchTosettingadmin(e);
        }*/
    }

    public void switchTosettingadmin (ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void overview(){
        overviewpane.toFront();
    }
  
}