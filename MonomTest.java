package myMath.matala1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTest {
	
	@Test
	void toStringTest() {
		fail("Not yet implemented");
	}


	@Test
	void monomTest() {
		String[] goodMonoms = {"x^2", "2","x^3.", "x^3.0", "-x", "x^6467", "+3*X^+7", ".6",  "-3.2x^2","0"};
		String[] badMonoms = {"fkfsa45","","x^-4",  "3x^2.1", "fwqfxsaf"};
		for(int i=0;i<goodMonoms.length;i++) {
			Monom m = new Monom(goodMonoms[i]);
			String s = m.toString();
			Monom m1 = new Monom(s);
			assertEquals(m, m1);
		}
		
	}
	
	@Test
	void derivativeTest() {
		assertEquals(1,1);
	}
	
	@Test
	void fTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void isZeroTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void addTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void multipyTest() {
		fail("Not yet implemented");
	}

	@Test
	void equalsTest() {
		fail("Not yet implemented");
	}

	@Test
	void copyTest() {
		fail("Not yet implemented");
	}
	
	

}
