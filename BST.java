public class BST {

    // Represents one node in the binary search tree

    // The first node in tree
    private Node root;

    // number of elements in tree
    private int counter;

    // creating empty binary search tree
    public BST() {
        root = null;
        counter = 0;
    }

    // returns the number of elements in the tree
    public int size() {
        return counter;
    }

    // inserts new element into bst
    public void insert(int element) {

        // create new node containing element
        Node newNode = new Node(element);

        if (root == null) {
            root = newNode;
            counter++;
            return;
        }
    
    
    // start searching from root
    Node current = root;

    // keep searching till empty

    while (true) {
        // if element exists, do not insert it
        if (element == current.getKey()) {
            System.out.println("Element is already in the tree");
            return;
        }

        // if element is smaller go left
        if (element < current.getKey()) {

            // if no left child, insert new node here
            if (current.getLeftChild() == null) {
                current.setLeftChild(newNode);
                newNode.setParent(current);
                counter++;
                return;
            }

            // otherwise, search down left side
            current = current.getLeftChild();

        } else {
            // if no right child, insert new node here
            if (current.getRightChild() == null) {
                current.setRightChild(newNode);
                newNode.setParent(current);
                counter++;
                return;
            }

            // otherwise continue searching right side

            current = current.getRightChild();
        }

        }
}

    // searches tree for node that has the element
    private Node findNode(int element) {

        // begin searching at root
        Node current = root;

        while (current != null) {

            if (element == current.getKey()) {
                return current;
            }

            else if (element < current.getKey()) {
                current = current.getLeftChild();
            }

            else {
                current = current.getRightChild();
            }
        }
        // if element isnt in tree
        System.out.println("Not in tree man");
        return null;
    }
private Node findMaximum(Node current) {
    // keep moving right while right child exists
    while (current.getRightChild() != null) {
        current = current.getRightChild();
    }
    return current;
}
   

    

    private void transplant(Node oldNode, Node newNode) {

        // if oldnode is root, new node becomes root
        if (oldNode.getParent() == null) {
            root = newNode;
        }

        // if old node is its parents left child, connect the parent to newNode on left
        else if (oldNode == oldNode.getParent().getLeftChild()) {
            oldNode.getParent().setLeftChild(newNode);

        }

        // if not, connect parent with new node on right
        else {
            oldNode.getParent().setRightChild(newNode);
        }

        // if newnode exists, give it correct parent
        if (newNode != null) {
            newNode.setParent(oldNode.getParent());
        }
    }

    public void delete(int element) {
        Node target = findNode(element);

        if (target == null) {
            System.out.println("Element not here");
            return;
        }

        // case 1, target has no left child
        if (target.getLeftChild() == null) {

            // replace target with its right child
            transplant(target, target.getRightChild());
        }

        // case 2,, target has left child but no right child
        else if (target.getRightChild() == null) {

            // replace target with its left child
            transplant(target, target.getLeftChild());

        }

        // case 3, target has both left and right child
        // case 3, target has both left and right child
else {
    // find largest node in left subtree (the predecessor)
    Node predecessor = findMaximum(target.getLeftChild());

    // if predecessor is not target's direct child, move it out of its current position first
    if (predecessor.getParent() != target) {
        transplant(predecessor, predecessor.getLeftChild());

        // attach target's left subtree to predecessor
        predecessor.setLeftChild(target.getLeftChild());

        // update parent reference
        predecessor.getLeftChild().setParent(predecessor);
    }

    // replace the target with its predecessor
    transplant(target, predecessor);

    // attach target's right subtree to predecessor
    predecessor.setRightChild(target.getRightChild());

    // update the right child's parent reference
    predecessor.getRightChild().setParent(predecessor);
}

        // one element has been removed
        counter--;
    }

    public void preorder() {
        preorderHelper(root);
        System.out.println();
    }

    private void preorderHelper(Node current) {

        // base case, stop if no node
        if (current == null) {
            return;
        }

        System.out.print(current.getKey() + " ");

        preorderHelper(current.getLeftChild());

        preorderHelper(current.getRightChild());
    }

    public void inorder() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(Node current) {

        if (current == null) {
            return;
        }

        inorderHelper(current.getLeftChild());

        System.out.print(current.getKey() + " ");

        inorderHelper(current.getRightChild());
    }

    public void postorder() {
        postorderHelper(root);
        System.out.println();
    }

    private void postorderHelper(Node current) {

        if (current == null) {
            return;
        }

        postorderHelper(current.getLeftChild());

        postorderHelper(current.getRightChild());

        System.out.print(current.getKey() + " ");
    }


    private class Node {

        // number stored in node
        private int key;

        // reference to the node above this node
        private Node parent;

        // reference to the left child
        private Node leftChild;

        // Reference to right child
        private Node rightChild;

        // Constructor
        // Creates an empty node with a key of -1

        public Node() {
            this.key = -1;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;

        }

        // Constructor that creates node with specfic key

        public Node(int key) {
            this.key = key;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;

        }

        // constructor that initailizes every field
        public Node(int key, Node parent, Node leftChild, Node rightChild) {

            this.key = key;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
        // returns the number stored in the node

        public int getKey() {
            return key;
        }

        // changes number store3d in this node
        public void setKey(int key) {
            this.key = key;
        }

        // returns this node's parent
        public Node getParent() {
            return parent;
        }

        // changes this nodes parent
        public void setParent(Node parent) {
            this.parent = parent;
        }

        // returns left child
        public Node getLeftChild() {
            return leftChild;
        }

        // changes the left child
        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }
        // returns right child
        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
        


    }
}
