package tree;
/*
 * input 
 * (F(B(A)(D(C)(E)))(G(I(H)())))
 * or (F(B(A)(D(C,E)))(G(I(H,))))

 *  output:F B G A D I C E H
 *  
 *  二叉树的广度优先遍历，可以处理数字或者字符，用括号隔开
 *  
 */

import java.util.ArrayList;
import java.util.Scanner;


class Node
{
	public String data;//public int value;
	public Node left;
	public Node right;
	public Node parent;
 public Node(String data)
 {
	 this.data =data;
	 left=right=null;
	 
 }
 public Node(String data, Node left,Node right)
 {
	 this.data=data;
	 this.right=right;
	 this.left=left;
			 	 
 }
 public void setLeft(Node node)
 {
	 left=node;
	 node.parent=this;
 }
 public void setRight(Node node)
 {
	 right=node;
	 node.parent=this;
 }
	
}

public class binaryTreeBreathFirst {
	public Node root;
	public Node target;
	public ArrayList<Integer> neibourNodes=new ArrayList<>();
	private String levelOrder="";
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new binaryTreeBreathFirst().run();

	}

	private void run() {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner (System.in);
		String str=scanner.nextLine();
		// remove first and last bracket
		str=str.substring(1, str.length()-1);
		//String targetNode = scanner.nextLine(); //d
		//int distance=Integer.parseInt(scanner.nextLine()); //2
		
		if (str==null||str.isEmpty())
			return;
		binaryTreeBreathFirst tree =new binaryTreeBreathFirst();
		// construct Binary tree
	
		//str=str.replaceAll(",", ")(");
		tree.root= constructTree(str);
		//print level order
		tree.printLevelOrder(tree.root);
		
	}

	private void printLevelOrder(Node root) {
		// TODO Auto-generated method stub
		int h=height(root);//树的高度
		//System.out.println(h);
		int i;
		for(i=1;i<=h;i++)
			printGivenlevel(root,i);
		
	}
	
private void printGivenlevel(Node node, int level) {
		// TODO Auto-generated method stub
	if (node == null)
		return;
	if (level == 1)
	{
		System.out.print(node.data + " ");
		this.levelOrder += node.data + " ";
	}
	else if (level > 1) 
	{
		printGivenlevel(node.left, level - 1);
		printGivenlevel(node.right, level - 1);
	}
		
	}

// 高度为左右子树最大高度加1(root)；
	private int height(Node node) {
		// TODO Auto-generated method stub
		if(node==null)
		   return 0;
		else
		{
			/* compute the height of each subtree */
			int lheight =height(node.left);
			int rheight =height(node.right);
			if(lheight>=rheight)
				return lheight+1;
			else
				return rheight+1;		
		}
	}

	public Node constructTree(String str) {
		// TODO Auto-generated method stub
		//bacis case 
		if(str==null||str.length()==0)
		{
			return null;
		}
		int firstParent = str.indexOf("("); // value 1 F(B(A)(D(C)(E)))(G(I(H)()))
		//System.out.println(firstParent);
		 //Base Condition until no more left parentheses
        String r;
        if(firstParent == -1)
        	r = str;
        else
        	r = str.substring(0, firstParent);
        //F
        
        Node cur =new Node(r);
        if(firstParent ==-1)
        	return cur; // only root
        int start= firstParent;
        int leftParentCount=0;
        for(int i=start; i<str.length();i++)
        {
        	if (str.charAt(i) == '(') 
            	leftParentCount++;
            else if (str.charAt(i) == ')') 
            	leftParentCount--;
            if (leftParentCount == 0 && start == firstParent) 
            {
            	cur.left = constructTree(str.substring(start+1,i));
            
            	start = i+1;
            }
            else if (leftParentCount == 0)
            	cur.right = constructTree(str.substring(start+1,i));
            
        }
         
        return cur;

        
	}
}
