package exceptions;

// Exercise 11.16 Solution: Demo.java
// Catching subclass exceptions.

public class Demo {
   public static void main(String[] args) {
      try { // throw ExceptionC 
         throw new ExceptionC();
      } 
      catch (ExceptionA exception1) { // catch ExceptionA and subclasses 
         System.err.println("First Exception subclass caught. \n");
      } 
      
      try { // throw ExceptionB 
         throw new ExceptionB();
      }
      catch (ExceptionA exception2) { // catch ExceptionA and subclasses 
         System.err.println("Second Exception subclass caught. \n");
      } 
   } 
} 

// exception subclasses
class ExceptionA extends Exception {
   // empty class body
}

class ExceptionB extends ExceptionA {
   // empty class body
} 

class ExceptionC extends ExceptionB {
   // empty class body
} 

/**************************************************************************
 * (C) Copyright 1992-2005 by Deitel & Associates, Inc. and               *
 * Prentice Hall. All Rights Reserved.                                    *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
