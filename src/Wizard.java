// Wizard.java

import java.util.Random;

public class Wizard extends Character implements Attacker {
    private int mana;            // Recurso que consume al lanzar hechizos
    private int intelligence;    // Mide la fuerza de los hechizos del mago

    public Wizard (String name, int hp, int mana, int intelligence) {
        super (name, hp);
        this.mana = mana;
        this.intelligence = intelligence;
    }

    public int getMana() {
        return mana;
    }
    public int getIntelligence() {
        return intelligence;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    // Implementación del ataque (obligatorio por la interfaz Attacker)
    @Override
    public void attack(Character target) {
        Random random = new Random(); //Clase para generar nº aleatorios (para elegir entre fireball y staff hit)
        int choice = random.nextInt(2); // 0 = Fireball, 1 = Staff hit (el 2 es donde guarda este numero)

        // Si tiene suficiente mana para una fireball
        if (choice == 0 && mana >= 5) {
            System.out.println(this.getName() + " casts Fireball on " + target.getName());
            int damage = intelligence;
            mana -= 5;
            applyDamage(target, damage);
        }
        // Si no tiene suficiente mana para un fireball, pero suficiente mana para un staff hit
        else if (choice == 1 && mana >= 1) {
            System.out.println(this.getName() + " hits with Staff on " + target.getName());
            int damage = 2;
            mana -= 1;
            applyDamage(target, damage);
        }
        else { // Si no tiene mana suficiente para staff hit, recupera mana y descansa
            System.out.println(this.getName() + " is out of mana and rests.");
            mana += 2;
        }
    }

    // Metodo auxiliar para aplicar daño y verificar si el objetivo sigue vivo o está derrotado!!
    private void applyDamage(Character target, int damage) {
        int newHp = target.getHp() - damage;
        target.setHp(newHp);
        System.out.println(target.getName() + " takes " + damage + " damage! (HP left: " + newHp + ")");
        if (newHp <= 0) {
            target.setIsAlive(false);
            System.out.println(target.getName() + " has been defeated!");
        }
    }
}
