import java.util.Random;
import java.util.Scanner;

public abstract class Character {
    protected String name;
    protected int health;
    protected int strength;
    protected int agility;
    protected int experience;
    protected int gold;

    public Character(String name) {
        this(name, 20, 5, 5, 0, 0);
    }

    public Character(String name, int health, int strength, int agility, int experience, int gold) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.experience = experience;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int attack() {
        int randomValue = (int) (Math.random() * 100);
        if (agility * 3 > randomValue) {
            if (Math.random() < (agility * 0.05)) {
                System.out.println(name + " Это крит o(≧▽≦)o");
                return strength * 2;
            }
            return strength;
        } else {
            System.out.println(name + " промахнулся! ╰(▔∀▔)╯");
            return 0;
        }
    }
}