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
public class MaxHeap<T extends Comparable<T>> extends Heap<T> {

  /**
   * Constructor sin parámetros.
   */
  public MaxHeap() {
    super();
  }

  /**
   * Constructor que recibe un {@link}Coleccionable.
   * 
   * @param c Coleccionable para crear el MinHeap.
   */
  public MaxHeap(Coleccionable<T> c) {
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
    super.reordena(indice, false);
  }

  /**
   * Método para eliminar en el MaxHeap.
   * Recordemos que reordenamos suponiendo
   * que el elemento eliminado no es minimo.
   */
  @Override
  public T eliminar() {
    if (siguiente == 0)
      throw new NoSuchElementException("No hay elementos en el Max-Heap");
    T max = arreglo[0];
    arreglo[0] = arreglo[siguiente - 1];
    arreglo[--siguiente] = null;
    super.reordenaParaAbajo(0, false);
    return max;
  }

}
