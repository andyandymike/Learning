
public class Cal {
	private final double a, b, c;
	
	Cal(double a, double b, double c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public double[] solve(){
		double r1 = (-b + Math.sqrt(b*b - 4*a*c))/(2*a);
		double r2 = (-b - Math.sqrt(b*b - 4*a*c))/(2*a);
		double[] r = new double[2];
		r[0] = r1;
		r[1] = r2;
		return r;
	}
}
