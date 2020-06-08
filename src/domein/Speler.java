package domein;

	/**
	 * Speler bevat de attributen en het kenmerkend gedrag van een speler
	 * 
	 * @author Gr86
	 */
public class Speler {
	
	private String gebruikersnaam;
	private String wachtwoord;
	private boolean adminrechten;
	private String naam;
	private String voornaam;
	
	/**
	 * Constructor van Speler met parameters String gebruikersnaam, String wachtwoord, String naam en String voornaam
	 * @param gebruikersnaam gebruikersnaam van de speler
	 * @param wachtwoord wachtwoord van de speler
	 * @param naam naam van de speler
	 * @param voornaam voornaam van de speler
	 */
	public Speler(String gebruikersnaam, String wachtwoord, String naam, String voornaam) {
		this(gebruikersnaam, wachtwoord, false, naam, voornaam);
	}
	
	/**
	 * Constructor van Speler voor het bepalen van adminrechten
	 *  met parameters String gebruikersnaam, String wachtwoord, boolean adminrechten, String naam en String voornaam
	 * @param gebruikersnaam gebruikersnaam van de speler
	 * @param wachtwoord wachtwoord van de speler
	 * @param adminrechten adminrechten van de speler
	 * @param naam naam van de speler
	 * @param voornaam voornaam van de speler
	 */
	public Speler(String gebruikersnaam, String wachtwoord, boolean adminrechten, String naam, String voornaam) {
		setGebruikersnaam(gebruikersnaam);
		setWachtwoord(wachtwoord);
		setAdminrechten(adminrechten);
		setNaam(naam);
		setVoornaam(voornaam);
	}
	
	/**
	 * Setter voor voornaam
	 * @param voornaam de voornaam van de speler
	 */
	private void setVoornaam(String voornaam) {
		if(voornaam == null || voornaam.isBlank()) 
			this.voornaam = "undefined"; //omdat het geen verplichte veld is bij de registratie
		this.voornaam = voornaam;
	}
	
	/**
	 * Setter voor naam
	 * @param naam de naam van de speler
	 */
	private void setNaam(String naam) {
		if(naam == null || naam.isBlank()) 
			naam = "undefined"; //omdat het geen verplichte veld is bij de registratie
		this.naam = naam;
	}
	
	/**
	 * Getter voor naam
	 * @return  naam van de speler
	 */
	public String getNaam() {
		return naam;
	}
	
	/**
	 * Getter voor voornaam
	 * @return  voornaam van de speler
	 */
	public String getVoornaam() {
		return voornaam;
	}
	
	/**
	 * Getter voor gebruikersnaam
	 * @return  gebruikersnaam van de speler
	 */
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	
	/**
	 * Getter voor wachtwoord
	 * @return wachtwoord van de speler
	 */
	public String getWachtwoord() {
		return wachtwoord;
	}
	
	/**
	 * Setter voor wachtwoord
	 * Controleert op het opgegeven wachtwoord null of blank is en werpt een IllegalArgumentException
	 * Controleert dat het wachtwoord minstens 8 karakters lang is, 1 uppercase, 1 lowercase en 1 cijfer bevat en werpt een IllegalArgumentException indien dit niet het geval is
	 * @param wachtwoord
	 */
	private void setWachtwoord(String wachtwoord) {
		if(wachtwoord == null || wachtwoord.isBlank()) throw new IllegalArgumentException("Passwoord mag niet leeg zijn");
		if(! wachtwoord.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
			throw new IllegalArgumentException("Wachtwoord moet minstens 8 karakters lang zijn en bovendien 1 uppercase, 1 lowercase en 1 cijfer bevatten");
		}
		this.wachtwoord = wachtwoord;
	}
	
	private void setGebruikersnaam(String gebruikersnaam) {
		if(gebruikersnaam == null || gebruikersnaam.isBlank()) throw new IllegalArgumentException("Gebruikersnaam mag niet leeg zijn");
		if(gebruikersnaam.length() < 8) {
			throw new IllegalArgumentException("Gebruikersnaam moet minstens 8 karakters lang zijn");
		}
		this.gebruikersnaam = gebruikersnaam;
	}
	
	/**
	 * Setter voor adminrechten
	 * @param adminrechten de adminrechten van de speler
	 */
	public void setAdminrechten(boolean adminrechten) {
		this.adminrechten = adminrechten;
	}
	
	/**
	 * Getter voor adminrechten
	 * @return adminrechten van de speler
	 */
	public boolean getAdminrechten() {
		return adminrechten;
	}
	
	/**
	 * Overrides Object.toString()
	 * @return een String met de volgende attributen van de speler: gebruikersnaam, adminrechten, naam en voornaam
	 */
	public String toString() {
		return String.format("Gebruikersnaam: %s, Adminrechten: %s, Naam: %s, Voornaam: %s", this.gebruikersnaam, this.adminrechten, this.naam, this.voornaam);
	}	
	
	/**
	 * Stelt het wachtwoord van de speler in als een lege String ""
	 */
	public void resetPassword() {
		this.wachtwoord = "";
	}
	
}