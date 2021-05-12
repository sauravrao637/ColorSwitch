package ColorSwitch;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

	
	@FXML
	private AnchorPane mainRoot;
	
//	@FXML
//	private Button theme_btn;

	@FXML
	private Button reloadG_btn;

	@FXML
	private Button shop_btn;

	@FXML
	private Button newG_btn;
	
	@FXML
	private Label score_field;
	
	@FXML
	private Label total_field;
	
	@FXML
	private Button name_btn;

	@FXML
	private Button lb_btn;

	@FXML
	private ImageView exitG_btn;

	@FXML
	private Label name_field;
	@FXML
	private Button musicButton;
	Timeline timeline4;
	
	@Override
	public void initialize(URL url, ResourceBundle rb){
		if(Main.current == null)Main.current = new Player("ColorSwitch"); 
		if(Main.current==null)newG_btn.setDisable(true);
		//Main.current = new Player("");
		Main.setborder(mainRoot);
		try{
			name_field.textProperty().bind(Main.current.NAME);
			
		}
		catch(Exception e) {
			//e.printStackTrace();
			name_field.textProperty().setValue("ColorSwitch");
			
		}
		try{
			score_field.textProperty().bind(Main.current.HIGHEST);
		}
		catch(Exception e) {
			score_field.textProperty().setValue("0");
		}
		try {
			total_field.textProperty().bind(Main.TOTAL);
		}
		catch(Exception e) {
			total_field.textProperty().setValue("0");
		}
		reloadG_btn.getStyleClass().add("reload");
		RotateTransition rotatereload = new RotateTransition();
		{
			rotatereload.setAxis(Rotate.Z_AXIS);  
			rotatereload.setByAngle(360);  
			rotatereload.setCycleCount(500);  
			rotatereload.setDuration(Duration.millis(1000));  
			rotatereload.setAutoReverse(true);
		}
		rotatereload.setNode(reloadG_btn);
		rotatereload.play();
		Group c1 = Main.makeCircle(50);
		Group c2 = Main.makeCircle(65);
		c1.setLayoutX(200);
		c1.setLayoutY(150);
		
		c2.setLayoutX(200);
		c2.setLayoutY(150);
		
		Main.rotateNode(c1, 0, 0, 2);
		Main.rotateNode(c2, 0, 0, -1.5);
		mainRoot.getChildren().addAll(c1,c2);
		
		ObjectProperty<Color> baseColor = new SimpleObjectProperty<>();

		KeyValue keyValue1 = new KeyValue(baseColor, Color.RED);
		KeyValue keyValue2 = new KeyValue(baseColor, Color.YELLOW);
		KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
		KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1000), keyValue2);
		timeline4 = new Timeline(keyFrame1, keyFrame2);

		baseColor.addListener((obs, oldColor, newColor) -> {
			name_field.setStyle(String.format("-gradient-base: #%02x%02x%02x; ", 
					(int)(newColor.getRed()*255),
					(int)(newColor.getGreen()*255),
					(int)(newColor.getBlue()*255)));
		});

		timeline4.setAutoReverse(true);
		timeline4.setCycleCount(Animation.INDEFINITE);
		timeline4.play();


		if(Main.current!=null) {
			newG_btn.setDisable(false);
		}
		if(Main.sound) {
			musicButton.setStyle("-fx-background-image: url('Assets/sound.png')");
	    	Main.mediaPlayer.pause();
		}
		else {
			
			musicButton.setStyle("-fx-background-image: url('Assets/noSound.png')");
	    	Main.mediaPlayer.play();
		}
		Main.rotateNode(musicButton, 40, 40, -2);
	}
	@FXML
	void toggleSound(MouseEvent event) throws Exception{
		if(!Main.sound) {
			musicButton.setStyle("-fx-background-image: url('Assets/sound.png')");
	    	Main.sound = true;
	    	Main.mediaPlayer.pause();
		}
		else {
			Main.sound = false;
			musicButton.setStyle("-fx-background-image: url('Assets/noSound.png')");
	    	Main.mediaPlayer.play();
		}
	}
	
//	@FXML
//	void changeTheme(MouseEvent event) throws Exception{
//		if(!Main.darkT) {
//			Main.darkT = true;
//			theme_btn.setText("Light Theme");
//			Main.scene.getStylesheets().remove(MainPageController.class.getResource("Stylesheet/GameMenuL.css").toExternalForm());
//			Main.scene.getStylesheets().add(MainPageController.class.getResource("Stylesheet/GameMenuD.css").toExternalForm());
//			//mainRoot.getStylesheets().remove(MainPageController.class.getResource("Stylesheet/GameMenuL.css").toExternalForm());
//			//mainRoot.getStylesheets().add(MainPageController.class.getResource("Stylesheet/GameMenuD.css").toExternalForm());
//		}
//		else {
//			Main.darkT = false;
//			theme_btn.setText("Dark Theme");
//			Main.scene.getStylesheets().add(MainPageController.class.getResource("Stylesheet/GameMenuL.css").toExternalForm());
//			Main.scene.getStylesheets().remove(MainPageController.class.getResource("Stylesheet/GameMenuD.css").toExternalForm());
//			
//			//mainRoot.getStylesheets().remove(MainPageController.class.getResource("Stylesheet/GameMenuD.css").toExternalForm());
//			//mainRoot.getStylesheets().add(MainPageController.class.getResource("Stylesheet/GameMenuL.css").toExternalForm());
//
//		}
//	}


/**/
	
	@FXML
	void reload(MouseEvent event) throws Exception{
		ScrollPane pane= FXMLLoader.load(getClass().getClassLoader().getResource("Layout/saved.fxml"));
		Main.scene.setRoot(pane);
		//System.out.println("Reload clicked");
	}

	@FXML
	void shop(MouseEvent event) throws Exception{
		Main.current.selectBall();
	}

	@FXML
	void leaderboard(MouseEvent event) throws Exception{
		Pane pane= FXMLLoader.load(getClass().getClassLoader().getResource("Layout/lb.fxml"));
		Main.setborder(pane);
		Main.scene.setRoot(pane);
	}

	@FXML
	void newGame(MouseEvent event) throws Exception{
		Main.current.start();	
	}

	@FXML
	void login(MouseEvent event) throws Exception{
		if(Main.current == null) {
			Main.current = new Player("");
		}
		AnchorPane pane= FXMLLoader.load(getClass().getClassLoader().getResource("Layout/changeName.fxml"));
		Main.setborder(pane);
		Main.scene.setRoot(pane);
	}
	
	@FXML
	void exit(MouseEvent event) throws Exception{
		try{
			Main.serialize();
		}
		catch(IOException e){
			//System.out.println("Could not save the progress :(");
		}
		System.exit(0);
	}


}