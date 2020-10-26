package com.unipi.project1;

import java.util.TreeSet;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import com.unipi.project1.Exceptions.*;

public class Board<E extends Data> implements DataBoard<E> {

    /**
     * Overview: tipo di dato astratto rappresentante una bacheca virtuale di sottotipi di Data,
     *           gestita da un certo proprietario.
     *
     * Abstraction Function: AF(c) = <c.owner, c.passwd, c.contents, c.friends>, dove
     *                               owner e' un valore di tipo String che rappresenta il proprietario della bacheca,
     *                               psswd e' un valore di tipo String che rappresenta la password utilizzata per l'autenticazione da parte del proprietario,
     *                               contents e' una tavola Hash di tipo HashMap<String, TreeSet<E>> che utilizza come chiave una categoria della bacheca
     *                               e come valore un albero binario di ricerca che contiene i dati della categoria ordinati per numero di like,
     *                               friends e' anch'essa una tavola hash ma di tipo HashMap<String, HashSet<String>> che utilizza come chiave una categoria della bacheca
     *                               e come valore delle tavole hash di tipo HashSet<String> che contengono l'insieme degli amici che possono visualizzare i contenuti della categoria.
     *
     * Representation Invariant: RI(c) = c.owner, c.passwd, c.contents, c.friends != null
     *                                   && i dati in contents sono distinti
     *                                   && gli amici in friends sono distinti
     *                                   && le chiavi della tavola hash (categorie) sono distinte (vero per definizione di tavola hash)
     *
     */

    private HashMap<String, TreeSet<E>> contents;
    private HashMap<String, HashSet<String>> friends;
    private String owner;
    private String psswd;


    public Board(String owner, String psswd)
            throws NullPointerException, InvalidDataException {
        DataVerifier.verifyUser(owner);
        DataVerifier.verifyPassw(psswd);
        this.owner = owner;
        this.psswd = psswd;
        this.contents = new HashMap<String, TreeSet<E>>();
        this.friends = new HashMap<String, HashSet<String>>();
    }


    public String getOwner() {
        return new String(this.owner);
    }


    public void checkPasswd(String p)
            throws UnauthorizedLoginException, NullPointerException, InvalidDataException {
        DataVerifier.verifyPassw(p);
        if(!this.psswd.equals(p)) throw new UnauthorizedLoginException("Wrong Credentials");
    }


    @Override
    public void createCategory(String category, String psswd)
            throws DuplicateDataException, NullPointerException, InvalidDataException, UnauthorizedLoginException {
        DataVerifier.verifyCategory(category);
        this.checkPasswd(psswd);
        if(this.contents.containsKey(category)) throw new DuplicateDataException(category);
        if(this.friends.containsKey(category)) throw new DuplicateDataException(category);
        this.contents.put(category, new TreeSet<E>());
        this.friends.put(category, new HashSet<String>());
    }


    @Override
    public void removeCategory(String category, String psswd)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException {
        DataVerifier.verifyCategory(category);
        this.checkPasswd(psswd);
        if(this.contents.remove(category) == null) throw new DataNotFoundException("category: " + category);
        if(this.friends.remove(category) == null) throw new DataNotFoundException("category: " + category);
    }


    @Override
    public void addFriend(String category, String psswd, String friend)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException, DuplicateDataException {
        DataVerifier.verifyCategory(category);
        DataVerifier.verifyUser(friend);
        this.checkPasswd(psswd);
        if(!this.friends.containsKey(category)) throw new DataNotFoundException("category: " + category);
        HashSet<String> frs = this.friends.get(category);
        // controllo preventivo
        if(frs == null) throw new NullPointerException();
        if(frs.contains(friend)) throw new DuplicateDataException("friend: " + friend);
        frs.add(friend);
    }


    @Override
    public void removeFriend(String category, String psswd, String friend)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException {
        DataVerifier.verifyCategory(category);
        DataVerifier.verifyUser(friend);
        this.checkPasswd(psswd);
        if(!this.friends.containsKey(category)) throw new DataNotFoundException("category: " + category);
        HashSet<String> frs = this.friends.get(category);
        // controllo preventivo
        if(frs == null) throw new NullPointerException();
        if(!frs.contains(friend)) throw new DataNotFoundException("friend: " + friend);
        frs.remove(friend);
    }


