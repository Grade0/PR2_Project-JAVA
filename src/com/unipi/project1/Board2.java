package com.unipi.project1;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import com.unipi.project1.Exceptions.*;

public class Board2<E extends Data> implements DataBoard<E> {

    /**
     * Overview: tipo di dato astratto rappresentante una bacheca virtuale di sottotipi di Data,
     *           gestita da un certo proprietario.
     *
     * Abstraction Function: AF(c) = <c.owner, c.psswd, c.categories>, dove
     *                               owner è un valore di tipo String che rappresenta il proprietario della bacheca,
     *                               passwd è un valore di tipo String che rappresenta la password utilizzata per l'autenticazione da parte del proprietario,
     *                               categories è un ArrayList di tipo ArrayList<Category<E>> contenente le categorie della bacheca
     *
     * Representation Invariant: RI(c) = c.owner, c.psswd, c.categories != null
     *                                   && le categorie in categories sono distinte
     *
     */

    private ArrayList<Category<E>> categories;
    private String owner;
    private String psswd;


    public Board2(String owner, String psswd)
            throws NullPointerException, InvalidDataException {
        DataVerifier.verifyUser(owner);
        DataVerifier.verifyPassw(psswd);
        this.owner = owner;
        this.psswd = psswd;
        this.categories = new ArrayList<Category<E>>();
    }


    public String getOwner() {
        return new String(this.owner);
    }


    public void checkPasswd(String psswd)
            throws UnauthorizedLoginException, NullPointerException, InvalidDataException {
        DataVerifier.verifyPassw(psswd);
        if(!this.psswd.equals(psswd)) throw new UnauthorizedLoginException("Wrong Credentials");
    }


