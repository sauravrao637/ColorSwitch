package ColorSwitch;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
//ILLUSION
public class Physics {
	private static final double GRAVITY = 0.01;
	private static double v= 0;

	public boolean stopped = false;
	Timeline timeline5;
	Timeline timeline6;
	Ball b ;

	public void start() {
			timeline5.play();
	}
	public void stop() {
		stopped = true;
		timeline5.stop();
	}
	public Physics() {
		v=0;
		stopped = false;
		try{
			b = Main.current.currBall;
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		if(timeline5==null) {
			timeline5 = new Timeline(
					new KeyFrame(Duration.millis(1), 
							new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							fall();
						}
					}));
			timeline5.setCycleCount(Timeline.INDEFINITE);
			timeline5.stop();
		}

	}

	public void fall() {
		if(!stopped) {
			if(b.y<650) {
				b.y+=v/25;
				v+=GRAVITY;
			}
		}
	}

	public void jump() {

		if(!stopped) {
			//System.out.println(b.getRank());
			start();
			v= 0;
			timeline6 = new Timeline(

					new KeyFrame(Duration.millis(1), 
							new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							up();

						}
					}));
			timeline6.setCycleCount(30);
			timeline6.play();
		}
	}

	public void up() {
		if(!stopped)
		{
			if(b.y<650) {
				v-=20*GRAVITY;
				b.y+=v/50;
			}

		}
	}


	public void draw(Pane canvas) {
		b.updateView();
		try{
			canvas.getChildren().remove(0);
		}
		catch(Exception e) {

		}
		canvas.getChildren().add(0,b.getView());

	}
	public void disapper(Pane canvas,int i) {
		try{
			Main.current.controller.all.get(canvas.getChildren().get(i)).y = Math.min((-2*((Obstacle)Main.current.controller.latest).getRadius())-100,Main.current.controller.latest.y-(2*((Obstacle)Main.current.controller.latest).getRadius()+100));
			Main.current.controller.all.get(canvas.getChildren().get(i)).reset(canvas);
			if(Main.current.controller.all.get(canvas.getChildren().get(i)).getClass().getName().equals("ColoSwitch.Obstacle"))Main.current.controller.latest = Main.current.controller.all.get(canvas.getChildren().get(i));

		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		return;
	}

	public void simulate(Pane playCanvas) {
		Ball balY = Main.current.currBall;
		if((balY.y)<300) {
			balY.y+=1;
			for(int i = 1;i<playCanvas.getChildren().size();i++) {
				playCanvas.getChildren().get(i).setLayoutY(Main.current.controller.all.get(playCanvas.getChildren().get(i)).y+(300-balY.y)/100);
				Main.current.controller.all.get(playCanvas.getChildren().get(i)).y+=(500-balY.y)/200;
			}

		}
		else if(balY.y>=650) {
			balY.burst();
			stopped = true;
		}

		for(int i = 1;i<playCanvas.getChildren().size();i++) {
			if(playCanvas.getChildren().get(i).getLayoutY()>800)
			{
				disapper(playCanvas, i);
			}
		}
	}

}