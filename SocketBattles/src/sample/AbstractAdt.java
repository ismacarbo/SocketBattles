package sample;

import java.util.LinkedList;

/**
 * Interfaccia che rappresenta l'implemetazione astratta di una lista
 * @author Ismaele Carbonari
 * @param <T>
 */
public interface AbstractAdt <T>{
    void add(T element);
    void remove(int position);
    void remove(T element);
    void remove();//remove the last element
    T get(Integer position);
    void clear();
    Boolean contains(T element);
    Integer size();
    T[] toArray();
    LinkedList<T> toLinkedList();
    @Override
    String toString();

}
