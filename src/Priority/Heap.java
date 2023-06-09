package Priority;
import java.util.Iterator;
import datos.PilasAndColas.Coleccionable;

/**
 * Clase para heaps mínimos (<i>min heaps</i>). Podemos crear un heap
 * mínimo con <em>n</em> elementos en tiempo <em>O</em>(<em>n</em>), y podemos
 * agregar y actualizar elementos en tiempo <em>O</em>(log <em>n</em>). Eliminar
 * el elemento mínimo también nos toma tiempo <em>O</em>(log <em>n</em>).
 * 
 * @author <a href="mailto:alejandrohmora@ciencias.unam.mx"> Alejandro Hernández
 *         Mora </a>
 * @version 1.0
 */
public abstract class Heap<T extends Comparable<T>> implements Coleccionable<T> {

  /**
   * Clase privada para iteradores de heaps.
   */
  private class Iterador implements Iterator<T> {

    /**
     * Índice del iterador.
     */
    private int actual;

    /**
     * Construye un nuevo iterador, auxiliándose del heap mínimo.
     */
    public Iterador() {
    }

    /**
     * Nos dice si hay un siguiente elemento.
     */
    @Override
    public boolean hasNext() {
      return siguiente < tamanio;
    }

    /**
     * Regresa el siguiente elemento.
     */
    @Override
    public T next() {
      T elem = arreglo[siguiente++];
      actual++; // por que tambien sumamos al indice actual no?
      return elem;
    }

    /**
     * No lo implementamos: siempre lanza una excepción.
     */
    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  /** El siguiente índice dónde agregar un elemento. */
  protected int siguiente;

  /** Tamaño del heap */
  protected int tamanio;

  /**
   * El arreglo que guarda los datos con la estructura de árbol ue requiere el
   * heap.
   */
  protected T[] arreglo;

  /*
   * Truco para crear arreglos genéricos. Es necesario hacerlo así por cómo
   * Java implementa sus genéricos; de otra forma obtenemos advertencias del
   * compilador.
   */
  @SuppressWarnings("unchecked")
  private T[] creaArregloGenerico(int n) {
    return (T[]) (new Comparable[n]);
  }

  /**
   * Constructor sin parámetros. Es más eficiente usar {@link
   * MinHeap}, pero se ofrece este constructor por completez.
   */
  public Heap() {
    this.arreglo = creaArregloGenerico(32);
    // 32 simplemente por ser potencia de 2, pero probar
    // con 16 ó 64 igual funcióno.
  }

  /**
   * Constructor para heap mínimo que recibe un coleccionable. Es más barato
   * construir un heap con todos sus elementos de antemano (tiempo
   * <i>O</i>(<i>n</i>)), que el insertándolos uno por uno (tiempo
   * <i>O</i>(<i>n</i> log <i>n</i>)).
   * 
   * @param coleccionable la lista a partir de la cuál queremos construir el
   *                      heap.
   */
  public Heap(Coleccionable<T> coleccionable) {
    arreglo = creaArregloGenerico(coleccionable.getTamanio() * 2);
    Iterator<T> hi = coleccionable.iterator();
    while (hi.hasNext()) {
      agregar(hi.next());
    }
  }

  /**
   * Métodos auxiliares. Es un swap de elementos en el arreglo.
   * 
   * @param i Indice del primer elemento a intercambiar.
   * @param j Indice del segundo elemento a intercambiar.
   */
  private void intercambia(int i, int j) {
    if ((0 <= i && i < arreglo.length) && (0 <= j && j < arreglo.length)) {
      T elem = (T) arreglo[i];
      arreglo[i] = arreglo[j];
      arreglo[j] = elem;
    }
  }

  /**
   * Método auxiliar que devuelve la posición del hijo izquierdo,
   * si no tiene hijo izquiero devuelve -1.
   * Recordemos que el hijo izquierdo se encuentra a 2i + i
   */
  private int izquierdo(int i) {
    if ((i * 2) + 1 < arreglo.length)
      return (i * 2) + 1;
    return -1;
  }

  /**
   * Método auxiliar que devuelve la posición del hijo derecho,
   * si no tiene hijo derecho devuelve -1.
   * Recordemos que el hijo izquierdo se encuentra a 2i + 2
   */
  private int derecho(int i) {
    if ((i * 2) + 2 < arreglo.length)
      return (i * 2) + 2;
    return -1;
  }

  /**
   * Método auxiliar que devuelve la posición del padre.
   * Si no tiene padre, devuelve -1;
   * Recordemos que el hijo izquierdo se encuentra a (i -1)/2
   */
  private int padre(int i) {
    if ((i - 1 / 2) >= 0)
      return (i - 1) / 2;
    return -1;
  }

  /**
   * Agrega un nuevo elemento en el heap.
   * 
   * @param elemento el elemento a agregar en el heap.
   */
  @Override
  public void agregar(T elemento) {
    if (siguiente == arreglo.length) {
      T[] aux = creaArregloGenerico(siguiente * 2);
      for (int i = 0; i < arreglo.length; i++)
        aux[i] = arreglo[i];
      arreglo = aux;
    }

    arreglo[siguiente] = elemento;
    reordena(siguiente);
    siguiente++;
    tamanio++;
  }

  /**
   * Método que reordena hacia abajo un elemento en un heap.
   * Si el elemento que queremos está en un orden incorrecto,
   * se intercambia con el hijo correspondiente para que el heap sea
   * válido. Es importante considerar si el heap es mínimo o máximo.
   * 
   * @param n     Indice desde donde se reordena.
   * @param esMin Si el elemento en el indice es el minimo o no.
   */
  protected void reordenaParaAbajo(int n, boolean esMin) {

  }

