package ColorSwitch;

import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SquareO extends Obstacle{
	private double radius;
	private double rs;
	private Line line1;
	private Line line2;
	private Line line3;
	private Line line4;
	public SquareO(double x, double y,double r,double speed) {
		super(x, y);
		this.radius = r;
		this.rs = speed;
		//Main.rotateNode(this.View, 0,0, rs);
		update();
	}
	@Override
	public Group getView() {
		//System.out.println("returned view");
		return this.View;

	}
	@Override
	public void action(Pane canvas) {
		Ball b = Main.current.currBall;
		double dist = Math.pow(Math.pow((b.x+15-this.x),2)+Math.pow((b.y+15 -  this.y),2),0.5);
		if(dist<((radius*1.414)+15)&&dist>((radius/2)-15)) {
			double y1= line1.getBoundsInParent().getCenterX();
			double x1= line1.getBoundsInParent().getCenterY();
			double tangent1 = Math.abs(y1)/Math.abs(x1);
			double degs1 = Math.toDegrees(Math.atan(tangent1));
			double x = radius/(Math.cos(Math.toRadians(degs1%45)));
			//System.out.println("angle"+Math.cos(Math.toRadians(degs1%45)));
			if(dist<x+15 &&dist>x-15) {
				
			}
			else {
				//GamePlay.pause();
				//System.out.println("x"+x);
				return;
			}
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
			//this.radius -= 5;
		}
		update();

	}
	@Override
	protected void update() {
		this.View.setLayoutX(x);
		this.View.setLayoutY(y);
		{
			line1 = new Line(-this.radius, -this.radius, this.radius, -this.radius);
			line1.setStroke(Color.BLUE);
			line1.setStrokeWidth(10);
			line1.setEffect(new Glow(10));
		}
		
		{
			line2 = new Line(-this.radius, -this.radius, -this.radius,this.radius);
			line2.setStroke(Color.GREEN);
			line2.setStrokeWidth(10);
			line2.setEffect(new Glow(10));
		}
		
		{
			line3 = new Line(this.radius,-this.radius, this.radius,this.radius);
			line3.setStroke(Color.YELLOW);
			line3.setStrokeWidth(10);
			line3.setEffect(new Glow(10));
		}
		
		
		{
			line4 = new Line(-this.radius, this.radius, this.radius,this.radius);
			line4.setStroke(Color.RED);
			line4.setStrokeWidth(10);
			line4.setEffect(new Glow(10));
		}
		this.View.getChildren().clear();
		Main.rotateNode(line1,0, 0, rs);
		Main.rotateNode(line2,0, 0, rs);
		Main.rotateNode(line3,0, 0, rs);
		Main.rotateNode(line4,0, 0, rs);
		this.View.getChildren().addAll(line1,line2,line3,line4);
		
	}
	@Override
	protected double getRadius() {
		return this.radius;
	}
	
	@Override
	public String toString() {
		String form ="SquareO ";
		form+=Double.toString(radius);
		form+=" ";
		form+=Double.toString(rs);
		form+=" ";
		form+=Double.toString(x);
		form+=" ";
		form+=Double.toString(y);
		
		return form;
	}
	public static SquareO fromString(String s) {
		String details[] = s.split(" ");
		SquareO c = null;
		c = new SquareO(Double.valueOf(details[3]),Double.valueOf(details[4]),Double.valueOf(details[1]),Double.valueOf(details[2]));
		return c;
	}
}
