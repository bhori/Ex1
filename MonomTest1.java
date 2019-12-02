package myMath.matala1;
import java.util.ArrayList;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
*****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest1 {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}
	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] goodMonoms = {"x^2", "2","x^3.", "x^3.0", "-x", "x^6467", "+3*X^+7", ".6",  "-3.2x^2","0"};
		String[] badMonoms = {"fkfsa45","","x^-4",  "3x^2.1", "fwqfxsaf"};
		for(int i=0;i<goodMonoms.length;i++) {
			Monom m = new Monom(goodMonoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
		for(int i=0;i<badMonoms.length;i++) {
			try {
			Monom m = new Monom(badMonoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
			}
			catch(Exception e) {
				System.out.println("ERR:monom: "+badMonoms[i]+ " not realy monom" );
			}
		}
	}
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		try {
		monoms.add(new Monom(-2.2,-2));
		}
		catch(Exception e) {
			System.out.println("ERR: -2.2x^-2 not realy monom");
		}
		
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
	}
	private static void test3() {
		System.out.println("*****  Test3:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.get(2).multipy(monoms.get(1).derivative());
		System.out.println("should be 0: "+monoms.get(2));
	}
}
