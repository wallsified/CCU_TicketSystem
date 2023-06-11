package System;

import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

public class Visitante implements Comparable<Visitante> {

  /**
   * Acticidad del cliente.
   */
  protected Actividad act;

  /**
   * Tipo de membresía del cliente.
   */
  protected Membresia memb;

  /**
   * Marca de tiempo única para cada visitante.
   * Añadimos 100 segundos extra entre cada visitante para simular una
   * marca de tiempo diferente aun cuando creamos visitantes al mismo tiempo.
   * Ocupamos esta marca para comparar entre visitantes.
   */
  protected Timestamp timeMark = Timestamp.from(Instant.now().plusSeconds(100));

  /**
   * Marca de tiempo usada únicamente para estilizar la hora de entrada.
   */

  LocalDateTime entrada = LocalDateTime.now().plusSeconds(100);

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
  public int prioridad;

  /**
   * Constructor de Visitante.
   */
  public Visitante() {
    memb = seleccionaMembresiaAleatoria();
    act = seleccionaActividadAleatoria();
    act.boletosVendidosPorActividad++;
    asignaciónActividades(act);
    asignaPrioridad(memb);
  }

  /**
   * Tipos de Membresia que puede tener el visitante.
   */
  private enum Membresia {
    /** MiembroClub */
    MiembroClub,
    /** Comunidad UNAM */
    ComunidadUNAM,
    /** Estudiante */
    Estudiante,
    /** Visitante Normal */
    VisitanteNormal;

    /**
     * Método para imprimir en texto
     * el tipo de Membresía.
     * 
     * @return Cadena de la membresia.
     */
    @Override
    public String toString() {
      switch (this) {
        case MiembroClub:
          return "Miembro CCU";
        case ComunidadUNAM:
          return "Comunidad UNAM";
        case Estudiante:
          return "Estudiante";
        case VisitanteNormal:
          return "Visitante Normal";
        default:
          return null;
      }
    }
  }

  /**
   * Enum de actividades variadas. Cada actividad tiene
   * propiedades de precio, cupo y cantidad de boletos
   * propios.
   */
  protected enum Actividad {
    /** Cine */
    Cine,
    /** Exposición */
    Expo,
    /** Museo */
    Museo,
    /** Teatro */
    Teatro;

    /** Boletos vendidos por actividad */
    protected int boletosVendidosPorActividad;

    /** Método para sumar uno al total de voletos por actividad. */
    public void boletoVendido() {
      boletosVendidosPorActividad++;
    }

    /**
     * Método para imprimir en texto
     * el tipo de Actividad.
     * 
     * @return Cadena de la Actividad.
     */
    @Override
    public String toString() {
      switch (this) {
        case Cine:
          return "Cine";
        case Expo:
          return "Exposición";
        case Museo:
          return "Museo";
        case Teatro:
          return "Teatro";
        default:
          return null;
      }
    }
  }

  /**
   * Método para poder formatear la hora de entrada del visitante.
   * 
   * @return Entrada del visitante formateada.
   */
  private String entradaFormateada() {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:sS:n");
    return entrada.format(formato);
  }

  /**
   * Metodo para asignar valores de cupo y pago dada una
   * actividad.
   * 
   * @param act Activad a asignar valores.
   */
  private void asignaciónActividades(Actividad act) {
    switch (act) {
      case Cine:
        precioActividad = 60;
        cupoPorActividad = 80;
        break;
      case Expo:
        precioActividad = 40;
        cupoPorActividad = 100;
        break;
      case Teatro:
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
        prioridad = 1;
        break;
      case ComunidadUNAM:
        prioridad = 2;
        break;
      case Estudiante:
        prioridad = 3;
        break;
      default:
        prioridad = 4;
    }
    return prioridad;
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

  /**
   * Método para comparar visitantes. Revisamos
   * prioridad y hora de entrada.
   * 
   * @param vis Visitante a comparar.
   * @return <code>true</code> si se habla del mismo visitante,
   *         <code>false</code> en otro caso.
   */
  @Override
  public int compareTo(Visitante vis) {

    if (prioridad != vis.prioridad)
      return prioridad - vis.prioridad;

    if (timeMark.before(vis.timeMark))
      return -1;
    else if (timeMark.after(vis.timeMark))
      return 1;

    // return (entradaFormato.equals(vis.entradaFormato)) ? 1 : -1;

    return 0;
  }

  /**
   * Método para imprimir la información del visitante.
   * 
   * @return Información del visitante.
   */
  @Override
  public String toString() {
    String vis = "";
    vis += "Membresia: " + memb.toString() + "\n";
    vis += "Prioridad: " + this.prioridad + "\n";
    vis += "Hora de entrada: " + this.entradaFormateada() + "\n";
    vis += "Actividad: " + act.toString() + "\n";
    return vis;
  }
}
