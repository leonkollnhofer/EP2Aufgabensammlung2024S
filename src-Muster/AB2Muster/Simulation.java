package AB2Muster;

import AB1Muster.Vector3;
import codedraw.CodeDraw;

import java.awt.*;
import java.util.Random;

/**
 * Simulates a cluster of bodies.
 */
public class Simulation {

    // gravitational constant
    public static final double G = 6.6743e-11;

    // one astronomical unit (AU) is the average distance between earth and sun.
    public static final double AU = 150e9; // meters

    // some further constants needed in the simulation
    public static final double SUN_MASS = 1.989e30; // kilograms
    public static final double SUN_RADIUS = 696340e3; // meters

    // set some system parameters
    public static final int NUMBER_OF_BODIES = 50;
    public static final double OVERALL_SYSTEM_MASS = 30 * SUN_MASS; // kilograms

    // all quantities are based on units of kilogram respectively second and meter.

    // TODO: implement according to 'Aufgabenblatt2.md'.
    /**
     * The main simulation method using instances of other classes.
     * @param args not used.
     */
    public static void main(String[] args) {

        // simulation
        CodeDraw cd = new CodeDraw();
        BodyQueue queue = new BodyQueue(NUMBER_OF_BODIES);
        BodyAccelerationMap map = new BodyAccelerationMap(NUMBER_OF_BODIES);

        Random random = new Random(2024);

        for (int i = 0; i < NUMBER_OF_BODIES; i++) {
            queue.add(new Body(Math.abs(random.nextGaussian()) * OVERALL_SYSTEM_MASS / NUMBER_OF_BODIES,
                    new Vector3( 0.2 * random.nextGaussian() * AU,  0.2 * random.nextGaussian() * AU,  0.2 * random.nextGaussian() * AU),
                    new Vector3(random.nextGaussian() * 5e3,  random.nextGaussian() * 5e3,
                            random.nextGaussian() * 5e3)));
        }


        double seconds = 0;
/*
        queue = new BodyQueue(4);
        map = new BodyAccelerationMap(4);

        Body sun = new Body(1.989e30,new Vector3(0,0,0),new Vector3(0,0,0));
        Body earth = new Body(5.972e24,new Vector3(-1.394555e11,5.103346e10,0),new Vector3
                (-10308.53,-28169.38,0));
        Body mercury = new Body(3.301e23,new Vector3(-5.439054e10,9.394878e9,0),new Vector3
                (-17117.83,-46297.48,-1925.57));
        Body venus = new Body(4.86747e24,new Vector3(-1.707667e10,1.066132e11,2.450232e9),new
                Vector3(-34446.02,-5567.47,2181.10));

        queue.add(sun);
        queue.add(earth);
        queue.add(mercury);
        queue.add(venus);

 */


        // simulation loop
        while (true) {
            seconds++; // each iteration computes the movement of the celestial bodies within one second.

            // for each body (with index i): compute its total acceleration.
            /*
            BodyQueue bodyCopy1 = new BodyQueue(queue);

            while (bodyCopy1.size() > 0) {
                Vector3 acceleration = new Vector3(0, 0, 0); // begin with zero
                Body body1 = bodyCopy1.poll();
                BodyQueue bodyCopy2 = new BodyQueue(queue);
                while (bodyCopy2.size() > 0) {
                    Body body2 = bodyCopy2.poll();
                    if (body1 != body2) {
                        Vector3 accelerationToAdd = body1.acceleration(body2);
                        acceleration = acceleration.plus(accelerationToAdd);
                    }
                }
                map.put(body1, acceleration);
            }
            */

            for (int i = 0; i < queue.size(); i++) {
                Vector3 acceleration = new Vector3(0, 0, 0); // begin with zero
                Body toProcess = queue.poll();
                for (int j = 0; j < queue.size(); j++) {
                    Body partner = queue.poll();
                    Vector3 accelerationToAdd = toProcess.acceleration(partner);
                    queue.add(partner);
                    acceleration = acceleration.plus(accelerationToAdd);
                }
                queue.add(toProcess);
                map.put(toProcess, acceleration);
            }

            // now map associates the current acceleration vector for each body

            // for each body: accelerate it for one second.
            for (int i = 0; i < queue.size(); i++) {
                Body currentBody = queue.poll();
                queue.add(currentBody);
                currentBody.accelerate(map.get(currentBody));
            }

            // show all movements in the canvas only every hour (to speed up the simulation)
            if (seconds % (3600) == 0) {
                // clear old positions (exclude the following line if you want to draw orbits).
                cd.clear(Color.BLACK);

                // draw new positions
                for (int i = 0; i < queue.size(); i++) {
                    Body currentBody = queue.poll();
                    queue.add(currentBody);
                    currentBody.draw(cd);
                }

                // show new positions
                cd.show();
            }


        }

    }

}
