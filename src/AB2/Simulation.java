package AB2;

import AB1.SpaceDraw;
import AB1.Vector3;
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
}
