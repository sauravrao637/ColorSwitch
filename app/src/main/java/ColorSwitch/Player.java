package ColorSwitch;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player {


	public final SimpleStringProperty NAME;
	public final SimpleStringProperty HIGHEST;
	public GameElement Allelements[] = {null,null,null,null,null,null,null,null};

	private int score=0 ;
	private int highest=0 ;
	private Ball bestBall;
	public Ball currBall;
	Random rd = new Random();
	public int n = rd.nextInt(4);
	Color[] colors = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};
	FXMLLoader fxmlLoader;
	AnchorPane pane = null;
	Physics physics ;
	GamePlay controller;
	public Player(String name) {
		//Main.currentd.Players.add(name);
		this.NAME = new SimpleStringProperty(name);
		this.HIGHEST = new SimpleStringProperty(Integer.toString(highest));
		selectBall(1);
	}
	private  Media sound = new Media(getClass().getClassLoader().getResource("Assets/ress.mpeg").toString());
	private  MediaPlayer mediaPlayer = new MediaPlayer(sound);
	{
		//mediaPlayer.setAutoPlay(false);
		mediaPlayer.setCycleCount(1);
		mediaPlayer.setStartTime(Duration.seconds(2));
		mediaPlayer.setStopTime(Duration.seconds(10));
	}
	
	private void resetElem() {
		this.score = 0;
		currBall.x = 185;
		currBall.y = 500;
		GameElement cs1  = new CS(185,300);
		GameElement star = new Star(185,120);
		GameElement circle = new Circular(200,-570,100,-1);
		GameElement star2 = new Star(185,-280);
		GameElement sqr = new SquareO(200,-220,90,2);
		GameElement cs2  = new CS(185,-400);
		//GameElement circle2 = new Circular(200,-580,100,3);
		GameElement circle2 = new Circular(200,-570,90,3);
		GameElement circle3 = new Circular(200,120,100,3);

		Allelements[0] = cs1;
		Allelements[1] = circle;
		Allelements[2] = star;
		Allelements[3] = sqr;
		Allelements[4] = star2;
		Allelements[5] = circle2;
		Allelements[6] = cs2;
		Allelements[7] = circle3;
	}
	public void pauseGame() {

		this.physics.stop();
		//		fxmlLoader = new FXMLLoader(getClass().getResource("Layout/gameMenu.fxml"));
		//		try {
		//			pane = fxmlLoader.load();
		//			Main.scene.setRoot(pane);
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//			System.out.println("Could not load gameMenu.fxml");
		//		}

	}

	public void saveGame() {
		String[] details = new String[Allelements.length+3];
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		details[0] = this.getNAME()+" "+timeStamp;
		details[1]= currBall.toString();
		details[2] = this.getNAME()+" "+Integer.toString(this.score)+" ";
		for(int i = 3;i<details.length;i++) {
			details[i]= Allelements[i-3].toString();
		}
		savedStates s = new savedStates(details);
		Main.currentd.saved.add(s);
		this.exitGame();
	}

	private void restartGame() {

	}

	public void exitGame() {
		this.physics.stop();
		if(Main.currentd.Players.containsKey(this.getNAME())){
			Main.currentd.Players.remove(this.getNAME());
			Main.currentd.Players.put(getNAME(), toString());
		}
		fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Layout/MainPage.fxml"));
		try {
			pane = fxmlLoader.load();
			Main.scene.setRoot(pane);
		} catch (IOException e) {
			//e.printStackTrace();
			//System.out.println("Could not load MainPage.fxml");
		}
		//this.physics = null;

	}


	public void selectBall(int rank) {
		//System.out.println("rank"+rank);
		this.currBall = new Ball(colors[n], rank);
	}



	public void selectBall() {
		fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Layout/selectBall.fxml"));
		try {
			//selectBall.intializePane(mainRoot);
			pane = fxmlLoader.load();
			Main.scene.setRoot(pane);
			//mainRoot.getChildren().setAll(pane);
		} catch (IOException e) {
			//e.printStackTrace();
			//System.out.println("Could not load selectBall.fxml");
		}
	}

	private void unlockBall() {
		///TODO
	}
	boolean done = false;
	public void die() {

		//Main.current = this;
		AnchorPane p =new AnchorPane();
		p.setMinSize(400, 700);
		p.setMaxSize(400, 700);
		Label A = new Label("Game Over :( \n"+"Score : "+this.score+"\n\nContinue with\n" +(Math.max(1,(int)this.score/5))+"\nstar(s) ?");
		A.setMinWidth(400);
		A.setMaxWidth(400);
		A.setTextAlignment(TextAlignment.CENTER);
		//A.setLayoutX(200-A.getMaxWidth()/2);
		A.setLayoutY(50);

		Button b = new Button("Yes");
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				//End();
				Continue();		
			}

		};		Button b1 = new Button("Exit");
		EventHandler<MouseEvent> eventHandlere = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				End();	
			}

		};

		b.setMinWidth(300);
		b.setMaxWidth(300);
		b1.setMinWidth(300);
		b1.setMaxWidth(300);
		b.setTextAlignment(TextAlignment.CENTER);
		b1.setTextAlignment(TextAlignment.CENTER);
		b1.setOnMouseClicked(eventHandlere);
		b.setLayoutX(50);
		b.setOnMouseClicked(eventHandler);
		b.setLayoutY(500);
		b1.setLayoutX(50);
		b.setOnMouseClicked(eventHandler);
		b1.setLayoutY(600);
		p.getChildren().addAll(b,A,b1);
		if(this.score<2) {
			b.setDisable(true);
			b.setText("Can't continue (score<2)");
		}
		Main.setborder(p);
		Main.scene.setRoot(p);



	}


	protected void End() {
		Main.incTotal(score);
		int temp = Integer.valueOf(Main.currentd.getLeaderBaord().split(" ")[1]);
		if(score >= temp) {
			Main.currentd.setLeaderBaord(Main.current.getNAME()+" "+score);
		}
		try{
			Main.current.controller.Exit();
		}catch(Exception e) {
			//e.printStackTrace();
		}
		//this.resetElem();
		Main.current.exitGame();
	}

	protected void Continue() {
		mediaPlayer.seek(mediaPlayer.getStartTime());
		if(!Main.sound){
			mediaPlayer.play();
		}
		else {
			mediaPlayer.pause();
		}
		//System.out.println("now");
		this.score-=Math.max(1,this.score/5);
		this.physics.stop();
		Main.current.currBall.y += 100;
		if(Main.current.currBall.y>500)Main.current.currBall.y = 500;
		Main.current.currBall.dead = false;
		Main.current.controller.Exit();
		//physics = new Physics();
		fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Layout/GamePlay.fxml"));
		try {
			pane = fxmlLoader.load();
			controller = fxmlLoader.getController();
			Main.setborder(pane);
			Main.scene.setRoot(pane);
		} catch (IOException e) {
			//e.printStackTrace();
			//System.out.println("Could not load GamePlay.fxml");
		}
	}

	public String getNAME() {
		return this.NAME.get();
	}

	public int getScore() {
		return this.score;
	}
	public void setScore(int s) {
		this.score += s;
		if(this.score > this.highest) {
			this.highest = this.score;
			this.HIGHEST.set(Integer.toString(highest));
			//mediaPlayerHS.play();
		}

	}

	public int getHighest() {
		return this.highest;
	}


	public void changeNAME(String name) {
		if(Main.currentd.Players.containsKey(name)) {
			Main.current = Player.fromString(Main.currentd.Players.get(name));
		}
		else {
			this.highest = 0;
			Main.currentd.Players.put(name, this.toString());
		}
		this.NAME.set(name);
		this.HIGHEST.set(Integer.toString(highest));
	}

	private static Player fromString(String string) {
		Player p = null;
		String details[] = string.split(" ");
		p = new Player(details[0]);
		p.highest = Integer.valueOf(details[1]);
		p.HIGHEST.set(details[1]);
		return p;
	}

	public void start() {
		resetElem();
		Main.current.currBall.dead  = false;
		physics = new Physics();
		fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Layout/GamePlay.fxml"));
		try {
			pane = fxmlLoader.load();
			controller = fxmlLoader.getController();
			Main.setborder(pane);
			Main.scene.setRoot(pane);
		} catch (IOException e) {
			//e.printStackTrace();
			//System.out.println("Could not load GamePlay.fxml");
		}

	}

	public void load(savedStates s) {
		String b = s.objects.get(1);
		this.currBall = Ball.fromString(b);
		this.changeNAME(s.objects.get(2).split(" ")[0]);
		reloadElem(s);
		this.physics = new Physics();
		//Main.current = this;
		fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Layout/GamePlay.fxml"));

		try {
			pane = fxmlLoader.load();
			controller = fxmlLoader.getController();
			Main.setborder(pane);
			Main.scene.setRoot(pane);
		} catch (IOException e) {
			//e.printStackTrace();
			//System.out.println("Could not load GamePlay.fxml");
		}

		Main.currentd.saved.remove(s);
	}

	private void reloadElem(savedStates s) {
		this.score = Integer.valueOf(s.objects.get(2).split(" ")[1]);
		currBall = Ball.fromString(s.objects.get(1));
		//		currBall.x = 185;
		//		currBall.y = 500;
		for(int j  = 3;j<s.objects.size();j++) {
			Allelements[j-3]  = GameElement.fromString(s.objects.get(j));
			//System.out.println(Allelements[j-3].getClass());
		}
		Main.current = this;

	}

	@Override
	public String toString() {
		String form = "";
		form+=this.getNAME();
		form+=" ";
		form+=Integer.toString(this.highest);
		return form;
	}





}
