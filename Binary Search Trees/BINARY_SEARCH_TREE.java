public class BINARY_SEARCH_TREE {

    private Node root;
    private final Node NIL; // Sentinel NIL node

    public BINARY_SEARCH_TREE() {
        NIL = new Node(); // Initialize the NIL node
        root = NIL;
    }

    private class Node {
        int key;
        Node left, right, p;

        // Regular constructor for standard nodes with a key
        Node(int key) {
            this.key = key;
            this.left = NIL;
            this.right = NIL;
            this.p = NIL;
        }

        // Private constructor for NIL node
        Node() {
            this.left = this;
            this.right = this;
            this.p = this;
        }
    }

    // Tree Traversal Methods
    public void INORDER_TREE_WALK(Node x) {
        if (x != NIL) {
            INORDER_TREE_WALK(x.left);
            System.out.print(x.key + " ");
            INORDER_TREE_WALK(x.right);
        }
    }

    public void PREORDER_TREE_WALK(Node x) {
        if (x != NIL) {
            System.out.print(x.key + " ");
            PREORDER_TREE_WALK(x.left);
            PREORDER_TREE_WALK(x.right);
        }
    }

    public void POSTORDER_TREE_WALK(Node x) {
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
    public Node TREE_MINIMUM(Node x) {
        while (x.left != NIL) {
            x = x.left;
        }
        return x;
    }

    public Node TREE_MAXIMUM(Node x) {
        while (x.right != NIL) {
            x = x.right;
        }
        return x;
    }

    // Successor/Predecessor Methods
    public Node TREE_SUCCESSOR(Node x) {
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

    public Node TREE_PREDECESSOR(Node x) {
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
    public void TREE_INSERT(BINARY_SEARCH_TREE T, Node z) {
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
    public void TRANSPLANT(BINARY_SEARCH_TREE T, Node u, Node v) {
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
    public void TREE_DELETE(BINARY_SEARCH_TREE T, Node z) {
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
}
