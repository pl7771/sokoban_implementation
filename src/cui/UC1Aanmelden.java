package cui;

import java.util.InputMismatchException;
import java.util.Scanner;
import domein.DomeinController;
import exceptions.FoutWachtwoordException;
import exceptions.SpelerNietGekendException;
import util.Taal;

public class UC1Aanmelden {
	
	private String gebruikersnaam;
	private String wachtwoord;
	private Scanner s = new Scanner(System.in);
	private boolean validatieGelukt = false;
	private Taal taal = Taal.getInstance();  

	public void meldAan(DomeinController dc) {
		while (!validatieGelukt) {			
			//Login en wachtwoord opvragen bij user
			System.out.printf("\n%s",taal.vertaal("gebruikersnaam"));
			gebruikersnaam = s.nextLine();
			System.out.printf("%s",taal.vertaal("wachtwoord"));
			wachtwoord = s.nextLine();
			try {
				dc.meldAan(gebruikersnaam, wachtwoord);
				//indien dc.meldAan geen exception werpt wordt menu getoond;
				validatieGelukt = true;
				System.out.printf("\n%s %s, %s\n\n", taal.vertaal("welkom"), dc.geefSpeler()[0], taal.vertaal("aangemeld"));
				showMenu(dc); //oproepen methode showMenu
			} catch (SpelerNietGekendException npe) {
					validatieGelukt = true;
					vragenOmTeRegistreren(dc);
			}catch (FoutWachtwoordException ex) {
				System.out.println(taal.vertaal("foutPasswdExc"));
					validatieGelukt = false;
			}
		}		
	}
	
	 private void vragenOmTeRegistreren(DomeinController dc) {
		int keuze = 0;
		
		do {	
			System.out.printf("%s",taal.vertaal("spelerBestaatNietExc"));
			try {
				keuze = s.nextInt();
			} catch (InputMismatchException e) {
			}
			s.nextLine();
		}while(keuze < 1 || keuze > 2);
		
		if(keuze == 1) {
			new UC2Registreren().registeer(dc);
		}
		if(keuze == 2) {
			new ConsoleApplication(dc).start();
		}
		
	}

	public void showMenu(DomeinController dc) {
		//initialisatie van adminrechten volgens speler uit domeincontroller
		boolean adminrechten = Boolean.parseBoolean(dc.geefSpeler()[1]);
		int keuze = 0;
		boolean keuzeOk = false;
		//KEUZEMOGELIJKHEDEN voor gewone user
		while (!keuzeOk) {
			try {
			System.out.printf("+-------------------------------+\n");
			System.out.printf("|              MENU             |  \n");
			System.out.printf("+-------------------------------+\n");
			
			//System.out.printf("%s", taal.vertaal("maakKeuze"));
			
			System.out.printf("%n%s", taal.vertaal("afsluiten"));
			System.out.printf("%n%s", taal.vertaal("speelSpel"));

			//KEUZEMOGELIJKHEDEN VOOR ADMIN
				if (adminrechten == true) {
					System.out.printf("%n%s", taal.vertaal("maakSpel"));
					System.out.printf("%n%s", taal.vertaal("wijzigSpel"));
					System.out.printf("%n%s", taal.vertaal("maakJeKeuze"));
					//Keuze van speler
					keuze = s.nextInt();
					keuzeOk = true;
					//Validatie dat keuze binnen opgegeven waarden is
					while (adminrechten == true && (keuze < 1 || keuze > 4)) { //keuzes in hakjes, anders wordt eerst (adminrechten == true && keuze < 1) gecheckt
						System.out.printf("%n%s",taal.vertaal("isGeenOptie"));
						System.out.printf("%n%s", taal.vertaal("maakKeuze"));
						System.out.printf("%n%s", taal.vertaal("afsluiten"));
						System.out.printf("%n%s", taal.vertaal("speelSpel"));
						System.out.printf("%n%s", taal.vertaal("maakSpel"));
						System.out.printf("%n%s", taal.vertaal("wijzigSpel"));
						System.out.printf("%n%s", taal.vertaal("maakJeKeuze")); 
																
						keuze = s.nextInt();
					}
				} else //else statement toegevoegd voor not admin, anders stond alles in if van Admin rechten
				{
					System.out.printf("%n%s", taal.vertaal("maakJeKeuze"));
					keuze = s.nextInt();
					keuzeOk = true;
					while (adminrechten == false && (keuze < 1 || keuze > 2)) { //keuzes in hakjes, anders wordt eerst (adminrechten == true && keuze < 1) gecheckt
						System.out.printf("%n%s",taal.vertaal("isGeenOptie"));
						System.out.printf("%n%s", taal.vertaal("maakKeuze"));
						System.out.printf("%n%s", taal.vertaal("afsluiten"));
						System.out.printf("%n%s", taal.vertaal("speelSpel"));
						System.out.printf("%n%s", taal.vertaal("maakJeKeuze")); 
															
						keuze = s.nextInt();
						keuzeOk = true;
					}
				}
			} catch (InputMismatchException e) {
				keuzeOk = false;
				System.err.printf("%s",taal.vertaal("moetCijferZijn"));
				s.nextLine();
				keuze = 0;
			} 
		}
		switch (keuze) {
			case 1:
					//implementatie 1. Afsluiten spel
			
				new ConsoleApplication(dc).start();
				break;
			case 2:
					//implementatie 2. Speel spel
			    new UC3SpeelSpel().geefNamenBestaandeSpellen(dc);
				break;
			case 3:
					//implementatie 3. Maak nieuw spel
				System.err.println("Deze functie wordt ondersteund enkel in de GUI omgeving");
				showMenu(dc);
				break; 
			case 4:
				System.err.println("Deze functie wordt niet ondersteund");
				showMenu(dc);
		}// EINDE SWITCH
	}// EINDE SHOWMENU
}// EINDE UC1AANMELDEN