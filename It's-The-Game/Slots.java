/** Plays Slots as if one was in a casino
*
*   @author  Jason Lui
*   @version 1.0
*   @since   2018-01-09
*
*/
import java.util.*;
class Slots implements Game{
  //Initalize and declare variables

  //Declare and initalize scanner and random
  private Scanner sc = new Scanner(System.in);   
  private Random rand = new Random();

  //List of payouts
  private int[] payouts = {1000,160,80,40,20,10,5};

  //User's info
  private int bet = 0;
  private int userPayout = 0;
  private int outcomeDetermine = 0;
  private int[] outcome = new int [3];
  private String playAgain = "0";

  /**
   * Empty constuctor
   */
  public Slots(){
    
  }

  
  /** Plays slots
   * @param account is the player's account
   * @return if the user is bankrupted for the second time
   *
   */
  @Override 
  public boolean play(Account account){
    //Do while loop for user to play 
    do{

      System.out.print("\033[H\033[2J");  
      System.out.flush();  
      
      //Do while loop for user to enter a bet
      do {
      //Greets user, tells them what their balance is and instructions for the user to make a bet
      System.out.println("Welcome to slots.");
      System.out.println("Your current balance is $" + account.getMoney() + ".");
      System.out.print("Enter a bet amount that is divisble by 5: ");

      //bet is equal to user's input
      bet = sc.nextInt();

      //Seperator
      System.out.println("============================");

      } while((bet % 5 != 0) || (bet < 0) || (bet > account.getMoney())); //While the user's bet is less than 0 or user's bet mod 5 is not equal to 0

      //Tell user's the rolls
      System.out.print("Your rolls are: ");

      for(int i = 0; i < 3; i ++){
        //Sets outcomeDetermine to a random number from 1 to 128
        outcomeDetermine = rand.nextInt(128)+1;

        //If outcome is less than or equal to 2
        if(outcomeDetermine <= 2){
          //Set outcome at index i to 7
          outcome[i] = 7;
        }

        //Otherwise if outcome is less than or equal to 4
        else if(outcomeDetermine <= 4){
          //Set outcome at index i to 6
          outcome[i] = 6;
        }

        //Otherwise if outcome is less than or equal to 8
        else if(outcomeDetermine <= 8){
          //Set outcome at index i to 5
          outcome[i] = 5;
        }

        //Otherwise if outcome is less than or equal to 16
        else if(outcomeDetermine <= 16){
          //Set outcome at index i to 4
          outcome[i] = 4;
        }

        //Otherwise if outcome is less than or equal to 32
        else if(outcomeDetermine <= 32){
          //Set outcome at index i to 3
          outcome[i] = 3;
        }

        //Otherwise if outcome is less than or equal to 64
        else if(outcomeDetermine <= 64){
          //Set outcome at index i to 2
          outcome[i] = 2;
        }

        //Otherwise
        else{
          //Set outcome at index i to 1
          outcome[i] = 1;
        }

        //Prints out outcome at index i
        System.out.print(outcome[i] + " ");
      }

      //Prints new line
      System.out.println();

      //If the whole array is equal to the same number
      if(outcome[0] == outcome[1]  && outcome[0] == outcome[2]){
        //userPayout is equal to payout at index 7 - outcome at index 0
        userPayout = payouts[7 - outcome[0]];

        //Multiply bet by userPayout
        bet *= userPayout;

        //Tell that the user wins, their payouts and the amount earned
        System.out.println("You win!");
        System.out.println("Payouts are " + userPayout +" to 1.");
        System.out.println("You earned $" + bet +"." );
        sc.nextLine();

        //Seperator
        System.out.println("============================");
      }

      //Otherwise
      else{
      //Tell user they lost and the amount lost
      System.out.println("You lose.");
      System.out.println("You lost $" + bet + ".");
      sc.nextLine();

      //Seperator
      System.out.println("============================");

      //Multiplies bet by -1
      bet *= -1;  
     }

    //Adds bet to the variable money in account
    account.addMoney(bet);

      //If the user's bet is greater than the account's money
      if(account.getMoney() <= 0){

        //If the user has not gone bankrupted before
        if(!(account.getHasBankrupted()))
        {
          //Tell user that $500 has been added
          System.out.println("$500 has been added to your account due to insuficent funds.");
          sc.nextLine();
          sc.nextLine();

          //Add 500 to money and sets bankrupted to true
          account.addMoney(500);
          account.setHasBankrupted(true);
        }

        //Otherwise
        else{
          //Tell user has gone bankrupt
          System.out.println("You have gone bankrupt.");
          sc.nextLine();
          sc.nextLine();
          
          //Returns true
          return true;
        }
      }

      //Asks the user if they want to play again and stores it in playAgain
      System.out.print("Do you want to play again? Enter Y to play again: ");
      playAgain = sc.nextLine();

    } while(playAgain.equals("Y"));//While play again is equal to 0

    //Returns false
    return false;
  }  
}