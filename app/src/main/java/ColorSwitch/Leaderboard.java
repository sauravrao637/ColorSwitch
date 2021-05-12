package ColorSwitch;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Leaderboard implements Initializable {
	@FXML
	private Label name;
	@FXML
	private Label score;

	@FXML
	void home() {
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		name.setText(Main.currentd.getLeaderBaord().split(" ")[0]);
		score.setText(Main.currentd.getLeaderBaord().split(" ")[1]);
	}
}
