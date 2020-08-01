/** The typical  hot grill object that you can interact with to potentially lose money
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-16
*
*/

import java.util.*;

class HotGrill extends NPC{

  public HotGrill (){ //Creates the HotGrill class with brianna as the name

    this.name = "Brianna";
    this.dialogue = new String[0];

  }

  /**
   * interacts with a hot grill
   * @param account is the user's account
   */
  @Override
  public void interact(Account account){

    System.out.print("\033[H\033[2J");  //clear output console
    System.out.flush();  

    System.out.println(this.name + "\n"); //print name

    System.out.print("Excuse me?"); //dialogue
    sc.nextLine();
    System.out.print("Are you flirting with me?");
    sc.nextLine();

    if (account.getMoney() < 10000){ //If the player is poor

      System.out.print("Ugh, go away!");
      sc.nextLine();
      System.out.print("\nConfidence -1");
      sc.nextLine();

    } else {

      System.out.print("Ugh, go-"); //If the player is rich
      sc.nextLine();
      System.out.print("Oooooooo~");
      sc.nextLine();
      System.out.print("What a big stack you have there~");
      sc.nextLine();
      System.out.print("On second thought, how about you join me on a nice dinner?");
      sc.nextLine();
      System.out.print("It's not that expensive and I want to get to know you more~ \n\nEnter response (Enter \"Begone WOMAN\" to decline): ");

      if (sc.nextLine().equals ("Begone WOMAN")){ //If the player rejects the invitation

        System.out.print("Wow, how rude!");
        sc.nextLine();
        System.out.print("\nConfidence +9000\n\nBalance: Saved");
        sc.nextLine();

      } else { //If the player doesnt reject the invitation the proper way

        System.out.print("C'mon let's just have some fun together ok?");
        sc.nextLine();
        System.out.print("\nConfidence +1\n\nBalance: -$5000");
        account.addMoney(-5000); //Take 5000 from their balance
        sc.nextLine();

      }

    }

  }


}