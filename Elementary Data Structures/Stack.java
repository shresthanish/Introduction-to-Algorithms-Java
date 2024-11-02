// Stack.java
public class Stack {
    private int[] array;
    private int top;
    private int size;

    // Constructor
    public Stack(int n) {
        array = new int[n];
        this.size = n;
        top = -1;  // Initialize top to -1 to indicate empty stack
    }

    // STACK-EMPTY(S)
    public static boolean STACK_EMPTY(Stack S) {
        if (S.top == -1)
            return true;
        else
            return false;
    }

    // PUSH(S,x)
    public static void PUSH(Stack S, int x) {
        if (S.top == S.size - 1)
            throw new RuntimeException("overflow");
        S.top = S.top + 1;
        S.array[S.top] = x;
    }

    // POP(S)
    public static int POP(Stack S) {
        if (STACK_EMPTY(S))
            throw new RuntimeException("underflow");
        S.top = S.top - 1;
        return S.array[S.top + 1];
    }
}
