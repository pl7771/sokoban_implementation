package util;
/**Java singleton class is a class that can have only 1 object(instance of the class) at a time
 * 1. make constructor as private
 * 2. write a public static methode (getInstance) that has return type of object of this singleton class
 * 
 * diff bw normal class and singleton class:
 * for normal class we use constructor whereas for singleton class we use getInstance() method for instantiation
 */

import java.util.ResourceBundle;
import java.util.Locale;

public class Taal {

    private ResourceBundle str;//singleton class variabele
    private static Taal ms;//object wordt automatisch geïnitialiseerd op null
    
    //Private constructor
    private Taal() {
    		
    	}
       
    public void setTaal(int r) {
    	
    	Locale l;
    	
        switch (r) {
            case 1:
                l = new Locale("nl", "BE");
                break;
            case 2:
                l = new Locale("en", "US");
                break;
            case 3:
                l = new Locale("fr", "FR");
                break;
            default:
            	l = new Locale("nl", "BE");
        }
        str = ResourceBundle.getBundle("util/Bundle", l);
    }

    
    public static Taal getInstance() {
    	
    	if(ms == null) {
    		ms = new Taal();
    		
    	}
    	return ms;
    	
    }
    
    //Methode vertaal is de belangrijkse methode
    //een sleutel wordt doorgegeven en de overeenkomstige zin
    //in de juiste taal wordt teruggegeven.
    
    public String vertaal(String sleutel) {
    	
    	return str.getString(sleutel);
    }

}