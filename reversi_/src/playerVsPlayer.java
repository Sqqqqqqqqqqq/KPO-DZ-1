import java.util.*;

public class playerVsPlayer {
    private static final Field oldField = new Field();
    public static ArrayList<Integer> possibleMovesX = new ArrayList<>();
    public static ArrayList<Integer> possibleMovesY = new ArrayList<>();
    public static int xMax = 0;
    public static int yMax = 0;
    public static void randomSetColors(Gamer gamer1, Gamer gamer2) {
        Random random = new Random();
        if (random.nextInt(100) > 50) {
            gamer1.setColor('W');
            gamer2.setColor('B');
        } else {
            gamer1.setColor('B');
            gamer2.setColor('W');
        }
    }

    public static void getMoves(Field field, Gamer gamer) {
        int max = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int tmp = gamer.subMove(field, 1, i, j, false);
                tmp += gamer.subMove(field, -1, i, j, false);
                if (tmp != 0 && field.getColor(i, j) == '.') {
                    possibleMovesX.add(i);
                    possibleMovesY.add(j);
                    if (tmp > max) {
                        xMax = i;
                        yMax = j;
                        max = tmp;
                    }
                }
            }
        }
    }

    public static void printMoves() {
        System.out.println("Возможные ходы:");
        for (int i = 0; i < possibleMovesX.size(); i++) {
            System.out.printf("%d) %c %d\n", i + 1, possibleMovesY.get(i) + 'a', possibleMovesX.get(i) + 1);
        }
    }

    public static void clearPossibleMoves() {
        possibleMovesX.clear();
        possibleMovesY.clear();
    }

    static int inputChecker(Field field, Gamer gamer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ход делает: " + (char) gamer.getColor());
        getMoves(field, gamer);
        if (possibleMovesX.isEmpty()) {
            return 0;
        }
        field.printScore();
        field.printField();
        printMoves();
        char tmp;
        int y;
        int x;
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("/moveBack")) {
                field.clone(oldField);
                return 2;
            } else if (input.equals("/endGame")) {
                System.out.println("Главное меню:");
                Runner.printOptions();
                return 1;
            } else if (input.equals("/help")) {
                Runner.printOptions();
                continue;
            } else if (input.length() != 3) {
                System.out.println("Некорректный ввод\nПопробуйте еще раз:");
                continue;
            }
            Field tmpField = new Field();
            tmpField.clone(oldField);
            oldField.clone(field);
            tmp = input.charAt(0);
            y = tmp - 'a';
            x = input.charAt(2) - '1';
            if (tmp < 'a' || tmp > 'h') {
                oldField.clone(tmpField);
                System.out.println("Буква не входит в диапазон от 'a' до 'h'\nПопробуйте еще раз:");
                continue;
            }
            if (x < 0 || x > 7) {
                oldField.clone(tmpField);
                System.out.println("Числа должны быть больше от 1 до 8\nПопробуйте еще раз:");
                continue;
            }
            if (gamer.move(field, x, y) == 0) {
                oldField.clone(tmpField);
                System.out.println("Нельзя совершить данный ход\nПопробуйте еще раз:");
            } else {
                clearPossibleMoves();
                field.setScore();
                break;
            }
            clearPossibleMoves();
        }
        return 0;
    }

    public static void run(Field field, Gamer gamer1, Gamer gamer2) {
        playerVsPlayer.randomSetColors(gamer1, gamer2);
        System.out.printf("Игрок 1: %c\nИгрок 2: %c\n\n", gamer1.getColor(), gamer2.getColor());
        while (field.getWhiteScore() + field.getBlackScore() < 64) {
            if (!field.turn && gamer1.getColor() == 'B' || field.turn && gamer1.getColor() == 'W') {
                if (inputChecker(field, gamer1) == 1) {
                    field.setDefault();
                    gamer1.setScore(0);
                    gamer2.setScore(0);
                    return;
                }
            } else {
                if (inputChecker(field, gamer2) == 1) {
                    field.setDefault();
                    gamer1.setScore(0);
                    gamer2.setScore(0);
                    return;
                }
            }
            if (gamer1.getColor() == 'W') {
                gamer1.setScore(field.getWhiteScore());
                gamer2.setScore(field.getBlackScore());
            } else {
                gamer2.setScore(field.getWhiteScore());
                gamer1.setScore(field.getBlackScore());
            }
        }
        field.printScore();
        field.printField();
        if (field.getWhiteScore() > field.getBlackScore()) System.out.println("Победили белые!");
        else if (field.getWhiteScore() < field.getBlackScore()) System.out.println("Победили черные!");
        else System.out.println("Ничья!");
        System.out.println("Главное меню:");
        Runner.printOptions();
    }
}
