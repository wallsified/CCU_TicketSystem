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

    /**
     * Constructor principal.
     */
    public CCU() {

    }

    public int calculoBoletosPorActividad(){
        return 0;
    }


    /**
     * Método para la compra-venta de boletos.
     * 
     * @param vis {@link}Visitante en cuestión.
     */
    public void venta(Visitante vis) {
        //Actividad hi = new Actividad(vis);
        darCambio(vis.pago);

    }

    /**
     * Método que calcula el cambio del ticket.
     * 
     * @param cobrar cobro del ticket.
     * @return cambio a entregar.
     */
    public double darCambio(double cobrar) {
        return cambioInicio - cobrar;
    }

    /**
     * Método que genera un resumen del dia laborado del CCU
     * 
     * @return Resumen en cadena.
     */
    public String CCUToText() {
        String resumen = "";
        resumen += "Caja Abierta con " + cambioInicio + "\n";
        resumen += "Total de Boletos Vendidos: " + totalTicketsVendidos + "\n";
        resumen += "Actividad más vendida: " + "\n";
        resumen += "Actividad menos vendida :" + "\n";
        resumen += "Ganancias Totales del día: " + ganancias;
        return resumen;
    }

   /*  public static void main(String[] args) {
        Visitante daniel = new Visitante();
        venta(daniel);
        CCUToText();
    } */
}
