package ColorSwitch;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CS extends GameElement{
	String path = "Assets/CS.png";
	Color color;
	Color colors[] = {Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW};
	private Group view;

	public CS(double x, double y) {
		super(x,y);
		int n  = rd.nextInt(4);
		this.color = colors[n];

		view = new Group();
		Main.rotateNode(view, 15, 15,2);
		update();
	}
	@Override
	public void action(Pane canvas) {
		while(this.color.equals(Main.current.currBall.getColor())) {
			int n  = rd.nextInt(4);
			this.color = colors[n];
			
		}
		Main.current.currBall.changeColor(this.color);
		this.isActive = false;
		canvas.getChildren().get(id).setVisible(false);
	}

	@Override
	public void reset(Pane canvas) {
		int n  = rd.nextInt(4);
		this.color = colors[n];
		this.isActive = true;
		this.path = "Assets/CS.png";
		canvas.getChildren().get(id).setVisible(true);
	}

	@Override
	public Group getView() {
		return this.view;
		//return null;
	}
	@Override
	protected void update() {
		ImageView csI = new ImageView(new Image(this.path,30,30,false,false));
		view.getChildren().clear();
		view.getChildren().addAll(csI);
		view.setLayoutX(x);
		view.setLayoutY(y);
	}
	@Override
	public String toString() {
		String form = "CS ";
		form+=Double.toString(x);
		form+=" ";
		form+=Double.toString(y);
		form+=" ";
		form+=this.isActive;
		return form;
	}
	public static CS fromString(String s) {
		String details[] = s.split(" ");
		CS cs = new CS(Double.valueOf(details[1]),Double.valueOf(details[2]));
		cs.isActive = Boolean.valueOf(details[3]);
		return cs;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
