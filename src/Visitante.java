import java.util.Random;

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

  private Actividad act;
  private Membresia memb;

  public double pago = new Random().nextDouble(200) + 1;

  protected int precioActividad;

  protected int boletosVendidosPorActividad;

  protected int cupoPorActividad;

  private int proridad;

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

  /*
   * Enum de actividades. Simplemente por variedad.
   */
  private enum Actividad {
    Cine1, Cine2, Cine3, Museo, Teatro1, Teatro2
  }

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

  public Actividad seleccionaActividadAleatoria() {
    return Actividad.values()[new Random().nextInt(Actividad.values().length)];
  }


  /**
   * Método para seleccionar una membresia aleatoria.
   * Como simulamos n cantidad de visitantes, tiene sentido
   * que tengan membresias aleatorias.
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
   * @param tipo
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

}
