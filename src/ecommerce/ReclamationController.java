package ecommerce;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class ReclamationController implements Initializable {
   

    @FXML
    private TableView<RECLAMATION> tablereclamation;

    @FXML
    private TableColumn<RECLAMATION, String> utilisateur;
    

    @FXML
    private TableColumn<RECLAMATION, String> msj;

    public static ObservableList<RECLAMATION> getTableCommande (){
		
        Connection connection = classconnection.getDBConnection();
        ObservableList<RECLAMATION>list = FXCollections.observableArrayList();
        try{
			Statement stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT email , message FROM contact ");
            while(rs.next()){
                list.add(new RECLAMATION(rs.getString("email"),rs.getString("message")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void showListeReclamation() {
		  ObservableList<RECLAMATION>list = getTableCommande ();
			 utilisateur.setCellValueFactory(new PropertyValueFactory<RECLAMATION,String>("email"));
			 msj.setCellValueFactory(new PropertyValueFactory<RECLAMATION,String>("message"));
		// viewmsg.setCellValueFactory(new PropertyValueFactory<>(""));
	 /* Callback<TableColumn<RECLAMATION, RECLAMATION>, TableCell<RECLAMATION, String>> cellFoctory = (TableColumn<RECLAMATION, RECLAMATION> param) -> {
		            // make cell containing buttons
		            final TableCell<RECLAMATION, RECLAMATION> cell = new TableCell<RECLAMATION, RECLAMATION>() {
		                @Override
		                public void updateItem(RECLAMATION item, boolean empty) {
		                    super.updateItem(item, empty);
		                    //that cell created only on non-empty rows
		                    if (empty) {
		                        setGraphic(null);
		                        setText(null);

		                    } else {
		                    	final Button viewBtn=new Button("View");
                          viewBtn.setOnMouseClicked((MouseEvent event) -> {
		                            
		                            try {
		                            	RECLAMATION R=tablereclamation.getSelectionModel().getSelectedItem();
		                            	 FXMLLoader loader = new FXMLLoader ();
		                            	 loader.setLocation(getClass().getResource("showmsg.fxml"));
				                            try {
				                                loader.load();
				                            } catch (IOException ex) {
				                            	 ex.printStackTrace();
				                            }

				                            showmsgController M = loader.getController();
				                            
		                                 String query = "Select message FROM `contact` WHERE id_contact  ="+RECLAMATION.getId()+"";
		                                Connection connection = classconnection.getDBConnection();
		                                PreparedStatement ps = connection.prepareStatement(query);
		                               ps.execute();
		                         Parent parent = loader.getRoot();
		                            Stage stage = new Stage();
		                            stage.setScene(new Scene(parent));
		                            stage.initStyle(StageStyle.UTILITY);
		                            stage.show();
		                            
                                  } catch (SQLException ex) {
		                                ex.printStackTrace();
		                            }
		                    });
		                        HBox managebtn = new HBox();
		                        managebtn.setStyle("-fx-alignment:center");
		                        HBox.setMargin(viewBtn, new Insets(2, 2, 0, 3));
		                        setGraphic(managebtn);
                               setText(null);

		                    }
		                }
 };
£return cell;
		        };*/
		       // viewmsg.setCellValueFactory(cellFoctory); 
		 tablereclamation.setItems(list);
	 }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showListeReclamation();
	}

}
