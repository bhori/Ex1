package myMath.matala1;

public class ComplexFunctionTest111 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polynom p1 = new Polynom("x+x^2");
		Polynom p2 = new Polynom("x^2");
		ComplexFunction cf = new ComplexFunction("div", p1, p2);
		System.out.println(cf);
//		System.out.println(cf.getOp());
		System.out.println(cf.f(0));
		function cf2 = cf.copy();
	//	System.out.println(cf2);
//		System.out.println(0.0/0);
	}

}
