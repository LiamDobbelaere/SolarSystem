import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Digaly on 9/10/2016.
 */
public class Solarsystem extends Application
{
    private static double zTrans = -3500;
    private static double frames;
    private static List<Planet> planets;

    public static void main(String[] args) {
        planets = new ArrayList<>();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        planets.add(new Planet("Mercury", 57_900_000, 4_878, 84960, 88));
        planets.add(new Planet("Venus", 108_160_000, 12_104, 349920, 224));
        planets.add(new Planet("Earth", 149_600_000, 12_756, 1436, 365.25));
        planets.add(new Planet("Mars", 227_936_640, 6_794, 1477, 687));
        planets.add(new Planet("Jupiter", 778_369_000, 142_984, 595, 4328.9));
        planets.add(new Planet("Saturn", 1_427_034_000, 120_536, 639, 10585));
        planets.add(new Planet("Uranus", 2_870_658_186.0, 51_118, 1034, 30660));
        planets.add(new Planet("Neptune", 4_496_976_000.0, 49_532, 967, 60152));

        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.BLACK);

        final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.WHITE);
        //whiteMaterial.setDiffuseMap(new Image("sun.gif"));

        Sphere sphere = new Sphere(695.700);
        sphere.setMaterial(whiteMaterial);
        root.getChildren().add(sphere);

        Camera camera = new PerspectiveCamera(true);
        camera.setFarClip(10000);
        camera.setNearClip(0.1);
        scene.setCamera(camera);

        scene.setOnScroll(new EventHandler<ScrollEvent>()
        {
            @Override
            public void handle(ScrollEvent event)
            {
                zTrans += event.getDeltaY();
            }
        });

        new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                //sphere.setTranslateX(sphere.getTranslateX() + 1);
                sphere.setTranslateX(Math.cos(frames / 10) * 800);
                sphere.setTranslateY(Math.sin(frames / 10) * 800);

                sphere.setRotationAxis(new Point3D(0, 1, 0));
                sphere.setRotate(sphere.getRotate() + 1);
                //zTrans-=5;
                scene.getCamera().setTranslateZ(zTrans);

                frames++;
            }
        }.start();


        primaryStage.setTitle("Solar System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
