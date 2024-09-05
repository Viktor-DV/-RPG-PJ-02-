import java.util.Random;

public class Monster extends Character {
    private static final Random random = new Random();

    public Monster(String name, int level) {
        super(name, generateRandomHealth(level), generateRandomStrength(level), generateRandomAgility(level), 0, 0);
    }

    private static int generateRandomHealth(int level) {
        return 1 + (level * 2);
    }

    private static int generateRandomStrength(int level) {
        return 1 + level;
    }

    private static int generateRandomAgility(int level) {
        return 1 + level;
    }

    public DropItem dropLoot() {
        int roll = random.nextInt(100);
        if (roll < 10) {
            return new DropItem("Череп", 100);
        } else if (roll < 40) {
            return new DropItem("Кости", 50);
        } else {
            return new DropItem("Зубы", 10);
        }
    }

    public int getDropExperience() {
        DropItem loot = dropLoot();
        if (loot.getName().equals("Череп")) {
            return 3;
        } else if (loot.getName().equals("Кости")) {
            return 2;
        } else {
            return 1;
        }
    }

    public static Monster getRandomMonster(int playerLevel) {
        return new Monster(random.nextBoolean() ? "Гоблин" : "Скелет", playerLevel);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public String getName() {
        return name;
    }
}