package PerfectPolicy;

import java.util.ArrayList;

public class BinaryTree {

    NodeBinary root;
    String btData;
    ArrayList<NodeBinary> nodeArrayList = new ArrayList<>();

    class NodeBinary {

        int qn;
        String topic;
        String subtopic;

        NodeBinary leftChild;
        NodeBinary rightChild;



        NodeBinary(int qn, String topic, String subtopic) {

            this.qn = qn;
            this.topic = topic;
            this.subtopic = subtopic;

        }

        public String toString() {

            return qn + "-" + topic + "(" + subtopic + "), ";


         /* return name + " has the key " + key + "\nLeft Child: " + leftChild +
          "\nRight Child: " + rightChild + "\n";*/


        }

    }

    public void addNode(int qn, String topic, String subtopic) {

        // Create a new Node and initialize it

        NodeBinary newNode = new NodeBinary(qn, topic, subtopic);

        // If there is no root this becomes root

        if (root == null) {

            root = newNode;

        } else {

            // Set root as the Node we will start

            NodeBinary focusNode = root;

            // Future parent for our new Node

            NodeBinary parent;

            while (true) {

                // root is the top parent so we start
                // there

                parent = focusNode;

                // Check if the new node should go on
                // the left side of the parent node

                if (qn < focusNode.qn) {

                    // Switch focus to the left child

                    focusNode = focusNode.leftChild;

                    // If the left child has no children

                    if (focusNode == null) {

                        // then place the new node on the left of it

                        parent.leftChild = newNode;
                        return; // All Done

                    }

                } else { // If we get here put the node on the right

                    focusNode = focusNode.rightChild;

                    // If the right child has no children

                    if (focusNode == null) {

                        // then place the new node on the right of it

                        parent.rightChild = newNode;
                        return; // All Done

                    }

                }

            }
        }

    }

    // All nodes are visited in ascending order
    // Recursion is used to go to one node and
    // then go to its child nodes and so forth

    public void inOrderTraverseTree(NodeBinary focusNode) {

        if (focusNode != null) {

            // Traverse the left node

            inOrderTraverseTree(focusNode.leftChild);

            // Visit the currently focused on node
            btData = btData + focusNode;
            nodeArrayList.add(focusNode);
            // System.out.println(focusNode);

            // Traverse the right node

            inOrderTraverseTree(focusNode.rightChild);

        }

    }

    public void preorderTraverseTree(NodeBinary focusNode) {

        if (focusNode != null) {

            // System.out.println(focusNode);
            btData = btData + focusNode;
            preorderTraverseTree(focusNode.leftChild);
            preorderTraverseTree(focusNode.rightChild);

        }

    }

    public void postOrderTraverseTree(NodeBinary focusNode) {

        if (focusNode != null) {



            postOrderTraverseTree(focusNode.leftChild);
            postOrderTraverseTree(focusNode.rightChild);

            btData = btData + focusNode;
            // System.out.println(focusNode);


        }
    }


    public NodeBinary findNode(int qn) {

        // Start at the top of the tree
        NodeBinary focusNode = root;

        // While we haven't found the Node
        // keep looking

        while (focusNode.qn != qn) {

            // If we should search to the left

            if (qn < focusNode.qn) {

                // Shift the focus Node to the left child

                focusNode = focusNode.leftChild;

            } else {

                // Shift the focus Node to the right child

                focusNode = focusNode.rightChild;

            }

            // The node wasn't found

            if (focusNode == null) {
                return null;

            }

            return focusNode;

        }
        return focusNode;
    }


    /*public static void main(String[] args) {

        BinaryTree theTree = new BinaryTree();

        theTree.addNode(50, "Boss");

        theTree.addNode(25, "Vice President");

        theTree.addNode(15, "Office Manager");

        theTree.addNode(30, "Secretary");

        theTree.addNode(75, "Sales Manager");

        theTree.addNode(85, "Salesman 1");
*/
    // Different ways to traverse binary trees

    // theTree.inOrderTraverseTree(theTree.root);

    // theTree.preorderTraverseTree(theTree.root);

    // theTree.postOrderTraverseTree(theTree.root);

    // Find the node with key 75

  /*      System.out.println("\nNode with the key 75");

        System.out.println(theTree.findNode(75));
*/
}