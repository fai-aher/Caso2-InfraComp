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

        System.out.println("Digite el tamano de pagina:");
		int tamPagina = sc.nextInt();

		System.out.println("Digite el numero deseado de filas para las matrices:");
		numeroFilas = sc.nextInt();

        System.out.println("Digite el numero deseado de columnas para las matrices:");
		numeroColumnas = sc.nextInt();

        try {
            int TP = tamPagina;
            int NF = numeroFilas;
            int NC1 = numeroColumnas;
            int NC2 = numeroColumnas;
            int NR = calcularNumeroDeReferencias(TP, NF, NC1, NC2);
            int NP = calcularNumeroDePaginas(TP, NF, NC1, NC2);

            // Aquí se crea un archivo 
            BufferedWriter writer = new BufferedWriter(new FileWriter("referencias.txt"));
            writer.write("TP:" + TP + ", NF:" + NF + ", NC1:" + NC1 + ", NC2:" + NC2 + ", NR:" + NR + ", NP:" + NP);
            writer.newLine();

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


}
