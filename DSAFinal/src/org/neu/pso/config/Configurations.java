/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neu.pso.config;

import org.neu.pso.swarmfunctions.SwarmFunctions;
import java.util.Vector;
import org.neu.pso.model.Particle;

/**
 *
 * @author anshit
 */
public interface Configurations {
    int SWARM_SIZE = 50;
	int MAX_ITERATION = 200;
	int PROBLEM_DIMENSION = 2;
	double C1 = 2.0;
	double C2 = 2.0;
	double W_UPPERBOUND = 1.0;
	double W_LOWERBOUND = 0.0;
        double RANGEBOUND = 150.0;
        public double[] fitnessValueList = new double[SWARM_SIZE];
        SwarmFunctions sFunc = new SwarmFunctions();
        public static Vector<Particle> swarm = sFunc.initializeSwarm();

}
