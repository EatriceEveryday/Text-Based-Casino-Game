/** A Card object with a name, suit, value and full name.
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2019-01-09 
*/

class Card implements Comparable<Card>{
  
  private String name, suit, fullName;
  private int value;
  
  /** Creates a card with the specified name, value and suit.
   * 
   *  @param name is the name of the card (Ace, 2, 3, etc.).
   *  @param value is the value of the card.
   *  @param suit is the suit of the card (Hearts, Spades, etc.).
   *  @fullName is set to be the combination of name and suit.
   */
  
  public Card (String name, int value, String suit){
    
    this.name = name;
    this.suit = suit;
    this.value = value;
    this.fullName = name + " of " + suit;
    
  }
  
  /** Gets the value of the card.
   *  @returns the value of the card.
   */
  
  public int getValue (){   
    return this.value;   
  }

  /** Gets the name of the card.
   *  @returns the name of the card.
   */

  public String getName(){
    return this.name;    
  }
  
  /** Gets the full name of the card.
   *  @returns the fullName of the card.
   */
  
  public String toString (){  
    return fullName;   
  }

  /**Compares card through value (for PokerAI)
   * @returns the difference of the values
   */

  @Override
  public int compareTo(Card card) {
    return this.value - card.value;
  }
  
}