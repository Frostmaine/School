
package textbook.ch16;

// Fig. 16.10: DeckOfCards.java

class Card {   
   public static enum Face {Ace, Deuce, Three, Four, Five, Six,
      Seven, Eight, Nine, Ten, Jack, Queen, King};
   public static enum Suit {Clubs, Diamonds, Hearts, Spades};

   private final Face face; 
   private final Suit suit;
   
   // constructor
   public Card(Face face, Suit suit) {  
       this.face = face;
       this.suit = suit; 
   } 
   
   // return face of the card
   public Face getFace() {
      return face; 
   } 

   // return suit of Card
   public Suit getSuit() {
      return suit; 
   } 

   // return String representation of Card
   public String toString() {
      return String.format("%s of %s", face, suit);
   } 
} // end class Card
