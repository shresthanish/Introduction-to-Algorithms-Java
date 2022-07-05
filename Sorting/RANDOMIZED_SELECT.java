class Sorting {
	
	public static void main(String[] args) {
		
		//The following code for RANDOMIZED-SELECT returns the ith smallest element of the array A[p..r];
		//For example, if you sort array A, it's 10th element is 35.
		
		int[] A = { 9, 4, 8, 1, 7, 90, 3, 2, 35, 6,5};
		System.out.print(RANDOMIZED_SELECT(A, 0, A.length-1, 10));
	}
	
	public static int RANDOMIZED_SELECT(int A[], int p, int r, int i){
		if (p == r) {
			return A[p];
		}
		int q = RANDOMIZED_PARTITION(A, p, r);
		int k = q-p+1;
		
		if (i==k) {
			return A[q];
		}
		else if (i<k) {
			return RANDOMIZED_SELECT(A, p, q-1, i);
		}
		else {
			return RANDOMIZED_SELECT(A, q+1, r, i-k);
		}
	}
	
	public static int RANDOMIZED_PARTITION(int A[], int p, int r){
		int i = RANDOM(p,r);
		exchange(A, r, i); 
		return PARTITION(A, p, r);
	}
	
	public static void RANDOMIZED_QUICKSORT(int A[],int p,int r){
		if (p<r) {
			int q = RANDOMIZED_PARTITION(A, p, r);
			RANDOMIZED_QUICKSORT(A, p, q-1);
			RANDOMIZED_QUICKSORT(A, q+1, r);
		}
	}
	
	public static int PARTITION(int A[], int p, int r){
		int x = A[r];
		int i = p-1;
		for (int j = p; j<r; j++) {
			if (A[j] <= x) {
				i = i+1;
				exchange(A, i, j);
			}
		}
		exchange(A, i+1, r);
		return i+1;
	}
	
	public static void exchange(int A[], int i, int j){
		int tempValue = A[i];
		A[i] = A[j];
		A[j] = tempValue;
	}
	
	public static int RANDOM(int p, int r){
		
		//Sends a random integer between 'p' and 'r'
		int randomValue = (int)(Math.random()*(r-p))+p;
		return randomValue;
	}
}
