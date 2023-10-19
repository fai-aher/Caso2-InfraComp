package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

public class SimuladorMemoriaVirtual {
    private int cantidadMarcos;
    private HashMap<Integer, Integer> tablaPaginas = new HashMap<>();
    private LinkedList<Integer> marcos = new LinkedList<>();
    private HashMap<Integer, Byte> edadesMarcos = new HashMap<>();
    private List<Reference> referencias = new ArrayList<>();
    private int fallosPagina = 0;

    public SimuladorMemoriaVirtual(int cantidadMarcos, String nombreArchivoReferencias) throws IOException {
        this.cantidadMarcos = cantidadMarcos;
        cargarArchivo(nombreArchivoReferencias);

        // Hilos para simular los dos procesos
        Thread hiloActualizacion = new Thread(this::actualizarPaginasYMarcos);
        Thread hiloAging = new Thread(this::algoritmoAging);

        hiloActualizacion.start();
        hiloAging.start();

        try {
            hiloActualizacion.join();
            hiloAging.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTotal de fallos de página: " + fallosPagina);
    }

    private void cargarArchivo(String nombreArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.contains("=") && linea.startsWith("[")) {
                    String[] partes = linea.split(",");
                    int paginaVirtual = Integer.parseInt(partes[1]);
                    int desplazamiento = Integer.parseInt(partes[2]);
                    referencias.add(new Reference(paginaVirtual, desplazamiento));
                }
            }
        }
    }

    private synchronized void actualizarPaginasYMarcos() {
        for (Reference ref : referencias) {
            int paginaVirtual = ref.getVirtualPage();
            if (!tablaPaginas.containsKey(paginaVirtual)) {
                fallosPagina++;
                System.out.println("\nFallo de página para página virtual " + paginaVirtual);
                
                if (marcos.size() < cantidadMarcos) {
                    int nuevoMarco = marcos.size();
                    marcos.add(nuevoMarco);
                    tablaPaginas.put(paginaVirtual, nuevoMarco);
                    edadesMarcos.put(nuevoMarco, (byte) 0); 
                } else {
                    int marcoReemplazar = obtenerMarcoMasAntiguo();
                    int paginaVirtualExpulsada = tablaPaginas.entrySet().stream()
                        .filter(entry -> marcoReemplazar == entry.getValue())
                        .map(Map.Entry::getKey)
                        .findFirst().orElse(-1);
                    
                    if (paginaVirtualExpulsada != -1) {
                        tablaPaginas.remove(paginaVirtualExpulsada);
                    }
                    tablaPaginas.put(paginaVirtual, marcoReemplazar);
                    edadesMarcos.put(marcoReemplazar, (byte) 0); 
                    System.out.println("Página virtual expulsada " + paginaVirtualExpulsada + " del marco " + marcoReemplazar);
                }
            }
            System.out.println("Referencia a página virtual " + paginaVirtual + " mapeada al marco " + tablaPaginas.get(paginaVirtual));
        }
    }

    private synchronized int obtenerMarcoMasAntiguo() {
        int marcoMasAntiguo = -1;
        byte edadMaxima = -128; // empezar con el valor de byte más pequeño

        for (Map.Entry<Integer, Byte> entry : edadesMarcos.entrySet()) {
            if (entry.getValue() > edadMaxima) {
                marcoMasAntiguo = entry.getKey();
                edadMaxima = entry.getValue();
            }
        }

        return marcoMasAntiguo;
    }

    synchronized void algoritmoAging() {
        // 1. Actualizar edades: Desplazar a la derecha la edad de cada marco
        for (Map.Entry<Integer, Byte> entry : edadesMarcos.entrySet()) {
            byte edadAntigua = entry.getValue();
            byte edadNueva = (byte) (edadAntigua >>> 1); // Desplazar a la derecha                
            edadesMarcos.put(entry.getKey(), edadNueva);
        }

        // 2. Comprobar referencias y establecer el bit más significativo si se accedió a la página
        for (Reference ref : referencias) {
            int paginaVirtual = ref.getVirtualPage();
            if (tablaPaginas.containsKey(paginaVirtual)) {
                int marco = tablaPaginas.get(paginaVirtual);
                byte edadAntigua = edadesMarcos.get(marco);
                byte edadNueva = (byte) (edadAntigua | 0b10000000); // Establecer el bit más significativo en 1
                edadesMarcos.put(marco, edadNueva);
            }
        }
    }
}
