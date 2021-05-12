package ColorSwitch;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Ball {
	private ImageView ballView = new ImageView();
	private boolean collided;
	public boolean dead;
	private Color colour;
	private int rank;
	private String path;
	private String shape;
	public double x = 185;
	public double y = 500;
	public Ball(Color clr, int rnk ) {
		this.colour = clr;
		this.rank = rnk;
		this.dead = false;
		if(rank==1)shape = "Assets/circle";
		else if(rank == 2)shape = "Assets/square";
		else if(rank == 3)shape = "Assets/triangle";
		this.changeColor(clr);
		this.ballView.setCache(true);
		updateView();
		Main.rotateNode(ballView, 15, 15,2);
	}

	public int getRank() {
		return this.rank;
	}

	public Color getColor() {
		return this.colour;
	}

	public void changeColor(Color clr) {
		if(clr.equals(Color.RED))this.path = shape+"Red.png";
		else if(clr.equals(Color.BLUE))this.path=shape+"Blue.png";
		else if(clr.equals(Color.GREEN))this.path=shape+"Green.png";
		else if(clr.equals(Color.YELLOW))this.path=shape+"Yellow.png";
		this.colour = clr;
		this.updateView();
		
	}

	public void burst() {
		if(!dead) {
			//Main.current.physics.stop();
			
			Main.current.die();
			//
			dead = true;
		}
		//dead = false;
		//TODO
		//this.path = shape+"RED.png";
		//this.colour = Color.RED;
		//fromName("1 0xffff00ff 185.0 297.26879999999727");
		//this.y = 500;
		//Main.current.pauseGame();
		//TODO end game
		//System.exit(0);
		//Physics.stopped = true;
		
		
	}

	public String getPath() {

		return path;
	}
	public boolean isCollided() {
		return this.collided;
	}

	public void collide() {
		this.collided  = true;
	}
	
	public ImageView getView() {
		return this.ballView;
	}
	
	public void updateView() {
		Image i  = new Image(this.getPath(), 30, 30, false, false);
		ballView.setImage(i);
		ballView.setLayoutX(x);
		ballView.setLayoutY(y);
	}
	
	@Override
	public String toString() {
		String form = "Ball ";
		form+=Integer.toString(rank);
		form+=" ";
		form+=this.colour.toString();
		form+=" ";
		form+=Double.toString(x);
		form+=" ";
		form+=Double.toString(y);
		return form;
	}
	
	
	public static Ball fromString(String s) {
		String details[] = s.split(" ");
		Color c = Color.valueOf(details[2]);
		int rank = Integer.valueOf(details[1]);
		//System.out.println(c);
		Ball b = new Ball(c, rank);
		b.x = Double.valueOf(details[3]);
		b.y = Double.valueOf(details[4]);
		return b;
	}
}
