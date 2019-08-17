/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neu.pso.agent;

import java.util.Random;
import org.neu.pso.config.Configurations;
import org.neu.pso.model.Location;
import org.neu.pso.model.PBest;
import org.neu.pso.model.Particle;
import org.neu.pso.model.Velocity;
import org.neu.pso.swarmfunctions.SwarmFunctions;
import static org.neu.pso.swarmfunctions.SwarmFunctions.calculatePopulation;

/**
 *
 * @author anshit
 */
public class PSOAgent implements Configurations {

    private double gBest;
    private Location gBestLocation;
    private final double[] fitnessValues = new double[SWARM_SIZE];

    Random generator = new Random();

    public Location execute(int Id) {

        updateFitnessList();
        Particle p = swarm.get(Id);
        int t = 0;
        double w;
        double err = 9999;
        while (t < MAX_ITERATION && err > SwarmFunctions.PAR_ERR_TOLERANCE) {
            // step 1 - update pBest, based on each particle
            for (int i = 0; i < SWARM_SIZE; i++) {
                double oldFitnessValue = swarm.get(i).getFitnessValue();
                PBest newPBest = getPBest(swarm.get(i));
                double newFitnessValue = newPBest.getFitnessValue();
                if (newFitnessValue > oldFitnessValue) {
                    swarm.get(i).setpBest(newPBest);
                }
            }
            p.setpBest(getPBest(p));
            fitnessValues[Id] = swarm.get(Id).getFitnessValue();

            // step 2 - update population gBest
            updateFitnessList();
            int bestParticleIndex = SwarmFunctions.getMaxPos(fitnessValues);//to find a maximum position
            if (t == 0 || fitnessValues[bestParticleIndex] > gBest) {
                gBest = fitnessValues[bestParticleIndex];
                gBestLocation = swarm.get(bestParticleIndex).getLocation();
            }

            w = W_UPPERBOUND - (((double) t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);

            double r1 = generator.nextDouble();
            double r2 = generator.nextDouble();

            // step 3 - update population velocity
            double[] newVel = new double[PROBLEM_DIMENSION];
            newVel[0] = (w * p.getVelocity().getPos()[0])
                    + (r1 * C1) * (getPBest(swarm.get(Id)).getLocation().getLoc()[0] - p.getLocation().getLoc()[0])
                    + (r2 * C2) * (gBestLocation.getLoc()[0] - p.getLocation().getLoc()[0]);
            newVel[1] = (w * p.getVelocity().getPos()[1])
                    + (r1 * C1) * (getPBest(swarm.get(Id)).getLocation().getLoc()[1] - p.getLocation().getLoc()[1])
                    + (r2 * C2) * (gBestLocation.getLoc()[1] - p.getLocation().getLoc()[1]);
            Velocity vel = new Velocity(newVel);
            p.setVelocity(vel);

            // step 4 - update  poulation location
            double[] newLoc = new double[PROBLEM_DIMENSION];
            newLoc[0] = p.getLocation().getLoc()[0] + newVel[0];
            newLoc[1] = p.getLocation().getLoc()[1] + newVel[1];
            Location loc = new Location(newLoc);
            p.setLocation(loc);

            err = SwarmFunctions.calculateFitnessValue(gBestLocation, calculatePopulation(gBestLocation)) - 0; // minimizing the functions means it's getting closer to 0

            System.out.println("ITERATION " + t + ": ");
            System.out.println(" Best X: " + gBestLocation.getLoc()[0] + "\n"
                    + " Best Y:" + gBestLocation.getLoc()[1] + "\n"
                    + "Value:" + SwarmFunctions.calculateFitnessValue(gBestLocation, calculatePopulation(gBestLocation)));
            t++;
            updateFitnessList();
        } // end while loop

        System.out.println("----------------------------------------------------------------------------");
        System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
        System.out.println("     Best X: " + gBestLocation.getLoc()[0]);
        System.out.println("     Best Y: " + gBestLocation.getLoc()[1]);
        System.out.println("----------------------------------------------------------------------------");
        return gBestLocation;
    } // end function execute

    public void updateFitnessList() {
        for (int i = 0; i < SWARM_SIZE; i++) {
            fitnessValues[i] = swarm.get(i).getFitnessValue();
        }
    }
    
    //based on the view of this specific particle, store the PBest in a range
    public PBest getPBest(Particle particle) {
        PBest p = new PBest();
        p.setId(particle.getId());
        p.setLocation(particle.getLocation());
        p.setFitnessValue(particle.getFitnessValue());

        double max = 0;
        double x0 = particle.getLocation().getLoc()[0];
        double y0 = particle.getLocation().getLoc()[1];

        //get some information of other particles
        for (int i = 0; i < SWARM_SIZE; i++) {
            double x1 = swarm.get(i).getLocation().getLoc()[0];
            double y1 = swarm.get(i).getLocation().getLoc()[1];
            double range = Math.sqrt((Math.pow((x0 - x1), 2) + Math.pow((y0 - y1), 2))); // calculate the distance between current particle and other particle
            double population1 = calculatePopulation(swarm.get(i).getLocation());
            double fitnessVue1 = SwarmFunctions.calculateFitnessValue(swarm.get(i).getLocation(), population1);
            if (range <= RANGEBOUND && (max < fitnessVue1)) {
                max = fitnessVue1;
                p.setId(i);
                p.setLocation(swarm.get(i).getLocation());
                p.setFitnessValue(max);
            }
        }
        return p;
    }
}
