import java.util.Scanner;

public class Battle {
    private Player player;
    private Monster monster;
    private Scanner scanner;

    public Battle(Player player, Scanner scanner) {
        this.player = player;
        this.monster = Monster.getRandomMonster(player.getLevel());
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Вы вступаете в битву с " + monster.getName() + " уровня " + player.getLevel() + "!");
        player.displayStatus();

        while (player.getHealth() > 0 && monster.getHealth() > 0) {
            player.displayStatus();
            System.out.println("Бой против " + monster.getName() + "!");

            int playerDamage = player.attack();
            if (playerDamage == 0) {
                System.out.println(player.getName() + " промахнулся!");
            } else {
                monster.takeDamage(playerDamage);
                System.out.println("Вы нанесли " + playerDamage + " урона " + monster.getName());
            }

            if (monster.getHealth() <= 0) {
                handleMonsterDefeat();
                return;
            }

            int monsterDamage = monster.attack();
            if (monsterDamage == 0) {
                System.out.println(monster.getName() + " промахнулся!");
            } else {
                player.takeDamage(monsterDamage);
                System.out.println(monster.getName() + " нанёс вам " + monsterDamage + " урона");
            }

            if (player.getHealth() <= 0) {
                handlePlayerDefeat();
                return;
            }
        }
    }

    private void handleMonsterDefeat() {
        System.out.println("Вы победили " + monster.getName() + "!");

        int experiencePoints = monster.getDropExperience();
        player.gainExperience(experiencePoints);

        DropItem loot = monster.dropLoot();
        System.out.println("Лут с монстра: " + loot.getName() + ", стоимость: " + loot.getValue() + " золота.");

        player.addItem(loot.getName());

    }


    private void handlePlayerDefeat() {
        System.out.println("Вас убил " + monster.getName() + "(๑˘︶˘๑)");
        System.out.println("Хотите начать заново или выйти?");
        System.out.println("1. Начать заново");
        System.out.println("2. Выйти из игры");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            Game game = new Game();
            game.start();
        } else {
            System.out.println("Спасибо за игру! До встречи!");
            System.exit(0);
        }
    }
}