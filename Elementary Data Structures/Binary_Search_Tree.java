public class Binary_Search_Tree {

    private Node root;
    public Binary_Search_Tree() {
        this.root = null;
    }

    public static class Node {
        int key;
        Node left, right, parent;

        public Node(int key) {
            this.key = key;
            left = right = parent = null;
        }
    }

    public static void INORDER_TREE_WALK(Node x) {
        if (x != null) {
            INORDER_TREE_WALK(x.left);
            System.out.print(x.key + " ");
            INORDER_TREE_WALK(x.right);
        }
    }

    public static void PREORDER_TREE_WALK(Node x){
        if (x != null){
            System.out.print(x.key+ " ");
            PREORDER_TREE_WALK(x.left);
            PREORDER_TREE_WALK(x.right);
        }
    }

    public static void POSTORDER_TREE_WALK(Node x){
        if (x != null){
            POSTORDER_TREE_WALK(x.left);
            POSTORDER_TREE_WALK(x.right);
            System.out.print(x.key+" ");
        }
    }

    public Node TREE_SEARCH(Node x, int k ){
        if (x == null || k == x.key){
            return x;
        }
        if (k < x.key){
            return TREE_SEARCH(x.left, k);
        } else {
            return TREE_SEARCH(x.right, k);
        }
    }

    public Node ITERATIVE_TREE_SEARCH(Node x,int k){
        while (x != null && k != x.key){
            if (k < x.key){
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return x;
    }

    public static Node TREE_MINIMUM(Node x){
        while (x.left != null){
            x = x.left;
        }
        return x;
    }

    public static Node TREE_MAXIMUM(Node x){
        while (x.right != null){
            x = x.right;
        }
        return x;
    }

    public static Node TREE_SUCCESSOR(Node x){
        if (x.right != null){
            return TREE_MINIMUM(x.right);
        }
        Node y = x.parent;
        while (y != null && x == y.right){
            x = y;
            y = y.parent;
        }
        return y;
    }

    public static Node TREE_PREDECESSOR(Node x){
        if (x.left != null){
            return TREE_MAXIMUM(x.left);
        }
        Node y = x.parent;
        while (y != null && x == y.left){
            x = y;
            y = y.parent;
        }
        return y;
    }

    public static void TREE_INSERT(Binary_Search_Tree T, Node z){
        Node y = null;
        Node x = T.root;

        while (x != null){
            y = x;
            if (z.key < x.key){
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;

        if (y == null){
            T.root = z;
        } else if (z.key < y.key){
            y.left = z;
        } else {
            y.right = z;
        }
    }

    public static void TRANSPLANT(Binary_Search_Tree T,Node u, Node v){
        if (u.parent == null){
            T.root = v;
        }
        else if (u == u.parent.left){
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }

        if (v != null){
            v.parent = u.parent;
        }
    }

    public static void TREE_DELETE(Binary_Search_Tree T,Node z){
        if (z.left == null){
            TRANSPLANT(T, z, z.right);
        }
        else if (z.right == null){
            TRANSPLANT(T, z, z.left);
        }
        else {
            Node y = TREE_MINIMUM(z.right);
            if (y.parent != z){
                TRANSPLANT(T, y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            TRANSPLANT(T, z, y);
            y.left = z.left;
            y.left.parent = y;
        }
    }


    //INORDER_TREE_WALK(x);
    //TREE_SEARCH(x,k);
    //ITERATIVE_TREE_SEARCH(x,k);
    //TREE_MINIMUM(x);
    //TREE_MAXIMUM(x);
    //TREE_SUCCESSOR(x);
    //TREE_PREDECESSOR(x);
    //TREE_INSERT(T,z);
    //TRANSPLANT(T,u,v);
    //TREE_DELETE(T,z);

    public static void main(String[] args) {
        Binary_Search_Tree tree = new Binary_Search_Tree();
        int[] keysToInsert = {6, 5, 1, 5, 7, 8};
        for (int key: keysToInsert){
            Node newNode = new Node(key);
            tree.TREE_INSERT(tree, newNode);
        }
        System.out.print("InOrder Tree Walk: ");
        tree.INORDER_TREE_WALK(tree.root);
        System.out.println();
        System.out.print("PreOrder Tree Walk: ");
        tree.PREORDER_TREE_WALK(tree.root);
        System.out.println();
        System.out.print("PostOrder Tree Walk: ");
        tree.POSTORDER_TREE_WALK(tree.root);
        System.out.println();

        //TREE-SERACH
        int keyToSearch = 8;
        Node result = tree.TREE_SEARCH(tree.root, keyToSearch);
        if (result != null){
            System.out.println("Key " + keyToSearch + " found in the tree.");
        } else {
            System.out.println("Key " + keyToSearch + " not found in the tree.");
        }

        //TREE_MINIMUM(x)
        Node min = TREE_MINIMUM(tree.root);
        System.out.println("The minimum value of the tree is: "+min.key);

        //TREE_MAXIMUM(x)
        Node max = TREE_MAXIMUM(tree.root);
        System.out.println("The minimum value of the tree is: "+max.key);
        System.out.println();


        //give me an example of TREE_SUCCESSOR using numbers.
        keysToInsert = new int[]{15, 6, 18, 3, 7, 17, 20, 2, 4, 13, 9};
        for (int key: keysToInsert){
            Node newNode = new Node(key);
            tree.TREE_INSERT(tree, newNode);
        }

        //TREE_SUCCESSOR(x)
        int keyToFindSuccessor = 13;
        Node nodeToFindSuccessor = tree.TREE_SEARCH(tree.root, keyToFindSuccessor);
        if (nodeToFindSuccessor != null) {
            Node successor = tree.TREE_SUCCESSOR(nodeToFindSuccessor);
            if (successor != null) {
                System.out.println("Successor of key " + keyToFindSuccessor + ": " + successor.key);
            } else {
                System.out.println("No successor found for key " + keyToFindSuccessor);
            }
        } else {
            System.out.println("Key " + keyToFindSuccessor + " not found in the tree.");
        }

        //TREE_PREDECESSOR(x)
        int keyToFind = 13;
        Node nodeToFindPredecessor = tree.TREE_SEARCH(tree.root, keyToFind);
        if (nodeToFindPredecessor != null) {
            Node predecessor = tree.TREE_PREDECESSOR(nodeToFindPredecessor);
            if (predecessor != null) {
                System.out.println("Predecessor of key " + keyToFind + ": " + predecessor.key);
            } else {
                System.out.println("No predecessor found for key " + nodeToFindPredecessor);
            }
        } else {
            System.out.println("Key " + keyToFind + " not found in the tree.");
        }

        // Choose a key to delete
        int keyToDelete = 15;

        // Find the node with the specified key
        Node nodeToDelete = tree.TREE_SEARCH(tree.root, keyToDelete);

        if (nodeToDelete != null) {
            // Perform deletion
            tree.TREE_DELETE(tree, nodeToDelete);

            System.out.println("InOrder Tree Walk (After Deletion): ");
            tree.INORDER_TREE_WALK(tree.root);
            System.out.println();
        } else {
            System.out.println("Key " + keyToDelete + " not found in the tree.");
        }

    }
}