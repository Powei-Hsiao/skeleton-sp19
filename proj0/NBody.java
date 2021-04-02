public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Body[] readBodies(String filename){

		In in = new In(filename);
		int n = in.readInt();
		Body[] bodies = new Body[n];
		double radius = in.readDouble();
		for (int i = 0; i < n; i = i + 1){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFilename = in.readString();
			bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFilename);
		}
		return bodies;

	}
	public static void main(String[] args){
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Body[] bodies = NBody.readBodies(filename);
		double radius = NBody.readRadius(filename);
		String imageToDraw = "images/starfield.jpg";

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0,0,imageToDraw);
		for (int i = 0; i < bodies.length; i = i + 1){
			Body b = bodies[i];
			b.draw();
		}
		StdDraw.show();

		for (double time = 0; time < t; time = time + dt){
			Double[] xForces = new Double[bodies.length];
			Double[] yForces = new Double[bodies.length];
			for (int i = 0; i < bodies.length; i = i + 1){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for (int i = 0; i < bodies.length; i = i + 1){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0,0,imageToDraw);
			for (int i = 0; i < bodies.length; i = i + 1){
			    Body b = bodies[i];
			    b.draw();
		    }
		    StdDraw.show();
		    StdDraw.pause(10);
		}


	}
}