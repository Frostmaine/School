
package recursion;

/**
 *
 * @author cjones
 */
public class RecursionExamples1 {
    public static long factorialFunction(int n){//not tested
        if (n<=1) return 1; // Base case - -always do first 
        return n*factorialFunction(n-1); 
    }
    
    public static long factorialIteration(int n){//not tested
        if (n<=1) return 1;
        long total = 1;
        for(int i = 2; i<=n; i++) total *= i;
        return total;
    }
    
    public static long fibonacciFunction(int n){
        if( n<=0) return 0; 
        if (n <=2) return 1;
        //return fibonacciFunction(n-1) + fibonacciFunction(n-2);
        long value1 = fibonacciFunction(n-1);
        long value2 = fibonacciFunction(n-2);
        return value1+value2;
    }
    
        public static long fibonacciIteration(int n){ //not tested
        if( n<=0) return 0; 
        if (n <=2) return 1;
        long nMinus2 = 0;
        long nMinus1 = 1;
        long N = 1; 
        for(int i=2; i<=n; i++){
            N= nMinus1+nMinus2; // current answer for this value of i
            //set up for the next iteration
            nMinus2 = nMinus1;  
            nMinus1 = N;
        }
        return N;
    }
        
    public static void doWork(int n){
        if(n ==0)return; //Base case - always check for them first
        System.out.print(n%10);
        doWork(n/10);
    }
    
    public static void printReverse(int n){
       //First make sure we need recursion
        if(n<0) {
            n= -n;
            System.out.print("-");
        }
        else  if (n<10){
                 System.out.println(n);
                return;
              }
       //Setup the recursion 
       // call recursive routine and then cleanup; 
       doWork(n);
       System.out.println();
    }
    
    public static void merge(int A[], int start,int mid,int end){ 
        //merge sorted elelments A[start] to A[mid] with A[mid+1] to A[end]
        int firstLoc = start;
        int secondLocation =mid+1;
        int tempArray[] = new int[end-start + 1];
        int numberOfElements = end-start + 1;
        int tempLoc = 0;
        while (firstLoc <=mid && secondLocation <=end){
            if (A[firstLoc] <= A[secondLocation]){
                tempArray[tempLoc++] = A[firstLoc++];
            }else {
                tempArray[tempLoc++] = A[secondLocation++];
            }
        }
        //Only 1 of the following 2  loops will execute -- why?
        while (firstLoc <=mid){
            tempArray[tempLoc++] = A[firstLoc++];
        }
        while(secondLocation <=end){
            tempArray[tempLoc++] = A[secondLocation++];
        }
        for(int i = 0; i<numberOfElements; i++){
            A[start+i] =tempArray[i];
        }
    }
    
    public static void mSort(int A[], int start, int end){ 
        int midPoint = (end + start)/2;
        if (end==start ) return; // 1 element arrays are sorted -- base case
        mSort(A,start, midPoint);
        mSort(A,midPoint+1, end);
        merge(A,start, midPoint,end); // merge these two sorted sub-arrays
        //No work to split problem, all work to combine solutions 
    }
    
    public static void mergeSort(int A[]){
        mSort(A,0,A.length-1); // Set up the recursion
    }
    
   // Partition puts all elements less than A[pivotIndex] on left of index k. >= on right starting at index k.  
    public static int partition(int A[], int i, int j, int pivotValue){
         //return a k such that A[i] to A[k-1] < pivotValue and A[k] to A[j] >= pivotValue
        int leftIndex = i;
        int rigthIndex = j; 
        while (leftIndex<=rigthIndex){
            swap(A,leftIndex,rigthIndex);
            while(A[leftIndex] < pivotValue) leftIndex++;
            while(A[rigthIndex] >= pivotValue) rigthIndex--;
        }
        return leftIndex;
    }
    
    public static void swap(int A[], int i,int j){
        int temp = A[i];
        A[i] = A[j];
        A[j]=temp;
    }
    public static int findPivotIndex(int A[],int i,int j){ 
       if(j-i <1) return i-1; // only 1 location - already partitioned
       int k = i+1;
       while (k<=j && A[k] == A[i])k++;
       if (k<= j){   // found 2 different values
        if(A[i] > A[k]) return i;
        else if(A[i] < A[k])return k;
        else return j+1;
       } 
       else
           return j+1; //all the same
           
    }
    
    //Sort locations i to j in the array A 
    public static void quickSort(int A[], int i, int j){
        if (j==i) return; // 1 element arrays are sorted -- base case
        /*
            //Normally a second base case CUTOFF = 100 or so
             if (j-i <= CUTOFF){
                selectionSort(A,i,j);
                return; 
            }
         */
        int pivotIndex = findPivotIndex(A,i,j); //what element to pivot on
        if (pivotIndex >= i && pivotIndex <=j){ // we have a pivot element
            int pivotValue = A[pivotIndex];
            //returns start of group on right
            int k = partition(A, i,j,pivotValue);// work to split problem 
            quickSort(A,i,k-1);
            quickSort(A,k,j);
            //no work needed to combine solutions
        }
    }

    public static void qSort(int [] A){
        quickSort(A,0,A.length-1); //setup the recursion
    }

    
    public static void main(String[] args){
        System.out.println("The fib(6) = " + fibonacciFunction(6));
        printReverse(1234);
        printReverse(0);
        printReverse(9);
        printReverse(-110);
        printReverse(-9);
        printReverse(-0);
        //test sorting algorithms
        int A[] = new int [30];
        for (int i=0; i<25; i++){
          A[i] = 30-i; 
        }
        for (int i=25; i<A.length; i++){
          A[i] = 5; 
        }
        System.out.print("Array to start [ ");
        for (int i=0; i<A.length; i++){
            System.out.print(A[i]+", ");
        }
        System.out.println("]");
        //mergeSort(A);
        qSort(A);
        System.out.print("After sorting the array is [ ");
        for (int i=0; i<A.length; i++){
            System.out.print(A[i]+", ");
        }
        System.out.println("]");
        System.out.println("Calling Quicksort on a sorted array]");
        qSort(A);
        System.out.print("[ ");
        for (int i=0; i<A.length; i++){
            System.out.print(A[i]+", ");
        }
        System.out.println("]");
    }
}
