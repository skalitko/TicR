package tictactoe.main;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private final Board board;

    public TicTacToe() {
        board = new Board();
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                board.print();
                do {
                    System.out.println("Please select next cell");
                } while (!playerMove(scanner.nextInt()));
                computerMove();
            }
        }

    }

    private void computerMove() {
        Random random = new Random();

        while (true) {
            int position = random.nextInt(9) + 1;
            if (board.setCell(position, Sign.O)) break;
        }
    }

    private boolean playerMove(int cell) {
        return board.setCell(cell, Sign.X);
    }
}
