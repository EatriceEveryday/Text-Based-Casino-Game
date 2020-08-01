/** A PokerAI object that can make decisions based on what hand they have
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-14
*
*/

import java.util.*;

class PokerAI{

  private Deck deck;
  private Player table;
  private Random random;
  private ArrayList<Player> betters;
  private ArrayList<Double> bets;

  public PokerAI(Deck deck, Player table, ArrayList<Player> betters, ArrayList<Double> bets){
    this.deck = deck;
    this.table = table;
    this.random = new Random();
    this.betters = betters;
    this.bets = bets;
  }

  public double makeDecision (int playerIndex, double biggestBet){

    if (random.nextInt(4) == 0){ //25% Chance to trust in the luck

      if (biggestBet >= betters.get(playerIndex).getChips() + bets.get(playerIndex)){
        return 0;
      }

      if (random.nextInt(2) == 0){ //12.5% chance to raise for no reason
        return biggestBet + 0.1*random.nextInt((int)(betters.get(playerIndex).getChips() + bets.get(playerIndex) - biggestBet)) + 1; // Raise a random ammount
      }
      return 0; //12.5% chance to check for no reason
    }

    double rating = betters.get(playerIndex).getRating(table.getHand(0));
    double bigPot = (betters.get(playerIndex).getChips() + bets.get(playerIndex))*0.2; 

    if (rating > 100 && biggestBet < bigPot/2){

      return bigPot/2 + random.nextInt((int)(0.5*bigPot)); // Raise a random percentage between 10% and 15%

    }

    if (biggestBet > bigPot){ //If the highest bet is greater than 20% of what the player can afford, fold

      return -1;

    }

    return 0;
    
  }

  public double makeDecision (int playerIndex, int turnIndex, double biggestBet){

    if (random.nextInt(5) == 0){ //20% Chance to perform a random actions

      System.out.println("I'm Trolling");

      if (biggestBet >= betters.get(playerIndex).getChips() + bets.get(playerIndex)){ //If the bet is too high to raise, check
        return 0;
      }

      switch (random.nextInt(3)){

        case 0:
          return betters.get(playerIndex).getChips() + bets.get(playerIndex); //6.66% to all-in

        case 1:
          return (int)(biggestBet + 0.9*random.nextInt((int)(betters.get(playerIndex).getChips() + bets.get(playerIndex) - biggestBet)) + 1); //6.66% to raise to a random amount

      }

      return 0; //6.66% to check

    } 

    double rating = betters.get(playerIndex).getRating(table.getHand(turnIndex + 3));

    if (rating >= 400){ //If the hand is higher than or equal to a straight, be aggressive

      double bigPot = (betters.get(playerIndex).getChips() + bets.get(playerIndex))*0.7; //70% of the players all-in

      if (biggestBet < bigPot){ //if the biggest bet is worth less than 70% of what the player can contributed

        return(betters.get(playerIndex).getChips() + bets.get(playerIndex)); //Always all-in if given the opportunity

      }

    } else if (rating >= 200){ //If the hand is a triple or a double pair, be somewhat aggresive

      double bigPot = (betters.get(playerIndex).getChips() + bets.get(playerIndex))*0.35; //35% of the players all-in

      if (biggestBet < bigPot){

        return(bigPot + random.nextInt((int)(0.3*bigPot))); // Raise a random percentage between 35% and 45%

      } else if (biggestBet > bigPot*1.5){ //If the highestbet is greater than 52.5% of what the player can afford, fold
        return -1;

      }

    } else if ((int)(rating/100) == 1){

      double bigPot = (betters.get(playerIndex).getChips() + bets.get(playerIndex))*0.1; //10% of the players all-in

      if (biggestBet < bigPot){

        return(bigPot + random.nextInt((int)(0.5*bigPot))); // Raise a random percentage between 10% and 15%

      } else if (biggestBet > bigPot*2){ //If the highestbet is greater than 20% of what the player can afford, fold

        return -1;

      }

    } else {

      double bigPot = (betters.get(playerIndex).getChips() + bets.get(playerIndex))*0.05; //5% of the players all-in

      if (biggestBet > bigPot*2){ //If the highestbet is greater than 10% of what the player can afford, fold

        return -1;

      }

    }

    return 0; //If the Ai gets here, it checks

  }

}