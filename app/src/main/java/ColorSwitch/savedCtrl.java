package ColorSwitch;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class savedCtrl implements Initializable{
	@FXML
	public AnchorPane savedRoot;
	int i = 0;
	
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
		Main.setborder(savedRoot);
		savedRoot.setStyle("-fx-background-color: black");
		i = 0;

		double y = 150;
		for(i = 1;i<Main.currentd.saved.size();i++) {
			Button b = new Button("LoadGame "+(i));
			EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
				@Override 
				public void handle(MouseEvent e) { 
					submit(Main.currentd.saved.get(i-1));
				}

				private void submit(savedStates s) {
					try{
						Main.current.load(s);
					}catch(Exception e) {
						//e.printStackTrace();
					}

				}
			};
			b.setLayoutX(140-b.getWidth()/2);
			b.setOnMouseClicked(eventHandler);
			b.setLayoutY(y);
			y+=50;
			savedRoot.getChildren().add(b);
		}
	}



}
