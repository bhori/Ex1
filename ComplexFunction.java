package Ex1;

public class ComplexFunction implements complex_function {
	private function left;
	private function right;
	private Operation op;

	// constructors
	public ComplexFunction(function f1) {
		if (f1==null) {
			throw new RuntimeException("ERR: ComplexFunction cannot be null, it should have left function!");
		}
		if (f1 instanceof ComplexFunction) {
			ComplexFunction cf = (ComplexFunction) f1;
			this.op = cf.getOp();
			this.left = cf.left().copy();
			if (cf.right() == null) {
				this.right = null;
			} else {
				this.right = cf.right().copy();
			}
		} else {
			this.op = Operation.None;
			this.left = f1.copy();
			this.right = null;
		}
	}

	public ComplexFunction(Operation op, function f1, function f2) {
		if(f1==null) {
			throw new RuntimeException("ERR: left function in ComplexFunction cannot be null!");
		}
		if((op!=Operation.None) && (op!=Operation.Error) && (f2==null)) {
			throw new RuntimeException("ERR: this Complexfunction is incorrect complex function.");
		}
		if((op==Operation.None)&&(f2!=null)) {
			throw new RuntimeException("ERR: this Complexfunction is incorrect complex function.");
		}
		this.op = op;
		this.left = f1.copy();
		if (f2 == null) {
			this.right = null;
		} else {
			this.right = f2.copy();
		}
	}

	public ComplexFunction(String s, function f1, function f2) {
		if(f1==null) {
			throw new RuntimeException("ERR: left function in ComplexFunction cannot be null!");
		}
		s = s.toLowerCase();
		if ((s == null) || (s.equals("none"))) {
			if(f2!=null) {
				throw new RuntimeException("ERR: this Complexfunction is incorrect complex function.");
			}else {
				this.op = Operation.None;
			}
		} else if (s.equals("mul")|| s.equals("times")) {
			this.op = Operation.Times;
		} else if (s.equals("div") || s.equals("divid")) {
			this.op = Operation.Divid;
		} else if (s.equals("max")) {
			this.op = Operation.Max;
		} else if (s.equals("min")) {
			this.op = Operation.Min;
		} else if (s.equals("comp")) {
			this.op = Operation.Comp;
		} else if (s.equals("plus")) {
			this.op = Operation.Plus;
		} else {
			this.op = Operation.Error;
		}
		if((this.op!=Operation.None) && (this.op!=Operation.Error) && (f2==null)) {
			throw new RuntimeException("ERR: this Complexfunction is incorrect complex function.");
		}
		this.left = f1.copy();
		if (f2 == null) {
			this.right = null;
		} else {
			this.right = f2.copy();
		}
	}

	// methods
	
	/**
	 * this method returns the value of the ComplexFunction at point x  
	 */
	public double f(double x) {
		double left = 0, right = 0, result = 0;
		if (this.op == Operation.Error) {
			throw new RuntimeException("there is an unknown Operation, cannot calculate the result.");
		}
		if (this.op == Operation.None) {
			result = this.left.f(x);
		} else if (this.op == Operation.Comp) {
			result = this.left.f(this.right.f(x));
		} else {
			left = this.left.f(x);
			right = this.right.f(x);
			if (this.op == Operation.Plus) {
				result = left + right;
			} else if (this.op == Operation.Times) {
				result = left * right;
			} else if (this.op == Operation.Divid) {
				if (right == 0) {
					throw new RuntimeException("cannot divide by zero, the function is not set for x = " + x);
				} else {
					result = left / right;
				}
			} else if (this.op == Operation.Max) {
				result = Math.max(left, right);
			} else if (this.op == Operation.Min) {
				result = Math.min(left, right);
			}
		}
		return result;
	}

