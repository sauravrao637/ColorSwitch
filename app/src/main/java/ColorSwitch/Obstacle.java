package ColorSwitch;

import javafx.scene.Group;
import javafx.scene.shape.Arc;

public abstract class Obstacle extends GameElement{
	
	public Group View = new Group();
	
	public Obstacle(double x, double y) {
		super(x, y);
		this.View.setCache(true);
	}
	
	@Override 
	public abstract Group getView();

	protected abstract double getRadius();
	
	
	

}
