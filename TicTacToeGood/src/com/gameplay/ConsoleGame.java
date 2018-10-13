package com.gameplay;

import java.util.Scanner;

public class ConsoleGame {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("\n--- Welcome to Tic-Tac-Toe! ---\n");

        // Determine user and Computer
        System.out.print("Would you like to be X or O? ");
        String selection = in.next();
        char user = selection.toUpperCase().charAt(0);
        while (!(user == 'X' || user == 'O')) {
            System.out.println("Please enter X or O");
            System.out.print("Would you like to be X or O? ");
            selection = in.next();
            user = selection.toUpperCase().charAt(0);
        }
        char cpu;
        if (user == 'X') {
            cpu = 'Y';
        } else {
            cpu = 'X';
        }
        System.out.println("You are " + user + ".");

        // game loop
        boolean keepPlaying = true;
        while (keepPlaying) {

            // create new Board
            Board b = new Board();

            // Determine first move
            b.firstTurn();
            char turn;
            if ((user == 'X' && b.xTurn) || (user == 'O' && b.oTurn)) {
                System.out.println("\nYour turn!\n");
                turn = 'U';
            } else {
                System.out.println("\nCPU's turn!\n");
                turn = 'C';
            }


            // obtain moves
            int move;
            char winner = 'T';
            int movesPerformed = 0;
            while (!b.winner || !b.tie) {
                if (movesPerformed == 9) {
                    b.tie = true;
                    break;
                }
                if (turn == 'U') {
                    System.out.println(b.toStringWithIndex());
                    boolean validMove = false;
                    while (!validMove) {
                        try {
                            System.out.print("\nEnter the code of the location you would like to go: ");
                            move = in.nextInt();
                            b.recordMove(move);
                            validMove = true;
                        } catch (RuntimeException e) {
                            System.out.println("You cannot make your move there! Try again.");
                        }
                    }
                    winner = b.determineWinner();
                    System.out.println();
                    System.out.println(b.toString());
                    turn = 'C';
                }
                else {
                    System.out.println();
                    System.out.println("CPU has made their move.\n");

                    boolean recordedMove = false;
                    while (!recordedMove) {
                        try {
                            int x = b.random.nextInt(b.board.length);
                            b.recordMove(x);
                            recordedMove = true;
                        } catch (RuntimeException e) {
                        }
                    }
                    winner = b.determineWinner();
                    System.out.println();
                    System.out.println(b.toString());
                    System.out.println();
                    turn = 'U';
                }
                movesPerformed++;
            }

            // determine winner, celebrate
            System.out.println();
            if (winner == user) {
                System.out.println("You Win!!!");
            }
            else if (winner == cpu) {
                System.out.println("CPU Wins!!!");
            }
            else if (b.tie){
                System.out.println("It's a Tie!!!");
            }
            else {
                System.out.println("I'm not sure");
            }

            System.out.println();
            boolean validResponse = false;
            String response;
            while (!validResponse) {
                System.out.print("Would you like to play again? (Y/N) ");
                response = in.next();
                if (response.toUpperCase().charAt(0) == 'Y') {
                    validResponse = true;
                } else if (response.toUpperCase().charAt(0) == 'N') {
                    validResponse = true;
                    keepPlaying = false;
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("That is not a valid response.");
                }
            }
        }
    }
}
