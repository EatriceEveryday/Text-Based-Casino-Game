/** The standard NPC object where you can set their name and dialogue
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-16
*
*/

import java.util.*;

class NPC {
  
  Scanner sc = new Scanner(System.in);
  String name;
  String[] dialogue;

  public NPC (){ //Empty npc constructor just to satisfy conditions for the subclasses
    this.name = "";
    this.dialogue = new String[0];
  }

  public NPC (String name, String[] dialogue){ //Constructor to create a generic npc with a a name and dialogue
    this.name = name;
    this.dialogue = dialogue;
  }

  //Interacts with the NPC

  public void interact(Account account){ 

    System.out.print("\033[H\033[2J");  //Clears
    System.out.flush();  

    System.out.println(this.name + "\n"); //Prints out the name

    for (String text: dialogue){ //Runs through the dialogue
      System.out.print(text);
      sc.nextLine();
    }
  }

}