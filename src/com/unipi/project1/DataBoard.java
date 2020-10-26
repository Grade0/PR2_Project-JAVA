package com.unipi.project1;

import java.util.Iterator;
import java.util.List;

import com.unipi.project1.Exceptions.*;

public interface DataBoard<E extends Data> {

    /**
     * REQUIRES: category != null, psswd != null,
     *           0 < category.length < 50, 0 < psswd.length < 50,
     *           password psswd corretta, categoria category inesistente
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se category = null || psswd = null,
     *         InvalidDataException (checked) se category.length <= 0 || category.length > 50
     *                                     || se psswd.length <= 0 || psswd.length > 50
     *         UnauthorizedLoginException (checked) se la password psswd è errata
     *         DuplicateDataException (checked) se la categoria category esiste già
     * EFFECTS: crea una categoria di dati con nome category nella bacheca
     */
    public void createCategory(String category, String psswd)
            throws DuplicateDataException, NullPointerException, InvalidDataException, UnauthorizedLoginException;

    /**
     * REQUIRES: category != null, psswd != null,
     *           0 < category.length < 50, 0 < psswd.length < 50,
     *           password passwd corretta, categoria category esistente
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se category = null || psswd = null,
     *         InvalidDataException (checked) se category.length <= 0 || category.length > 50
     *                                     || se psswd.length <= 0 || psswd.length > 50
     *         UnauthorizedLoginException (checked) se la password psswd è errata
     *         DataNotFoundException (checked) se la categoria category non esiste
     * EFFECTS: rimuove la categoria di dati di nome category dalla bacheca
     */
    public void removeCategory(String category, String psswd)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException;

    /**
     * REQUIRES: category != null, psswd != null, friend != null,
     *           0 < category.length < 50, 0 < psswd.length < 50, 0 < friend.length < 50
     *           password psswd corretta, categoria category esistente, amico friend inesistente
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se category = null || psswd = null || friend = null,
     *         InvalidDataException (checked) se category.length <= 0 || category.length > 50
     *                                     || se psswd.length <= 0 || psswd.length > 50
     *                                     || se friend.length <= 0 || friend.length > 50
     *         UnauthorizedLoginException (checked) se la password psswd è errata,
     *         DataNotFoundException (checked) se la categoria category non esiste,
     *         DuplicateDataException (checked) se l'amico friend esiste già
     * EFFECTS: aggiunge un amico di nome friend alla categoria di dati category
     */
    public void addFriend(String category, String psswd, String friend)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException, DuplicateDataException;

    /**
     * REQUIRES: category != null, psswd != null, friend != null,
     *           0 < category.length < 50, 0 < psswd.length < 50, 0 < friend.length < 50
     *           passsword psswd corretta, categoria category esistente, amico friend esistente
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se category = null || psswd = null || friend = null,
     *         InvalidDataException (checked) se category.length <= 0 || category.length > 50
     *                                     || se psswd.length <= 0 || psswd.length > 50
     *                                     || se friend.length <= 0 || friend.length > 50
     *         UnauthorizedLoginException (checked) se la password psswd è errata,
     *         DataNotFoundException (checked) se la categoria category non esiste o se l'amico friend non esiste
     * EFFECTS: rimuove l'amico friend dalla categoria category
     */
    public void removeFriend(String category, String psswd, String friend)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException;

    /**
     * REQUIRES: psswd != null, data != null, category != null,
     *           0 < psswd.length < 50, 0 < category.length < 50
     *           password passwd corretta, categoria category esistente, dato data inesistente
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se psswd = null || data = null || category = null,
     *         InvalidDataException (checked) se category.length <= 0 || category.length > 50
     *                                     || se psswd.length <= 0 || psswd.length > 50
     *         UnauthorizedLoginException (checked) se la password passwd è errata,
     *         DataNotFoundException (checked) se la categoria category non esiste,
     *         DuplicateDataException (checked) se il dato data esiste già
     * EFFECTS: inserisce il dato data nella categoria category in bacheca
     */
    public boolean put(String psswd, E data, String category)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException, DuplicateDataException;

    /**
     * REQUIRES: psswd != null, data != null,
     *           0 < psswd.length < 50
     *           password psswd corretta, dato data esistente
     * MODIFIES: /
     * THROWS: NullPointerException (unchecked) se psswd = null || dato = null,
     *         InvalidDataException (checked) se psswd.length <= 0 || psswd.length > 50
     *         UnauthorizedLoginException (checked) se la password psswd è errata,
     *         DataNotFoundException (checked) se il dato data non esiste
     * EFFECTS: restituisce una copia del dato data in bacheca
     */
    public E get(String psswd, E data) throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException;

    /**
     * REQUIRES: passwd != null, data != null,
     *           0 < psswd.length < 50
     *           password passwd corretta, dato data esistente
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se psswd = null || data = null,
     *         InvalidDataException (checked) se psswd.length <= 0 || psswd.length > 50
     *         UnauthorizedLoginException (checked) se la password psswd è errata,
     *         DataNotFoundException (checked) se il dato data non esiste
     * EFFECTS: rimuove il dato data dalla bacheca
     */
    public E remove(String psswd, E data) throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException;

    /**
     * REQUIRES: psswd != null, category != null,
     *           password psswd corretta, categoria category esistente
     * MODIFIES: /
     * THROWS: NullPointerException (unchecked) se psswd = null || category = null || se la categoria è vuota
     *         InvalidDataException (checked) se category.length <= 0 || category.length > 50
     *                                     || se psswd.length <= 0 || psswd.length > 50
     *         UnauthorizedLoginException (checked) se la password psswd è errata,
     *         DataNotFoundException (checked) se la categoria non esiste
     * EFFECTS: restituisce la lista dei dati in bacheca appartenenti alla categoria category
     */
    public List<E> getDataCategory(String psswd, String category) throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException;

    /**
     * REQUIRES: friend != null, data != null,
     *           0 < friend.length < 50
     *           liste di amici delle categorie non vuote, dato data esistente
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se friend = null || data = null
     *                                       || se non ci sono amici nella lista della categoria
     *         InvalidDataException (checked) se friend.length <= 0 || friend.length > 50
     *         DuplicateDataException (checked) se l'amico friend ha già messo like al dato data
     *         DataNotFoundException (checked) se il dato data non esiste
     * EFFECTS: aggiunge un like al dato data a nome dell'amico friend
     */
    public void insertLike (String friend, E data) throws DuplicateDataException, NullPointerException, InvalidDataException, DataNotFoundException;

    /**
     * REQUIRES: psswd != null,
     *           0 < psswd.length < 50
     *           password psswd corretta
     * MODIFIES: /
     * THROWS: NullPointerException (unchecked) se psswd = null
     *         InvalidDataException (checked) se psswd.length <= 0 || psswd.length > 50
     *         UnauthorizedLoginException (checked) se la password passwd è errata
     * EFFECTS: restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al
     *          numero di like (in modo decrescente)
     */
    public Iterator<E> getIterator(String psswd) throws NullPointerException, InvalidDataException, UnauthorizedLoginException;

    /**
     * REQUIRES: friend != null
     *           0 < friend.length < 50
     * MODIFIES: /
     * THROWS: NullPointerException (unchecked) se friend = null
     *         InvalidDataException (checked) se friend.length <= 0 || friend.length > 50
     * EFFECTS: restituisce un iteratore (senza remove) che genera tutti i dati in bacheca visi  bili all'amico friend
     */
    public Iterator<E> getFriendIterator(String friend) throws NullPointerException, InvalidDataException;
}