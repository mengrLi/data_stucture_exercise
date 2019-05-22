package AVLTree;
/*
 * input: 4 2 6 3 5 7 1 -1
 *  aavltree: 左右子树高度差为1
 *  output:能不能构成avl，能输出前序遍历
 */
import java.util.LinkedList;
import java.util.Scanner;
class Node {

	private int data;
	private Node rightChild;
	private Node leftChild;
	
	
	public Node(int data)
	{
		this.data=data;
		this.rightChild=null;
		this.leftChild=null;
	}
	public int getData()
	{
		return data;
	}
	public void setData(int input)
	{
		data=input;
	}
	
	public Node getRightChild()
	{
		return rightChild;
	}
	public void setRightChild(Node input)
	{
		rightChild=input;
	}
	
	public Node getLeftChild()
	{
		return leftChild;
	}
	public void setLeftChild(Node input)
	{
		leftChild=input;
	}

}

public class AVLTree {
	private Node root;
	private LinkedList<Integer> treeData;
        
	public AVLTree()
	{
		treeData=new LinkedList<Integer>();
	}
	public Node getRoot()
	{
		return root;
	}
	public void setRoot(Node input)
	{
		root=input;
	}
	
	public void insertNode(int data,Node root)
	{
		if (this.root==null)
			this.root=new Node(data);
		else if (data>root.getData())
		{
			if (root.getRightChild()==null)
				root.setRightChild(new Node(data));
			else
			{
				
				insertNode(data,root.getRightChild());

			}
		}
		else
		{
			if (root.getLeftChild()==null)
				root.setLeftChild(new Node(data));
			else
			{
				
				insertNode(data,root.getLeftChild());

			}
		}
		

	}
	public boolean balanceIsOk(Node input)
	{
		return Math.abs(height(input.getLeftChild())-height(input.getRightChild()))>1?false:true;
	}
	public int height(Node input)
	{
		
		if (input==null)
			return 0;
		return Math.max(height(input.getRightChild()), height(input.getLeftChild()))+1;
	}
        
        void printPreOrder(Node node)
                
        {
            if (node == null)
                return;
            System.out.print(node.getData() + " ");
            printPreOrder(node.getLeftChild());
            printPreOrder(node.getRightChild());
        }
 
    void printPreOrder()    
    {     
        printPreOrder(root);   
    }
   




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree testTree=new AVLTree();
		Scanner keyboard=new Scanner(System.in);
		int tmp=keyboard.nextInt();
		testTree.insertNode(tmp, null);
		tmp=keyboard.nextInt();
		while (tmp!=-1)
		{
			testTree.insertNode(tmp, testTree.getRoot());
			tmp=keyboard.nextInt();
		}
		if( testTree.balanceIsOk(testTree.getRoot()))	
                        testTree.printPreOrder();
		else
                    System.out.println("NOT");
	}
	

}
