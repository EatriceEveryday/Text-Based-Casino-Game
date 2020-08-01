/** A Deck object that contains a Card object arrays called deck.
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-09 
*/
import java.util.*;

class Deck{
  
  private ArrayList<Card> deck = new ArrayList<Card>();
  
  /** Creates a deck which contains each card from Ace of Diamonds to King of Spades in order of suits. For Blackjack
   *  @Values represent their value in BlackJack
   */
  
  public Deck (String bj){
    
    String[] names = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    String[] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};
    int [] nums = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    
    for (int i = 0; i < 4; i++){
      for (int a = 0; a < 13; a++){
        this.deck.add(new Card(names[a], nums[a], suits[i]));
      }
    }
  }

  /** Creates a deck which contains each card from 2 of Diamonds to King of Spades in order of strength. For Poker
   *  @Values represents their strength in high card
   *  @Divide by 4 to get their name
   *  @Module by 4 to get their suit
   */

  public Deck (){
    
    String[] names = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    String[] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};
    
    for (int i = 0; i < 13; i++){
      for (int a = 0; a < 4; a++){
        this.deck.add(new Card(names[i], i*4 + a, suits[a]));
      }
    }
  }
  
  /** Draws a card from the deck.
   *  
   *  @Saves the first card in topDeck.
   *  @Changes the array to exclude the first index.
   *  @Returns topDeck.
   */

  public Card draw () {
    
    Card topDeck = this.deck.get(0);
    this.deck.remove(0);   
    return topDeck;
    
  }
  
  /** Shuffles the deck.
   *  
   *  @For each index:
   *  @Generates a random index within the deck.
   *  @Switches the cards in the two indexes.
   */
  
  public void shuffle (){
    
    int index;
    Card tempCard;
    
    for (int i = 0; i < this.deck.size(); i++){
      
      index = (int)(Math.random()*this.deck.size());
      
      tempCard = this.deck.get(i);
      this.deck.set(i, this.deck.get(index));
      this.deck.set(index, tempCard);
      
    }
    
  }
  
  /** Adds a card into the deck.
   *  
   */
  
  public void addCard (Card card){
    
    this.deck.add(card);
    
  }
  
}