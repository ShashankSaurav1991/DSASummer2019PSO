/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neu.pso.driver;

import static org.neu.pso.config.Configurations.SWARM_SIZE;
import static org.neu.pso.config.Configurations.swarm;
import org.neu.pso.display.SwarmDisplay;
import static java.lang.Thread.sleep;
import javax.swing.JFrame;

/**
 *
 * @author anshit
 */
public class Driver {

    public static void main(String args[]) throws InterruptedException {
        JFrame newFrame = new JFrame();
        newFrame.setVisible(true);
        newFrame.setSize(600, 600);
        newFrame.setTitle("Particle Swarm Optimization");

        Thread[] threads = new Thread[SWARM_SIZE];
        for (int i = 0; i < SWARM_SIZE; i++) {
            threads[i] = new Thread(swarm.get(i));
        }
        for (int i = 0; i < SWARM_SIZE; i++) {
            threads[i].start();
            sleep(100);
            newFrame.add(new SwarmDisplay());
        }
    }
}
