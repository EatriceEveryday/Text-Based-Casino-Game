/** A BJAI object that contains the AI for blackjack dealer.
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-12 
*/

class BJAI{

  private Deck deck;
  private Player player;

  /** Creates an AI with references to the deck and the player representing the AI.
   */

  public BJAI(Deck deck, Player player){
    this.deck = deck;
    this.player = player;
  }

  /** Commands the player what to do based on the scenario
   *  @Hit until it is seventeen
   *  @If the hand is a soft seventeen (a 17 made with an ace as 11), hit again
   */

  public void makeDecision(){

    int numOfAces = 0;

    while (player.calculateBJValue() < 17){ //hit until the total is 17 or greater
      player.draw(deck);
    }

    for (Card card: player.getHand()){ //counts the number of aces in the hand
      if (card.getValue() == 11){
        numOfAces += 1;
      }
    }

    if (player.calculateBJValue() == 17){ 

      while (numOfAces > 0){ //Only hit if there are aces left

        player.draw(deck); //Hits once and updates the counter
        numOfAces -= 1;

        while (player.calculateBJValue() < 17){ //Keep hitting until its greater than 16

          player.draw(deck);

          if (player.getHand().get(0).getValue() == 11){ //If more aces are drawn, update counter
            numOfAces += 1;
          }

        }

      }

    }
    
  }

}
