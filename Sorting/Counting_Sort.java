public class Counting_Sort {

    public static void COUNTING_SORT(int[] A, int[] B, int k) {
        int[] C = new int[k + 1];

        for (int i = 0; i <= k; i++) {
            C[i] = 0;
        }

        for (int j = 0; j < A.length; j++) {
            C[A[j]]++;
        }

        for (int i = 1; i <= k; i++) {
            C[i] = C[i] + C[i - 1];
        }

        for (int j = A.length - 1; j >= 0; j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]] = C[A[j]] - 1;
        }
    }

    public static int MAXIMUM(int[] A) {
        int max = A[0];

        for (int i = 1; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] A = { 2, 5, 3, 0, 2, 3, 0, 3 };
        int[] B = new int[A.length];
        int k = MAXIMUM(A);

        COUNTING_SORT(A, B, k);

        System.out.print("Sorted array: ");
        for (int num : B) {
            System.out.print(num + " ");
        }
    }

}
