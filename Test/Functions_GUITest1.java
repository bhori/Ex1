package Ex1.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import org.junit.jupiter.api.Test;

class Functions_GUITest1 {

	@Test
	void testDrawFunctionsIntIntRangeRangeInt() {
		//_data.drawFunctions();
		Functions_GUI f = new Functions_GUI();
		f.add(new Polynom ("2x+6"));
		f.add(new Polynom ("2x^2"));
		f.drawFunctions(400, 300, new Range(-10,10),new Range(-5,15), 200);
		assertNotEquals(f.get(0),f.get(1));
	}

	    @Test
		void testFunctions_GUI() {
		//	fail("Not yet implemented");
		}

		@Test
		void testInitFromFile() {
		//	fail("Not yet implemented");
		}

		@Test
		void testSaveToFile() {
		//	fail("Not yet implemented");
		}

		@Test
		void testDrawFunctions() {
			//_data.drawFunctions();
		//	fail("Not yet implemented");
		}

}
