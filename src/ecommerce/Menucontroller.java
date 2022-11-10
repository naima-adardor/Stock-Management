package ecommerce;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Menucontroller  implements Initializable{
	
	
	
	    @FXML
	    private Parent fxml;
	    @FXML
	    private Button btn_categ;
	    @FXML
	    private Button btn_promo;

	    @FXML
	    private Button btn_produit;
	    @FXML
	    private Button btn_reclamation;

	    @FXML
	    private Button btn_subcateg;
	    @FXML
	    private AnchorPane root;
	    @FXML
	    private Button closebtn;
	    @FXML
	    private Button btn_marque;

	    @FXML
	    void gestcateg(ActionEvent event) {
	    	try {
				OpenWindow("categorie.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    }
	    @FXML
	    void gestsubcateg(ActionEvent event) {
	    	try {
				OpenWindow("Subcategorie.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    @FXML
	   
	    void gestpromo(ActionEvent event) {

	    
	    	try {
				OpenWindow("promotion.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    @FXML
	    void gestreclamation(ActionEvent event) {
	    	try {
				OpenWindow("reclamation.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    

	    @FXML
	    void gestionproduit(ActionEvent event) {
	    	try {
	    		
				OpenWindow("Gestionproduit.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    

	    @FXML
	    void gestmarq(ActionEvent event) {
try {
	    		
				OpenWindow("Marque.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    void OpenWindow(String resource ) throws IOException {
	    	fxml = FXMLLoader.load(getClass().getResource(resource));
	    	root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
				    }
  
	    @FXML
	    void Arreter(ActionEvent event) {
	    	//request.getSession().invalidate();
	    	 Stage stage = (Stage) closebtn.getScene().getWindow();
	    	    stage.close();
	    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
