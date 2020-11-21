# PR2_Project-JAVA
"Programming 2" Class Project - University of Pisa

## English Version (per l'italiano guardare sotto)
#### PROGRAMMING II - A.Y. 2019-20 | First Project

**Target:** The project aims to apply the concepts and techniques of Object-Oriented programming examined during the course.

**Description:** You are required to design, implemente and document the collection ***Databoard\<E extends Data >***. The *Databoard\<E extends Data\>* collection is a container of generic objects that extend the data type Data.
Intuitively, the collection acts as a space for storing and displaying data that can be of various types but that must implement the *display() method*.
The board must ensure data privacy by providing its own data sharing management mechanism. Every data on the board associated the data category.
The board owner can define their own categories and draw up a list of contacts (friends) to which the data will be visible for each category type.
The shared data is only visible in reading: in particular, the data can be viewed by friends but modified only by the board owner.
Friends can associate a "like" to the shared content.

Some of the board operation are described below.

    public interface DataBoard<Eextends Data>() {  
    
        //Create a data category if identity checks are respected    
        public void createCategory(String category, String passw);  
    
        //Removes a  data category if identity checks are respected  
        public void removeCategory(String category, String passw);  
        
        //Add a friend to a data category if identity checks are respected  
        public void addFriend(String category, String passw, String friend);  
        
        //Remove a friend from a data category if identity checks are respected 
        public void removeFriend(String category, String passw, String friend);  
        
        //Put a data on the board if the identity checks are respected
        public boolean put(String passw, <E extends Data> dato, String categoria);  
        
        //Returns a copy of the data on the board if identity checks are respected  
        public E get(String passw, <E extends Data> dato);  
        
        //Removes data from the board if identity checks are respected  
        public E remove(String passw, <E extends Data> dato);  
        
        //Create the list of data on the board of a given category if identity checks are respected
        public List<E extends Data> getDataCategory(String passw, String category);
        
        //Add a like to a given if identity checks are respected
        void insertLike(String friend, <E extends Data> data);
        
        //returns an iterator (without remove) that generates all the data on the board 
        //ordered with respect to the number of likes if identity checks are respected
        public Iterator<E>getIterator(String passw);
        
        //returns an iterator (without remove) that generates all the data in the shared board  
        public Iterator<E> getFriendIterator(String friend);
        
        // ... other operations to be defined at choice
    }
    
1. Define the complete specification and implementation of the data type Data, providing reasons for the choices made.

2. Define the full specification of the data type *Board\<E extends Data\>*, providing the reasons for the choices made.
    
3. Define the implementation of the data type *Board\<E extends Data\>*, providing at least two implementations that use different support structures. In both cases, however, both the abstraction function and the representation invariant must be described. The characteristics of the two implementations are discussed.
    
To evaluate the behavior of the proposed implementations, a battery of tests is realized that can operate, without specific changes, on both proposed implementations.
<hr><br>


## Versione Italiana
#### PROGRAMMAZIONE II - A.A. 2019–20 | Primo Progetto

**Obiettivo:** Il progetto ha l’obiettivo di applicare i concetti e le tecniche di programmazione Object-Oriented esaminate durante il corso. 

**Descrizione:** Si richiede di progettare, realizzare e documentare la collezione ***DataBoard\<E extends Data\>***. La collezione *DataBoard\<E extends  Data\>* è un  contenitore  di oggetti generici  che  estendono  il  tipo  di  dato  Data. 
Intuitivamente la collezione si comporta come uno spazio per la memorizzazione e visualizzazionidi dati che possono essere di vario tipo ma che implementano obbligatoriamente il metodo *display()*. 
La bacheca deve garantire la privacy dei dati fornendo un proprio meccanismo di gestione della condivisione dei dati. Ogni dato presente nella bachecaha associato la categoria del dato. 
Il proprietario della bacheca può definire le proprie categorie e stilare una lista di contatti (amici) a cui saranno visibili i dati per ogni tipologia di categoria. 
I dati condivisi sono visibili solamente in lettura: in particolare i dati possono essere visualizzati dagli amici ma modificati solamente dal proprietario della bacheca. 
Gli amici possono associare un “like” al contenuto condiviso. 

Alcune dell’operazione della bacheca sono definite descritte di seguito.

    public interface DataBoard<Eextends Data>() {  
    
        //Crea una categoria di dati se vengono rispettati i controlli di identità  
        public void createCategory(String category, String passw);  
    
        //Rimuove  una categoria di dati se vengono rispettati i controlli di identità
        public void removeCategory(String category, String passw);  
        
        //Aggiunge un amico ad una categoria di dati se vengono rispettati i controlli di identità
        public void addFriend(String category, String passw, String friend);  
        
        //Rimuove un amico da una categoria di dati se vengono rispettati i controlli di identità
        public void removeFriend(String category, String passw, String friend);  
        
        //Inserisce un dato in bacheca se vengono rispettati i controlli di identità
        public boolean put(String passw, <E extends Data> dato, String categoria);  
        
        //Restituisce una copia del dato in bacheca se vengono rispettati i controlli di identità
        public E get(String passw, <E extends Data> dato);  
        
        //Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità
        public E remove(String passw, <E extends Data> dato);  
        
        //Crea la lista dei dati in bacheca di una determinata categoria se vengono  
        //rispettati i controlli di identità
        public List<E extends Data> getDataCategory(String passw, String category);
        
        //Aggiunge un like a un dato se vengono rispettati i controlli di identità
        void insertLike(String friend, <E extends Data> data);
        
        //restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati  
        //rispetto al numero di like se vengono rispettati i controlli di identità
        public Iterator<E>getIterator(String passw);
        
        //restituisce un iteratore (senza remove) che genera tutti i dati in bacheca condivisi 
        public Iterator<E> getFriendIterator(String friend);
        
        // ... altre operazione da definire a scelta
    }
    
1. Si definisca la specifica completa e l’implementazionedel tipo di dato Data, fornendole motivazioni delle scelte effettuate.

2. Si definisca la specifica completa del tipo di dato *Board\<E extends Data\>*, fornendo le motivazioni delle scelte effettuate.

3. Si definisca l’implementazione del tipo di dato *Board\<E extends Data\>*, fornendo almeno due implementazioni che utilizzano differenti strutture di supporto. In entrambi i casi si devono comunque descrivere sia la funzione di astrazione sia l’invariante di rappresentazione. Si discutano le caratteristiche delle due implementazioni.

Per valutare il comportamento dell’implementazioni proposte si realizziuna batteria di test in grado di operare, senza modifiche specifiche, su entrambe le implementazioni proposte.
