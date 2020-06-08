package cui;

import java.util.InputMismatchException;
import java.util.Scanner;

import domein.DomeinController;
import util.Taal;

public class ConsoleApplication {
	
	private Taal taal;
	private DomeinController dc;
	private Scanner s = new Scanner(System.in);

	
	public ConsoleApplication(DomeinController dc) {
		this.dc = dc;
	}
	
	
	public void start() {		
		kiesTaal();
		// hoofd menu selectie
		int keuze = 0;
		do {		
			System.out.printf("%n%s%n%s%n%s",taal.vertaal("newUser"), taal.vertaal("aanmelden"), taal.vertaal("sluitenSpel"));
	        System.out.printf("%s",taal.vertaal("choice"));
	        try {
				keuze = s.nextInt();
			} catch (InputMismatchException e) {
			}
	        s.nextLine();
		}while(keuze < 1 || keuze > 3);
	
		switch(keuze) { //schakelt naar juiste menu afhankelijk van keuze 1, 2 of 3
			case 1:
				new UC2Registreren().registeer(dc);
				break;
			case 2:
				new UC1Aanmelden().meldAan(dc);
				break;
			case 3: // close -> keert terug naar taalselectie
				new ConsoleApplication(dc).start();
		}
	}
	
	private void kiesTaal() {
		int gekozenTaal = 0;
		do {
			System.out.printf("+------------------------------+\n"
			         +"|                              |\n"
					 +"|            SOKOBAN           |\n"
					 +"|                              |\n"
					 +"+------------------------------+\n");
			System.out.print("Kies taal(nl): 1\nChoose language(en): 2\nChoisissez une langue(fr): 3\nEXIT: 4\n> ");
			try {
				gekozenTaal  = s.nextInt();
			} catch (InputMismatchException e) {
			}	
			s.nextLine();
		}while(gekozenTaal < 1 || gekozenTaal > 4);
		if(gekozenTaal == 4) {
			System.out.println("///////////FINITO////////////");
			System.exit(0);
		}
		taal = Taal.getInstance();
		taal.setTaal(gekozenTaal);//gekozen taal meegeven aan methode setTaal van singleton klasse Taal
	}

	
}
