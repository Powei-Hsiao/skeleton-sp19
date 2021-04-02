public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double x1 = this.xxPos;
		double x2 = b.xxPos;
		double y1 = this.yyPos;
		double y2 = b.yyPos;
		double d1 = x1 - x2;
		d1 = Math.pow(d1, 2);
		double d2 = y1 - y2;
		d2 = Math.pow(d2, 2);
		return Math.sqrt(d1 + d2);

	}

	public double calcForceExertedBy(Body b){
		double g = 6.67e-11;
		double m1 = this.mass;
		double m2 = b.mass;
		return g *  m1 * m2 / Math.pow(this.calcDistance(b), 2);
	}
	public double calcForceExertedByX(Body b){
		double f = this.calcForceExertedBy(b);
		double r = this.calcDistance(b);
		double dx = b.xxPos - this.xxPos;
		return f * dx / r;
	}
	public double calcForceExertedByY(Body b){
		double f = this.calcForceExertedBy(b);
		double r = this.calcDistance(b);
		double dy = b.yyPos - this.yyPos;
		return f * dy / r;
    }
    public double calcNetForceExertedByX(Body[] allBodys){
    	double netFx = 0;
    	for (int i = 0; i < allBodys.length; i = i + 1){
    		if (this.equals(allBodys[i])){
                continue;
    		}else{
    			netFx = netFx + this.calcForceExertedByX(allBodys[i]);
    		}
    	}
    	return netFx;
    }
    public double calcNetForceExertedByY(Body[] allBodys){
    	double netFy = 0;
    	for (int i = 0; i < allBodys.length; i = i + 1){
    		if (this.equals(allBodys[i])){
    			continue;
    		} else {
    			netFy = netFy + this.calcForceExertedByY(allBodys[i]);
    		}
    	}
    	return netFy;
    }
    public void update(double dt, double fX, double fY){
    	double newvx = this.xxVel + dt * fX / this.mass;
    	double newvy = this.yyVel + dt * fY / this.mass;
    	double newpx = this.xxPos + dt * newvx;
    	double newpy = this.yyPos + dt * newvy;
    	this.xxVel = newvx;
    	this.yyVel = newvy;
    	this.xxPos = newpx;
    	this.yyPos = newpy;
    }
    public void draw(){
    	String imgToDraw = "images/"+ this.imgFileName;
    	StdDraw.picture(this.xxPos, this.yyPos, imgToDraw);
    }
}