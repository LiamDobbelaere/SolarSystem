import javafx.scene.shape.Sphere;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Digaly on 10/10/2016.
 */
public class PlanetManager implements Iterable<Planet3D>
{
    private List<Planet3D> planets;

    public PlanetManager() {
        this.planets = new ArrayList<>();
    }

    public void add(Planet planet) {
        planets.add(new Planet3D(planet, new Sphere(planet.getDiameter()/2)));
    }

    @Override
    public Iterator iterator()
    {
        return planets.iterator();
    }
}
