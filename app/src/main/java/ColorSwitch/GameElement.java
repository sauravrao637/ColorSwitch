package ColorSwitch;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public abstract class GameElement implements Pos{
	protected boolean isActive = true;
	public static Random rd = new Random();
	double x;
	double y;
	public int id;

	public GameElement(double x,double y) {
		this.x= x;
		this.y = y;
	}
	
	public abstract void action(Pane canvas);

	public abstract void reset(Pane canvas);
	public double getY() {
		return this.y;
	}
	public Group getView() {
		return null;
	}

	protected abstract void update();
	@Override
	public abstract String toString();
	
	public  static  GameElement fromString(String s) {
		String details[] = s.split(" ");
		GameElement g = null;
		if(details[0].equals("CS")) {
			g = CS.fromString(s);
		}
		else if(details[0].equals("Star")) {
			g = Star.fromString(s);
		}
		else if(details[0].equals("Circular")) {
			g = Circular.fromString(s);
		}
		else if(details[0].equals("SquareO")) {
			g = SquareO.fromString(s);
		}
		return g;
		
	}
}


