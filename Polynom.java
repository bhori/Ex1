package myMath.matala1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import javax.management.RuntimeErrorException;

import myMath.matala1.Monom;
//StringBuilder sb = new StringBuilder();
//
//sb.append("first name");
//sb.append(",");
//sb.append("last name");
//sb.append("\n");
//sb.append("Israel");
//sb.append(",");
//sb.append("Israeli");
//sb.append("\n");
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
    private ArrayList<Monom> polynom;
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		polynom= new ArrayList<Monom>();
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		this();
		if(s==null || s.length()==0)
			throw new RuntimeException("ERR:The String is empty or null");
		//checking if the string is one monom with one char like "x".
		else if( s.length()==1) {
			try {
			this.add(new Monom(s));
			}
			catch(Exception e) {
				throw new RuntimeException("ERR:The String "+ s+" incorrect polynom");
			}
			return;
			}
		int i,j=0;
		for( i=1; i<s.length(); i++) {
			if(s.charAt(i)=='+' || s.charAt(i)=='-') {
				try {
				Monom m= new Monom(s.substring(j, i));
				this.add(m);
				j=i;
				}
				catch (Exception e) {
					throw new RuntimeException("ERR:The String "+ s+" incorrect polynom");
				}
			}
		}
		try {
			//to add the last monom in the string to the polynom.
			Monom m= new Monom(s.substring(j));
			this.add(m);
			}
			catch (Exception e) {
				throw new RuntimeException("ERR:The String "+ s+" incorrect polynom");
			}
				
			
		
	}
	
	/**
	 * this method returns the value of the Polynom at point x  
	 */
	public double f(double x) {
		double sum=0;
		for(Monom m:polynom)
		{
			sum+=m.f(x);
		}
		return sum;
	}

	/**
	 * Add p1 to this Polynom
	 * @param p1
	 */
	public void add(Polynom_able p1) {
		if(p1==null)
			throw new RuntimeException("ERR: The polynom is empty");
		Iterator<Monom> itr=p1.iteretor(); 
		while(itr.hasNext()) {
			this.add(new Monom(itr.next()));
		}
		
	}

	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 */
	public void add(Monom m1) {
		if(m1==null)
			throw new RuntimeException("ERR: The monom is empty");
		if(this.polynom.size()==0) {
			polynom.add(m1);
			return;
		}
		for(int i=0; i<polynom.size(); i++) {
			if(polynom.get(i).get_power()==m1.get_power()) {
				Monom m=new Monom(polynom.get(i).get_coefficient()+m1.get_coefficient(), m1.get_power());
				//if the monom now equal to 0 and have more then 1 monom in the polynom so throw the monom
				if(m.isZero() && polynom.size()>1 ) {
					polynom.remove(i);
					return;
				}
				else {
				    polynom.set(i, m);
				    return;
				}
			}	
		}
		polynom.add(m1);
		Monom_Comperator c= new Monom_Comperator(); 
		polynom.sort(c);
		
	}

	/**
	 * Subtract p1 from this Polynom
	 * @param p1
	 */
	public void substract(Polynom_able p1) {
		if(p1==null)
			throw new RuntimeException("ERR: The polynom is empty");
		if(this.equals(p1)) {
			this.polynom.clear();
		    this.add(new Monom("0"));
		    return;
		}
		Monom m=new Monom("-1");
		p1.multiply(m);
		this.add(p1);
		
	}

	/**
	 * Multiply this Polynom by p1
	 * @param p1
	 */
	public void multiply(Polynom_able p1) {
		if(p1==null)
			throw new RuntimeException("ERR: The polynom is empty");
		Iterator<Monom> itr=p1.iteretor(); 
		Polynom_able temp1= new Polynom();
		while(itr.hasNext()) {
			function temp= this.copy();
			Polynom_able tempp=(Polynom)temp;
			tempp.multiply(itr.next());
			temp1.add(tempp);
		}
		this.polynom.clear();
		this.add(temp1);
	}

	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1
	 * @return true iff this polynom represents the same function as p1
	 */
	public boolean equals(Object  p1) {
		if(p1==null)
			throw new RuntimeException("ERR: The polynom is empty");
		
		if(p1 instanceof Polynom ) {
			Polynom p= (Polynom)p1;
		    Iterator<Monom> itr1=p.iteretor();
		    Iterator<Monom> itr2= this.iteretor();
		    while(itr1.hasNext()&& itr2.hasNext()) {
			   if(!itr1.next().equals(itr2.next()))
				  return false;
		    }
		    if((!itr1.hasNext() && itr2.hasNext()) || (!itr2.hasNext() && itr1.hasNext()))
               return false;
		    return true;
		 }
		if(p1 instanceof Monom) {
			if(this.polynom.size()!= 1)
				return false;
			return(this.polynom.get(0).equals(p1));
		}
		return false;
			
			
	}

	/**
	 * Test if this is the Zero Polynom
	 */
	public boolean isZero() {
		return (this.equals(Monom.ZERO));
	}

	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, else should throws runtimeException 
	 * computes f(x') such that:
	 * 	(i) x0<=x'<=x1 && 
	 * 	(ii) |f(x')|<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * @return an approximated value (root) for this (cont.) function 
	 */
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		if(x0>x1) {
			throw new RuntimeException("ERR: the range is wrong, x0>x1!");
		}
		if (!(this.f(x0) * this.f(x1) <= 0)) {
			throw new RuntimeException("ERR: in that points (f(x0)*f(x1))>0");
		}
		if(this.f(x0)==0) {
			return x0;
		}
		if(this.f(x1)==0) {
			return x1;
		}

		double mid = (x0 + x1) / 2;// mid = x'
		while (Math.abs(this.f(mid)) >= eps) {
			if(this.f(mid)*this.f(x0)<0) {
				x1=mid;
				mid=(x0+x1)/2;
			}else {
				x0=mid;
				mid=(mid+x1)/2;
			}
		}
		return mid;
	}

	/**
	 * create a deep copy of this Polynom
	 */
	public function copy() {
		Polynom p= new Polynom();
		for(Monom m:polynom)
		{
			p.add(new Monom(m));
		}
		function f=p;
		return f;
	}

	/**
	 * Compute and returns a new Polynom which is the derivative of this Polynom
	 */
	public Polynom_able derivative() {
		Polynom_able p= new Polynom();
		for(Monom m:polynom)
		{
			p.add(m.derivative());
		}
		return p;
	}

	/**
	 * Compute a Riman's integral from x0 to x1 in eps steps. 
	 * @param x0 starting pooint
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the approximated area above X-axis below this function bounded in the range of [x0,x1]
	 */
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double area = 0;
		double tmp=0;
		if((x0>=x1) || (Math.abs(x1-x0)<eps)){
			return area; 
		}
		for (double i = x0 + eps; i <=x1; i = i + eps) { 
			if (this.f(i) > 0) {
				tmp=(this.f(i - eps)+this.f(i))*eps/2;
				area+=tmp;
			}
		}
		return area;
	}

	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 */
	public Iterator<Monom> iteretor() {
		return this.polynom.iterator();
		
		
	}
	/**
	 * Multiply this Polynom by Monom m1
	 * @param m1
	 */
	public void multiply(Monom m1) {
		if(m1.get_coefficient()==0) {
			polynom.clear();
			Monom m= new Monom(0,0);
			polynom.add(m);
			return;
		}
		for(Monom m:polynom)
		{
			m.multipy(m1);;
		}
		
	}
	/**
	 * @return a String that represents the polynom 
	 */
	public String toString() {
		String s="";
		String sMon="";
		for(int i=0; i<this.polynom.size(); i++) {
			//if the monom  equal to 0 and have more then 1 monom in the polynom so dont put this monom in the string
			if(!this.polynom.get(i).isZero() || this.polynom.size()==1) {
			   sMon=""+ this.polynom.get(i);
			   if(sMon.charAt(0)!='+'&& sMon.charAt(0)!='-' )
					sMon='+'+sMon;
			   s=s+ sMon;
			}
		}
		if(s.charAt(0)=='+')
			s=s.substring(1);
		return s;
		
	}
	@Override
	public function initFromString(String s) {
		return new Polynom(s);
	}
}
