
package textbook.ch16;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cjones
 */
public class ListExample1 {
    public static void main(String [] args){
        List <Integer> list = new LinkedList<>();
        list.add(10); list.add(20); list.add(0,5); list.add(3,40);
        list.add(2,15); 
        list.remove(new Integer(20)); //what would list.remove(20) do? 
        list.add(3,30); list.add(list.size(),50); list.add(100);
        
        System.out.println("The elements in the list are:");
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        
        Collections.reverse(list);
        System.out.println("\nThe elements in the reversed list are:");
         it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    
}
