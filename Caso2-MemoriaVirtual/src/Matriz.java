public class Matriz {
    private int filas;
    private int columnas;
    private PaginaMatriz[][] matriz;

    public Matriz(int numRows, int numColumns, int pageSize) {
        this.filas = numRows;
        this.columnas = numColumns;
        this.matriz = new PaginaMatriz[numRows][numColumns];
        llenarMatriz(pageSize);
    }

    private void llenarMatriz(int pageSize) {
        int numPaginaActual = 0;
        int desplazamiento = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = new PaginaMatriz(numPaginaActual, desplazamiento);
                desplazamiento += 4;
                if (desplazamiento >= pageSize) {
                    numPaginaActual++;
                    desplazamiento = 0;
                }
            }
        }
    }

    public PaginaMatriz obtenerPaginaYDesplazamiento(int i, int j) {
        return matriz[i][j];
    }

    public static class PaginaMatriz {
        private int numPagina;
        private int desplazamiento;

        public PaginaMatriz(int numPagina, int desplazamiento) {
            this.numPagina = numPagina;
            this.desplazamiento = desplazamiento;
        }

        public int obtenerNumPagina() {
            return numPagina;
        }

        public int obtenerDesplazamiento() {
            return desplazamiento;
        }

        @Override
        public String toString() {
            return numPagina + ", " + desplazamiento;
        }
    }
}
