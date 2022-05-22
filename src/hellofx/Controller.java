package hellofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.text.TextAlignment;
import javafx.scene.control.*;

import java.io.IOException;

//import hellofx.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javafx.scene.Cursor;
import javafx.scene.Node;




import javafx.event.*;
//import javafx.scene.*;

public class Controller extends Main {

    private Stage stage;
    private Scene scene;
   // private Parent root;

    @FXML
    private Label output;
    @FXML
    private Label outputup;
    @FXML
    public Button loginBtn;
    @FXML
    private TextField inputUser;
    @FXML
    private PasswordField inputPass;
    @FXML
    private AnchorPane pageSignin;
    @FXML
    private AnchorPane pageSignup;
    @FXML
    private TextField inputMail;
    @FXML
    private TextField inputUserup;
    @FXML
    private PasswordField inputPassup;
    @FXML
    private PasswordField inputPassRe;

    private  Auth auth = new Auth();

    private String usernow;

   /* public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    }*/

    public void login (ActionEvent event) throws IOException{

        System.out.println(inputUser.getText());
        usernow = inputUser.getText();
        System.out.println(inputPass.getText());


        


        output.setText("");
        

      // if (auth.init())
     //  {
            //Main.getScene().setCursor(Cursor.WAIT);
            //stage.getScene().getRoot().setCursor(Cursor.WAIT);
            if(auth.login(inputUser.getText(), inputPass.getText()) > 0){
                
                switchToScene2(event);
                //scene.setCursor(Cursor.DEFAULT);
                output.setText("");
                inputUser.setText("");
                inputPass.setText("");
            }
            else{
                output.setText("Incorrect Password or Username");
                inputPass.setText("");
              //  scene.setCursor(Cursor.DEFAULT);
            }  
      //  }
    }

    public void signUp (Event e){
      //  System.out.println(inputMail.getText());
      //  System.out.println(inputUserup.getText());
      //  System.out.println(inputPassup.getText());
      //  System.out.println(inputPassRe.getText());
      outputup.setText("");
      // if (auth.init())
      //  {
            if(auth.repeatCheck(inputPassup.getText(),inputPassRe.getText()))
            {
                if (auth.register(inputMail.getText(), inputUserup.getText(), inputPassup.getText()))
                {
                    inputMail.setText("");
                    inputUserup.setText("");
                    inputPassup.setText("");
                    inputPassRe.setText("");
                    System.out.println("ok");

                    outputup.setText("SignUp Successful");
                }
                else
                {
                    System.out.println("error");
                }
            }
            else{

            }
      //  }  
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        


        FXMLLoader loader = new FXMLLoader(getClass().getResource("SceneTwo.fxml"));
       
       Parent root = loader.load();
       // Parent root = FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));

        scene2Controller controller2 =  loader.getController();

        controller2.setLabelText(usernow);
      //  scene2Controller controller2 =  root.load();
       // controller2.beforeredata();
      //  controller2.setLabelText(inputUser.getText());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signupPage(){
        pageSignup.toFront();
    }
    public void signinPage(){
        pageSignin.toFront();
    }

    
}