/** A Pingu object where you can slowly unlock his godlike language as you gain money 
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-16
*
*/

import java.util.*;

class Pingu extends NPC{

  public Pingu (){
    this.name = "Pingu The Intellectual";
    this.dialogue = new String[0];
  }

  /**
   *Talks to the all know lord, pingu
   *
   **/

  @Override
  public void interact(Account account){
    
    //Clears output console
    System.out.print("\033[H\033[2J");  
    System.out.flush();  

    System.out.println(this.name + "\n");

    if (account.getMoney() < 10000){ //If the player has less then $10,000 they are not considered intellectual
      System.out.print("Noot noot!");
      sc.nextLine();
      System.out.print("Noot noot noo-oot.");
      sc.nextLine();
      System.out.print("NOOOOOT noot noot!");
      sc.nextLine();
      System.out.print("Noot noot?");
      sc.nextLine();
      System.out.print("Noot noot NOOT!");
      sc.nextLine();
      System.out.print("Noot noot noot no-ot noot noot noot noooooot noot!");
      sc.nextLine();
      System.out.print("NoOt NoOOt nOOoT NooOT.");
      sc.nextLine();
      System.out.print("Noot noot noot!");
      sc.nextLine();

    } else if (account.getMoney() < 20000){//If they have between 10,000 and 20,000, translate all the pronouns and posession words

      System.out.print("I Noot you noot your!");
      sc.nextLine();
      System.out.print("I Noot noot noo-oot.");
      sc.nextLine();
      System.out.print("NOOOOOT noot noot!");
      sc.nextLine();
      System.out.print("Noot you noot your?");
      sc.nextLine();
      System.out.print("I Noot you noot noot no-ot noot noot noot noooooot noot!");
      sc.nextLine();
      System.out.print("Noot noot NOOT!");
      sc.nextLine();
      System.out.print("NoOt NoOOt your nOOoT NooOT.");
      sc.nextLine();
      System.out.print("I Noot noot noot!");
      sc.nextLine();

    } else if (account.getMoney() < 50000){ //If they have between 20,000 and 50,000 translate all the common words

      System.out.print("I Noot that you noot your at the Noot!");
      sc.nextLine();
      System.out.print("I am noot noo-oot.");
      sc.nextLine();
      System.out.print("NOOOOOT noot to noot at the noot is to noot at!");
      sc.nextLine();
      System.out.print("Noot you have noot your?");
      sc.nextLine();
      System.out.print("I Noot you the noot no-ot of noot this noot noooooot  of noot!");
      sc.nextLine();
      System.out.print("Noot noot to NOOT!");
      sc.nextLine();
      System.out.print("NoOt to NoOOt your nOOoT at the NooOT.");
      sc.nextLine();
      System.out.print("I Noot noot noot!");
      sc.nextLine();

    } else if (account.getMoney() < 100000){ //If they have between 50,000 and 100,000 translate a bit more

      System.out.print("I see that you have noot your at the noot!");
      sc.nextLine();
      System.out.print("I am rather noo-oot.");
      sc.nextLine();
      System.out.print("NOOOOOT once said \"to noot at the noot is to noot at noot.\"");
      sc.nextLine();
      System.out.print("Noot you have noot your noot?");
      sc.nextLine();
      System.out.print("I Noot you the noot no-ot of noot this noot noooooot of noot!");
      sc.nextLine();
      System.out.print("Noot next to NOOT!");
      sc.nextLine();
      System.out.print("Continue to NoOOt your nOOoT at the NooOT.");
      sc.nextLine();
      System.out.print("I Noot good noot!");
      sc.nextLine();

    } else { //If they have greater than 100,000, congratuate them by translating the words of god

      System.out.print("I see that you have proven your skills at the casino.");
      sc.nextLine();
      System.out.print("I am rather impressed.");
      sc.nextLine();
      System.out.print("Gandhi once said that \"to win at the casino is to win at life\"");
      sc.nextLine();
      System.out.print("Clearly you have proven your worth.");
      sc.nextLine();
      System.out.print("I grant you the greatest honour of joining this casino’s hall of fame.");
      sc.nextLine();
      System.out.print("Right next to Heshi.");
      sc.nextLine();
      System.out.print("Continue to prove your skills at the tables.");
      sc.nextLine();
      System.out.print("I expect good results.");
      sc.nextLine();

    }

    System.out.print("\nOmnipotent Intellegence +1");
    sc.nextLine();



  }
}