package Ex1.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {
	
	@Test
	void testF() {
		Polynom p1 = new Polynom("x+x^2");
		Polynom p2 = new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction("plus", p1, p2);
		assertEquals(10, cf1.f(2));
		p1.add(p2);
		assertEquals(p1.f(2), cf1.f(2));
		p1.substract(p2);
		ComplexFunction cf2 = new ComplexFunction("mul", p1, p2);
		assertEquals(24, cf2.f(2));
		ComplexFunction cf3 = new ComplexFunction("div", p1, p2);
		assertEquals(1.5, cf3.f(2));
		ComplexFunction cf4 = new ComplexFunction("max", p1, p2);
		assertEquals(6, cf4.f(2));
		ComplexFunction cf5 = new ComplexFunction("min", p1, p2);
		assertEquals(4, cf5.f(2));
		ComplexFunction cf6 = new ComplexFunction("comp", p1, p2);
		assertEquals(20, cf6.f(2));
		function cf7 = cf1.copy();
		assertEquals(10, cf7.f(2));
	}
	
	@Test
	void testInitFromString() {
		Polynom p1 = new Polynom("x+x^2");
		Polynom p2 = new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction("mul",p1,p2);
		function cf2 = cf1.initFromString(cf1.toString());
		assertTrue(cf1.equals(cf2));
		assertEquals(cf1.toString(), cf2.toString());
		ComplexFunction cf3 = new ComplexFunction(Operation.None,p1,null);
		function cf4 = cf3.initFromString(cf3.toString());
		assertTrue(cf3.equals(cf4));
		assertEquals(cf3.toString(), cf4.toString());
		ComplexFunction cf5 = new ComplexFunction("das2342",p1,p2);
		function cf6 = cf5.initFromString(cf5.toString());
		assertEquals(cf5.toString(), cf6.toString());
	}
	
	@Test
	void testToString() {
		Polynom p1 = new Polynom("0.123x^8+x^2-3");
		Polynom p2 = new Polynom("65x^3-2");
		ComplexFunction cf1 = new ComplexFunction("mul",p1,p2);
		function cf2 = cf1.initFromString(cf1.toString());
		assertEquals(cf1.toString(), cf2.toString());
	}

	@Test
	void testEquals() {
		Polynom p1 = new Polynom("x+x^2");
		Polynom p2 = new Polynom("x^2");
		Polynom p3 = new Polynom("x-1");
		ComplexFunction cf1 = new ComplexFunction("mul",p1,p2);
		ComplexFunction cf3 = new ComplexFunction("None",p1,null);
		cf3.mul(p2);
		assertTrue(cf1.equals(cf3));
		function cf2 = cf1.initFromString(cf1.toString());
		assertTrue(cf1.equals(cf2));
		p1.multiply(p2);
		assertTrue(cf1.equals(p1));
		ComplexFunction cf4 = new ComplexFunction("div",p1,p3);
		cf1.div(p3);
		assertTrue(cf1.equals(cf4));
		Monom m = new Monom("x^2");
		ComplexFunction cf5 = new ComplexFunction("None",p2,null);
		assertTrue(cf5.equals(m));
	}
	
	@Test
	void testCopy() {
		Polynom p1 = new Polynom("x+x^2");
		Polynom p2 = new Polynom("x^2");
		ComplexFunction cf1 = new ComplexFunction("mul",p1,p2);
		function cf2 = cf1.copy();
		assertEquals(cf1, cf2);
		assertTrue(cf1.equals(cf2));
	}
	
	@Test
	void testPlus() {
		Monom m = new Monom("x^2");
		Polynom p = new Polynom("x^2+5x");
		ComplexFunction cf = new ComplexFunction(p);
		ComplexFunction cf2 = new ComplexFunction(Operation.Plus,p,m);
		cf.plus(m);
		p.add(m);
		for(double i=1; i<=10; i=i+0.5) {
			assertEquals(p.f(i), cf.f(i));
		}
		assertTrue(cf.equals(cf2));
		assertEquals(cf2.toString(), cf.toString());
	}
	
	@Test
	void testMul() {
		Monom m = new Monom("x^2");
		Polynom p = new Polynom("x^2+5x");
		ComplexFunction cf = new ComplexFunction(p);
		ComplexFunction cf2 = new ComplexFunction(Operation.Times,p,m);
		cf.mul(m);
		p.multiply(m);
		for(double i=1; i<=10; i=i+0.5) {
			assertEquals(p.f(i), cf.f(i));
		}
		assertTrue(cf.equals(cf2));
		assertEquals(cf2.toString(), cf.toString());
	}
	
	@Test
	void testDiv() {
		Monom m = new Monom("x^2");
		Polynom p = new Polynom("x^2+5x");
		ComplexFunction cf = new ComplexFunction(p);
		cf.div(m);
		double p_result;
		double m_result;
		double p_div_m;
		for(double i=1; i<=10; i=i+0.5) {
			p_result=p.f(i);
			m_result=m.f(i);
			p_div_m=p_result/m_result;
			assertEquals(p_div_m, cf.f(i));
		}
		ComplexFunction cf2 = new ComplexFunction(Operation.Divid,p,m);
		assertTrue(cf.equals(cf2));
		assertEquals(cf2.toString(), cf.toString());
	}
	
	@Test
	void testMax() {
		Monom m = new Monom("x^2");
		Polynom p = new Polynom("x^2+5x");
		ComplexFunction cf = new ComplexFunction(p);
		ComplexFunction cf2 = new ComplexFunction(Operation.Max,p,m);
		cf.max(m);
		double p_max_m;
		for(double i=1; i<=10; i=i+0.5) {
			p_max_m = Math.max(p.f(i), m.f(i));
			assertEquals(p_max_m, cf.f(i));
		}
		assertTrue(cf.equals(cf2));
		assertEquals(cf2.toString(), cf.toString());
	}
	
	@Test
	void testMin() {
		Monom m = new Monom("x^2");
		Polynom p = new Polynom("x^2+5x");
		ComplexFunction cf = new ComplexFunction(p);
		ComplexFunction cf2 = new ComplexFunction(Operation.Min,p,m);
		cf.min(m);
		double p_min_m;
		for(double i=1; i<=10; i=i+0.5) {
			p_min_m = Math.min(p.f(i), m.f(i));
			assertEquals(p_min_m, cf.f(i));
		}
		assertTrue(cf.equals(cf2));
		assertEquals(cf2.toString(), cf.toString());
	}
	
	@Test
	void testComp() {
		Monom m = new Monom("x^2");
		Polynom p = new Polynom("x^2+5x");
		ComplexFunction cf = new ComplexFunction(p);
		ComplexFunction cf2 = new ComplexFunction(Operation.Comp,p,m);
		cf.comp(m);
		double p_comp_m;
		for(double i=1; i<=10; i=i+0.5) {
			p_comp_m = p.f( m.f(i));
			assertEquals(p_comp_m, cf.f(i));
		}
		assertTrue(cf.equals(cf2));
		assertEquals(cf2.toString(), cf.toString());
	}
	
	
	@Test
	void testLeft() {
		Monom m = new Monom("x^2");
		Polynom p = new Polynom("x^2+5x");
		ComplexFunction cf = new ComplexFunction(p);
		ComplexFunction cf2 = new ComplexFunction(Operation.Comp,p,m);
		assertTrue(p.equals(cf.left()));
		assertTrue(p.equals(cf2.left()));
	}
	
	@Test
	void testRight() {
		Monom m = new Monom("x^2");
		Polynom p = new Polynom("x^2+5x");
		ComplexFunction cf2 = new ComplexFunction(Operation.Comp,p,m);
		assertTrue(m.equals(cf2.right()));
	}
	
	@Test
	void testGetOp() {
		Monom m = new Monom("x^2");
		Polynom p = new Polynom("x^2+5x");
		Operation[] op = {Operation.Plus, Operation.Times, Operation.Divid, Operation.Max, Operation.Min, Operation.Comp , Operation.None, Operation.Error};
		ComplexFunction cf;
		for(int i=0;i<op.length;i++) {
			if(op[i]==Operation.None) {
				cf = new ComplexFunction(op[i],p,null);
			}else {
				cf = new ComplexFunction(op[i],p,m);
			}
			assertEquals(op[i], cf.getOp());
		}
	}
}
