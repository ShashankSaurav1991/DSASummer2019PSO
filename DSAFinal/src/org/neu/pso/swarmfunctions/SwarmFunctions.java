/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neu.pso.swarmfunctions;

import java.util.Random;
import java.util.Vector;
import static org.neu.pso.config.Configurations.PROBLEM_DIMENSION;
import static org.neu.pso.config.Configurations.SWARM_SIZE;
import org.neu.pso.model.Location;
import org.neu.pso.model.PBest;
import org.neu.pso.model.Particle;
import org.neu.pso.model.Velocity;

/**
 *
 * @author anshit
 * @author shashanksaurav
 */
public class SwarmFunctions {
    private Vector<Particle> swarm = new Vector<Particle>();
    
    public static final double S_X_LOW_LOC = 0;
    public static final double S_X_HIGH_LOC = 200;
    public static final double S_Y_LOW_LOC = 0;
    public static final double S_Y_HIGH_LOC = 200; // define the actual range of location
    public static final double PAR_LOW_VEL = 0;
    public static final double PAR_HIGH_VEL = 10; // define the actual range of velocity

    public static final double PAR_ERR_TOLERANCE = 1E-20; // smaller will have a accuracy on result 
    
    
    
    public static final double X_NEIGHBOURHOOD_C = 100;
    public static final double Y_NEIGHBOURHOOD_C = 100; //initialize the location of living area in population zone C
    public static final double X_NEIGHBOURHOOD_D = 150;
    public static final double Y_NEIGHBOURHOOD_D = 150; //initialize the location of living area area in population zone D

    public static final double X_NEIGHBOURHOOD_B = 0;
    public static final double Y_NEIGHBOURHOOD_B = 0;
    public static final long POPULATION_B = 300;
    public static final long POPULATION_C = 100;
    public static final double DIATANCE_B = Math.sqrt((Math.pow((X_NEIGHBOURHOOD_B - X_NEIGHBOURHOOD_C), 2) + Math.pow((Y_NEIGHBOURHOOD_B - Y_NEIGHBOURHOOD_C), 2))); //set some attributes for the basic point B, can be modified

    // Reilly's law of retail gravitation
    public static double calculateFitnessValue(Location location, double population) { 
        double x = location.getLoc()[0];
        double y = location.getLoc()[1];
        double distance = Math.sqrt((Math.pow((x - X_NEIGHBOURHOOD_C), 2) + Math.pow((y - Y_NEIGHBOURHOOD_C), 2))); // calculate the distance from point C
        double result = 3000 - Math.pow((distance - 30), 2);
        return result;
    }
    
    public static int getMaxPos(double[] listOfPos) {
        int positionResult = 0;
        double maxPosValue = listOfPos[0];
        for (int i = 1; i < listOfPos.length; i++) {
            if (listOfPos[i] > maxPosValue) {
                maxPosValue = listOfPos[i];
                positionResult = i;
            }
        }
        return positionResult;
    }

    public static double calculatePopulation(Location location) {
        double population = 0;
        double x = location.getLoc()[0];
        double y = location.getLoc()[1];
        double distance = Math.sqrt((Math.pow((x - X_NEIGHBOURHOOD_C), 2) + Math.pow((y - Y_NEIGHBOURHOOD_C), 2)));
        population = POPULATION_C - distance / 2 ; 
        if (population > 0) {
            return population;
        } else {
            return 0;
        }
    }

    public static double calculateDistance(Location location) {
        double x = location.getLoc()[0];
        double y = location.getLoc()[1];
        double distance = Math.sqrt((Math.pow((x - X_NEIGHBOURHOOD_C), 2) + Math.pow((y - Y_NEIGHBOURHOOD_C), 2)));
        return distance;
    }
    
    public Vector<Particle> initializeSwarm() {
        Random generator = new Random();
        calculateParticleMovement(generator);
        return swarm;
    }
    
    
    public void calculateParticleMovement(Random generator){
            for (int i = 0; i < SWARM_SIZE; i++) {
                Particle particle = new Particle();
               // randomize location
                double[] loc = new double[PROBLEM_DIMENSION];
                loc[0] = SwarmFunctions.S_X_LOW_LOC + generator.nextDouble() * (SwarmFunctions.S_X_HIGH_LOC - SwarmFunctions.S_X_LOW_LOC);
                loc[1] = SwarmFunctions.S_Y_LOW_LOC + generator.nextDouble() * (SwarmFunctions.S_Y_HIGH_LOC - SwarmFunctions.S_Y_LOW_LOC);
                Location location = new Location(loc);

                // randomize velocity
                double[] vel = new double[PROBLEM_DIMENSION];
                vel[0] = SwarmFunctions.PAR_LOW_VEL + generator.nextDouble() * (SwarmFunctions.PAR_HIGH_VEL - SwarmFunctions.PAR_LOW_VEL);
                vel[1] = SwarmFunctions.PAR_LOW_VEL + generator.nextDouble() * (SwarmFunctions.PAR_HIGH_VEL - SwarmFunctions.PAR_LOW_VEL);
                Velocity velocity = new Velocity(vel);

                // pBest is calculated initially with current particle itself
                PBest pBest = new PBest();
                pBest.setId(i);
                pBest.setLocation(location);
                pBest.setFitnessValue(calculateFitnessValue(location, calculatePopulation(location)));
                particle.setLocation(location);
                particle.setVelocity(velocity);
                particle.setId(i);
                particle.setPopulation(calculatePopulation(location));
                particle.setDistance(calculateDistance(location));
                particle.setpBest(pBest);
                swarm.add(particle);
            } // end for
    } // end calculateParticleMovement function
}
