package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import javax.management.RuntimeErrorException;

import myMath.Monom;

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
	
	@Override
	public double f(double x) {
		double sum=0;
		for(Monom m:polynom)
		{
			sum+=m.f(x);
		}
		return sum;
	}

	@Override
	public void add(Polynom_able p1) {
		if(p1==null)
			throw new RuntimeException("ERR: The polynom is empty");
		Iterator<Monom> itr=p1.iteretor(); 
		while(itr.hasNext()) {
			this.add(new Monom(itr.next()));
		}
		
	}

	@Override
	public void add(Monom m1) {
		if(m1==null)
			throw new RuntimeException("ERR: The monom is empty");
		if(this.isZero()) {
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

	@Override
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

	@Override
	public void multiply(Polynom_able p1) {
		if(p1==null)
			throw new RuntimeException("ERR: The polynom is empty");
		Iterator<Monom> itr=p1.iteretor(); 
		Polynom_able temp1= new Polynom();
		while(itr.hasNext()) {
			Polynom_able temp= this.copy();
			temp.multiply(itr.next());
			temp1.add(temp);
		}
		this.polynom.clear();
		this.add(temp1);
	}

	@Override
	public boolean equals(Polynom_able p1) {
		if(p1==null)
			throw new RuntimeException("ERR: The polynom is empty");
		Iterator<Monom> itr1=p1.iteretor();
		Iterator<Monom> itr2= this.iteretor();
		while(itr1.hasNext()&& itr2.hasNext()) {
			if(!itr1.next().equals(itr2.next()))
				return false;
		}
		if((!itr1.hasNext() && itr2.hasNext()) || (!itr2.hasNext() && itr1.hasNext()))
           return false;
		return true;
	}

	@Override
	public boolean isZero() {
		if(this.polynom.size()!=0)
			return false;
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if(!(this.f(x0)*this.f(x1)<=0)) {
			throw new RuntimeException("ERR: in that points (f(x0)*f(x1))>0");
		}
		double pos, neg;
		if(this.f(x0)>0) {
			pos=x0;
			neg=x1;
		}else {
			pos=x1;
			neg=x0;
		}
		double mid=(pos+neg)/2;//x'
		while(Math.abs(this.f(mid))>=eps) {
			if(this.f(mid)>0) {
				pos=mid;
				mid=(pos+neg)/2;
			}else {
				neg=mid;
				mid=(pos+neg)/2;
			}
		}
		return mid;
	}

	@Override
	public Polynom_able copy() {
		Polynom_able p= new Polynom();
		for(Monom m:polynom)
		{
			p.add(new Monom(m));;
		}
		return p;
	}

	@Override
	public Polynom_able derivative() {
		Polynom_able p= new Polynom();
		for(Monom m:polynom)
		{
			p.add(m.derivative());
		}
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		double area = 0;
		double tmp=0;
		if(x0>x1) {
			throw new RuntimeException("ERR: the range of the integral is wrong");
		}
		for (double i = x0 + eps; i <=x1; i = i + eps) { // i=x0 or i=x0+eps??
			if (this.f(i) > 0) {
				tmp=(this.f(i - eps)+this.f(i))*eps/2;
				area+=tmp;
			}
		}
		return area;
	}

	@Override
	public Iterator<Monom> iteretor() {
		return this.polynom.iterator();
		
		
	}
	@Override
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
}
