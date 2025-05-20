import java.util.Random;
import java.util.Scanner;

public class BattleSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== RPG Battle Simulator ===");
            System.out.println("1. Batalla personalizada");
            System.out.println("2. Batalla aleatoria");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (choice) {
                case 1 -> manualBattle(scanner);
                case 2 -> RandomBattleSimulator.simulateBattle();
                case 3 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void manualBattle(Scanner scanner) {
        Character player1, player2;

        // Crear primer personaje
        System.out.print("Nombre del primer personaje: ");
        String name1 = scanner.nextLine();
        player1 = createCharacter(scanner, name1);

        // Crear segundo personaje
        System.out.print("Nombre del segundo personaje: ");
        String name2 = scanner.nextLine();
        player2 = createCharacter(scanner, name2);

        System.out.println("\n--- ¡Comienza la batalla entre " +
                player1.getName() + " y " + player2.getName() + "! ---");

        while (true) {
            player1.attack(player2);
            if (!player2.isAlive()) break;
            player2.attack(player1);

            System.out.println(player1.getName() + " (HP: " + player1.getHp() + ")");
            System.out.println(player2.getName() + " (HP: " + player2.getHp() + ")");
            System.out.println("----------------------");

            if (!player1.isAlive() && !player2.isAlive()) {
                System.out.println("¡Empate! Reiniciando la batalla...");
                resetCharacter(player1);
                resetCharacter(player2);
            } else if (!player1.isAlive()) {
                System.out.println(player2.getName() + " gana la batalla.");
                break;
            } else if (!player2.isAlive()) {
                System.out.println(player1.getName() + " gana la batalla.");
                break;
            }
        }
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

    private static void resetCharacter(Character c) {
        Random rand = new Random();
        if (c instanceof Warrior) {
            c.setHp(rand.nextInt(101) + 100);
        } else {
            c.setHp(rand.nextInt(51) + 50);
        }
        c.setIsAlive(true);
    }
}
