/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import org.neu.pso.config.Configurations;
import static org.neu.pso.swarmfunctions.SwarmFunctions.X_NEIGHBOURHOOD_C;
import static org.neu.pso.swarmfunctions.SwarmFunctions.X_NEIGHBOURHOOD_D;
import static org.neu.pso.swarmfunctions.SwarmFunctions.Y_NEIGHBOURHOOD_C;
import static org.neu.pso.swarmfunctions.SwarmFunctions.Y_NEIGHBOURHOOD_D;

/**
 *
 * @author shashanksaurav
 */
public class Display extends JPanel implements Configurations{
    
    public void paint (Graphics g){
        Graphics2D plot_2dG = (Graphics2D) g;
        plot_2dG.fillOval((int)X_NEIGHBOURHOOD_C, (int)Y_NEIGHBOURHOOD_C, 30, 30);
        plot_2dG.fillOval((int)X_NEIGHBOURHOOD_D, (int)Y_NEIGHBOURHOOD_D, 30, 30);
        plot_2dG.setColor(Color.ORANGE);
        
        for(int i = 0; i < SWARM_SIZE; i++){
            plot_2dG.fillOval((int) swarm.get(i).getLocation().getLoc()[0], (int) swarm.get(i).getLocation().getLoc()[1], 10, 10);//should be double, need to be modified
            repaint((int) swarm.get(i).getLocation().getLoc()[0], (int) swarm.get(i).getLocation().getLoc()[1], 10, 10);
        }
  
    }
}