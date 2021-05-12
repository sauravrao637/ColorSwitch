package ColorSwitch;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class changeName{
	@FXML
	private AnchorPane changeNamePane;
	@FXML
	private TextField changeNameField;
	@FXML
	private Button submit_btn;

	@FXML
	void submit(MouseEvent event) throws IOException{
		String name  = changeNameField.getText();
		name.trim();
		if(name == "") name = "MrNoName";
		name = name.substring(0, Math.min(name.length(), 10));
		Main.current.changeNAME(name);
		AnchorPane pane= FXMLLoader.load(getClass().getClassLoader().getResource("Layout/MainPage.fxml"));
		Main.scene.setRoot(pane);
	}


}