package ColorSwitch;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.ResourceBundle;

public class GamePlay implements Initializable{
	@FXML
	public AnchorPane playRoot;

	@FXML
	private Pane playCanvas;

	@FXML
	private Button home_btn;

	@FXML
	private Label scoreLabel;

	public  boolean paused = false;

	Timeline timeline4,t;
	public  void pause() {
		for(int i=0;i<Main.allTimelines.size();i++) {
			Main.allTimelines.get(i).pause();
		}
		paused = true;
		Main.current.physics.stopped = true;
	}

	public  void unpause() {
		for(int i=0;i<Main.allTimelines.size();i++) {
			Main.allTimelines.get(i).play();
		}
		paused = false;
		try{
			Main.current.physics.stopped = false;
		}catch(Exception e) {
			
		}
	}
	@FXML
	void home() {
		if(!paused) {
			pause();
		}
		else {
			unpause();
		}
	}

	@FXML
	void jump() {

		if(!paused&&Main.current.currBall.y>50 )	Main.current.physics.jump();
	}

	public GameElement latest;
	ArrayList<GameElement> queue = new ArrayList<GameElement>();
	public Map<Group,GameElement> all = new HashMap<Group,GameElement>();

	public static ImageView ballView ;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		//System.out.println("here");
		unpause();
		playRoot.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
		() {
			@Override
			public void handle(KeyEvent key) {
				if(key.getCode()==KeyCode.ESCAPE)
				{
					//initialize(null,null);
					Exit();
					Main.current.exitGame();
					//Main.current.saveGame();
				}

				else if(key.getCode()==KeyCode.ALT) {
					Exit();
					//System.out.println("saved");
					Main.current.saveGame();
				}
				else {
					home();
					//Main.current.pauseGame(playRoot);
				}
			}


		});

		scoreLabel.setText(Integer.toString(Main.current.getScore()));
		Ball b = Main.current.currBall;
		ballView = b.getView();

		playCanvas.getChildren().add(0, ballView);
		playCanvas.getChildren().get(0).setLayoutY(b.y);
		all.clear();

		int j = 0;
		//System.out.println(queue.size());
		for(j=0;j<queue.size();j++){
			try{
				Group temp = queue.get(j).getView();
				queue.get(j).id = j+1;
				all.put(temp,queue.get(j));
				playCanvas.getChildren().add(j+1, temp);
				if(!queue.get(j).isActive)playCanvas.getChildren().get(j+1).setVisible(false);
			}
			catch(Exception e) {
				//System.out.println(j);
			}
			
			//playCanvas.getChildren().get(j+1).setLayoutX(queue.get(j).x);
			//playCanvas.getChildren().get(j+1).setLayoutY(queue.get(j).y);
		}
		latest = all.get(playCanvas.getChildren().get(8));


	}
	public void Exit() {
		t.stop();
		timeline4.stop();
		queue.clear();
		all.clear();
		pause();
		Main.allTimelines.remove(t);
		Main.allTimelines.remove(timeline4);
	}
	public GamePlay() {

		queue = new ArrayList<GameElement>();
		all = new ConcurrentHashMap<Group,GameElement>();
		queue.clear();
		//if(queue.isEmpty()) {
		//System.out.println(((GameElement)Main.Allelements[0]).x);
		queue.add(Main.current.Allelements[0]);
		queue.add(Main.current.Allelements[1]);
		queue.add(Main.current.Allelements[2]);
		queue.add(Main.current.Allelements[3]);
		queue.add(Main.current.Allelements[4]);
		queue.add(Main.current.Allelements[5]);
		queue.add(Main.current.Allelements[6]);
		queue.add(Main.current.Allelements[7]);
		
		//}



		timeline4 = new Timeline(

				new KeyFrame(Duration.millis(15), 
						new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						//physics.fall();
						try{
							//System.out.println(playCanvas.getChildren().size());
							try{
								refresh();
							}
							catch(Exception e) {
								//e.printStackTrace();
							}
						}
						catch(Exception e) {
							//e.printStackTrace();
							System.exit(0);
						}
					}
				}));


		timeline4.setCycleCount(Timeline.INDEFINITE);
		//							timeline4.setCycleCount(10);
		timeline4.play();

		t = new Timeline(

				new KeyFrame(Duration.millis(20), 
						new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						DetectCollsion();
					}
				}));
		t.setCycleCount(Timeline.INDEFINITE);
		t.play();
		Main.allTimelines.add(timeline4);
		Main.allTimelines.add(t);
	}

	protected void DetectCollsion() {
		if(Main.current.physics==null)return;
		try{
			if(Main.current.physics.stopped)return;
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		try{
			Iterator<Entry<Group, GameElement>>  it;

			it = all.entrySet().iterator();
			Entry<Group, GameElement> group;
			while (it.hasNext())
			{
				group = it.next();
				if (group.getKey().getBoundsInParent().intersects(ballView.getBoundsInParent()) && group.getValue().isActive) {
					group.getValue().action(playCanvas);
				}
			}
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
		//		for (Entry<Group, GameElement> group : all.entrySet()) { 
		//			if (group.getKey().getBoundsInParent().intersects(ballView.getBoundsInParent()) && group.getValue().isActive) {
		//				group.getValue().action(playCanvas);
		//			}
		//		}

	}
	void refresh() {
		if(Main.current.physics==null)return;
		if(!paused) {
			update();
			try{
				Main.current.physics.simulate(playCanvas);
				Main.current.physics.draw(playCanvas);
			}

			catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

	void update() {
		scoreLabel.setText(Integer.toString(Main.current.getScore()));
	}
}
