package hellofx;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {

   

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        primaryStage.setTitle("COVID-19 Dashboard");
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("C:/Users/DELL/OneDrive - KMITL/Documents/javatest/test01/HelloFX/icon3.png"));

        
      //  auth.init();
        primaryStage.show();
        //auth.init();

    }
    public static void main(String[] args) {

        launch(args);

        
    }
}
