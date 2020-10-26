package com.unipi.project1;

import java.util.ArrayList;

import com.unipi.project1.Exceptions.*;

public class Data implements Comparable<Data> {

    /**
     * Overview: tipo di dato astratto rappresentante un dato generico,
     *           provvisto di un autore, una stringa di testo,
     *           numero di like e i nomi dei rispettivi amici che ne hanno aggiunti.
     *
     * Typical Element: <author, text, likes{like_1, ..., like_n}, numLikes>, dove
     *                  author e' un valore di tipo String che rappresenta l'autore del dato,
     *                  text e' un valore di tipo String che contiene del testo,
     *                  likes e' un array di tipo ArrayList<String> contenente i nomi degli amici che hanno messo like all'elemento,
     *                  numLikes e' un valore di tipo int che rappresenta la dimensione dell'array likes.
     *
     * Representation Invariant: RI(c) = c.author, c.text, c.likes, c.numLikes != null
     *                                   && c.numLikes = c.likes.size()
     *                                   && gli elementi di likes sono distinti
     */

    private String author;
    private String text;
    private String category;
    private int numlikes;
    private ArrayList<String> likes;

    /**
     * REQUIRES: author != null, text!= null
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se author = null || text = null
     *         InvalidDataException (checked) se author.length <= 0 || author.length > 50
     *                                     || se text.length <= 0 || text.length > 128
     * EFFECTS: istanzia un oggetto della classe, inizializzando l'autore author,
     *          la stringa text,
     *          il numero di like numLikes con la relativa lista likes
     */
    public Data(String author, String text)
            throws NullPointerException, InvalidDataException {
        DataVerifier.verifyUser(author);
        DataVerifier.verifyText(text);
        this.author = author;
        this.text = text;
        this.numlikes = 0;
        this.likes = new ArrayList<String>();
    }

    /**
     * REQUIRES: data != null
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se data = null
     * EFFECTS: istanzia un oggetto della classe con gli stessi valori degli attributi di un oggetto Data in input
     */
    public Data(Data e) {
        this.author = e.getAuthor();
        this.text = e.getText();
        this.category = e.getCategory();
        this.numlikes = e.getNumlikes();
        this.likes = e.getLikes();
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: restituisce una copia dell'oggetto Data
     */
    @Override
    public Data clone () {
        return new Data(this);
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: ritorna l'autore author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: ritorna la stringa text
     */
    public String getText() {
        return text;
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: ritorna la data di pubblicazione category
     */
    public String getCategory() {
        return category;
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
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: ritorna il numero di like numLikes
     */
    public int getNumlikes() {
        return numlikes;
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: ritorna la lista di like likes
     */
    public ArrayList<String> getLikes() {
        return new ArrayList<String>(this.likes);
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: restituisce gli attributi dell'oggetto in formato testuale
     */
    public String display() {
        return this.toString();
    }

    /**
     * REQUIRES: /
     * MODIFIES: /
     * THROWS: /
     * EFFECTS: restituisce i valori degli attributi dell'oggetto
     */
    @Override
    public String toString() {
        if(this.getCategory() == null)
            return "@" + this.getAuthor() + " says (liked by " + this.getNumlikes() + " people)\n" + this.getText();
        return "@" + this.getAuthor() + " says in category " + this.getCategory()
                + " (liked by " + this.getNumlikes() + " people)\n" + this.getText();
    }

    /**
     * REQUIRES: who != null
     *           who non abbia già messo like
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se friend = null
     *         InvalidDataException (checked) se who.length <= 0 || who.length > 50
     *         DuplicateDataException (checked) se who aveva già messo like
     * EFFECTS: aggiunge un like a nome dell'amico who e lo inserisce nella lista likes
     */
    public void addLike(String who)
            throws NullPointerException, InvalidDataException, DuplicateDataException {
        DataVerifier.verifyUser(who);
        if(this.likes.contains(who)) throw new DuplicateDataException("like by: " + who);
        this.numlikes++;
        this.likes.add(who);
    }

    /**
     * REQUIRES: friend != null,
     *           like di who deve essere già presente
     * MODIFIES: this
     * THROWS: NullPointerException (unchecked) se friend = null
     *         DataNotFoundException (checked) se il like di who non è presente
     * EFFECTS: rimuove il like a nome dell'amico who e lo rimuove dalla lista likes
     */
    public void removeLike(String who)
            throws InvalidDataException, DataNotFoundException {
        DataVerifier.verifyUser(who);
        if(!this.likes.contains(who)) throw new DataNotFoundException("like by: " + who);
        this.numlikes--;
        this.likes.remove(who);
    }

    /**
     * REQUIRES: data != null
     * MODIFIES: /
     * THROWS: NullPointerException (unchecked) se data = null
     * EFFECTS: compara l'oggetto locale con un oggetto Data e ritorna l'ordinamento da eseguire,
     *          (ordina gli oggetti in modo decrescente per il numero di like numLike, altrimenti per categoria o testo)
     */
    public int compareTo(Data o) {
        if(this.equals(o)) return 0;
        if(this.numlikes == o.getNumlikes()) {
            if(o.getCategory() != null && this.getCategory() != null) {
                if(this.category.equals(o.getCategory())) return this.text.compareTo(o.getText());
                return this.category.compareTo(o.getCategory());
            } else {
                return this.text.compareTo(o.getText());
            }
        }
        return o.getNumlikes() - this.numlikes;
    }
}