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
     * Enum de actividades. Simplemente por variedad.
     * Actividad como subclase con diferentes precios?
     * Even more. Actividades como padre de todas las 
     * demás?
     */
    private enum Actividades {
        Cine1,
        Cine2,
        Cine3,
        Museo,
        Teatro1,
        Teatro2,
    }

    /**
     * Ganancias
     */
    private double ganancias;

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
     * Método que calcula el cambio del ticket.
     * 
     * @param cobrar cobro del ticket.
     * @return cambio a entregar.
     */
    public double darCambio(int cobrar) {
        return cambioInicio - cobrar;
    }


    public String CCUToText(){
        return null;
    } 
}
