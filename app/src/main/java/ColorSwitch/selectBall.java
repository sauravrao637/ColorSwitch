package ColorSwitch;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class selectBall implements Initializable{
	@FXML
	public AnchorPane BallSelection;
	
	@FXML
	private Button home_btn;
	@FXML
	void home() {
		Main.current.selectBall(ball);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Layout/MainPage.fxml"));
		AnchorPane pane;
		try {
			pane = fxmlLoader.load();
			Main.setborder(pane);
			Main.scene.setRoot(pane);
		} catch (IOException e) {
			//System.out.println("Could not load MainPage.fxml");
		}
	}
	@FXML ImageView ball1;
	@FXML ImageView ball2;
	@FXML ImageView ball3;
	@FXML private Label available;

	int ball = 1;
	
	@FXML void selectBall1() {
		ball =1;
	}
	
	@FXML void selectBall2(){
		ball =2;

	}
	@FXML void selectBall3() {
		ball = 3;
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		available.setText(Integer.toString(Main.currentd.getTotal()));
	}
	
	public static void intializePane(AnchorPane m) {
		
	}
	

}
