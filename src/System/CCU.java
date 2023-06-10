package System;

import java.util.Random;

import Priority.priorityQueueMin;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import System.Visitante.Actividad;

/**
 * Clase Controladora?. La lógica sería que todo el ajuste
 * que haya que hacer con las colas y visitantes se haga desde aqui.
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

    private priorityQueueMin<Visitante> colaPrioridad;

    /**
     * Instancia de aleatorio para diversos usos.
     */
    Random test = new Random(System.currentTimeMillis());

    /**
     * 
     */
    private double cambioInicio = test.nextInt(2000) + 1;

    /**
     * Total de tickets vendidos
     */
    private int totalTicketsVendidos;

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
     * Método para la compra-venta de boletos.
     * 
     * @param vis {@link}Visitante en cuestión.
     */
    public void venta(Visitante vis) {
        colaPrioridad.queue(vis);
        ganancias += vis.precioActividad;
        totalTicketsVendidos++;
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
        resumen += "Total de Boletos Vendidos: " + totalTicketsVendidos + "\n";
        resumen += "Actividad más vendida: " + actividadMasVendida().toString() + "\n";
        resumen += "Actividad menos vendida :" + actividadMenosVendida().toString() + "\n";
        resumen += "Ganancias Totales del día: " + ganancias;
        return resumen;
    }

    /**
     * Contador de dias trabajados
     */
    private int contadorDias = 0;

    /**
     * Dia de ejecución del programa. Es simulada.
     */
    LocalDate fechaActual = LocalDate.now();

    /**
     * Método privado para imprimir la hora en que se hacen los movimientos
     * en el ticket.
     *
     * @return representación en cadena de la hora.
     */
    /*
     * private String regresaHora() {
     * DateTimeFormatter fechaFormato =
     * DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
     * String formattedDate = "Fecha y Hora actuales: " +
     * fechaActual.format(fechaFormato) + "\n";
     * return formattedDate;
     * }
     */

    /**
     * Métódo que genera el archivo de salida de CCU por dia.
     *
     * @param veh     Vehiculo del cual se generará el ticket.
     * @param counter Número de Dia trabajado/simulado.
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

    private void terminaDia() throws IOException {
        fechaActual.plusDays(1000);
        generaResumen();
        contadorDias++;
        totalTicketsVendidos = 0; // para reiniciar secuencia.
    }

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
        // System.out.println(nuevo.contadorDias);

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
