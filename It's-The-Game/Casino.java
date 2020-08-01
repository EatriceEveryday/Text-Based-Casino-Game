import java.util.*;
import java.io.*;

class Casino{
  //Initalize and declare scanners
  private Scanner sc = new Scanner(System.in);
  private Scanner scString = new Scanner(System.in);

  //Initalize and declare npcs and games and their associated lists
  private Game[] gameList = {new Slots(), new Roulette(), new Blackjack(), new Poker()};
  private NPC[] npcList = {new Bartender(), new Drunkard(), new HotGrill(), new Pingu()};

  //Initalize and declare accounts
  private ArrayList<Account> accounts = new ArrayList<Account>(); 
  private Account currentAccount;
  
  /**
   * Empty constructor
   */
  public Casino(){
  
  
  }

  /**
   *The sign in function. Will continue to ask to sign in/sign up till exited
   */

  public void run() throws IOException{

    readFile(); //Reads in the file and fills accountList with accounts

    //Initalize and declare user's information
    int userChoice = 0;
    String username;
    String password;
    String decision;
    
    do { //Repeat until the user exits

      do{ //Repeat until the user enters the correct

        //Clears output console      
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 

        //Asks for and store user's decision
        System.out.println("0.Exit");
        System.out.println("1.Sign up");
        System.out.println("2.Sign in");
        userChoice = sc.nextInt();

      } while(userChoice > 2 || userChoice < 0); 

      switch(userChoice){ 

        case 1: //if userChoice is 1

          decision = "Y";

          do {

            System.out.print("\033[H\033[2J");  //clears output console
            System.out.flush();

            //Asks for and stores username
            System.out.println("Create a username (no spaces)"); 
            username = scString.nextLine();

            if (checkUsername(username)){ //Tell the user if their username as been taken

              System.out.println("\nUsername has been taken.");
              System.out.print("Do you want to try again? (Y/N): ");
              decision = scString.nextLine();

            } else if (checkSpaces(username)){ //If checkSpaces returns true

              //Tell user their username has spaces
              System.out.println("\nThere are spaces.");
              System.out.print("Do you want to try again? (Y/N): ");
              decision = scString.nextLine();

            }

          } while ((checkSpaces(username) || checkUsername(username) || username.length() == 0) && !decision.equals("N")); //Repeats as long as the username has spaces or was taken
          
          //If decision is equal to "N"
          if (decision.equals("N")){
            break;
          }

          //Asks the user to create their password
          do {

            System.out.print("\033[H\033[2J");  //clears output console
            System.out.flush();

            //Asks for the password and stores it
            System.out.println("Create a password (no spaces)");
            password = scString.nextLine();

            //If checkSpaces return true
            if (checkSpaces(password)){
              
              //Tell user their password has spaces
              System.out.println("\nThere are spaces.");
              System.out.print("Do you want to try again? (Y/N): ");
              decision = scString.nextLine();

            }

          } while ((checkSpaces(password) || password.length() == 0) && !decision.equals("N")); //Repeats as long as their password has spaces

          if (!decision.equals("N")){
            this.currentAccount = new Account(username,password,500,false); //Sets the current account to the new account made 
            this.accounts.add(currentAccount);
            mainMenu(); //run the main menu
          }

          break;
        
        case 2: //If userChoice is equal to 2

          decision = "Y";
          
          do {

            System.out.print("\033[H\033[2J");  //clears output console
            System.out.flush(); 

            //Asks for username and password to log in
            System.out.print("Enter your username: ");
            username = scString.nextLine();

            System.out.print("\nEnter your password: ");
            password = scString.nextLine();

            this.currentAccount = searchAccount(username, password); //sets the current account to searched account

            //If currentAccount is null
            if (this.currentAccount == null){
              
              //Tell user they inputed incorrectly
              System.out.println("\nWrong combination.");
              System.out.print("Do you want to try again? (Y/N): ");
              decision = scString.nextLine();

            }

          } while (this.currentAccount == null && !decision.equals("N")); //if the account is null, that means the account wasn't found. repeat till found.

          if (!decision.equals("N")){
            mainMenu(); //run the main menu
          }
          break;

      }

    } while (userChoice != 0); //repeat until user exits

    updateFile();
    accounts.clear(); //If the user exits, clear all the accounts (everything is going to be saved into the file again right?)

  }

  /**
   *Checks if there are any spaces in the string
   *@param string is the string being checked if it has any spaces
   *@return a boolean if the string has a space
   */

  private boolean checkSpaces(String string){
    
    String[] charChecker = string.split(""); //splits it into an array to make it easier to loop through
    
    for (String character: charChecker){
      if (character.equals(" ")){ //if theres a space, return true;
        return true;
      }
    }

    return false; //if it gets here, theres no spaces so return false;

  }

  /**checks if the username exists
   *@param username is the username
   *@return boolean if the username is in the list of accounts
   */

