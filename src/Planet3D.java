import javafx.scene.shape.Sphere;

/**
 * Created by Digaly on 10/10/2016.
 */
public class Planet3D
{
    private Planet planet;
    private Sphere sphere;

    public Planet3D(Planet planet, Sphere sphere) {
        this.planet = planet;
        this.sphere = sphere;
    }

    public Planet getPlanet()
    {
        return planet;
    }

    public Sphere getSphere()
    {
        return sphere;
    }
}
