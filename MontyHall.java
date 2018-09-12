//By Max S. Haberman

import javax.swing.JOptionPane;
public class MontyHall {

  public static void main(String[] args){
    Object[] options = {"Simulate", "Play"};
    int sims = 0, wins = 0, origWins = 0, end;
    int[] choice, winners, switchTo, shown;
    choice = new int[3];
    winners = new int[3];
    switchTo = new int[3];
    shown = new int[4];
    
    MHStuff play = new MHStuff();
    
    int start = JOptionPane.showOptionDialog(null,
                                             "How would you like to start?",
                                             "Monty Hall Problem",
                                             //JOptionPane.QUESTION_MESSAGE,
                                             JOptionPane.YES_NO_OPTION,
                                             JOptionPane.QUESTION_MESSAGE,
                                             null,
                                             options,
                                             options[0]);
    while (start == 0){
      choice[0] = choice[1] = choice[2] = winners[0] = winners[1] = winners[2] = switchTo[0] = switchTo[1] = switchTo[2] = 0;
      String hold = JOptionPane.showInputDialog("How many simulations?");
      sims = Integer.parseInt(hold);
      
      int result = 1;

      if (sims <= 5000){
        result = JOptionPane.showConfirmDialog(null,
                                                   "Would you like to see the each simulation?",
                                                   "Monty Hall Problem",
                                                   JOptionPane.YES_NO_OPTION);
      }
        
      
      end = 0;
      while (end != sims){
        end++;
        play.setWinner();
        play.setChoice((int)(3*Math.random()));
        
        if (play.getWinner() == play.getChoice())
          origWins++;
        
        choice[play.getChoice()]++;
        
        play.switchDoor();
        if (play.checkWin())
          wins++;
        
        winners[play.getWinner()]++;
        switchTo[play.getChoice()]++;
        shown[play.getShown()]++;
        
        if (result == 0){
          System.out.println("Original: " + play.getOriginal() + "  Shown: " + play.getShown() + "  Choice: " + play.getChoice() + "  Winner: " + play.getWinner() + "  Wins: " + wins +
                             "  Sims: " + end + "  WP: " + ((double)wins/(double)end));
        }
      }
      start = 2;
      
      
    }
    
    while(start == 1){
      boolean chose, seen, reveal;
      int again = 0;
      Object[] doors = {"Door 0", "Door 1", "Door 2"};
      
      while (again == 0){
        play.setWinner();
        chose = seen = reveal = false;
        System.out.println(play.doors(chose, seen, reveal));
        
        int selectDoor = JOptionPane.showOptionDialog(null,
                                                      play.doors(chose, seen, reveal),
                                                      "Select a door:",
                                                      JOptionPane.YES_NO_CANCEL_OPTION,
                                                      JOptionPane.QUESTION_MESSAGE,
                                                      null,
                                                      doors,
                                                      doors[2]);
        if (selectDoor == 0)
          play.setChoice(0);
        if (selectDoor == 1)
          play.setChoice(1);
        if (selectDoor == 2)
          play.setChoice(2);
        
        int switchQ = JOptionPane.showConfirmDialog(null,
                                                      play.doors(true, true, false),
                                                      "Switch to door " + play.getShown() + "?",
                                                      JOptionPane.YES_NO_OPTION);
        
        if (switchQ == 0){
          play.switchDoor();}
        
        String find;
        if (play.checkWin())
          find = "Win!";
        else
          find = "Lose!";
        
        again = JOptionPane.showConfirmDialog(null,
                                                play.doors(true, true, true),
                                                "You" + find + " Again?",
                                                JOptionPane.YES_NO_OPTION);
      }
      start = 0;
    }
    
    
    System.out.println("Orig.  0: " + choice[0] +  "  1: " + choice[1] + "  2: " + choice[2]);
    System.out.println("Shown  0: " + shown[0] + "  1: " + shown[1] + "  2: " + shown[2]);
    System.out.println("Switch 0: " + switchTo[0] +  "  1: " + switchTo[1] + "  2: " + switchTo[2]);
    System.out.println("Win    0: " + winners[0] +  "  1: " + winners[1] + "  2: " + winners[2]);
    
    System.out.println("Wins: " + wins + "  Simulations: " + sims + "  OrigPer: " + ((double)origWins/(double)sims));
    System.out.println("Win percentage: " + ((double)wins/(double)sims));
  }
}