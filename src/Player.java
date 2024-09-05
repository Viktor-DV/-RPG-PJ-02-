import java.util.Scanner;

public class Player extends Character {
    private int skulls;
    private int bones;
    private int teeth;
    private int level;
    private int experience;
    private int skillPoints;

    public Player(String name) {
        super(name);
        this.skulls = 0;
        this.bones = 0;
        this.teeth = 0;
        this.level = 1;
        this.experience = 0;
        this.skillPoints = 0;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public int getSkulls() {
        return skulls;
    }

    public int getBones() {
        return bones;
    }

    public int getTeeth() {
        return teeth;
    }

    public void setSkulls(int skulls) {
        this.skulls = skulls;
    }

    public void setBones(int bones) {
        this.bones = bones;
    }

    public void setTeeth(int teeth) {
        this.teeth = teeth;
    }

    public void addItem(String item) {
        switch (item.toLowerCase()) {
            case "skull":
                skulls++;
                gainExperience(3);
                break;
            case "bone":
                bones++;
                gainExperience(2);
                break;
            case "tooth":
                teeth++;
                gainExperience(1);
                break;
            default:
                System.out.println("Неизвестный предмет: " + item);
                break;
        }
    }

    public void sellItems() {
        System.out.println("Вы можете продать следующие предметы ( ´ ∀ ` )ﾉ:");
        System.out.println("1. Черепы - 100 золота за штуку");
        System.out.println("2. Кости - 50 золота за штуку");
        System.out.println("3. Зубы - 10 золота за штуку");
        System.out.println("4. Вернуться");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                sellItem("Черепы", skulls, 100);
                break;
            case 2:
                sellItem("Кости", bones, 50);
                break;
            case 3:
                sellItem("Зубы", teeth, 10);
                break;
            case 4:
                return;
            default:
                System.out.println("Так нельзя");
        }
    }

    private void sellItem(String itemName, int itemCount, int pricePerItem) {
        if (itemCount > 0) {
            int amount = promptAmount(itemName);
            if (amount > 0 && amount <= itemCount) {
                if (itemName.equals("Черепы")) skulls -= amount;
                else if (itemName.equals("Кости")) bones -= amount;
                else if (itemName.equals("Зубы")) teeth -= amount;

                addGold(amount * pricePerItem);
                System.out.println("Вы продали " + amount + " " + itemName.toLowerCase() + " за " + (amount * pricePerItem) + " золота.");
            } else {
                System.out.println("Так нельзя");
            }
        } else {
            System.out.println("У вас нет " + itemName.toLowerCase() + " для продажи.");
        }
    }

    private int promptAmount(String itemName) {
        System.out.println("Введите количество " + itemName + " для продажи:");
        return new Scanner(System.in).nextInt();
    }

    public void displayStatus() {
        System.out.println("Имя: " + getName());
        System.out.println("Здоровье: " + getHealth());
        System.out.println("Сила: " + getStrength());
        System.out.println("Ловкость: " + getAgility());
        System.out.println("Золото: " + getGold());
        System.out.println("Черепы: " + skulls);
        System.out.println("Кости: " + bones);
        System.out.println("Зубы: " + teeth);
        System.out.println("Уровень: " + getLevel());
        System.out.println("Опыт: " + getExperience());
        System.out.println("Очки навыков: " + getSkillPoints());
    }

    public void heal(int amount) {
        health = Math.min(health + amount, getMaxHealth());
        System.out.println("Вы восстановили " + amount + " здоровья. Текущее здоровье: " + getHealth());
    }

    public void increaseStrength() {
        if (skillPoints > 0) {
            strength++;
            skillPoints--;
            System.out.println("Вы стали сильнее: " + getStrength());
        } else {
            System.out.println("У вас недостаточно очков навыков.");
        }
    }

    public void increaseAgility() {
        if (skillPoints > 0) {
            agility++;
            skillPoints--;
            System.out.println("Вы стали ловчее: " + getAgility());
        } else {
            System.out.println("У вас недостаточно очков навыков.");
        }
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void takeDamage(int amount) {
        health -= amount;
        System.out.println("Вы получили " + amount + " урона. Текущее здоровье: " + getHealth());
    }

    public void gainExperience(int amount) {
        experience += amount;
        System.out.println("Вы получили " + amount + " опыта. Текущий опыт: " + getExperience());

        if (experience >= 10) {
            levelUp();
        }
    }

    public void levelUp() {
        level++;
        experience = 0;
        skillPoints += 1;
        if (level % 5 == 0) {
            skillPoints += 2;
        }
        System.out.println("Лвл ап! σ(≧ε≦σ) ♡ " + level);
    }

    public int getMaxHealth() {
        return 100;
    }
}