    @Override
    public void createCategory(String category, String psswd)
            throws DuplicateDataException, InvalidDataException, UnauthorizedLoginException {
        DataVerifier.verifyCategory(category);
        this.checkPasswd(psswd);
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) throw new DuplicateDataException(category);
        }
        Category<E> newCat = new Category<E>(category);
        this.categories.add(newCat);
    }


    @Override
    public void removeCategory(String category, String psswd)
            throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException {
        DataVerifier.verifyCategory(category);
        this.checkPasswd(psswd);
        Category<E> toRemove = null;
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) {
                toRemove = c;
            }
        }
        if(toRemove == null) throw new DataNotFoundException("category: " + category);
        this.categories.remove(toRemove);
    }


    @Override
    public void addFriend(String category, String psswd, String friend)
            throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException, DuplicateDataException {
        DataVerifier.verifyCategory(category);
        DataVerifier.verifyUser(friend);
        this.checkPasswd(psswd);
        Category<E> toAdd = null;
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) {
                toAdd = c;
            }
        }
        if(toAdd == null) throw new DataNotFoundException("category: " + category);
        this.categories.remove(toAdd);
        HashSet<String> frs = toAdd.getFriends();
        // this should not happen
        if(frs == null) throw new NullPointerException();
        if(frs.contains(friend)) {
            this.categories.add(toAdd);
            throw new DuplicateDataException("friend: " + friend);
        }
        frs.add(friend);
        toAdd.setFriends(frs);
        this.categories.add(toAdd);
    }


    @Override
    public void removeFriend(String category, String psswd, String friend)
            throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException {
        DataVerifier.verifyCategory(category);
        DataVerifier.verifyUser(friend);
        this.checkPasswd(psswd);
        Category<E> toRemove = null;
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) {
                toRemove = c;
            }
        }
        if(toRemove == null) throw new DataNotFoundException("category: " + category);
        this.categories.remove(toRemove);
        HashSet<String> frs = toRemove.getFriends();
        // this should not happen
        if(frs == null) throw new NullPointerException();
        if(!frs.contains(friend)) {
            this.categories.add(toRemove);
            throw new DataNotFoundException("friend: " + friend);
        }
        frs.remove(friend);
        toRemove.setFriends(frs);
        this.categories.add(toRemove);
    }


    @Override
    public boolean put(String psswd, E data, String category)
            throws DataNotFoundException, NullPointerException, InvalidDataException, UnauthorizedLoginException, DuplicateDataException {
        if(data == null) throw new NullPointerException();
        if(!data.getAuthor().equals(this.getOwner())) throw new InvalidDataException("Author-Owner mismatch");
        DataVerifier.verifyCategory(category);
        this.checkPasswd(psswd);
        Category<E> toAdd = null;
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) {
                toAdd = c;
            }
        }
        if(toAdd == null) throw new DataNotFoundException("category: " + category);
        this.categories.remove(toAdd);
        TreeSet<E> cts = toAdd.getContents();
        // this should not happen
        if(cts == null) throw new NullPointerException();
        E cloned = (E)data.clone();
        cloned.setCategory(category);
        if(cts.contains(cloned)) {
            this.categories.add(toAdd);
            throw new DuplicateDataException(cloned.display() + " in category " + category);
        }
        cts.add(cloned);
        toAdd.setContents(cts);
        this.categories.add(toAdd);
        return true;
    }


    @Override
    public E get(String psswd, E data)
            throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException {
        if(data == null) throw new NullPointerException();
        this.checkPasswd(psswd);
        E x = null;
        for(Category<E> c : this.categories) {
            if(c.getContents().contains(data)) x = data;
        }
        if(x == null) throw new DataNotFoundException(data.display());
        return x;
    }


    @Override
    public E remove(String psswd, E data)
            throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException {
        if(data == null) throw new NullPointerException();
        this.checkPasswd(psswd);
        Category<E> toRemove = null;
        E x = null;
        for(Category<E> c : this.categories) {
            if(c.getContents().remove(data)) {
                x = data;
            }
        }
        if(x == null) throw new DataNotFoundException(data.display());
        return x;
    }


    @Override
    public List<E> getDataCategory(String psswd, String category)
            throws DataNotFoundException, InvalidDataException, UnauthorizedLoginException {
        DataVerifier.verifyCategory(category);
        this.checkPasswd(psswd);
        Category<E> toReturn = null;
        for(Category<E> c : this.categories) {
            if(c.getCategory().equals(category)) {
                toReturn = c;
            }
        }
        if(toReturn == null) throw new DataNotFoundException("category: " + category);
        TreeSet<E> cts = toReturn.getContents();
        // this should not happen
        if(cts == null) throw new NullPointerException();
        return new ArrayList<E>(cts);
    }


    @Override
    public void insertLike(String friend, E data)
            throws DuplicateDataException, InvalidDataException, DataNotFoundException {
        if(data == null) throw new NullPointerException();
        E x = null;
        Category<E> updated = null;
        for(Category<E> c : this.categories) {
            TreeSet<E> t = c.getContents();
            if(c.getFriends().contains(friend) && t.contains(data)) {
                updated = c;
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
                c.setContents(t);
            }
        }
        if(x == null || updated == null) throw new DataNotFoundException("post not found or @" + friend + " is not authorized to view it");
    }


    @Override
    public Iterator<E> getIterator(String psswd)
            throws UnauthorizedLoginException, InvalidDataException {
        this.checkPasswd(psswd);
        TreeSet <E> all = new TreeSet<E>();
        for(Category<E> c : this.categories) {
            for(E e : c.getContents()) {
                all.add(e);
            }
        }

        SortedSet<E> unmodifiableAll = Collections.unmodifiableSortedSet(all);
        return unmodifiableAll.iterator();
    }


    @Override
    public Iterator<E> getFriendIterator(String friend)
            throws InvalidDataException {
        DataVerifier.verifyUser(friend);
        ArrayList <E> all = new ArrayList<E>();
        for(Category<E> c : this.categories) {
            if(c.getFriends().contains(friend)) {
                for(E e : c.getContents()) {
                    all.add(e);
                }
            }
        }
        List<E> unmodifiableAll = Collections.unmodifiableList(all);
        return unmodifiableAll.iterator();
    }
}
