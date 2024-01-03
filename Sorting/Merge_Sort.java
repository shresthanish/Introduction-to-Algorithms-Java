class Sorting {
	public static void main(String[] args) {
		int[] A = { 9, 4, 8, 1, 7, 90, 3, 2, 35, 6};
		MERGE_SORT(A, 0, A.length-1);
		
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]+" ");
		}
	}
	
	public static void MERGE(int[] A, int p, int q, int r){
		int n1 = q-p+1;
		int n2 = r-q;
		
		int[] L = new int[n1];
		int[] R = new int[n2];
		
		for (int i = 0; i < n1; i++)
			L[i] = A[p+i];
		
		for (int j = 0; j < n2; j++)
			R[j] = A[q+j+1];
		
		for(int i = 0, j = 0, k = p; k <= r; k++) {
			if (i == n1) {
				A[k] = R[j];
				j=j+1;
				
			} else if (j == n2) {
				A[k] = L[i];
				i= i+1;
				
			} else if (L[i] <= R[j]) {
				A[k]=L[i]; 
				i= i+1;
				
			} else {
				A[k]=R[j];
				j= j+1;
			}
		}
	}
	
	public static void MERGE_SORT(int[] A, int p, int r){
		if (p<r) {
			int q = (p+r)/2;
			MERGE_SORT(A, p, q);
			MERGE_SORT(A, q+1, r);
			MERGE(A, p, q, r);
		}
	}
	
}
