import java.util.Random;
import java.util.Scanner;

public class BattleSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Character player1 = null;
        Character player2 = null;

        System.out.println("=== RPG Battle Simulator ===");

        // Crear primer personaje
        System.out.print("Nombre del primer personaje: ");
        String name1 = scanner.nextLine();
        player1 = createCharacter(scanner, name1);

        // Crear segundo personaje
        System.out.print("Nombre del segundo personaje: ");
        String name2 = scanner.nextLine();
        player2 = createCharacter(scanner, name2);

        // Batalla
        System.out.println("\n--- ¡Comienza la batalla entre " + player1.getName() + " y " + player2.getName() + "! ---");

        while (true) {
            player1.attack(player2);
            player2.attack(player1);

            System.out.println(player1.getName() + " (HP: " + player1.getHp() + ")");
            System.out.println(player2.getName() + " (HP: " + player2.getHp() + ")");
            System.out.println("----------------------");

            if (!player1.isAlive() && !player2.isAlive()) {
                System.out.println("¡Empate! Reiniciando la batalla...");
                player1.setHp(randomHp(player1));
                player2.setHp(randomHp(player2));
                player1.setIsAlive(true);
                player2.setIsAlive(true);
            } else if (!player1.isAlive()) {
                System.out.println(player2.getName() + " gana la batalla.");
                break;
            } else if (!player2.isAlive()) {
                System.out.println(player1.getName() + " gana la batalla.");
                break;
            }
        }

        scanner.close();
    }

    private static Character createCharacter(Scanner scanner, String name) {
        Random rand = new Random();
        System.out.print("Tipo de personaje (1 = Guerrero, 2 = Mago): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        if (type == 1) {
            int hp = rand.nextInt(101) + 100; // 100-200
            int stamina = rand.nextInt(41) + 10; // 10-50
            int strength = rand.nextInt(10) + 1; // 1-10
            return new Warrior(name, hp, stamina, strength);
        } else {
            int hp = rand.nextInt(51) + 50; // 50-100
            int mana = rand.nextInt(41) + 10; // 10-50
            int intelligence = rand.nextInt(50) + 1; // 1-50
            return new Wizard(name, hp, mana, intelligence);
        }
    }

    private static int randomHp(Character c) {
        Random rand = new Random();
        if (c instanceof Warrior) return rand.nextInt(101) + 100;
        else return rand.nextInt(51) + 50;
    }
}
