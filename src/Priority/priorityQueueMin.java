package Priority;

import java.util.NoSuchElementException;
import datos.PilasAndColas.Coleccionable;
import datos.PilasAndColas.Encolable;

/**
 * Clase adaptada para usar minHeap con nuestros {@link}Visitantes
 * usando la interfaz de encolable.
 * 
 * @version 1.0
 * @author gentle_earthquake
 * @author wallsified
 */
public class priorityQueueMin<T extends Comparable<T>> extends MinHeap<T> implements Encolable<T> {

    /**
     * Constructor vació por omisión
     */
    public priorityQueueMin() {
        super();
    }

    /**
     * Constructor que recibe un coleccionable.
     * 
     * @param c Coleccionable desde el que se construye.
     */
    public priorityQueueMin(Coleccionable<T> c) {
        super(c);
    }

    /**
     * Método para añadir a cola/heap.
     * 
     * @param elemento Elemento a añadir a la cola.
     */
    @Override
    public void queue(T elemento) throws IllegalArgumentException {
        if (elemento == null)
            throw new IllegalArgumentException();
        super.agregar(elemento);
    }

    /**
     * Método para eliminar de la cola.
     * 
     * @throws NoSuchElementException En caso de no
     *                                tener siguiente en el heap.
     * 
     */
    @Override
    public T dequeue() throws NoSuchElementException {
        if (siguiente == 0)
            throw new NoSuchElementException();
        return eliminar();
    }

    /**
     * Método para regresar el primer elemento
     * en la cola.
     * 
     * @return Elemento en la posición 0.
     */
    @Override
    public T peek() {
        return arreglo[0];
    }

    /**
     * WIP
     */
    @Override
    public void reordena(int elemento){
        super.reordena(elemento);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}