  private boolean checkUsername(String username){

    for (Account account: accounts){ //iterates through the accounts

      if (account.getUsername().equals(username)){ //If the username already exists, return true
        return true;
      }

    }

    return false; //if it gets here, there was no account with the same username
  }

  /**searches for the account
   *@param username is the username
   *@param password is the password
   *@return account if the combination of username and password is in the list of accounts
   */
  
  private Account searchAccount(String username, String password){

    for (Account account: accounts){ //iterates through the accouns

      if (account.isEqual(username, password)){ //if the account has the same username and password, return the account
        return account;
      }

    }

    return null; //if it gets here, there were no accounts, return null to show that there were no accounts

  }
  
  /**Runs the main menu until the user signs out
   *Asks the user for which game to player and plays that game
   *if chooses to meet NPCs, the user can then choose an NPC to talk to
  **/

  private void mainMenu() {

    int userChoice = 0; //Main menu choice
    int userSecondChoice = 0; //NPC choice

    do { //Repeat until they exit

      do{ //Repeat until they get the right input

      //Clear Output console
      System.out.print("\033[H\033[2J");  
      System.out.flush(); 

      //Ask for what user wants to do
      System.out.println("Enter the game you want to play");
      System.out.println("0.Sign Out");
      System.out.println("1.Slots");
      System.out.println("2.Roulette");
      System.out.println("3.Blackjack");
      System.out.println("4.Poker");
      System.out.println("5.Visit some kool people");

      //Stores user's choice in userChoice
      userChoice = sc.nextInt();
    
      }while(userChoice < 0 || userChoice > 5);// While userChoice is less than 0 or greater than 5

      if (userChoice == 5) { //If they choose the NPCs

        do { //repeat till they exit

           do { //repeat till they get the input right
            
            //Clears output console
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 

            //Asks user who they want to meet
            System.out.println("Who do you want to meet?");
            System.out.println("0.Main Menu");
            System.out.println("1.Bartender");
            System.out.println("2.Drunkard");
            System.out.println("3.HotGrill");
            System.out.println("4.Pingu");

            //Stores user's input in userSecondChoice
            userSecondChoice = sc.nextInt();

          } while (userSecondChoice < 0 || userSecondChoice > 4);// While userSecondChoice is less than 0 or greater than 4

          if (userSecondChoice > 0){
             npcList[userSecondChoice - 1].interact(currentAccount); //interact with taht npc
          }

        } while (userSecondChoice != 0); //repeat till they exit
       
      } else if (userChoice != 0) { //as long as the option wasnt exit

        if (gameList[userChoice-1].play(currentAccount)){
          
          System.out.println("Your account has been banished from the casino.");
          this.accounts.remove(accounts.indexOf(currentAccount));
          this.currentAccount = null;
          scString.nextLine();
          return;

        }

      }

    } while (userChoice != 0); //repeats until they leave    
    this.currentAccount = null; //Reset the current account

  }

  //Method run at the beginning of the program to read all the stored accounts
  private void readFile() {
    //Creates temporary variables used to create the accounts
    String userTemp;
    String passTemp;
    double moneyTemp;
    boolean boolTemp;

    //null scanner to read the file
    Scanner reader = null;

    try { //tries to run this block of code
    //creates a new scanner buffered reader file reader that reads "Accounts.txt"
    reader = new Scanner(new BufferedReader(new FileReader("Accounts.txt")));
    
    while(reader.hasNext()){//while loop to check if the text file has anything
    //reads the lines and stores 
      userTemp = reader.next();
      passTemp = reader.next();
      moneyTemp = reader.nextDouble();
      if (reader.next().equals("false")){//checks if the next string is false or not to set the boolean variable as true or false
        boolTemp = false;
      } else {
        boolTemp = true;
      }
      //creates and appends the account into the accounts arraylist
      accounts.add(new Account(userTemp,passTemp,moneyTemp,boolTemp));
    }
    } catch (IOException e) { //catches error and does nothing instead of crashing

    } finally { //runs this block of code at the end
    if (reader != null){ // checks if the scanner reader opened up the file if it did close the scanner reader
      reader.close();
    }
  }
  }

  //Method run at the end of the code to update all the accounts' information
  private void updateFile() throws IOException{
    //Creates a null filewriter
    FileWriter writer = null;
    try{//tries to run this block of code
    //creates a file writer that opens up the text file "Accounts.txt"
      writer = new FileWriter("Accounts.txt");

      if (accounts.size() == 0){//if the size of the accounts arraylist is 0 update the file with " " to have no accounts
        writer.write(" ");
      }

      for(int i = 0; i<accounts.size();i++){ //For loop to iterate through the accounts arraylist and writes the account information into the file
        writer.write(accounts.get(i).toString() + "\n");
      }

    } catch (IOException e) { // catches the error and outputs error message
      System.out.println("Can't write to file");
    } finally {// runs this block of code at the end
      if(writer != null){ // checks if the filewriter opened up the file and closes it if it did
        writer.close();
      }
    }
  }
}