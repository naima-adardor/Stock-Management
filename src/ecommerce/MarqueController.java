package ecommerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MarqueController  implements Initializable{

    @FXML
    private Button btnparcourir;

    @FXML
    private TableColumn<MARQUE, String> coldesignation;

    @FXML
    private TableColumn<MARQUE, Integer> colid;

    @FXML
    private ImageView img;
    @FXML
    private AnchorPane anch;
    @FXML
    private TableView<MARQUE> table;

    @FXML
    private TextField txtnom;
    @FXML
    private Label Lbl_url;
    @FXML
    private TextField textfileldrecherche;
    @FXML
    void AjoutMa(ActionEvent event) throws SQLException, FileNotFoundException {
    	 String nom=txtnom.getText();
    	 File image = new File(Lbl_url.getText());
    	
         if(nom.isEmpty()) {
   	       Alert alert = new Alert(Alert.AlertType.ERROR);
   	       alert.setTitle("Error");
   	     	  alert.setHeaderText("Attention !!");
   	         alert.setContentText(" Remplir tous les champs");
   	         alert.showAndWait();
   	         }else {

       	MARQUE C=new MARQUE(nom);
       //	txtnom.setText("");
    	//insert(C);
       	Connection connection =classconnection.getDBConnection();
		
		
		String sql="INSERT INTO brand (name_brand,image) VALUES (?,?)"; 
		PreparedStatement ps=connection.prepareStatement(sql);
		FileInputStream fs = new FileInputStream(image);
		ps.setString(1, nom);
		
		ps.setBinaryStream(2,fs, image.length());
	    ps.execute();
	    txtnom.setText("");
	    img.setImage(null);
	    showListeMarque();
		
       	 Alert aler = new Alert(AlertType.INFORMATION);
            aler.setTitle("Ajout Marque"); 
            aler.setHeaderText(null);
            aler.setContentText(" Marque ajoutée avec succés");
         
            aler.showAndWait();
      // 	getAll();
       	  
       }
    }

    @FXML
    void search_marque() {
    	String sql="select name_brand,image from brand where name_brand='"+textfileldrecherche.getText()+"'";
		int m=0;
		try {
			Connection connection =classconnection.getDBConnection();
			PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet result=ps.executeQuery();
			 //FileInputStream fs = new FileInputStream();
			 if(result.next()) {

			    	txtnom.setText(result.getString("name_brand"));
			    	//img.setBinaryStream(result.getBlob("image"));
			   m=1;
	     }
			
		}catch(SQLException e) {
			e.printStackTrace();
		
		}
		if(m==0) {
			Alert alert=new Alert(AlertType.ERROR,"AuCUNE Marque",javafx.scene.control.ButtonType.OK);
		}
    }
    int myIndex;
    int id;
	@FXML
    void DeleteMa(ActionEvent event) {
		myIndex = table.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                 

      try
      { Connection connection = classconnection.getDBConnection();
      	
      	
        PreparedStatement  pst =  connection.prepareStatement("delete from brand where id_brand = ? ");
          pst.setInt(1, id);
          pst.executeUpdate();
          
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Deletion");

alert.setHeaderText("Brand");
alert.setContentText("Deletedd!");
alert.showAndWait();
showListeMarque();
      }
      catch (SQLException ex)
      {
      	 ex.printStackTrace();
      }


    }
	
    @FXML
    void select(MouseEvent event) {
    	myIndex=table.getSelectionModel().getFocusedIndex();
	if(myIndex<=-1) {
    		return;
    	}
    	//PRODUIT C=tableproduit.getSelectionModel().getSelectedItem();
    		    	
	txtnom.setText(coldesignation.getCellData(myIndex).toString());
    
   
    
    }

    @FXML
    void ModifierMa(ActionEvent event) throws SQLException, FileNotFoundException {
    	 String nom=txtnom.getText();
    	 File image = new File(Lbl_url.getText());
    	
         if(nom.isEmpty()) {
   	       Alert alert = new Alert(Alert.AlertType.ERROR);
   	       alert.setTitle("Error");
   	     	  alert.setHeaderText("Attention !!");
   	         alert.setContentText(" Remplir tous les champs");
   	         alert.showAndWait();
   	         }else {

       	MARQUE C=new MARQUE(nom);
        //txtnom.setText("");
    	//insert(C);
       	Connection connection =classconnection.getDBConnection();
	    String sql="UPDATE brand SET name_brand=?,image=? where name_brand like '"+textfileldrecherche.getText()+"'";
		PreparedStatement ps=connection.prepareStatement(sql);
		FileInputStream fs = new FileInputStream(image);
		ps.setString(1, nom);
	    ps.setBinaryStream(2,fs, image.length());
	    ps.execute();
		showListeMarque();
	    Alert aler = new Alert(AlertType.INFORMATION);
            aler.setTitle("Ajout Marque"); 
            aler.setHeaderText(null);
            aler.setContentText(" Marque Modifier avec succés");
            aler.showAndWait();
      // 	getAll();
       	  
       }
    }
    @FXML
    void showOpenDialog(ActionEvent event) {
    	FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("image Files","*.jpeg", "*.png", "*.jpg"));
		File f = fc.showOpenDialog(null);
		if (f != null) {
			Lbl_url.setText(f.getAbsolutePath());
			Image image = new Image(f.toURI().toString(), img.getFitWidth(), img.getFitHeight(),
					true, true);
			img.setImage(image);
		}
     }
    private ObservableList<MARQUE> getAll() {
		// TODO Auto-generated method stub
    	Connection connection = classconnection.getDBConnection();
        ObservableList<MARQUE>list = FXCollections.observableArrayList();
        try{
			Statement stmt = connection.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT id_brand,name_brand  FROM brand");
            while(rs.next()){
                list.add(new MARQUE(Integer.parseInt(rs.getString("id_brand")),rs.getString("name_brand")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
    private void showListeMarque() {
		// TODO Auto-generated method stub
		ObservableList<MARQUE>list = getAll();
		coldesignation.setCellValueFactory(new PropertyValueFactory<MARQUE,String>("nom"));
		colid.setCellValueFactory(new PropertyValueFactory<MARQUE,Integer>("id"));
		table.setItems(list);
}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showListeMarque();
		
	}

}
