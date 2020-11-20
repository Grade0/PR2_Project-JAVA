# PR2_Project-JAVA
"Programming 2" Class Project - University of Pisa

### PROGRAMMAZIONE II - A.A. 2019–20  
#### Primo Progetto-sessione autunnale

Il progetto ha l’obiettivo di applicare i concetti e le tecniche di programmazione Object-Oriented esaminate durante il corso. 

Si richiede di progettare, realizzare e documentare la collezione ***DataBoard\<E extends Data\>***. La collezione *DataBoard\<E extends  Data\>* è un  contenitore  di oggetti generici  che  estendono  il  tipo  di  dato  Data. 
Intuitivamente lacollezione si comporta come unospazioper la memorizzazione e visualizzazionidi dati che possono essere di vario tipo  ma che implementano obbligatoriamente il metodo *display()*. 
La bachecadeve garantire la privacy dei dati fornendo un proprio meccanismo di gestione della condivisione dei dati. Ogni dato presente nella bachecaha associato la categoria del dato. 
Il proprietario della bacheca può definire le proprie categorie e stilare una lista di contatti (amici) a cui saranno visibili i dati per ogni tipologia di categoria. 
I dati condivisi sono visibili solamente in lettura: inparticolare i dati possono essere visualizzati dagli amici ma modificati solamente dal proprietario della bacheca. 
Gli amici possono associare un “like” al contenuto condiviso. 

Alcune dell’operazione della bachecasono definite descritte di seguito.

    public interface DataBoard<Eextends Data>() {  
    
        //Crea una categoria di dati se vengono rispettati i controlli di identità  
        public void createCategory(String category, String passw);  
    
        //Rimuove  una categoria di dati se vengono rispettati i controlli di identità
        public void removeCategory(String category, String passw);  
        
        //Aggiunge un amico ad una categoria di dati se vengono rispettati i controlli di identità
        public void addFriend(String category, String passw, String friend);  
        
        //Rimuove un amico dauna categoria di dati se vengono rispettati i controlli di identità
        public void removeFriend(String category, String passw, String friend);  
        
        //Inserisce un dato inbacheca se vengono rispettati i controlli di identità
        public boolean put(String passw, <E extends Data> dato, String categoria);  
        
        //Restituisce una copia del dato in bacheca se vengono rispettati i controlli di identità
        public E get(String passw, <E extends Data> dato);  
        
        //Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità
        public E remove(String passw, <E extends Data> dato);  
        
        //Crea la lista dei datiin bacheca di una determinata categoria se vengono  
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
    
1. Si definiscala specifica completa e l’implementazionedel tipo di dato Data, fornendole motivazionidelle scelte effettuate.

2. Si definisca la specifica completa del tipo di dato Board<E extends Data>, fornendole motivazionidelle scelte effettuate.

3. Si  definiscal’implementazione  del  tipo  didatoBoard<E  extends  Data>,fornendoalmeno due implementazioni  che  utilizzano  differenti  strutture  di  supporto. In  entrambi  i  casi  si devono comunque descrivere sia la funzione di astrazione sia l’invariante di rappresentazione. 
Si discutano le caratteristiche delle due implementazioni.

Per valutare  il  comportamento  dell’implementazionp roposte si realizziuna batteria di test in grado di operare, senza modifiche specifiche, su entrambe le implementazioni proposte.
