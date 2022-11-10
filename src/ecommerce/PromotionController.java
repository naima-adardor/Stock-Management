package ecommerce;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PromotionController implements Initializable {

	 @FXML
	    private TableColumn<PROMOTION, String> colDesc;

	    @FXML
	    private TableColumn<PROMOTION, Date> coldated;

	    @FXML
	    private TableColumn<PROMOTION, Date> coldatef;

	    @FXML
	    private TableColumn<PROMOTION, Float> colremise;

	    @FXML
	    private DatePicker dated;

	    @FXML
	    private DatePicker datef;

	    @FXML
	    private TableView<PROMOTION> table;

	    @FXML
	    private TextField textfileldrecherche;

	    @FXML
	    private TextField txtnom;

	    @FXML
	    private TextField txtremise;
	    ObservableList<PROMOTION> data;
	    ObservableList<PROMOTION> list;
	    @FXML
	    void Ajoutpromo(ActionEvent event) throws SQLException {
	    	 String nom=txtnom.getText();

	    	 float remise=Float.parseFloat(txtremise.getText());
	    	 // LocalDate d= dated.getValue();
	    	  //LocalDate f= datef.getValue();
	    	 LocalDate d= dated.getValue(); 
	    	 LocalDate f= datef.getValue(); 
	          if(nom.isEmpty()) {
	   	       Alert alert = new Alert(Alert.AlertType.ERROR);
	   	       alert.setTitle("Error");
	   	     	  alert.setHeaderText("Attention !!");
	   	         alert.setContentText(" Remplir tous les champs");
	   	         alert.showAndWait();
	   	         }else {
	   	        	 
	       	PROMOTION P=new PROMOTION(nom,remise);
	   	        	 String sql = null;
	       	Connection connection =classconnection.getDBConnection();
	    	//insert(P);
	        sql="INSERT INTO promotion (description,dateD,dateF,discount) VALUES (?,?,?,?)"; 
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, P.getNom());
			//ps.setDate(2, p.getDateD());
			//ps.setDate(3, p.getDateF());
		
			ps.setDate(2,Date.valueOf(d));		
			ps.setDate(3,Date.valueOf(f));
			ps.setFloat(4, P.getDiscount());
			ps.execute();
			txtnom.setText("");
			txtremise.setText("");
			dated.setValue(null);
			datef.setValue(null);
	       	Alert aler = new Alert(AlertType.INFORMATION);
	        aler.setTitle("Ajout Promotion"); 
	        aler.setHeaderText(null);
	        aler.setContentText(" Promotion ajoutée avec succés");
	        aler.showAndWait();
	        showListePromotion();
			search_promo() ;
	       	  
	       }

	    }

	    private ObservableList<PROMOTION> getAll() {
			// TODO Auto-generated method stub
	    	Connection connection = classconnection.getDBConnection();
	        ObservableList<PROMOTION>list = FXCollections.observableArrayList();
	        try{
				Statement stmt = connection.createStatement();
			      ResultSet rs = stmt.executeQuery("SELECT description , discount ,dateD ,dateF FROM promotion");
	            while(rs.next()){
	                list.add(new PROMOTION(rs.getString("description"),Float.parseFloat(rs.getString("discount")),rs.getDate("dateD"),rs.getDate("dateF")));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
		}

		private void insert(PROMOTION p) {
			// TODO Auto-generated method stub
try {
				
				Connection connection =classconnection.getDBConnection();
				   LocalDate d= dated.getValue();
				   LocalDate f= datef.getValue();
				
				String sql="INSERT INTO promotion (description,dateD,dateF,discount) VALUES (?,?,?,?)"; 
				PreparedStatement ps=connection.prepareStatement(sql);
				//ps.setString(1, p.getNom());
				//ps.setDate(2, p.getDateD());
				//ps.setDate(3, p.getDateF());
				//ps.setFloat(4, p.getDiscount());
				ps.setDate(1,Date.valueOf(d));		
				ps.setDate(2,Date.valueOf(f));
				ps.execute();
				showListePromotion();
				search_promo() ;
			} catch (Exception e) {
				e.printStackTrace();
			}
	   
		}
		  @FXML
		    void search_promo() {
			  String sql="Select description,dateD,dateF,discount from promotion where description like '"+textfileldrecherche.getText()+"'";
				int m=0;
				try {
					Connection connection =classconnection.getDBConnection();
					PreparedStatement ps=connection.prepareStatement(sql);
					 ResultSet result=ps.executeQuery();
					 if(result.next()) {

					    	txtnom.setText(result.getString("description"));
					   
					    	txtremise.setText(result.getString("discount"));
					    	Date date=result.getDate("dateD");
					    	dated.setValue(date.toLocalDate());
					      	Date dateE=result.getDate("dateF");
					    	datef.setValue(dateE.toLocalDate());
				    	m=1;
			     }
					
				}catch(SQLException e) {
					e.printStackTrace();
				
				}
				if(m==0) {
					Alert alert=new Alert(AlertType.ERROR,"Aucune promotion",javafx.scene.control.ButtonType.OK);
				}
		    }
		private void search_promotion() {
			// TODO Auto-generated method stub
			/*
			 * colDesc.setCellValueFactory(new
			 * PropertyValueFactory<PROMOTION,String>("nom"));
			 * coldated.setCellValueFactory(new
			 * PropertyValueFactory<PROMOTION,Date>("dateD"));
			 * coldatef.setCellValueFactory(new
			 * PropertyValueFactory<PROMOTION,Date>("dateF"));
			 * colremise.setCellValueFactory(new
			 * PropertyValueFactory<PROMOTION,Float>("discount")); list=getAll();
			 * table.setItems(list); FilteredList<PROMOTION> filteredData =new
			 * FilteredList<>(list,b->true);
			 * textfileldrecherche.textProperty().addListener((observable,oldValue,newValue)
			 * ->{ filteredData.setPredicate(PROMOTION->{ if(newValue==null||
			 * newValue.isEmpty()) { return true; } String lowerCaseFilter
			 * =newValue.toLowerCase();
			 * if(PROMOTION.getNom().toLowerCase().indexOf(lowerCaseFilter) !=-1) { return
			 * true; } return false; }); }); SortedList<PROMOTION> sorteddata=new
			 * SortedList<>(filteredData);
			 * //SortedList.comparatorProperty().bind(table.comparatorProperty());
			 * sorteddata.comparatorProperty().bind(table.comparatorProperty());
			 * table.setItems(sorteddata);
			 */
			
		}
		private void showListePromotion() {
			// TODO Auto-generated method stub
			ObservableList<PROMOTION>list = getAll();
			colDesc.setCellValueFactory(new PropertyValueFactory<PROMOTION,String>("nom"));
			 coldated.setCellValueFactory(new PropertyValueFactory<PROMOTION,Date>("dateD"));
			 coldatef.setCellValueFactory(new PropertyValueFactory<PROMOTION,Date>("dateF"));
			 colremise.setCellValueFactory(new PropertyValueFactory<PROMOTION,Float>("discount"));
			table.setItems(list);
	
			
		}

		@FXML
	    void Deletepromo(ActionEvent event) throws SQLException {
			 if(testOblig()!= null)
	         {
	             Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setTitle("Error");
	             alert.setHeaderText("Suppression");
	             alert.setContentText( testOblig());
	             alert.show();
         }else {
	    		//PROMOTION P=table.getSelectionModel().getSelectedItem();
    		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	             alert.setTitle("Confirmer la suppression");
	             alert.setContentText("Cette promotion va etre supprimer , Voulez vous vraiment la supprimer?");
             Optional<ButtonType> result = alert.showAndWait();
	             if(result.get() == ButtonType.OK) {
	    		//delete(P.getId());
	            		Connection connection =classconnection.getDBConnection();
	    				String sql="DELETE FROM `promotion` WHERE description = '"+textfileldrecherche.getText()+"'";
    				PreparedStatement ps=connection.prepareStatement(sql);
    				ps.executeUpdate();
    				
    				txtnom.setText("");
    				txtremise.setText("");
    				dated.setValue(null);
    				datef.setValue(null);
	    				
    				search_promo() ;
	    		 Alert ale = new Alert(AlertType.INFORMATION);
		            ale.setTitle("Suppression Promotion");
		            ale.setHeaderText(null);
		            ale.setContentText(" Promotion supprimée avec succés");
		            ale.showAndWait();
	    		  //txtnom.setText("");
		           showListePromotion();
	    		
	    	}
	           //  txtnom.setText("");
	             }
	    }

	    private void delete(int id) {
			// TODO Auto-generated method stub
	    	try {
				Connection connection =classconnection.getDBConnection();
				String sql="DELETE FROM `promotion` WHERE `promotion`.`id_promotion` = ?"; 
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setInt(1,id);

				ps.execute();
				showListePromotion();
				search_promo() ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String testOblig() {
			// TODO Auto-generated method stub
			 if(txtnom.getText()=="")
	         {
	             return "Entrer Nom de la promo";
	         }
			return null;
		}

		@FXML
	    void Modifierpromo() throws SQLException {
			
			  if(testOblig()!= null) { 
				  Alert alert = new Alert(Alert.AlertType.ERROR);
			  alert.setTitle("Error");
			  alert.setHeaderText("Modification");
			  alert.setContentText(testOblig()); 
			  alert.show();
			  }else {
			  
			  Connection connection = classconnection.getDBConnection();
			  
			  String nom=txtnom.getText();
			  float remise=Float.parseFloat(txtremise.getText());
			 
			  
			//PROMOTION p=table.getSelectionModel().getSelectedItem();
			  
			  String sql="UPDATE promotion SET description =? ,  dateD=? , dateF=? , discount=? where description='"+textfileldrecherche.getText()+"'";
			  PreparedStatement ps=connection.prepareStatement(sql);
			//  ps=connection.prepareStatement(sql);
			  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			  alert.setTitle("Confirmer la Modification");
			  alert. setContentText("Cet promotion va etre modifier, Voulez vous vraiment la modifier?" );
			  Optional<ButtonType> result = alert.showAndWait();
			  if(result.get() == ButtonType.OK) {
				  ps.setString(1,nom);//(ZoneId,systemDefault()).toInstant());
			  java.util.Date date=java.util.Date.from(dated.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			  Date sqlDate1=new Date(date.getTime());
			  ps.setDate(2,sqlDate1);
			  java.util.Date daAte=java.util.Date.from(datef.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			  Date sqlDate2=new Date(daAte.getTime());
			  ps.setDate(3, sqlDate2);
			  
			  ps.setFloat(4, remise);
			 //s.setInt(5, p.getId());
			  ps.execute();
			  txtnom.setText("");
			  txtremise.setText("");
			  dated.setValue(null);
			  datef.setValue(null);
			  
			  
			  getAll();
			  showListePromotion(); 
			  Alert ale = new Alert(AlertType.INFORMATION);
			  ale.setTitle("Modification Categorie");
			  ale.setHeaderText(null);
			  ale.setContentText(" Promotion modifiée avec succés");
			  ale.showAndWait();
			  
			  search_promo() ;
 }  }
			 
		
	   }
	   // int index =-1;
	    @FXML
	    void Selected(MouseEvent event) {
			/*
			 * PROMOTION P=table.getSelectionModel().getSelectedItem(); String
			 * sql="select * from promotion where id_promotion=?";
			 * 
			 * Connection connection = classconnection.getDBConnection(); try {
			 * PreparedStatement ps=connection.prepareStatement(sql);
			 * ps.setInt(1,P.getId()); ResultSet result=ps.executeQuery(); if(result.next())
			 * {
			 * 
			 * txtnom.setText(result.getString("description"));
			 * 
			 * txtremise.setText(result.getString("discount")); Date
			 * date=result.getDate("dateD"); dated.setValue(date.toLocalDate()); Date
			 * dateE=result.getDate("dateF"); datef.setValue(dateE.toLocalDate()); }
			 * }catch(SQLException e) { e.printStackTrace(); }
			 */
	    	//txtnom.setText(P.getNom());
	    	//dated.setText((P.getDateD());
	    	//datef.setValue(P.getDateF());
	    	//txtremise.Float.parseFloat(setText(P.getDiscount()));
	    	//=table.getSelectionModel().getFocusedIndex();
	    	//if(index<=-1) {
	    	//	return;
	    	//}
	    	//PRODUIT C=tableproduit.getSelectionModel().getSelectedItem();
	    		    	
	    	//txtnom.setText(colDesc.getCellData(index).toString());
	   //Date date=coldatef.getDate();
	    	//dated.setValue(coldatef.getCellData(index).toString());
	    	//txtremise.setText(colremise.getCellData(index).toString());
	    	
	
  }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//loaddate();
		showListePromotion();
		search_promo();	}

}
