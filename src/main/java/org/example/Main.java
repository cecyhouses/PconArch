import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int[] histograma = new int[11];

        try (BufferedReader lector = new BufferedReader(new FileReader("divina_comedia_sp.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] palabras = linea.split(" ");
                for (String palabra : palabras) {
                    int longitud = palabra.length();
                    if (longitud >= 2 && longitud <= 10 && !contieneNumeros(palabra)) {
                        histograma[longitud]++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir histograma con número de palabras
        for (int i = 2; i <= 10; i++) {
            System.out.println("con " + i + " caracteres: " + histograma[i] + " palabras!!!");
        }
    }

    public static boolean contieneNumeros(String palabra) {
        for (char c : palabra.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
