class Insertion_Sort {
	public static void main(String[] args) {
		int A[] = { 235, 4, 3, 2, 41 };
		System.out.print("Intput: ");
		print(A);
		INSERTION_SORT(A);
		System.out.print("Output: ");
		print(A);
	}

	public static void INSERTION_SORT(int A[]) {
		for (int j = 1; j < A.length; j++) {
			int key = A[j];
			int i = j - 1;
			while (i >= 0 && A[i] > key) {
				A[i + 1] = A[i];
				i = i - 1;
			}
			A[i + 1] = key;
		}
	}

	public static void print(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.printf("%4d", A[i]);
			if (i != A.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
}
