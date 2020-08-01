/** A Poker object that contains everything needed to run the game
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-13
*
*   Notes/Bugs:
*   A winner can't be determined if there are 2 or more betters left
*   Remember to implement AI and Player sign ins in setUp()
*/

import java.util.*;

class Poker extends CardGames{

  private ArrayList<Player> betters;
  private double buyIn, biggestBet;
  private double[] blinds;
  private ArrayList<Double> bets;
  private Player table;
  private int index; //Represents which player's turn
  private PokerAI pokerAI;

  /** Creates an insance of the game which contains whatever is in card games, a table, a deck, betters, their respective bets, and an AI.
   * 
   *  @Player is created to represent the Dealer
   *  @A BJ deck is created, where the values of all face cards are 10
   */

  public Poker (){
    super();
    table = new Player("Table");
    deck = new Deck();
    betters = new ArrayList<Player>();
    bets = new ArrayList<Double>();
    blinds = new double[2];
    pokerAI = new PokerAI(deck, table, betters, bets);
  }

  /** Plays the game. Will continue running until one player is left.
   */
  
  @Override
  public boolean play(Account account){

    clear();

    if (account.getMoney() < 100){

      System.out.println("It seems that you don't have enough money!");
      System.out.println("Press enter to continue.");
      scString.nextLine();
      return false;

    }

    setUp(account); //Sets up the game

    while (players.size() > 1 && checkHumans()){

      setRound(); //Sets up the round
      index = 0;

      while (index < betters.size() && betters.size() > 1){ //Goes through all the players at least once unless, unless only one player is still betting.

        clear();

        if (betters.get(index).getStatus().equals("All-In")){ //Skips the players turn if they all-in

          bets.set(index, biggestBet); //sets their bet equal to the biggestbet to prevent an infinite loop
          index += 1;

        } else if (!betters.get(index).isAI()){ //If the player is not an AI, run human turn

          index = humanTurn(index); //Function updates the index. It is to do the appropiate changes if the player decides to fold
          
        } else {

          index = AITurn(index, (int)pokerAI.makeDecision(index, biggestBet));

        }

      }

      index = 0;

      while (!checkBets() && betters.size() > 1){ //If the bets are not all equal, that means someone raised during the round. This run will go through until every either checks or folds unless only one player is still betting.
        clear();

        if (betters.get(index).getStatus().equals("All-In")){ //Skips the players turn if they all-in

          bets.set(index, biggestBet); //sets their bet equal to the biggestbet to prevent an infinite loop
          index += 1;

        } else if (!betters.get(index).isAI()){ //If the player is not an AI, run human turn
          index = humanTurn(index);//Function updates the index. It is to do the appropiate changes if the player decides to fold
        } else {
          index = AITurn(index, (int)pokerAI.makeDecision(index, biggestBet));
        }
        
        if (index >= betters.size()){ //If the index number goes over, loop it back to the first player
          index = 0;
        }

      }

      for (int a = 0; a < 3; a++){ //Run 3 times for the flop, turn and river

        index = 0;

        while (index < betters.size() && betters.size() > 1){//Goes through all the players at least once unless, unless only one player is still betting.

          if (betters.get(index).getStatus().equals("All-In")){ //Skips the players turn if they all-in

            bets.set(index, biggestBet); //sets their bet equal to the biggestbet to prevent an infinite loop
            index += 1;

          } else if (!betters.get(index).isAI()){ //If the player is not an AI, run human turn

            clear();
            System.out.println("Table: \n" + table.getHand(a + 3) + "\n"); //Show the table cards
            index = humanTurn(index);//Function updates the index. It is to do the appropiate changes if the player decides to fold

          } else {

            clear();
            System.out.println("Table: \n" + table.getHand(a + 3) + "\n"); //Show the table cards
            index = AITurn(index, (int)pokerAI.makeDecision(index, a, biggestBet));

          }

        }

        index = 0;

        while (!checkBets() && betters.size() > 1){//If the bets are not all equal, that means someone raised during the round. This run will go through until every either checks or folds unless only one player is still betting.

          if (betters.get(index).getStatus().equals("All-In")){  //Skips the players turn if they all-in

            bets.set(index, biggestBet); //sets their bet equal to the biggestbet to prevent an infinite loop
            index += 1;

          } else if (!betters.get(index).isAI()){ //If the player is not an AI, run human turn

            clear();
            System.out.println("Table: \n" + table.getHand(a + 3) + "\n"); //Show the table cards
            index = humanTurn(index);//Function updates the index. It is to do the appropiate changes if the player decides to fold

          } else {

            clear();
            System.out.println("Table: \n" + table.getHand(a + 3) + "\n"); //Show the table cards
            index = AITurn(index, (int)pokerAI.makeDecision(index, a, biggestBet));

          }

          if (index >= betters.size()){//If the index number goes over, loop it back to the first player
            index = 0;
          }

        }

      }

      determineWinner();

      reset(); //Resets the round to start a new one

    }  

    if (!players.get(0).isAI()){
      clear();
      System.out.println(players.get(0).getAccount().getUsername() + " is the winner!\nPress enter to continue");
      players.get(0).getAccount().addMoney(players.get(0).getChips()); 
      players.clear();
      scString.nextLine();
    }
    
    if (account.getMoney() <= 0){ //checks if the account is broke

      if (!account.getHasBankrupted()){ //Give them money if they havent been bankruped

        System.out.println("\nHey! It seems that you need money! (Money has been added).");
        scString.nextLine();
        account.addMoney(500);
        account.setHasBankrupted(true);

      } else {

        System.out.println("\nYou've been broke one too many times.");
        scString.nextLine();
        players.clear();
        return true;

      }
    
    }

    players.clear();
    return false;

  }

