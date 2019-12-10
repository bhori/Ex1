package Ex1;

public class ComplexFunction implements complex_function {
	private function left;
	private function right;
	private Operation op;

	// constructors
	public ComplexFunction(function f1) {
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
		this.op = op;
		this.left = f1.copy();
		if (f2 == null) {
			this.right = null;
		} else {
			this.right = f2.copy();
		}
	}

	public ComplexFunction(String s, function f1, function f2) {
		s = s.toLowerCase();
		if ((s == null) || (s.equals("none"))) {
			this.op = Operation.None;
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
		this.left = f1.copy();
		if (f2 == null) {
			this.right = null;
		} else {
			this.right = f2.copy();
		}
	}

	// methods
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

	public function initFromString(String s) {
		if (s.indexOf('(') == -1) {
			if (s.equals("null")) {
				return null;
			} else {
				function cf = new Polynom(s);
				return cf;
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
			String left = s.substring(s.indexOf('(') + 1, i);
			String right = s.substring(i + 1, s.length() - 1);
			function leftFunc = initFromString(left);
			function rightFunc = initFromString(right);
			String operation = s.substring(0, s.indexOf('('));
			function cf = new ComplexFunction(operation, leftFunc, rightFunc);
			return cf;
		}
	}

	public String toString() {
		if (this.getOp() == Operation.None) {
			if (this.right == null) {
				return "" + this.left();
			}
		}
		return "" + this.getOp() + "(" + this.left() + "," + this.right() + ")";
	}

	public function copy() {
		if (this.right() == null) {
			ComplexFunction cf = new ComplexFunction(this.getOp(), this.left().copy(), null);
			return cf;
		} else {
			ComplexFunction cf = new ComplexFunction(this.getOp(), this.left().copy(), this.right().copy());
			return cf;
		}
	}

	public boolean sampleTest(function func) {
		for (int i = 1; i <= 50; i++) {
			if (this.f(i) != func.f(i)) {
				return false;
			}
			double random = Math.random() * 100;
			if (this.f(random) != func.f(random)) {
				return false;
			}
		}
		return true;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Polynom) {
			Polynom p = (Polynom) obj;
			return sampleTest(p);
		} else if (obj instanceof Monom) {
			Monom m = (Monom) obj;
			return sampleTest(m);
		} else {// if(obj instanceof ComplexFunction) {
			ComplexFunction cf = (ComplexFunction) obj;
			return sampleTest(cf);
		}

	}

	public void plus(function f1) {
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Plus;
	}

	public void mul(function f1) {
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Times;
	}

	public void div(function f1) {
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Divid;
	}

	public void max(function f1) {
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Max;
	}

	public void min(function f1) {
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Min;
	}

	public void comp(function f1) {
		this.left = this.copy();
		this.right = f1.copy();
		this.op = Operation.Comp;
	}

	public function left() {
		return this.left;
	}

	public function right() {
		return this.right;
	}

	public Operation getOp() {
		return this.op;
	}

}
