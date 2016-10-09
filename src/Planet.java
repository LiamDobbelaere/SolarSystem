/**
 * Created by Tom Dobbelaere on 9/10/2016.
 *
 */
public class Planet
{
    private String name;
    private double distanceToSun; //In kilometers
    private double diameter; //In kilometers
    private double axisSpinTime; //In minutes
    private double sunOrbitTime; //In days

    public Planet(String name, double distanceToSun, double diameter, double axisSpinTime, double sunOrbitTime) {
        this.name = name;
        this.distanceToSun = distanceToSun;
        this.diameter = diameter;
        this.axisSpinTime = axisSpinTime;
        this.sunOrbitTime = sunOrbitTime;
    }

    public String getName()
    {
        return name;
    }

    public double getDistanceToSun()
    {
        return distanceToSun;
    }

    public double getDiameter()
    {
        return diameter;
    }

    public double getAxisSpinTime()
    {
        return axisSpinTime;
    }

    public double getSunOrbitTime()
    {
        return sunOrbitTime;
    }
}