  //Does all the neccsary preparation before the game starts.

  private void setUp(Account account){

    System.out.println("Your balance: " + account.getMoney());
    do{

      System.out.print("What is the buy-in? (min: 100): ");
      buyIn = sc.nextDouble();

    } while (buyIn > account.getMoney() || buyIn < 100); //Asks for the buy-in amount

    blinds[1] = buyIn/200.0; //Calculate smallBlind and bigBlind based on buy-in
    blinds[0] = blinds[1]*2;
    
    players.add(new Player(account, buyIn)); //Create a player to represent the player
    account.addMoney(-buyIn); //Take money from the player

    int numOfPlayers = 0;

    do {

      System.out.print("How many AIs do you want? (Max: 6 Total Players): "); //Asks for the number of AIs
      numOfPlayers = sc.nextInt();

    } while (numOfPlayers > 5 || numOfPlayers < 0);

    for (int i = 1; i < numOfPlayers + 1; i++){

      players.add(new Player("AI " + i, buyIn)); //Adds the AIs
      players.get(i).getAccount().addMoney(-buyIn);

    }

  }

  //Does all the neccsary preparation before the round starts.

  private void setRound(){

    deck.shuffle();

    for (Player player: players){ //Resets all the players statuses
      player.setStatus("Undecided");
    }

    for (int i = 0; i < players.size(); i++){ //Re-adds the betters and their respective bets
      bets.add(0.0);
      betters.add(players.get(i));
    }

    for (int i = 0; i < 2; i++){ //Have every player draw two cards
      for (Player player: betters){
        player.draw(deck);
      }
    }

    for (int i = 0; i < 5; i++){ //Put 5 cards on the table
      table.draw(deck);
    }

    biggestBet = blinds[0]; //Set the biggestBet

    for (int i = 0; i < 2; i++){

      if (betters.get(betters.size() - i - 1).getChips() > blinds[i]){

        betters.get(betters.size() - i - 1).addChips(-blinds[i]); //Pay blinds
        bets.set(betters.size() - i - 1, blinds[i]);
        table.addChips(blinds[i]);

      } else {

        table.addChips(betters.get(i).getChips());
        betters.get(betters.size() - i - 1).addChips(-betters.get(betters.size() - i - 1).getChips()); //Pay blinds
        bets.set(betters.size() - i - 1, biggestBet);
        betters.get(betters.size() - i - 1).setStatus("All-In");

      }
    }

  }

  //Clears the display

  private void clear(){

    System.out.print("\033[H\033[2J");  
    System.out.flush();  

  }

  //Runs a turn for the user to interact with

