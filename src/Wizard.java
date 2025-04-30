// Wizard.java

// Importa herramientas que vamos a usar
import java.util.Random; //para generar números aleatorios

//Clase pública Wizard que hereda (extends) de la clase base (abstracta) Character y que usa la interfaz Attacker (usa este metodo)
public class Wizard extends Character implements Attacker {

    //-------------------
    // Atributos privados del Wizard
    private int mana;            // Recurso que consume al lanzar hechizos
    private int intelligence;    // Mide la fuerza de los hechizos del mago

    // Constructor PARAMETRIZADO que recibe nombre, vida (hp), mana e inteligencia
    public Wizard (String name, int hp, int mana, int intelligence) {
        super (name, hp); // Llama (super) al constructor del padre/base/abstracto: Character
        this.mana = mana;
        this.intelligence = intelligence;
    }

    //-------------------
    // Getters: para leer los valores de las variables (mana e intelligence)
    public int getMana() {
        return mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    // Setters: para cambiar los valores de las variables (mana e intelligence)
    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    //-------------------
    // Implementación del ataque (obligatorio por la interfaz Attacker)
    @Override //Vamos a sobreescribir el metodo attack de la interfaz Attacker
    public void attack(Character target) { //Cualquier personaje Character podrá ser atacado por el Wizard
        Random random = new Random(); //Clase para generar nº aleatorios (para elegir entre fireball y staff hit)
        int choice = random.nextInt(2); // 0 = Fireball, 1 = Staff hit (el 2 es donde guarda este numero)

        // Si tiene suficiente mana para una fireball
        if (choice == 0 && mana >= 5) {
            System.out.println(this.getName() + " casts Fireball on " + target.getName()); //Imprime un mensaje diciendo que está lanzando un Fireball sobre el objetivo (target).
            int damage = intelligence;           //El daño causado por la Fireball es igual a la inteligencia del mago (intelligence).
            mana -= 5;                          //Wizard  pierde 5 puntos de mana por lanzar el hechizo
            applyDamage(target, damage);        //se llama a la función applyDamage(target, damage), que aplica el daño al objetivo usando la cantidad calculada (damage)
        }
        // Si no tiene suficiente mana para un fireball, pero suficiente mana para un staff hit
        else if (choice == 1 && mana >= 1) {
            System.out.println(this.getName() + " hits with Staff on " + target.getName());
            int damage = 2;
            mana -= 1;
            applyDamage(target, damage);
        }
        // Si no tiene mana suficiente para staff hit, recupera mana y descansa
        else {
            System.out.println(this.getName() + " is out of mana and rests.");
            mana += 2;
        }
    }

    //-------------------
    // Metodo auxiliar para aplicar daño y verificar si el objetivo sigue vivo o está derrotado!!
    private void applyDamage(Character target, int damage) { //Aplicar el damage al personaje objetivo
        // 1. Calcular la nueva salud del objetivo
        int newHp = target.getHp() - damage;
        // 2. Actualizar los puntos de salud (HP) del objetivo
        target.setHp(newHp);
        // 3. Mostrar un mensaje de que el objetivo ha recibido daño
        System.out.println(target.getName() + " takes " + damage + " damage! (HP left: " + newHp + ")");
        // 4. Verificar si el objetivo ha sido derrotado
        if (newHp <= 0) {
            // Si los HP son 0 o menos, el objetivo está derrotado
            target.setAlive(false);                                         // Marcar al objetivo como no vivo
            System.out.println(target.getName() + " has been defeated!");   // Imprimir que ha sido derrotado
        }
    }
}
