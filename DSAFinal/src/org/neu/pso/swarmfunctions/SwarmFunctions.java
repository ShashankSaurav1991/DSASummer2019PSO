/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neu.pso.swarmfunctions;

import org.neu.pso.model.Location;

/**
 *
 * @author anshit
 */
public class SwarmFunctions {
     public static final double X_C = 100;
    public static final double Y_C = 100; //initialize the location of living area C
    public static final double X_D = 150;
    public static final double Y_D = 150; //initialize the location of living area D

    public static final double X_B = 0;
    public static final double Y_B = 0;
    public static final long POPULATION_B = 500;
    public static final long POPULATION_C = 100;
    public static final double DIATANCE_B = Math.sqrt((Math.pow((X_B - X_C), 2) + Math.pow((Y_B - X_C), 2))); //set some attributes for the basic point B, can be modified

    public static double calculateFitnessValue(Location location, double population) { // Reilly's law of retail gravitation

        double x = location.getLoc()[0];
        double y = location.getLoc()[1];
        double distance1 = Math.sqrt((Math.pow((x - X_C), 2) + Math.pow((y - Y_C), 2))); // calculate the distance from C
        double result = 3000 - Math.pow((distance1 - 30), 2);
        return result;
    }
}