  private int humanTurn(int index){

    String decision;
    double bet;

    System.out.println("Highest Bet: " + biggestBet + "\nPile: $" + table.getChips() + "\n"); //Show the table

    for (Player peep: players){
      System.out.println(peep.getAccount().getUsername() + " ($" + peep.getChips() + ") : " + peep.getStatus()); //Show the decisions of all the players in the game
    }

    System.out.println("\n" + betters.get(index).toStringPoker());//Show the current player's hand
    
    if (biggestBet >= betters.get(index).getChips() + bets.get(index)){

      do{

        System.out.print("\nOptions:\n1. All-In\n2. Fold\nNumber: ");
        decision = scString.nextLine();

      } while (!(decision.equals("1") || decision.equals("2"))); //Ask for an action

      switch(decision){

        case "1": // if the player All-In

          check(index);       
          return index + 1; //Return index + 1 to continue iterating through the list of betters

        case "2": //if the player folds

          fold(index);
          break;

      }

    } else {

      do{

        System.out.print("\nOptions:\n1. Raise\n2. Check/Call\n3. Fold\nNumber: ");
        decision = scString.nextLine();

      } while (!(decision.equals("1") || decision.equals("2") || decision.equals("3"))); //Ask for an action

      switch(decision){

        case "1": //If they raise
        
          do{

            System.out.print("\nHow high do you want to raise it to?");
            bet = sc.nextDouble();

          } while (bet <= biggestBet || bet > betters.get(index).getChips() + bets.get(index)); //Ask for the ammount they want to raise as long as it isn't smaller than the biggest bet and that they can pay

          raise (index, bet);
          return index + 1; //Return index + 1 to continue iterating through the list of betters

        case "2": // if the player checks

          check(index);
          return index + 1;

        case "3": //if the player folds

          fold(index);

          break;

      }

    }

    return index; //Only reached by folding. If they folded, the next player shifts to the current index so an index update is not required
    
  }

  //Performs the actions and UI during the AI's turn

  private int AITurn (int index, int AIdecision){

    System.out.println("Highest Bet: " + biggestBet + "\nPile: $" + table.getChips() + "\n"); //Show the table

    for (Player peep: players){
      System.out.println(peep.getAccount().getUsername() + " ($" + peep.getChips() + ") : " + peep.getStatus()); //Show the decisions of all the players in the game
    }
      
    if (AIdecision == 0){ //Performs the check function if the AI checks

      System.out.println("\n" + betters.get(index).getAccount().getUsername() + " checks");
      check(index);

    } else if(AIdecision > 0){ //Performs the raise function if the ai raises

      System.out.println("\n" + betters.get(index).getAccount().getUsername() + " raised to " + AIdecision);
      raise(index, AIdecision);

    } else { //Performs the fold function if the ai folds (not change in index since the next better will shift over)

      System.out.println("\n" + betters.get(index).getAccount().getUsername() + " folds");
      fold(index);
      System.out.print("\nEnter enter to continue");
      scString.nextLine();
      return index;

    }

    System.out.print("\nEnter enter to continue"); //Increase the index by one to continue down the array
    scString.nextLine();
    return index + 1;

  }

  //Clears the table of chips and cards

  private void reset(){

    table.returnHand(deck); //Returns all cards on the table to the deck
    betters.clear(); //Clear any remaining betters and bets in their lists
    bets.clear();

    for (int i = 0; i < players.size(); i++){ //Iterates through the players to return their hands

      players.get(i).returnHand(deck);

      if (players.get(i).getChips() == 0){ //If the player has no more chips left, they are removed from the game

        players.remove(i);
        i -= 1;

      }

    }

    Player tempPlayer = players.get(0); //Moves the first player to the bottom. This represents the dealer chip being passed around
    players.remove(0);
    players.add(tempPlayer);


  }

  //Determines the Winner

