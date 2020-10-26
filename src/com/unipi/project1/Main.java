package com.unipi.project1;

import java.util.Iterator;

import com.unipi.project1.Exceptions.*;

public class Main {

    public static void main(String[] args) {
        Main.test1();
        Main.test2();
    }

    public static void test1() {
        try {
            System.out.println("\n===> Batteria di test di esempio -- IMPLEMENTAZIONE 1 <===");

            // Costanti
            String USER_INVALID = "--asdgUTU";
            String TEXT_INVALID = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            String PW_ALICE = "passwordbella";
            String PW_BOB = "password2";
            String PW_CIRO = "abcabc";
            String PW_DONATELLA = "qwerty";+
            String PW_INVALID   = TEXT_INVALID;

            // variabili locali
            Board<Data> temp_board;
            Board<Data> board_alice = new Board<Data>("alice", PW_ALICE);
            Board<Data> board_bob = new Board<Data>("bob", PW_BOB);
            Board<Data> board_ciro = new Board<Data>("ciro", PW_CIRO);
            Board<Data> board_donatella = new Board<Data>("donatella", PW_DONATELLA);
            Data element_tolike;
            Data element_tolike2;

            /* ===========================
                TEST BOARDS
               =========================== */
            System.out.println("\n===> AGGIUNGO BOARD CON UTENTI DI TEST... <===");
            try {
                System.out.println("\n===> Inserisco un username invalido. Mi aspetto InvalidDataException");
                temp_board = new Board<Data>(USER_INVALID, "INVALID");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco un username vuoto. Mi aspetto InvalidDataException");
                temp_board = new Board<Data>("", "INVALID");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco un username troppo lungo. Mi aspetto InvalidDataException");
                temp_board = new Board<Data>(PW_INVALID, "INVALID");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            try {
                System.out.println("\n===> Inserisco una password invalida. Mi aspetto InvalidDataException");
                temp_board = new Board<Data>("INVALID", PW_INVALID);
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco una password vuota. Mi aspetto InvalidDataException");
                temp_board = new Board<Data>("INVALID", "");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            /* ===================================================
                TEST CATEGORIES
               ================================================= */
            System.out.println("\n\n===> AGGIUNGO CATEGORIE ALLE BOARD <===");
            board_alice.createCategory("main",    PW_ALICE);
            board_alice.createCategory("cani",    PW_ALICE);
            board_alice.createCategory("gatti",   PW_ALICE);
            board_bob.createCategory("main",    PW_BOB);
            board_bob.createCategory("cani",    PW_BOB);
            board_bob.createCategory("gatti",   PW_BOB);
            board_ciro.createCategory("main",     PW_CIRO);
            board_ciro.createCategory("cani",     PW_CIRO);
            board_ciro.createCategory("gatti",    PW_CIRO);
            board_donatella.createCategory("main",       PW_DONATELLA);
            board_donatella.createCategory("cani",       PW_DONATELLA);
            board_donatella.createCategory("gatti",      PW_DONATELLA);
            board_donatella.createCategory("remove",     PW_DONATELLA);
            board_donatella.removeCategory("remove",     PW_DONATELLA);
            try {
                System.out.println("\n===> Inserisco una categoria già esistente. Mi aspetto DuplicateDataException");
                board_donatella.createCategory("gatti",  PW_DONATELLA);
            } catch(DuplicateDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Rimuovo una categoria non esistente. Mi aspetto DataNotFoundException");
                board_alice.removeCategory("nonesisto",  PW_ALICE);
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco una password errata. Mi aspetto UnauthorizedLoginException");
                board_alice.createCategory("invalid", PW_BOB);
            } catch(UnauthorizedLoginException e) {
                System.out.println(e);
            }  catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            /* ===================================================
                TEST POSTS
               ================================================= */
            System.out.println("\n\n===> AGGIUNGO POST ALLA BACHECA DI ALICE <===");
            Data temp_element = new Data("alice", "hello world");
            System.out.println(temp_element.display());
            board_alice.put(PW_ALICE, temp_element, "main");
            // Utilizzato nel test dei like
            element_tolike = temp_element;
            element_tolike.setCategory("main");
            temp_element = new Data("alice", "hello gatto");
            System.out.println(temp_element.display());
            board_alice.put(PW_ALICE, temp_element, "gatti");
            temp_element = new Data("alice", "hello cane");
            System.out.println(temp_element.display());
            board_alice.put(PW_ALICE, temp_element, "cani");

            System.out.println("\n===> Inserisco un post duplicato in due categorie diverse");
            temp_element = new Data("alice", "duplicato 1");
            System.out.println(temp_element.display());
            board_alice.put(PW_ALICE, temp_element, "main");
            board_alice.put(PW_ALICE, temp_element, "gatti");

            System.out.println("\n===> Recupero di un post dalla board");
            temp_element = board_alice.get(PW_ALICE, temp_element);
            System.out.println(temp_element.display());

            System.out.println("\n===> Rimuovo l'ultimo post dalla board");
            temp_element = board_alice.remove(PW_ALICE, temp_element);
            try {
                System.out.println("\n===> Provo ad accedere al post rimosso. Mi aspetto DataNotFoundException");
                board_alice.get(PW_ALICE, temp_element);
                System.out.println(temp_element.display());
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            try {
                System.out.println("\n===> Inserisco un post con testo errato. Mi aspetto InvalidDataException");
                temp_element = new Data("alice", TEXT_INVALID);
                board_alice.put(PW_ALICE, temp_element, "main");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco un post con autore diverso dall'owner della board. Mi aspetto InvalidDataException");
                temp_element = new Data("bob", "non sono alessandro");
                board_alice.put(PW_ALICE, temp_element, "main");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco un post in una categoria non esistente. Mi aspetto DataNotFoundException");
                temp_element = new Data("alice", "categoria errata");
                board_alice.put(PW_ALICE, temp_element, "nonesisto");
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco un post duplicato. Mi aspetto DuplicateDataException");
                temp_element = new Data("alice", "duplicato 2");
                board_alice.put(PW_ALICE, temp_element, "main");
                board_alice.put(PW_ALICE, temp_element, "main");
            } catch(DuplicateDataException e) {
                System.out.println(e);
            }  catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            element_tolike2 = temp_element;
            element_tolike2.setCategory("main");

            try {
                System.out.println("\n===> Recupero un post non esistente dalla board. Mi aspetto DataNotFoundException");
                temp_element = new Data("alice", "nonesistoperniente");
                board_alice.get(PW_ALICE, temp_element);
                System.out.println(temp_element.display());
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Rimuovo un post non esistente dalla board. Mi aspetto DataNotFoundException");
                temp_element = new Data("alice", "nonesistoperniente");
                board_alice.get(PW_ALICE, temp_element);
                System.out.println(temp_element.display());
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            /* ===================================================
                TEST FRIENDS
               ================================================= */

            System.out.println("\n\n===> AGGIUNGO AMICI ALLA BACHECA DI ALICE <===");
            board_alice.addFriend("main", PW_ALICE, "bob");
            board_alice.addFriend("main", PW_ALICE, "donatella");
            board_alice.addFriend("main", PW_ALICE, "ciro");
            board_alice.removeFriend("main", PW_ALICE, "ciro");

            try {
                System.out.println("\n===> Rimuovo un amico non esistente dalla categoria. Mi aspetto DataNotFoundException");
                board_alice.removeFriend("main", PW_ALICE, "ciro");
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            try {
                System.out.println("\n===> Aggiungo un amico ad una categoria non esistente. Mi aspetto DataNotFoundException");
                board_alice.addFriend("nonesisto", PW_ALICE, "bob");
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            try {
                System.out.println("\n===> Aggiungo un amico duplicato alla categoria. Mi aspetto DuplicateDataException");
                board_alice.addFriend("main", PW_ALICE, "bob");
            } catch(DuplicateDataException e) {
                System.out.println(e);
            }  catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            /* ===================================================
                TEST LIKES
               ================================================= */

            System.out.println("\n\n===> AGGIUNGO LIKE A POST NELLA BACHECA \"main\" DI ALICE <===");
            System.out.println("Il post a cui metto like è\n" + element_tolike.display());
            board_alice.insertLike("bob", element_tolike);
            element_tolike.addLike("bob");
            board_alice.insertLike("donatella", element_tolike);
            element_tolike.addLike("donatella");
            board_alice.insertLike("bob", element_tolike2);

            System.out.println(element_tolike.display());

            try {
                System.out.println("\n===> Aggiungo un like duplicato al post. Mi aspetto DuplicateDataException");
                board_alice.insertLike("bob", element_tolike);
            } catch(DuplicateDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            try {
                System.out.println("\n===> Aggiungo un like da un utente non presente negli amici della categoria. Mi aspetto DataNotFoundException");
                board_alice.insertLike("ciro", element_tolike);
            } catch(DataNotFoundException e) {
                System.out.println(e);
            }  catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            /* ===================================================
                TEST ITERATORS
               ================================================= */

            System.out.println("\n\n===> TEST DELL'ITERATORE DELLA BACHECA DI ALICE <===");
            Iterator<Data> itr = board_alice.getIterator(PW_ALICE);
            while(itr.hasNext()) {
                Data el = itr.next();
                System.out.println(el.display());
            }

            System.out.println("\n===> Test dell'iteratore friends della bacheca di alice");
            itr = board_alice.getFriendIterator("bob");
            while(itr.hasNext()) {
                Data el = itr.next();
                System.out.println(el.display());
            }

            System.out.println("\n===> Test dell'iteratore friends di un amico non presente negli amici della bacheca di alice\nUn risultato vuoto è corretto");
            itr = board_alice.getFriendIterator("ciro");
            while(itr.hasNext()) {
                Data el = itr.next();
                System.out.println(el.display());
            }

        } catch (Exception e) {
            System.err.println("FATAL ERROR - THIS SHOULD NOT HAVE HAPPENED:\n" + e);
        }
    }

    public static void test2() {
        try {
            System.out.println("\n\n\n\n===> Batteria di test di esempio -- IMPLEMENTAZIONE 2 <===");

            // Costanti
            String USER_INVALID = "--asdgUTU";
            String TEXT_INVALID = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            String PW_ALICE = "passwordbella";
            String PW_BOB = "password2";
            String PW_CIRO = "abcabc";
            String PW_DONATELLA = "qwerty";
            String PW_INVALID = TEXT_INVALID;

            // variabili locali
            Board2<Data> temp_board;
            Board2<Data> board_alice =     new Board2<Data>("alice", PW_ALICE);
            Board2<Data> board_bob = new Board2<Data>("bob", PW_BOB);
            Board2<Data> board_ciro =  new Board2<Data>("ciro", PW_CIRO);
            Board2<Data> board_donatella =    new Board2<Data>("donatella", PW_DONATELLA);
            Data temp_element;
            Data element_tolike;
            Data element_tolike2;

            /* ===========================
                TEST BOARDS
               =========================== */
            System.out.println("\n===> AGGIUNGO BOARD CON UTENTI DI TEST... <===");
            try {
                System.out.println("\n===> Inserisco un username invalido. Mi aspetto InvalidDataException");
                temp_board = new Board2<Data>(USER_INVALID, "INVALID");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco un username vuoto. Mi aspetto InvalidDataException");
                temp_board = new Board2<Data>("", "INVALID");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco un username troppo lungo. Mi aspetto InvalidDataException");
                temp_board = new Board2<Data>(PW_INVALID, "INVALID");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            try {
                System.out.println("\n===> Inserisco una password invalida. Mi aspetto InvalidDataException");
                temp_board = new Board2<Data>("INVALID", PW_INVALID);
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco una password vuota. Mi aspetto InvalidDataException");
                temp_board = new Board2<Data>("INVALID", "");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            /* ===================================================
                TEST CATEGORIES
               ================================================= */
            System.out.println("\n\n===> AGGIUNGO CATEGORIE ALLE BOARD <===");
            board_alice.createCategory("main",    PW_ALICE);
            board_alice.createCategory("cani",    PW_ALICE);
            board_alice.createCategory("gatti",       PW_ALICE);
            board_bob.createCategory("main",    PW_BOB);
            board_bob.createCategory("cani",    PW_BOB);
            board_bob.createCategory("gatti",   PW_BOB);
            board_ciro.createCategory("main",     PW_CIRO);
            board_ciro.createCategory("cani",     PW_CIRO);
            board_ciro.createCategory("gatti",    PW_CIRO);
            board_donatella.createCategory("main",       PW_DONATELLA);
            board_donatella.createCategory("cani",       PW_DONATELLA);
            board_donatella.createCategory("gatti",      PW_DONATELLA);
            board_donatella.createCategory("remove",     PW_DONATELLA);
            board_donatella.removeCategory("remove",     PW_DONATELLA);
            try {
                System.out.println("\n===> Inserisco una categoria già esistente. Mi aspetto DuplicateDataException");
                board_donatella.createCategory("gatti",  PW_DONATELLA);
            } catch(DuplicateDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Rimuovo una categoria non esistente. Mi aspetto DataNotFoundException");
                board_alice.removeCategory("nonesisto",  PW_ALICE);
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco una password errata. Mi aspetto UnauthorizedLoginException");
                board_alice.createCategory("invalid", PW_BOB);
            } catch(UnauthorizedLoginException e) {
                System.out.println(e);
            }  catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            /* ===================================================
                TEST POSTS
               ================================================= */
            System.out.println("\n\n===> AGGIUNGO POST ALLA BACHECA DI ALICE");
            temp_element = new Data("alice", "hello world");
            System.out.println(temp_element.display());
            board_alice.put(PW_ALICE, temp_element, "main");
            // Utilizzato nel test dei like
            element_tolike = temp_element;
            element_tolike.setCategory("main");
            temp_element = new Data("alice", "hello gatto");
            System.out.println(temp_element.display());
            board_alice.put(PW_ALICE, temp_element, "gatti");
            temp_element = new Data("alice", "hello cane");
            System.out.println(temp_element.display());
            board_alice.put(PW_ALICE, temp_element, "cani");
            System.out.println("\n===> Inserisco un post duplicato in due categorie diverse");
            temp_element = new Data("alice", "duplicato 1");
            System.out.println(temp_element.display());
            board_alice.put(PW_ALICE, temp_element, "main");
            board_alice.put(PW_ALICE, temp_element, "gatti");
            System.out.println("\n===> Recupero di un post dalla board");
            temp_element = board_alice.get(PW_ALICE, temp_element);
            System.out.println(temp_element.display());
            System.out.println("\n===> Rimuovo l'ultimo post dalla board");
            temp_element = board_alice.remove(PW_ALICE, temp_element);
            try {
                System.out.println("\n===> Provo ad accedere al post rimosso. Mi aspetto DataNotFoundException");
                board_alice.get(PW_ALICE, temp_element);
                System.out.println(temp_element.display());
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            try {
                System.out.println("\n===> Inserisco un post con testo errato. Mi aspetto InvalidDataException");
                temp_element = new Data("alice", TEXT_INVALID);
                board_alice.put(PW_ALICE, temp_element, "main");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco un post con autore diverso dall'owner della board. Mi aspetto InvalidDataException");
                temp_element = new Data("bob", "non sono alessandro");
                board_alice.put(PW_ALICE, temp_element, "main");
            } catch(InvalidDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco un post in una categoria non esistente. Mi aspetto DataNotFoundException");
                temp_element = new Data("alice", "categoria errata");
                board_alice.put(PW_ALICE, temp_element, "nonesisto");
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Inserisco un post duplicato. Mi aspetto DuplicateDataException");
                temp_element = new Data("alice", "duplicato 2");
                board_alice.put(PW_ALICE, temp_element, "main");
                board_alice.put(PW_ALICE, temp_element, "main");
            } catch(DuplicateDataException e) {
                System.out.println(e);
            }  catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            element_tolike2 = temp_element;
            element_tolike2.setCategory("main");

            try {
                System.out.println("\n===> Recupero un post non esistente dalla board. Mi aspetto DataNotFoundException");
                temp_element = new Data("alice", "nonesistoperniente");
                board_alice.get(PW_ALICE, temp_element);
                System.out.println(temp_element.display());
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }
            try {
                System.out.println("\n===> Rimuovo un post non esistente dalla board. Mi aspetto DataNotFoundException");
                temp_element = new Data("alice", "nonesistoperniente");
                board_alice.get(PW_ALICE, temp_element);
                System.out.println(temp_element.display());
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            /* ===================================================
                TEST FRIENDS
               ================================================= */

            System.out.println("\n\n===> AGGIUNGO AMICI ALLA BACHECA DI ALICE <===");
            board_alice.addFriend("main", PW_ALICE, "bob");
            board_alice.addFriend("main", PW_ALICE, "donatella");
            board_alice.addFriend("main", PW_ALICE, "ciro");
            board_alice.removeFriend("main", PW_ALICE, "ciro");

            try {
                System.out.println("\n===> Rimuovo un amico non esistente dalla categoria. Mi aspetto DataNotFoundException");
                board_alice.removeFriend("main", PW_ALICE, "ciro");
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            try {
                System.out.println("\n===> Aggiungo un amico ad una categoria non esistente. Mi aspetto DataNotFoundException");
                board_alice.addFriend("nonesisto", PW_ALICE, "bob");
            } catch(DataNotFoundException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            try {
                System.out.println("\n===> Aggiungo un amico duplicato alla categoria. Mi aspetto DuplicateDataException");
                board_alice.addFriend("main", PW_ALICE, "bob");
            } catch(DuplicateDataException e) {
                System.out.println(e);
            }  catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            /* ===================================================
                TEST LIKES
               ================================================= */

            System.out.println("\n\n===> AGGIUNGO LIKE A POST NELLA BACHECA \"main\" DI ALICE");
            System.out.println("Il post a cui metto like è\n" + element_tolike.display());
            board_alice.insertLike("bob", element_tolike);
            element_tolike.addLike("bob");
            board_alice.insertLike("donatella", element_tolike);
            element_tolike.addLike("donatella");
            board_alice.insertLike("bob", element_tolike2);

            System.out.println(element_tolike.display());

            try {
                System.out.println("\n===> Aggiungo un like duplicato al post. Mi aspetto DuplicateDataException");
                board_alice.insertLike("bob", element_tolike);
            } catch(DuplicateDataException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            try {
                System.out.println("\n===> Aggiungo un like da un utente non presente negli amici della categoria. Mi aspetto DataNotFoundException");
                board_alice.insertLike("ciro", element_tolike);
            } catch(DataNotFoundException e) {
                System.out.println(e);
            }  catch (Exception e) {
                System.err.println("FAIL:\n" + e);
            }

            /* ===================================================
                TEST ITERATORS
               ================================================= */

            System.out.println("\n\n===> TEST DELL'ITERATORE DELLA BACHECA DI ALICE");
            Iterator<Data> itr = board_alice.getIterator(PW_ALICE);
            while(itr.hasNext()) {
                Data el = itr.next();
                System.out.println(el.display());
             }

            System.out.println("\n===> Test dell'iteratore friends della bacheca di alice");
            itr = board_alice.getFriendIterator("bob");
            while(itr.hasNext()) {
                Data el = itr.next();
                System.out.println(el.display());
            }

            System.out.println("\n===> Test dell'iteratore friends di un amico non presente negli amici della bacheca di alice\nUn risultato vuoto è corretto");
            itr = board_alice.getFriendIterator("ciro");
            while(itr.hasNext()) {
                Data el = itr.next();
                System.out.println(el.display());
            }

        } catch (Exception e) {
            System.err.println("FATAL ERROR 2 - THIS SHOULD NOT HAVE HAPPENED:\n" + e);
        }
    }
}
