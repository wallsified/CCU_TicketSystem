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

  // tipo de membresia
  private enum tipoMembresia {
    MiembroClub,
    ComunidadUNAM,
    Estudiante,
    VisitanteNormal
  }

  // nivel de prioridad
  private int proridad;

  /**
   * Idea de método para asignar prioridades según tipo
   * de visitante. Revisar si no interfiere con la forma
   * en la que agregamos a la Cola de Prioridad.
   * 
   * @param tipo
   */
  private void asignaPrioridad(tipoMembresia tipo) {

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

  }

}
