package assignment_4;

/**
 * 
 * @author frostmaine
 */
public class example_generic {

    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {
        Integer x[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(countOdds(x));
    }
    
    public static <T extends Integer> int countOdds(T[] nums)
    {
        int counter = 0;
        for (T num : nums)
            if (num % 2 == 1) counter++; // banking on an integer
        return counter;
    }
    
}
