
package textbook.ch16;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeckofCards {
   private List<Card> list; // declare List that will store Cards

   // set up deck of Cards and shuffle
   public DeckofCards()   {
      Card[] deck = new Card[52];
      int count = 0; // number of cards

      // populate deck with Card objects
      for (Card.Suit suit : Card.Suit.values()) {
         for (Card.Face face : Card.Face.values()) {
            deck[count] = new Card(face, suit);
            ++count;
         } 
      } 

      list = Arrays.asList(deck); // get List
      Collections.shuffle(list);  // shuffle deck
   } // end DeckofCards constructor

   // output deck
   public void printCards() {
      // display 52 cards in two columns
      for (int i = 0; i < list.size(); i++)
         System.out.printf("%-19s%s", list.get(i),
            ((i + 1) % 4 == 0) ? "\n" : "");
   } 

   public static void main(String[] args) {
      DeckofCards cards = new DeckofCards();
      cards.printCards();
   }   
} // end class DeckofCards

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
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

