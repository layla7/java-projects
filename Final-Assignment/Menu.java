import java.util.Scanner;
/**
 * Write a description of class Menu here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Menu
{
    public static void main(String args[]){
        Menu myMenu =  new Menu();
        myMenu.processUserChoices();
    }
    
    /**
     * Displays options of methods to run
     */
    public void displayMenu(){
        System.out.println("please select one of the options below");
        System.out.println("1. New Game");
        System.out.println("2. Load Previous Game");
        System.out.println("0. Exit");
    }
    
    public void processUserChoices(){
        Game myGame = new Game();
        int userChoice;
        boolean exitMenu = false;
        while(exitMenu == false){
            displayMenu();
            Scanner s1 = new Scanner(System.in);
            userChoice = s1.nextInt();
            switch(userChoice){
                case 1:
                    myGame.runGame();
                    break;
                case 0:
                    System.out.println("Exiting game...");
                    System.exit(0);
                default:
                    System.out.println("Input not recognised");
            }
        }
    }
}
