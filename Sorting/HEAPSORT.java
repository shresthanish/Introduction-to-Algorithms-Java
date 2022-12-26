public class HEAPSORT {

    private static final int PARENT(int i){
        return (i-1)/2;
    }

    private static final int LEFT(int i){
        return 2*i+1;
    }
    private static final int RIGHT(int i){
        return 2*i+2;
    }

    public static void exchange(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void maxHeapify(int[] A, int i, int heapSize){
        int l = LEFT(i);
        int r = RIGHT(i);
        int largest = i;
        if (l<= heapSize && A[l]>A[i]){
            largest = l;
        }
        if (r<= heapSize && A[r]>A[largest]){
            largest=r;
        }
        if (largest != i){
            exchange(A, i, largest);
            maxHeapify(A, largest,heapSize);
        }
    }
    public static void buildMaxHeap(int[] A){
        int heapSize = A.length-1;
        for (int i = heapSize/2; i>=0; i--){
            maxHeapify(A,i,heapSize);
        }
    }

    public static void HeapSort(int[] A) {
        int heapSize = A.length-1;
        buildMaxHeap(A);
        for (int i = A.length - 1; i > 0; i--) {
            exchange(A,0,i);
            heapSize = heapSize - 1;
            maxHeapify(A, 0,heapSize);
        }
    }
    
    //Testing
    public static void main(String[] args) {
        int[] A = {4,1,3,2,16,9,10,14,8,7,5};
        //Sorting array A
        HeapSort(A);
        for (int k : A) {
            System.out.print(k+ " ");
        }
    }

}
