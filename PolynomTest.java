package myMath;

public class PolynomTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}

	public static void test1() {
		System.out.println("*****  Test1:  *****");
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) {
		 Monom m = new Monom(monoms[i]);
		 p1.add(m);
		 double aa = p1.area(0, 1, 0.0001);
		 System.out.println("polynom: "+p1+" area(x0=0,x1=1,epsilon=0.0001)= "+ aa);
		}
		System.out.println("(polynom)': "+p1.derivative());
		p1.substract(p1.derivative());
		System.out.println("polynom-(polynom)': "+p1);
		p1.substract(p1);
		 System.out.println("(polynom)'-(polynom)': "+p1);
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
}
