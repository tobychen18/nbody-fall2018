	

/**
 * @author Toby Chen
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
	
		// TODO: read values at beginning of file to
		// find the radius
		s.nextInt(); //don't read the # of bodies
		double radius = s.nextDouble(); //find the radius
		
		s.close();
		
		// TODO: return radius read
		return radius;	
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies(String fname) throws FileNotFoundException {
		
			Scanner s = new Scanner(new File(fname));
			
			// TODO: read # bodies, create array, ignore radius
			int nb = s.nextInt(); // # bodies to be read
			Body[] readBodies = new Body[nb];
			s.nextDouble(); //skip the next double bc that's the radius
			for(int k=0; k < nb; k++) {
				//reads the data in that line where each number corresponds with the proper values in the constructor
				readBodies[k] = new Body(s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), s.next());
				// TODO: read data for each body ^^
				// construct new body object and add to array 
			}
			
			s.close();
			
			// TODO: return array of body objects read
			return readBodies;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 157788000.0;
		double dt = 25000.0;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname); 
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {
			
			// TODO: create double arrays xforces and yforces
			// to hold forces on each body
			//xForces and yForces hold the net x and y forces reflectively
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			// TODO: loop over all bodies, calculate
			// net forces and store in xforces and yforces
			//loops over all the bodies to add the forces using i as a counter
			int i = 0;
			for(Body b: bodies){
				
				yForces[i] = b.calcNetForceExertedByY(bodies);
				xForces[i] = b.calcNetForceExertedByX(bodies);
				
				i++;
						
			}
			// TODO: loop over all bodies and call update
			// with dt and corresponding xforces, yforces values
			//loops over all the bodies to updated the body using i as a counter
			i = 0;
			for(Body b: bodies){
				b.update(dt, xForces[i], yForces[i]);
				i++;
			}
			
			
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			// TODO: loop over all bodies and call draw on each one
			for(Body b: bodies){
				b.draw();
			}
			StdDraw.show(10);
		}
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
