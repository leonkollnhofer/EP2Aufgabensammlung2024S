package AB3Muster;

import AB1Muster.Vector3;
import AB2Muster.Body;
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

    // TODO: implement according to 'Aufgabenblatt3.md'.
    /**
     * The main simulation method using instances of other classes.
     * @param args not used.
     */
    public static void main(String[] args) {

        // simulation
        CodeDraw cd = new CodeDraw();
        AB3Muster.BodyQueue queue = new AB3Muster.BodyQueue();
        BodyAccelerationTreeMap map = new BodyAccelerationTreeMap();

        Random random = new Random(2024);

        for (int i = 0; i < NUMBER_OF_BODIES; i++) {
            queue.add(new Body(Math.abs(random.nextGaussian()) * OVERALL_SYSTEM_MASS / NUMBER_OF_BODIES,
                    new Vector3( 0.2 * random.nextGaussian() * AU,  0.2 * random.nextGaussian() * AU,  0.2 * random.nextGaussian() * AU),
                    new Vector3(random.nextGaussian() * 5e3,  random.nextGaussian() * 5e3,
                            random.nextGaussian() * 5e3)));
        }

        double seconds = 0;

        // simulation loop
        while (true) {
            seconds++; // each iteration computes the movement of the celestial bodies within one second.

            // merge bodies that have collided
            for (int i = 0; i < queue.size(); i++) {
                Body first = queue.poll();
                for (int j = 0; j < queue.size(); j++) {
                    Body second = queue.poll();
                    if (first.distanceTo(second) < first.getRadius() + second.getRadius()) {
                        first = first.merge(second);
                    } else {
                        queue.add(second);
                    }
                }
                queue.add(first);
            }

            // for each body (with index i): compute its total acceleration.
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

            // now map associates for each body its current acceleration vector.

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
                //System.out.println(seconds);

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