/**
 * Created by Abdullah A on 2017-07-13
 */

import java.util.Scanner;


public class MazeRunner {
    public static void main(String[] args) {
        Maze myMap = new Maze();
        intro(myMap);
        userMove(0, myMap);

    }

    public static void intro(Maze myMap) {
        System.out.println("Welcome to the Maze Runner!");
        System.out.println("In this maze, you have 100 turns to survive. You will face pits and walls that you must surpass, may the odds ever be in your favour.\n");
        System.out.println("Here is your current position:");
        System.out.println("---------------------------------------");
        myMap.printMap();
    }

    public static void userMove(int moves, Maze myMap) {

        System.out.println("Where would you like to move? (R, L, U, D)");

        Scanner movementScan = new Scanner(System.in);
        String movement = movementScan.next();

        //Making sure user enters R, L, U, or D:
        while (!movement.equals("R") && !movement.equals("L") && !movement.equals("U") && !movement.equals("D")) {
            System.out.println("Please enter \"R\", \"L\", \"U\", or \"D\"");
            movement = movementScan.next();
        }

        //Movement:

        if (movement.equals("R") && myMap.canIMoveRight() == true) {
            myMap.moveRight();
            myMap.printMap();
            moves++;
            moveMessages(moves, myMap);
        }

        else if (movement.equals("R") && myMap.canIMoveRight() == false && myMap.isThereAPit(movement) == false) {
            myMap.printMap();
            System.out.println("Sorry, you've hit a wall.");
        }


        if (movement.equals("L") && myMap.canIMoveLeft() == true) {
            myMap.moveLeft();
            myMap.printMap();
            moves++;
            moveMessages(moves, myMap);
        }
        else if (movement.equals("L") && myMap.canIMoveLeft() == false && myMap.isThereAPit(movement) == false) {
            myMap.printMap();
            System.out.println("Sorry, you've hit a wall.");
        }


        if (movement.equals("U") && myMap.canIMoveUp() == true) {
            myMap.moveUp();
            myMap.printMap();
            moves++;
            moveMessages(moves, myMap);
        }
        else if (movement.equals("U") && myMap.canIMoveUp() == false && myMap.isThereAPit(movement) == false) {
            myMap.printMap();
            System.out.println("Sorry, you've hit a wall.");
         }


        if(movement.equals("D") && myMap.canIMoveDown() == true) {
            moves++;
            myMap.moveDown();
            myMap.printMap();
            moveMessages(moves, myMap);
        }
        else if(movement.equals("D") && myMap.canIMoveDown() == false && myMap.isThereAPit(movement) == false) {
            myMap.printMap();
            System.out.println("Sorry, you've hit a wall.");
        }


        if(myMap.didIWin() == false) {
            navigatePit(moves, movement, myMap);
            userMove(moves, myMap);
        }

    }

    public static void moveMessages(int moves, Maze myMap) {
        System.out.println("Moves: " + moves);

        if(myMap.didIWin() == false) {
            if(moves == 50)
                System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
            if(moves == 75)
                System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
            if(moves == 90)
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");

            if(moves == 100) {
                System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
                throw new IllegalArgumentException("Game over.");
            }

            userMove(moves, myMap); //Ask for next move

        }
        else if(myMap.didIWin() == true)
            System.out.println("Congratulations, you made it out alive!\nYou've finished the maze in " + moves + " moves");
    }

    public static void navigatePit(int moves, String movement, Maze myMap) {

        Scanner PitScan = new Scanner(System.in);

        if(myMap.isThereAPit(movement) == true) {
            System.out.println("Watch out, there's a pit ahead! Would you like to jump it?");
            String jumpPit = PitScan.next();

            if(jumpPit.indexOf("y") == 0 || jumpPit.indexOf("Y") == 0) {
                myMap.jumpOverPit(movement);
                myMap.printMap();
                moves++;
                moveMessages(moves, myMap);
            }
        }
    }
}
