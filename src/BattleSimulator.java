import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Character player1 = null;
        Character player2 = null;

        System.out.println("=== RPG Battle Simulator ===");

        int opcion = 0;

        // Validar entrada del menú principal
        while (opcion < 1 || opcion > 3) {
            System.out.println("1. Crear personajes manualmente");
            System.out.println("2. Cargar personajes desde archivo CSV");
            System.out.println("3. Batalla automática con personajes aleatorios");
            System.out.print("Selecciona una opción (1, 2 o 3): ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción inválida. Intenta otra vez.\n");
                }
            } else {
                System.out.println("Entrada no válida. Escribe un número.\n");
                scanner.nextLine(); // limpiar entrada incorrecta
            }
        }

        if (opcion == 1) {
            // Crear primer personaje
            System.out.print("Nombre del primer personaje: ");
            String name1 = scanner.nextLine();
            player1 = createCharacter(scanner, name1);

            // Crear segundo personaje
            System.out.print("Nombre del segundo personaje: ");
            String name2 = scanner.nextLine();
            player2 = createCharacter(scanner, name2);

        } else if (opcion == 2) {
            System.out.println("Cargando personajes desde CSV...");
            List<Character> characters = CharacterImporter.importFromCSV("character.csv");

            if (characters.size() >= 2) {
                player1 = characters.get(0);
                player2 = characters.get(1);
                System.out.println("Personajes cargados: " + player1.getName() + " y " + player2.getName());
            } else {
                System.out.println("No hay suficientes personajes en el archivo.");
                scanner.close();
                return;
            }

        } else if (opcion == 3) {
            Random rand = new Random();

            if (rand.nextBoolean()) {
                player1 = new Warrior("AutoWarrior1", rand.nextInt(101) + 100, rand.nextInt(41) + 10, rand.nextInt(10) + 1);
            } else {
                player1 = new Wizard("AutoWizard1", rand.nextInt(51) + 50, rand.nextInt(41) + 10, rand.nextInt(50) + 1);
            }

            if (rand.nextBoolean()) {
                player2 = new Warrior("AutoWarrior2", rand.nextInt(101) + 100, rand.nextInt(41) + 10, rand.nextInt(10) + 1);
            } else {
                player2 = new Wizard("AutoWizard2", rand.nextInt(51) + 50, rand.nextInt(41) + 10, rand.nextInt(50) + 1);
            }

            System.out.println("Personajes aleatorios generados: " + player1.getName() + " y " + player2.getName());
        }

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
        int type = 0;

        // Validar tipo de personaje
        while (type != 1 && type != 2) {
            System.out.print("Tipo de personaje (1 = Guerrero, 2 = Mago): ");
            if (scanner.hasNextInt()) {
                type = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                if (type != 1 && type != 2) {
                    System.out.println("Opción inválida. Introduce 1 o 2.");
                }
            } else {
                System.out.println("Entrada inválida. Introduce un número.");
                scanner.nextLine(); // limpiar entrada incorrecta
            }
        }

        if (type == 1) {
            int hp = rand.nextInt(101) + 100;
            int stamina = rand.nextInt(41) + 10;
            int strength = rand.nextInt(10) + 1;
            return new Warrior(name, hp, stamina, strength);
        } else {
            int hp = rand.nextInt(51) + 50;
            int mana = rand.nextInt(41) + 10;
            int intelligence = rand.nextInt(50) + 1;
            return new Wizard(name, hp, mana, intelligence);
        }
    }

    private static int randomHp(Character c) {
        Random rand = new Random();
        if (c instanceof Warrior) return rand.nextInt(101) + 100;
        else return rand.nextInt(51) + 50;
    }
}

