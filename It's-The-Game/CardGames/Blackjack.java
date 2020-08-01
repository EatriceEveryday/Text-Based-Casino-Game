/** A Blackjack object that contains everything needed to run the game
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-12 
*/

import java.util.*;

class Blackjack extends CardGames{

  private BJAI AI;
  private double bet;
  private String decision;
  private String playing;

  /** Creates an insance of the game which contains whatever is in card games, a player, a deck and an AI.
   * 
   *  @Player is created to represent the Dealer
   *  @A BJ deck is created, where the values of all face cards are 10
   */

  public Blackjack (){
    super();
    players.add(new Player("Dealer"));
    deck = new Deck("Bj");
    AI = new BJAI (deck, players.get(0));
  }

  /** Plays the game. Will continue running until the player exits the game.
   *  
   *  @Repeats the following until the player exits the game:
   *    @Set everything up (bet, deal cards)
   *    @Checks if the player dealted cards are a pair, if so, ask if they want to split
   *    @Checks if the player dealted cards is a black jack, if so, pay the player 1.5x
   *    @If none apply, will run the runGame() function without interruption
   *    @After the round is done, reset everything and ask the user if they want to continue
   */

  @Override
  public boolean play(Account account){

    players.add(new Player(account));
    
    do{

      System.out.print("\033[H\033[2J");  
      System.out.flush();

      System.out.println("Your money: " + account.getMoney());
      System.out.print("Enter your bet: ");

      do{

        bet = sc.nextDouble();

      } while (bet > account.getMoney() && bet < 0);

      deck.shuffle(); 
      
      for (Player player: players){ //Deal 2 cards for each player
        player.draw(deck);
        player.draw(deck);
      }

      System.out.println("\nDealer's face up card: " + players.get(0).getHand().get(0)); //Show 1 face up card from the dealer

      if (players.get(1).getHand().get(0).getName().equals(players.get(1).getHand().get(1).getName())){ //If the player's hand is a pair

        System.out.println("\n" + players.get(1).toStringBJ() + "\n"); //Show their hand

        do{

          System.out.print("Do you want to split?(Y/N): ");
          decision = scString.nextLine();

        } while (!(decision.equals("Y") || decision.equals("N"))); //Ask to split

        if (decision.equals("Y")){ //If user says yes, make a copy player and move one of the cards to the copy

          players.add(new Player (account));
          players.get(2).getHand().add(players.get(1).getHand().get(0));
          players.get(1).getHand().remove(0);

        }

        runGame(account); //runs the round

      } else if (players.get(1).calculateBJValue() == 21){ //checks if the player has a black jack

        if (players.get(0).calculateBJValue() == 21){ //checks if the dealer also has a blackjack

          System.out.println("\n" + players.get(1).toStringBJ()); //Show their hand
          System.out.println("\n" + players.get(0).toStringBJ());

          System.out.println("It's a tie, both the dealer and player has blackjacks.");

        } else { //if not, pay the player 1.5x their bet

          System.out.println("\n" + players.get(1).toStringBJ()); //Show their hand
          System.out.println("You got a blackjack!");
          account.addMoney(1.5*bet);

        }

      } else { //if none of the above applied, run the game normally

        runGame(account);

      }

      for (Player player: players){ //iterates through the players to reshuffle their hands
        player.returnHand(deck);
      }

      if (players.size() == 3){ //if there was a split, remove the copy
        players.remove(2);
      }

      if (account.getMoney() <= 0){ //checks if the account is broke

        if (!account.getHasBankrupted()){

          System.out.println("\nHey! It seems that you need money! (Money has been added).");
          scString.nextLine();
          account.addMoney(500);
          account.setHasBankrupted(true);

        } else {

          System.out.println("\nYou've been broke one too many times.");
          scString.nextLine();
          players.remove(1);
          return true;

        }
      
      }

      System.out.print("Do you want to play again?(Y/N): "); //ask the user if they want to play again.
      playing = scString.nextLine();

    } while (playing.equals("Y"));

    players.remove(1);
    return false;
    
  } 

  /** Runs the round. Will iterate through each player and will exit if all are busted.
   *  
   *  @Repeats the following for each non-dealer player:
   *    @Ask the user to perform an action
   *    @If they hit, give them a card and ask again
   *    @If they stand, iterate to the next player
   *    @If double down, double the bet, give them a card and iterate to the next player
   *    @If they bust, iterate to the next player
   *  @After all the players' turn, the AI instructs the dealer what to do
   *  @Iterates through all the hands to see which hand won agains the dealer and pay accordingly
   */

  private void runGame(Account account){

    int busted = 0;

    for (int i = 1; i < players.size(); i++){

      System.out.println("\n" + players.get(i).toStringBJ()); //Show status

      do{
        System.out.print("\nOptions:\n1. Hit\n2. Stand\n3. Double Down?\nEnter a number: ");
        decision = scString.nextLine();
      } while (!(decision.equals("1") || decision.equals("2") || decision.equals("3"))); //Ask for an action

      while (!decision.equals("2")){ //Runs as long as the user hasn't chosen "stand"

        players.get(i).draw(deck);
        System.out.println("\n" + players.get(i).toStringBJ()); //Hit and show status

        if (players.get(i).calculateBJValue() > 21){ //If they bust, take their money and exit the loop

          System.out.println("You're busted!");
          account.addMoney(-bet);
          busted += 1;
          decision = "2";

        } else if (decision.equals("3")){ //If they double downed, double their bet and exit the loop

          bet = bet*2;
          decision = "2";

        }
        else { //If they hitted, ask them for an action again

          do{
            System.out.print("\nOptions:\n1. Hit\n2. Stand\n3. Double Down?\nEnter a number: ");
            decision = scString.nextLine();
          } while (!(decision.equals("1") || decision.equals("2") || decision.equals("3")));

        }
      }
    }

    if (busted == players.size() - 1){ //If all the players are busted, exit the round
      return;
    }

    AI.makeDecision(); //Ai commands dealer what to do

    System.out.println("\n" + players.get(0).toStringBJ() + "\n"); //Show dealer's status

    if (players.get(0).calculateBJValue() > 21){ //If the dealer is busted, pay all who didn't bust

      System.out.println("Dealer is busted!");

      for (int i = 1; i < players.size(); i++){

        if (players.get(i).calculateBJValue() <= 21){
          account.addMoney(bet);
        }
      }

    } else {

      for (int i = 1; i < players.size(); i++){ //iterates through all non-dealer players

        if (players.get(i).calculateBJValue() > 21){

        } else if (players.get(i).calculateBJValue() > players.get(0).calculateBJValue()) { //If the player has a greater hand, pay the player

          System.out.println(players.get(i).getAccount().getUsername() + " wins!");
          account.addMoney(bet);

        } else if (players.get(i).calculateBJValue() < players.get(0).calculateBJValue()){ //if the dealer has a greater hand, pay take money from the player

        System.out.println("Dealer wins!");
        account.addMoney(-bet);

        } else { //If neither, say its a draw

        System.out.println("It's a tie!");

        }

      }

    }
 
  }

}

