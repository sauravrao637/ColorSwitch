package ColorSwitch;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.*;
import java.util.ArrayList;
import javafx.scene.media.Media;
import java.util.stream.Stream;
public class Main extends Application{
	public static Scene scene;
	public static MediaPlayer mediaPlayer;
	public static ArrayList<Timeline> allTimelines = new ArrayList<Timeline>();
	public static Player current = null;
	public static boolean darkT = true;
	private static int total = 0;
	public static DataBase currentd;
	public static boolean sound = false;
	public final static SimpleStringProperty TOTAL = new SimpleStringProperty(Integer.toString(total));

	@Override
	public void start(Stage primaryStage) throws Exception{
		deserialize();
		addMusic();
//		System.out.println(currentd.saved.size());
		incTotal(currentd.getTotal());
//		System.out.println(total);
		try{
			Parent mainPage = FXMLLoader.load(getClass().getClassLoader().getResource("Layout/MainPage.fxml"));
			scene = new Scene(mainPage,400,700);
			scene.setFill(Color.TRANSPARENT);

			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setTitle("Color Switch");
			primaryStage.setScene(scene);
			primaryStage.show();
			Main.scene.getStylesheets().add(getClass().getClassLoader().getResource("Stylesheet/GameMenuD.css").toExternalForm());

		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("could not load MainPage.fxml");
		}
	}


	public void addMusic() {
        Media sound = new Media(getClass().getClassLoader().getResource("Assets/background.mpeg").toString());
        mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setStartTime(Duration.seconds(0));
        mediaPlayer.setStopTime(Duration.seconds(50));
        //mediaPlayer.play();
    }


	public static void serialize() throws IOException {
		currentd.setTotal(total);
		ObjectOutputStream out=null;
		try {
			out = new ObjectOutputStream (new FileOutputStream("database.txt"));
			out.writeObject(currentd);
		}
		finally {
			out.close();
			//System.out.println("Saved!");
			System.exit(0);
		}
	}

	public static  void deserialize() throws ClassNotFoundException, FileNotFoundException, IOException{
		ObjectInputStream in = null;
		try {
			in=new ObjectInputStream (new FileInputStream("database.txt"));
			currentd=(DataBase) in.readObject();
			in.close();
		}
		catch (FileNotFoundException e){
			currentd=new DataBase();
		}
		catch (NullPointerException e) {
			currentd=new DataBase();
			//System.out.println("This user does not exist in the database");
		}
	}



	public static void main(String[] args) {
		launch(args);
	}

	public static void setborder(Pane pane) {
		Color[] colors = Stream.of("darkorange", "tomato", "deeppink", "blueviolet", "steelblue", "cornflowerblue", "lightseagreen", "#6fba82", "chartreuse", "crimson")
				.map(Color::web)
				.toArray(Color[]::new);


		int mills[] = {-200};
		KeyFrame keyFrames[]  = Stream.iterate(0, i -> i+1)
				.limit(100)
				.map(i -> new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0, colors[i%colors.length]), new Stop(1, colors[(i+1)%colors.length])}))
				.map(lg -> new Border(new BorderStroke(lg, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(10))))
				.map(b -> new KeyFrame(Duration.millis(mills[0]+=200), new KeyValue(pane.borderProperty(), b, Interpolator.EASE_IN)))
				.toArray(KeyFrame[]::new);

		Timeline timeline1 = new Timeline(keyFrames);
		timeline1.setCycleCount(Timeline.INDEFINITE);
		timeline1.play();
		allTimelines.add(timeline1);
	}

	public static void rotateNode(Node node,double x,double y,double rs){
		double t = Math.abs(12/rs);

		Rotate rotate = new Rotate(0,x,y);
		if(rs<0) {
			rotate.setAxis(new Point3D(0, 0,-1));
		}
		node.getTransforms().add(rotate);
		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0d)),
				new KeyFrame(Duration.seconds(t), new KeyValue(rotate.angleProperty(), 360d)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(false);

		timeline.play();
		allTimelines.add(timeline);
	}



	public static Group makeCircle(double radius) {
		Arc arc1,arc2,arc3,arc4;
		Glow g  = new Glow(10);
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

		Group rotatee = new Group();
		rotatee.getChildren().addAll(arc1,arc2,arc3,arc4);
		return rotatee;
	}

	public static void incTotal(int t) {
		total+=t;
		TOTAL.setValue(Integer.toString(total));
	}
}
