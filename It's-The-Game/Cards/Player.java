/** A Playe object that contains that contains a name, age and hand.
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-10-23 
*/
import java.util.*;

class Player {
  
  private double chips;
  private Account account;
  private ArrayList<Card> hand;
  private boolean AI;
  private String status;
  
  /** Creates a blackjack player for the user.
   * 
   *  @account is the player's account
   *  @sets the ammount of chips to 0
   *  @creates an empty card list called hand
   *  @sets AI to false
   */
  
  public Player (Account account){
    
    this.account = account;
    this.chips = 0;
    this.hand = new ArrayList<Card>();
    this.AI = false;
    
  }

  /** Creates a blackjack player for an AI.
   * 
   *  @account is the player's account
   *  @sets the ammount of chips to 0
   *  @creates an empty card list called hand
   *  @sets AI to true
   */

  public Player (String name){
    
    this.account = new Account(name, "12345",500,false);
    this.chips = 0;
    this.hand = new ArrayList<Card>();
    this.AI = true;
    
  }

  /** Creates a poker player for the user.
   * 
   *  @account is the player's account
   *  @chips is the amount of money the player has
   *  @creates an empty card list called hand
   *  @sets AI to false
   */

  public Player (Account account, double chips){
    
    this.account = account;
    this.chips = chips;
    this.hand = new ArrayList<Card>();
    this.AI = false;
    
  }

  /** Creates a poker player for an AI.
   * 
   *  @account is the player's account
   *  @sets the ammount of chips to 0
   *  @creates an empty card list called hand
   *  @sets AI to true
   */

  public Player (String name, double chips){

    this.account = new Account(name, "12345",500,false);
    this.chips = chips;
    this.hand = new ArrayList<Card>();
    this.AI = true;

  }
  
  /** Draws a card from the deck.
   */
  
  public void draw(Deck deck) {

    this.hand.add(0, deck.draw());

  }
  
  /** Returns the hand back into the deck.
   *  Iterates through the hand and adds it to the deck
   *  Clears the hand
   */
  
  public void returnHand (Deck deck){
    
    for (Card card: this.hand){
      deck.addCard(card);
    }

    hand.clear();

  }

  /** Returns the hand
   */

  public ArrayList<Card> getHand (){
    return this.hand;
  }

  /** Returns part of the hand for table in poker
   */

  public ArrayList<Card> getHand (int num){

    ArrayList<Card> newHand = new ArrayList<Card>();

    for (int i = 0; i < num; i++){ //adds num number of cards into the new hand and returns it
      newHand.add(hand.get(i));
    }

    return newHand;

  }

  /** adds a certain amount of chips for poker
   */
  
  public void addChips(double num){
    this.chips += num;
  }

  /** returns chips for poker
   */

  public double getChips(){
    return this.chips;
  }

  /** checks if the player represents an AI for poker
   */

  public boolean isAI(){
    return this.AI;
  }

  /** sets the status for poker (Undecided/Raise/Checks/Folds)
   */
  public void setStatus(String status){
    this.status = status;
  }

  /** returns the status for poker (Undecided/Raise/Checks/Folds)
   */

  public String getStatus(){
    return this.status;
  }
  
  /** returns the account
   */

  public Account getAccount(){
    return account;
  }
  
  /** Returns the black jack version of the information in player
   *  Contains: name, hand and total value of cards
   */

  public String toStringBJ (){
    
    String info = this.account.getUsername() + "\n" + this.hand + "\nTotal: " + calculateBJValue(); 
    return info;
  }

  /** Returns the poker version of the information in player
   *  Contains: name, hand and chips
   */

  public String toStringPoker(){

    String info = this.account.getUsername() + " Chips: $" + this.chips + "\n" + this.hand;
    return info;

  }

  /** Calculates the value of the hand on blackjack standards. Accounts for when aces can be 11 and 1
   */

  public int calculateBJValue(){

    ArrayList<Integer> values = new ArrayList<Integer>();
    int numOfAces = 0;
    int totalValue = 0;

    for (Card card: hand){ //Create an array of values for the hand
      values.add(card.getValue());
    }

    for (int value: values){ //Counts the number of aces in hand
      if (value == 11){
        numOfAces += 1;
      }
    }

    for (int value: values){ //Adds up all the values
      totalValue += value;

      if (numOfAces > 0 && totalValue > 21){ //If the value goes over 21, checks if there are any aces left and if so, treat one as a 1
        totalValue -= 10;
        numOfAces -= 1;
      }

    }

    return totalValue;

  }

  /** Rates the hand and returns a double
   *  
   *  @ <100 is a high card
   *  @ 100+ is a pair
   *  @ 200+ is a 2 pair
   *  @ 300+ is a three of a kind
   *  @ 400+ is a Straight
   *  @ 500+ is a flush
   *  @ 600+ is a full house
   *  @ 700+ is a four of a kind
   *  @ 800+ is a straight-flush
   *  
   */

