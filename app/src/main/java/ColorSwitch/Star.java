package ColorSwitch;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Star extends GameElement{
	public Group view;
	String path = "Assets/star.png";
	public Star(double x, double y) {
		super(x, y);
		view = new Group();
		ImageView s = new ImageView(new Image(this.path,30,30,false,false));
		view.getChildren().clear();
		view.getChildren().addAll(s);
		view.setLayoutX(x);
		view.setLayoutY(y);
		Main.rotateNode(view, 15, 15,3);
	}
	private  Media hs = new Media(getClass().getClassLoader().getResource("Assets/highScore.mpeg").toString());
	private  MediaPlayer mediaPlayerHS = new MediaPlayer(hs);
	{
		mediaPlayerHS.setAutoPlay(false);
		mediaPlayerHS.setCycleCount(1);
		mediaPlayerHS.setStartTime(Duration.seconds(2));
		mediaPlayerHS.setStopTime(Duration.seconds(8));
		
	}
	
	@Override
	public void action(Pane canvas) {
		Main.current.setScore(1*Main.current.currBall.getRank());
		if(Main.current.getScore()>=Main.current.getHighest()) {
			if(!Main.sound){
				mediaPlayerHS.play();
			}
			else {
				mediaPlayerHS.pause();
			}
			
			
		}
		//System.out.println(Main.current.getScore());
		//System.out.println(Main.current.getHighest());
		this.isActive = false;
		canvas.getChildren().get(id).setVisible(false);
		//canvas.getChildren().remove(this.id);
		//canvas.getChildren().add(this.getView());
		
		
	}
	@Override
	public void reset(Pane canvas) {
		this.isActive = true;
		this.path = "Assets/star.png";
		canvas.getChildren().get(id).setVisible(true);
		mediaPlayerHS.seek(mediaPlayerHS.getStartTime());
	}
	
	@Override
	public Group getView() {
		return this.view;
	}
	
	@Override
	public void update() {
		ImageView csI = new ImageView(new Image(this.path,30,30,false,false));
		view.getChildren().clear();
		view.getChildren().addAll(csI);
		view.setLayoutX(x);
		view.setLayoutY(y);
	}


	@Override
	public String toString() {
		String form = "Star ";
		form+=Double.toString(x);
		form+=" ";
		form+=Double.toString(y);
		form+=" ";
		form+=this.isActive;
		return form;
	}


	public static Star fromString(String s) {
		String details[] = s.split(" ");
		Star ss = new Star(Double.valueOf(details[1]),Double.valueOf(details[2]));
		ss.isActive = Boolean.valueOf(details[3]);
		return ss;
	}

}
