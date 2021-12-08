import java.util.Scanner;
import java.util.Random;
import java.io.*;
import java.lang.*;
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{   
    String[][] gridValues = new String[7][8];
    String counterType;
    String colNumbers;
    
    public Game(){
        for(int row = 0; row < gridValues.length; row++){
            for(int col = 0; col < gridValues[0].length; col++){
                gridValues[row][col] = "-";
            }
        }
        
        colNumbers = "1 2 3 4 5 6 7 8";
    }
    
    public void runGame(){
        displayBoard();
        boolean end = false;
        int i=0;
        while(end != true){
            if((i % 2) == 0) {
                placeCounter(getCompCol());
                i++;
                end = checkGameState();
                if(end == true){
                    System.out.println("Player 2 Wins!");
                    System.exit(0);
                }
            }
            else{
                int userCol = Integer.parseInt(getUserCol()) - 1;
                placeCounter(userCol);
                i++;
                end = checkGameState();
                if(end == true){
                    System.out.println("Player 1 Wins!");
                    System.exit(0);
                }
            }
            
            if(i >= 8){
                for(int row = 0; row < gridValues.length; row++){
                for(int col = 0; col < gridValues[0].length; col++){
                    gridValues[0][col] = " ";
                    gridValues[row][7] = " ";
                    colNumbers = "1 2 3 4 5 6 7";
                }
            }
            }
            
            if(i >= 16){
                for(int row = 0; row < gridValues.length; row++){
                for(int col = 0; col < gridValues[0].length; col++){
                    gridValues[1][col] = " ";
                    gridValues[row][6] = " ";
                    colNumbers = "1 2 3 4 5 6";
                }
            }
            }
        }
    }
    
    public void saveGame(){
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
        try{
            outputStream = new FileOutputStream("save.txt");
            printWriter = new PrintWriter(outputStream);
            
            for(int row = 0; row < gridValues.length; row++){
                printWriter.println(gridValues[row]);
            }
        }
        catch (IOException e){
            System.out.println("Sorry there has been a problem opening or writing to the file");
            System.out.println("/t" + e);
        }
        
        finally
        {
            if (printWriter != null){
                printWriter.close();
            }
        }
    }
    
    public void displayBoard(){
        for(int row = 0; row <  gridValues.length; row++){
            System.out.print("\t");
            for(int col = 0; col < gridValues[row].length; col++){
                System.out.print(gridValues[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("\t");
        System.out.println("\t" + colNumbers);
    }
    
    public String getUserCol(){
        String userCol = "";
        boolean validInput = false;
        counterType = "O";
        Scanner sc = new Scanner(System.in);
        while(validInput != true){
            System.out.println("Enter the column you wish to place your counter in:");
            userCol = sc.nextLine();
            validInput = validateInput(userCol);
        }
        return userCol;
    }
    
    public int getCompCol(){
        System.out.println("Computer placing counter...");
        counterType = "X";
        Random rand = new Random();
        int value = rand.nextInt(8);
        return value;
    }
    
    public void placeCounter(int counterCol){
        boolean placed = false;
        for(int row = gridValues.length - 1; row >= 0; row--){
           if(gridValues[row][counterCol] == "-" && placed == false){
               gridValues[row][counterCol] = counterType;
               placed = true;
           }
        }
            
        displayBoard();
    }
    
    public boolean checkGameState(){
        int i = 0;
        //check horizontal
        for(int row = 0; row <  gridValues.length; row++){
            for(int col = 0; col < gridValues[0].length - 3; col++){
                if(gridValues[row][col] == counterType &&
                    gridValues[row][col + 1] == counterType &&
                    gridValues[row][col + 2] == counterType &&
                    gridValues[row][col + 3] == counterType){
                        return true;
                }
            }
        }
        
        //check up and down
        for(int row = 0; row <  gridValues.length - 3; row++){
            for(int col = 0; col < gridValues[0].length; col++){
                if(gridValues[row][col] == counterType &&
                    gridValues[row + 1][col] == counterType &&
                    gridValues[row + 2][col] == counterType &&
                    gridValues[row + 3][col] == counterType){
                        return true;
                }
            }
        }
        
        // check up diag
        for(int row = 0; row < gridValues.length; row++){
            for(int col = 0; col < gridValues[0].length - 3; col++){
                if(gridValues[row][col] == counterType &&
                    gridValues[row - 1][col + 1] == counterType &&
                    gridValues[row - 2][col + 2] == counterType &&
                    gridValues[row - 3][col + 3] == counterType){
                        return true;
                }
            }
        }
        
        //check down diag
        for(int row = 0; row < gridValues.length - 3; row++){
            for(int col = 0; col < gridValues[0].length - 3; col++){
                if(gridValues[row][col] == counterType &&
                    gridValues[row + 1][col + 1] == counterType &&
                    gridValues[row + 2][col + 2] == counterType &&
                    gridValues[row + 3][col + 3] == counterType){
                        return true;
                }
            }
        }
        
        return false;
    }
    
    private static boolean validateInput(String input){
        boolean valid = true;
        int intInput;
        if(input == null){
            valid = false;
        }
        try{
            intInput = Integer.parseInt(input);
        } catch(NumberFormatException e){
            valid = false;
            intInput = 0;
        }
        if(intInput > 8 || intInput < 1){
            valid = false;
        }
        if(valid == false){
            System.out.println("Invalid");
        }
        return valid;
    }
}