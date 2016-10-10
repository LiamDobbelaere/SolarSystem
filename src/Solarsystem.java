import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Digaly on 9/10/2016.
 */
public class Solarsystem extends Application
{
    private static double zTrans = -3500;
    private static double frames;
    private static PlanetManager planets;
    private static List<Trail3D> trails;
    private static double timeScale;
    private static int camSpeed;

    public static void main(String[] args) {
        planets = new PlanetManager();
        trails = new CopyOnWriteArrayList<>();
        timeScale = 1;
        camSpeed = 100000;
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
        whiteMaterial.setDiffuseColor(Color.RED);
        //whiteMaterial.setDiffuseMap(new Image("sun.gif"));

        for (Planet3D planet : planets) {
            root.getChildren().add(planet.getSphere());
        }

        Sphere sun = new Sphere(695700 / 2);
        root.getChildren().add(sun);
        //Sphere sphere = new Sphere(695.700);
        //sphere.setMaterial(whiteMaterial);
        //root.getChildren().add(sphere);

        Camera camera = new PerspectiveCamera(true);
        camera.setFarClip(Integer.MAX_VALUE);
        camera.setNearClip(0.1);
        scene.setCamera(camera);

        scene.setOnScroll(new EventHandler<ScrollEvent>()
        {
            @Override
            public void handle(ScrollEvent event)
            {
                zTrans += event.getDeltaY() * (zTrans / -50);
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch (event.getCode()) {
                    case RIGHT:
                        scene.getCamera().setTranslateX(camera.getTranslateX() + camSpeed);
                        break;
                    case LEFT:
                        scene.getCamera().setTranslateX(camera.getTranslateX() - camSpeed);
                        break;
                    case UP:
                        scene.getCamera().setTranslateY(camera.getTranslateY() - camSpeed);
                        break;
                    case DOWN:
                        scene.getCamera().setTranslateY(camera.getTranslateY() + camSpeed);
                        break;
                    case NUMPAD8:
                        scene.getCamera().setRotationAxis(new Point3D(1, 0, 0));
                        scene.getCamera().setRotate(scene.getCamera().getRotate() + 5);
                        break;
                    case NUMPAD2:
                        scene.getCamera().setRotationAxis(new Point3D(1, 0, 0));
                        scene.getCamera().setRotate(scene.getCamera().getRotate() - 5);
                        break;

                }
            }
        });

        new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                //sphere.setTranslateX(sphere.getTranslateX() + 1);
                /*sphere.setTranslateX(Math.cos(frames / 10) * 800);
                sphere.setTranslateY(Math.sin(frames / 10) * 800);

                sphere.setRotationAxis(new Point3D(0, 1, 0));
                sphere.setRotate(sphere.getRotate() + 1);*/
                //zTrans-=5;

                for (Planet3D planet : planets) {
                    planet.getSphere().setTranslateX(Math.cos(frames / planet.getPlanet().getSunOrbitTime() * timeScale) * planet.getPlanet().getDistanceToSun() / 100);
                    planet.getSphere().setTranslateY(Math.sin(frames / planet.getPlanet().getSunOrbitTime() * timeScale) * planet.getPlanet().getDistanceToSun() / 100);
                    Sphere trail = new Sphere(planet.getSphere().getRadius());

                    /*if (planet.getPlanet().getName().equals("Earth")) {
                        scene.getCamera().setTranslateX(planet.getSphere().getTranslateX());
                        scene.getCamera().setTranslateY(planet.getSphere().getTranslateY());
                    }*/

                    if (frames % 5 == 0) {
                        trail.setTranslateX(planet.getSphere().getTranslateX());
                        trail.setTranslateY(planet.getSphere().getTranslateY());
                        trail.setMaterial(new PhongMaterial(Color.GREEN));
                        trails.add(new Trail3D(trail, 10000));
                        root.getChildren().add(trail);
                    }

                    for (Trail3D trailInList : trails) {
                        trailInList.drainLife();

                        if (trailInList.getLife() <= 0) {
                            root.getChildren().remove(trailInList.getSphere());
                            trails.remove(trailInList);
                        }
                    }
                }

                scene.getCamera().setTranslateZ(zTrans);

                frames++;
            }
        }.start();


        primaryStage.setTitle("Solar System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
