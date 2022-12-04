import java.util.Scanner;

public class Runner {
    public static void printOptions() {
        System.out.print("""
                /help - Вывод существующих опций
                /pvp - Режим игрок против игрока
                /moveBack - Отменить последний ход
                /vsBot - режим игры против компьютера
                /vsSmartBot - Усложненный режим игры против компьютера
                /endGame - Досрочно завершить игру
                /exit - Выйти из игры
                /getScore - Вывести очки игроков
                """);
    }
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Field field = new Field();
        Gamer gamer1 = new Gamer();
        Gamer gamer2 = new Gamer();
        field.setDefault();
        System.out.println("Главное меню:");
        printOptions();
        while (true) {
            String whatToDo = scanner.nextLine();
            if (whatToDo.equals("/pvp")) {
                playerVsPlayer.run(field, gamer1, gamer2);
                System.out.println("Главное меню:");
                printOptions();
            } else if (whatToDo.equals("/endGame") || whatToDo.equals("/moveBack")) {
                System.out.println("Опция сейчас недоступна\nПопробуйте еще раз:");
            } else if (whatToDo.equals("/exit")) {
                System.out.println("¯\\_(ツ)_/¯");
                break;
            } else if (whatToDo.equals("/help")) {
                printOptions();
            } else if (whatToDo.equals("/vsSmartBot")) {
                System.out.println("В разработке!");
            } else if (whatToDo.equals("/getScore")) {
                System.out.println("Игрок 1: " + gamer1.getScore() + '\n' + "Игрок 2: " + gamer2.getScore());
            } else if (whatToDo.equals("/vsBot")) {
                field.setDefault();
                vsBot.run(field, gamer1);
            }
            else {
                System.out.println("Такой опции нет\nПопробуйте еще раз:");
            }
        }
    }
}
