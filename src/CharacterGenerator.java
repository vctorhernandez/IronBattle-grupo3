import java.io.*;
import java.util.*;

public class CharacterGenerator {

    public static List<Character> loadCharactersFromCSV(String filePath) {
        List<Character> characters = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // encabezado
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String type = parts[0].trim();
                String name = parts[1].trim();
                int hp = Integer.parseInt(parts[2]);
                int stamina = Integer.parseInt(parts[3]);
                int strength = Integer.parseInt(parts[4]);
                int mana = Integer.parseInt(parts[5]);
                int intelligence = Integer.parseInt(parts[6]);

                if (type.equalsIgnoreCase("warrior")) {
                    characters.add(new Warrior(name, hp, stamina, strength));
                } else if (type.equalsIgnoreCase("wizard")) {
                    characters.add(new Wizard(name, hp, mana, intelligence));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo CSV: " + e.getMessage());
        }

        return characters;
    }
}
