import javafx.scene.shape.Sphere;

/**
 * Created by Digaly on 10/10/2016.
 */
public class Trail3D
{
    private Sphere sphere;
    private int life;

    public Trail3D(Sphere sphere, int life) {
        this.sphere = sphere;
        this.life = life;
    }

    public Sphere getSphere()
    {
        return sphere;
    }

    public int getLife()
    {
        return life;
    }

    public void drainLife() {
        life -= 1;
    }
}
