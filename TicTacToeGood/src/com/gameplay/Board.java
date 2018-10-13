package com.gameplay;

import java.util.Random;

public class Board {

    protected char[] board;
    protected boolean winner;
    protected boolean xWins;
    protected boolean oWins;
    protected boolean xTurn;
    protected boolean oTurn;
    protected boolean tie;
    protected Random random = new Random();
    protected char user;
    protected char cpu;


    public Board() {
        board = new char[9];
        for (int i=0; i < board.length; i++) {
            board[i] = ' ';
        }
        winner = false;
        xWins = false;
        oWins = false;
        tie = false;
    }
    
    public Board(char user, char cpu) {
        board = new char[9];
        for (int i=0; i < board.length; i++) {
            board[i] = ' ';
        }
        winner = false;
        xWins = false;
        oWins = false;
        tie = false;
        this.user = user;
        this.cpu = cpu;
    }

    public String toString() {
        return  "     |     |  " + '\n'
                +  "  " + board[0] + "  |" + "  " + board[1] + "  |" + "  " + board[2] + '\n'
                + "_____|_____|_____" + '\n'
                + "     |     |  " + '\n'
                + "  " + board[3] + "  |" + "  " + board[4] + "  |" + "  " + board[5] + '\n'
                + "_____|_____|_____" + '\n'
                + "     |     |  " + '\n'
                + "  " + board[6] + "  |" + "  " + board[7] + "  |" + "  " + board[8] + '\n'
                + "     |     |  ";
    }

    public String toStringWithIndex() {
        return  "     |     |  " + '\n'
                +  "  " + 0 + "  |" + "  " + 1 + "  |" + "  " + 2 + '\n'
                + "_____|_____|_____" + '\n'
                + "     |     |  " + '\n'
                + "  " + 3 + "  |" + "  " + 4 + "  |" + "  " + 5 + '\n'
                + "_____|_____|_____" + '\n'
                + "     |     |  " + '\n'
                + "  " + 6 + "  |" + "  " + 7 + "  |" + "  " + 8 + '\n'
                + "     |     |  ";
    }

    public void recordX(int i) {
        if (i >= board.length) {
            throw new RuntimeException("Index out of range");
        }
        if (board[i] == ' ') {
            board[i] = 'X';
            this.oTurn = true;
            this.xTurn = false;
        }
        else {
            throw new RuntimeException("Position already occupied");
        }
    }

    public void recordO(int i) {
        if (i >= board.length) {
            throw new RuntimeException("Index out of range");
        }
        if (board[i] == ' ') {
            board[i] = 'O';
            this.xTurn = true;
            this.oTurn = false;
        }
        else {
            throw new RuntimeException("Position already occupied");
        }
    }


    public void firstTurn() {
        int x = random.nextInt(2);

        if (x == 0) {
            xTurn = true;
            oTurn = false;
        }
        else {
            oTurn = true;
            xTurn = false;
        }
    }

    public boolean moveAvailable() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                return true;
            }
        }
        return false;
    }

    public void recordMove(int i) {
        if (moveAvailable()) {
            if (xTurn) {
                recordX(i);
            } else {
                recordO(i);
            }
        }
    }


     public char determineWinner() {
        checkX();
        if (xWins) {
            return 'X';
        }
        checkO();
        if (oWins) {
            return 'O';
        }
        else {
            // Tie
            this.tie = true;
            return 'T';
        }
    }

    private void checkX() {
        if (       (this.board[0] == 'X' && this.board[1] == 'X' && this.board[2] == 'X')
                || (this.board[3] == 'X' && this.board[4] == 'X' && this.board[5] == 'X')
                || (this.board[6] == 'X' && this.board[7] == 'X' && this.board[8] == 'X')
                || (this.board[0] == 'X' && this.board[3] == 'X' && this.board[6] == 'X')
                || (this.board[1] == 'X' && this.board[4] == 'X' && this.board[7] == 'X')
                || (this.board[2] == 'X' && this.board[5] == 'X' && this.board[8] == 'X')
                || (this.board[0] == 'X' && this.board[4] == 'X' && this.board[8] == 'X')
                || (this.board[2] == 'X' && this.board[4] == 'X' && this.board[6] == 'X')) {
            this.xWins = true;
            this.winner = true;
        }
    }

    private void checkO() {
        if (       (this.board[0] == 'O' && this.board[1] == 'O' && this.board[2] == 'O')
                || (this.board[3] == 'O' && this.board[4] == 'O' && this.board[5] == 'O')
                || (this.board[6] == 'O' && this.board[7] == 'O' && this.board[8] == 'O')
                || (this.board[0] == 'O' && this.board[3] == 'O' && this.board[6] == 'O')
                || (this.board[1] == 'O' && this.board[4] == 'O' && this.board[7] == 'O')
                || (this.board[2] == 'O' && this.board[5] == 'O' && this.board[8] == 'O')
                || (this.board[0] == 'O' && this.board[4] == 'O' && this.board[8] == 'O')
                || (this.board[2] == 'O' && this.board[4] == 'O' && this.board[6] == 'O')) {
            this.oWins = true;
            this.winner = true;
        }
    }
}