  /**
   * Elimina el elemento hasta arriba del heap.
   * 
   * @return el elemento mínimo o máximo del heap.
   * @throws IllegalStateException si el heap es vacío.
   */
  public abstract T eliminar();

  /**
   * Puesto que no deberíamos eliminar un elemento que
   * no sea el mínimo, lanzamos una excepción. En una
   * implementación más aplicada es posible implementar
   * esta operación con los métodos ya implementados sin mucho
   * esfuerzo.
   * 
   * @param elemento a eliminar del heap.
   * @throws IllegalStateException Esta operación no debería ser posible en un
   *                               Heap.
   */
  @Override
  public void eliminar(T elemento) throws IllegalStateException {
    throw new IllegalStateException("Esta operación no debería ser válida para Heaps");
  }

  /**
   * Nos dice si un elemento está contenido en el heap.
   * 
   * @param elemento el elemento que queremos saber si está contenido.
   * @return <code>true</code> si el elemento está contenido,
   *         <code>false</code> en otro caso.
   */
  @Override
  public boolean contiene(T elemento) {
    Iterator<T> hi = this.iterator();
    boolean existe = false;
    while (hi.hasNext()) {
      existe = (hi.next() == elemento) ? true : false;
    }
    return existe;
  }

  /**
   * Nos dice si el heap es vacío.
   * 
   * @return <code>true</code> si ya no hay elementos en el heap,
   *         <code>false</code> en otro caso.
   */
  public boolean esVacia() {
    return arreglo.length == 0; // tamanio == 0 seria igual de valido.
  }

  /**
   * Elimina todos los elementos del heap.
   * pone todos los espacios del arreglo en <code>null</code>
   */
  @Override
  public void vaciar() {
    tamanio = 0;
    arreglo = null; // aprovechar el recolector?
  }

  /**
   * Método auxiliar que regresa el índice en el arreglo que
   * tiene al elemento menor de tres.
   * Recibe tres índices de elementos en el arreglo.
   * 
   * @param elemento Elemento principal a comparar.
   * @param i        Segundo elemento en la comparación
   * @param d        Tercer elemento en la comparación
   */
  private int getMenor(int elemento, int i, int d) {
    if (arreglo[elemento].compareTo(arreglo[i]) <= 0 && arreglo[elemento].compareTo(arreglo[d]) <= 0)
      return elemento;

    if (arreglo[i].compareTo(arreglo[elemento]) <= 0 && arreglo[i].compareTo(arreglo[d]) <= 0)
      return i;

    if (arreglo[d].compareTo(arreglo[elemento]) <= 0 && arreglo[d].compareTo(arreglo[i]) <= 0)
      return d;

    return -1;
  }

  /**
   * Método auxiliar que regresa el índice en el arreglo que
   * tiene al elemento menor de tres.
   * Recibe tres índices de elementos en el arreglo.
   * 
   * @param elemento Elemento principal a comparar.
   * @param i        Segundo elemento en la comparación
   * @param d        Tercer elemento en la comparación
   */
  private int getMayor(int elemento, int i, int d) {
    if (arreglo[elemento].compareTo(arreglo[i]) >= 0 && arreglo[elemento].compareTo(arreglo[d]) >= 0)
      return elemento;

    if (arreglo[i].compareTo(arreglo[elemento]) >= 0 && arreglo[i].compareTo(arreglo[d]) >= 0)
      return i;

    if (arreglo[d].compareTo(arreglo[elemento]) >= 0 && arreglo[d].compareTo(arreglo[i]) >= 0)
      return d;

    return -1;
  }

  /**
   * Reordena un elemento en el árbol.
   * La implementación se deja a la clase concreta, pues el reordenamiento
   * depende de si es un heap mínimo o máximo.
   * 
   * @param elemento el elemento que hay que reordenar.
   */
  protected abstract void reordena(int elemento);

  /**
   * Este método hace la chamba, dependiendo de si
   * el heap es mínimo o máximo.
   * 
   * @param elemento el elemento a reordenar.
   * @param esMin    nos dice si el heap que manda a llamar esta operación es
   *                 mínimo.
   */
  protected void reordena(int elemento, boolean esMin) {
    int padre = padre(elemento);
    if (padre >= 0) {
      if (esMin && arreglo[elemento].compareTo(arreglo[padre]) < 0) {
        intercambia(elemento, padre);
        reordena(padre, esMin);
      } else if (!esMin && arreglo[elemento].compareTo(arreglo[padre]) > 0) {
        intercambia(elemento, padre);
        reordena(padre, esMin);
      }
    }
  }

  /**
   * Regresa el número de elementos en el heap.
   * 
   * @return el número de elementos en el heap.
   */
  @Override
  public int getTamanio() {
    return tamanio;
  }

  @Override
  /**
   * Regresa la representación en cadena del heap.
   * 
   * @return la representación en cadena del heap.
   */
  public String toString() {
    if (esVacia()) {
      return "[]";
    }

    String output = "\n["; // solo para tener un espacio extra
    for (int i = 0; i < siguiente; i++) {
      output += arreglo[i];
      if (i < siguiente - 1)
        output += ", ";
    }
    output += "]";

    return output;
  }

  /**
   * Regresa un iterador para recorrer el heap.
   * 
   * @return un iterador para recorrer el heap.
   */
  @Override
  public Iterator<T> iterator() {
    return new Iterador();
  }
}
