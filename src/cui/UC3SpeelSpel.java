package cui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import domein.DomeinController;
import javafx.collections.FXCollections;
import util.Taal;

public class UC3SpeelSpel {
	
	private Scanner s = new Scanner(System.in);
	String gekozenSpel;
	private Taal taal = Taal.getInstance();  

	public void geefNamenBestaandeSpellen(DomeinController dc) {

		
		
		int keuze;
		int aantalSpelborden = 0;
		boolean keuzeOk = false;
		
		
		while(!keuzeOk) {
			try {
					
				System.out.printf("\n+----------------------------------+\n");
				System.out.printf("|            " + "%s" + "           |", taal.vertaal("spelTitelBalk"));
				System.out.printf("\n+----------------------------------+");
					System.out.printf("%s",taal.vertaal("kiesBestaandeSpellen")); //vraagt om enkel uit bestaande spellen te kiezen
					
					//dc.geefNamenBestaandeSpellen().forEach(e -> System.out.println(e)); // geef Lijst van spellen in Stringvorm en plaatst deze onder elkaar in de console
					
					List<String> lijstSpellen = new ArrayList<String>();
					lijstSpellen = dc.geefNamenBestaandeSpellen();
					for (int i = 0 ; i < lijstSpellen.size(); i++) {
						System.out.printf("%d" + "." + "%s" + "\n", i + 1, lijstSpellen.get(i)); //print elk spel met zijn corresponderend nummer
						
					}
					
					System.out.printf("%s",taal.vertaal("0VoorHoofdmenu")); //0 kan ingegeven worden om terug naar het hoofdmenu te gaan
					System.out.printf("%s",taal.vertaal("choice2")); //vraagt keuze aan gebruiker				
					keuze = s.nextInt()-1;
					if(keuze == -1) { // stemt overeen met 0 -> dus terug naar hoofdmenu
						new UC1Aanmelden().showMenu(dc);
					}
						gekozenSpel = dc.geefNamenBestaandeSpellen().get(keuze); // haalt gekozen spelnaam op
						dc.kiesSpel(gekozenSpel); // haalt spel op uit databank
						aantalSpelborden = dc.geefAantalSpelborden(); // nummer van spelborden in het gekozen spel bepalen
						
						
					if(aantalSpelborden == 0) {
						System.out.printf("%s",taal.vertaal("enkel1Spel")); // Enkel spel 1 mag gekozen worden omdat er nog geen spelborden aanwezig zijn in dit spel
						keuzeOk = false;
					}else {
						keuzeOk = true;
					}
			} catch (IndexOutOfBoundsException e) {
				keuzeOk = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				keuzeOk = false;
			} catch (InputMismatchException e) {
				System.out.printf("%s",taal.vertaal("enkelCijfers")); 

				keuzeOk = false;
				s.next();
			} catch (NullPointerException e) {
				keuzeOk = false;
			}
		}
		
		
		System.out.printf("%s %s %s %d %s\n",taal.vertaal("gekozenSpel"), gekozenSpel, taal.vertaal("bestaatUit"), dc.geefAantalSpelborden(), taal.vertaal("spelborden"));
		kiezenUitMenu(dc);
		
	}	
	
	private void kiezenUitMenu(DomeinController dc) {		
		int keuze = 0;
		do {
			System.out.printf("%s",taal.vertaal("2mogelijkheden")); 
			try {
				keuze = s.nextInt();
			} catch (InputMismatchException e) {
				System.out.printf("%s",taal.vertaal("enkelCijfers")+"\n");
			}
			s.nextLine();
		}while( keuze < 1 || keuze > 2);
		if(keuze == 2) {
			geefNamenBestaandeSpellen(dc); //gaat terug naar vorig keuzemenu - toont lijst met spelnamen
		}
		if(keuze == 1 ) {
			new UC4VoltooiSpelbord().voltoiSpelbord(dc); //roept voltooispelbord op uit UC4
		}
	}	
	
	
}