  public double getRating(ArrayList<Card> table){

    ArrayList<Card> combinedHand = new ArrayList<Card>();

    combinedHand.addAll(this.hand);
    combinedHand.addAll(table);

    Collections.sort(combinedHand);
    Collections.reverse(combinedHand); //Sorted so that its greates to smallest

    int isFlush = checkStraightFlush(combinedHand);

    if (isFlush != -1){
      return 500 + isFlush;
    }

    int isStraight = checkStraight(combinedHand);

    if (isStraight != - 1){
      return 400 + isStraight;
    }

    int highCard = combinedHand.get(0).getValue(); //Represents the highcard at the start of the combo
    int[][] combos = new int[2][2]; //represents all the combos. [numCombo][0 = combo, 1 = highCard]
    int counter = 1; //Represents how many common cards are grouped together (2 = pair for example)

    for (int i = 1; i < combinedHand.size(); i++){

      if (combinedHand.get(i).getValue()/4 == combinedHand.get(i-1).getValue()/4){ //Counts how many common cards are grouped together

        counter += 1;

      } else {
        
        switch (counter){

          case 2:
            for (int a = 0; a < 2; a ++){ //Puts the pair in the first or second combo, if free

              if (combos[a][0] == 0){
                combos[a][0] = 2;
                combos[a][1] = highCard;
                a = 2;
              }
            }
            break;

          case 3:

            if (combos[1][0] < 3){ //Puts the triple in the second combo as long as its weaker than a triple
              combos[1][0] = 3;
              combos[1][1] = highCard;
            }
            break;

          case 4: //Return the rating for a four of a kind
            return 700 + highCard;
        }

        counter = 1; //Reset counter
        highCard = combinedHand.get(i).getValue(); //Reset highCard

      }     

    }

    switch (counter){ //Check once more at the end

      case 2:
        for (int a = 0; a < 2; a ++){ //Puts the pair in the first or second combo, if free

          if (combos[a][0] == 0){

            combos[a][0] = 2;
            combos[a][1] = highCard;
            a = 2;

          }
        }
        break;

      case 3:
        if (combos[1][0] < 3){ //Puts the triple in the second combo as long as its weaker than a triple

          combos[1][0] = 3;
          combos[1][1] = highCard;

        }
        break;

      case 4: //Return the rating for a four of a kind
        return 700 + highCard;
    }

    if (combos[1][0] == 3){ //If theres a triple

      if (combos[0][0] == 2){
        return 600 + combos[1][1] + ((double)(combos[0][1]))/100; //And a double, return rating for a full house
      } else {
        return 300 + combos[1][1]; //And no double, return rating for a triple
      }

    } else if (combos[0][0] == 2){ //If theres a pair

      if (combos[1][0] == 2){
        return 200 + combos[0][1]/4 + ((double)(combos[1][1]))/100; //And another pair, return rating for 2 pairs. Anything after the decimal represents the second pair strength
      } else {
        return 100 + combos[0][1]; //And no other pair, return rating for a pair
      }

    } else { //If you get here, theres only a high card
      return combinedHand.get(0).getValue();
    }

  }

  //Checks if theres a straight and returns the highcard

  private int checkStraight(ArrayList<Card> combinedHand){

    int counter = 0;
    int highCard = combinedHand.get(0).getValue();

    for (int i = 1; i < combinedHand.size(); i++){ //Go through the combinedHand

      if (combinedHand.get(i).getValue()/4 + 1 == combinedHand.get(i - 1).getValue()/4){ //If two cards are consectuive, add one counter
        counter += 1;
      } else if (combinedHand.get(i).getValue()/4 != combinedHand.get(i - 1).getValue()/4){ //As long as the cards are not the same, reset counter (straight is broken)
        counter = 0;
        highCard = combinedHand.get(i).getValue();
      }

      if (counter == 4){ //If the counter hits 5, a straight is made. Return the highest card.
        return highCard;
      }

    }

    if (counter == 3 && combinedHand.get(0).getValue()/4 == 12 && combinedHand.get(combinedHand.size() - 1).getValue()/4 == 0){ //Checks if theres a possibility to make a straight with the ace as a low card

      return highCard;

    }

    return -1; //If it gets here, no straight was made

  }

  //Checks if theres a flush and returns the highcard

  private int checkStraightFlush (ArrayList<Card> combinedHand){

    int[] counters = new int[4];

    for (Card card: combinedHand){

      counters[card.getValue()%4] += 1; //Adds one to their respective suit

      if (counters[card.getValue()%4] == 5){ //When one of the counters hits 5, 

        for (int i = 0; i < combinedHand.size(); i++){ //Remove every card that does not have the suit

          if (combinedHand.get(i).getValue()%4 != card.getValue()%4){
            combinedHand.remove(i);
            i--;
          }

        }

        int isStraight = checkStraight(combinedHand); //Checks if there is a straight

        if (isStraight > -1){ //If it is a straight
          return isStraight + 300; //return high card of striaght (300 + 500(from flush) = 800) 
        } else {
          return combinedHand.get(0).getValue(); //If not, return the highcard of straight;
        }

      }

    }

    return -1; //If it get here, no flush was made

  }

}