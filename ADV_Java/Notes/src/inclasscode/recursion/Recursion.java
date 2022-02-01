package inclasscode.recursion;

/**
 *
 * @author 
 */
public class Recursion {
    public static int F(int n){
        if(n<=10) return 10;
        return F(n-1) + 5;
    }
    private static int a;
    private static int b;
    private static int c;
    private static int d;
    
    public static int G(int n, int a ,int b, int c, int d){
        if(c<=0) throw 
  new IllegalArgumentException("Value for c can't be zero or negative, value for c is "+c); 
        Recursion.a = a;
        Recursion.b = Math.abs(b);
        Recursion.c = c;
        Recursion.d = d;
        return cleanG(n);
        
        
    }

    private static int cleanG(int n) {
        if(n<= b)return a;
        return cleanG(n-c)+d;
    }
    
    /**
    Computes a raised to the nth power
    */
    
    
    public static long  power(int a, int n){
        if (n<0) throw new IllegalArgumentException("Value for N can't be negative, N is "+n); // could be in setup routine 
        if (n==2) return a*a;
        if (n==1) return a;
        if (n==0) return 1; 
        int midPoint = n/2; 
        return power(a, midPoint) * power(a,n-midPoint); 
    }
    
    public static void main(String [] args){
        System.out.println("F(3) is "+ F(3));
        System.out.println("F(15) is "+ F(15));
        System.out.println("F(-25) is "+ F(-25));
        System.out.println("G(5,3,2,2, 2) is "+ G(5,3,2,2, 2));
        try{
            System.out.println("G(5,3,2,-1, 2) is "+ G(5,3,2,-1, 2));
        }catch (IllegalArgumentException e){
            System.out.println ("G(5,3,2,-1, 2) throw an illegal argument exception" );
            System.out.println ("Exception messsage is: "+ e.getMessage());
        }
        System.out.println("power(2,5) is "+power(2,5));
        System.out.println("power(2,9) is "+power(2,9));
        System.out.println("power(2,9) is "+power(2,10));
    }
 
       
    
}
