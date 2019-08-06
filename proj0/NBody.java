public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}

	public static Body[] readBodies(String filename) {
		In in = new In(filename);
		int num = in.readInt();
		in.readDouble();
		Body[] Planets = new Body[num];
		for (int i = 0; i < num ; i += 1){
			double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Planets[i] = new Body(xP, yP, xV, yV, m, img);
		}
		return Planets;
	}

	public static void main(String[] args) {
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		double radius = NBody.readRadius(filename);
        Body[] Planets = NBody.readBodies(filename);
        int num = Planets.length;

        /** Draw the background of the Universe; */
        String imageToDraw = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw, 2*radius, 2*radius);

        /** Draw all the Bodies. */
        for(Body planet : Planets){
        	StdDraw.picture(planet.xxPos, planet.yyPos, "images/"+planet.imgFileName);
        }
        StdDraw.show();

        /** Create the animation. */
        for(double t = 0; t <= T; T += dt){
        	double[] xForces = new double[num];
        	double[] yForces = new double[num];
        	for(int i = 0; i < num; i += 1){
        	xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
        	yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
        	}
        	for(int i = 0; i < num; i += 1){
        	Planets[i].update(dt, xForces[i], yForces[i]);
        	}
        	StdDraw.clear();
        	StdDraw.picture(0, 0, imageToDraw, 2*radius, 2*radius);
        	for(Body planet : Planets){
        	StdDraw.picture(planet.xxPos, planet.yyPos, "images/"+planet.imgFileName);
        	}
        	StdDraw.show();
        	StdDraw.pause(10);

        }

        StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
}

	}
}

