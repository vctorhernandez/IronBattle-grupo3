import java.util.UUID;

public abstract class Character implements Attacker {
    private String id;
    private String name;
    private int hp;
    private boolean isAlive;

    public Character(String name, int hp) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.hp = hp;
        this.isAlive = true;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getHp() { return hp; }
    public boolean isAlive() { return isAlive; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp <= 0) {
            this.isAlive = false;
        }
    }
    public void setIsAlive(boolean isAlive) { this.isAlive = isAlive; }
}
