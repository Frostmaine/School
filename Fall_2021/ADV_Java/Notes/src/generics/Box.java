
package generics;

import solutions.assignment3.Employee;
import solutions.assignment3.Person;



/**
 *
 * @author cajones
 */
public class Box<T> {
    private T t; 
    public void set(T t) { this.t = t; } 
    public T get() { return t; }
    
    public static void main(String[] args){
        //3 versions of Box created by compiler
        Box<String> myStringBox = new Box<>();
        Box<Integer> myIntBox = new Box<>();  // primitive types are not allowed \
        Box<Person> myPersonBox = new Box<>();
        myIntBox.set(323); //Autoboxing at work 
        myStringBox.set("test"); 
        myPersonBox.set(new Employee());//Inheritance at work 
    }
}

