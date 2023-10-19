import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static int tp;
    private static int nf;
    private static int nc1;
    private static int nc2;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("Cual modo de programa desea ejecutar?");
            System.out.println("Digite 1 para el primer modo (Generacion de referencias)");
            System.out.println("Digite 2 para el segundo modo(Numero de fallas)");

            int modo = sc.nextInt();

            switch(modo){
                case 1:

                    System.out.print("Seleccione el tamaño de pagina deseado: ");
                    int tp = sc.nextInt();
                   

                    System.out.print("Cantidad de filas de la primera matriz: ");
                    int nf = sc.nextInt();
                 

                    //El numero de columnas de la matriz 1 es igual al numero de filas de la segunda matriz
                    System.out.print("Cantidad de columnas de la primera matriz y filas de la segunda matriz:  ");
                    int nc1 = sc.nextInt();
                

                    System.out.print("Cantidad de columnas de la segunda matriz: ");
                    int nc2 = sc.nextInt();
                  
                    generarReferencias(tp, nf, nc1, nc2);

                case 2:

                //

                default:

                    System.out.println("Opcion no valida. Por favor, seleccione 1 o 2.");
                    break;

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
                        String referenceA = "[A-" + i + "-" + k + "], " + A.valoresRespuesta(i, k);
                        fileWriter.write(referenceA + "\n");

                        String referenceB = "[B-" + k + "-" + j + "], " + B.valoresRespuesta(k,j);
                        fileWriter.write(referenceB + "\n");
                    }
                    String referenceC = "[C-" + i + "-" + j + "], " + C.valoresRespuesta(i, j);
                    fileWriter.write(referenceC + "\n");
                }
            }

            fileWriter.close();
            System.out.println("Referencias generadas y guardadas en referencias.txt");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de referencias.");
        }
    }

}