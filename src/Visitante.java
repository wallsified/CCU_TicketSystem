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

  /**
   * Nivel de prioiridad del visitante
   * según su membresia.
   * Nota. Cambiar a char o int funciona igual?
   */
  private int proridad;

  /**
   * Pago del Visitante. 
   */
  public double pago = new Random().nextDouble(200)+1;

  /**
   * Constructor de Visitante.
   */
  public Visitante() {
    seleccionaMembresiaAleatoria();
  }

  /**
   * Tipos de Membresia que puede tener el visitante.
   */
  private enum tipoMembresia {
    MiembroClub,
    ComunidadUNAM,
    Estudiante,
    VisitanteNormal
  }

  /**
   * Método para seleccionar una membresia aleatoria.
   * Como simulamos n cantidad de visitantes, tiene sentido
   * que tengan membresias aleatorias.
   */
  private void seleccionaMembresiaAleatoria() {
    tipoMembresia hi = tipoMembresia.values()[new Random().nextInt(tipoMembresia.values().length)];
    asignaPrioridad(hi);
  }



  /**
   * Idea de método para asignar prioridades según tipo
   * de visitante. Revisar si no interfiere con la forma
   * en la que agregamos a la Cola de Prioridad.
   * 
   * @param tipo
   */
  private int asignaPrioridad(tipoMembresia tipo) {

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
