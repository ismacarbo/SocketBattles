package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe che definisce una lista per coordinate non ordinata ma SENZA DUPLICATI
 *
 * @author Ismaele Carbonari
 * @param <T>
 */
public class BetterArrayList<T> implements AbstractAdt<T> {

    private T[] array;

    /**
     * Costruttore senza parametri
     */
    public BetterArrayList() {
        array = (T[]) new Object[0];
    }

    /**
     * Costruttore con una capacità iniziale
     *
     * @param intialCapacity
     */
    public BetterArrayList(Integer intialCapacity) {
        array = (T[]) new Object[intialCapacity];
    }

    /**
     * Costruttore con un elemento di inizio
     *
     * @param element
     */
    public BetterArrayList(T element) {
        array = (T[]) new Object[0];
        array[0] = element;
    }

    /**
     * Metodo che aggiunge un elemento all'array
     *
     * @param element
     */
    @Override
    public void add(T element) {
        if (Control.isNull(array)) {
            array = (T[]) new Object[0];
        }
        if (!Control.isNull(element) && !contains(element)) {
            T[] nuovo = (T[]) new Object[array.length + 1];
            System.arraycopy(array, 0, nuovo, 0, array.length);
            nuovo[nuovo.length - 1] = element;
            array = nuovo;
        }
    }

    /**
     * Metodo che rimuove in quella posizione l'elemento
     *
     * @param position
     */
    @Override
    public void remove(int position) {
        if (Control.isNull(array)) {
            array = (T[]) new Object[0];
        }
        if (!Control.isNull(position)) {
            T[] nuovo = (T[]) new Object[array.length - 1];
            int j = 0;
            for (int i = 0; i < array.length; i++) {
                if (i != position) {
                    nuovo[j++] = array[i];
                }
            }
            array = nuovo.clone();
        }
    }

    /**
     * Metodo che rimuove l'elemento specificato da parametro
     *
     * @param element
     */
    @Override
    public void remove(T element) {
        if (Control.isNull(array)) {
            array = (T[]) new Object[0];
        }

        if (!Control.isNull(element)) {
            T[] nuovo = (T[]) new Object[array.length - 1];
            int j = 0;
            for (int i = 0; i < array.length; i++) {
                if (!array[i].equals(element)) {
                    nuovo[j++] = array[i];
                }
            }
            array = nuovo.clone();
        }
    }

    /**
     * metodo che rimuove l'ultimo elemento dell'array
     */
    @Override
    public void remove() {
        if (Control.isNull(array)) {
            array = (T[]) new Object[0];
        }
        T[] nuovo = (T[]) new Object[array.length - 1];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                nuovo[j++] = array[i];
            }
        }
        array = nuovo.clone();
    }

    /**
     * Metodo che permette di clonare l'array contentente tutti gli elementi
     * @return
     */
    public BetterArrayList<T> clone(){
        BetterArrayList<T> nuova= (BetterArrayList<T>) Arrays.asList(array);
        return nuova;
    }

    /**
     * Metodo che ritorna l'oggetto nella posizione specificata
     *
     * @param position
     * @return
     */
    @Override
    public T get(Integer position) {
        return array[position];
    }

    /**
     * Metodo che pulisce l'array
     */
    @Override
    public void clear() {
        Arrays.fill(array,null);
    }

    /**
     * Metodo che verifica se l'elemento è presente
     *
     * @param element
     * @return
     */
    @Override
    public Boolean contains(T element) {
        List list=new ArrayList(Arrays.asList(array));
        return list.contains(element);
    }

    /**
     * Metodo che ritorna la grandezza dell'array
     *
     * @return
     */
    @Override
    public Integer size() {
        return array.length;
    }

    /**
     * Metodo che ritorna un clone dell'array interno
     *
     * @return
     */
    @Override
    public T[] toArray() {
        return array.clone();
    }

    /**
     * Metodo che ritorna una linked list con all'interno i valori dell'array
     * @return
     */
    @Override
    public LinkedList<T> toLinkedList(){
        LinkedList<T> out=new LinkedList<>();
        for(int i=0;i<array.length;i++){
            out.add(array[i]);
        }
        return out;
    }

    /**
     * Metodo stampa le informazioni dell'array
     *
     * @return
     */
    @Override
    public String toString() {
        String out = "";
        for (T array1 : array) {
            if (array1 != null) {
                out += array1;
            }
        }
        return out;
    }
}
