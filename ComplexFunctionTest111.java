package Ex1;

public class ComplexFunctionTest111 {
	public class TestStdDraw {
	      
	   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polynom p1 = new Polynom("x+x^2");
		Polynom p2 = new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction(Operation.Divid, p1, p2);
//		ComplexFunction cf2 = new ComplexFunction(Operation.Times, p1, null);
		ComplexFunction cf3 = new ComplexFunction(Operation.Comp, p1, p2);
		ComplexFunction cf4 = new ComplexFunction(Operation.Max, p1, p2);
		ComplexFunction cf5 = new ComplexFunction(Operation.Min, p1, p2);
		ComplexFunction cf6 = new ComplexFunction(Operation.Plus, p1, p2);
		ComplexFunction cf7 = new ComplexFunction(Operation.Error, p1, p2);
		ComplexFunction cf8 = new ComplexFunction("none",p1, null);
		System.out.println(cf8);
		function cf9 = cf8.initFromString("mul ( N o n e (x+3,n u l l )  ,  7   8 )");
//		cf5.mul(null);
		System.out.println(cf9);
//		System.out.println(cf2);
		function cf55 = cf6.initFromString("None(x+3,null)");
		System.out.println(cf55);
		System.out.println(cf3);
		System.out.println(cf4);
		System.out.println(cf5);
		System.out.println(cf6);
		System.out.println(cf7);
//		System.out.println(cf2.f(0));
//		System.out.println(cf8);
//		System.out.println(cf.getOp());
//		System.out.println(cf.f(0));
//		function cf2 = cf.copy();
	//	System.out.println(cf2);
//		System.out.println(0.0/0);
		
//		  StdDraw.setPenRadius(0.05);
//          StdDraw.setPenColor(StdDraw.BLUE);
//          StdDraw.point(0.5, 0.5);
//          StdDraw.setPenColor(StdDraw.MAGENTA);
//          StdDraw.line(0.2, 0.2, 0.8, 0.2);
	}

}
