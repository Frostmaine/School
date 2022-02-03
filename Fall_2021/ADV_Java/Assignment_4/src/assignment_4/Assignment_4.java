package assignment_4;

/**
 *
 * @author frostmaine
 */
public class Assignment_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        for (int i = 0; i < 16; i++) // count ones test
            System.out.println(i + " : " + countOnes(i));
        
        for (int i = 0; i < 16; i++) // binaryRepresentation test
            System.out.println(i + " : " + binaryRepresentation(i));
        
        System.out.println("123456789 : " + digitSum(231987492)); // digit sum test
        
        // cube all numbers
       for (int i = -10; i <= 10; i++)
            System.out.println(i + "^3 = " + power(i, 3));
       
       // test powers of 2
        for (int i = 0; i < 31; i++)
            System.out.println("2^" + i + " = " + power(2, i));
        
        for (int i = 0; i < 10; i++) // C(n, k) test
            for (int j = 0; j < 10; j++)
            {
                if (j > i)
                    continue;
                System.out.println(i + " choose " + j + " = " + C(i, j));
            }
    }
    
    /**
     * Counts the 1's in the binary representation of the integer n.
     */
    public static int countOnes(int n)
    {
        if (n <= 0) // base case
            return 0;
        return n % 2 == 1 ? 1 + countOnes(n/2) : countOnes(n/2); //recursive case
    }
    
    /**
     * Similarly to above we divide until we hit zero and then append to the front
     * of a string
     * @param n the integer being calculated
     * @return the binary representation
     */
    public static String binaryRepresentation(int n)
    {
        if (n <= 0) // base cases
            return "0";
        if (n == 1)
            return "1";
        // recursive case
        return n % 2 == 1 ? binaryRepresentation(n/2) + "1" : binaryRepresentation(n/2) + "0";
    }
    
    /**
     * Sums up the digits in a given integer n
     * @param n
     * @return 
     */
    public static long digitSum(int n)
    {
        if (n <= 0) // base case
            return 0;
        return (n % 10) + digitSum(n/10); // Recursive case
    }
    
    /**
     * (Note: Used double here because I felt it made more sense than long)
     * Recursively calculates x^n
     * @param x the base 
     * @param n the power
     * @return 
     */
    public static double power(double x, int n)
    {
        if (n == 0) // base case
            return 1.0;
        if (n < 0) // flip the power (start of recursive case)
            return 1.0 / power(x, Math.abs(n));
        if (n % 2 == 0)
            return power(x, n/2) * power(x, n/2);
        return power(x, n-1) * x;
    }
    
    /**
     * Calculates the number of combinations of k things in a set of n things
     * @param n the total group
     * @param k the number of objects selected from the group
     * @return 
     */
    public static int C(int n, int k)
    {
        if (k <= 0 || n == k) // base case
            return 1;
        return C(n-1, k-1) + C(n-1, k); // recursive case
    }
}
