package domein;

import exceptions.FoutWachtwoordException;
import exceptions.SpelerNietGekendException;
import persistentie.SpelerMapper;
//import java.util.ResourceBundle;

/**
 * SpelerRepository instantieert een nieuwe SpelerMapper die de link legt met de databank
 * alle communicatie van package domein naar package persistentie met betrekking tot een speler verloopt via deze SpelerRepository
 * 
 * @author Gr86
 */
public class SpelerRepository {
	
	private SpelerMapper mapper;
	
	/**
	 * Default constructor van SpelerRepository
	 * instantieert een nieuwe SpelerMapper mapper
	 */
	public SpelerRepository() {
        mapper = new SpelerMapper();
    }
	
	/**
	 * Slaat een nieuwe speler op indien deze nog niet gekend is
	 * @param speler de speler
	 */
    public void voegToe(Speler speler) {
    	if(bestaatSpeler(speler.getGebruikersnaam())) throw new IllegalArgumentException("speler reeds gekend");
    	   mapper.voegToe(speler);
    }

    private boolean bestaatSpeler(String gebruikersnaam){
    	return mapper.zoekSpeler(gebruikersnaam) != null;
    }
    
    /**
     * Geeft de speler terug met de opgegeven gebruikersnaam en wachtwoord
     * indien het ingegeven wachtwoord overeenstemt met het wachtwoord die reeds opgeslagen is in de databank
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param wachtwoord wachtwoord van de speler
     * @return speler
     */
    public Speler geefSpeler(String gebruikersnaam, String wachtwoord){
    	
       Speler speler = mapper.zoekSpeler(gebruikersnaam);
       if(speler == null)
    	   throw new SpelerNietGekendException();
       if(!speler.getWachtwoord().equals(wachtwoord))
    	   throw new FoutWachtwoordException(); 
       return speler;
    }
}