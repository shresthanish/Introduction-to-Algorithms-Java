public class Quick_Sort {

    public static void QUICKSORT(int[] A, int p, int r) {
        if (p < r) {
            int q = PARTITION(A, p, r);
            QUICKSORT(A, p, q - 1);
            QUICKSORT(A, q + 1, r);
        }
    }

    public static int PARTITION(int[] A, int p, int r) {
        int x = A[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (A[j] <= x) {
                i = i + 1;
                exchange(A, i, j);
            }
        }
        exchange(A, i + 1, r);
        return i + 1;
    }

    public static void exchange(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    public static void main(String[] args) {
        int[] A = { 9, 4, 8, 1, 7, 90, 3, 2, 35, 6, 5 };
        QUICKSORT(A, 0, A.length - 1);

        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
