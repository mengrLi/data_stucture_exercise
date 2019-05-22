import java.util.*;

public class detectGraphCircle {

	public detectGraphCircle() {
		// TODO Auto-generated constructor stub
	}
	
	public static class Node{
		int data;
		Node parent;
		boolean visited;
		ArrayList<Integer> adjList;
		
		Node(int d){
			data = d;
			parent = null;
			visited = false;
			adjList = new ArrayList<Integer>();
		}
	}
	
	public static Node getNode(LinkedList<Node>list, int data) {
		ListIterator<Node> it = list.listIterator();
		while(it.hasNext()) {
			Node cur = it.next();
			if(cur.data == data) {
				return cur;
			}
		}
		return null;
	}
	
	//replace the node of value data, with the new Node
	public static void replaceNode(LinkedList<Node>list, int data, Node newNode) {
		Node oldNode = getNode(list, data);
		if(oldNode == null)
			list.add(newNode);
		else {
			list.remove(oldNode);
			list.add(newNode);
		}
	}

	public static void printGraph(LinkedList<Node>list) {
		int numV = list.size();
		for(int i=0; i<numV; ++i) {
			Node curNode = list.get(i);
			System.out.print(curNode.data+": ");
			ArrayList<Integer> adj = curNode.adjList;
			System.out.println(adj.toString());
		}
	}
	
	public static boolean DFSCircle(LinkedList<Node>myGraph, int data) {
		Node cur = getNode(myGraph, data);		//find the current node
		if(cur.visited == true) {
			return true;						//find circle
		}
		
		cur.visited = true;						//mark as visited
		replaceNode(myGraph, cur.data, cur);	//put back to graph
		
		if(cur.adjList.size() == 0) {
			return false;						//end of dfs, no circle
		}
		
		ArrayList<Integer> adj = cur.adjList;  	//find the next unvisited adjacent node
		boolean findCircle = false;
		for(int i=0; i<adj.size(); ++i) {
			Node nextNode = getNode(myGraph, adj.get(i));
			if(nextNode.visited == true)		//find circle
				return true;
			findCircle = (findCircle || DFSCircle(myGraph, nextNode.data));
			if(findCircle)
				return true;
			
		}
		return false;
	}
	
	public static boolean DFSVisit(LinkedList<Node> myGraph, int u, LinkedList<Integer>myStack) {
		//check if u is in stack
		for(int i=0; i<myStack.size(); ++i) {
			if(myStack.get(i) == u)
				return true;							//node u in stack, find circle
		}
		boolean findCircle = false;
		myStack.push(u);
		Node nodeU = getNode(myGraph, u);				//get u
		if(nodeU.visited == false) {					//unvisited
			nodeU.visited = true;						//mark as visited
			replaceNode(myGraph, u, nodeU);				//put back
			ArrayList<Integer> adjList = nodeU.adjList;	
			for(int i=0; i<adjList.size(); ++i) {		//explore next depth
				findCircle =  findCircle || DFSVisit(myGraph, adjList.get(i), myStack);
			}
		}
		myStack.pop();
		return findCircle;
	}
	
	public static Node findUnvisitedNode(LinkedList<Node>list) {
		for(int i=0; i<list.size(); ++i) {
			Node cur = list.get(i);
			if(cur.visited == false)
				return cur;
		}
		return null;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = Integer.parseInt(sc.nextLine());
		String Line = "";
		
		LinkedList<Node> myGraph = new LinkedList<Node>();
		//scan each line and add each node into myGraph
		while(count >0) {
			Line = sc.nextLine();
			String [] nodesArr = Line.split("\\s+");
			int X = Integer.parseInt(nodesArr[0]);
			int Ri = Integer.parseInt(nodesArr[1]);
			int Rj = Integer.parseInt(nodesArr[2]);
			//Ri->X->Rj
			
			//process X
			Node nodeX = getNode(myGraph, X);		//get node X
			if(nodeX == null) {
				nodeX = new Node(X);
			}
			if(Rj != -1) {							//update
				boolean exist = nodeX.adjList.contains(X);
				if(!exist) {
					nodeX.adjList.add(Rj);
				}
			}
			replaceNode(myGraph, X, nodeX);			//replace
			
			//process Ri
			if(Ri != -1) {
				Node nodeRi = getNode(myGraph, Ri);			//get node Ri
				if(nodeRi == null) {
					nodeRi = new Node(Ri);
				}
				boolean exist = nodeRi.adjList.contains(X);	//update
				if(!exist) {
					nodeRi.adjList.add(X);
				}
				replaceNode(myGraph, Ri, nodeRi);			//replace
			}
			
			//process Rj
			if(Rj != -1) {
				Node nodeRj = getNode(myGraph, Rj);			//get node Rj
				if(nodeRj == null) {
					nodeRj = new Node(Rj);
				}
				replaceNode(myGraph, Rj, nodeRj);			//replace
			}
			count--;
		}
		//depth first search
		//printGraph(myGraph);
		
		//start from the random first node
		//while there is unvisited node in myGraph
		LinkedList<Integer>myS = new LinkedList<Integer>();
		boolean findCircle = false;
		Node u = findUnvisitedNode(myGraph);
		
		while(u != null) {
			//findCircle = DFSCircle(myGraph, u.data);
			findCircle  = DFSVisit(myGraph, u.data, myS);
			if(findCircle) {
				System.out.println("YES");
				break;
			}
			u = findUnvisitedNode(myGraph);
		}
		if(u == null)
			System.out.println("NO");
	}
}
