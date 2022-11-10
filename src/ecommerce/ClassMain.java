package ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class ClassMain extends Application {

@Override
	public void start(Stage primaryStage)  {
		// TODO Auto-generated method stub
		try {
			primaryStage.initStyle(StageStyle.UTILITY);
			primaryStage.setResizable(false);
			Parent root = FXMLLoader.load(getClass().getResource("connecter.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
public static void main(String[] args) {
	// TODO Auto-generated method stub
	launch(args);
	
}

}
