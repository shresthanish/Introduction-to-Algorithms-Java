class SORTING {
	public static void main(String[] args) {
		int A[] = {235,4,3,2,41};
		INSERTION_SORT(A);
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]+" ");
		}
		
	}
	
	public static void INSERTION_SORT(int A[]){
		for (int j=1; j<A.length; j++) {
			int key = A[j];
			int i = j-1;
			while (i>=0 && A[i]>key) {
				A[i+1] = A[i];
				i = i-1;
			}
			A[i+1] = key;
		}
	}
	
}
