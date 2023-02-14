import java.util.Scanner;

public class StartGame {

    public static final String emptyCell = "   ", cross = " X ", zero = " O ";
    public static String player, winner;

    public static final int lines = 3, columns = 3;

    public static String[][] grid = new String[lines][columns];

    public static int gameStatus;
    public static int gameContinue = 0, gameTie = 1, xWin = 3, oWin = 4;

    public static Scanner input = new Scanner(System.in);
    public static boolean res = false;

    public static void main(String[] args) {

        gameStart();
        do {
            input();
            gridAnalysis();
            printGrid();
            if (gameStatus == xWin){
                System.out.println("Player X is a winner! \nPlay again?\"");
            } else if (gameStatus == oWin) {
                System.out.println("Player O is a winner! \nPlay again?");
            } else if (gameStatus == gameTie){
                System.out.println("No winners :( \nPlay again?");
            }
            if (player == cross){
                player = zero;
            } else if (player == zero) {
                player = cross;
            }
        }
        while(gameStatus == gameContinue);

        System.out.println();



    }

    public static void gameStart() {
        for (int line = 0; line < lines ; line++) {
            for (int column = 0; column < columns; column++) {
                grid[line][column] = emptyCell;
            }
        }
        player = cross;
        printGrid();
    }

    public static void input() {
        boolean validInput = false;

        do {
            System.out.println("Player" + player + ", please input line & column (1-3) separated by space!");
            int line = input.nextInt() - 1;
            int column = input.nextInt() - 1;
            if (line >= 0 && line < lines && column >= 0 && column < columns && grid[line][column] == emptyCell){
                grid[line][column] = player;
                validInput = true;
            } else {
                System.out.println("Invalid input (" + (line + 1 ) + "," + (column + 1) + ")! Please try again!");
            }
        }
        while (!validInput);
    }

    public static String winnersFind() {
        
        // horizontal
        int same = 0;
        for (int line = 0; line < lines; line++) {
            for (int column = 0; column < columns; column++) {
                if (grid[line][0] != emptyCell && grid[column][01] != emptyCell){
                    same++;
                }
            }
            if (same == 3){
                return grid[line][0];
            }
        }
        //  vertical
        for (int column = 0; column < columns; column++) {
            same = 0;
            for (int line = 0; line < lines; line++) {
                if (grid[0][column] != emptyCell && grid[0][column] == grid[line][column]){
                    same++;
                }
            }
            if (same == 3){
                return grid[0][column];
            }
        }

        // diagonal ->
        if (grid[0][0] != emptyCell && grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
            return grid[0][0];
        }
        //  diagonal <-
        if (grid[0][2] != emptyCell && grid[1][1] == grid[0][2] && grid[2][0] == grid[0][2]) {
            return grid[0][2];
        }
        return emptyCell;
    }

    public static void gridAnalysis() {

        winner = winnersFind();

        if (winner.equals(cross)) {
            gameStatus = xWin;
        } else if (winner.equals(zero)) {
            gameStatus = oWin;
        } else if (AllGridFiled()) {
            gameStatus = gameTie;
        } else {
            gameStatus = gameContinue;
        }
    }

    public static boolean AllGridFiled() {

        for (int line = 0; line < lines; line++) {
            for (int column = 0; column < columns; column++) {
                if (grid[line][column] == emptyCell) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printGrid() {
        for (int line = 0; line < lines; line++) {
            for (int column = 0; column < columns; column++) {
                System.out.print(grid[line][column]);
                if (column != columns -1){
                    System.out.print("|");
                }
            }
            System.out.println();
            if (line != lines - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }
}
