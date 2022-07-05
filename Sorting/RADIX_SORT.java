class Sorting {
	public static void main(String[] args) {
		int[] A = {329,457,657,839,436,720,355};
		
		//number of digits
		int d = 1+(int)Math.floor(Math.log10(MAXIMUM(A)));
		
		int[] storedArray = RADIX_SORT(A,d);
		
		for (int i = 0; i < storedArray.length; i++) {
			System.out.print(storedArray[i]+ " ");
		}
	}
	
	public static int[] RADIX_SORT(int[] A,int d){
		
		int[] B = new int[A.length];
		int[] tempArray = new int[A.length];
		int k = MAXIMUM(A);
		
		for (int i = 0; i < d; i++) {
			tempArray = COUNTING_SORT(A, B, k);
		}
		return tempArray;
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
		
		// C[i] now contains the number of elements equal to i.
		for (int i=1; i<=k; i++) {
			C[i]=C[i]+C[i-1];
		}
		
		// C[i] now contains the number of elements less than or equal to i.
		for (int j = A.length-1; j>=0; j--) {
			B[C[A[j]]-1] = A[j];
			C[A[j]]=C[A[j]]-1;
		}
		return B;
	}
	
	public static int MAXIMUM(int[] A){
		int max = A[0];
		for (int i=1; i<A.length; i++) {
			if (A[i]>max) {
				max = A[i];
			}
		}
		return max;
	}
	
}
