package exceptions;


public class Demo5 {
   public static void main(String[] args) {
      try { // call method someMethod 
         someMethod();
      } 
      catch (ExceptionB exception) {
         System.err.printf("Handled in main: %s", exception);
      }
      catch (Exception exception) {
         exception.printStackTrace();
      } 
      finally {
          //This code gets executed no mater what 
      }
   }

   // call method someMethod2
   public static void someMethod() throws Exception {
      try {
         someMethod2();
      }
      catch (ExceptionA exception) { // only catches ExceptionA 
         System.err.printf(
            "Handled execeptionA in someMethod: ", exception);
      } 
   } 

   // throw Exception
   public static void someMethod2() throws Exception {
      throw new ExceptionB();
   } 
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