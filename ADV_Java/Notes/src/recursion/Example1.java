
package recursion;

/**
 *
 * 
 */
public class Example1 {
    public static void main(String[] args)  {
     int N = 10;
     for(int i=47; i<51; i++)
      System.out.println("For n= " +i+ ", the result is "+ G(i,0,1));
   }
    
    private static long G(int n, long a, long b){
        if(n<=0) return a;
        return G(n-1, b, a+b);
    }
}


