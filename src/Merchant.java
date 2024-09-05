import java.util.Scanner;

public class Merchant {
    private Potion[] potions;
    private Scanner scanner;

    public Merchant(Scanner scanner) {
        this.scanner = scanner;
        potions = new Potion[]{
                new Potion("Малое зелье здоровья", 20, 10),
                new Potion("Среднее зелье здоровья", 50, 25),
                new Potion("Большое зелье здоровья", 100, 50)
        };
    }

    public void displayPotions() {
        System.out.println("Ну, на что глаз положил ? (^_-):");
        for (int i = 0; i < potions.length; i++) {
            System.out.println((i + 1) + ". " + potions[i].getName() + " - Восстанавливает: "
                    + potions[i].getHealingAmount() + " здоровья, Стоимость: "
                    + potions[i].getCost() + " золота");
        }
    }

    public void sellPotion(Player player) {
        while (true) {
            displayPotions();
            System.out.println("Выберите зелье, которое хотите купить, или 0 для выхода:");

            int choice = scanner.nextInt();
            if (choice > 0 && choice <= potions.length) {
                Potion chosenPotion = potions[choice - 1];
                if (player.getGold() >= chosenPotion.getCost()) {
                    player.setGold(player.getGold() - chosenPotion.getCost());
                    player.heal(chosenPotion.getHealingAmount());
                    System.out.println("Вы купили " + chosenPotion.getName() + " и восстановили " + chosenPotion.getHealingAmount() + " здоровья.");
                } else {
                    System.out.println("У вас недостаточно золота.┐(￣ヘ￣)┌");
                }
            } else if (choice == 0) {
                System.out.println("Вы покинули магазин.");
                return;
            } else {
                System.out.println("Так нельзя");
            }
        }
    }

    public void sellItems(Player player) {
        while (true) {
            System.out.println("Вы можете продать следующие предметы ( ´ ∀ ` )ﾉ:");
            System.out.println("1. Черепы - 100 золота за штуку");
            System.out.println("2. Кости - 50 золота за штуку");
            System.out.println("3. Зубы - 10 золота за штуку");
            System.out.println("4. Вернуться");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    sellItem("Черепы", player.getSkulls(), 100, player);
                    break;
                case 2:
                    sellItem("Кости", player.getBones(), 50, player);
                    break;
                case 3:
                    sellItem("Зубы", player.getTeeth(), 10, player);
                    break;
                case 4:
                    System.out.println("Вы покинули магазин.");
                    return;
                default:
                    System.out.println("Так нельзя");
            }
        }
    }

    private void sellItem(String itemName, int itemCount, int pricePerItem, Player player) {
        if (itemCount > 0) {
            int amount = promptAmount(itemName);
            if (amount > 0 && amount <= itemCount) {
                if (itemName.equals("Черепы")) player.setSkulls(player.getSkulls() - amount);
                else if (itemName.equals("Кости")) player.setBones(player.getBones() - amount);
                else if (itemName.equals("Зубы")) player.setTeeth(player.getTeeth() - amount);

                player.addGold(amount * pricePerItem);
                System.out.println("Вы продали " + amount + " " + itemName.toLowerCase() + " за " + (amount * pricePerItem) + " золота.");
            } else {
                System.out.println("Недопустимое количество.");
            }
        } else {
            System.out.println("У вас нет " + itemName.toLowerCase() + " для продажи.");
        }
    }

    private int promptAmount(String itemName) {
        System.out.println("Введите количество " + itemName + " для продажи:");
        return scanner.nextInt();
    }
}