    @Override
    public boolean put(String psswd, E data, String category)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException, DuplicateDataException {
        if(data == null) throw new NullPointerException();
        DataVerifier.verifyCategory(category);
        this.checkPasswd(psswd);
        if(!this.contents.containsKey(category)) throw new DataNotFoundException("category: " + category);
        TreeSet<E> cts = this.contents.get(category);
        // controllo preventivo
        if(cts == null) throw new NullPointerException();
        if(!data.getAuthor().equals(this.getOwner())) throw new InvalidDataException("Author-Owner mismatch");
        E cloned = (E)data.clone();
        cloned.setCategory(category);
        if(cts.contains(cloned)) throw new DuplicateDataException(cloned.display() + " in category " + category);
        cts.add(cloned);
        return true;
    }


    @Override
    public E get(String psswd, E data)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException {
        if(data == null) throw new NullPointerException();
        this.checkPasswd(psswd);
        E x = null;
        for(TreeSet<E> t : this.contents.values()) {
            if(t.contains(data)) x = data;
        }
        if(x == null) throw new DataNotFoundException(data.display());
        return x;
    }


    @Override
    public E remove(String psswd, E data)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException {
        if(data == null) throw new NullPointerException();
        this.checkPasswd(psswd);
        E x = null;
        for(TreeSet<E> t : this.contents.values()) {
            if(t.remove(data)) x = data;
        }
        if(x == null) throw new DataNotFoundException(data.display());
        return x;
    }


    @Override
    public List<E> getDataCategory(String psswd, String category)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException {
        DataVerifier.verifyCategory(category);
        this.checkPasswd(psswd);
        if(!this.contents.containsKey(category)) throw new DataNotFoundException("category: " + category);
        TreeSet<E> cts = this.contents.get(category);
        // controllo preventivo
        if(cts == null) throw new NullPointerException();
        return new ArrayList<E>(cts);
    }


    @Override
    public void insertLike(String friend, E data)
            throws DuplicateDataException, NullPointerException, InvalidDataException, DataNotFoundException {
        if(data == null) throw new NullPointerException();
        DataVerifier.verifyUser(friend);
        E x = null;
        for(String category : this.contents.keySet()) {
            TreeSet<E> t = this.contents.get(category);
            if(this.friends.get(category) == null) throw new NullPointerException("friends list in " + category);
            if(this.friends.get(category).contains(friend)) {
                if(t.contains(data)) {
                    t.remove(data);
                    x = (E) data.clone();
                    try {
                        x.addLike(friend);

                    } catch(Exception e) {
                        t.add(x);
                        throw e;
                    }
                    System.out.println("Just added like by " + friend + " to " + x.display());
                    t.add(x);
                }
            }
        }
        if(x == null) throw new DataNotFoundException("post not found or @" + friend + " is not authorized to view it");
    }


    @Override
    public Iterator<E> getIterator(String psswd)
            throws NullPointerException, InvalidDataException, UnauthorizedLoginException{
        this.checkPasswd(psswd);
        TreeSet <E> all = new TreeSet<E>();
        for(TreeSet<E> t : this.contents.values()) {
            for(E e : t) {
                all.add(e);
            }
        }

        SortedSet<E> unmodifiableAll = Collections.unmodifiableSortedSet(all);
        return unmodifiableAll.iterator();
    }


    @Override
    public Iterator<E> getFriendIterator(String friend)
            throws NullPointerException, InvalidDataException {
        DataVerifier.verifyUser(friend);
        ArrayList <E> all = new ArrayList<E>();
        for(String cat : this.contents.keySet()) {
            if(this.friends.get(cat).contains(friend)) {
                for(E e : this.contents.get(cat)) {
                    all.add(e);
                }
            }
        }   List<E> unmodifiableAll = Collections.unmodifiableList(all);
        return unmodifiableAll.iterator();
    }
}
