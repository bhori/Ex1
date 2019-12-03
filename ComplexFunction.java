package myMath.matala1;

public class ComplexFunction implements complex_function {
	private Node root;				
//constructors
	public ComplexFunction(function f1) {
		this.root = new Node(null, f1, null);
	}
	public ComplexFunction(Operation op, function f1, function f2) {
		this.root = new Node(op, f1, f2);
	}

	
	//methods	
	public double f(double x) {
		return 0;
	}
	
	public function initFromString(String s) {
		return null;
	}
	
	public function copy() {
		return null;
	}
	
	public void plus(function f1) {
		Operation.Comp
	}
	
	public void mul(function f1) {
		
	}
	
	public void div(function f1) {
		
	}
	
	public void max(function f1) {
		
	}
	
	public void min(function f1) {
		
	}
	
	public void comp(function f1) {
		
	}
	
	public function left() {
		return null;
	}
	
	public function right() {
		return null;
	}
	
	public Operation getOp() {
		
		return null;
	}
	
	//innec classes
	/* Class containing left and right child of current 
	node and key value*/
	class Node 
	{ 
	 Operation op;
	 function func; 
	 Node left, right; 

	 public Node(function func){ 
	     this.func = func; 
	     this.left = this.right = null; 
	 } 
	 public Node(Operation op, function func1, function func2){
		 this.op=op;
		 this.left=new Node(func1);
		 this.right=new Node(func2);
	 }
	} 

	//A Java program to introduce Binary Tree 
	public class BinaryTree 
	{ 
	 // Root of Binary Tree 
	 Node root; 

	 // Constructors 
	 BinaryTree() 
	 { 
	     this.root = null; 
	 }
	 
	 BinaryTree(function func) 
	 { 
	     this.root = new Node(func); 
	 } 
	 BinaryTree(Operation op, function func1, function func2){
		 this.root = new Node(op, func1, func2);
	 }
	 
	}
}
