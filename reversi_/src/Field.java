public class Field {
    private final int[][] board = new int[8][8];
    boolean turn = false;
    private int whiteScore = 2;
    private int blackScore = 2;

    public void setScore() {
        whiteScore = 0;
        blackScore = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) whiteScore++;
                else if (board[i][j] == 2) blackScore++;
            }
        }
    }

    public void clone(Field b) {
        for (int i = 0; i < 8; i++) {
            System.arraycopy(b.board[i], 0, this.board[i], 0, 8);
        }
        this.turn = b.turn;
        this.whiteScore = b.whiteScore;
        this.blackScore = b.blackScore;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public int getBlackScore() {
        return blackScore;
    }

    public void printScore() {
        System.out.print("Белые: " + whiteScore + " Черные: " + blackScore);
    }

    public void printField() {
        System.out.println();
        for (int i = 1; i < 9; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < 8; j++) {
                    System.out.printf("%c | ", this.getColor(i - 1, j));
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int i = 0; i < 8; i++) {
            System.out.printf("   %c", 'a' + i);
        }
        System.out.println();
    }

    public char getColor(int corX, int corY) {
        if (board[corX][corY] == 1) {
            return 'W';
        } else if (board[corX][corY] == 2) {
            return 'B';
        } else {
            return '.';
        }
    }

    public void setDefault() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
                    board[i][j] = 1;
                } else if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
                    board[i][j] = 2;
                } else {
                    board[i][j] = 0;
                }
            }
        }
        whiteScore = 2;
        blackScore = 2;
        turn = false;
    }

    public void changeColor(char color, int corX, int corY) {
        board[corX][corY] = (color == 'W') ? 1 : 2;
    }
}
