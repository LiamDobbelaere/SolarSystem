import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * Created by Digaly on 9/10/2016.
 */
public class Solarsystem extends Application
{
    private static double zTrans;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.BLUE);

        final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.WHITE);
        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);

        Sphere sphere = new Sphere(40);
        sphere.setMaterial(whiteMaterial);
        sphere.setTranslateX(100);
        sphere.setTranslateY(100);
        root.getChildren().add(sphere);

        Camera camera = new PerspectiveCamera();
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
                //zTrans-=5;
                scene.getCamera().setTranslateZ(zTrans);
            }
        }.start();


        primaryStage.setTitle("Solar System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
