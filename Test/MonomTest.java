package Ex1.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;

class MonomTest {
	
	@Test
	void toStringTest() {
		Monom m=new Monom(0,5);
		String s= m.toString();
		assertEquals("0", s);
		m=new Monom(-1,0);
		s= m.toString();
		assertEquals("-1.0", s);
		m=new Monom(-1.3,1);
		s= m.toString();
		assertEquals("-1.3x", s);
		m=new Monom(-2.2,2);
	    s= m.toString();
		assertEquals("-2.2x^2", s);
		
	}


	@Test
	void monomTest() {
		String[] goodMonoms = {"x^2", "2","x^3.", "x^3.0", "-x", "x^6467", "+3*X^+7", ".6",  "-3.2x^2","0"};
		String[] badMonoms = {"fkfsa45","","x^-4",  "3x^2.1", "fwqfxsaf"};
		Monom m,m1;
		for(int i=0;i<goodMonoms.length;i++) {
		    m = new Monom(goodMonoms[i]);
			String s = m.toString();
			m1 = new Monom(s);
			assertEquals(m, m1);
		}
			boolean fl=true;
			try {
				for(int i=0;i<badMonoms.length;i++) {
					m= new Monom(badMonoms[i]);
				}
			}
			catch(Exception e) {
				fl=false;
			}
			assertFalse(fl);
	}
	
	@Test
	void derivativeTest() {
		Monom m=new Monom("9");
		m=m.derivative();
		assertEquals(new Monom("0.0"), m);
		m=new Monom("7x^3");
		m=m.derivative();
		assertEquals(new Monom("21.0x^2"), m);
		m=new Monom("-2.5x^2");
		m=m.derivative();
		assertEquals(new Monom("-5x"), m);
	}
	
	@Test
	void fTest() {
		Monom m=new Monom("9");
		assertEquals(9, m.f(1));
		m=new Monom("7x^3");
		assertEquals(0, m.f(0));
		m=new Monom("-2.5x^2");
		assertEquals(-10, m.f(2));
	}
	
	@Test
	void isZeroTest() {
		Monom m=new Monom("0");
		assertEquals(Monom.ZERO, m);
		m=new Monom("0x^3");
		assertEquals(Monom.ZERO, m);
		m=new Monom("-0x^2");
		assertEquals(Monom.ZERO, m);
	}
	
	@Test
	void addTest() {
		Monom m1=new Monom("0");
		Monom m2=new Monom("4x^8");
		m1.add(m2);
		assertEquals(new Monom("4x^8"), m1);
		m1.add(Monom.ZERO);
		assertEquals(new Monom("4x^8"), m1);
		m1=new Monom("-4x^2");
		m2=new Monom("2x^2");
		m1.add(m2);
		assertEquals(new Monom("-2x^2"), m1);
		boolean fl=true;
		try {
			m1=new Monom("-4x^6");
			m2=new Monom("2x^2");
			m1.add(m2);
		}
		catch(Exception e) {
			fl=false;
		}
		assertFalse(fl);
		fl=true;
		try {
			m1=new Monom("-4x^6");
			m2=new Monom("2");
			m1.add(m2);
		}
		catch(Exception e) {
			fl=false;
		}
		assertFalse(fl);
	}
	
	@Test
	void multipyTest() {
		Monom m1=new Monom("0");
		Monom m2=new Monom("4x^8");
		m1.multipy(m2);
		assertEquals(Monom.ZERO, m1);
		m1=new Monom("-4x^2");
		m2=new Monom("2x^3");
		m1.multipy(m2);
		assertEquals(new Monom(-4*2,2+3), m1);
		m1=new Monom("-4x^0");
		m2=new Monom("2x^3");
		m1.multipy(m2);
		assertEquals(new Monom(-4*2,0+3), m1);
	}

	@Test
	void equalsTest() {
		Monom m1=new Monom("0");
		Monom m2=new Monom(0,5);
		assertEquals(m2, m1);
		m1=new Monom("-4.6x^2");
		m2=new Monom(-4.6,2);
		assertEquals(m2, m1);
	}

	@Test
	void copyTest() {
		Monom m1=new Monom("0");
		Monom m2=(Monom)m1.copy();
		Monom m3=new Monom("5.3x");
		m2.add(m3);
		assertNotEquals(m2, m1);
		m1=new Monom("-4.6x^2");
		m2=(Monom)m1.copy();
		m3=new Monom("5.3x^2");
		m2.add(m3);
		assertNotEquals(m2, m1);
	}
	
	

}
