
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import logic.MatrizPrueba;
import logic.SimuladorMemoriaVirtual;
import logic.SimuladorMemoriaVirtual;

public class Main {

    private static int tp;
    private static int nf;
    private static int nc1;
    private static int nc2;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String input;

        while(true){
            System.out.println("\n¿Cuál modo de programa desea ejecutar?:\n");
            System.out.println("1. Digite 1 para el primer modo (Generacion de referencias)");
            System.out.println("2. Digite 2 para el segundo modo(Numero de fallas)\n");

            System.out.printf("Opción seleccionada: ");
            input = scanner.nextLine().trim();

            if (input.equals("1")){

                System.out.print("\nSeleccione el tamaño de pagina en Bytes deseado: ");
                int tp = scanner.nextInt();
                   

                System.out.print("Cantidad de filas de la primera matriz: ");
                int nf = scanner.nextInt();
                 

                //El numero de columnas de la matriz 1 es igual al numero de filas de la segunda matriz
                System.out.print("Cantidad de columnas de la primera matriz y filas de la segunda matriz: ");
                int nc1 = scanner.nextInt();
                scanner.nextLine();

                
                System.out.print("Cantidad de columnas de la segunda matriz: ");
                int nc2 = scanner.nextInt();
                scanner.nextLine();

                  
                generarReferencias(tp, nf, nc1, nc2);
            }

            else if (input.equals("2")){

                //Pedir input al usuario correspondiente al número de marcos de página que se quieren generar
                System.out.print("\nSeleccione el número de marcos de página que desea generar: ");
                int numFrames = scanner.nextInt();
                scanner.nextLine();

                //Pedir input al usuario correspondiente al nombre del archivo de referencias
                System.out.print("\nIngrese el nombre del archivo de referencias (sin .txt): ");
                String fileName = scanner.nextLine().trim();

                //Buscar y abrir el archivo de referencias en la carpeta principal del proyecto Java para mostrar los datos cargados.
                try {
                    Scanner fileScanner = new Scanner(new java.io.File(fileName + ".txt"));
                    String tpString = fileScanner.nextLine().trim();
                    String nfString = fileScanner.nextLine().trim();
                    String nc1String = fileScanner.nextLine().trim();
                    String nc2String = fileScanner.nextLine().trim();
                    String numReferencesString = fileScanner.nextLine().trim();
                    String numPagesString = fileScanner.nextLine().trim();

                    tp = Integer.parseInt(tpString.substring(3));
                    nf = Integer.parseInt(nfString.substring(3));
                    nc1 = Integer.parseInt(nc1String.substring(4));
                    nc2 = Integer.parseInt(nc2String.substring(4));
                    int numReferences = Integer.parseInt(numReferencesString.substring(3));
                    int numPages = Integer.parseInt(numPagesString.substring(3));

                    System.out.println("\n>>Resultado: Archivo de referencias encontrado y abierto correctamente.");
                    System.out.println("\n>>Resultado: Información cargada desde el archivo de referencias:");
                    System.out.println("TP=" + tp);
                    System.out.println("NF=" + nf);
                    System.out.println("NC1=" + nc1);
                    System.out.println("NC2=" + nc2);
                    System.out.println("NR=" + numReferences);
                    System.out.println("NP=" + numPages);

                } catch (IOException e) {
                    System.err.println("\n>>Resultado: Error al abrir el archivo de referencias.");
                }

                //Llamado a la clase VirtualMemorySimulator:
                new SimuladorMemoriaVirtual(numFrames, fileName+".txt");

            } 
            
            else {
                System.out.println("\n>>Resultado: Opción inválida. Por favor intente de nuevo.");
            }
        }
     
    }

    public static void generarReferencias(int tp, int nf, int nc1, int nc2) {
        
        int numPages = 4*(nc1*nf + nc1*nc2 + nc2*nf) / tp;
        int numReferences = (nf*nc2)*(nc1*2+1);

        try {

            FileWriter fileWriter = new FileWriter("referencias.txt");
            fileWriter.write("TP=" + tp + "\n");
            fileWriter.write("NF=" + nf + "\n");
            fileWriter.write("NC1=" + nc1 + "\n");
            fileWriter.write("NC2=" + nc2 + "\n");
            fileWriter.write("NR=" + numReferences + "\n");
            fileWriter.write("NP=" + numPages + "\n");


            MatrizPrueba A = new MatrizPrueba (nf,nc1,tp);
            MatrizPrueba B = new MatrizPrueba (nc1,nc2,tp);
            MatrizPrueba C = new MatrizPrueba (nf,nc2,tp);


            for (int i = 0; i < nf; i++) {
                for (int j = 0; j < nc2; j++) {
                    for (int k = 0; k < nc1; k++) {
                        String referenceA = "[A-" + i + "-" + k + "]," + A.valoresRespuesta(i, k);
                        fileWriter.write(referenceA + "\n");

                        String referenceB = "[B-" + k + "-" + j + "]," + B.valoresRespuesta(k,j);
                        fileWriter.write(referenceB + "\n");
                    }
                    String referenceC = "[C-" + i + "-" + j + "]," + C.valoresRespuesta(i, j);
                    fileWriter.write(referenceC + "\n");
                }
            }

            fileWriter.close();
            System.out.println("\n>>Resultado: Referencias generadas y guardadas en referencias.txt en la carpeta principal del proyecto Java.");
        } catch (IOException e) {
            System.err.println("\n>>Resultado: Error al escribir el archivo de referencias.");
        }
    }

}