package ecommerce;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class seConnecterController implements Initializable {

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;
    @FXML
    void seconnecter(ActionEvent event) {
	Window owner=txt_username.getScene().getWindow();
    	
    	if(txt_username.getText().isEmpty()) {
    		showAlert(Alert.AlertType.ERROR,owner,"Veuillez remplir le champs ' nom d'utilisateur '","Erreur");
    	return;
    	}
    	if(txt_password.getText().isEmpty()) {
    		showAlert(Alert.AlertType.ERROR,owner,"Veuillez remplir le champs ' mot de passe '"," Erreur");
    	return;
    	}
    
    	String nom=txt_username.getText();
    	String pass=txt_password.getText();
    
    	boolean flag =classconnection.validate(nom,pass);
    	
    	if(!flag) {
    		infoBox("Veuillez saisir un nom d'utilisateur  et un mot de passe valide ",null,"Erreur");
    		
    		
    	}else {
    		infoBox("connection avec succée",null,"Succés");
    	
    		try {
    			owner.hide();
    			FXMLLoader fxmlloader	= new FXMLLoader(getClass().getResource("Main.fxml"));
    			Parent root=fxmlloader.load();
    			Stage stage =new Stage();
    			stage.setScene(new Scene(root));	
    			stage.initStyle(StageStyle.UTILITY);

    			stage.show();
    		}catch(IOException e){
    			e.printStackTrace();
            }
    		
   }
    }
    public static void infoBox(String infoMessage,String headerText,String title) {
  	  Alert alert =new Alert(AlertType.INFORMATION);
  	  alert.setContentText(infoMessage);
  	  alert.setTitle(title);
  	  alert.setHeaderText(headerText);
  	  alert.showAndWait();
    }
    public static void showAlert(Alert.AlertType alertType,Window owner ,String message,String title) {
  	  Alert alert =new Alert(alertType);
  	  alert.setContentText(message);
  	  alert.setTitle(title);
  	  alert.setHeaderText(null);
  	  alert.initOwner(owner);
  	  alert.show();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
