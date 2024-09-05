public class Potion {
    private String name;
    private int healingAmount;
    private int cost;

    public Potion(String name, int healingAmount, int cost) {
        this.name = name;
        this.healingAmount = healingAmount;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    public int getCost() {
        return cost;
    }
}