package myMath.matala1;

public class ComplexFunction implements complex_function {
	private function left;
	private function right;
	private Operation op;
	
	//constructors
	public ComplexFunction(function f1) {
		this(null, f1, null);
	}
	
	public ComplexFunction(String s, function f1, function f2) {
		if(s==null) {
			this.op=Operation.None;
		}else if(s.equals("mul")) {
			this.op=Operation.Times;
		}else if(s.equals("div")) {
			this.op=Operation.Divid;
		}else if(s.equals("max")) {
			this.op=Operation.Max;
		}else if(s.equals("min")) {
			this.op=Operation.Min;
		}else if(s.equals("comp")) {
			this.op=Operation.Comp;
		}else if(s.equals("plus")) {
			this.op=Operation.Plus;
		}else {
			this.op=Operation.Error;
		}
		this.left = f1;
		this.right = f2;
	}


	//methods	
	public double f(double x) {
		double left=0, right=0, result=0;
		if(this.op==Operation.Error) {
			System.out.println("there is an unknown Operation, cannot calculate the result.");
		}
		if(this.op==Operation.None) {
			result=this.left.f(x);
		}
		if(this.op==Operation.Comp) {
			result=this.left.f(this.right.f(x));
		}
		left = this.left.f(x);
		right = this.right.f(x);
		if(this.op==Operation.Plus) {
			result=left+right;
		}else if(this.op==Operation.Times) {
			result=left*right;
		}else if(this.op==Operation.Divid) {
			result=left/right;
		}else if(this.op==Operation.Max) {
			result=Math.max(left, right);
		}else if(this.op==Operation.Min) {
			result=Math.min(left, right);
		}
		return result;
	}
	
	public function initFromString(String s) {
		return null;
	}
	
	public String toString() {
		return ""+this.getOp()+"("+this.left()+","+this.right()+")";
	}
	
	public function copy() {
		ComplexFunction cf = new ComplexFunction(""+this.getOp(), this.left(), this.right());
		return cf;
	}
	
	public void plus(function f1) {
		this.left = this.copy();
		this.right=f1;
		this.op =Operation.Plus;
	}
	
	public void mul(function f1) {
		this.left = this.copy();
		this.right=f1;
		this.op =Operation.Times;
	}
	
	public void div(function f1) {
		this.left = this.copy();
		this.right=f1;
		this.op =Operation.Divid;
	}
	
	public void max(function f1) {
		this.left = this.copy();
		this.right=f1;
		this.op =Operation.Max;
	}
	
	public void min(function f1) {
		this.left = this.copy();
		this.right=f1;
		this.op =Operation.Min;
	}
	
	public void comp(function f1) {
		this.left = this.copy();
		this.right=f1;
		this.op =Operation.Comp;
	}
	
	public function left() {
		return this.left;
	}
	
	public function right() {
		if(this.right==null) {
			System.out.println("there is no function on the right");
			return null;
		}
		return this.right;
	}
	
	public Operation getOp() {
//		if(this.op==Operation.None){
//			System.out.println("there is no Operation");
//			return null;
//		}else if(this.op==Operation.Error) {
//			System.out.println("there is an unknown Operation");
//			return null;
//		}
		return this.op;
	}
	
}
