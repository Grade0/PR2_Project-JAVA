package com.unipi.project1;

import java.util.TreeSet;
import java.util.HashSet;

public class Category<E extends Data> {

    /**
     * Overview: tipo di dato astratto rappresentante una categoria di dati.
     *
     * Typical Element: <category, contents, friends>, dove
     *                  category è un valore di tipo String rappresentante il nome della categoria,
     *                  contents è un albero binario di ricerca di tipo TreeSet<E> contenente i dati appartenenti alla categoria,
     *                  friends è una tabela hash di tipo HashSet<String> contenente gli amici appartenenti alla categoria.
     *
     * Representation Invariant: RI(c) = c.category != null && c.contents != null && c.friends != null
     *                                   && gli elementi di contents sono distinti
     *                                   && gli elementi di friends sono distinti
     *
     */

    private String category;
    private TreeSet<E> contents;
    private HashSet<String> friends;

    /**
     * REQUIRES: name != null
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se name = null
     * EFFECTS: istanzia un oggetto della classe, inizializzando la category con nome e le strutture dati
     */
    public Category(String name) {
        if(name == null) throw new NullPointerException();
        this.category = name;
        this.contents = new TreeSet<E>();
        this.friends = new HashSet<String>();
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: restituisce la categoria category
     */
    public String getCategory() {
        return category;
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: restituisce l'insieme di dati contents della categoria
     */
    public TreeSet<E> getContents() {
        return contents;
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: restituisce l'insieme di amici friends della categoria
     */
    public HashSet<String> getFriends() {
        return friends;
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: istanzia la categoria category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * REQUIRES: /
     * MODIFIES: this
     * THROWS: /
     * EFFECTS: istanzia albero binario contents
     */
    public void setContents(TreeSet<E> contents) {
        this.contents = contents;
    }

    /**
     * REQUIRES: /
     * MODIFIES: this
     * THROWS: /
     * EFFECTS: istanzia l'HashSet friends
     */
    public void setFriends(HashSet<String> friends) {
        this.friends = friends;
    }

}
