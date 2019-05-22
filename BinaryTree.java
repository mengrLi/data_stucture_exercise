package tree;
import java.util.*;
/*
 * 二叉树广度优先遍历，找到与给定node距离为k的节点
 */
class TreeNode 
{
    public String data;
	public TreeNode left, right;

	public TreeNode(String data) 
    {
        this.data = data;
        left = right = null;
    }

	public TreeNode(String data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

}

public class BinaryTree 
{
    private TreeNode overallRoot;
    private TreeNode target;
    private ArrayList<String> neighbouringNodes = new ArrayList<String>();
    private String levelOrder="";
    
    public static TreeNode constructTree(String s)
	{
    	if (s == null || s.length() == 0) return null;
        int firstParen = s.indexOf("(");
        
        //Base Condition until no more left parentheses
        String val;
        if(firstParen == -1)
        	val = s;
        else
        	val = s.substring(0, firstParen);
        
        TreeNode cur = new TreeNode(val);
        if (firstParen == -1) 
        	return cur;
        int start = firstParen, leftParenCount = 0;//
        
        for (int i=start;i<s.length();i++)
        {
            if (s.charAt(i) == '(') 
            	leftParenCount++;
            else if (s.charAt(i) == ')') 
            	leftParenCount--;
            if (leftParenCount == 0 && start == firstParen) 
            {
            	cur.left = constructTree(s.substring(start+1,i));
            	start = i+1;
            }
            else if (leftParenCount == 0)
            	cur.right = constructTree(s.substring(start+1,i));
        }
        return cur;
	}

	void printPreorder(TreeNode node)
	{
		if (node == null)
    		return;
    	System.out.print(node.data + " ");
    	/* then recur on left sutree */
    	printPreorder(node.left);
    	/* now recur on right subtree */
    	printPreorder(node.right);
	}
	  
	// function to print level order traversal of tree 
	void printLevelOrder(TreeNode root)
	{
		int h = height(root);
		int i;
		for (i=1; i<=h; i++)
			printGivenLevel(root, i);
	}
			
	/* Print nodes at a given level */
	void printGivenLevel(TreeNode node, int level) 
	{
		if (node == null)
			return;
		if (level == 1)
		{
			System.out.print(node.data + " ");
			this.levelOrder += node.data + " ";
		}
		else if (level > 1) 
		{
			printGivenLevel(node.left, level - 1);
			printGivenLevel(node.right, level - 1);
		}
	}
		
	// Compute the height of a tree 
	int height(TreeNode node) 
	  {
		  if (node == null)
				return 0;
		  else
		  {
				/* compute the height of each subtree */
				int lheight = height(node.left);
				int rheight = height(node.right);

				/* use the larger one */
				if (lheight > rheight)
					return (lheight + 1);
				else
					return (rheight + 1);
		  }
	  }
	
	void printkdistanceNodeDown(TreeNode node, int k) 
	{
		if (node == null || k < 0)
			return;

		if (k == 0) 
		{
			neighbouringNodes.add(node.data);
			return;
		}

		printkdistanceNodeDown(node.left, k - 1);
		printkdistanceNodeDown(node.right, k - 1);
	}

	// Prints all nodes at distance k from a given target node. The k distant nodes may be upward or downward.
	int printkdistanceNode(TreeNode node, TreeNode target, int k) 
	{
		if (node == null)
			return -1;

		// If target is same as root. Use the printkdistanceNodeDown function
		if (node == target) 
		{
			printkdistanceNodeDown(node, k);
			return 0;
		}

		
		int dl = printkdistanceNode(node.left, target, k);

		// Check if target node was found in left subtree. Note that dl is Distance of root's left child from target
		if (dl != -1) 
		{
			// If root is at distance k from target, print root					
			if (dl + 1 == k) 
				neighbouringNodes.add(node.data);				  		
			// Else go to right subtree and print all distant nodes. The right child is 2 edges away from left child
			else
				printkdistanceNodeDown(node.right, k - dl - 2);
	
			// Add 1 to the distance and return value for parent calls
			return 1 + dl;
		}
	
		//  Check for right subtree, when node was not found in left subtree 
		int dr = printkdistanceNode(node.right, target, k);
		if (dr != -1) 
		{
			if (dr + 1 == k) 
				neighbouringNodes.add(node.data);
			else
				printkdistanceNodeDown(node.left, k - dr - 2);
			return 1 + dr;
		}
	
		return -1; // If target was not found
	 }
	  
	public boolean findNode(TreeNode root, String x)
	{
		 if (root != null)
		 {
			 // check if current node (root) has the element we are looking for
			 if (root.data.equals(x))
			 {
				target = root;
				
				return true;
			 }
			 else			 
				 // check if the sub trees
				 return findNode(root.left, x) || findNode(root.right, x);			 
		 }
		 return false;
	}
	 
	public String printNeighbors()
	{
		 String neighbours = "";
		 for(String S: levelOrder.split(" "))
		 {
			 if(neighbouringNodes.contains(S))
				 neighbours += S + " ";
		 }	
		 return neighbours.trim();
	}
  	  
    // Driver program to test above functions
    public static void main(String[] args) 
    {
		Scanner fin = new Scanner(System.in);
		//Tree
		String str = fin.nextLine();
		
		//Remove first and last round bracket
		str = str.substring(1, str.length()-1);
				
		//Node
		String targetNode = fin.nextLine();
		
		//Distance k
		int k = Integer.parseInt(fin.nextLine());
		
	
		if(str == null || str.isEmpty())
			return;
				
		BinaryTree tree = new BinaryTree();
        //Construct Binary Tree
		tree.overallRoot = constructTree(str);                
        //Print Level Order
		tree.printLevelOrder(tree.overallRoot);//output:F B G A D I C E H
        //Find the specified node in the tree (guaranteed to have this node so no need to check for null)
		tree.findNode(tree.overallRoot, targetNode);
        //Find neighbors in distance k
		tree.printkdistanceNode(tree.overallRoot, tree.target, k);   
        System.out.print(tree.printNeighbors());
    }
}