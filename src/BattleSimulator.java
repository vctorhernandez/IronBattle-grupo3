import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Character player1;
        Character player2;

        System.out.println("=== RPG Battle Simulator ===");

        System.out.print("¿Deseas simular una batalla rápida con personajes aleatorios? (s/n): ");
        String opcion = scanner.nextLine();

        if (opcion.equalsIgnoreCase("s")) {
            player1 = generarPersonajeAleatorio("Jugador 1");
            player2 = generarPersonajeAleatorio("Jugador 2");
            System.out.println("\nSe han creado los siguientes personajes aleatoriamente:");
            System.out.println("- " + player1.getName() + " (" + player1.getClass().getSimpleName() + ")");
            System.out.println("- " + player2.getName() + " (" + player2.getClass().getSimpleName() + ")");
        } else {
            System.out.print("¿Deseas cargar personajes desde archivo CSV? (s/n): ");
            String cargarDesdeCSV = scanner.nextLine();

            if (cargarDesdeCSV.equalsIgnoreCase("s")) {
                List<Character> lista = CharacterGenerator.loadCharactersFromCSV("characters.csv");
                if (lista.size() >= 2) {
                    player1 = lista.get(0);
                    player2 = lista.get(1);
                    System.out.println("\nSe han cargado los siguientes personajes desde CSV:");
                    System.out.println("- " + player1.getName() + " (" + player1.getClass().getSimpleName() + ")");
                    System.out.println("- " + player2.getName() + " (" + player2.getClass().getSimpleName() + ")");
                } else {
                    System.out.println("No hay suficientes personajes en el archivo. Se usarán personajes manuales.");
                    player1 = crearPersonajeManual(scanner, "Jugador 1");
                    player2 = crearPersonajeManual(scanner, "Jugador 2");
                }
            } else {
                player1 = crearPersonajeManual(scanner, "Jugador 1");
                player2 = crearPersonajeManual(scanner, "Jugador 2");
            }
        }

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

    private static Character crearPersonajeManual(Scanner scanner, String etiqueta) {
        Random rand = new Random();
        System.out.print("Nombre para " + etiqueta + ": ");
        String nombre = scanner.nextLine();
        System.out.print("Tipo de personaje (1 = Guerrero, 2 = Mago): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        if (tipo == 1) {
            int hp = rand.nextInt(101) + 100;
            int stamina = rand.nextInt(41) + 10;
            int strength = rand.nextInt(10) + 1;
            return new Warrior(nombre, hp, stamina, strength);
        } else {
            int hp = rand.nextInt(51) + 50;
            int mana = rand.nextInt(41) + 10;
            int intelligence = rand.nextInt(50) + 1;
            return new Wizard(nombre, hp, mana, intelligence);
        }
    }

    private static Character generarPersonajeAleatorio(String nombre) {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            return new Warrior(nombre + " el Guerrero",
                    rand.nextInt(101) + 100,
                    rand.nextInt(41) + 10,
                    rand.nextInt(10) + 1);
        } else {
            return new Wizard(nombre + " el Mago",
                    rand.nextInt(51) + 50,
                    rand.nextInt(41) + 10,
                    rand.nextInt(50) + 1);
        }
    }

    private static int randomHp(Character c) {
        Random rand = new Random();
        return (c instanceof Warrior) ? rand.nextInt(101) + 100 : rand.nextInt(51) + 50;
    }
}
