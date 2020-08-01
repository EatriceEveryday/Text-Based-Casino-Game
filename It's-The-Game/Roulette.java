/** Plays roulette as if the user is in a casino
*
*   @author  Peter Xue/Jason Lui
*   @version 1.0
*   @since   2018-01-10
*
*/
import java.util.*;
class Roulette implements Game{
  //Declare and initalize variables

  //Declare and initalize scanners
  private Scanner sc = new Scanner(System.in);
  private Scanner scString = new Scanner(System.in);

  //Declare and initalize random
  private Random rand = new Random();

  //User's variables  
  private String playAgain = "Y";
  private int choice;
  private int betAmount;
  private int[] userNum;
  private int payouts;  
  private boolean userCorrect = false;

  //Roulette's variables
  private int spin = rand.nextInt(38);
  private int tracker = 0;

  //2-D arrays for displaying purposes
  private String rouletteDisplay[][] = {
    {"00","3","6","9","12","15","18","21","24","27","30","33","36"},
    {"0 ","2","5","8","11","14","17","20","23","26","29","32","35"},
    {"  ","1","4","7","10","13","16","19","22","25","28","31","34"},
  };
  private int red[] = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
  private int black[] = {2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};

  /** 
    * Empty Constructor
    */
  public Roulette(){

  }

