package Priority;

import java.util.NoSuchElementException;
import datos.PilasAndColas.Coleccionable;

/**
 * Clase para MinHeap.
 * 
 * @author <a href="mailto:alejandrohmora@ciencias.unam.mx"> Alejandro Hernández
 *         Mora </a>
 * @version 1.0
 */
public class MinHeap<T extends Comparable<T>> extends Heap<T> {

  /**
   * Constructor sin parámetros.
   */
  public MinHeap() {
    super();
  }

  /**
   * Constructor que recibe un {@link}Coleccionable.
   * 
   * @param c Coleccionable para crear el MinHeap.
   */
  public MinHeap(Coleccionable<T> c) {
    super(c);
  }

  /**
   * Método para ordenar desde un indice,
   * considerando que dicho índice es minimo.
   * 
   * @param indice Indice desde el cual se reordena.
   */
  @Override
  protected void reordena(int indice) {
    super.reordena(indice, true);
  }

  /**
   * Método para eliminar en el MaxHeap.
   * Recordemos que reordenamos suponiendo
   * que el elemento eliminado es el minimo.
   */
  @Override
  public T eliminar() {
    if (siguiente == 0)
      throw new NoSuchElementException("No hay elementos en el Min-Heap");
    T min = arreglo[0];
    arreglo[0] = arreglo[siguiente - 1];
    arreglo[--siguiente] = null;
    super.reordenaParaAbajo(0, true);

    if (siguiente == 0)
      vaciar();

    return min;
  }

}
