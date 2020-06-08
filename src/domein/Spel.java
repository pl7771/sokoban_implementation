package domein;
import java.util.ArrayList;

import java.util.List;
/**
 * Spel bevat de attributen en het kenmerkend gedrag van een spel
 * 
 * @author Gr86
 */
public class Spel {
	
		//ATTRIBUTEN
		private String naamSpel;
		private List<String> lijstVanSpelborden;
		private int spelbordTeller = 0;
		private int aantalVoltooideSpelborden = 0;
		private Spelbord huidigSpelbord;
		
		//CONSTRUCTOR
		/**
		 * Constructor van Spel met parameters String naamSpel en List lijstVanSpelborden
		 * @param naamSpel naam van het spel als String
		 * @param lijstVanSpelborden List met alle spelborden als String
		 */
		public Spel (String naamSpel, List<String> lijstVanSpelborden) {
			setNaamSpel(naamSpel);
			setLijstVanSpelborden(lijstVanSpelborden);
			 if (!lijstVanSpelborden.isEmpty()) 
                 huidigSpelbord = new Spelbord(lijstVanSpelborden.get(spelbordTeller));                 
		}
		
		/**
		 * Constructor van Spel met parameters String naamSpel 
		 * @param naamSpel naam van het spel als String
		 */
		public Spel(String naamSpel) {
			 this(naamSpel, new ArrayList<>());
		}
		
		
		//SETTERS & GETTERS
		
		/**
		 * Setter voor naamSpel
		 * @param naamSpel naam van het spel als String
		 */
		public void setNaamSpel(String naamSpel) {
			this.naamSpel = naamSpel;
		}
		
		/**
		 * Getter voor naamSpel
		 * @return naam van het spel als String
		 */
		public String getNaamSpel() {
			return naamSpel;
		}
		
		/**
		 * Getter voor lijstVanSpelborden
		 * @return lijstVanSpelborden als List
		 */
		public List<String> getLijstVanSpelborden() {
			return lijstVanSpelborden;
		}
		
		/**
		 * Setter voor lijstVanSpelborden
		 * @param lijstVanSpelborden als List
		 */
		public void setLijstVanSpelborden(List<String> lijstVanSpelborden) {
			this.lijstVanSpelborden = lijstVanSpelborden;
		}

		// UC3 geefAantalVoltooideSpellen en geefTotaalAantalSpelborden
		
		/**
		 * Geeft het aantal voltooide spelborden in het huidige spel als int
		 * @return int aantalVoltooideSpelborden
		 */
		public int geefAantalVoltooideSpellen() {
			return aantalVoltooideSpelborden;
		}
		
		/**
		 * Geeft het aantal spelborden van het huidige spel als int
		 * @return lijstVanSpelborden.size()
		 */
		public int geefAantalSpelborden() {
				return lijstVanSpelborden.size();
		}
		
		/**
		 * Geeft het spelbord als een tweedimensionele array van type char
		 * @return char[][] spelbord
		 */
		public char[][] geefSpelbord() {
			char[][] spelbord = new char[SpelInt.rijen][SpelInt.colommen];
			for (int i = 0; i < huidigSpelbord.getVelden().length; i++) {
				for (int j = 0; j < huidigSpelbord.getVelden().length; j++) {
					spelbord[i][j] = huidigSpelbord.getVelden()[i][j].getVeldType().symbol.charAt(0);
				}
			}
			return spelbord; 
		}

			

		/**
		 * Geeft aan of het einde van het spel is bereikt
		 * @return boolean
		 */
		public boolean isEindeSpel() {
			return spelbordTeller == lijstVanSpelborden.size();
		}
		
		// UC 4
		
		/**
		 * Verplaatst de werkman op het spelbord volgens de opgegeven richting 
		 * @param richting waarin men wenst te bewegen (LINKS, RECHTS, BOVEN, BENEDEN, SLUITEN)
		 */
		public void verplaatsWerkman(Richting richting) {
			huidigSpelbord.verplaatsWerkman(richting);
		}
		
		/**
		 * Gaat na of het einde van het spelbord bereikt is
		 * Indien het spelbord is voltooid verhoogt de spelbordTeller++
		 * Instantieert een nieuw Spelbord
		 * Verhoogt aantalVoltooideSpelborden++
		 * @return boolean isSpelbordVoltooid
		 */
		public boolean isSpelbordVoltooid() {
			if(huidigSpelbord.isSpelbordVoltooid()) {
				spelbordTeller++;
				huidigSpelbord = new Spelbord(lijstVanSpelborden.get(spelbordTeller));
				aantalVoltooideSpelborden++;
				return true;
			}
			return false;	
		}
		
		/**
		 * Geeft het aantal verplaatsingen binnen het huidige spelbord van het huidige spel
		 * @return aantalVerplaatsingen
		 */
		public int geefAantalVerplaatsingen() {
			return huidigSpelbord.getAantalVerplaatsingen();
		}
		
		
		//UC 6 - naamgeving komt nog niet overeen met diagramma
		
		/**
		 * maakt een nieuwe instantie van spelbord aan opgevuld met 10x10 velden van VeldType LEEGVELD
		 */
		public void maakLeegSpelbordAan() {
			huidigSpelbord = new Spelbord();
		}
		
		/**
		 * Wijzigt een veld van het spelbord op index van velden[i][j] volgens het opgegeven VeldType
		 * @param i int die refereert naar de positie op de x-as
		 * @param j int die refereert naar de positie op de y-as
		 * @param type VeldType van de gekozen indexen
		 */
		public void wijzigSpelbord(int i, int j, VeldType type) {
			huidigSpelbord.wijzigSpelbord(i, j, type);
		}
		
		/**
		 * Controleert of een nieuw gemaakt spelbord voldoet aan alle voorwaarden
		 * @return boolean
		 */
		public boolean valideerSpelbord() {
			return huidigSpelbord.valideerSpelBord();
		}
		
		/**
		 * Slaat het nieuw gemaakt spelbord op als String door dit toe te voegen aan lijstVanSpelborden
		 */
		public void registreerSpelbord() {
			lijstVanSpelborden.add(huidigSpelbord.toString());
		}
		
		/**
		 * Geeft het spelbord terug in zijn originele toestand voordat verplaatsingen werden gemaakt, door een nieuwe instantie van Spelbord te creëeren met hetzelfde telnummer
		 */
		public void resetSpelbord() {
			huidigSpelbord = new Spelbord(lijstVanSpelborden.get(spelbordTeller));
		}
		
		
}
