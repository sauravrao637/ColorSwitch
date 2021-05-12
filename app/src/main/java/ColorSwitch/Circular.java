package ColorSwitch;



import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class Circular extends Obstacle{



	private double radius ;
	private double rs; // rotation speed
	private Arc arc1;
	private Arc arc2;
	private Arc arc3;
	private Arc arc4;


	public Circular(double x, double y,double r,double speed) {
		super(x, y);
		this.radius = r;
		this.rs = speed;
		//x+=40;
		Glow g = new Glow();
		g.setLevel(10);
		arc1 = new Arc();
		{
			arc1.setFill(Paint.valueOf("TRANSPARENT"));

			arc1.setLayoutX(0);
			arc1.setLayoutY(0);
			arc1.setLength(90.0);
			arc1.setRadiusX(radius);
			arc1.setRadiusY(radius);
			arc1.setStartAngle(-45);
			arc1.setStroke(Paint.valueOf("BLUE"));
			arc1.setStrokeLineCap(StrokeLineCap.valueOf("BUTT"));
			arc1.setStrokeLineJoin(StrokeLineJoin.valueOf("ROUND"));
			arc1.setStrokeWidth(5.0);
			arc1.setEffect(g);
		}

		arc2 = new Arc();{


			arc2.setFill(Paint.valueOf("TRANSPARENT"));
			arc2.setLayoutX(0);
			arc2.setLayoutY(0);
			arc2.setLength(90.0);
			arc2.setRadiusX(radius);
			arc2.setRadiusY(radius);
			arc2.setStartAngle(45);
			arc2.setStroke(Paint.valueOf("GREEN"));
			arc2.setStrokeLineCap(StrokeLineCap.valueOf("BUTT"));
			arc2.setStrokeLineJoin(StrokeLineJoin.valueOf("ROUND"));
			arc2.setStrokeWidth(5.0);
			arc2.setEffect(g);
		}
		arc3 = new Arc();
		{
			arc3.setFill(Paint.valueOf("TRANSPARENT"));
			arc3.setLayoutX(0);
			arc3.setLayoutY(0);
			arc3.setLength(90.0);
			arc3.setRadiusX(radius);
			arc3.setRadiusY(radius);
			arc3.setStartAngle(135);
			arc3.setStroke(Paint.valueOf("RED"));
			arc3.setStrokeLineCap(StrokeLineCap.valueOf("BUTT"));
			arc3.setStrokeLineJoin(StrokeLineJoin.valueOf("ROUND"));
			arc3.setStrokeWidth(5.0);
			arc3.setEffect(g);
		}
		arc4 = new Arc();
		{
			arc4.setFill(Paint.valueOf("TRANSPARENT"));
			arc4.setLayoutX(0);
			arc4.setLayoutY(0);
			arc4.setLength(90.0);
			arc4.setRadiusX(radius);
			arc4.setRadiusY(radius);
			arc4.setStartAngle(225);
			arc4.setStroke(Paint.valueOf("YELLOW"));
			arc4.setStrokeLineCap(StrokeLineCap.valueOf("BUTT"));
			arc4.setStrokeLineJoin(StrokeLineJoin.valueOf("ROUND"));
			arc4.setStrokeWidth(5.0);
			arc4.setEffect(g);
		}
		Main.rotateNode(arc1,0,0,rs);
		Main.rotateNode(arc2,0,0,rs);
		Main.rotateNode(arc3,0,0,rs);
		Main.rotateNode(arc4,0,0,rs);

		//this.View.getStyleClass().add("fx-background-color:white");
		this.View.getChildren().addAll(arc1,arc2,arc3,arc4);
		update();
	}
	private Circle b = new Circle();

	@Override
	public void action(Pane canvas) {

		Ball b = Main.current.currBall;
		double dist = Math.pow(Math.pow((b.x+15-this.x),2)+Math.pow((b.y+15 -  this.y),2),0.5);
		if(dist<this.radius+14 && dist > this.radius-14 )
		{
			double y1 = (this.arc1.getBoundsInParent().getCenterX());
			double x1 = this.arc1.getBoundsInParent().getCenterY();
			double tangent1 = Math.abs(y1)/Math.abs(x1);
			double degs1 = Math.toDegrees(Math.atan(tangent1));
			//degs1-=45;
			if(x1>0 &&y1>0 ) {

			}
			else if(x1<0 && y1>0) {
				degs1 = 180-degs1;
			}
			else if(x1<0 &&y1<0) {
				degs1 = degs1+180;
			}
			else if(x1>0 && y1<0) {
				degs1 = 360-degs1;
			}
			if(degs1>45 && degs1<135) {
				if(this.y>b.y) {
					if(b.getColor().equals(Color.GREEN));
					else {
						b.burst();
					}
					//System.out.println("green");
				}
				else
				{
					if(b.getColor().equals(Color.YELLOW));
					else {
						b.burst();
					}
					//System.out.println("yellow");
				}

			}
			else if(degs1>135 && degs1<225) {
				if(this.y>b.y) {
					if(b.getColor().equals(Color.BLUE));
					else {
						b.burst();
					}
					//System.out.println("blue");
				}
				else
				{
					if(b.getColor().equals(Color.RED));
					else {
						b.burst();
					}
					//System.out.println("red");
				}
			}
			else if(degs1>225 && degs1 <315) {
				if(this.y>b.y) {
					if(b.getColor().equals(Color.YELLOW));
					else {
						b.burst();
					}
					//System.out.println("yellow");
				}
				else
				{
					if(b.getColor().equals(Color.GREEN));
					else {
						b.burst();
					}
					//System.out.println("green");
				}
			}
			else if((degs1>315 && degs1<359)||(degs1>0 &&degs1<45)) {
				if(this.y>b.y) {
					if(b.getColor().equals(Color.RED));
					else {
						b.burst();
					}
					//System.out.println("red");
				}
				else 
				{
					if(b.getColor().equals(Color.BLUE));
					else {
						b.burst();
					}
					//System.out.println("blue");
				}
			}
			
		}



	}

	@Override
	public void reset(Pane canvas) {
		this.isActive = true;
		if(this.radius>50) {
			this.radius -= 5;
		}
		update();

	}

	@Override
	public Group getView() {

		return this.View;
	}

	@Override
	public void update() {
		this.View.setLayoutX(x);
		this.View.setLayoutY(y);
		arc1.setRadiusX(radius);
		arc1.setRadiusY(radius);
		arc2.setRadiusX(radius);
		arc2.setRadiusY(radius);
		arc3.setRadiusX(radius);
		arc3.setRadiusY(radius);
		arc4.setRadiusX(radius);
		arc4.setRadiusY(radius);
	}

	@Override
	protected double getRadius() {
		return this.radius;
	}
	
	@Override
	public String toString() {
		String form ="Circular ";
		form+=Double.toString(radius);
		form+=" ";
		form+=Double.toString(rs);
		form+=" ";
		form+=Double.toString(x);
		form+=" ";
		form+=Double.toString(y);
		
		return form;
	}
	
	
	public static Circular fromString(String s) {
		String details[] = s.split(" ");
		Circular c = null;
		c = new Circular(Double.valueOf(details[3]),Double.valueOf(details[4]),Double.valueOf(details[1]),Double.valueOf(details[2]));
		return c;
	}
}
