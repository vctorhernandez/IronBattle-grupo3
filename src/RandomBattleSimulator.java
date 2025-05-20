import java.util.Random;

public class RandomBattleSimulator {

    private static final Random random = new Random();

    public static Attacker createRandomCharacter() {
        String[] names = {"Aragorn", "Legolas", "Gimli", "Saruman", "Morgana", "Achilles"};
        String name = names[random.nextInt(names.length)];
        boolean isWarrior = random.nextBoolean();

        if (isWarrior) {
            int hp = 100 + random.nextInt(101); // 100-200
            int stamina = 10 + random.nextInt(41); // 10-50
            int strength = 1 + random.nextInt(10); // 1-10
            return new Warrior(name, hp, stamina, strength);
        } else {
            int hp = 50 + random.nextInt(51); // 50-100
            int mana = 10 + random.nextInt(41); // 10-50
            int intelligence = 1 + random.nextInt(50); // 1-50
            return new Wizard(name, hp, mana, intelligence);
        }
    }

    public static void simulateBattle() {
        Attacker p1 = createRandomCharacter();
        Attacker p2 = createRandomCharacter();

        Character player1 = (Character) p1;
        Character player2 = (Character) p2;

        System.out.println("\n--- Batalla automática entre ---");
        System.out.println(player1.getName() + " (HP: " + player1.getHp() + ") vs " +
                player2.getName() + " (HP: " + player2.getHp() + ")");
        System.out.println("--------------------------------");

        while (player1.isAlive() && player2.isAlive()) {
            p1.attack(player2);
            if (!player2.isAlive()) break;
            p2.attack(player1);

            System.out.println(player1.getName() + " (HP: " + player1.getHp() + ")");
            System.out.println(player2.getName() + " (HP: " + player2.getHp() + ")");
            System.out.println("--------------------------------");
        }

        if (!player1.isAlive() && !player2.isAlive()) {
            System.out.println("¡Empate!");
        } else if (!player1.isAlive()) {
            System.out.println(player2.getName() + " gana la batalla.");
        } else {
            System.out.println(player1.getName() + " gana la batalla.");
        }
    }
}

