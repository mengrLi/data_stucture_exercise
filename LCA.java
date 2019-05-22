package LCATree;

import java.util.ArrayList;
import java.util.Scanner;
/*
 * 最近的公共祖先
 * input：treeheight node1 node2 
 * output: lca heightofLCA
 * eg: input :3 1 11  *root is 0
 *     output: 0 0
 */
//lowest Common Ancestor
public class LCA {

    Node root;
    ArrayList<Integer> descendant;

    public LCA(int height) {

        descendant = new ArrayList<Integer>();

        root = new Node(0, 0);

        if (height >= 1) {

            Node one = new Node(1, 1);
            root.left = one;
            one.parent = root;

            Node two = new Node(2, 1);
            root.right = two;
            one.parent = root;
        }

        int cur = 3;
        for (int i=2; i <= height; i++) {

            int nodesOfUpperlevel = getNodesNum(i-1);

            for (int j=1; j <= nodesOfUpperlevel; j++) {

                int preVal = getNodesNum(i-1) - 1 + j - 1; //because node comes from 0, so -1 additionally

                addNode(cur++, preVal, i);
                addNode(cur++, preVal, i);

            }
        }

    }

    public boolean addNode(int val, int preVal, int depth) {

        Node temp = new Node(val, depth);

        Node parent = search(preVal);
        if (parent.left == null && parent.right == null) {
            parent.left = temp;
        } else {
            parent.right = temp;
        }

        temp.parent = parent;

        return true;
    }



    public int getNodesNum(int height) {

        int result = 1;
        if (height == 0) {
            result = 1;

        } else {

            while (height != 0) {
                result *= 2;
                height--;
            }
        }

        return result;
    }

    public Node search(int val) {

        return search(root, val);

    }

    public Node search(Node cur, int val) {

        if (cur != null) {
            if (cur.val == val) {
                return cur;
            } else {

                Node temp = search(cur.left, val);
                if (temp == null) {
                    temp = search(cur.right, val);
                }
                return temp;
            }
        }
        return null;
    }

    public void preOrderList(Node cur) {

        if (cur != null) {
            descendant.add(cur.val);
            preOrderList(cur.left);
            preOrderList(cur.right);
        }
    }

    public ArrayList<Integer> getPreOrder() {
        descendant.clear();
        preOrderList(root);

        return descendant;
    }

    public ArrayList<Integer> getDescendant(Node cur) {
        descendant.clear();
        preOrderList(cur);

        return descendant;
    }


    public class Node{
        int val;
        Node parent;
        Node left;
        Node right;
        int depth;

        public Node() {

        }

        public Node(int val, int depth) {
            this.val = val;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String line = in.nextLine();
        String[] parseLine = line.split(" ");

        int[] data = new int[parseLine.length];
        for (int i=0; i<parseLine.length; i++) {
            data[i] = Integer.valueOf(parseLine[i]);
        }

        LCA tree = new LCA(data[0]);

//        System.out.println(tree.getPreOrder());

        Node cur;
        int compareData;
        if (data[1] <= data[2]) {
            cur = tree.search(data[1]);
            compareData = data[2];

        } else {
            cur = tree.search(data[2]);
            compareData = data[1];
        }

        while (cur != null) {

            ArrayList<Integer> descendant = tree.getDescendant(cur);
//            System.out.println(descendant);

            if (descendant.indexOf(compareData) != -1) {
                System.out.println(cur.val + " " + cur.depth);
                break;
            } else {
                cur = cur.parent;
            }
        }
    }
}
