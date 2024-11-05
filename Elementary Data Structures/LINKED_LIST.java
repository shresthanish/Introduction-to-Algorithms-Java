public class LINKED_LIST {

    public static class Node {
        int key;
        Node prev;
        Node next;

        public Node(int key) {
            this.key = key;
            this.prev = NIL; // Use NIL instead of null
            this.next = NIL; // Use NIL instead of null
        }
    }

    public static final Node NIL = null; // Use NIL as null directly
    Node head;

    public LINKED_LIST() {
        this.head = NIL; // Initialize head to NIL
    }

    // LIST_SEARCH(L, k)
    public Node LIST_SEARCH(LINKED_LIST L, int k) {
        Node x = L.head;
        while (x != NIL && x.key != k) {
            x = x.next;
        }
        return x;
    }
    // The time complexity for LIST_SEARCH is O(n) in the worst case.

    // LIST_PREPEND(L, x)
    public void LIST_PREPEND(LINKED_LIST L, Node x) {
        x.next = L.head;
        x.prev = NIL; // Use NIL instead of null
        if (L.head != NIL) {
            L.head.prev = x;
        }
        L.head = x;
    }

    // LIST_INSERT(x, y)
    public void LIST_INSERT(Node x, Node y) {
        x.next = y.next;
        x.prev = y;
        if (y.next != NIL) {
            y.next.prev = x;
        }
        y.next = x;
    }

    // The time complexity for LIST_INSERT is O(1).

    // LIST_DELETE(LinkedList L, Node x)
    public void LIST_DELETE(LINKED_LIST L, Node x) {
        if (x.prev != NIL) {
            x.prev.next = x.next; // Link previous node to the next node
        } else {
            L.head = x.next; // If x is the head, update head to the next node
        }
        if (x.next != NIL) {
            x.next.prev = x.prev; // Link next node to the previous node
        }
    }

    // LIST_DELETE(LinkedList L, int key)
    public void LIST_DELETE(LINKED_LIST L, int key) {
        Node x = LIST_SEARCH(L, key); // Find the node with the specified key
        if (x != NIL) { // If the node was found
            LIST_DELETE(L, x); // Delete the node
        }
    }

    private void printList() {
        Node current = head;
        while (current != NIL) {
            System.out.print(current.key + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LINKED_LIST L = new LINKED_LIST();
        Node n1 = new Node(1);
        Node n4 = new Node(4);
        Node n16 = new Node(16);
        Node n9 = new Node(9);

        L.LIST_PREPEND(L, n1);
        L.LIST_PREPEND(L, n4);
        L.LIST_PREPEND(L, n16);
        L.LIST_PREPEND(L, n9);

        System.out.print("Linked List: ");
        L.printList();

        Node n25 = new Node(25);
        L.LIST_PREPEND(L, n25);
        System.out.print("Linked List after insertions: ");
        L.printList();

        Node n36 = new Node(36);
        L.LIST_INSERT(n36, n9);
        System.out.print("Linked List after insertions: ");
        L.printList();

        // Delete a node by key
        L.LIST_DELETE(L, 4);
        System.out.print("Linked List after deletion of 4: ");
        L.printList();

        Node new4 = new Node(4);
        L.LIST_INSERT(new4, n16);
        System.out.print("Linked List after inserting new 4: ");
        L.printList();

        // Delete the newly inserted node by reference
        L.LIST_DELETE(L, new4.key);
        System.out.print("Linked List after deletion of new 4: ");
        L.printList();
    }
}
