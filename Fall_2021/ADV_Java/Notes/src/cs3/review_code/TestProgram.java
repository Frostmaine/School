
package cs3.review_code;

import java.time.LocalDateTime;

/**
 *
 * @author cjones
 */
public class TestProgram {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        Thread.sleep(5000);
        String time = now.toString();
        System.out.println(time);
        LocalDateTime later = LocalDateTime.parse(time);
        if(now.equals(later)){
            System.out.println("They are equal");
        }
        System.out.println(later);
        
    }
    
}
