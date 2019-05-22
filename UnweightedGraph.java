

//import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class UnweightedGraph {

    ArrayList<Vertex> vertices;
    HashMap<Vertex, Vertex> parentTemp;
    ArrayList<Integer> orderTemp;

    public UnweightedGraph() {

        vertices = new ArrayList<Vertex>();
        parentTemp = new HashMap<Vertex, Vertex>();
        orderTemp = new ArrayList<Integer>();
    }

    public UnweightedGraph(ArrayList<int[]> input) {

        vertices = new ArrayList<Vertex>();
        parentTemp = new HashMap<Vertex, Vertex>();
        orderTemp = new ArrayList<Integer>();
        createGraph(input);
    }



    private void createGraph(ArrayList<int[]> input) {


        for(int[] each : input) {

            Vertex from = search(each[0]);
            if (from == null) {
                from = new Vertex(each[0]);
                vertices.add(from);
            }

            Vertex to = search(each[1]);
            if (to == null) {
                to = new Vertex(each[1]);
                vertices.add(to);
            }

            from.neighbours.add(to);//
        } 
            
            for(int[] each : input) {
            		Vertex x  = search(each[0]);
            		 if (x == null) {
                         x = new Vertex(each[0]);
                         vertices.add(x);
                     }
            		 
            		 Vertex ri = search(each[0]);
            		 if (ri== null) {
                         ri = new Vertex(each[0]);
                         vertices.add(ri);
                     }
            		 
            		 Vertex rj  = search(each[0]);
            		 if (rj == null) {
                         rj = new Vertex(each[0]);
                         vertices.add(rj);
                     }
            		 
            		 ri.neighbours.add(x);
            		 x.neighbours.add(rj);
            }    
        
    }

    public Vertex search(int val) {

        if (vertices == null) {
            return null;
        }

        Vertex result = null;
        for (Vertex each : vertices) {
            if (each.val == val) {
                result = each;
                break;
            }
        }

        return result;
    }

    public Tree depthfirst(int root) {

        orderTemp.clear();
        parentTemp.clear();

        Vertex cur = search(root);
        parentTemp.put(cur, null);
        Tree tree = dfs(cur);
        return tree;
    }

    public Tree dfs(Vertex cur) {

        orderTemp.add(cur.val);

        for (Vertex each : cur.neighbours) {
            if (!orderTemp.contains(each.val)) {
                parentTemp.put(each, cur);
                dfs(each);
            }
        }

        return new Tree(cur, parentTemp, orderTemp);

    }

    public Tree breathfirst(int root) {

        orderTemp.clear();
        parentTemp.clear();

        Vertex cur = search(root);
        parentTemp.put(cur, null);
        orderTemp.add(cur.val);
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.addLast(cur.val);

        while (!queue.isEmpty()) {
            int top = queue.pollFirst();
            Vertex topp = search(top);

            for (Vertex each : topp.neighbours) {
                if (!orderTemp.contains(each.val)) {
                    parentTemp.put(each, cur);
                    orderTemp.add(each.val);
                    queue.add(each.val);
                }
            }
        }

        return new Tree(cur, parentTemp, orderTemp);
    }

    public ArrayList<Integer> getPath(int val1, int val2) {

        ArrayList<Integer> path = new ArrayList<Integer>();
        Tree tree = depthfirst(val1);

        HashMap<Vertex, Vertex> parents = tree.parents;
        Vertex temp = search(val2);

        path.add(temp.val);
        while (parents.get(temp) != null) {
            temp = parents.get(temp);
            path.add(temp.val);
        }

        return path;
    }


    public ArrayList<Integer> getShortPath(int val1, int val2) {

        ArrayList<Integer> path = new ArrayList<Integer>();
        Tree tree = breathfirst(val1);

        HashMap<Vertex, Vertex> parents = tree.parents;
        Vertex temp = search(val2);

        path.add(temp.val);
        while (parents.get(temp) != null) {
            temp = parents.get(temp);
            path.add(temp.val);
        }

        return path;
    }

    public class Tree{
        Vertex root;
        HashMap<Vertex, Vertex> parents;
        ArrayList<Integer> searchOrder;

        public Tree(Vertex root, HashMap<Vertex, Vertex> parents, ArrayList<Integer> searchOrder) {
            this.root = root;
            this.parents = parents;
            this.searchOrder = searchOrder;
        }
    }




    public class Vertex{
        int val;
        ArrayList<Vertex> neighbours;

        public Vertex(int val) {
            this.val = val;
            neighbours = new ArrayList<Vertex>();
        }

    }


    public static void main(String[] args) {

        ArrayList<int[]> input = new ArrayList<int[]>();

        for (int i=1; i<=4; i++) {
            for (int j=1; j<=4; j++) {
                if (i!=j) {
                    int[] next = {i, j};
                    input.add(next);
                }
            }
        }

        UnweightedGraph graph = new UnweightedGraph(input);
        Tree tree = graph.breathfirst(1);


        for (Vertex each : tree.parents.keySet()) {
            if (tree.parents.get(each) == null) {
                System.out.println(each.val + ":" );
            } else {
                System.out.println(each.val + ":" + tree.parents.get(each).val);
            }

        }

        System.out.println(graph.getShortPath(1,3));

    }


}
