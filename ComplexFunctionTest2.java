package Ex1;

public class ComplexFunctionTest2 {
	
//	public static Functions_GUI FunctionsFactory() {
//		Functions_GUI ans = new Functions_GUI();
//		String s1 = "3.1 +2.4x^2 -x^4";
//		String s2 = "5 +2x -3.3x +0.1x^5";
//		String[] s3 = {"x +3","x -2", "x -4"};
//		Polynom p1 = new Polynom(s1);
//		Polynom p2 = new Polynom(s2);
//		Polynom p3 = new Polynom(s3[0]);
//		ComplexFunction cf3 = new ComplexFunction(p3);
//		for(int i=1;i<s3.length;i++) {
//			cf3.mul(new Polynom(s3[i]));
//		}
//		
//		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
//		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
//		cf4.plus(new Monom("2"));
//		ans.add(cf.copy());
//		ans.add(cf4.copy());
//		cf.div(p1);
//		ans.add(cf.copy());
//		String s = cf.toString();
//		function cf5 = cf4.initFromString(s1);
//		function cf6 = cf4.initFromString(s2);
//		ans.add(cf5.copy());
//		ans.add(cf6.copy());
//		ComplexFunction max = new ComplexFunction(ans.get(0).copy());
//		ComplexFunction min = new ComplexFunction(ans.get(0).copy());
//		for(int i=1;i<ans.size();i++) {
//			max.max(ans.get(i));
//			min.min(ans.get(i));
//		}
//		ans.add(max);
//		ans.add(min);
//		
//		return ans;
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polynom p1 = new Polynom("2  ");
		Polynom p2 = new Polynom("x^2  ");
		Polynom p3 = new Polynom("x^2+2");
		ComplexFunction cf = new ComplexFunction("plus",p1,p2);
		cf.mul(p1);
		System.out.println(cf);
		p1.add(new Monom("x^2"));
		System.out.println(cf);
		System.out.println(cf.equals(p3));
//		ComplexFunction cf10 = new ComplexFunction(null,p2,null);
//		System.out.println(cf10);
//		System.out.println(cf10.equals(p2));
//		Range x= new Range(-10, 10);
//		Range y = new Range(-10, 10);
//		Functions_GUI f = new Functions_GUI();
//		f.add(cf);
//		f.drawFunctions(5, 5, x, y, 1000);
		System.out.println(cf);
		ComplexFunction cf1 = new ComplexFunction("mul",p1,p2);
		System.out.println(cf1);
		ComplexFunction cf2 = new ComplexFunction("plus",cf,p2);
		System.out.println(cf2);
		function c = cf2.initFromString(cf1.toString());
		System.out.println(c);
		ComplexFunction cf3 = new ComplexFunction(Operation.Divid, c, p2);
		System.out.println(cf3);
		function c2 = cf3.initFromString("Divid(Times(2.0,1.0x^2),1.0x^2)");
//		cf3.plus(p2);
		System.out.println(cf3);
		System.out.println(c2);
		System.out.println(c2.equals(cf3));
		function c3 = new ComplexFunction("mul",c2,c2);
		System.out.println(c3);
		System.out.println(c3.f(-10));
		
//		Polynom p1 = new Polynom("2  ");
//		Polynom p2 = new Polynom("x^2  ");
//		ComplexFunction cf3 = new ComplexFunction(Operation.Divid, p1, p2);
//		function c2 = cf3.initFromString("Divid(Times(2.0,1.0x^2),1.0x^2)");
//		System.out.println(c2);
	}

}
