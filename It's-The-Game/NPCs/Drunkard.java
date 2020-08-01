/** The drunkard object that you can interact with to get sweared at
*
*   @author  Nathaniel Cho
*   @version 1.0
*   @since   2018-01-16
*
*/

import java.util.*;

class Drunkard extends NPC{

  public Drunkard (){ //Creates a drunkard with a mysterious name because he is too drunk to tell us
    this.name = "?!$!?2%!";
    this.dialogue = new String[0];
  }

  /**
   * interacts with the drunk
   * @param account is the user's account
   */
  @Override
  public void interact (Account account){

    System.out.print("\033[H\033[2J");  //clears
    System.out.flush();  

    System.out.println(this.name + "\n"); //shows name

    System.out.print("Who the ********* *hic* R u!?");
    sc.nextLine();
    System.out.print("U look laick *hic* somewon who has depression!");
    sc.nextLine();
    System.out.print("Just like me!");
    sc.nextLine();
    System.out.print("Do u *hic* want to listen to ma storee?\nEnter \"Yes\" to listen: ");
    
    if (sc.nextLine().equals("Yes")){ //If they decide to listen to the story, tell them the tale of how he realised that "things" are gone

      System.out.print("Sutch a nice *hic* person!");
      sc.nextLine();
      System.out.print("U see, it all happened too ears ago");
      sc.nextLine();
      System.out.print("Ma waife left me because *hic* i didnt have job");
      sc.nextLine();
      System.out.print("That ******* slapped *hic* me in the face into a concussion");
      sc.nextLine();
      System.out.print("When i woke up from ma *hic* coma");
      sc.nextLine();
      System.out.print("i wanted to bless maself with some good ol ********");
      sc.nextLine();
      System.out.print("but I learned *hic* that the ******** site got shut down");
      sc.nextLine();
      System.out.print("ever since then *hic*, ive been drinking nonstop to cure my depression");
      sc.nextLine();
      System.out.print("its a hard World *hic* out there boi");
      sc.nextLine();

    } else { //if not, tell them to ******** off

      System.out.print("man, *hic* ******* u and ur fAmily");
      sc.nextLine();
      System.out.print("get the ******* out of *hic* ma site");
      sc.nextLine();

    }
    
    System.out.print("\nDepression +1");
    sc.nextLine();


  }

}

