/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recursion;

/**
 *
 * @author cjones
 */
public class Test {
    public static void main(String Args[]){
        int result = (int) Math.pow(2, 32);
        for(int i = 1; i<=10; i++){
            System.out.println(result++);
        }
          
        
    }
}
