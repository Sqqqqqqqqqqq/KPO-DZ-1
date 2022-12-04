public class vsBot {
    private static void botTurn(Field field, Gamer simpleBot) {
        playerVsPlayer.getMoves(field, simpleBot);
        if (playerVsPlayer.possibleMovesX.isEmpty() || playerVsPlayer.possibleMovesY.isEmpty()) {
            playerVsPlayer.clearPossibleMoves();
            field.turn = !field.turn;
            return;
        }
        simpleBot.move(field, playerVsPlayer.xMax, playerVsPlayer.yMax);
        playerVsPlayer.clearPossibleMoves();
    }

    public static void run(Field field, Gamer gamer) {
        field.setDefault();
        Gamer simpleBot = new Gamer();
        playerVsPlayer.randomSetColors(simpleBot, gamer);
        System.out.printf("Игрок 1: %c\nБот: %c\n\n", gamer.getColor(), simpleBot.getColor());
        while (field.getWhiteScore() + field.getBlackScore() < 64) {
            if (!field.turn && gamer.getColor() == 'B' || field.turn && gamer.getColor() == 'W') {
                int tmp = playerVsPlayer.inputChecker(field, gamer);
                if (tmp == 1) {
                    field.setDefault();
                    gamer.setScore(0);
                    simpleBot.setScore(0);
                    return;
                } else if (tmp == 2) {
                    continue;
                }
            }
            botTurn(field, simpleBot);
            if (gamer.getColor() == 'W') {
                gamer.setScore(field.getWhiteScore());
                simpleBot.setScore(field.getBlackScore());
            } else {
                simpleBot.setScore(field.getWhiteScore());
                gamer.setScore(field.getBlackScore());
            }
        }
        field.printScore();
        field.printField();
        if (field.getWhiteScore() > field.getBlackScore()) System.out.println("Победили белые!");
        else if (field.getWhiteScore() < field.getBlackScore()) System.out.println("Победили черные!");
        else System.out.println("Ничья!");
    }
}