  /** Plays Roulette
   * @param account is the player's account
   * @return if the user is bankrupted for the second time
   *
   *
   */
  @Override 
  public boolean play(Account account){
    
    //Greets user
    System.out.println("Welcome to Roulette");

    do {
      //Clears the screen
      System.out.print("\033[H\033[2J");  
      System.out.flush();  

        //Display the roulette board 
        System.out.println("Roulette Board");
        for(int i = 0; i < rouletteDisplay.length;i++){
          for (int j = 0; j<rouletteDisplay[i].length;j++){
            System.out.print(rouletteDisplay[i][j] + " ");
          }
          System.out.println();
        }

        //Displays all red numbers
        System.out.println("======================");
        System.out.println("Red numbers");
        for (int i = 0; i<red.length;i++){
          System.out.print(red[i] + " ");
        }

        //Displays all black numbers
        System.out.println();
        System.out.println("Black numbers");
        for (int i = 0; i<black.length;i++){
          System.out.print(black[i] + " ");
        }

        //Asks user for their choice of bet
        System.out.println();
        System.out.println("======================");
        System.out.println("What type of bet do you want to make?");
        System.out.println("1. Straight bet");
        System.out.println("2. Split bet");
        System.out.println("3. Street bet");
        System.out.println("4. Corner bet");
        System.out.println("5. Top Line bet");
        System.out.println("6. Red bet");
        System.out.println("7. Black bet");
        System.out.println("8. Odd bet");
        System.out.println("9. Even bet");
        System.out.println("10. 1-18 bet");
        System.out.println("11. 19-36 bet");
        System.out.println("12. 1-12 bet");
        System.out.println("13. 13-24 bet");
        System.out.println("14. 25-36 bet");
        System.out.println("15. Column bet");
        System.out.println("======================");

        //Asks for user input then uses switch case using choice variable
        do{
        System.out.print("Enter the number that coresponds to the bet you want to make: ");
        choice = sc.nextInt();
        sc.nextLine();  
        }while(!(choice < 16 && choice > 0)); //While choice is not less than 16 or greater than 0

        //Seperator
        System.out.println("======================");

        switch(choice){
          //Straight bet
          case 1: userNum = new int[1]; //Length of userNum is equal to 1
                  do{
                    //Asks user for their bet on a number
                    System.out.print("Bet on a number (enter 37 for 00): ");

                    //User's input is stored in userNum
                    userNum[0] = sc.nextInt();       
                    
                  }while(!(0 <= userNum[0]  && userNum[0]<= 37));//While user's input is greater than or equal to 0 and less than or equal to 37

                  //Tells user their input is correct
                  System.out.println("Input correctly entered.");

                  //Set payouts to 35
                  payouts = 35;

                  //End the switch case
                  break;

          //Split bet
          case 2: userNum = new int[2]; //Length of userNum is equal to 2
                  do{
                    //Asks user to bet on two numbers that are horizontal or veritcal to each other
                    System.out.println("Bet on two numbers that are either horizontal or veritcal to each other: ");

                    System.out.print("Number 1: ");
                    userNum[0] = sc.nextInt();

                    System.out.print("Number 2: ");
                    userNum[1] = sc.nextInt();                   
                  }while ((Math.abs(userNum[0]-userNum[1]) != 3 && Math.abs(userNum[0]-userNum[1]) != 1) || (!(0 <=userNum[0] && userNum[0]< 37) || !(0 <=userNum[1]&& userNum[1] < 37)));//While the difference of the two inputs is not equal to 3 and 1 or if the user's input is not greater than or equal to 0 and less than 37

                  //Tells user their input is correct
                  System.out.println("Input correctly entered.");

                  //Sets payouts to 17
                  payouts = 17;

                  //Ends the switch case
                  break;

          //Street bet
          case 3: userNum = new int[3]; //Set length of userNum ot 3
                  do{
                    //Tell user to input a number at the bottom of the column
                    System.out.println("Bet on 3 numbers that form a horizontal column on the roulette board: ");
                    System.out.println("Enter the bottom number of the column: ");
                    
                    //user's input is stored in userNum[0]
                    userNum[0] = sc.nextInt();

                  }while(userNum[0]%3 != 1);//While the remainder of user[0] /3 is not equal to 1

                  //Tell user their input is correct
                  System.out.println("Input correctly entered.");

                  //append userNum[0]+1 and userNum[2] to userNum
                  userNum[1] = userNum[0] + 1;
                  userNum[2] = userNum[0] + 2;

                  //Set payouts to 11
                  payouts = 11;

                  //End the switch case
                  break;

          //Corner bet
          case 4: userNum = new int[4];//Set the length of userNum to 4
                  do{
                    //Tell user to enter the corner number of the 4 numbers that create a square
                    System.out.println("Enter the top  left corner number of the 4 numbers that you wish to bet on: ");

                    //Stores user's input in userNum[0]
                    userNum[0] = sc.nextInt();
                  } while(!(0 <=userNum[0] && userNum[0]< 34) || (userNum[0] % 3 == 1));

                  //Tell user they input correctly
                  System.out.println("Input correctly entered.");

                  //Creates the other 3 numbers the user bets on and stores it in userNum
                  userNum[1] = userNum[0] + 3;
                  userNum[2] = userNum[0] - 1;
                  userNum[3] = userNum[0] + 2;

                  //Set payouts to 9
                  payouts = 9;

                  //Ends the switch case
                  break;

          //Top line bet
          case 5: int[] topLineNum = {0,37,1,2,3};//initalize and declares a set of numbers that turn into a top line bet
                  userNum = topLineNum;//userNum equals to topLineNum

                  //Set payouts to 6
                  payouts = 6;

                  //Tell user they betted on top line
                  System.out.println("You betted on top line numbers (0,00,1,2,3).");

                  //Break out of the switch case
                  break;

          //Red numbers bet
          case 6: userNum = red;//userNum is equal to red

                  //Set payouts to 1
                  payouts = 1;

                  //Tell user they bet on red numbers
                  System.out.println("You betted on all red numbers.");

                  //Break out of the switch case
                  break;
                  
          //Black numbers bet
          case 7: userNum = black;//userNum is equal to black

                  //Set payouts to 1
                  payouts = 1;

                  //Tell user they bet on black numbers
                  System.out.println("You betted on all black numbers.");

                  //Break out of switch case
                  break;

          //Odd numbers bet
          case 8: userNum = new int [18];//Set length of userNum to 18
                  //Set tracker to 0
                  tracker = 0;

                  //For all numbers from 1 to 35
                  for (int i = 1; i < 36; i++){
                    //If the remainder of i/2 is 1
                    if(i % 2 == 1){
                      //Add the number to userNum at index tracker
                      userNum[tracker] = i;

                      //tracker's value is increased by 1
                      tracker += 1;
                    }
                  }

                  //Set payouts to 1
                  payouts = 1;

                  //Tell user they betted on odd numbers
                  System.out.println("You betted on all odd numbers.");

                  //Break out of switch case
                  break;

          //Even numbers bet
          case 9: userNum = new int [18];//Set length of userNum to 18
                  //Set tracker to 0
                  tracker = 0;

                  //For a numbers from 1 to 35
                  for (int i = 1; i < 36; i++){

                    //If the remainder of i/2 is 0
                    if(i % 2 == 0){

                      //Add the number to userNum at index tracker
                      userNum[tracker] = i;

                      //Increase the value of tracker by 1
                      tracker += 1;
                    }
                  }

                  //Set payouts to 1
                  payouts = 1;

                  //Tell user they betted on all even numbers
                  System.out.println("You betted on all even numbers.");

                  //Break out of switch case
                  break;

          //1-18 bet
          case 10:  userNum = new int[18];//Set the length of userNum to 18

                    //For all numbers from 1-18
                    for (int i = 1; i < 19; i ++){
                      //userNum at index i-1 is equal to i
                      userNum[i-1] = i;
                    }

                    //Set payouts to 1
                    payouts = 1;

                    //Tell user they betted on all numbers from 1 to 18
                    System.out.println("You betted on all numbers from 1 - 18.");

                    //Break out of switch case
                    break;  

          //19-36 bet
          case 11:  userNum = new int[18];//Set length of userNum to 18

                    //For all numbers from 19 to 36
                    for (int i = 19; i < 37; i ++){
                      //userNum at index i-19 is equal to 19
                      userNum[i-19] = i;
                    }

                    //Set payouts to 1
                    payouts = 1;

                    //Tell user they betted on all numbers from 19-36
                    System.out.println("You betted on all numbers from 19 - 36.");

                    //Break out of switch case
                    break;  


          //1-12 bet
          case 12:  userNum = new int[12];//Set the length of userNum to 12

                    //For all numbers from 1 to 12
                    for (int i = 1; i < 13; i ++){
                      //userNum at index i-1 is equal to i
                      userNum[i-1] = i;
                    }

                    //Set payouts to 2
                    payouts = 2;

                    //Tell user they betted on all numbers from 1-12
                    System.out.println("You betted on all numbers from 1-12.");

                    //Break out of switch case
                    break;     

          //13-24 bet
          case 13:  userNum = new int[12];//Set the length of userNum to 12

                    //For all numbers from 13 to 24
                    for (int i = 13; i < 25; i ++){
                      //userNum at index i-13 is equal to i
                      userNum[i-13] = i;
                    }

                    //Set payouts to 2
                    payouts = 2;

                    //Tell user they betted on numbers from 13 to 24
                    System.out.println("You betted on all numbers from 13-24.");

                    //Break out of switch case
                    break;  

          //25-36 bet
          case 14:  userNum = new int[12];//Set the length of userNum to 12

                    //For all numbers fron 25 to 36
                    for (int i = 25; i < 37; i ++){
                      //userNum at index i-25 is equal to i
                      userNum[i-25] = i;
                    }

                    //Set payouts to 2
                    payouts = 2;

                    //Tell user they betted on numbers from 25 to 36
                    System.out.println("You betted on all numbers from 25-36.");

                    //Break out of switch case
                    break;  

          //Column bet
          case 15:  userNum = new int[12];//Set the length of userNum to 12

                    //Declare and initalize variable
                    int userColumn = 0;

                    //Set tracker to 0
                    tracker = 0;


                    do{
                    //Tells user the columns and their coressponding number
                    System.out.println("0.[3,6,9,12,15,18,21,24,27,30,33,36]");
                    System.out.println("1.[1,4,7,10,13,16,19,22,25,28,31,34]");
                    System.out.println("2.[2,5,8,11,14,17,20,23,26,29,32,35]");             

                    //Ask for and store user's input
                    System.out.println("Enter the number that corresponds to the column: ");
                    userColumn = sc.nextInt();
                    
                    }while(!(0<= userColumn && userColumn<= 2));//While userColumn is not less than or equal to 2 and greater than or equal to 0

                    //For all numbers from 1 to 36
                    for(int i = 1; i < 37; i ++){
                      //If the remainder of i /3 is equal to userColumn
                      if(i % 3 == userColumn){
                        //userNum at index tracker is equal to i
                        userNum[tracker] = i;

                        //tracker's value is increase by 1
                        tracker ++;
                      }
                    }

                    //Set payouts to 2
                    payouts = 2;

                    //Break out of switch case
                    break;

          default: //Otherwise
                   //Tell user to input correctly
                   System.out.println("Enter a correct input.");

                   //Break out of switch case
                   break;
        }

        //Seperator
        System.out.println("======================");

        do{
          //Tells user their balance and how much they want to bet
          System.out.println("You have $" + account.getMoney() + ".");
          System.out.print("Enter the amount you want to bet: ");

          //Stores user's bet amount in betAmount
          betAmount = sc.nextInt();
        } while( betAmount > account.getMoney() || betAmount < 0);//While bet amount is greater than the account's balance or lower than 0

        //Seperator
        System.out.println("======================");

        userCorrect = false;
        //Give spin a value from 0 to 37
        spin = rand.nextInt(38);

        //Iterates through userNum
        for(int i = 0; i < userNum.length; i ++){
          //If the index in userNum is equal to spin
          if(userNum[i] == spin){
            //Sets userCorrect to true and end the loop
            userCorrect = true;
            i = userNum.length;
          }
        }

        //Tells user the spin
        System.out.println("The roulette spins and lands on " + spin + ".");

        //If userCorrect is true
        if(userCorrect){
          //Multiply betAmount by payouts
          betAmount *= payouts;

          //Tell user that they win, their payouts and the amount earned
          System.out.println("You win");
          System.out.println("Payouts are " + payouts + " to 1.");
          System.out.println("You earned $" + betAmount + ".");
        }

        //Otherwise
        else{
          //Tell user they lose and the amount lost
          System.out.println("You lose");
          System.out.println("you lost $" + betAmount + "."); 

          //Multiply bet amount by -1
          betAmount *= -1;
        }

        //Adds betAmount to the account's balance
        account.addMoney(betAmount);

        //If the user has less or equal to 0 in account funds
        if(account.getMoney() <= 0){
          //If the user has not been bankrupted before
          if(!account.getHasBankrupted()){
            //Tells user that $500  was added to their account
            System.out.println("\n$500 has been added due to insuficent funds.");

            //Seperator
            System.out.println("======================");

            //Add $500 to the account and set bankrupt to true
            account.addMoney(500);
            account.setHasBankrupted(true);

            scString.nextLine();          
          }

          //Otherwise 
          else {

            //Tells user that they have gone fully bankrupt
            System.out.println("\nYou've been broke one too many times.");
            scString.nextLine();
            return true;
          }
        }
      
      //Ask if user wants to play again
      System.out.println("Do you want to play again? (Y/N)");
      playAgain = scString.nextLine();
    } while (playAgain.equals("Y"));//While exit is equal to Y

    return false; // Return false if the whole method was iterated through

  }
}
