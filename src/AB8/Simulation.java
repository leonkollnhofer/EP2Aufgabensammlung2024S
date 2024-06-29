package AB8;

import java.io.IOException;
import java.util.*;

/**
 * Simulates the solar system.
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
    /**
     * The main simulation method using instances of other classes.
     * @param args not used.
     */
    public static void main(String[] args) {

        // create solar system (only selected objects):
        Body sun = new Body("Sun", 1.989E30);
        Body earth = new Body("Earth", 5.972E24);
        Body moon = new Body("Moon", 7.349E22);
        Body mars = new Body("Mars", 6.41712E23);
        Body deimos = new Body("Deimos", 1.8E15);
        Body phobos = new Body("Phobos", 1.072E16);
        Body mercury = new Body("Mercury", 3.301E23);
        Body venus = new Body("Venus", 4.86747E24);
        Body vesta = new Body("Vesta", 2.5908E20);
        Body pallas = new Body("Pallas", 2.14E20);
        Body hygiea = new Body("Hygiea", 8.32E19);
        Body ceres = new Body("Ceres", 9.394E20);
        Body jupiter = new Body("Jupiter", 1.89813E27);
        Body io = new Body("Io", 8.93193797E22);
        Body europa = new Body("Europa", 4.8E22);
        Body ganymede = new Body("Ganymede", 1.4819E23);
        Body callisto = new Body("Callisto", 1.0759E23);
        Body amalthea = new Body("Amalthea", 2.07E18);
        Body saturn = new Body("Saturn", 5.6834E26);
        Body mimas = new Body("Mimas", 3.75E19);
        Body enceladus = new Body("Enceladus", 1.08E20);
        Body tethys = new Body("Tethys", 6.176E20);
        Body dione = new Body("Dione", 1.096E21);
        Body rhea = new Body("Rhea", 2.309E21);
        Body titan = new Body("Titan", 1.34553E23);
        Body neptune = new Body("Neptune", 1.024E26);
        Body uranus = new Body("Uranus", 8.681E25);
        Body halley = new Body("Halley", 2E14);
        Body voyager1 = new Body("Voyager1", 810);
        Body voyager2 = new Body("Voyager2", 825);

        // bodies for which there is no state-file for testing exception handling:
        Body oumuamua = new Body("Oumuamua", 8e6);
        Body himalia = new Body("Himalia", 6.7E18);

        /* TODO: uncomment this block:
        HierarchicalSystem marsSystem = new MultiBody(mars,
                phobos, deimos);
        HierarchicalSystem earthSystem = new MultiBody(earth, moon);
        HierarchicalSystem jupiterSystem = new MultiBody(jupiter, io, europa, ganymede, callisto,
                amalthea, himalia);
        HierarchicalSystem saturnSystem = new MultiBody(saturn, mimas, enceladus, tethys, dione,
                rhea, titan);
        HierarchicalSystem solarSystem = new MultiBody(sun, mercury, marsSystem,
                earthSystem, jupiterSystem, saturnSystem, uranus, neptune, venus, vesta, pallas,
                hygiea, ceres, halley, voyager1, voyager2, oumuamua);

        //TODO: end of block to uncomment.*/

        //TODO ('Bonusaufgabe'): implement simulation including validation of external data
        // according to 'Aufgabenblatt8.md'.
    }
}