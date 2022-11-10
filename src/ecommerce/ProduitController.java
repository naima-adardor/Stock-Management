package ecommerce;

import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import ecommerce.AjoutproduitController;
public class ProduitController implements Initializable {
	@FXML
    private TableColumn<PRODUIT,String> colcategorie;

    @FXML
    private TableColumn<PRODUIT,String> coldesignation;

    @FXML
    private TableColumn<PRODUIT,Float> colprix;

    @FXML
    private TableColumn<PRODUIT,Integer> colquantite;

    @FXML
    private TableView<PRODUIT> tableproduit;

    @FXML
    private TextField textfieldrecherche;


    @FXML
    private Parent fxml;
    @FXML
    private AnchorPane root;

    
    ObservableList<PRODUIT> data;
    ObservableList<PRODUIT> list;
    
 
    @FXML
    void Ajouterprod(ActionEvent event) {
    	try {
			OpenWindow("AjouterProduit.fxml");
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

    private ObservableList<PRODUIT> getAll() {
		// TODO Auto-generated method stub
    	Connection connection = classconnection.getDBConnection();
        ObservableList<PRODUIT>list = FXCollections.observableArrayList();
        try{
			Statement stmt = connection.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT designation ,prix,stock,name_categ FROM produit P, categorie C where P.id_categ=C.id_categ");
            while(rs.next()){
                list.add(new PRODUIT(rs.getString("designation"),Integer.parseInt(rs.getString("stock")),Float.parseFloat(rs.getString("prix")),rs.getString("name_categ")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
    int myIndex ;
    int id;
     @FXML
    void Deleteproduct(ActionEvent event) throws SQLException {
    	/* int selectedIndex = tableproduit.getSelectionModel().getSelectedIndex();
    	    if (selectedIndex >= 0) {
    	    	tableproduit.getItems().remove(selectedIndex);

    	 PRODUIT P=tableproduit.getSelectionModel().getSelectedItem();
    	 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmer la suppression");
         alert.setContentText("Ce produit va etre supprimer , Voulez vous vraiment le supprimer?");
         Optional<ButtonType> result = alert.showAndWait();
         if(result.get() == ButtonType.OK) {
        	 Connection connection = classconnection.getDBConnection();
        	    String sql ="delete from produit where id_produit=?";
		         PreparedStatement pst= connection.prepareStatement(sql);
		         pst.setInt(1,P.getId());
		         pst.executeUpdate();
		         showListeProduit();
		         
             Alert ale = new Alert(AlertType.INFORMATION);
	            ale.setTitle("Suppression Produit");
	            ale.setHeaderText(null);
	            ale.setContentText(" Produit supprimé avec succés");
	            ale.showAndWait();
         }}*/
    	 myIndex = tableproduit.getSelectionModel().getSelectedIndex();
          id = Integer.parseInt(String.valueOf(tableproduit.getItems().get(myIndex).getId()));
           try
        { Connection connection = classconnection.getDBConnection();
        	
            PreparedStatement  pst =  connection.prepareStatement("delete from produit where id_produit = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deletion");
 
            alert.setHeaderText("Delet product");
            alert.setContentText("Deletedd!");
 
            alert.showAndWait();
            showListeProduit() ;
        }
        catch (SQLException ex)
        {
        	 ex.printStackTrace();
        }
 
    }
    //int index =-1;
    @FXML
    void selectionner(MouseEvent event) {
    	/*PRODUIT P=tableproduit.getSelectionModel().getSelectedItem();
    	
    	//AjoutproduitController A=new AjoutproduitController();
    	txtdesignation.setText(P.getDesignation());
    	txtdescription.setText(P.getDescription());
    	txtquantity.setText(String.valueOf(P.getQuantite()));
    	txtprice.setText(String.valueOf(P.getPrix()));
    	txtoldprice.setText(String.valueOf(P.getOldprice()));
    	combocateg.setValue(P.getCategorie());
    	combosize.setValue(P.getCategorie());
    	combosubcateg.setValue(P.getSubcategorie());
    	combomarque.setValue(P.getBrand());
    	combopromo.setValue(P.getPromotion());
    
    	combocolor.setValue(P.getColor());*/
    
     	myIndex=tableproduit.getSelectionModel().getFocusedIndex();
    	if(myIndex<=-1) {
        		return;
        	}
  
    	
    }

    @FXML
    void EditProduct(MouseEvent event) {
    	try {
			OpenWindow("ModifierProduit.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void search_produit() {
		ObservableList<PRODUIT>list = getAll ();
		 coldesignation.setCellValueFactory(new PropertyValueFactory<PRODUIT,String>("Designation"));
		 colquantite.setCellValueFactory(new PropertyValueFactory<PRODUIT,Integer>("Quantite"));
		 colprix.setCellValueFactory(new PropertyValueFactory<PRODUIT,Float>("prix"));
		 colcategorie.setCellValueFactory(new PropertyValueFactory<PRODUIT,String>("Categorie"));
		 
		   tableproduit.setItems(list);
   	
	FilteredList<PRODUIT> filteredData =new FilteredList<>(list,b->true);
	textfieldrecherche.textProperty().addListener((observable,oldValue,newValue)->{
		filteredData.setPredicate(PRODUIT->{
		if(newValue==null|| newValue.isEmpty()) {
			return true;
		}
		String lowerCaseFilter =newValue.toLowerCase();
		if(PRODUIT.getDesignation().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
			return true;
		}
		if(PRODUIT.getCategorie().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
			return true;
		}
	
		
		else {
		return false;
		}});
	});
	SortedList<PRODUIT> sorteddata=new SortedList<>(filteredData);
	//SortedList.comparatorProperty().bind(table.comparatorProperty());
	sorteddata.comparatorProperty().bind(tableproduit.comparatorProperty());
	tableproduit.setItems(sorteddata);}
    private String testOblig() {
		// TODO Auto-generated method stub
		return null;
	}
	private void showListeProduit() {
		// TODO Auto-generated method stub
		ObservableList<PRODUIT>list = getAll();
	    coldesignation.setCellValueFactory(new PropertyValueFactory<PRODUIT,String>("Designation"));
		colprix.setCellValueFactory(new PropertyValueFactory<PRODUIT,Float>("prix"));
		colquantite.setCellValueFactory(new PropertyValueFactory<PRODUIT,Integer>("Quantite"));
		colcategorie.setCellValueFactory(new PropertyValueFactory<PRODUIT,String>("categorie"));
		tableproduit.setItems(list);
}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 showListeProduit();
		 search_produit();
	}

}
