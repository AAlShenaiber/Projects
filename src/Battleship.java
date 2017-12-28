/**
 * Created by Abdullah A on 2017-06-21
 */
import java.util.*;

public class Battleship {
    public static void main(String[] args) {
        System.out.println("**** Welcome to the Battleships! ****\n");
        System.out.println("Right now the sea is empty");

        char[][] Grid = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        printGrid(Grid,0, 5, 5);

    }

    public static void printGrid(char[][] Grid, int repetitions, int playerShips, int computerShips) {

        System.out.println("\n  0123456789  ");
        System.out.println("  ----------  ");

        //Printing the 10x10 grid
        int count = 0;
        for(int i=0; i<10; i++) {
            String a = Arrays.toString(Grid[i]);

            a = a.replace("[", "");               //removing the characters Arrays.toString adds
            a = a.replace("]", "");
            a = a.replace(", ", "");

            a = a.replace("C", " ");              //making computers ships invisible to user

            System.out.println(count + "|" + a + "|" + count);      //printing every row, "a" being the grids row
            count++;
        }

        System.out.println("  ----------  ");
        System.out.println("  0123456789  ");

        if(repetitions == 0) {                                      //will only declare once
            deployShip(Grid, repetitions);                          //so user will only deploy the 5 ships once
        }

        else if(repetitions == -1) {                               //repetitions is -1 only for the battle phase

            System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips + "\n");

            Battle(Grid, playerShips, computerShips);
        }
    }

    public static void deployShip(char[][] Grid, int repetitions) {

        Scanner row = new Scanner(System.in);
        Scanner col = new Scanner(System.in);

        if(repetitions<5) {

            System.out.print("\nEnter the x coordinate for ship #" + (repetitions+1) + ": ");
            int xCoord = row.nextInt();

            while (xCoord > 9 || xCoord < 0) {                                  //if numbers are out of grid
                System.out.print("Please enter a number between 0 and 9: ");
                xCoord = row.nextInt();
            }

            System.out.print("Enter the y coordinate for ship #" + (repetitions+1) + ": ");
            int yCoord = col.nextInt();

            while (yCoord > 9 || yCoord < 0) {                                  //if numbers are out of grid
                System.out.print("Please enter a number between 0 and 9: ");
                yCoord = col.nextInt();
            }

            if (Grid[yCoord][xCoord] == ' ')                                    //if its an empty spot, occupy it
                Grid[yCoord][xCoord] = '@';

            else if (Grid[yCoord][yCoord] == '@') {                             //if a ship already occupies it, re-prompt
                System.out.println("\nYou already placed a ship there, please enter new coordinates...\n");
                deployShip(Grid, repetitions);
            }

            repetitions++;
            deployShip(Grid, repetitions);
        }

        else if(repetitions==5)
            deployComputer(Grid); //start computer's turn after user plays 5 ships
    }

    public static void deployComputer(char[][] Grid) {

        System.out.println("\n--------------------------------------");
        System.out.println("\nThe computer is deploying its ships...");

        Random rand = new Random();

        for(int i = 0; i<5; i++) {

            int row = rand.nextInt(10);
            int col = rand.nextInt(10);

            if(Grid[row][col] == ' ')                                   //if it's an empty space, occupy
                Grid[row][col] = 'C';

            else if(Grid[row][col] == 'C' || Grid[row][col] == '@')     //if its occupied, re-prompt
                i--;
        }

        System.out.print("\n--------------------------------------");
        printGrid(Grid, -1,5 , 5); //made repetitions -1 to start the battle in printGrid()
    }

    public static void Battle(char[][] Grid, int playerShips, int computerShips) {

        //PLAYERS TURN

        Scanner xScan = new Scanner(System.in);
        Scanner yScan = new Scanner(System.in);

        System.out.print("Please enter an x coordinate: ");
        int x = xScan.nextInt();

        while (x > 9 || x < 0) {
            System.out.print("Please enter a number between 0 and 9: ");
            x = xScan.nextInt();
        }

        System.out.print("Please enter a y coordinate: ");
        int y = yScan.nextInt();

        while (y > 9 || y < 0) {
            System.out.print("Please enter a number between 0 and 9: ");
            y = yScan.nextInt();
        }

        System.out.print("\n--------------------------------------");

        if(Grid[y][x] == 'C') {
            System.out.println("\nBOOM! You sunk an enemy ship!");
            computerShips--;
            Grid[y][x] = '□';
        }

        else if(Grid[y][x] == '@') {
            System.out.println("\nNOO! You sunk your own ship!");
            playerShips--;
            Grid[y][x] = '□';
        }

        else {
            System.out.println("\nYou missed.");
            Grid[y][x] = '*';
        }

        //COMPUTERS TURN

        Random rand = new Random();
        int xComp = rand.nextInt(10);
        int yComp = rand.nextInt(10);

        //wow! L'ordinateur is smart and wont kill its own ship or choose guessed positions:

        while(Grid[yComp][xComp] == 'C' || Grid[yComp][xComp] == '*' || Grid[yComp][xComp] == '□') {
            yComp = rand.nextInt(10);
        }

        if(Grid[yComp][xComp] == '@') {
            System.out.println("\nNOO! The computer sunk your ship!");
            playerShips--;
            Grid[yComp][xComp] = '□';
        }
        else {
            System.out.println("\nThe computer missed.");
            Grid[yComp][xComp] = '*';
        }

        System.out.print("--------------------------------------\n");

        //WINNING AND LOSING

        if(playerShips==0)
            System.out.println("\nAw no! The computer won. Better luck next time!");

        else if(computerShips==0)
            System.out.println("YOU WON! Congratulations, you sunk all the opponents ships!");

        else                                                                //if there's no winner, continue battle
            printGrid(Grid, -1, playerShips, computerShips);
    }
}
