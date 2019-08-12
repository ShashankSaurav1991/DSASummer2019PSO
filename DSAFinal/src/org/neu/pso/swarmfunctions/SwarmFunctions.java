/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neu.pso.swarmfunctions;

import java.util.Random;
import java.util.Vector;
import static org.neu.pso.config.Configurations.SWARM_SIZE;
import org.neu.pso.model.Location;
import org.neu.pso.model.Particle;

/**
 *
 * @author anshit
 */
public class SwarmFunctions {
    private Vector<Particle> swarm = new Vector<Particle>();
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
    
        public static int getMaxPos(double[] list) {
        int result = 0;
        double maxValue = list[0];
        for (int i = 1; i < list.length; i++) {
            if (list[i] > maxValue) {
                maxValue = list[i];
                result = i;
            }
        }
        return result;
    }

    public static double calculatePopulation(Location location) {
        double population = 0;
        double x = location.getLoc()[0];
        double y = location.getLoc()[1];
        double distance = Math.sqrt((Math.pow((x - X_C), 2) + Math.pow((y - Y_C), 2)));
        population = POPULATION_C - distance / 2 ; // cause the distribution of population is linear, so I designed a linear f(x) to calculate population
        if (population > 0) {
            return population;
        } else {
            return 0;
        }
    }

    public static double calculateDistance(Location location) {
        double x = location.getLoc()[0];
        double y = location.getLoc()[1];
        double distance = Math.sqrt((Math.pow((x - X_C), 2) + Math.pow((y - Y_C), 2)));
        return distance;
    }
    
    
        public Vector<Particle> initializeSwarm() {
        
        Random generator = new Random();
        for (int i = 0; i < SWARM_SIZE; i++) {
            Particle p = new Particle();

        }
        return swarm;
}
}
