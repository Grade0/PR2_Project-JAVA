package com.unipi.project1;
import java.util.regex.*;

import com.unipi.project1.Exceptions.*;

public class DataVerifier {
       /**
        * REQUIRES: user != null,
        *           0 < user.length < 50
        * MODIFIES: /
        * THROWS: NullPointerException (unchecked) se user = null
        *         InvalidDataException (checked) se user.length <= 0 || user.length > 50
        * EFFECTS: verifica se l'utente user è un valore alfanumerico non più lungo di 50 caratteri
        * e inizia con un carattere o cifra
       */
       public static void verifyUser(String user) throws InvalidDataException {
           if(user == null) throw new NullPointerException();
           if(user.length() == 0) throw new InvalidDataException("Username field is empty");
           if(user.length() > 50) throw new InvalidDataException("Username field is greater than 50 chars");
           /* Check alphanumeric regex */
           if(! Pattern.compile("[\\w\\d]+[\\w\\d-_]*").matcher(user).matches())
               throw new InvalidDataException("Invalid Username field");
       }

       /**
        * REQUIRES: text != null,
        *           0 < text.length < 128,
        * MODIFIES: /
        * THROWS: NullPointerException (unchecked) se text = null
        *         InvalidDataException (checked) se text.length <= 0 || text.length > 50
        * EFFECTS: verifica se la lunghezza del testo è inferiore o uguale a 128 caratteri e non vuota
        */
       public static void verifyText(String text) throws InvalidDataException {
           if(text == null) throw new NullPointerException();
           if(text.length() == 0)  throw new InvalidDataException("Text field is empty");
           if(text.length() > 128) throw new InvalidDataException("Text field is greater than 128 chars");
       }

       /**
        * REQUIRES: psswd != null,
        *           0 < psswd.length < 50,
        * MODIFIES: /
        * THROWS: NullPointerException (unchecked) se psswd = null
        *         InvalidDataException (checked) se psswd.length <= 0 || psswd.length > 50
        * EFFECTS: verifica se la lunghezza della password psswd è inferiore o uguale a 50 caratteri e non vuota
       */
       public static void verifyPassw(String psswd) throws InvalidDataException {
           if(psswd == null) throw new NullPointerException();
           if(psswd.length() == 0)  throw new InvalidDataException("Password field is empty");
           if(psswd.length() > 50) throw new InvalidDataException("Password field is greater than 50 chars");
       }

       /**
        * REQUIRES: category != null,
        *           0 < category.length < 50,
        * MODIFIES: /
        * THROWS: NullPointerException (unchecked) se category = null
        *         InvalidDataException (checked) se category.length <= 0 || category.length > 50
        * EFFECTS: verifica se la categoria è un valore alfanumerico non superiore a 50 caratteri
        */
       public static void verifyCategory(String cat) throws InvalidDataException {
           if(cat == null) throw new NullPointerException();
           if(cat.length() == 0) throw new InvalidDataException("Category field is empty");
           if(cat.length() > 50) throw new InvalidDataException("Category field is greater than 128 chars");
           /* Check alphanumeric regex */
           if(! Pattern.compile("[\\w\\d]+[\\w\\d-_]*").matcher(cat).matches())
               throw new InvalidDataException("Invalid Category field");
       }

}
