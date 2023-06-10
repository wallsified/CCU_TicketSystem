package System;

import Priority.priorityQueueMin;
import System.Visitante.Actividad;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

/**
 * Clase del "Sistema de gestión". Consideramos que el sistema tiene
 * a su mano parámetros establecidos para funcionar (como una cola
 * de proridad, una fecha de inicio, una "impresora", etc). y
 * simulamos la compra-venta de boletos de la actividad del
 * {@link}Visitante
 * 
 * @version 1.0
 * @author wallsified
 * @author gentle_earthquake
 */

public class CCU {

    /**
     * Ganancias
     */
    private double ganancias;

    /**
     * Cola de Prioridad del sistema.
     */
    private priorityQueueMin<Visitante> colaPrioridad;

    /**
     * Instancia de aleatorio para diversos usos.
     */
    Random test = new Random(System.currentTimeMillis());

    /**
     * Cambio disponible para empezar la venta.
     * 
     * @Deprecated
     */
    private double cambioInicio = test.nextInt(2000) + 1;

    /**
     * Total de tickets vendidos
     */
    private int totalTicketsVendidos;

    /**
     * Contador de dias trabajados
     */
    private int contadorDias = 0;

    /**
     * Dia de ejecución del programa. Es simulada.
     */
    LocalDate fechaActual = LocalDate.now();

    /**
     * Constructor sin parámetros para CCU.
     * Iniciamos con una cola de prioridad
     * de {@link}Visitante
     */
    public CCU() {
        colaPrioridad = new priorityQueueMin<Visitante>();
    }

    /**
     * Método para conocer la actividad más vendida del día.
     * 
     * @return Actividad con el mayor número de tickets vendidos.
     */
    public Actividad actividadMasVendida() {
        int maximo1 = Math.max(Actividad.Cine.boletosVendidosPorActividad,
                Actividad.Expo.boletosVendidosPorActividad);
        int maximo2 = Math.max(Actividad.Museo.boletosVendidosPorActividad,
                Actividad.Teatro.boletosVendidosPorActividad);
        int maximoTotal = Math.max(maximo1, maximo2);
        for (Actividad masVendida : Actividad.values()) {
            if (masVendida.boletosVendidosPorActividad == maximoTotal)
                return masVendida;
        }
        return null;
    }

    /**
     * Método para conocer la actividad menos vendida del día.
     * 
     * @return Actividad con el menor número de tickets vendidos.
     */
    public Actividad actividadMenosVendida() {
        int minimo1 = Math.min(Actividad.Cine.boletosVendidosPorActividad,
                Actividad.Expo.boletosVendidosPorActividad);
        int minimo2 = Math.min(Actividad.Museo.boletosVendidosPorActividad,
                Actividad.Teatro.boletosVendidosPorActividad);
        int maximoTotal = Math.min(minimo1, minimo2);
        for (Actividad masVendida : Actividad.values()) {
            if (masVendida.boletosVendidosPorActividad == maximoTotal)
                return masVendida;
        }
        return null;
    }

    /**
     * No se si esto tenga sentido
     * @param vis Visitante a añadir a la fila
     */
    public void fila(Visitante vis){
        this.colaPrioridad.queue(vis);
    }

    /**
     * Método para la compra-venta de boletos.
     * 
     * @param vis {@link}Visitante en cuestión.
     */
    public void venta(Visitante vis) {
        colaPrioridad.reordena(elemento, esMin);
        for (Visitante a : colaPrioridad){
            ganancias += a.precioActividad;
            totalTicketsVendidos++;
        }
    }

    /**
     * Método que genera un resumen del dia laborado del CCU
     * 
     * @return Resumen en cadena.
     */
    @Override
    public String toString() {
        String resumen = "\n";
        resumen = fechaActual.toString();
        resumen += "\nCaja Abierta con " + cambioInicio + "\n";
        resumen += "Fila de Venta: \n" + colaPrioridad + "\n\n";
        resumen += "Total de Boletos Vendidos: " + totalTicketsVendidos + "\n";
        resumen += "Actividad más vendida: " + actividadMasVendida().toString() + "\n";
        resumen += "Actividad menos vendida :" + actividadMenosVendida().toString() + "\n";
        resumen += "Ganancias Totales del día: " + ganancias;
        return resumen;
    }

    /**
     * Métódo que genera el archivo de salida de CCU por dia.
     * 
     * @return {@link}CCU en formato .txt generado como archivo externo.
     * @throws IOException En caso de que el archivo no se pueda generar.
     */
    public FileWriter generaResumen() throws IOException {
        FileWriter resumen = new FileWriter("Dia #" + contadorDias + ".txt");
        String startCCU = toString();
        resumen.write(startCCU);
        resumen.close();
        System.out.println("CCU Cerrado por hoy. Resumen del día guardado exitosamente.\n");

        return resumen;
    }

    /**
     * Método que simula el final del día del CCU.
     * 
     * @throws IOException En caso de que no se pueda generar el corte de día.
     */
    private void terminaDia() throws IOException {
        fechaActual.plusDays(1000);
        generaResumen();
        contadorDias++;
        totalTicketsVendidos = 0; // para reiniciar secuencia.
    }

    /**
     * Main de prueba. Posiblemente cambie a {@link}SimuladorDiario.
     * 
     * @param args N/A
     */
    public static void main(String[] args) {
        Visitante daniel = new Visitante();
        Visitante norbert = new Visitante();
        Visitante john = new Visitante();
        CCU nuevo = new CCU();
        nuevo.venta(daniel);
        nuevo.venta(john);
        nuevo.venta(norbert);
        System.out.println(nuevo.toString());

        try {
            nuevo.terminaDia();
        } catch (IOException e) {
            System.out.println("oh shit");
        }

        nuevo.venta(daniel);
        nuevo.venta(john);
        nuevo.venta(norbert);
        System.out.println(nuevo.toString());

        try {
            nuevo.terminaDia();
        } catch (IOException e) {
            System.out.println("oh shit");
        }

    }

}
