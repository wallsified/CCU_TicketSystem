package Priority;

import java.util.Iterator;
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

    private class Iterador<t extends Comparable<T>> implements Iterator<T> {

        /** Índice del iterador. */
        private priorityQueueMin<T> aux = new priorityQueueMin<T>();

        /** Construye un nuevo iterador, auxiliándose del heap mínimo. */
        public Iterador() {
            aux = new priorityQueueMin<T>(arreglo);
        }

        /**
         * Método para saber si hay siguiente.
         * 
         * @return <code>true</code> si hay siguiente, <code>false</code> en otro caso.
         */
        @Override
        public boolean hasNext() {
            return !aux.esVacia();
        }

        /**
         * Regresa el siguiente elemento.
         * 
         * @return Siguiente elemento.
         */
        @Override
        public T next() {
            return (T) aux.eliminar();
        }

        /** No lo implementamos: siempre lanza una excepción. */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

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
     * Constructor que recibe un arreglo genérico.
     * 
     * @param c Arreglo desde el que se construye.
     */
    public priorityQueueMin(T[] c) {
        for (T t : c)
            agregar(t);
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
    public void reordena(int elemento) {
        super.reordena(elemento);
    }

    @Override
    public String toString() {
        String out = "\n";
        for (T t : this) 
            out += t + "\n"; 
        return out;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterador<T>();
    }
}