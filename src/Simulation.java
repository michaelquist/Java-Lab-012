import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static void main(String[] args) {
        SolarSystem solarSystem = new SolarSystem();
        solarSystem.addPlanet(new Planet("Earth", 6371, 5.97E24, 149.6E6, 0, 0));
        solarSystem.addPlanet(new Planet("Mars", 3389, 6.39E23, 227.9E6, 0, 0));
        solarSystem.addSun(new Sun("Sun", 696340.0, 1.99E30, 5778.0,0.0, 0.0));
        solarSystem.movePlanets();
        solarSystem.showPlanets();

    }
}

class SolarSystem {
    private List<Planet> planets;
    private Sun theSun;

    public SolarSystem() {
        planets = new ArrayList<>();
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }

    public void addSun(Sun sun) {
        theSun = sun;
    }

    public void showPlanets() {
        for (Planet planet : planets) {
            System.out.println(planet.toString());
        }
    }

    public void movePlanets() {
        double G = 0.1;
        double dt = 0.001;

        for (Planet planet : planets) {
            planet.moveTo(planet.getXPos() + dt * planet.getXVel(),
                    planet.getYPos() + dt * planet.getYVel());

            double rx = theSun.getXPos() - planet.getXPos();
            double ry = theSun.getYPos() - planet.getYPos();
            double r = Math.sqrt(Math.pow(rx, 2) + Math.pow(ry, 2));

            double accX = G * theSun.getMass() * rx / Math.pow(r, 3);
            double accY = G * theSun.getMass() * ry / Math.pow(r, 3);

            planet.setXVel(planet.getXVel() + dt * accX);
            planet.setYVel(planet.getYVel() + dt * accY);
        }
    }
}

class Planet {
    private String name;
    private double radius;
    private double mass;
    private double distance;
    private double x;
    private double y;
    private double velX;
    private double velY;

    public Planet(String name, double radius, double mass, double distance, double velX, double velY) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.distance = distance;
        this.velX = velX;
        this.velY = velY;
    }

    public double getXPos() {
        return x;
    }

    public double getYPos() {
        return y;
    }

    public void moveTo(double newX, double newY) {
        x = newX;
        y = newY;
    }

    public double getXVel() {
        return velX;
    }

    public double getYVel() {
        return velY;
    }

    public void setXVel(double newVelX) {
        velX = newVelX;
    }

    public void setYVel(double newVelY) {
        velY = newVelY;
    }

    public String toString() {
        return name + " at (" + x + ", " + y + ")";
    }
}

class Sun {
    private String name;
    private double radius;
    private double mass;
    private double temp;
    private double x;
    private double y;

    public Sun(String name, double radius, double mass, double temp, double v, double v1) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.temp = temp;
        this.x = x;
        this.y = y;
    }

    public double getXPos() {
        return x;
    }

    public double getYPos() {
        return y;
    }

    public double getMass() {
        return mass;
    }
}

