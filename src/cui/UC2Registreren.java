package cui;

import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;
import util.Taal;

public class UC2Registreren {
	
	
	private String gebruikersnaam;
	private String wachtwoord;
	private String bevestigPassword;
	private String naam;
	private String voornaam;
	private Speler speler;
	private Scanner s = new Scanner(System.in);
	private boolean registratieOk = false;
	private Taal taal = Taal.getInstance();  

	public void registeer(DomeinController dc) {
		
		while (!registratieOk) {
			try {
				System.out.printf("%n%s",taal.vertaal("geefVolgendeGegevens"));
				System.out.printf("%n%s",taal.vertaal("gegevensVereisten"));
				System.out.printf("%n%s",taal.vertaal("nieuweGebruikersnaam")); //vraagt nieuwe gebruikersnaam
				gebruikersnaam = s.nextLine();
				System.out.printf("%s",taal.vertaal("nieuwWachtwoord")); //vraagt nieuw wachtwoord
				wachtwoord = s.nextLine();
				System.out.printf("%s",taal.vertaal("bevestigWachtwoord")); //vraagt wachtwoord bevestiging
				bevestigPassword = s.nextLine();
				System.out.printf("%s",taal.vertaal("achternaam")); // vraagt achternaam
				naam = s.nextLine();
				System.out.printf("%s",taal.vertaal("voornaam")); //vraagt voornaam
				voornaam = s.nextLine();
		
					if(!wachtwoord.equals(bevestigPassword)) { //indien nieuw wachtwoord en bevestig wachtwoord niet gelijk zijn
						registratieOk = false;
						throw new IllegalArgumentException(taal.vertaal("controleBevestigWachtwoord"));
					}

				speler = new Speler(gebruikersnaam, wachtwoord, naam, voornaam);
				dc.registreer(gebruikersnaam, wachtwoord, naam, voornaam);
				registratieOk = true;
			} catch (IllegalArgumentException ex) {
				System.err.println(ex.getMessage());				
			} 
		}
		System.out.printf("%s",taal.vertaal("spelerGeregistreerd")); //Geeft weer dat speler is geregistreerd
		dc.meldAan(gebruikersnaam, wachtwoord); // meld speler aan met gekozen gegevens
		
		new UC1Aanmelden().showMenu(dc);

	}
	
	
	
}