package tree;
import java.util.*;

public class relationTest {

	public relationTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static class Node{
		String value;
		Node left;
		Node right;
		
		public Node(String val) {
			this.value = val;
			this.left = null;
			this.right = null;
		}
	}
	
	//find parent name, insert child name after
	public static boolean preOrderFind(Node root, String pName, String cName) {
		if(root == null) {//empty tree;
			root = new Node(pName);
			root.left = new Node(cName);
			return true;
		}else{
			
		}
		return false;
	}
	
	//find the parent name node, and insert the child name node under it
	public static boolean findInsertNode(Node root, String parentName, String childName) {
		if(root == null)
			return false;
		if(root.value.equals(parentName)) {
			if(root.left == null)
				root.left = new Node(childName);
			else
				root.right = new Node(childName);
			return true;
		}
		return (findInsertNode(root.left, parentName, childName) || findInsertNode(root.right, parentName, childName));
	}
	
	public static boolean isSibling(Node root, String name1, String name2) {
		if(root == null)
			return false;
		if(root.left == null || root.right == null)
			return false;
		if(root.left.value.equals(name1) && root.right.value.equals(name2))
			return true;
		if(root.right.value.equals(name1) && root.left.value.equals(name2))
			return true;
		return (isSibling(root.left, name1, name2) || isSibling(root.right, name1, name2));
	}
	
	//find a node by name, starting from the root
	public static Node findNode(Node root, String name) {
		if(root == null)
			return null;
		if(root.value.equals(name)) {
			return root;
		}
		Node left = findNode(root.left, name);
		Node right = findNode(root.right, name);
		if(left != null)
			return left;
		else
			return right;
	}
	
//	public static void printTreeByLevel(Node root) {
//		Queue<Node> myQueue = new LinkedList<Node>();
//		myQueue.add(root);
//		while(!myQueue.isEmpty()) {
//			Node tmp = myQueue.remove();
//			if(tmp.left != null)
//				myQueue.add(tmp.left);
//			if(tmp.right != null)
//				myQueue.add(tmp.right);
//			System.out.print(tmp.value+" ");
//		}
//		System.out.println();
//	}
	
	public static void preOrderPrint(Node root) {
		if(root == null)
			return;
		System.out.print(root.value+" ");
		preOrderPrint(root.left);
		preOrderPrint(root.right);
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String Line = sc.nextLine();
		int datasetNum = Integer.parseInt(Line);
		
		//scan dataset and create the family tree
		Node root  = null;
		while(datasetNum > 0) {
			Line = sc.nextLine();
			String[] strArray = Line.split("\\s+");
			String parentName = strArray[0];
			String childName = strArray[1];
			
			if(root == null) {
				root = new Node(parentName);
				root.left = new Node(childName);
			}else {
				findInsertNode(root, parentName, childName);
			}
			
			//System.out.println(parentName+","+childName);
			datasetNum--;
		}
		//printTreeByLevel(root);
		
		Line = sc.nextLine();
		datasetNum = Integer.parseInt(Line);	//test datasets count
		while(datasetNum > 0) {
			Line = sc.nextLine();
			String[] strArray = Line.split("\\s+");
			String relation = strArray[1];
			String name1 = strArray[0];
			String name2 = strArray[2];
			
			if(relation.equals("child")) {
				Node node2 = findNode(root, name2);
				if(node2 == null)
					System.out.print("F");
				else {
					if(node2.left!=null && node2.left.value.equals(name1))
						System.out.print("T");
					else if(node2.right!=null && node2.right.value.equals(name1))
						System.out.print("T");
					else 
						System.out.print("F");
				}
			}else if(relation.equals("sibling")) {
				if(isSibling(root, name1, name2))
					System.out.print("T");
				else
					System.out.print("F");
			}else if(relation.equals("ancestor")) {
				Node node1 = findNode(root, name1);
				Node node2 = findNode(node1, name2);
				if(node2 != null)
					System.out.print("T");
				else 
					System.out.print("F");
			}else if(relation.equals("descendant")) {
				Node node2 = findNode(root, name2);
				Node node1 = findNode(node2, name1);
				if(node1 != null)
					System.out.print("T");
				else
					System.out.print("F");
			}
			System.out.print(" ");
			datasetNum--;
		}
		System.out.println();
		preOrderPrint(root);
	}
}
