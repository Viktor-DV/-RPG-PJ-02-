import java.util.Scanner;

public class Trainer {
    private Scanner scanner;

    public Trainer(Scanner scanner) {
        this.scanner = scanner;
    }

    public void interactWithPlayer(Player player) {
        while (true) {
            System.out.println("Ну, что тренируем сегодня (o-_-o)");
            System.out.println("1. Повысить силу");
            System.out.println("2. Повысить ловкость");
            System.out.println("3. Выйти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    player.increaseStrength();
                    break;
                case 2:
                    player.increaseAgility();
                    break;
                case 3:
                    System.out.println("Вы покинули тренировку.");
                    return;
                default:
                    System.out.println("Так нельзя.");
            }
        }
    }
}
