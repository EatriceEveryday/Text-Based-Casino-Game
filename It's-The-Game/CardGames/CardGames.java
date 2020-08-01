/** A Card game abstract class that contains everything that blackjack and poker have in common
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-12 
*/

import java.util.*;

abstract class CardGames implements Game{

  ArrayList<Player> players;
  Scanner scString;
  Scanner sc;
  Deck deck;

  public CardGames() { //Creates an empty list of players and the scanners
    this.players = new ArrayList<Player>();
    this.scString = new Scanner(System.in);
    this.sc = new Scanner(System.in);
  }

  public boolean play(Account account){ //A play function
    return true;
  }

}