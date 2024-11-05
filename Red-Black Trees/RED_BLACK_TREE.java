public class RED_BLACK_TREE {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private final Node NIL; // Sentinel NIL node

    // Node class representing each node in the tree
    private class Node {
        int key;
        boolean color;
        Node left, right, p;

        // Constructor for regular nodes
        Node(int key) {
            this.key = key;
            this.color = RED; // New nodes are initially red
            this.left = NIL;
            this.right = NIL;
            this.p = NIL;
        }

        // Constructor for the NIL node
        Node() {
            this.color = BLACK;
            this.left = null;
            this.right = null;
            this.p = null;
        }
    }

    // Constructor for the Red-Black Tree
    public RED_BLACK_TREE() {
        NIL = new Node(); // Initialize the NIL node
        root = NIL;
    }

    // Left Rotate
    private void LEFT_ROTATE(RED_BLACK_TREE T, Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != T.NIL) {
            y.left.p = x;
        }
        y.p = x.p;

        if (x.p == T.NIL) {
            T.root = y;
        } else if (x == x.p.left) {
            x.p.left = y;
        } else {
            x.p.right = y;
        }
        y.left = x;
        x.p = y;
    }

    // Right Rotate
    private void RIGHT_ROTATE(RED_BLACK_TREE T, Node y) {
        Node x = y.left;
        y.left = x.right;

        if (x.right != T.NIL) {
            x.right.p = y;
        }
        x.p = y.p;

        if (y.p == T.NIL) {
            T.root = x;
        } else if (y == y.p.right) {
            y.p.right = x;
        } else {
            y.p.left = x;
        }
        x.right = y;
        y.p = x;
    }

    // Insert
    public void RB_INSERT(RED_BLACK_TREE T, Node z) {
        Node x = T.root; // Start from the root
        Node y = T.NIL; // Initialize y as T.NIL

        // Find the appropriate position for the new node
        while (x != T.NIL) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.p = y; // Set the parent of the new node
        if (y == T.NIL) {
            T.root = z; // Tree was empty
        } else if (z.key < y.key) {
            y.left = z; // Insert as left child
        } else {
            y.right = z; // Insert as right child
        }

        z.left = T.NIL; // Initialize left and right children to T.NIL
        z.right = T.NIL;
        z.color = RED; // New node is red

        // Fix the tree
        RB_INSERT_FIXUP(T, z);
    }

    // Fix up the tree after insertion
    private void RB_INSERT_FIXUP(RED_BLACK_TREE T, Node z) {
        while (z.p.color == RED) {
            if (z.p == z.p.p.left) {
                Node y = z.p.p.right; // Uncle node
                if (y.color == RED) {
                    // Case 1: Uncle is red
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                } else {
                    // Case 2: Uncle is black
                    if (z == z.p.right) {
                        // Case 2a: z is a right child
                        z = z.p;
                        LEFT_ROTATE(T, z);
                    }
                    // Case 2b: z is a left child
                    z.p.color = BLACK;
                    z.p.p.color = RED;
                    RIGHT_ROTATE(T, z.p.p);
                }
            } else {
                Node y = z.p.p.left; // Uncle node
                if (y.color == RED) {
                    // Case 1: Uncle is red
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                } else {
                    // Case 2: Uncle is black
                    if (z == z.p.left) {
                        // Case 2a: z is a left child
                        z = z.p;
                        RIGHT_ROTATE(T, z);
                    }
                    // Case 2b: z is a right child
                    z.p.color = BLACK;
                    z.p.p.color = RED;
                    LEFT_ROTATE(T, z.p.p);
                }
            }
        }
        T.root.color = BLACK; // Ensure the root is always black
    }

    // Transplant
    private void RB_TRANSPLANT(RED_BLACK_TREE T, Node u, Node v) {
        if (u.p == T.NIL) {
            T.root = v; // If u is root, make v the new root
        } else if (u == u.p.left) {
            u.p.left = v; // If u is a left child, link v as left child
        } else {
            u.p.right = v; // If u is a right child, link v as right child
        }
        v.p = u.p; // Update parent pointer of v
    }

    // Delete
    public void RB_DELETE(RED_BLACK_TREE T, Node z) {
        Node y = z; // Node to be deleted
        Node x; // Node that will replace z
        boolean y_original_color = y.color; // Store the color of y

        if (z.left == T.NIL) {
            // Case 1: z has no left child
            x = z.right;
            RB_TRANSPLANT(T, z, z.right);
        } else if (z.right == T.NIL) {
            // Case 2: z has no right child
            x = z.left;
            RB_TRANSPLANT(T, z, z.left);
        } else {
            // Case 3: z has two children
            y = TREE_MINIMUM(z.right); // Get the successor
            y_original_color = y.color; // Store the color of y
            x = y.right; // y's right child

            if (y.p == z) {
                x.p = y; // Maintain the parent link
            } else {
                RB_TRANSPLANT(T, y, y.right);
                y.right = z.right;
                y.right.p = y;
            }

            RB_TRANSPLANT(T, z, y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
        }
        if (y_original_color == BLACK) {
            RB_DELETE_FIXUP(T, x); // Fix the tree if necessary
        }
    }

    // Fix up the tree after deletion
    private void RB_DELETE_FIXUP(RED_BLACK_TREE T, Node x) {
        while (x != T.root && x.color == BLACK) {
            if (x == x.p.left) {
                Node w = x.p.right; // Sibling node
                if (w.color == RED) {
                    // Case 1: w is red
                    w.color = BLACK;
                    x.p.color = RED;
                    LEFT_ROTATE(T, x.p);
                    w = x.p.right; // Update w
                }
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    // Case 2: w's children are both black
                    w.color = RED;
                    x = x.p; // Move up the tree
                } else {
                    if (w.right.color == BLACK) {
                        // Case 3: w's left child is red and right child is black
                        w.left.color = BLACK;
                        w.color = RED;
                        RIGHT_ROTATE(T, w);
                        w = x.p.right; // Update w
                    }
                    // Case 4: w's right child is red
                    w.color = x.p.color;
                    x.p.color = BLACK;
                    w.right.color = BLACK;
                    LEFT_ROTATE(T, x.p);
                    x = T.root; // Exit loop
                }
            } else {
                Node w = x.p.left; // Sibling node
                if (w.color == RED) {
                    // Case 1: w is red
                    w.color = BLACK;
                    x.p.color = RED;
                    RIGHT_ROTATE(T, x.p);
                    w = x.p.left; // Update w
                }
                if (w.right.color == BLACK && w.left.color == BLACK) {
                    // Case 2: w's children are both black
                    w.color = RED;
                    x = x.p; // Move up the tree
                } else {
                    if (w.left.color == BLACK) {
                        // Case 3: w's right child is red and left child is black
                        w.right.color = BLACK;
                        w.color = RED;
                        LEFT_ROTATE(T, w);
                        w = x.p.left; // Update w
                    }
                    // Case 4: w's left child is red
                    w.color = x.p.color;
                    x.p.color = BLACK;
                    w.left.color = BLACK;
                    RIGHT_ROTATE(T, x.p);
                    x = T.root; // Exit loop
                }
            }
        }
        x.color = BLACK; // Ensure the new node is black
    }

    // Find the minimum node
    private Node TREE_MINIMUM(Node x) {
        while (x.left != NIL) {
            x = x.left;
        }
        return x;
    }

    public static void main(String[] args) {
        // Create a new red-black tree
        RED_BLACK_TREE tree = new RED_BLACK_TREE();

        // Insert some nodes
        int[] keys = {41, 38, 31, 12, 19, 8};
        for (int key : keys) {
            Node newNode = tree.new Node(key);
            tree.RB_INSERT(tree, newNode);
        }

        // Print the tree using in-order traversal
        System.out.println("In-order traversal of the Red-Black Tree:");
        tree.inOrderTraversal(tree.root);

        // Find and delete a specific node (e.g., node with key 8)
        Node nodeToDelete_8 = tree.findNode(tree.root, 8);
        if (nodeToDelete_8 != tree.NIL) {
            tree.RB_DELETE(tree, nodeToDelete_8);
            System.out.println("\nAfter deleting node with key 8:");
            tree.inOrderTraversal(tree.root);
        } else {
            System.out.println("\nNode with key 8 not found.");
        }

        // Find and delete a specific node (e.g., node with key 12)
        Node nodeToDelete_12 = tree.findNode(tree.root, 12);
        if (nodeToDelete_12 != tree.NIL) {
            tree.RB_DELETE(tree, nodeToDelete_12);
            System.out.println("\nAfter deleting node with key 12:");
            tree.inOrderTraversal(tree.root);
        } else {
            System.out.println("\nNode with key 12 not found.");
        }

        // Find and delete a specific node (e.g., node with key 19)
        Node nodeToDelete_19 = tree.findNode(tree.root, 19);
        if (nodeToDelete_19 != tree.NIL) {
            tree.RB_DELETE(tree, nodeToDelete_19);
            System.out.println("\nAfter deleting node with key 19:");
            tree.inOrderTraversal(tree.root);
        } else {
            System.out.println("\nNode with key 19 not found.");
        }

        // Find and delete a specific node (e.g., node with key 15)
        Node nodeToDelete_31 = tree.findNode(tree.root, 31);
        if (nodeToDelete_31 != tree.NIL) {
            tree.RB_DELETE(tree, nodeToDelete_31);
            System.out.println("\nAfter deleting node with key 31:");
            tree.inOrderTraversal(tree.root);
        } else {
            System.out.println("\nNode with key 31 not found.");
        }

        // Find and delete a specific node (e.g., node with key 15)
        Node nodeToDelete_38 = tree.findNode(tree.root, 38);
        if (nodeToDelete_38 != tree.NIL) {
            tree.RB_DELETE(tree, nodeToDelete_38);
            System.out.println("\nAfter deleting node with key 38:");
            tree.inOrderTraversal(tree.root);
        } else {
            System.out.println("\nNode with key 38 not found.");
        }

        // Find and delete a specific node (e.g., node with key 41)
        Node nodeToDelete_41 = tree.findNode(tree.root, 41);
        if (nodeToDelete_41 != tree.NIL) {
            tree.RB_DELETE(tree, nodeToDelete_41);
            System.out.println("\nAfter deleting node with key 41:");
            tree.inOrderTraversal(tree.root);
        } else {
            System.out.println("\nNode with key 41 not found.");
        }
    }

    // In-order traversal to print the tree
    private void inOrderTraversal(Node x) {
        if (x != NIL) {
            inOrderTraversal(x.left);
            System.out.print(x.key + " ");
            inOrderTraversal(x.right);
        }
    }

    // Find a node by key
    private Node findNode(Node x, int key) {
        while (x != NIL && key != x.key) {
            if (key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return x;
    }
}
