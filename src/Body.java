
public class Body {
	private double myXVel;
	private double myYVel;
	private double myXPos;
	private double myYPos;
	private double myMass;
	private String myFileName;

	/**
	 * 
	 * @param x initial x position
	 * @param y initial y position
	 * @param xv initial x velocity
	 * @param yv initial y veloitcy
	 * @param mass mass of the object
	 * @param filename of image for object animation
	 */
	public Body(double x, double y, double xv, double yv, double mass, String filename){
		myXVel = xv;
		myYVel = yv;
		myXPos = x;
		myYPos = y;
		myMass = mass;
		myFileName = filename;
	}
	/**
	 * Copy constructor: copy instance variables from one body to this body
	 * @param b used to initialize this body
	 */
	public Body(Body b) {
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myXPos = b.getX();
		myYPos = b.getY();
		myMass = b.getMass();
		myFileName = b.getName();
	}

	/**
	 * all the getters for the body class
	 */
	public double getXVel(){
		return myXVel;
	}
	public double getYVel(){
		return myYVel;
	}
	public double getX(){
		return myXPos;
	}
	public double getY(){
		return myYPos;
	}
	public double getMass(){
		return myMass;
	}
	public String getName(){
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this and body b
	 */
	public double calcDistance(Body b) {
		double deltaX = b.getX() - myXPos; //store the change in x
		double deltaY = b.getY() - myYPos; //store change in y
		double rSquared = Math.pow(deltaX, 2) + Math.pow(deltaY, 2); //calculate r^2
		return Math.sqrt(rSquared); //find r
	}

	/**
	 * Return the forceExterted by this body on another body
	 * @param p the other body to which the force is being exerted by
	 * @return the force exerted
	 */
	public double calcForceExertedBy(Body p){
		double g = 6.67*1e-11; 
		return ((g * p.getMass() * myMass) / Math.pow(calcDistance(p), 2));
	}
	/**
	 * return the force exerted in the x direction by p on this
	 * @param p the other body
	 * @return the force exerted in the x direction
	 */
	public double calcForceExertedByX(Body p) {
		return this.calcForceExertedBy(p)*((p.getX() - this.getX())/(this.calcDistance(p)));
	}

	/**
	 * return the force exerted in the y direction by p on this
	 * @param p the other body
	 * @return the force exerted in the y direction
	 */
	public double calcForceExertedByY(Body p) {
		return this.calcForceExertedBy(p)*((p.getY() - this.getY())/(this.calcDistance(p)));
	}

	/**
	 * Return the total/net force exerted on this body by all the bodies in the array parameter in the x direction
	 * @param array of all the bodies in the array 
	 * @return the total force exerted in the x direction by all the bodies on this body
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double sum = 0;
		for(Body b : bodies) {
			if(!b.equals(this)) {
				sum += calcForceExertedByX(b);
			}
		}
		return sum;
	}
	
	/**
	 * Return the total/net force exerted on this body by all the bodies in the array parameter in the y direction
	 * @param array of all the bodies in the array 
	 * @return the total force exerted in the y direction by all the bodies on this body
	 */
	
	public double calcNetForceExertedByY(Body[] bodies) {
		double sum = 0;
		for(Body b : bodies) {
			if(!b.equals(this)) {
				sum += calcForceExertedByY(b);
			}
		}
		return sum;
	
	}
	/**
	 * Update the position and velocities of the body based on everything else around it
	 * @param deltaT the small time steps to update the body
	 * @param xforce net x forces exerted on this body by all other bodies in the simulation
	 * @param yforce net y forces exerted on this body by all other bodies in the simulation
	 */
	public void update(double deltaT, double xforce, double yforce) {
		double xAcceleration = 0;
		double yAcceleration = 0; 
		double nvx = 0;
		double nvy = 0;
		double nx = 0;
		double ny = 0;
		xAcceleration = xforce/myMass;
		yAcceleration = yforce/myMass;
		nvx = myXVel + (deltaT * xAcceleration);
		nvy = myYVel + (deltaT * yAcceleration);
		nx = myXPos + (deltaT * nvx);
		ny = myYPos + (deltaT * nvy);
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
		
	}
	
	/**
	 * draws the bodies, based on the positions and the image and file name
	 */
	
	public void draw(){
	StdDraw.picture(myXPos, myYPos, "images/"+ myFileName);
	}
	
	

}

