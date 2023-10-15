import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Configuracion {

    private int numeroFilas;
	private int numeroColumnas;

    public int getNumeroFilas() {
        return numeroFilas;
    }

    public void setNumeroFilas(int numeroFilas) {
        this.numeroFilas = numeroFilas;
    }

    public int getNumeroColumnas() {
        return numeroColumnas;
    }

    public void setNumeroColumnas(int numeroColumnas) {
        this.numeroColumnas = numeroColumnas;
    }

    public void ejecutar(){

        try {

             Scanner sc = new Scanner(System.in);

            System.out.println("Cual modo de programa desea ejecutar?");
			System.out.println("Digite 1 para el primer modo");
			System.out.println("Digite 2 para el segundo modo");

            int modo = sc.nextInt();

			if(modo == 1) {
				//Aqui va el metodo del modo 1
                modo1();
			}
			else if(modo == 2) {
				//Aqui va el metodo del modo 2
			}
			else 
				System.out.println("Se ha introducido una opción invalida");
            
            }    
            catch (Exception e) {
            System.out.println("Error al ejecutar el programa: " + e.getMessage());
        }	
    }

    public void modo1(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite el tamaño de página:");
        int tamPagina = sc.nextInt();

        System.out.println("Digite el número deseado de filas para las matrices:");
        int numeroFilas = sc.nextInt();

        System.out.println("Digite el número deseado de columnas para las matrices:");
        int numeroColumnas = sc.nextInt();

        try {
            int NR = calcularNumeroDeReferencias(tamPagina, numeroFilas, numeroColumnas, numeroColumnas);
            int NP = calcularNumeroDePaginas(tamPagina, numeroFilas, numeroColumnas, numeroColumnas);

            // Aquí puedes crear un archivo y escribir los datos antes de las referencias.
            BufferedWriter writer = new BufferedWriter(new FileWriter("referencias.txt"));
            writer.write("TP=" + tamPagina);
            writer.newLine();
            writer.write("NF=" + numeroFilas);
            writer.newLine();
            writer.write("NC=" + numeroColumnas);
            writer.newLine();
            writer.write("NR=" + NR);
            writer.newLine();

           
            for (int i = 0; i < NR; i++) {
        
                String referencia = generarReferencia(i);
                writer.write(referencia);
                writer.newLine();
            }

            writer.close();

            System.out.println("Referencias generadas y almacenadas en el archivo referencias.txt.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de referencias: " + e.getMessage());
        }
    }

    public void modo2(){

    }


    public int calcularNumeroDeReferencias(int tamPagina, int NF, int NC1, int NC2){
        return 0;
    }

    public int calcularNumeroDePaginas(int tamPagina, int NF, int NC1, int NC2){
        return 0; 
    }

    private String generarReferencia(int i) {
        String matriz = (i % 3 == 0) ? "A" : (i % 3 == 1) ? "B" : "C";
        int pagina = i / 3;
        int fila = (pagina % 8) * 4;
        int columna = (pagina / 8) * 4;

        return String.format("[%s-%d-%d],%d,%d", matriz, fila, columna, pagina, i);
    }

    

}
