package cui;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import domein.DomeinController;
import domein.VeldType;
import exceptions.SpelBestaatException;

public class UC5TEST {
	
	Scanner s = new Scanner(System.in);	
	
	public void maakSpelAan(DomeinController dc) {	
		String spelNaam;
		boolean validatieOk = false;
		
		while (!validatieOk) {
			try {
				System.out.printf("Geef naam spel: ");
				spelNaam = s.nextLine().trim();
				dc.maakNieuwSpel(spelNaam);//nieuw spel aanmaken
				validatieOk = true;
				dialoogSpelborden(dc);
			} catch (SpelBestaatException e) {
				System.out.println(e.getMessage());
				validatieOk = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				validatieOk = false;
			} 
		}
		
	}

	private void dialoogSpelborden(DomeinController dc) {
		System.out.println("Wenst u een spelbord toe te voegen? (Y/N): ");
		String antwoord = s.nextLine().toUpperCase();
		while(!antwoord.equals("Y") && !antwoord.equals("N")) {
			System.out.println("Print Y of N: ");
			antwoord = s.nextLine().toUpperCase();
		}
		if(antwoord.equals("Y")) 
			maakSpelborden(dc);
		if(antwoord.equals("N")) {
			System.out.println("Wenst u het nieuw gemaakte spel op te slaan?");
			String opslaan = s.next().toUpperCase();
			if(opslaan.equals("Y")) {
				
					dc.registreerSpel();
				
				System.out.println("Spel en spelborden opgeslagen");
				new UC1Aanmelden().showMenu(dc);
			}
			if(opslaan.equals("N")) {
				//spel
				new UC1Aanmelden().showMenu(dc);
			}
		}
			
		
	}

	private void maakSpelborden(DomeinController dc) {
		dc.maakLeegSpelbordAan();
		System.out.println(dc.geefAantalSpelborden());
		System.out.println("-------HuidigeSpelbord-------");
		System.out.println(this.spelbordToString(dc.geefSpelbord()));
		System.out.println("-------HuidigeSpelbord-------");
		
		System.out.println("Wenst u huidige spelbord aan te passen? (Y/N): ");
		String antwoord = s.nextLine();
		while(antwoord.equals("Y")) {
				pasSpelbordAan(dc);		
				System.out.println("-------HuidigeSpelbord-------");
				System.out.println(this.spelbordToString(dc.geefSpelbord()));
				System.out.println("-------HuidigeSpelbord-------");
		}
		if(antwoord.equals("N"))
			vragenOpslaanOfVerwijderen(dc);	
	}
	
	private void pasSpelbordAan(DomeinController dc) {
		System.out.printf("Geef coordinaten en VeldType in (vb: 1 1 Muur/Werkman/Leegveld/Kist/Doel/STOP om te stoppen):");		
		String input = s.nextLine().toUpperCase();
		if(input.equals("STOP")) {
			vragenOpslaanOfVerwijderen(dc);
		}
		while(!controleerInputUserOk(input)) {
			System.out.printf("Geef coordinaten en VeldType in (vb: 1 1 Muur/Werkman/Leegveld/Kist/Doel):");		
			input = s.nextLine().toUpperCase();
		}
		String plaatsenVeld[] = input.split("\\s+");
		plaatsenVeld[2] = String.valueOf(Character.toUpperCase(plaatsenVeld[2].charAt(0))) + plaatsenVeld[2].substring(1);
		dc.wijzigSpelbord(Integer.parseInt(plaatsenVeld[0]), Integer.parseInt(plaatsenVeld[1]), VeldType.valueOf(plaatsenVeld[2]));
	}
	
	private void vragenOpslaanOfVerwijderen(DomeinController dc) {
		System.out.println("Wilt u de huidigespelbord oplsaan of verwijderen? (O/V): ");
		String input = s.nextLine();
		if(input.equals("O")) {
			if(dc.valideerSpelbord()) {
				dc.registreerSpelbord();
				dialoogSpelborden(dc);
			}
			else {
				System.out.println("niet gevalideerd");
			}
		}
		if(input.equals("V")) {
			System.out.println("Laatste spelbord is niet opgeslagen");
			dialoogSpelborden(dc);
		}
	}

	private boolean controleerInputUserOk(String input) {
		String plaatsenVeld[] = input.split("\\s+");
		if(Integer.parseInt(plaatsenVeld[0]) > 8 || Integer.parseInt(plaatsenVeld[0]) < 1) return false;
		if(Integer.parseInt(plaatsenVeld[1]) > 8 || Integer.parseInt(plaatsenVeld[0]) < 1) return false;
		if(!plaatsenVeld[2].equals("MUUR") && !plaatsenVeld[2].equals("WERKMAN") && !plaatsenVeld[2].equals("LEEGVELD") && !plaatsenVeld[2].equals("KIST") && !plaatsenVeld[2].equals("DOEL")) return false;
		return true;
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
