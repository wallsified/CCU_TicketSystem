package System;
import java.util.Random;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Clase Visitante. Aqui podemos manejar
 * el tipo de membresia del posible visitante
 * asi como la asignación de prioridad que
 * pueda tener. Quiza aqui podamos usar la cola
 * de prioridad, idk.
 * 
 * @version 1.0
 * @author wallsified
 * @author gentle_earthquake
 * 
 */

public class Visitante {

  /**
   * Acticidad del cliente.
   */
  private Actividad act;

  /**
   * Tipo de membresía del cliente.
   */
  private Membresia memb;

  /**
   * Marca de tiempo única para cada visitante.
   */
  private Timestamp entrada = Timestamp.from(Instant.now());;

  /**
   * Pago del visitante. Probablemente se elimine.
   */
  public double pago = new Random().nextDouble(200) + 1;

  /**
   * Precio de la actividad
   */
  protected int precioActividad;

  /**
   * Boletos vendidos por actividad.
   */
  protected int boletosVendidosPorActividad;

  /**
   * Cupo de la actividad.
   */
  protected int cupoPorActividad;

  /**
   * Prioridad que se le da al cliente.
   */
  public int proridad;

  /**
   * Constructor de Visitante.
   */
  public Visitante() {
    memb = seleccionaMembresiaAleatoria();
    act = seleccionaActividadAleatoria();
    asignaciónActividades(act);
    asignaPrioridad(memb);
  }

  /**
   * Tipos de Membresia que puede tener el visitante.
   */
  private enum Membresia {
    MiembroClub, ComunidadUNAM, Estudiante, VisitanteNormal
  }

  /**
   * Enum de actividades. Simplemente por variedad.
   */
  private enum Actividad {
    Cine1, Cine2, Cine3, Museo, Teatro1, Teatro2
  }

  /**
   * Metodo para asignar valores de cupo y pago dada una
   * actividad.
   * 
   * @param act Activad a asignar valores.
   */
  private void asignaciónActividades(Actividad act) {
    switch (act) {
      case Cine1, Cine2, Cine3:
        precioActividad = 60;
        cupoPorActividad = 80;
        break;
      case Teatro1, Teatro2:
        precioActividad = 80;
        cupoPorActividad = 150;
        break;
      default:
        precioActividad = 30;
        cupoPorActividad = 200;
    }
  }

  /**
   * Método para seleccionar una actividad aleatoria para un
   * visitante aleatorio. Esto por que simulamos el sistema.
   * 
   * @return Actividad aleatoria.
   */
  public Actividad seleccionaActividadAleatoria() {
    return Actividad.values()[new Random().nextInt(Actividad.values().length)];
  }

  /**
   * Método para seleccionar una membresia aleatoria.
   * Como simulamos n cantidad de visitantes, tiene sentido
   * que tengan membresias aleatorias.
   * 
   * @return Membresía aleatoria.
   */
  private Membresia seleccionaMembresiaAleatoria() {
    Membresia hi = Membresia.values()[new Random().nextInt(Membresia.values().length)];
    asignaPrioridad(hi);
    return hi;
  }

  /**
   * Idea de método para asignar prioridades según tipo
   * de visitante. Revisar si no interfiere con la forma
   * en la que agregamos a la Cola de Prioridad.
   * 
   * @param tipo tipo de membresia.
   * @return Prioridad asignada. (1 es mayor, 4 es la menor)
   */
  private int asignaPrioridad(Membresia tipo) {

    switch (tipo) {
      case MiembroClub:
        proridad = 1;
        break;
      case ComunidadUNAM:
        proridad = 2;
        break;
      case Estudiante:
        proridad = 3;
      default:
        proridad = 4;
    }
    return proridad;
  }

  /**
   * Método para verificar si dos visitantes son iguales.
   * Esto ayuda para poder "re-priorizar" según la hora de entrada.
   * De esta forma, aun se tenga la misma activad/membresia, se puede
   * atender por hora de llegada.
   * 
   * @param vis Visitante a comparar.
   * @return <code>true</code> si se habla del mismo visitante,
   *         <code>false</code> en otro caso.
   */
  public boolean equals(Visitante vis) {
    return act == vis.act && entrada.equals(vis.entrada) && memb == vis.memb;
  }

}
