/** The bartender object that you can interact with to get vodka
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-16
*
*/

import java.util.*;

class Bartender extends NPC{

  public Bartender(){ //creates a bartender with Simon cocktail as the name
    this.name = "Simon Cocktail";
    this.dialogue = new String[0];
  }

  /**
   * interacts with the bartender for potential vodka
   * @param account is the user's account
   */

  @Override
  public void interact(Account account){

    System.out.print("\033[H\033[2J");  //clears output console
    System.out.flush();  

    System.out.println(this.name + "\n"); //shows name

    //Greets user and ask if they want a drink
    System.out.print("Welcome fellow customer.");
    sc.nextLine();
    System.out.print("Would you like to have a drink?\n\nType \"Yes\" to buy a drink: ");

    if (sc.nextLine().equals("Yes")){ //if they decide to get a drink

      if (account.getMoney() < 25){ //return if they dont have enough money
        System.out.print("I think you are a bit broke for that dear customer.");
        return;
      }

      System.out.println("Very well."); //Give them vodka
      sc.nextLine();
      System.out.print("The bartender beautifully prepares your vodka in front of you.");
      sc.nextLine();
      System.out.print("You drank the vodka.");
      sc.nextLine();
      System.out.print("You feel better about your day.");
      sc.nextLine();
      System.out.print("\nDrunk +1\nLuck +1\nBalance: -25");
      account.addMoney(-25);
      sc.nextLine();

    }

    System.out.print("\nPlease do come again.");
    sc.nextLine();

  }


}