package ecommerce;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SubCategController implements Initializable {
	 @FXML
	    private TableColumn<SUBCATEGORIE, Integer> cid;

	    @FXML
	    private TableColumn<SUBCATEGORIE, String> cnom;

	    @FXML
	    private TableView<SUBCATEGORIE> table;

	    @FXML
	    private TextField textfiieldrechercher;

	    @FXML
	    private TextField txtnom;
	    ObservableList<SUBCATEGORIE> data;
	    ObservableList<SUBCATEGORIE> list;
	 @FXML
	    void AjoutCateg(ActionEvent event) {
		 String nom=txtnom.getText();
         if(nom.isEmpty()) {
   	       Alert alert = new Alert(Alert.AlertType.ERROR);
   	       alert.setTitle("Error");
   	     	  alert.setHeaderText("Attention !!");
   	         alert.setContentText(" Remplir tous les champs");
   	         alert.showAndWait();
   	         }else {

       	SUBCATEGORIE S=new SUBCATEGORIE(nom);
       	txtnom.setText("");
    	insert(S);
       	 Alert aler = new Alert(AlertType.INFORMATION);
            aler.setTitle("Ajout de SubCategorie"); 
            aler.setHeaderText(null);
            aler.setContentText(" SubCategorie ajoutée avec succés");
         
            aler.showAndWait();
       	getAll();
       	  
       }
	    }

	    private ObservableList<SUBCATEGORIE> getAll() {
		// TODO Auto-generated method stub
	    	Connection connection = classconnection.getDBConnection();
	        ObservableList<SUBCATEGORIE>list = FXCollections.observableArrayList();
	        try{
				Statement stmt = connection.createStatement();
			      ResultSet rs = stmt.executeQuery("SELECT * FROM sub_categorie");
	            while(rs.next()){
	                list.add(new SUBCATEGORIE(Integer.parseInt(rs.getString("id_sub_categ")),rs.getString("designation_sub_categ")));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
		
	}

		private void insert(SUBCATEGORIE S) {
		// TODO Auto-generated method stub
try {
				
				Connection connection =classconnection.getDBConnection();
				
				
				String sql="INSERT INTO sub_categorie (designation_sub_categ) VALUES (?)"; 
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, S.getNom());
				
				ps.execute();
				showListeCategorie();
				search_categ() ;
			} catch (Exception e) {
				e.printStackTrace();
			}
	   
		
	}

		private void search_categ() {
			// TODO Auto-generated method stub
			cid.setCellValueFactory(new PropertyValueFactory<SUBCATEGORIE,Integer>("id"));
			 cnom.setCellValueFactory(new PropertyValueFactory<SUBCATEGORIE,String>("nom"));
		list=getAll();
		table.setItems(list);
		FilteredList<SUBCATEGORIE> filteredData =new FilteredList<>(list,b->true);
		textfiieldrechercher.textProperty().addListener((observable,oldValue,newValue)->{
			filteredData.setPredicate(SUBCATEGORIE->{
			if(newValue==null|| newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter =newValue.toLowerCase();
			if(SUBCATEGORIE.getNom().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
				return true;
			}
			return false;
		});
		});
		SortedList<SUBCATEGORIE> sorteddata=new SortedList<>(filteredData);
		//SortedList.comparatorProperty().bind(table.comparatorProperty());
		sorteddata.comparatorProperty().bind(table.comparatorProperty());
			table.setItems(sorteddata);
			
		}

		private void showListeCategorie() {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
						ObservableList<SUBCATEGORIE>list = getAll();
						cid.setCellValueFactory(new PropertyValueFactory<SUBCATEGORIE,Integer>("id"));
						cnom.setCellValueFactory(new PropertyValueFactory<SUBCATEGORIE,String>("nom"));
						table.setItems(list);
				
		}

		@FXML
	    void DeletCateg(ActionEvent event) {
			 if(testOblig()!= null)
	         {
	             Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setTitle("Error");
	             alert.setHeaderText("Suppression");
	             alert.setContentText(testOblig());
	             alert.show();
	         }else {
	    		SUBCATEGORIE S=table.getSelectionModel().getSelectedItem();
	    		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	             alert.setTitle("Confirmer la suppression");
	             alert.setContentText("Cette categorie va etre supprimer , Voulez vous vraiment la supprimer?");
	             Optional<ButtonType> result = alert.showAndWait();
	             if(result.get() == ButtonType.OK) {
	    		delete(S.getId());
	    		 Alert ale = new Alert(AlertType.INFORMATION);
		            ale.setTitle("Suppression Categorie");
		            ale.setHeaderText(null);
		            ale.setContentText(" Categorie supprimée avec succés");
		            ale.showAndWait();
	    		txtnom.setText("");
	    		
	    	}
	             txtnom.setText("");
	             }
	    }

	    private void delete(int id) {
			// TODO Auto-generated method stub
	    	try {
				Connection connection =classconnection.getDBConnection();
				String sql="DELETE FROM `SUB_CATEGORIE` WHERE `SUB_CATEGORIE`.`id_sub_categ` = ?"; 
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setInt(1,id);

				ps.execute();
				showListeCategorie();
				search_categ() ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String testOblig() {
			// TODO Auto-generated method stub
			 if(txtnom.getText()=="")
	         {
	             return "Entrer Nom de la subcatégorie";
	         }
			
			return null;
		}

		@FXML
	    void Select(MouseEvent event) {
SUBCATEGORIE S=table.getSelectionModel().getSelectedItem();
	    	
	    	txtnom.setText(S.getNom());
	
	    }

	    @FXML
	    void UpdateCateg(ActionEvent event) {
	    	try {
	    		   if(testOblig()!= null)
			         {
			             Alert alert = new Alert(Alert.AlertType.ERROR);
			             alert.setTitle("Error");
			             alert.setHeaderText("Modification");
			             alert.setContentText(testOblig());
			             alert.show();
			         }else {
			
			Connection connection = classconnection.getDBConnection();
			
			String nom=txtnom.getText();
	    
	    	SUBCATEGORIE p=table.getSelectionModel().getSelectedItem();
			
			String sql="UPDATE `SUB_CATEGORIE` SET `designation_sub_categ` =? WHERE `SUB_CATEGORIE`.`id_sub_categ` = ?"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirmer la Modification");
      alert.setContentText("Cet categorie va etre modifier, Voulez vous vraiment la modifier?");
      Optional<ButtonType> result = alert.showAndWait();
      if(result.get() == ButtonType.OK) {
      	ps.setString(1, nom);
			ps.setInt(2, p.getId());
			ps.execute();
			
			txtnom.setText("");
	    	
			
	    	getAll();
	    	showListeCategorie()
	    	;Alert ale = new Alert(AlertType.INFORMATION);
      ale.setTitle("Modification Categorie");
      ale.setHeaderText(null);
      ale.setContentText(" Categorie modifiée avec succés");
      ale.showAndWait();
			
	    	search_categ() ;
			         }txtnom.setText("");	         
			         }} catch (Exception e) {
			e.printStackTrace();
		}
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			showListeCategorie();
			search_categ() ;	
			
		}

}
