import java.util.Random;

public class Warrior extends Character implements Attacker {
    private int stamina;
    private int strength;

    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        this.stamina = stamina;
        this.strength = strength;
    }

    // Getters y Setters
    public int getStamina() { return stamina; }
    public int getStrength() { return strength; }
    public void setStamina(int stamina) { this.stamina = stamina; }
    public void setStrength(int strength) { this.strength = strength; }

    @Override
    public void attack(Character character) {
        Random rand = new Random();
        if (stamina >= 5 && rand.nextBoolean()) {
            System.out.println(getName() + " performs a Heavy Attack!");
            character.setHp(character.getHp() - strength);
            stamina -= 5;
        } else if (stamina >= 1) {
            int damage = strength / 2;
            System.out.println(getName() + " performs a Weak Attack!");
            character.setHp(character.getHp() - damage);
            stamina += 1;
        } else {
            System.out.println(getName() + " is too tired and recovers 2 stamina.");
            stamina += 2;
        }
    }
}
