import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharacterImporter {

    public static List<Character> importFromCSV(String filename) {
        List<Character> characters = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Saltar la primera línea (cabecera)

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String type = fields[1];
                int hp = Integer.parseInt(fields[2]);
                int resource = Integer.parseInt(fields[3]);
                int stat = Integer.parseInt(fields[4]);

                if (type.equalsIgnoreCase("Warrior")) {
                    characters.add(new Warrior(name, hp, resource, stat));
                } else if (type.equalsIgnoreCase("Wizard")) {
                    characters.add(new Wizard(name, hp, resource, stat));
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return characters;
    }
}
