public class LinkedList {

    public static class Node {
        int key;
        Node prev;
        Node next;

        public Node(int key) {
            this.key = key;
            this.prev = null;
            this.next = null;
        }
    }

    Node head;

    public LinkedList() {
        this.head = null;
    }

    //LIST_SEARCH(L, k)
    public Node LIST_SEARCH(LinkedList L, int k) {
        Node x = L.head;
        while (x != null && x.key != k) {
            x = x.next;
        }
        return x;
    }
    // To search a list of n objects, the LIST-SEARCH procedure takes O(n) time in the worst case, since it may have to search the entire list.

    //LIST_INSERT(L,x)
    public void LIST_PREPEND(LinkedList L, Node x) {
        x.next = L.head;
        x.prev = null;
        if (L.head != null) {
            L.head.prev = x;
        }
        L.head = x;
    }

    //LIST_INSERT(x,y)

    public void LIST_INSERT(Node x, Node y) {
        x.next = y.next;
        x.prev = y;
        if (y.next != null) {
            y.next.prev = x;
        }
        y.next = x;
    }

    // The running time for LIST_INSERT on a list of n elements is O(1).

    //LIST_DELETE(L,x)
    public void LIST_DELETE(LinkedList L, int key) {
        Node x = LIST_SEARCH(L, key);
        if (x.prev != null) {
            x.prev.next = x.next;
        } else {
            L.head = x.next;
        }
        if (x.next != null) {
            x.next.prev = x.prev;
        }

    }

    public void LIST_DELETE(LinkedList L, Node x) {
        if (x.prev != null) {
            x.prev.next = x.next;
        } else {
            L.head = x.next;
        }
        if (x.next != null) {
            x.next.prev = x.prev;
        }

    }

    private static void printList(LinkedList L) {
        Node current = L.head;
        while (current != null) {
            System.out.print(current.key + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList L = new LinkedList();
        Node n1 = new Node(1);
        Node n4 = new Node(4);
        Node n16 = new Node(16);
        Node n9 = new Node(9);

        L.LIST_PREPEND(L, n1);
        L.LIST_PREPEND(L, n4);
        L.LIST_PREPEND(L, n16);
        L.LIST_PREPEND(L, n9);

        System.out.print("Linked List: ");
        L.printList(L);

        Node n25 = new Node(25);
        L.LIST_PREPEND(L, n25);
        System.out.print("Linked List after insertions: ");
        L.printList(L);

        Node n36 = new Node(36);
        L.LIST_INSERT(n36, n9);
        System.out.print("Linked List after insertions: ");
        L.printList(L);

        L.LIST_DELETE(L, 4);
        System.out.print("Linked List after deletion: ");
        L.printList(L);

        Node new4 = new Node(4);
        L.LIST_INSERT(new4, n16);
        System.out.print("Linked List after insertions: ");
        L.printList(L);

        L.LIST_DELETE(L,new4);
        System.out.print("Linked List after deletion: ");
        L.printList(L);

    }
}

