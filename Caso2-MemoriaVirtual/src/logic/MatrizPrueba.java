package logic;

public class MatrizPrueba {

    private String[][] matrix;
    private static int tp;
   

    private int matrixRows;
    private int matrixColumns;

    private static int desplazamientoPagina = 0;
    private static int numPagina = 0;

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

    public String valoresRespuesta(int i, int j) {
        return matrix[i][j];
    }

     public MatrizPrueba(int pMatrixRows, int pMatrixColumns, int pTp) {
        this.matrixRows = pMatrixRows;
        this.matrixColumns = pMatrixColumns;
        tp = pTp;
        matrix = new String[matrixRows][matrixColumns];
        generarMatriz();
    }

}
