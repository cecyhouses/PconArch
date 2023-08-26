import java.io.*;

public class mat {
    public static void main(String[] args) {
        try (DataInputStream entradaA = new DataInputStream(new FileInputStream("a.mat"));
             DataInputStream entradaB = new DataInputStream(new FileInputStream("b.mat"));
             DataOutputStream salidaC = new DataOutputStream(new FileOutputStream("c.mat"))) {

            // lee los primeros 2 bytes, definiendo las filas y comunas de las matrices
            int filasA = entradaA.read();
            int columnasA = entradaA.read();
            int filasB = entradaB.read();
            int columnasB = entradaB.read();

            double[][] matrizA = leerMatriz(entradaA, filasA, columnasA);
            double[][] matrizB = leerMatriz(entradaB, filasB, columnasB);

            double[][] matrizC = multiplicarMatrices(matrizA, matrizB);

            imprimirMatriz(matrizC);

            // definicion de filas y columnas de c.mat
            salidaC.write(filasA);
            salidaC.write(columnasB);

            for (int i = 0; i < filasA; i++) {
                for (int j = 0; j < columnasB; j++) {
                    salidaC.writeDouble(matrizC[i][j]);
                }
            }

            System.out.println("\nel producto se guardo en el archivo c.mat");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //LEE
    public static double[][] leerMatriz(DataInputStream archivo, int filas, int columnas) throws IOException {
        double[][] matriz = new double[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = archivo.readDouble();
            }
        }

        return matriz;
    }

    //MULTIPLICA
    public static double[][] multiplicarMatrices(double[][] a, double[][] b) {
        int filasA = a.length;
        int columnasA = a[0].length;
        int filasB = b.length;
        int columnasB = b[0].length;

        double[][] resultado = new double[filasA][columnasB];

        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasB; j++) {
                for (int k = 0; k < columnasA; k++) {
                    resultado[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return resultado;
    }

    //IMPRIME
    public static void imprimirMatriz(double[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        System.out.println("Matriz Resultante:");

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
