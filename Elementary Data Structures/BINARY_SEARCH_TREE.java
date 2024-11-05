public class BINARY_SEARCH_TREE {

    private Node root;
    private static final Node NIL = null; // NIL initialized as `null`

    public BINARY_SEARCH_TREE() {
        this.root = NIL;
    }

    public static class Node {
        int key;
        Node left, right, p;

        public Node(int key) {
            this.key = key;
            this.left = NIL;
            this.right = NIL;
            this.p = NIL;
        }
    }

    // Tree Traversal Methods
    public static void INORDER_TREE_WALK(Node x) {
        if (x != NIL) {
            INORDER_TREE_WALK(x.left);
            System.out.print(x.key + " ");
            INORDER_TREE_WALK(x.right);
        }
    }

    public static void PREORDER_TREE_WALK(Node x) {
        if (x != NIL) {
            System.out.print(x.key + " ");
            PREORDER_TREE_WALK(x.left);
            PREORDER_TREE_WALK(x.right);
        }
    }

    public static void POSTORDER_TREE_WALK(Node x) {
        if (x != NIL) {
            POSTORDER_TREE_WALK(x.left);
            POSTORDER_TREE_WALK(x.right);
            System.out.print(x.key + " ");
        }
    }

    // Search Methods
    public Node TREE_SEARCH(Node x, int k) {
        if (x == NIL || k == x.key) {
            return x;
        }
        if (k < x.key) {
            return TREE_SEARCH(x.left, k);
        } else {
            return TREE_SEARCH(x.right, k);
        }
    }

    public Node ITERATIVE_TREE_SEARCH(Node x, int k) {
        while (x != NIL && k != x.key) {
            if (k < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return x;
    }

    // Min/Max Methods
    public static Node TREE_MINIMUM(Node x) {
        while (x.left != NIL) {
            x = x.left;
        }
        return x;
    }

    public static Node TREE_MAXIMUM(Node x) {
        while (x.right != NIL) {
            x = x.right;
        }
        return x;
    }

    // Successor/Predecessor Methods
    public static Node TREE_SUCCESSOR(Node x) {
        if (x.right != NIL) {
            return TREE_MINIMUM(x.right);
        }
        Node y = x.p;
        while (y != NIL && x == y.right) {
            x = y;
            y = y.p;
        }
        return y;
    }

    public static Node TREE_PREDECESSOR(Node x) {
        if (x.left != NIL) {
            return TREE_MAXIMUM(x.left);
        }
        Node y = x.p;
        while (y != NIL && x == y.left) {
            x = y;
            y = y.p;
        }
        return y;
    }

    // Insert Method
    public static void TREE_INSERT(BINARY_SEARCH_TREE T, Node z) {
        Node y = NIL;
        Node x = T.root;

        while (x != NIL) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.p = y;

        if (y == NIL) {
            T.root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

    // Transplant Method
    public static void TRANSPLANT(BINARY_SEARCH_TREE T, Node u, Node v) {
        if (u.p == NIL) {
            T.root = v;
        } else if (u == u.p.left) {
            u.p.left = v;
        } else {
            u.p.right = v;
        }

        if (v != NIL) {
            v.p = u.p;
        }
    }

    // Delete Method
    public static void TREE_DELETE(BINARY_SEARCH_TREE T, Node z) {
        if (z.left == NIL) {
            TRANSPLANT(T, z, z.right);
        } else if (z.right == NIL) {
            TRANSPLANT(T, z, z.left);
        } else {
            Node y = TREE_MINIMUM(z.right);
            if (y.p != z) {
                TRANSPLANT(T, y, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            TRANSPLANT(T, z, y);
            y.left = z.left;
            y.left.p = y;
        }
    }

    public static void main(String[] args) {
        BINARY_SEARCH_TREE tree = new BINARY_SEARCH_TREE();

        // First test set
        int[] keysToInsert = {6, 5, 1, 5, 7, 8};
        for (int key : keysToInsert) {
            Node newNode = new Node(key);
            TREE_INSERT(tree, newNode);
        }

        // Tree traversal demonstrations
        System.out.print("InOrder Tree Walk: ");
        INORDER_TREE_WALK(tree.root);
        System.out.println();

        System.out.print("PreOrder Tree Walk: ");
        PREORDER_TREE_WALK(tree.root);
        System.out.println();

        System.out.print("PostOrder Tree Walk: ");
        POSTORDER_TREE_WALK(tree.root);
        System.out.println();

        // Search demonstration
        int keyToSearch = 8;
        Node result = tree.TREE_SEARCH(tree.root, keyToSearch);
        if (result != NIL) {
            System.out.println("Key " + keyToSearch + " found in the tree.");
        } else {
            System.out.println("Key " + keyToSearch + " not found in the tree.");
        }

        // Min/Max demonstrations
        Node min = TREE_MINIMUM(tree.root);
        System.out.println("The minimum value of the tree is: " + min.key);

        Node max = TREE_MAXIMUM(tree.root);
        System.out.println("The maximum value of the tree is: " + max.key);
        System.out.println();

        // Second test set
        keysToInsert = new int[]{15, 6, 18, 3, 7, 17, 20, 2, 4, 13, 9};
        for (int key : keysToInsert) {
            Node newNode = new Node(key);
            TREE_INSERT(tree, newNode);
        }

        // Successor demonstration
        int keyToFindSuccessor = 13;
        Node nodeToFindSuccessor = tree.TREE_SEARCH(tree.root, keyToFindSuccessor);
        if (nodeToFindSuccessor != NIL) {
            Node successor = TREE_SUCCESSOR(nodeToFindSuccessor);
            if (successor != NIL) {
                System.out.println("Successor of key " + keyToFindSuccessor + ": " + successor.key);
            } else {
                System.out.println("No successor found for key " + keyToFindSuccessor);
            }
        }

        // Predecessor demonstration
        int keyToFind = 13;
        Node nodeToFindPredecessor = tree.TREE_SEARCH(tree.root, keyToFind);
        if (nodeToFindPredecessor != NIL) {
            Node predecessor = TREE_PREDECESSOR(nodeToFindPredecessor);
            if (predecessor != NIL) {
                System.out.println("Predecessor of key " + keyToFind + ": " + predecessor.key);
            } else {
                System.out.println("No predecessor found for key " + nodeToFindPredecessor);
            }
        }

        // Delete demonstration
        int keyToDelete = 15;
        Node nodeToDelete = tree.TREE_SEARCH(tree.root, keyToDelete);
        if (nodeToDelete != NIL) {
            TREE_DELETE(tree, nodeToDelete);
            System.out.println("InOrder Tree Walk (After Deletion): ");
            INORDER_TREE_WALK(tree.root);
            System.out.println();
        } else {
            System.out.println("Key " + keyToDelete + " not found in the tree.");
        }
    }
}
