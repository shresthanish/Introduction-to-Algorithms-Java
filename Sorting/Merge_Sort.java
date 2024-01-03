public class Merge_Sort {
    public static void MERGE(int[] A, int p, int q, int r) {
        int nL = q - p + 1;
        int nR = r - q;

        int[] L = new int[nL];
        int[] R = new int[nR];

        for (int i = 0; i < nL; i++)
            L[i] = A[p + i];
        for (int j = 0; j < nR; j++)
            R[j] = A[q + 1 + j];

        int i = 0, j = 0;

        int k = p;
        while (i < nL && j < nR) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i = i + 1;
            } else {
                A[k] = R[j];
                j = j + 1;
            }
            k = k + 1;
        }

        while (i < nL) {
            A[k] = L[i];
            i = i + 1;
            k = k + 1;
        }

        while (j < nR) {
            A[k] = R[j];
            j = j + 1;
            k = k + 1;
        }
    }

    public static void MERGE_SORT(int[] A, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            MERGE_SORT(A, p, q);
            MERGE_SORT(A, q + 1, r);
            MERGE(A, p, q, r);
        }
    }

    public static void main(String[] args) {
        int[] A = { 9, 4, 8, 1, 7, 90, 3, 2, 35, 6 };
        System.out.print("Intput: ");
        print(A);
        MERGE_SORT(A, 0, A.length - 1);
        System.out.print("Output: ");
        print(A);
    }

    public static void print(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.printf("%4d", A[i]);
            if (i != A.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

}
