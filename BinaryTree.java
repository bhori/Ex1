package myMath.matala1;

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
     root = null; 
 }
 
 BinaryTree(function func) 
 { 
     root = new Node(func); 
 } 
 BinaryTree(Operation op, function func1, function func2){
	 root = new Node(op, func1, func2);
 }
 
}

