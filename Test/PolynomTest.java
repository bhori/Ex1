package Ex1.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

public class PolynomTest {
	public static void main(String[] args) {
		test1();
//		test2();
//		test3();
//		test4();
//		test5();
	}

	public static void test1() {
//		System.out.println("*****  Test1:  *****");
//		Polynom p1 = new Polynom();
//		String[] monoms = {"1","x","x^2", "0.5x^2"};
//		for(int i=0;i<monoms.length;i++) {
//		 Monom m = new Monom(monoms[i]);
//		 p1.add(m);
//		 double aa = p1.area(0, 1, 0.0001);
//		 System.out.println("polynom: "+p1+" area(x0=0,x1=1,epsilon=0.0001)= "+ aa);
//		}
//		System.out.println("(polynom)': "+p1.derivative());
//		p1.substract(p1.derivative());
//		System.out.println("polynom-(polynom)': "+p1);
//		p1.substract(p1);
//		 System.out.println("(polynom)'-(polynom)': "+p1);
		Polynom p=new Polynom("0+0+0+0+1");
		System.out.println(p.isZero());
	}
	public static void test2() {
		System.out.println("*****  Test2:  *****");
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2","-3.2x^2","4","-1.5x^2", "-x"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		Polynom_able pp1 = new Polynom(s1);
		System.out.println("from string: "+pp1);
		System.out.println("((p1+p2)*p2)': "+ pp1.derivative());
	}
	private static void test3() {
		System.out.println("*****  Test3:  *****");
		double x;
		String []good= {"x", "3+1.4X^3-34x","1", "0","0+0+0+0", "5*x^17-6 ","1+2+3", "-4+x^2"};
		String []bad= {"2x+4x^8+", "x^)2","(3+1.4X^3)(-34x", "(2x^2-4)*+(-1.2x-7.1)", "(3-3.4x+1)*()((3.1x-1.2)-(3X^2-3.1))"};
		for(int i=0; i<good.length; i++) {
			System.out.println( "polynom: "+new Polynom(good[i]));
			x=i+i*0.1;
			System.out.println("f("+x+"): "+new Polynom(good[i]).f(x));
		}
		for(int i=0; i<bad.length; i++)
			try {
			System.out.println(new Polynom(bad[i]));
		}
		catch(Exception e) {
			System.out.println("ERR:The String "+ bad[i]+" incorrect polynom");
		}
		Polynom p= new Polynom(good[0]);
		String s1=""+p+"+0.00000000001x";
		Polynom pE=new Polynom(s1);
		if(pE.equals(p))
			System.out.println(true);
		else System.out.println(false);
		
	}
	
	private static void test4() {
		Polynom p1 = new Polynom("x^2-4");
		System.out.println("***area test***");
		System.out.println("p1="+p1);
		System.out.println("p1: area in range -4,4: "+p1.area(-4, 4, 0.0001));
		System.out.println("p1: area in range -4,-4: "+p1.area(-4, -4, 0.0001));
		System.out.println("p1: area in range -2,2: "+p1.area(-2, 2, 0.0001));
		System.out.println("p1: area in range 0,5: "+p1.area(0, 5, 0.0001));
		System.out.println("p1: area in range 5,0: "+p1.area(5, 0, 0.0001));
		System.out.println("******************");
		Polynom p2 = new Polynom("-x+5");
		System.out.println("p2="+p2);
		System.out.println("p2: area in range -3.1,0: "+p2.area(-3.1, 0, 0.0001));
		System.out.println("p2: area in range 1.4,1.8: "+p2.area(1.4, 1.8, 0.0001));
		System.out.println("p2: area in range 0.5,3.4: "+p2.area(0.5, 3.4, 0.0001));
		System.out.println("p2: area in range 0,5.5: "+p2.area(0, 5.5, 0.0001));
		System.out.println("p2: area in range 0,0: "+p2.area(0, 0, 0.0001));
		System.out.println("p2: area in range 1.2,-45218.3256: "+p2.area(1.2, -45218.3256, 0.0001));
		System.out.println();
		System.out.println("*** root test***");
		System.out.println("p1="+p1);
		System.out.println("p1: root in range -3.4,-1.35: "+p1.root(-3.4, -1.35, 0.0001));
		System.out.println("p1: root in range 0,2: "+p1.root(0, 2, 0.0001));
		System.out.println("p1: root in range -0.7,17.9: "+p1.root(-0.7, 17.9, 0.0001));
		try {
			System.out.println("p1: root in range -6,137.5: "+p1.root(-6, 137.5, 0.0001));
		} catch (Exception e) {
			System.out.println("ERR: f(-6)*f(137.5)>0 ");
		}
		System.out.println("******************");
		System.out.println("p2="+p2);
		System.out.println("p2: root in range -779.1,64.7: "+p2.root(-779.1, 64.7, 0.0001));
		System.out.println("p2: root in range 1.52,5.001: "+p2.root(1.52, 5.001, 0.0001));
		try {
			System.out.println("p2: root in range -13,0.3: "+p2.root(-13, 0.3, 0.0001));
		} catch (Exception e) {
			System.out.println("ERR: f(-13)*f(0.3)>0 ");
		}
		
	}
	
	private static void test5() {
		Polynom p1=new Polynom("2.7x^5+0.236x^3-23.7");
		Polynom_able p2=new Polynom(p1.toString());
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p1==p2);
	}
	
	
}
