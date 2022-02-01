
package assignments.assignment4;


/**
 *
 * @author cjones
 */
public class Assignment4 {
    
    
    public static void main(String[] args)  {
     int n = 10;
     for(int i=50; i<51; i++)
         System.out.println("For n= " +i+ ", the result is "+ G(i,0,1));
     
     System.out.println("C(5,3) is "+C(5,3));
     System.out.println("C(5,5) is "+C(5,5));
     
     System.out.println("power(2,-2) is "+ power(2,-2));
     System.out.println("power(2,-3) is "+ power(2,-3));
     System.out.println("power(2,13) is "+ power(2,13));
     System.out.println("power(2,14) is "+ power(2,14));
     
     System.out.println("DigitSum(-375) is "+ digitSum(-375));
     System.out.println("DigitSum(1375) is "+ digitSum(1375));
     System.out.println("DigitSum(0) is "+ digitSum(0));
     
     System.out.println("The binary Representation of 7 is " + binaryRepresentation(7));
     System.out.println("The binary Representation of 8 is " + binaryRepresentation(8));
     System.out.println("The binary Representation of 12 is " + binaryRepresentation(12));
     System.out.println("The binary Representation of 10 is " + binaryRepresentation(10));
     System.out.println("The binary Representation of 102325 is " + binaryRepresentation(102325));
     
     System.out.println("The number of ones in the binary representation of 7 is " + countOnes(7));
     System.out.println("The number of ones in the binary representation of 8 is " + countOnes(8));
     System.out.println("The number of ones in the binary representation of 12 is " + countOnes(12));
     System.out.println("The number of ones in the binary representation of 10 is " + countOnes(10));
     System.out.println("The number of ones in the binary representation of 102325 is " + countOnes(102325));
    }
    
    private static long G(int n, long a, long b){
        if(n<=0) return a;
        return G(n-1, b, a+b);
    }
    
    private static long C(int n, int k){
        if(n<0 || k<0 || k>n) throw new IllegalArgumentException("Illegal Values for n or k. n is "+n+" k is "+k);
        if(k==0) return 1;
        if(k==n) return 1;
        return C(n-1,k-1) + C(n-1,k);
    }
    
    public static double power(int x, int n){
        if (n ==0) return 1;
        if (n == 1) return x;
        if (n>0)return pow(x,n);
        return 1.0/ pow(x,-n);
        
    }

    private static long pow2(int x, int n) {
       if(n==1) return x;
       int mid = n/2;
       return pow2(x,n/2)*pow(x,n-mid);
    }
   
    private static long pow(int x, int n) {
       if(n==1) return x;
       if (n% 2==1) return pow(x,n-1)*x;
       int mid = n/2;
       return pow2(x,n/2)*pow(x,n-mid);  
    }
    
    public static long digitSum(int n){
        if (n<0)return dSum(-n);
        if(n<10) return n;
        return dSum(n);
        
    }

    private static long dSum(int n) {
        if(n<10) return n;
        return (n%10) + dSum(n/10);
    }
    
   public static String binaryRepresentation(int n){
       if (n<0)return "-"+bRep(-n);
       if (n==0) return "0";
       if (n==1) return "1";
       return bRep(n);
   }
    
   private static String bRep(int n){
       if (n==0) return "0";
       if (n==1) return "1";
       return bRep(n/2)+bRep(n%2);
   } 

   public static int countOnes(int n){
      if (n<0)return cOnes(-n);
       if (n==0) return 0;
       if (n==1) return 1;
       return cOnes(n);
       
   }

    private static int cOnes(int n) {
       if (n==0) return 0;
       if (n==1) return 1;
       return cOnes(n/2)+cOnes(n%2);
    }
   
}
