package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.SpelBestaatException;


/**
 * DomeinController bevat alle methoden die aangeroepen kunnen worden vanuit de
 * user interface.
 *
 *@author Gr86
 */


public class DomeinController
{
	
	private final SpelerRepository spelerRepository;
	private final SpelRepository spelRepository;
	private Speler speler;
	private Spel spel;
	
	
	
	 /**
     * DomeinController initialiseert een nieuw object van de klassen
     * SpelerRepository, SpelRepository, SpelbordRepository en Taal.
     */
	public DomeinController() {
		spelerRepository = new SpelerRepository();
		spelRepository = new SpelRepository();
	}
	


	// UC 1 
	/**
	 * Meld de speler aan met de opgegeven gebruikersnaam en wachtwoord
	 * @param gebruikersnaam gebruikersnaam van de speler
	 * @param wachtwoord wachtwoord van de speler
	 */
	public void meldAan(String gebruikersnaam, String wachtwoord) {
			speler = spelerRepository.geefSpeler(gebruikersnaam, wachtwoord);
			speler.resetPassword();
	}
	
	/**
	 * Geeft de gebruikersnaam en adminrechten van de speler
	 * @return een array van String met String gebruikersnaam en String adminrechten
	 */
	public String[] geefSpeler() {
		return new String[] {
				speler.getGebruikersnaam(), String.valueOf(speler.getAdminrechten())
		};
	}
	
	
	// UC 2
	
	/**
	 * Registreert een nieuwe speler volgens de opgegeven gebruikersnaam, wachtwoord, naam en voornaam
	 * @param gebruikersnaam gebruikersnaam van de nieuwe speler
	 * @param wachtwoord wachtwoord van de nieuwe speler
	 * @param naam naam van de nieuwe speler
	 * @param voornaam voornaam van de nieuwe speler
	 */
	public void registreer(String gebruikersnaam, String wachtwoord, String naam, String voornaam) {
		speler = new Speler(gebruikersnaam, wachtwoord, naam, voornaam);
		spelerRepository.voegToe(speler);
		speler.resetPassword();
	}
	
	
	// UC 3
	/**
	 * Geeft het aantal spelborden in het spel
	 * @return aantal spelborden van spel
	 */
	public int geefAantalSpelborden() { //OOK IN UC5 GEBRUIKT
		return spel.geefAantalSpelborden();
	}
	
	/**
	 * Geeft het aantal voltooide spelborden in het spel
	 * @return aantal voltooide spelborden van spel
	 */
	public int geefAantalVoltooideSpelborden() {
		return spel.geefAantalVoltooideSpellen();
	}
	
	/**
	 * Geeft de namen van alle bestaande spellen
	 * @return een List van bestaande spellen
	 */
	public List<String> geefNamenBestaandeSpellen(){
		return spelRepository.geefNamenBestaandeSpellen();
	}
	
	/**
	 * Kiest een spel volgens zijn opgegeven naam
	 * @param naamSpel naam van het spel
	 */
	public void kiesSpel(String naamSpel) {
		spel = spelRepository.geefSpel(naamSpel);
	}
	
	/**
	 * Gaat na of het einde van het spel bereikt is
	 * @return boolean isEindeSpel
	 */
	public boolean isEindeSpel() {
		return spel.isEindeSpel();
	}
	
	/**
	 * Geeft het spelbord in een tweedimensionele array van type char
	 * @return char[][] spelbord
	 */
	public char[][] geefSpelbord() {
		return spel.geefSpelbord();
	}
	
	
	
	//UC4\
	/**
	 * Gaat na of het einde van het spelbord bereikt is
	 * @return boolean isSpelbordVoltooid
	 */
	public boolean isSpelbordVoltooid() {
		return spel.isSpelbordVoltooid();
	}
    
	/**
	 * Verplaatst de werkman  op het spelbord volgens de opgegeven richting 
	 * @param richting waarin men wenst te bewegen (LINKS, RECHTS, BOVEN, BENEDEN, SLUITEN)
	 */
	public void verplaatsWerkman(Richting richting) {
		spel.verplaatsWerkman(richting);
	}
	
	/**
	 * Geeft het aantal verplaatsingen binnen het huidige spelbord van het huidige spel
	 * @return aantalVerplaatsingen
	 */
	public int geefAantalVerplaatsingen() {
		    return spel.geefAantalVerplaatsingen();
	}
	
	/**
	 * Geeft de naam van het huidige spel
	 * @return naamSpel
	 */
	public String getNaamSpel() {
		return spel.getNaamSpel();
	}
	
	//UC5
	
	/**
	 * Maakt een nieuwe instantie van Spel aan met de opgegeven naam, enkel als deze uniek is
	 * @param naamSpel de naam van het spel
	 */
	public void maakNieuwSpel(String naamSpel) {
		boolean bestaatSpel = spelRepository.bestaatSpel(naamSpel);
		if(!naamSpel.matches("\\S+"))
			throw new IllegalArgumentException("Spel naam mag geen spaties bevatten");
		if (bestaatSpel) 
			throw new SpelBestaatException("Spel bestaat al, kies een andere naam");
		spel = new Spel(naamSpel);	
	}
	
	/**
	 * Voegt het spel toe aan de spelRepository 
	 */
	public void registreerSpel() {
		spelRepository.voegToe(spel, speler);
	}
	
	//UC 6 - naamgeving komt nog niet overeen met diagramma
	
	/**
	 * maakt een nieuwe instantie van spelbord aan opgevuld met 10x10 velden van VeldType LEEGVELD
	 */
	public void maakLeegSpelbordAan() {
		spel.maakLeegSpelbordAan();
	}
	
	/**
	 * Wijzigt een veld van het spelbord op index van velden[i][j] volgens het opgegeven VeldType
	 * @param i int die refereert naar de positie op de x-as
	 * @param j int die refereert naar de positie op de y-as
	 * @param type VeldType van de gekozen indexen
	 */
	public void wijzigSpelbord(int i, int j, VeldType type) {
		spel.wijzigSpelbord(i,j,type);
	}
	
	/**
	 * Controleert of een nieuw gemaakt spelbord voldoet aan alle voorwaarden
	 * @return boolean
	 */
	public boolean valideerSpelbord() {
		return spel.valideerSpelbord();
	}
	
	/**
	 * Slaat het nieuw gemaakt spelbord op als String door dit toe te voegen aan lijstVanSpelborden
	 */
	public void registreerSpelbord() {
		spel.registreerSpelbord();
	}
	
	/**
	 * Geeft het spelbord terug in zijn originele toestand voordat verplaatsingen werden gemaakt, door een nieuwe instantie van Spelbord te creëeren met hetzelfde telnummer
	 */
	public void resetSpelbord() {
		spel.resetSpelbord();
	}

	
}
