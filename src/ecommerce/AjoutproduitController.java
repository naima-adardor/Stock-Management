package ecommerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AjoutproduitController implements Initializable {
	  @FXML
	    private ComboBox<String> combocateg;

	    @FXML
	    private ComboBox<String> combocolor;

	    @FXML
	    private ComboBox<String> combomarque;

	    @FXML
	    private ComboBox<String> combopromo;

	    @FXML
	    private ComboBox<String> combosize;

	    @FXML
	    private ComboBox<String> combosubcateg;

	    @FXML
	    private ImageView img1;

	    @FXML
	    private ImageView img2;

	    @FXML
	    private ImageView img3;

	    @FXML
	    private Button parcourir1;

	    @FXML
	    private Button parcourir2;

	    @FXML
	    private Button parcourir3;

	    @FXML
	    private TextArea txtdescription;

	    @FXML
	    private TextField txtdesignation;

	    @FXML
	    private TextField txtoldprice;

	    @FXML
	    private TextField txtprice;

	    @FXML
	    private TextField txtquantity;
	    @FXML
	    private Label lbl1;

	    @FXML
	    private Label lbl2;

	    @FXML
	    private Label lbl3;
	    ObservableList<String> data;
	    ObservableList<String> list;

	    public void  remplire_combo(){
	     	Connection connection = classconnection.getDBConnection();
			String sql="select name_categ from categorie";
			List<String>  nom =new ArrayList<String>();
			try {
				Statement stmt = connection.prepareStatement(sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      while(rs.next()) {
			    	  nom.add(rs.getString("name_categ"));
			      }
			}catch(SQLException e) {
				e.printStackTrace();
			}
			combocateg.setItems(FXCollections.observableArrayList(nom));
			
		  }
	    public void  remplire_comboo(){
	     	Connection connection = classconnection.getDBConnection();
			String sql="select name from color";
			List<String>  nom =new ArrayList<String>();
			try {
				Statement stmt = connection.prepareStatement(sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      while(rs.next()) {
			    	  nom.add(rs.getString("name"));
			      }
			}catch(SQLException e) {
				e.printStackTrace();
			}
			combocolor.setItems(FXCollections.observableArrayList(nom));
			
		  }
	    public void  remplire_combooo(){
	     	Connection connection = classconnection.getDBConnection();
			String sql="select description from promotion";
			List<String>  nom =new ArrayList<String>();
			try {
				Statement stmt = connection.prepareStatement(sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      while(rs.next()) {
			    	  nom.add(rs.getString("description"));
			      }
			}catch(SQLException e) {
				e.printStackTrace();
			}
			combopromo.setItems(FXCollections.observableArrayList(nom));
			
		  }
	    public void  remplire_comboooo(){
	     	Connection connection = classconnection.getDBConnection();
			String sql="select designation_sub_categ from sub_categorie";
			List<String>  nom =new ArrayList<String>();
			try {
				Statement stmt = connection.prepareStatement(sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      while(rs.next()) {
			    	  nom.add(rs.getString("designation_sub_categ"));
			      }
			}catch(SQLException e) {
				e.printStackTrace();
			}
			combosubcateg.setItems(FXCollections.observableArrayList(nom));
			
		  }
	    public void  remplire_size(){
	    
			
		  }
	    public void  remplire_combooooo(){
	     	Connection connection = classconnection.getDBConnection();
			String sql="select name_brand from brand";
			List<String>  nom =new ArrayList<String>();
			try {
				Statement stmt = connection.prepareStatement(sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      while(rs.next()) {
			    	  nom.add(rs.getString("name_brand"));
			      }
			}catch(SQLException e) {
				e.printStackTrace();
			}
			combomarque.setItems(FXCollections.observableArrayList(nom));
		}
	    @FXML
	    void showOpenDialog(ActionEvent event) {
	    	FileChooser fc = new FileChooser();
			fc.getExtensionFilters().add(new ExtensionFilter("image Files","*.jpeg", "*.png", "*.jpg"));
			File f = fc.showOpenDialog(null);
			if (f != null) {
				lbl1.setText(f.getAbsolutePath());
				Image image = new Image(f.toURI().toString(), img1.getFitWidth(), img1.getFitHeight(),
						true, true);
				img1.setImage(image);
			}
	     } @FXML
	    void showOpenDialo(ActionEvent event) {
	    	FileChooser fc = new FileChooser();
			fc.getExtensionFilters().add(new ExtensionFilter("image Files","*.jpeg", "*.png", "*.jpg"));
			File f = fc.showOpenDialog(null);
			if (f != null) {
				lbl2.setText(f.getAbsolutePath());
				Image image = new Image(f.toURI().toString(), img2.getFitWidth(), img2.getFitHeight(),
						true, true);
				img2.setImage(image);
			}
	     } @FXML
	    void showOpenDial(ActionEvent event) {
	    	FileChooser fc = new FileChooser();
			fc.getExtensionFilters().add(new ExtensionFilter("image Files","*.jpeg", "*.png", "*.jpg"));
			File f = fc.showOpenDialog(null);
			if (f != null) {
				lbl3.setText(f.getAbsolutePath());
				Image image = new Image(f.toURI().toString(), img3.getFitWidth(), img3.getFitHeight(),
						true, true);
				img3.setImage(image);
			}
	     }
	    @FXML
	    void ADD(ActionEvent event) throws SQLException, FileNotFoundException {
	    	String designation=txtdesignation.getText();
	    	String description=txtdescription.getText();
	    	 File image = new File(lbl1.getText());
	    	 File imagee = new File(lbl2.getText());
	    	 File imageee = new File(lbl3.getText());
	    	  String categ=combocolor.getSelectionModel().getSelectedItem();
	    	  String catego=combosize.getSelectionModel().getSelectedItem();
	    	  String categor=combocateg.getSelectionModel().getSelectedItem();
	    	  String categori=combosubcateg.getSelectionModel().getSelectedItem();
	    	  String categorie=combopromo.getSelectionModel().getSelectedItem();
	    	  String categoriee=combomarque.getSelectionModel().getSelectedItem();
		      int quantite=Integer.parseInt(txtquantity.getText());
		     Float prix=Float.parseFloat(txtprice.getText());
		     Float oldpprice=Float.parseFloat(txtoldprice.getText());
		     if(designation.isEmpty()) {
			       Alert alert = new Alert(Alert.AlertType.ERROR);
			       alert.setTitle("Error");
			     	  alert.setHeaderText("Attention !!");
			         alert.setContentText(" Remplir tous les champs");
			         alert.showAndWait();
			         }else {
			        		PRODUIT P=new PRODUIT(designation,description,quantite,prix,oldpprice,categorie,catego,categor,categori,categoriee,categ);
			        	       //	txtnom.setText("");
			        	    	//insert(C);
			        	       	Connection connection =classconnection.getDBConnection();
			        			String sql="INSERT INTO produit (designation,description,id_brand,id_categ,id_color,id_promotion,id_sub_categ,image1,image2,image3,oldprice,prix,size,stock) VALUES (?,?,(select id_brand from brand where name_brand ='"+P.getBrand()+"'),(select id_categ from categorie where name_categ ='"+P.getCategorie()+"'),(select id_color from color where name='"+P.getColor()+"'),(select id_promotion from promotion where description='"+P.getPromotion()+"'),(select id_sub_categ from sub_categorie where designation_sub_categ='"+P.getSubcategorie()+"'),?,?,?,?,?,?,?)"; 
			        			PreparedStatement ps=connection.prepareStatement(sql);
			        			FileInputStream fs = new FileInputStream(image);
			        			FileInputStream fe = new FileInputStream(imagee);
			        			FileInputStream ff = new FileInputStream(imageee);
			        			ps.setString(1,designation);
			        			ps.setString(2,description);
			                    ps.setBinaryStream(3,fs, image.length());
			        			ps.setBinaryStream(4,fe, imagee.length());
			        			ps.setBinaryStream(5,ff, imageee.length());
			        			ps.setFloat(6, oldpprice);
			        			ps.setFloat(7, prix);
			        			ps.setString(8,P.getSize());
			        			ps.setInt(9,quantite);
			        			
			        			
			        			
			        		    ps.execute();
			        		  //  txtdesignation.setText("");
			        		   // img1.setImage(null)
			        		    
			        		    
			        			//showListeMarque();
			        			
			        	       	 Alert aler = new Alert(AlertType.INFORMATION);
			        	            aler.setTitle("Ajout Produit"); 
			        	            aler.setHeaderText(null);
			        	            aler.setContentText(" Produit ajoutée avec succés");
			        	         
			        	            aler.showAndWait();
			        	 
			         }
	    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		remplire_combo();
		remplire_combooooo();
		remplire_comboooo();
		remplire_combooo();
		remplire_comboo();
		ObservableList<String> List= FXCollections.observableArrayList("S","M","Xs","L","XL","36","37","38","39");
		combosize.setItems(List);
	}

}
