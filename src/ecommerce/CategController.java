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

public class CategController implements Initializable  {
	  @FXML
	    private TableColumn<CATEGORIE, Integer> cid;

	    @FXML
	    private TableColumn<CATEGORIE, String> cnom;

	    @FXML
	    private TableView<CATEGORIE> table;

	    @FXML
	    private TextField textfiieldrechercher;

	    @FXML
	    private TextField txtnom;
	    ObservableList<CATEGORIE> data;
	    ObservableList<CATEGORIE> list;
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

	       	CATEGORIE C=new CATEGORIE(nom);
	       	txtnom.setText("");
	    	insert(C);
	       	 Alert aler = new Alert(AlertType.INFORMATION);
	            aler.setTitle("Ajout Categorie"); 
	            aler.setHeaderText(null);
	            aler.setContentText(" Categorie ajoutée avec succés");
	         
	            aler.showAndWait();
	       	getAll();
	       	  
	       }
	    }

	    private ObservableList<CATEGORIE> getAll() {
			// TODO Auto-generated method stub
	    	Connection connection = classconnection.getDBConnection();
	        ObservableList<CATEGORIE>list = FXCollections.observableArrayList();
	        try{
				Statement stmt = connection.createStatement();
			      ResultSet rs = stmt.executeQuery("SELECT * FROM categorie");
	            while(rs.next()){
	                list.add(new CATEGORIE(Integer.parseInt(rs.getString("id_categ")),rs.getString("name_categ")));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
		}

		private void insert(CATEGORIE C) {
			// TODO Auto-generated method stub
			try {
				
				Connection connection =classconnection.getDBConnection();
				
				
				String sql="INSERT INTO categorie (name_categ) VALUES (?)"; 
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, C.getNom());
				
				ps.execute();
				showListeCategorie();
				search_categ() ;
			} catch (Exception e) {
				e.printStackTrace();
			}
	   
		}

		private void search_categ() {
			// TODO Auto-generated method stub
			cid.setCellValueFactory(new PropertyValueFactory<CATEGORIE,Integer>("id"));
			 cnom.setCellValueFactory(new PropertyValueFactory<CATEGORIE,String>("nom"));
		list=getAll();
		table.setItems(list);
		FilteredList<CATEGORIE> filteredData =new FilteredList<>(list,b->true);
		textfiieldrechercher.textProperty().addListener((observable,oldValue,newValue)->{
			filteredData.setPredicate(CATEGORIE->{
			if(newValue==null|| newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter =newValue.toLowerCase();
			if(CATEGORIE.getNom().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
				return true;
			}
			return false;
		});
		});
		SortedList<CATEGORIE> sorteddata=new SortedList<>(filteredData);
		//SortedList.comparatorProperty().bind(table.comparatorProperty());
		sorteddata.comparatorProperty().bind(table.comparatorProperty());
			table.setItems(sorteddata);
		}

		private void showListeCategorie() {
			// TODO Auto-generated method stub
			ObservableList<CATEGORIE>list = getAll();
			cid.setCellValueFactory(new PropertyValueFactory<CATEGORIE,Integer>("id"));
			cnom.setCellValueFactory(new PropertyValueFactory<CATEGORIE,String>("nom"));
			table.setItems(list);
	}

		@FXML
	    void DeleteCateg(ActionEvent event) {

			 if(testOblig()!= null)
	         {
	             Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setTitle("Error");
	             alert.setHeaderText("Suppression");
	             alert.setContentText(testOblig());
	             alert.show();
	         }else {
	    		CATEGORIE C=table.getSelectionModel().getSelectedItem();
	    		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	             alert.setTitle("Confirmer la suppression");
	             alert.setContentText("Cette categorie va etre supprimer , Voulez vous vraiment la supprimer?");
	             Optional<ButtonType> result = alert.showAndWait();
	             if(result.get() == ButtonType.OK) {
	    		delete(C.getId());
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
				String sql="DELETE FROM `CATEGORIE` WHERE `CATEGORIE`.`id_categ` = ?"; 
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
	             return "Entrer Nom de Client";
	         }
			
			return null;
		}

		@FXML
	    void ModifierCateg(ActionEvent event) {
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
	    
	    	CATEGORIE p=table.getSelectionModel().getSelectedItem();
			
			String sql="UPDATE `CATEGORIE` SET `name_categ` =? WHERE `CATEGORIE`.`id_categ` = ?"; 
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

	    @FXML
	    void mouseSateg(MouseEvent event) {
	    	CATEGORIE C=table.getSelectionModel().getSelectedItem();
	    	
	    	txtnom.setText(C.getNom());
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			showListeCategorie();search_categ() ;	
		}


}
