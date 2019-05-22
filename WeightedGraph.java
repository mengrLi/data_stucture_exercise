

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class WeightedGraph {

    ArrayList<Vertex> vertices;

    public WeightedGraph() {
        vertices = new ArrayList<Vertex>();
    }

    public WeightedGraph(int[][] inputs) {
        vertices = new ArrayList<Vertex>();
        createGraph(inputs);
    }

    public void createGraph(int[][] inputs) {
        //input is (from, to, weight)

        for (int[] each: inputs) {
            int f = each[0];
            int t = each[1];
            int w = each[2];

            Vertex from = search(f);
            if (from == null) {
                from = new Vertex(f);
                vertices.add(from);
            }

            Vertex to = search(t);
            if (to == null) {
                to = new Vertex(t);
                vertices.add(to);
            }

            from.neighbours.add(new Edge(from, to, w));
        }
    }

    public Vertex search(int val) {

        for (Vertex each : vertices) {
            if (each.val == val) {
                return each;
            }
        }

        return null;
    }

    //A minimum spanning tree of a graph is a spanning tree with the minimum total weights.
    public Tree MiniSpanningTree() {

        ArrayList<Vertex> order = new ArrayList<Vertex>();
        HashMap<Vertex, Vertex> parent = new HashMap<Vertex, Vertex>();
        int total = 0;

        Edge min = findMinEdgeInGraph();
        Vertex root = min.from;
        parent.put(root, null);
        parent.put(min.to, root);
        order.add(min.from);
        order.add(min.to);
        total += min.weight;


        while (order.size() < vertices.size()) {

            min = findMiniWeightEdge(order);
            order.add(min.to);
            parent.put(min.to, min.from);
            total += min.weight;

        }

        return new Tree(root, parent, order, total, null);
    }


    public Edge findMinEdgeInGraph() {

        Edge min = null;
        for(Vertex each : vertices) {
            for (Edge e : each.neighbours) {
                if (min == null) {
                    min = e;
                } else {
                    if (e.weight < min.weight) {
                        min = e;
                    }
                }
            }
        }
        return min;
    }


    public Edge findMiniWeightEdge(ArrayList<Vertex> potentialVertex) {

        Edge min = null;
        for(Vertex each : potentialVertex) {
            for (Edge e : each.neighbours) {

                if (potentialVertex.contains(e.to)) {
                    continue;
                }

                if (min == null) {
                    min = e;
                } else {
                    if (e.weight < min.weight) {
                        min = e;
                    }
                }
            }
        }
        return min;

    }

    public Tree shortestPath(Vertex source) {

        ArrayList<Vertex> order = new ArrayList<Vertex>();
        HashMap<Vertex, Vertex> parent = new HashMap<Vertex, Vertex>();
        HashMap<Vertex, Integer> distance = new HashMap<Vertex, Integer>();

        //initial
        order.add(source);
        parent.put(source, null);

        for (Vertex each : vertices) {
            if (each.val == source.val) {
                distance.put(each, 0);
            } else {
                distance.put(each, Integer.MAX_VALUE);
            }
        }

        while (order.size() < vertices.size()) {

            int minTotal = Integer.MAX_VALUE;
            Edge min = null;

            for(Vertex each : order) {
                for (Edge e : each.neighbours) {

                    if (order.contains(e.to)) {
                        continue;
                    }

                    int temp = e.weight + distance.get(e.from);
                    if (temp < minTotal) {
                        minTotal = temp;
                        min = e;
                    }
                }
            }

            order.add(min.to);
            parent.put(min.to, min.from);
            distance.put(min.to, minTotal);

        }

        return new Tree(source, parent, order, 0, distance);

    }




    public class Tree {
        Vertex root;
        HashMap<Vertex, Vertex> parents;
        ArrayList<Vertex> visitOrder;
        int totalWeight;
        HashMap<Vertex, Integer> distance;


        public Tree(Vertex root, HashMap<Vertex, Vertex> parents, ArrayList<Vertex> visitOrder, int totalWeight, HashMap<Vertex, Integer> distance) {
            this.root = root;
            this.parents = parents;
            this.visitOrder = visitOrder;
            this.totalWeight = totalWeight;
            this.distance = distance;

        }
     }


    public class Edge implements Comparable<Edge>{
        Vertex from;
        Vertex to;
        int weight;

        public Edge(Vertex from, Vertex to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            if (this.weight > e.weight){
                return 1;
            } else if (this.weight < e.weight) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    public class Vertex{
        int val;
        PriorityQueue<Edge> neighbours;

        public Vertex(int val) {
            this.val = val;
            neighbours = new PriorityQueue<Edge>();
        }
    }


    public static void main(String[] args) {

        int[][] input = new int[][]{ {0, 1, 2}, {0, 3, 8}, {1, 0, 2}, {1, 2, 7}, {1, 3, 3}, {2, 1, 7}, {2, 3, 4}, {2, 4, 5}, {3, 0, 8}, {3, 1, 3}, {3, 2, 4}, {3, 4, 6}, {4, 2, 5}, {4, 3, 6} };

        WeightedGraph graph = new WeightedGraph(input);

        Tree tree = graph.shortestPath(graph.search(0));


        for (Vertex each : tree.parents.keySet()) {
            if (tree.parents.get(each) == null) {
                System.out.println(each.val + ":" );
            } else {
                System.out.println(each.val + ":" + tree.parents.get(each).val);
            }

        }

        System.out.println();
        for (Vertex each : tree.visitOrder) {
            System.out.print(each.val + " ");
        }
        System.out.println();

        for (Vertex each : tree.distance.keySet()) {
            System.out.println(each.val + ":" + tree.distance.get(each));
        }

    }


}
