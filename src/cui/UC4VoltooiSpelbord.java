package cui;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

import domein.DomeinController;
import domein.Richting;
import exceptions.TegenMuurException;
import util.Taal;

public class UC4VoltooiSpelbord {
	
	private Scanner s = new Scanner(System.in);
	private Taal taal = Taal.getInstance();  

	public void voltoiSpelbord(DomeinController dc) {
		
		
		
		boolean richtingOk = false;
		int aantalStappen = 0;
		System.out.println(taal.vertaal("hetspelbord"));
		System.out.println();
		while (!dc.isEindeSpel()) { //zolang spel niet ten einde is
			System.out.println(spelbordToString(dc.geefSpelbord())); //geeft spelbord weer in stringvorm
			try {
			
				int gekozenRichting = 0;
				int keuze = 0;
				Richting richting = null;
				
				while(!dc.isSpelbordVoltooid()) {					
					
					do {
						System.out.printf(taal.vertaal("richting"));						
						try {
							gekozenRichting = s.nextInt();
						} catch (InputMismatchException e) {
							System.out.printf("%s",taal.vertaal("enkelCijfers")+"\n");
						}
						s.nextLine();
					}while(gekozenRichting < 1 || gekozenRichting > 5);
					
					if(gekozenRichting == 5) { //als gekozen is om af te sluiten
						new UC3SpeelSpel().geefNamenBestaandeSpellen(dc); //geeft lijst van beschikbare spellen weer
					}
					
					switch(gekozenRichting) {
						case 1:{
							richting = Richting.LINKS;
							break;
						}
						case 2:{
							richting = Richting.RECHTS;
							break;
						}
						case 3:{
							richting = Richting.BOVEN;
							break;
						}
						case 4:{
							richting = Richting.BENEDEN;
							break;
						}
						default: {
							throw new IllegalArgumentException(taal.vertaal("ongeldig"));
						
						}
					}
					
						dc.verplaatsWerkman(richting); //roept methode aan om werkman te verplaatsen met gekozen richting
						richtingOk = true;
					
						System.out.println(spelbordToString(dc.geefSpelbord())); //geeft opnieuw spelbord na verplaatsing van werkman
						aantalStappen = dc.geefAantalVerplaatsingen(); //verhoogt aantal verplaatsingen ++
				}
	
				System.out.printf("%s %d %s", taal.vertaal("spelbordvoltooid"), aantalStappen, taal.vertaal("verplaatsingen")); //spelbord is voltooid + aantal stappen
				System.out.printf("%s %s: %d/%d",taal.vertaal("voltooideborden"), dc.getNaamSpel(), dc.geefAantalVoltooideSpelborden(), dc.geefAantalSpelborden()); // aantal voltooide borden in spel
				System.out.println(taal.vertaal("probeer")); //probeer volgend spelbord?
				System.out.println("");
				aantalStappen = 0; // reset van stappenteller voor volgend bord
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
				richtingOk = false;
			} catch (TegenMuurException ex) {
				System.out.println(ex.getMessage());
				richtingOk = false;
			} catch (IndexOutOfBoundsException ex) {
				System.out.println(taal.vertaal("geenspelborden"));
			}
		}
		System.out.printf("%s %d %s", taal.vertaal("spelbordvoltooid"), aantalStappen, taal.vertaal("verplaatsingen"));
		System.out.println(taal.vertaal("einde")); //EINDE SPEL
		System.out.println(taal.vertaal("kiesterug"));
		new UC3SpeelSpel().geefNamenBestaandeSpellen(dc); //Keert terug naar lijst van spellen	
	}
	
	
	public String spelbordToString(char[][] spelbord) {
		return Arrays.stream(spelbord)
				.map(Arrays::toString)
				.collect(Collectors.joining(System.lineSeparator()))
				.replace(",", "")
			    .replace("[", "") 
			    .replace("]", "") ;
	}
	
	
}
