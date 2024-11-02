// Queue.java
public class Queue {
    private int[] array;
    private int head;
    private int tail;
    private int size;

    // Constructor
    public Queue(int n) {
        array = new int[n];
        this.size = n;
        head = 0;
        tail = 0;
    }

    // ENQUEUE(Q,x)
    public static void ENQUEUE(Queue Q, int x) {
        if (Q.head == Q.tail + 1 || Q.head == 0 && Q.tail == Q.size - 1)
            throw new RuntimeException("queue overflow");

        Q.array[Q.tail] = x;
        if (Q.tail == Q.size - 1)
            Q.tail = 0;
        else
            Q.tail = Q.tail + 1;
    }

    // DEQUEUE(Q)
    public static int DEQUEUE(Queue Q) {
        if (Q.head == Q.tail)
            throw new RuntimeException("queue underflow");

        int x = Q.array[Q.head];
        if (Q.head == Q.size - 1)
            Q.head = 0;
        else
            Q.head = Q.head + 1;
        return x;
    }
}
