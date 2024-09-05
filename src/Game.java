import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
    public void start() {
        System.out.println("Дорога завёт ٩(｡•́‿•̀｡)۶");
        createCharacter();
        while (true) {
            choosePath();
        }
    }

    private void createCharacter() {
        System.out.println("Введите имя вашего персонажа:");
        String name = scanner.nextLine();
        player = new Player(name);
        System.out.println("Ваш персонаж создан! ヽ(・∀・)ﾉ Имя: " + player.getName() + ", Здоровье: " + player.getHealth() +
                ", Сила: " + player.getStrength() + ", Ловкость: " + player.getAgility());
    }

    private void choosePath() {
        player.displayStatus();
        System.out.println("\nВыберите путь:");
        System.out.println("1. Пойти в Тёмный лес");
        System.out.println("2. Идти к торговцу");
        System.out.println("3. Посетить тренера героев");
        System.out.println("4. Выйти из игры");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                enterDarkForest();
                break;
            case 2:
                visitMerchant();
                break;
            case 3:
                visitTrainer();
                break;
            case 4:
                System.out.println("Ну и пока (」°ロ°)」");
                System.exit(0);
                break;
            default:
                System.out.println("Так нельзя.");
                break;
        }
    }

    private void enterDarkForest() {
        System.out.println("Вы входите в Тёмный лес...");

        while (true) {
            Monster monster = Monster.getRandomMonster(player.getLevel());
            System.out.println("Вы вступаете в битву с " + monster.getName() + "!");

            Battle battle = new Battle(player, scanner);
            battle.start();

            if (player.getHealth() <= 0) {
                System.out.println("Вы погибли. Игра окончена.");
                System.exit(0);
            }

            System.out.println("Хватил ли сил продолжить или лучше вернутся и отдохнуть ?");
            System.out.println("1. Продолжить исследование");
            System.out.println("2. Вернуться в город и отдохнуть");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 2) {
                player.heal(10);
                System.out.println("Вы вернулись в город. Ваше здоровье восстановлено на 10 единиц. 凸(￣ヘ￣)");
                break;
            }
        }
    }


    private void visitMerchant() {
        Merchant merchant = new Merchant(scanner);
        while (true) {
            System.out.println("Вы посетили торговца.");
            System.out.println("Что вы хотите сделать?");
            System.out.println("1. Купить зелье");
            System.out.println("2. Продать предметы");
            System.out.println("3. Выйти из магазина");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    merchant.sellPotion(player);
                    break;
                case 2:
                    merchant.sellItems(player);
                    break;
                case 3:
                    System.out.println("Вы покинули магазин.");
                    return;
                default:
                    System.out.println("Так нельзя.");
                    break;
            }
        }
    }

    private void visitTrainer() {
        player.displayStatus();
        System.out.println("Вы посетили тренера героев.");
        Trainer trainer = new Trainer(scanner);
        trainer.interactWithPlayer(player);
    }
}
