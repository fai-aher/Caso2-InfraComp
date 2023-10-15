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

    }

    public void modo2(){

    }


}
