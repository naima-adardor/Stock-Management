package ecommerce;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;


public class showmsgController implements Initializable {
	  @FXML
	    private TextArea txtarea;
	private ObservableList<MESSAGE> getMessage() {
		// TODO Auto-generated method stub

		String message= txtarea.getText();
   
    	Connection connection = classconnection.getDBConnection();
        ObservableList<MESSAGE>list = FXCollections.observableArrayList();
        try{
			Statement stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT message FROM contact c , users u where c.id_contact=u.id_contact ");
            while(rs.next()){
                list.add(new MESSAGE(rs.getString("message")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
