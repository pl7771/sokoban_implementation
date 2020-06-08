package domein;

import java.util.List;

import persistentie.SpelMapper;

	/**
	 * SpelRepository instantieert een nieuwe SpelMapper die de link legt met de databank
	 * alle communicatie van package domein naar package persistentie met betrekking tot een spel verloopt via deze SpelRepository
	 * 
	 * @author Gr86
	 */
public class SpelRepository {
	
	private SpelMapper mapper;
	
	/**
	 * Default constructor van SpelRepository
	 * instantieert een nieuwe SpelMapper mapper
	 */
	public SpelRepository() {
		mapper = new SpelMapper();
	}
	
	/**
	 * Geeft de namen van alle bestaande spellen als List
	 * @return namenSpelen
	 */
	public List<String> geefNamenBestaandeSpellen(){
		return mapper.geefNamenBestaandeSpellen();
	}
	
	/**
	 * Geeft het spel met de opgegeven naam
	 * @param naamSpel naam van het spel
	 * @return spel
	 */
	public Spel geefSpel(String naamSpel){
	    Spel spel = mapper.zoekSpel(naamSpel);
	    if(spel == null)
	    	throw new IllegalArgumentException("Spel bestaat niet");
	    return spel;
    }
	
	//UC5
	
	/**
	 * Gaat na of het spel reeds bestaat in de databank
	 * @param naamSpel naam van het spel
	 * @return boolean
	 */
	public boolean bestaatSpel (String naamSpel) {
		return mapper.zoekSpel(naamSpel) != null; 
	}
	
	/**
	 * Voegt een spel toe aan de databank met zijn opgegeven speler
	 * @param spel het spel
	 * @param speler de speler
	 */
    public void voegToe(Spel spel, Speler speler) {
    	   mapper.voegToe(spel, speler);
    }
	
	
}