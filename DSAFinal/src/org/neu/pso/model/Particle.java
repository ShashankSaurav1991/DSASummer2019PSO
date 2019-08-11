/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neu.pso.model;

/**
 *
 * @author anshit
 */
public class Particle implements Runnable{
    private int Id;
    private double fitnessValue;
    private Velocity velocity;
    private Location location;
    private double distance;
    private double population;
    private PBest pBest;

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Constructor
    public Particle(double fitnessValue, Velocity velocity, Location location) {
        super();
        this.fitnessValue = fitnessValue;
        this.velocity = velocity;
        this.location = location;
    }

    public Particle() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public PBest getpBest() {
        return pBest;
    }

    public void setpBest(PBest pBest) {
        this.pBest = pBest;
    }   
}
