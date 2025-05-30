import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            Path inputFile = Paths.get("in.txt");
            Path outputFile = Paths.get("out.txt");

            // Citim liniile din fisierul in.txt
            List<String> lines = Files.readAllLines(inputFile);

            // a) Adaugam un new line dupa fiecare linie
            List<String> modifiedLinesA = lines.stream()
                    .map(line -> line + "\n")
                    .collect(Collectors.toList());

            // Afisam rezultatul pentru a)
            System.out.println("Rezultatul pentru a):");
            modifiedLinesA.forEach(System.out::print);
            System.out.println();

            // b) Adaugam un new line dupa fiecare caracter punct (.)
            List<String> modifiedLinesB = lines.stream()
                    .map(line -> line.replace(".", ".\n"))
                    .collect(Collectors.toList());

            // Afisam rezultatul pentru b)
            System.out.println("Rezultatul pentru b):");
            modifiedLinesB.forEach(System.out::print);
            System.out.println();

            // c) Salvam rezultatul in out.txt
            Files.write(outputFile, modifiedLinesA);
            Files.write(outputFile, modifiedLinesB, java.nio.file.StandardOpenOption.APPEND);

            // 5.3.2 Generare fisier outrand.txt cu 5 linii, fiecare cu 10 cuvinte de 4 litere ordonate
            Path randomOutputFile = Paths.get("outrand.txt");
            Random random = new Random();
            StringBuilder randomText = new StringBuilder();

            for (int i = 0; i < 5; i++) {
                String[] words = new String[10];
                for (int j = 0; j < 10; j++) {
                    words[j] = random.ints(4, 'a', 'z' + 1)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();
                }
                Arrays.sort(words);
                randomText.append(String.join(" ", words)).append("\n");
            }

            Files.write(randomOutputFile, randomText.toString().getBytes());
            System.out.println("Fisierul outrand.txt a fost generat cu succes!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
