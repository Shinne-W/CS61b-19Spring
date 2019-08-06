public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
	}

	public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b) {
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		return Math.hypot(dx, dy);
	}

	public double calcForceExertedBy(Body b) {
		return G * mass * b.mass/Math.pow(this.calcDistance(b),2);
	}

	public double calcForceExertedByX(Body b) {
        return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
    }    

    public double calcNetForceExertedByX(Body[] bN) {
    	double FNet = 0;
    	for(Body b : bN){
    		if(! this.equals(b)){
    			FNet += this.calcForceExertedByX(b);
    		}
    	}
    	return FNet;
    }

    public double calcNetForceExertedByY(Body[] bN) {
    	double FNet = 0;
    	for(Body b : bN){
    		if(! this.equals(b)){
    			FNet += this.calcForceExertedByY(b);
    		}
    	}
    	return FNet;
    }    

    public void update(double dt, double fX, double fY) {
    	this.xxVel = this.xxVel + dt * fX/this.mass;
    	this.yyVel = this.yyVel + dt * fY/this.mass;
    	this.xxPos = this.xxPos + dt * this.xxVel;
    	this.yyPos = this.yyPos + dt * this.yyVel; 
    }
}