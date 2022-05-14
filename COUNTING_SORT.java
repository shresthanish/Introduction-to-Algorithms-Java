class COUNTING_SORT {
	
	public static void main(String[] args) {
		int[] A = {2,5,3,0,2,3,0,3};
		int[] B = new int[A.length];
		int k = maxVal(A);
		
		int[] sortedArray = COUNTING_SORT(A, B, k);
		for (int i = 0; i < sortedArray.length; i++) {
			System.out.print(sortedArray[i]+ " ");
		}
	}
	
	public static int[] COUNTING_SORT(int[] A, int[] B, int k)
	{
		int[] C = new int[k+1];
		
		for (int i=0; i<=k; i++) {
			C[i]=0;
		}
		
		for (int j=0; j<A.length; j++) {
			C[A[j]] = C[A[j]]+1;
		}
		
		// C[i] now contains the number of elements equal to i.
		for (int i=1; i<=k; i++) {
			C[i]=C[i]+C[i-1];
		}
		
		// C[i] now contains the number of elements less than or equal to i.
		for (int j = A.length-1; j>=0; j--) {
			B[C[A[j]]-1] = A[j];
			C[A[j]]=C[A[j]]-1;
		}
		return B;
	}
	
	public static int maxVal(int[] A){
		int maximum = A[0];
		for (int i=0; i<A.length; i++) {
			if (A[i]>maximum) {
				maximum = A[i];
			}
		}
		return maximum;
	}
	
}
