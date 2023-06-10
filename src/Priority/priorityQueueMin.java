package Priority;
import java.util.NoSuchElementException;
import datos.PilasAndColas.Coleccionable;


public class priorityQueueMin<T extends Comparable<T>> extends MinHeap<T> implements Encolable<T>{

    public priorityQueueMin() {

    }

    public priorityQueueMin(Coleccionable<T> c) {
        super(c);
    }

    @Override
    public void queue(T elemento) throws IllegalArgumentException {
        if(elemento == null)
            throw new IllegalArgumentException();        
        super.agregar(elemento);        
    }

    @Override
    public T dequeue() throws NoSuchElementException {
        if(siguiente == 0)
            throw new NoSuchElementException();
        return eliminar();
    }

    @Override
    public T peek() {
        return arreglo[0];
    }

}