  private void determineWinner(){

    clear();

    if (betters.size() == 1){ //If there is only one better left in the round, just proclaim the player the winner and exit

      System.out.print(betters.get(0).getAccount().getUsername() + " wins the round.\nPress enter to continue");
      betters.get(0).addChips(table.getChips());
      table.addChips(-table.getChips());
      scString.nextLine();
      return;

    }

    System.out.println("Table: \n" + table.getHand() + "\n"); //Show the table

    for (Player better: betters){ //Show every better's hand
      System.out.println(better.getAccount().getUsername() + ": " + better.getHand());
    }

    double[] ratings = new double[betters.size()]; //Parallel array to represent their hand strength

    for (int i = 0; i < betters.size(); i++){

      ratings[i] = betters.get(i).getRating(table.getHand()); //Assign ratings for each hand

    }

    //Insertion Sort the array in reverse (highest to lowest). Also move betters around when needed

    double key;
    int index;

    for (int i = 1; i < ratings.length; i++){

      key = ratings[i];
      index = i - 1;

      while (index >= 0 && ratings[index] < key){
        ratings[index + 1] = ratings[index];
        index--;
      }

      betters.add(index + 1, betters.get(i));
      betters.remove(i);

      ratings[index + 1] = key;

    }

    if (ratings[0] != ratings[1]){ //If there is only one winner

      System.out.print("\n" + betters.get(0).getAccount().getUsername() + " wins the round.\nPress enter to continue"); //Announce the winner
      betters.get(0).addChips(table.getChips());

    } else {

      int counter = 2;

      for (int i = 2; i < ratings.length; i++){ //counts how many winners there are

        if (ratings[i] == ratings[i-1]){
          counter += 1;
        } else {
          i = ratings.length;
        }

      }

      System.out.print("\n" + betters.get(0).getAccount().getUsername());

      for (int i = 1; i < counter; i++){
        System.out.print(" and " + betters.get(i).getAccount().getUsername());
      }

      System.out.println(" wins the round.\nPress enter to continue"); //Announce the winner

      for (int i = 0; i < counter; i++){ //Split the pot between all the winners
        betters.get(i).addChips(table.getChips()/counter);
      }

    }

    table.addChips(-table.getChips());
    scString.nextLine();

  }

  //Checks if all the bets are equal

  private boolean checkBets (){

    for (int i = 1; i < bets.size(); i++){ //Itereates through all the bets after the first

      if (!bets.get(i).equals(bets.get(0))){ //If one is not equal to the first, not all bets are equal
        return false;
      }

    }

    return true;

  }

  //Checks if there are any players left that are human

  private boolean checkHumans (){

    for (Player player: players){

      if (!player.isAI()){
        return true;
      }

    }

    return false;

  }

  //Performs the raise action

  public void raise (int playerIndex, double amount){

    biggestBet = amount;

    betters.get(playerIndex).addChips( -( biggestBet - bets.get(playerIndex) ) ); //Adds to the table what is needed to set their bets equal to the highest bet
    table.addChips(biggestBet - bets.get(playerIndex) );
    bets.set(playerIndex, biggestBet);

    if (betters.get(playerIndex).getChips() > 0){ //If they have more than 0 chips left, that means they only raised
      betters.get(playerIndex).setStatus("Raise");
    } else {
      betters.get(playerIndex).setStatus("All-In"); //If they have 0 chips left, that means they all-in
    }
  }

  //Performs the check action

  private void check (int playerIndex){

    if (biggestBet >= betters.get(playerIndex).getChips() + bets.get(playerIndex)){

      table.addChips(betters.get(playerIndex).getChips());
      betters.get(playerIndex).addChips(-betters.get(playerIndex).getChips()); //Pay what they can and set them as All-In
      betters.get(playerIndex).setStatus("All-In");

    } else {

      if (biggestBet == bets.get(playerIndex)){

        betters.get(playerIndex).setStatus("Checks");

      } else {

        betters.get(playerIndex).addChips( -( biggestBet - bets.get(playerIndex) ) );
        table.addChips(biggestBet - bets.get(playerIndex) );
        betters.get(playerIndex).setStatus("Calls");

      }

    }

    bets.set(playerIndex, biggestBet); //Updates the bet regardless if they can pay or not to prevent an infinite loop

  }

  //Performs the fold action

  private void fold (int playerIndex){

    betters.get(playerIndex).setStatus("Folds");

    betters.remove(playerIndex); //Remove the player and their bets from the list
    bets.remove(playerIndex);
  }

}