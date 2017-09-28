package Presentation.OrderUI;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CommentController implements Initializable{
    @FXML ComboBox<String> degreeComboBox;
    @FXML TextField commentTextField;
    @FXML CheckBox annoyCheckBox;
    ObservableList<String> degreeData=FXCollections.observableArrayList("1","2","3","4","5");
    @FXML
    protected void save(ActionEvent e){
    	
    }
    private void initialComboBox(){
    	degreeComboBox.setItems(degreeData);
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initialComboBox();
	}

}