package System;
import java.util.Random;

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


    public int calculoBoletosPorActividad() {
        return 0;
    }

    /**
     * Método para la compra-venta de boletos.
     * 
     * @param vis {@link}Visitante en cuestión.
     */
    public void venta(Visitante vis) {
        // darCambio(vis.pago);
        totalTicketsVendidos++;

    }

    /**
     * Método que calcula el cambio del ticket.
     * 
     * @param cobrar cobro del ticket.
     * @return cambio a entregar.
     */
    public double darCambio(double cobrar) {
        return 0;
    }

    /**
     * Método que genera un resumen del dia laborado del CCU
     * 
     * @return Resumen en cadena.
     */
    public String CCUToText() {
        String resumen = "\n";
        resumen += "Caja Abierta con " + cambioInicio + "\n";
        resumen += "Total de Boletos Vendidos: " + totalTicketsVendidos + "\n";
        // resumen += "Actividad más vendida: " + Actividad.boletosVendidosPorActividad
        // + "\n";
        // resumen += "Actividad menos vendida :" + "\n";
        resumen += "Ganancias Totales del día: " + ganancias;
        return resumen;
    }

    public static void main(String[] args) {
        Visitante daniel = new Visitante();
        CCU nuevo = new CCU();
        nuevo.venta(daniel);
        System.out.println(nuevo.CCUToText());
    }

}
