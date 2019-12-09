package Ex1.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.function;

class PolynomTest1 {


	@Test
	void testIsZero() {
		Polynom p1=new Polynom("2x");
		assertFalse(p1.isZero());
		Polynom p2=new Polynom("0");
		assertTrue(p2.isZero());
		Polynom p3=new Polynom();
		assertFalse(p3.isZero());
	}
	
	@Test
	void testToString() {
		Polynom p1=new Polynom("2.7x^5+0.236x^3-23.7");
		Polynom_able p2=new Polynom(p1.toString());
		assertEquals(p1.toString(), p2.toString());
	}
	
	@Test
	void testEquals() {
		Polynom p1=new Polynom("2.7x^5+0.236x^3-23.7");
		Polynom_able p2=new Polynom(p1.toString());
		assertEquals(p1.toString(), p2.toString());
		assertTrue(p1.equals(p2));
	}
	
	@Test
	void testAddMonom() {
		Polynom p1=new Polynom("2.7x^5+0.236x^3-23.7");
		Monom m=new Monom("0.7");
		p1.add(m);
		Polynom p2=new Polynom("2.7x^5+0.236x^3-23.0");
		assertEquals(p2.toString(), p1.toString());
//		m.multipy(new Monom("-1"));
//		p1.add(m);
//		assertFalse(p1.equals(p2));
		
	}
	
	@Test
	void testAddPolynom() {
		Polynom p1=new Polynom("2.7x^5+0.236x^3-23.7");
		p1.add(p1);
		Polynom p2=new Polynom("5.4x^5+0.472x^3-47.4");
		assertEquals(p2.toString(), p1.toString());
	}
	
	@Test
	void testSubstract() {
		Polynom p1=new Polynom("2.7x^5+0.236x^3-23.7");
		Polynom p2=new Polynom("2.7x^5+0.236x^3");
		p1.substract(p2);
		assertEquals("-23.7", p1.toString());
	}
	
	@Test
	void testMultiplyMonom() {
		Polynom p1=new Polynom("2.7x^5+0.236x^3-23.7");
		Polynom p2=new Polynom("2.7x^5+0.236x^3-23.7");
		Polynom p3=new Polynom("1.35x^5+0.118x^3-11.85");
		Monom m1=new Monom("1");
		p1.multiply(m1);
		assertEquals(p2.toString(), p1.toString());
		Monom m2=new Monom("0");
		p1.multiply(m2);
		assertEquals("0", p1.toString());
		Monom m3=new Monom("0.5");
		p2.multiply(m3);
		assertEquals(p3.toString(), p2.toString());
	}
	
	@Test
	void testMultiplyPolynom() {
		Polynom p1=new Polynom("2.7x^5+0.236x^3-23.7");
		Polynom p2=new Polynom("2x^2");
		p1.multiply(p2);
		assertEquals("5.4x^7+0.472x^5-47.4x^2", p1.toString());
	}
	
	@Test
	void testArea() {
		Polynom p1 = new Polynom("x^2-4");
		assertEquals(21.33213334005095, p1.area(-4, 4, 0.0001));
		assertEquals(0, p1.area(-4, -4, 0.0001));
		assertEquals(26.99790005505048, p1.area(0, 5, 0.0001));
		Polynom p2 = new Polynom("-x+5");
		
	}
	
	@Test
	void testRoot() {
		
	}
	
	@Test
	void testCopy() {
		Polynom p1=new Polynom("2.7x^5+0.236x^3-23.7");
		function p2=p1.copy();
		assertEquals(p1.toString(), p2.toString());
	}
	
	@Test
	void testDerivative() {
		Polynom p1=new Polynom("2.7x^5+0.236x^3-23.7");
		Polynom p2=new Polynom("13.5x^4+0.708x^2");
		Polynom_able p3=p1.derivative();
		assertEquals(p2.toString(), p3.toString());
	}
	
	@Test
	void testF() {
		
	}
	
	@Test
	void testItertor() {
		
	}

}
