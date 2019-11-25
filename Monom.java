
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	/**
	 * init a Monom from a String such as:
	 *  {"x^2", "2", "-x", "-3.2x^2","0"};
	 * @param s: is a string represents a Monom
	 */
	public Monom(String s) {
		if(s.length()==0)
			throw new RuntimeException("ERR: The string empty");
		double a=0;
		//b is double to check if the power not integer
		double b=0;
		// The flag is to check if has 'x' in the string.
		boolean flag= false;
		s=s.toLowerCase();
		// Checking if the string's shape is "x^3" or "x" etc.
		if(s.charAt(0)=='x')
			//Instead "x^3" "1x^3"
			s="1"+s;
		// Checking if the string's shape is "-x^3" or "-x" etc.
		else if(s.length()>1 && s.charAt(1)=='x' && s.charAt(0)=='-' )
			////Instead "-x^3" "-1x^3"
			s="-1"+s.substring(1);
		else if(s.length()>1 && s.charAt(1)=='x' && s.charAt(0)=='+' )
			////Instead "+x^3" "+1x^3"
			s="+1"+s.substring(1);
		for(int i=0; i<s.length() && !flag; i++) {
			if (s.charAt(i)=='x') {
				flag= true;
				// Checking if the string's shape is "-6*x^3" or "3*x" etc.and change the string to "-6x^3" or "3x" etc.
				if(s.charAt(i-1)=='*') {
					s=s.substring(0,i-1) +s.substring(i);
					//Now the index of the 'x' in s is i-1
					i--;
				}
				//Checking if  string's shape is "22x" or "x" etc.
				if(i==s.length()-1) {
					try {
					a=  Double.parseDouble(s.substring(0, i));
					}
					catch(Exception e) {
						throw new RuntimeException("ERR: ERR:The String "+ s +" incorrect monom");
					}
					b=1;
				    break;
				}
				//Checking if  string's shape is "22x45" etc. or string's shape "x^" etc.
				if(s.charAt(i+1)!= '^' || i+1==s.length()-1)
					throw new RuntimeException("ERR:The String "+ s +" incorrect monom");
				try {
				a= Double.parseDouble(s.substring(0, i));
				b= Double.parseDouble(s.substring(i+2));
				}
				catch(Exception e ){
					throw new RuntimeException("ERR:The String "+ s +" incorrect monom");
				}
				//Checking if the power of the monom is not integer or negative.
				if(b!=(int)b || b<0)
					throw new RuntimeException("ERR:The String "+ s +" incorrect monom");
				break;
			}
		}
		if(!flag) {
			try {
			a= Double.parseDouble(s);
			}
		    catch(Exception e ){
		    	throw new RuntimeException("ERR:The String "+ s +" incorrect monom");
		    }
		}
		//the monom equal to 0.
		if(a==0) 
			b=0;
		this._coefficient=a;
		this._power=(int)b;
	}
	/**
	 * this method receive a monom and if the power of the monom equal to our monom, the method add the receive monom to our monom,
	 * else the method throw exception.
	 * @param m
	 */
	public void add(Monom m) {
		if(this.get_power()==m.get_power())
			this.set_coefficient(this.get_coefficient()+m.get_coefficient());
		else 
			throw new RuntimeException("ERR: add(m) should get a monom with the same power of  this monom: "+ this.toString());
	
	}
	/**
	 *this method receive a monom and multiply our monom in the receive monom.
	 * @param d
	 */
	public void multipy(Monom d) {
		this.set_coefficient(this.get_coefficient()*d.get_coefficient());
		this.set_power(this.get_power()+d.get_power());
		
		
		}
	
	public String toString() {
		if(this.get_coefficient()==0) return "0";	
		else if(this.get_power()==0) return ""+this.get_coefficient();
		else  if(this.get_power()==1) return this.get_coefficient()+"x";
		return this.get_coefficient()+"x^"+this.get_power();
	}
	
	public boolean equals(Monom m1) {
		if(this.get_coefficient()==0 && m1.get_coefficient()==0)
			return true;
		if(Math.abs(this.get_coefficient()-m1.get_coefficient())<EPSILON && this.get_power()==m1.get_power())
			return true;
		return false;
		
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	
	
}