	/**
	 * init and returns a ComplexFunction from a String such as:
	 *  {"plus(x^4+12.2x^2-5,23.4)", "max(min(3+1.4X^3-34x,2x^8),10.08x^4)"};
	 * @param s: is a string represents a ComplexFunction
	 * @return a new ComplexFunction represented by the string s
	 */
	public function initFromString(String s) {
		if(s==null) {
			return null;
		}
		s=s.replace(" ", "");
		if (s.indexOf('(') == -1) {
			if (s.equals("null")) {
				return null;
			} else {
				try {
					function cf = new Polynom(s);
					return cf;
				} catch (Exception e) {
					throw new RuntimeException("ERR: this String is incorrect complex function.");
				}
			}
		} else {
			int count = 1;
			int i;
			for (i = s.indexOf('(') + 1; i < s.length(); i++) {
				if (s.charAt(i) == '(') {
					count++;
				} else if (s.charAt(i) == ',') {
					count--;
				}
				if (count == 0) {
					break;
				}
			}
			if (count!=0) {
				throw new RuntimeException("ERR:The String "+ s +" is incorrect complex function.");
			}
			try {
				if(s.charAt(s.length()-1)!=')') {
					throw new RuntimeException("ERR: this String is incorrect complex function.");
				}
				String left = s.substring(s.indexOf('(') + 1, i);
				String right = s.substring(i + 1, s.length() - 1);
				function leftFunc = initFromString(left);
				function rightFunc = initFromString(right);
				String operation = s.substring(0, s.indexOf('('));
				function cf = new ComplexFunction(operation, leftFunc, rightFunc);
				return cf;
			} catch (Exception e) {
				throw new RuntimeException("ERR:The String "+ s +" is incorrect complex function.");
			}
		}
	}

	/** 
	 * return a String representing this complex function
	 */
	public String toString() {
		if (this.getOp() == Operation.None) {
			if (this.right == null) {
				return "" + this.left();
			}
		}
		return "" + this.getOp() + "(" + this.left() + "," + this.right() + ")";
	}
	
	/**
	 * create a deep copy of this ComplexFunction
	 */
	public function copy() {
		if (this.right() == null) {
			ComplexFunction cf = new ComplexFunction(this.getOp(), this.left().copy(), null);
			return cf;
		} else {
			ComplexFunction cf = new ComplexFunction(this.getOp(), this.left().copy(), this.right().copy());
			return cf;
		}
	}
	
	/**
	 * Test if this ComplexFunction equals to func for several values
	 * @param func
	 * @return true iff this ComplexFunction represents the same function as func for certain values 
	 */
	private boolean sampleTest(function func) {
		boolean eror1, eror2;
		double random;
		for (int i = 1; i <= 50; i++) {
			eror1= false;
			eror2= false;
		    random = (Math.random() * 100)+50;
			try {
				this.f(i);
				this.f(random);
			}
			catch(Exception e) 
			{ 
				eror1=true;
			}
			try {
				func.f(i);
				func.f(random);
			}
			catch(Exception e) 
			{ 
				//one function undefined and one defined  in specific point
				if(eror1==false)
					return false;
				eror2=true;
			}//one function undefined and one defined  in specific point
			if(eror1==true && eror2==false)
				return false;
			if(eror1==false)
			{
				if (this.f(random) > func.f(random)+Monom.EPSILON || this.f(random) < func.f(random)-Monom.EPSILON ) 
					return false;
				if (this.f(random) > func.f(random)+Monom.EPSILON || this.f(random) < func.f(random)-Monom.EPSILON ) 
					return false;				
			}			
		}
		return true;
	}

	/**
	 * Test if this ComplexFunction is logically equals to obj.
	 * @param obj
	 * @return true iff this ComplexFunction represents the same function as obj for certain values
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Polynom) {
			Polynom p = (Polynom) obj;
			return sampleTest(p);
		} else if (obj instanceof Monom) {
			Monom m = (Monom) obj;
			return sampleTest(m);
		} else {
			ComplexFunction cf = (ComplexFunction) obj;
			return sampleTest(cf);
		}
	}
	
	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1) {
		if(f1==null) {
			throw new RuntimeException("ERR: add with null is impossible.");
		}
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Plus;
	}
	
	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {
		if(f1==null) {
			throw new RuntimeException("ERR: multiplication with null is impossible.");
		}
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Times;
	}
	
	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1) {
		if(f1==null) {
			throw new RuntimeException("ERR: dividing with null is impossible.");
		}
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Divid;
	}

	/** Computes the maximum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1) {
		if(f1==null) {
			throw new RuntimeException("ERR: get maximum with null is impossible.");
		}
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Max;
	}

	/** Computes the minimum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1) {
		if(f1==null) {
			throw new RuntimeException("ERR: get minimum with null is impossible.");
		}
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Min;
	}

	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x))
	 * @param f1 complex function
	 */
	public void comp(function f1) {
		if(f1==null) {
			throw new RuntimeException("ERR: comp with null is impossible.");
		}
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Comp;
	}

	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function left() {
		return this.left;
	}

	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function right() {
		return this.right;
	}

	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp
	 * @return the operation of this complex funcation
	 */
	public Operation getOp() {
		return this.op;
	}

}
