package logic;

public class MatrizPrueba {

    private String[][] matrix;
    private static int tp;
   
    private int matrixRows;
    private int matrixColumns;

    private static int desplazamientoPagina = 0;
    private static int numPagina = 0;

    // En esta sección del código, se genera la matriz de páginas y desplazamientos. Esto se logra con un ciclo
    // for que recorre la matriz y va agregando los valores de página y desplazamiento a cada celda.
    public void generarMatriz() {
        for (int i=0; i<matrixRows; i++) {
            for (int j=0; j<matrixColumns; j++) {
                matrix[i][j] = Integer.toString(numPagina) + "," + Integer.toString(desplazamientoPagina);
                desplazamientoPagina += 4;
                if (desplazamientoPagina >= tp) {
                    numPagina ++;
                    desplazamientoPagina = 0;
                }
            }
        }
    }   

    // Este método se usa para obtener el valor de una celda de la matriz, dado su índice
        public String valoresRespuesta(int i, int j) {
        return matrix[i][j];
    }

    // Este es el método constructor de la clase.
    // Recibe como parámetros el número de filas y columnas de la matriz, y el tamaño de página
     public MatrizPrueba(int pMatrixRows, int pMatrixColumns, int pTp) {
        this.matrixRows = pMatrixRows;
        this.matrixColumns = pMatrixColumns;
        tp = pTp;
        matrix = new String[matrixRows][matrixColumns];
        generarMatriz();
    }

}
