package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SettingController implements Initializable{
	
	@FXML private Button fileChooser;
	@FXML private Button fileAccept;
	@FXML private Text directoryText;
	@FXML private RadioButton highQualityRadio;
	@FXML private RadioButton lowQualityRadio;
	
	private File selectedDirectory;
	private ToggleGroup group = new ToggleGroup();
	
	@FXML
	private void handleDirectory(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
        selectedDirectory = 
                directoryChooser.showDialog(null);
         
        if(selectedDirectory == null){
        	directoryText.setText("No Directory selected");
        }else{
        	directoryText.setText(selectedDirectory.getAbsolutePath());
        }
	}
	
	@FXML
	private void handleAccept(ActionEvent event) {
		if(selectedDirectory != null) 
			UserPreferences.setDirectory(selectedDirectory.getAbsolutePath());
		Stage stage = (Stage) fileChooser.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void handleClose(ActionEvent event) {
		Stage stage = (Stage) fileChooser.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void onToggleHandle(ActionEvent event) {
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
				
				
				if(group.selectedToggleProperty().toString().contains("High")) {
					UserPreferences.setQuality("high");
				} else {
					UserPreferences.setQuality("low");
				}
				
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		directoryText.setText(FXController.folderDirectory);
		
		highQualityRadio.setToggleGroup(group);
		lowQualityRadio.setToggleGroup(group);
		
		if(FXController.qualityLevel.equals("high"))
			highQualityRadio.fire();
		else
			lowQualityRadio.fire();
	}
}
