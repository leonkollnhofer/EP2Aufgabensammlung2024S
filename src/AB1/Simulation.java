package AB1;

import codedraw.*;

import java.awt.*;
import java.util.Random;

// TODO: insert answers to questions (Zusatzfragen) in 'Aufgabenblatt1.md' as comment.

/**
 * Simulates the formation of a massive solar system.
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
    public static final double SECTION_SIZE = 2 * AU; // the size of the square region in space
    public static final int NUMBER_OF_BODIES = 50;
    public static final double OVERALL_SYSTEM_MASS = 30 * SUN_MASS; // kilograms

    // all quantities are based on units of kilogram respectively second and meter.

    /**
     * The main simulation method using instances of other classes.
     * @param args not used.
     */
    public static void main(String[] args) {

        //TODO: change implementation of this method according to 'Aufgabenblatt1.md'.

        // simulation
        CodeDraw cd = new CodeDraw();
        Body[] bodies = new Body[NUMBER_OF_BODIES];
        Vector3[] accelerationOfBody = new Vector3[bodies.length];

        Random random = new Random(2024);

        for (int i = 0; i < bodies.length; i++) {
            bodies[i] = new Body();
            bodies[i].mass = Math.abs(random.nextGaussian()) * OVERALL_SYSTEM_MASS / bodies.length; // kg
            bodies[i].massCenter = new Vector3();
            bodies[i].currentMovement = new Vector3();
            bodies[i].massCenter.x = 0.2 * random.nextGaussian() * AU;
            bodies[i].massCenter.y = 0.2 * random.nextGaussian() * AU;
            bodies[i].massCenter.z = 0.2 * random.nextGaussian() * AU;

            bodies[i].currentMovement.x = random.nextGaussian() * 5e3;
            bodies[i].currentMovement.y = random.nextGaussian() * 5e3;
            bodies[i].currentMovement.z = random.nextGaussian() * 5e3;

        }

        double seconds = 0;

        // simulation loop
        while (true) {
            seconds++; // each iteration computes the movement of the celestial bodies within one second.

            // merge bodies that have collided
            for (int i = 0; i < bodies.length; i++) {
                for (int j = i + 1; j < bodies.length; j++) {
                    if (distance(bodies[j].massCenter, bodies[i].massCenter) <
                            SpaceDraw.massToRadius(bodies[j].mass) + SpaceDraw.massToRadius(bodies[i].mass)) {
                        // collision of bodies i and j
                        bodies[i] = merge(bodies[i], bodies[j]);

                        // generate a duplicate of the array with body j removed.
                        Body[] bodiesOneRemoved = new Body[bodies.length - 1];
                        for (int k = 0; k < bodiesOneRemoved.length; k++) {
                            bodiesOneRemoved[k] = bodies[k < j ? k : k + 1];
                        }
                        bodies = bodiesOneRemoved;

                        // since the body index i changed size there might be new collisions
                        // at all positions of bodies, so start all over again
                        i = -1;
                        j = bodies.length;
                    }
                }
            }

            // for each body (with index i): compute its total acceleration.
            for (int i = 0; i < bodies.length; i++) {
                accelerationOfBody[i] = new Vector3(); // begin with zero
                for (int j = 0; j < bodies.length; j++) {
                    if (i != j) {
                        Vector3 toAdd = acceleration(bodies[i], bodies[j]);
                        accelerationOfBody[i] = plus(accelerationOfBody[i], toAdd);
                    }
                }
            }
            // now accelerationOfBody[i] holds the total acceleration vector for bodies[i].

            // for each body (with index i): accelerate it for one second.
            for (int i = 0; i < bodies.length; i++) {
                accelerate(bodies[i], accelerationOfBody[i]);
            }

            // show all movements in the canvas only every hour (to speed up the simulation)
            if (seconds % (3600) == 0) {
                // clear old positions (exclude the following line if you want to draw orbits).
                cd.clear(Color.BLACK);

                // draw new positions
                for (Body body : bodies) {
                    draw(cd, body);
                }

                // show new positions
                cd.show();
            }

        }

    }

    //TODO: remove static methods below.

    /**
     * Draws a body in the 'cd' canvas showing a projection onto the (x,y)-plane. The body's mass
     * center coordinates and its radius are transformed into canvas coordinates. The color of
     * the body corresponds to the temperature of the body, assuming the relation of mass and
     * temperature of a main sequence star.
     * The canvas is assumed to show a quadratic SECTION_SIZE x SECTION_SIZE
     * section of space centered arround (x, y) = (0, 0).
     * @param cd the CodeDraw object used for drawing.
     * @param b a body, b != null.
     */
    public static void draw(CodeDraw cd, Body b) {

        cd.setColor(SpaceDraw.massToColor(b.mass));
        drawAsFilledCircle(cd, b.massCenter, SpaceDraw.massToRadius(b.mass));

    }

    /**
     * Draws a filled circle in the 'cd' canvas using the (x,y)-coordinates of 'center'
     * Coordinates and 'radius' are transformed into canvas coordinates. The canvas is assumed
     * to show a quadratic SECTION_SIZE x SECTION_SIZE projection of space centered arround (x, y) =
     * (0, 0).
     * @param cd the CodeDraw object used for drawing.
     * @param center the center of the circle, center != null.
     * @param radius the radius of the circle, radius > 0.
     */
    public static void drawAsFilledCircle(CodeDraw cd, Vector3 center, double radius) {

        double x = cd.getWidth() * (center.x + Simulation.SECTION_SIZE / 2) / Simulation.SECTION_SIZE;
        double y = cd.getWidth() * (center.y + Simulation.SECTION_SIZE / 2) / Simulation.SECTION_SIZE;
        radius = cd.getWidth() * radius / Simulation.SECTION_SIZE;
        cd.fillCircle(x, y, Math.max(radius, 1.5));
    }

    /**
     * Returns the acceleration vector of 'b1' that results from the gravitational interaction with
     * body 'b2'. This returned vector is computed according to Newton's laws of gravitation.
     * @param b1 a body, b1 != null.
     * @param b2 another body, b2 != null.
     * @return an acceleration vector.
     */
    public static Vector3 acceleration(Body b1, Body b2) {

        // "force" F between two masses m₁ and m₂:
        // F = Gm₁m₂/d² = m₁a₁ -> a₁ = Gm₂/d²
        Vector3 direction = minus(b2.massCenter, b1.massCenter);
        double distance = length(direction);
        normalize(direction);
        double length = G * b2.mass / (distance * distance);
        return times(direction, length);
    }

    /**
     * Returns a new body that is formed by the collision of 'b1' and 'b2'. The mass of the
     * returned body is the sum of the masses of 'b1' and 'b2'. The current movement of the
     * returned body is given by the law of conservation of momentum. (The momentum of the
     * returned body is the sum of the momentums of 'b1' and 'b2').
     * @param b1 a body, b1 != null.
     * @param b2 another body, b2 != null.
     * @return the body formed by the collision.
     */
    public static Body merge(Body b1, Body b2) {

        Body result = new Body();
        result.mass = b1.mass + b2.mass;
        result.massCenter = times(plus(times(b1.massCenter, b1.mass), times(b2.massCenter,
                        b2.mass)),
                1 / result.mass);

        // Momentum of a body corresponds to its velocity (currentMovement) times its mass.
        // Momentum v₃m₃ of result b₃ is the sum of the momentums of b₁ and b₂:
        // v₃m₃ = v₁m₁ + v₂m₂ -> v₃ = (v₁m₁ + v₂m₂)/m₃
        result.currentMovement =
                times(plus(times(b1.currentMovement, b1.mass), times(b2.currentMovement, b2.mass)),
                        1.0 / result.mass);
        return result;
    }

    /**
     * Accelerate the body 'b' for one second according to the 'acceleration' vector.
     * @param b the body to be accelerated, b != null.
     * @param acceleration the acceleration vector, acceleration != null.
     */
    public static void accelerate(Body b, Vector3 acceleration) {

        // accelerate for one second and update movement
        b.currentMovement = plus(b.currentMovement, acceleration);
        b.massCenter = plus(b.massCenter, b.currentMovement);
    }

    /**
     * Returns the norm of v1 minus v2.
     * @param v1 a vector, v1 != null.
     * @param v2 a vector, v2 != null.
     * @return the norm of v1 - v2.
     */
    public static double distance(Vector3 v1, Vector3 v2) {

        double dX = v1.x - v2.x;
        double dY = v1.y - v2.y;
        double dZ = v1.z - v2.z;

        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }

    /**
     * Returns v1 plus v2.
     * @param v1 a vector, v1 != null.
     * @param v2 a vector, v2 != null.
     * @return a new vector representing v1 + v2.
     */
    public static Vector3 plus(Vector3 v1, Vector3 v2) {

        Vector3 result = new Vector3();
        result.x = v1.x + v2.x;
        result.y = v1.y + v2.y;
        result.z = v1.z + v2.z;

        return result;
    }

    /**
     * Returns v1 minus v2.
     * @param v1 a vector, v1 != null.
     * @param v2 a vector, v2 != null.
     * @return a new vector representing v1 - v2.
     */
    public static Vector3 minus(Vector3 v1, Vector3 v2) {

        Vector3 result = new Vector3();
        result.x = v1.x - v2.x;
        result.y = v1.y - v2.y;
        result.z = v1.z - v2.z;

        return result;
    }

    /**
     * Returns the product of 'v' and 'd'.
     * @param v a vector, v != null.
     * @param d a coefficient.
     * @return a new vector d times v.
     */
    public static Vector3 times(Vector3 v, double d) {

        Vector3 result = new Vector3();
        result.x = v.x * d;
        result.y = v.y * d;
        result.z = v.z * d;

        return result;
    }

    /**
     * Returns the norm of 'v'.
     * @param v a vector, v != null.
     * @return the norm of v.
     */
    public static double length(Vector3 v) {

        return distance(v, new Vector3()); // distance to origin.
    }

    /**
     * Normalizes the specified vector 'v': changes the length of the vector such that its length
     * becomes one. The direction of the vector is not affected.
     * @param v vector to be normalized, v != null.
     */
    public static void normalize(Vector3 v) {

        double length = length(v);
        v.x /= length;
        v.y /= length;
        v.z /= length;
    }
}
