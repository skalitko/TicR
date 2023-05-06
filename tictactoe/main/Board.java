package tictactoe.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    private final static int[] HORIZ_ONE = {1, 2, 3};
    private final static int[] HORIZ_TWO = {4, 5, 6};
    private final static int[] HORIZ_THREE = {7, 8, 9};
    private final static int[] VERT_ONE = {1, 4, 7};
    private final static int[] VERT_TWO = {2, 5, 8};
    private final static int[] VERT_THREE = {3, 6, 9};
    private final static int[] DIAG_ONE = {1, 5, 9};
    private final static int[] DIAG_TWO = {3, 5, 7};

    private final static int[][] SET_OF_LINES = {HORIZ_ONE, HORIZ_TWO, HORIZ_THREE, VERT_ONE, VERT_TWO, VERT_THREE, DIAG_ONE, DIAG_TWO};
    private final Sign[][] board;

    public Board() {
        board = new Sign[3][];
        for (int i = 0; i < 3; i++) {
            board[i] = new Sign[3];
            Arrays.fill(board[i], Sign.N);
        }
    }

    public void print() {
        System.out.println(Arrays.stream(board)
                .map(Board::composeLine).collect(Collectors.joining("\n–+–+–\n")));
    }

    private static String composeLine(Sign[] line) {
        return Arrays.stream(line).map(s -> Character.toString(s.value)).collect(Collectors.joining("|"));
    }

    private Sign getCell(int position) {
        position--;
        int i = position / 3;
        int j = position % 3;
        return board[i][j];
    }

    public boolean setCell(int position, Sign sign) {
        Sign cell = getCell(position);
        if (Sign.N.equals(cell)) {
            position--;
            int i = position / 3;
            int j = position % 3;
            board[i][j] = sign;
            isGameOver();
            return true;
        }
        return false;
    }

    public void isGameOver() {
        if (checkForWinner() || areAllCellsFilled()) {
            System.exit(0);
        }
    }

    private boolean areAllCellsFilled() {
        for (Sign[] line : board) {
            for (Sign cell : line) {
                if (Sign.N.equals(cell))
                    return false;
            }
        }
        showResult("GAME OVER - No Free Cells anymore");
        return true;
    }

    private boolean checkForWinner() {
        for (int[] line : SET_OF_LINES) {
            if (isWinnerInLine(line)) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinnerInLine(int[] checkCells) {
        Set<Sign> resultSet = new HashSet<>();

        for (int cell : checkCells) {
            Sign cellValue = getCell(cell);

            //Some fields are empty
            if (Sign.N.equals(cellValue))
                return false;

            resultSet.add(cellValue);

            //Line is not occupied by single player
            if (resultSet.size() > 1)
                return false;
        }
        String resultString = "GAME FINISHED!!! You " + (Sign.X.equals(getCell(checkCells[0])) ? "Win!!!" : "Lose =(");
        showResult(resultString);
        return true;
    }

    private void showResult(String resultString) {
        this.print();
        System.out.println("********************************");
        System.out.println(resultString);
        System.out.println("********************************");
    }
}
