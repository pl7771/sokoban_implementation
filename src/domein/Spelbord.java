package domein;

import static domein.SpelInt.*;
import util.Taal;

import exceptions.TegenMuurException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Spelbord bevat de attributen en het kenmerkend gedrag van een spelbord
 * 
 * @author Gr86
 */
public class Spelbord {
	
	/********
	 
	 
    ##########
	#        #
    #    x   #
    #        #
    #    0   #
    #    @   #
	#    0   #
	#        #
	#    x   #
	##########  
	
	
	
	String format van de spelbord is - "##########\n#        #\n#        #\n#    x   #\n#    0   #\n#    @   #\n#    0   #\n#   x    #\n#        #\n##########" 
	
	###########        ##        ##    x   ##    0   ##    @   ##    0   ##   x    ##        ###########

	String format van de spelbord is - "###########        ##    x   ##        ##    0   ##    @   ##        ##        ##        ###########" 
	x - doel
	@ - werkman
	0 - kist
	# - muur 
	' ' - uiteraard een leeg veld
	
	
	
	##########
	#      x #
	#        #
	#        #
	#        #
	#    @   #
	#    0   #
	#        #
	#        #
	##########
 
    String format van de spelbord is - "##########\n#      x #\n#        #\n#        #\n#        #\n#    @   #\n#    0   #\n#        #\n#        #\n##########"
 									   "###########      x ##        ##        ##        ##    @   ##    0   ##        ##        ###########"
 									   
 									   
 		nog een spelbord toegevoegd in DB voor de test
 		
 									   
 	##########
 	##########
	####x#####	
	#### #####
	####0 0x##
	##x 0@####
	#####0####
	#####x####
	##########
	##########					   
 									   
 	spelbord van de opdracht in usercases								   
 	in string format = "########################x######### #########0 0x####x 0@#########0#########x########################"
 									   
 *******/
	
	
	//ATTRIBUTEN

	private int aantalVerplaatsingen = 0;
	private Veld[][] velden;
	private boolean isVoltooid = false;
	private Taal taal = Taal.getInstance();

	
	//CONSTRUCTOR
	/**
	 * Constructor van Spelbord met parameter String map
	 * @param map het spelbord als String
	 */
	public Spelbord (String map) {
		vulVeldenAan(map);
	}
	
	/**
	 * Default constructor van Spelbord
	 * vult het spelbord op met lege velden
	 */
	public Spelbord() {
		vulLegeVeldenAan();
	}
		
	//SETTERS & GETTERS
	
	/**
	 * Setter voor velden van type Veld[][]
	 * @param velden van het spelbord
	 */
	public void setVelden(Veld[][] velden) {
		this.velden = velden;
	}
	
	
	/**
	 * Instantieert een tweedimensionele array spelBord van type Veld
	 * de lengte van elke array is bepaald volgens de Interface domein.SpelInt met zijn attributen rijen en colommen
	 * loopt vervolgens door de twee dimensies van de array
	 * het veld op de huidige index binnen de loop krijgt VeldType LEEGVELD volgens enum domein.VeldType aangewezen
	 */
	public void vulLegeVeldenAan() {
		Veld[][] spelBord = new Veld[rijen][colommen];
		for (int x = 0; x < spelBord.length; x++){
		    for (int y = 0; y < spelBord[x].length; y++){
		    		spelBord[x][y] = new Veld(VeldType.LEEGVELD, x, y, false);
		    }
		}
		this.velden = spelBord;
	}

	/**
	 * Instantieert een tweedimensionele array spelBord van type Veld
	 * de lengte van elke array is bepaald volgens de Interface domein.SpelInt met zijn attributen rijen en colommen
	 * loopt vervolgens door de twee dimensies van de array
	 * via een increment van de index en een increment van de positieVanSymbolInStringMap, wordt telkens een paar gemaakt van een index op de array en een positie van een karakter in de String map
	 * dit karakter wordt omgezet naar zijn respectievelijk VeldType volgens enum domein.VeldType
	 * het veld op de huidige index binnen de loop krijgt dit VeldType aangewezen
	 * @param map spelbord als String
	 */
	public void vulVeldenAan(String map) {
		
		Veld[][] spelBord = new Veld[rijen][colommen];
		int positieVanSymbolInStringMap = 0;
		for (int x = 0; x < spelBord.length; x++){
		    for (int y = 0; y < spelBord[x].length; y++){
		    		spelBord[x][y] = new Veld(VeldType.converteer(map.charAt(positieVanSymbolInStringMap)),x,y,false);
		    	positieVanSymbolInStringMap++;
		    }
		}
		
		this.velden = spelBord;
	}
	////Methode toString////
	
	/**
	 * Overrides Object.toString
	 * Zet het spelbord om in een String
	 * Loopt door de tweedimensionele array velden en voegt voor elk veld zijn VeldType toe achteraan de String output
	 * @return de String output
	 */
	public String toString() {
		String output = "\n";
		for(Veld[] x : velden) {
			for(Veld y : x ) {
				output+=y.getVeldType().toString();
			}
			output+="\n";
		}
		return output+"\n";
	}
	/// Geeft het aantal verplaatsingen weer ////
	
	/**
	 * Getter voor aantalVerplaatsingen
	 * @return int aantalVerplaatsingen
	 */
	public int getAantalVerplaatsingen() {
		   return aantalVerplaatsingen;
    }
	
	
	
	/**
	 * Getter voor velden van type Veld[][]
	 * @return Veld[][] velden
	 */
	public Veld[][] getVelden(){
		return velden;
	}
	
	/////////////////UC4////////////////////
	
	/**
	 * Verplaatst de werkman op het spelbord in de opgegeven richting
	 * @param richting van enum domein.Richting, kies uit LINKS, RECHTS, BOVEN, BENEDEN
	 */
	public void verplaatsWerkman(Richting richting) {  
		
		
		switch(richting) {
			case LINKS:{ 
				verplaats(0, -1);
				   break;
				  }
			case RECHTS:	
					{ 
				verplaats(0, 1);
				   break;
				  }
			case BOVEN:{ 
				verplaats(-1, 0);
				   break;
				  }
			case BENEDEN:{ 
				verplaats(1, 0);
				   break;
				  }
			default: {
				throw new IllegalArgumentException();
			}
		}
		//aantalVerplaatsingen++;
		}
		
		
	private void verplaats(int verplaatsNaarX, int verplaatsNaarY) {
		aantalVerplaatsingen++;
		int x = getHuidigePositieWerkman().getPositieX();
		int y = getHuidigePositieWerkman().getPositieY();
		
		//in de eerste if wordt gechecked of de volgende veld muur is;
		//het kan ook zijn dat de volgende veld kist op doel is en achter deze staat  nog een kist op doel, zo kan de mannetje ook niet door;
		//nog een situatie kan zich voordoen als de volgende veld kist op doel is maar de veld achter is gewone kist,
		//dus kan de manntje ook niet verplaats worden en wordt een tegen muur exception gegooid;
		if(velden[x+verplaatsNaarX][y+verplaatsNaarY].isMuur() || 
		  (velden[x+verplaatsNaarX][y+verplaatsNaarY].isKistopDoel() && velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].isKistopDoel()) ||
		  (velden[x+verplaatsNaarX][y+verplaatsNaarY].isKistopDoel() && velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].bevatKist()) 
		  ){
			throw new TegenMuurException();
			
			
		//in alle andere gevallen checken we voor de volgende gebeurtenissen	
		}else{
		//////////////////////////////////////	
		////INDIEN VOLGENDE VELD KIST IS//////
		//////////////////////////////////////
			if(velden[x+verplaatsNaarX][y+verplaatsNaarY].bevatKist()) { 
				//maar veld achter deze kist muur is, of ook een kist, of een kist op doel
				//moet ook een exception throwen
				if(velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].isMuur() ||
				   velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].bevatKist() ||
				   velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].isKistopDoel()
					) {
					throw new TegenMuurException();  
					
					
				//er zitten we nog altijd in situatie als de volgende veld kist is
			    //en we willen dan deze kist opschuiven
				}else {  
					velden[x+verplaatsNaarX][y+verplaatsNaarY].setWerkman();
					//de volgende veld wordt man
					
					//de EERSTE if controleert of de werkman op de positie werkmanopdoel zich bevindt
					if(velden[x][y].isWerkmanopDoel()) {
						
							//indien volgende veld doel is wordt de volgende veld kist op doel
							//maar het veld dat werkman afstapt wordt terug een doel
							if(velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].isDoel()) {
								velden[x][y].setVeldType(VeldType.DOEL);
								velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].setKistopDoel();
							//maar het ook zijn dat achter de kist dat jij duwt een kist op doel is
							//zo dus kan hij niet verder opgeschoven worden en werpen we een tegenmuurexception
							}else if(velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].isKistopDoel()) {
								throw new TegenMuurException();
							}
							//in alle andere gevallen (betreft dat werkman op doel staat)
							//wordt het veld waar werkamn afstapt een doel
							//en de volgende wordt een kist
							else {
							velden[x][y].setVeldType(VeldType.DOEL);
							velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].setKist();
							}			
					}
					//de TWEEDE controle checked of de volgende veld zchter de kist dat wij duwen een doel is
					else if(velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].isDoel()) {     
						// als de kist op doel staat wordt veld kistOpDoel
						// achter man wordt de veld leeg
						velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].setKistopDoel();  
						velden[x][y].setLeegVeld();	
					}
					 // anders wordt de kist gewoon verder opgeschoven
					// achter man wordt de veld leeg
					else {
						velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].setKist();
						velden[x][y].setLeegVeld();	
					}	
				}
			}
			///////////////////////////////////////////////////////
			/////INDIEN VOLGENDE VELD VOOR MAN EEN LEEG VELD IS////
			///////////////////////////////////////////////////////
			else if(velden[x+verplaatsNaarX][y+verplaatsNaarY].isLeeg()){ 
				//maar in de eerste wordt gechecked of het een werkman op doel is 
				//indien zo - na het opschuiven van werkamn wordt veld achter mannetje terug doel.
				if(velden[x][y].isWerkmanopDoel()) {
					velden[x+verplaatsNaarX][y+verplaatsNaarY].setWerkman();
					velden[x][y].setVeldType(VeldType.DOEL);
				
				//anders als het een gewone lege veld wordt mannetje veplaatst en de veld achter hem wordt leeg
				}else {
					velden[x][y].setLeegVeld();      
					velden[x+verplaatsNaarX][y+verplaatsNaarY].setWerkman();
				}
			
			///////////////////////////////////////////////
			/////INDIEN VOLGENDE VELD EEN LEGE DOEL IS/////
			///////////////////////////////////////////////
			}else if(velden[x+verplaatsNaarX][y+verplaatsNaarY].isDoel()){
				//checkt de eerste (if) of het een werkman op doel is 
				//indien zo wordt de veld achter verplaatsen werkman een doel
				if(velden[x][y].isWerkmanopDoel()) {
					velden[x+verplaatsNaarX][y+verplaatsNaarY].setWerkmanopDoel();
					velden[x][y].setVeldType(VeldType.DOEL);
				//anders wordt de man verplaats naar het doel en het wordt een werkman op doel veld
				}else {
				velden[x][y].setLeegVeld();
				//man staat op doel
				velden[x+verplaatsNaarX][y+verplaatsNaarY].setWerkmanopDoel(); 
				}
				
			/////////////////////////////////////////////////////////////////////////////////////////////////	
			////NOG EEN SITUATIE DIE ZICH KAN VOORTDOEN IS DAT DE WERKMAN VOOR HET VELD KISTOPDOEL STAAT/////
			/////////////////////////////////////////////////////////////////////////////////////////////////
			}else if(velden[x+verplaatsNaarX][y+verplaatsNaarY].isKistopDoel()) { 
				
				//de eerste if checkt dat de werkman OOK OP DOEL STAAT voor een kist op doel EN dus achter deze veld een lege veld is
				if(velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].isLeeg() && velden[x][y].isWerkmanopDoel() && velden[x+verplaatsNaarX][y+verplaatsNaarY].isKistopDoel() ) {
					//zo wrdt de huidige positie gewone doel
					//de volgende wordt werkman op doel
					//en veld achter doel een kist wordt in plaats van de lege veld
					velden[x][y].setVeldType(VeldType.DOEL);
					velden[x+verplaatsNaarX][y+verplaatsNaarY].setWerkmanopDoel();
					velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].setKist();
				}
				
				//het kan ook zijn dat de volgende veld achter de kistOpDoel dat we willen duwen een muur
				//zo wordt een tegenMuurException gegooid
				else if(velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].isMuur()) {
					throw new TegenMuurException();
				}
				//kan ook zijn dat achter een kist op doel dat we willen opchuiven ook een doel veld staat;
				else if(velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].isDoel()) {
					//zo, indien het een werk man op doel is 
					//wordt het veld achter kist op doel dat we willen opschuiven ook een kist op doel
					if(velden[x][y].isWerkmanopDoel()) {
						velden[x+verplaatsNaarX][y+verplaatsNaarY].setWerkmanopDoel();
						velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].setKistopDoel();
						velden[x][y].setVeldType(VeldType.DOEL);
					}
					//en ook de situatie dat werkman op een veld staat 
					//maar voor hem een kist op doel staat en achter deze nog een doel staat
					if(velden[x][y].bevatWerkman()) {
						velden[x+verplaatsNaarX][y+verplaatsNaarY].setWerkmanopDoel();
						velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].setKistopDoel();
						velden[x][y].setLeegVeld();
					}					
				}
				
				else {
					velden[x+verplaatsNaarX*2][y+verplaatsNaarY*2].setKist();
					velden[x+verplaatsNaarX][y+verplaatsNaarY].setWerkmanopDoel();	
					velden[x][y].setLeegVeld();
				}
			}
		}	
	}
	
	private Veld getHuidigePositieWerkman() {
		Veld werkman = null;
		for(Veld[] velden : velden) {
			for(Veld veld: velden) {
				if(veld.getVeldType() == VeldType.WERKMAN) {
					werkman = veld;
				}else if(veld.getVeldType() == VeldType.WERKMAN_DOEL) {
					werkman = veld;
				}
			}
		}
		return werkman;
	}
	
	/**
	 * Geeft het aantal velden met VeldType KIST_DOEL
	 * Loopt door de tweedimensionele array velden en verhoogt het aantal telkens er een veld met type KIST_DOEL is
	 * @return int aantal
	 */
	public int getAantalKistenOpDoelen() {
		int aantal = 0;
		for(Veld[] velden : velden) {
			for(Veld veld: velden) {
				if(veld.getVeldType() == VeldType.KIST_DOEL) {
					aantal++;
				}
			}
		}
		return aantal;
	}
	
	/**
	 * Geeft het aantal velden met VeldType DOEL
	 * Loopt door de tweedimensionele array velden en verhoogt het aantal telkens er een veld met type DOEL is
	 * @return int aantal
	 */
	public int getAantalDoelen() {
		int aantal = 0;
		for(Veld[] velden : velden) {
			for(Veld veld: velden) {
				if(veld.getVeldType() == VeldType.DOEL) { //voor controler of aantal doelen is gelijk aan aantal kisten
					aantal++;
				}
			}
		}
		return aantal;
	}
	
	/**
	 * Geeft het aantal velden met VeldType WERKMAN_DOEL
	 * Loopt door de tweedimensionele array velden en verhoogt het aantal telkens er een veld met type WERKMAN_DOEL is
	 * @return int aantal
	 */
	public int getAantalWerkmanDoel() {
		int aantal = 0;
		for(Veld[] velden : velden) {
			for(Veld veld: velden) {
				if(veld.getVeldType() == VeldType.WERKMAN_DOEL) {
					aantal++;
				}
			}
		}
		return aantal;
	}
	
	/**
	 * Geeft het aantal velden met VeldType KIST
	 * Loopt door de tweedimensionele array velden en verhoogt het aantal telkens er een veld met type KIST is
	 * @return int aantal
	 */
	public int getAantalKisten() {
		int aantal = 0;
		for(Veld[] velden : velden) {
			for(Veld veld: velden) {
				if(veld.getVeldType() == VeldType.KIST) {//voor controler of aantal doelen is gelijk aan aantal kisten
					aantal++;
				}
			}
		}
		return aantal;
	}
	
	/**
	 * Geeft het aantal velden met VeldType WERKMAN
	 * Loopt door de tweedimensionele array velden en verhoogt het aantal telkens er een veld met type WERKMAN is
	 * @return int aantal
	 */
	public int getAantalWerkmannen() {
		int aantal = 0;
		for(Veld[] velden : velden) {
			for(Veld veld: velden) {
				if(veld.getVeldType() == VeldType.WERKMAN) { //coor controler of enkel een man in het veld zit
					aantal++;
				}
			}
		}
		return aantal;
	}
	
	/**
	 * Gaat na of het spelbord voltooid is
	 * het spelbord is voltooid als er geen velden van VeldType DOEL meer zijn en dus allemaal gewijzigd zijn naar KIST_DOEL
	 * @return boolean
	 */
	public boolean isSpelbordVoltooid() {
		return getAantalDoelen() == 0 && getAantalWerkmanDoel() == 0 ? !isVoltooid : isVoltooid;
	} 

	
	
	//UC 6 
	
	/**
	 * Wijzigt een veld van het spelbord op index van velden[i][j] volgens het opgegeven VeldType
	 * @param j int die refereert naar de positie op de x-as
	 * @param i int die refereert naar de positie op de y-as
	 * @param type VeldType van de gekozen indexen
	 */
	public void wijzigSpelbord(int j, int i, VeldType type) {
		velden[i][j].setVeldType(type);
	}
	
	private void showErrorMessage(String message) {
		String validationFailMessage = taal.vertaal(message);
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(taal.vertaal("validationFail"));
		alert.setContentText(validationFailMessage);
		alert.showAndWait();
	}
	
	/**
	 * Valideert het nieuw gemaakte spelbord
	 * 
	 * 1. Gaat na of er sprake is van een binnenmuur, die omsloten is door een andere muur
	 * 2. Gaat na of de buitenmuur dicht is
	 * 3. Gaat na of er exact 1 werkman aanwezig is op het spelbord
	 * 4. Gaat na of er minimum 1 doel aanwezig is op het spelbord
	 * 5. Gaat na of het aantal kisten en doelen gelijk is
	 * 6. Gaat na of er zich elementen buiten de muur bevinden
	 * 7. Gaat na of de werkman omringt is door muren en zich dus niet verder kan verplaatsen
	 * 8. Gaat na of een kist in een hoek staat en dus niet langer verplaatst kan worden
	 * 
	 * Er is voor elk nummer respectievelijk één foutmelding indien het spelbord niet voldoet aan zijn voorwaarden
	 * @return boolean
	 */
	public boolean valideerSpelBord() {
		
	
		boolean correct = true;
		
		//VALIDATIE isMuurDicht ------------------------------------------------------------------------------------------------------------------------------
		Veld muur = null;
		int aantalAansluitendeMuren = 0;
		boolean isMuurDichtCheckVolledig = false;	
		
			for(Veld[] fields : velden){
				if (isMuurDichtCheckVolledig == true){
					break;
				}
				for(Veld veld: fields) {
					if (isMuurDichtCheckVolledig == true){
						break;
					}
				
						if(veld.getVeldType() == VeldType.MUUR) {
							muur = veld;
							int x_Muur = muur.getPositieX();
							int y_Muur = muur.getPositieY();
							
							//Atributten waarmee we controleren of een muurveld zich binnen een gesloten muur bevindt
							
							int binnenmuurOnder = 0;
							boolean binnenOnderzijde = false;
							
							int binnenmuurBoven = 0;
							boolean binnenBovenzijde = false;
							
							int binnenLinks = 0;
							boolean binnenLinkerzijde = false;
							
							int binnenRechts = 0;
							boolean binnenRechterzijde = false;
							
							boolean muurIsOmringt = false;
							
							// STAP 1 checkt of het muurveld zich binnen een gesloten muur bevindt
							
							//checkt onderzijde
							for(int i=x_Muur;i<9;i++) {
							  if(velden[i + 1][y_Muur].isMuur() ) {
								binnenmuurOnder++;
								}
							 }
							
							if (binnenmuurOnder > 0) {binnenOnderzijde = true;}
							
							//checkt bovenzijde
							for(int i=x_Muur;i>0;i--) {
								if(velden[i - 1][y_Muur].isMuur() ) {
								binnenmuurBoven++;
									}
								 }
							if (binnenmuurBoven > 0) {binnenBovenzijde = true;}
							
							
							//checkt linkerzijde
							for(int i=y_Muur;i>0;i--) {
								if(velden[x_Muur][i- 1].isMuur() ) {
									binnenLinks++;
									}
								 }
								
							if (binnenLinks > 0) {binnenLinkerzijde = true;}
							
							//checkt rechterzijde
							for(int i=y_Muur;i<9;i++) {
								if(velden[x_Muur][i + 1].isMuur() ) {
									binnenRechts++;
									}
								 }
								
							if (binnenRechts > 0) {binnenRechterzijde = true;}
							
							
							if (binnenOnderzijde == true && binnenBovenzijde == true && binnenLinkerzijde == true && binnenRechterzijde == true) {
								muurIsOmringt = true; // Muurveld bevind zich binnen de buitenmuur - die gesloten is
								aantalAansluitendeMuren = 2; // omzijlt error message en gaat verder met checken van muurvelden
								
							}
						
							// 	STAP 2 Bevestigt dat de muur gesloten is
							// eerste voorwaarden -> vermijden ArrayOutOfBoundsException door niet buiten 10x10 te gaan
					
							if (muurIsOmringt == false) {
								if (x_Muur < 9) {	
									if(velden[x_Muur + 1][y_Muur].isMuur()) {aantalAansluitendeMuren += 1;} // checkt onder
									} 
								if (y_Muur > 0) {
									if(velden[x_Muur][y_Muur - 1].isMuur()) {aantalAansluitendeMuren += 1;} // checkt links
								}
								if (x_Muur > 0) {
									if(velden[x_Muur - 1][y_Muur].isMuur()) {aantalAansluitendeMuren += 1;} // checkt boven
								}
								if (y_Muur < 9) {
									if(velden[x_Muur][y_Muur + 1].isMuur()) {aantalAansluitendeMuren += 1;} // checkt rechts
								}
							}
							
							// Muur is pas gesloten als het 2 raakvlakken heeft met andere muren - houd geen rekening met diagonalen
							if(aantalAansluitendeMuren < 2) {
								isMuurDichtCheckVolledig = true;
								String message = "muurIsNietGesloten";
								showErrorMessage(message);
								correct = false;
							}
								
							else if (aantalAansluitendeMuren >= 2) {
								aantalAansluitendeMuren = 0;
							}
						}
					}				
				}		
		// EINDE isMuurDicht
		
		//VALIDATIE #Werkmannen ------------------------------------------------------------------------------------------------------------------------------
		if (getAantalWerkmannen() != 1) {
			String message = "verkeerdAantalWerkmannen";
			showErrorMessage(message);
			correct = false;
		}//EINDE is #Werkmannen
		
		//VALIDATIE Minimum #Doelen ------------------------------------------------------------------------------------------------------------------------------
		if (getAantalDoelen() < 1) {
			String message = "Minimum1DoelVereist";
			showErrorMessage(message);
			correct = false;
		} //EINDE is Minimum #Doelen
		
		//VALIDATIE #Kisten = #Doelen ------------------------------------------------------------------------------------------------------------------------------
		if (getAantalDoelen() != getAantalKisten()) {
			String message = "NummerKistEnDoelNietGelijk";
			showErrorMessage(message);
			correct = false;
		} //EINDE is #Kisten = #Doelen
		
		//Validatie doel, virus, werkman buiten muren onder
		Veld doel= null;
		boolean isBuitenMuurValidatieVolledig = false;
		
		for(Veld[] fields: velden) {
			if (isBuitenMuurValidatieVolledig == true){
				break;
			}
			for(Veld veld: fields) {
				if (isBuitenMuurValidatieVolledig == true){
					break;
				}
				
				if(veld.getVeldType() == VeldType.DOEL || veld.getVeldType() == VeldType.KIST || veld.getVeldType() == VeldType.WERKMAN) {
					doel = veld;

					int x_Doel = doel.getPositieX();
					int y_Doel = doel.getPositieY();


					
					//Atributten waarmee we controleren of een doelveld zich binnen een gesloten muur bevindt
					
					int geenMuurOnder = 0;
					int geenMuurBoven = 0;
					int geenMuurLinks = 0;
					int geenMuurRechts = 0;
					

					//controle doel, virus buiten muren onderzijde
					if (x_Doel < 9 ) {	
						
						for(int i=x_Doel;i<9;i++) {
						   if(velden[i + 1][y_Doel].isMuur() ) {
							   geenMuurOnder++;
							}
					    }
					}
					//if (binnenmuurOnder > 0) {binnenOnderzijde = true;}
					
					//checkt bovenzijde
					if (x_Doel < 9 ) {
						
						for(int i=x_Doel;i>0;i--) {
						    if(velden[i - 1][y_Doel].isMuur() ) {
							    geenMuurBoven++;
							}
						}
					}
					//if (binnenmuurBoven > 0) {binnenBovenzijde = true;}
					
					//checkt linkerzijde
					if (x_Doel < 9) {
						
						for(int i=y_Doel;i>0;i--) {
							if(velden[x_Doel][i- 1].isMuur() ) {
								geenMuurLinks++;
							}
						}
					}
					//checkt rechterzijde
					if (x_Doel < 9 ) {
						
						for(int i=y_Doel;i<9;i++) {
							if(velden[x_Doel][i + 1].isMuur() ) {
								geenMuurRechts++;
								break;
							}
						}
					}
	
				    //if (binnenRechts > 0) {binnenRechterzijde = true;}

					
					if(geenMuurOnder < 1 || geenMuurBoven < 1 || geenMuurLinks <1 || geenMuurRechts < 1) {
						isBuitenMuurValidatieVolledig = true;
						String message = "elementBuitenMuren";
						showErrorMessage(message);
						correct = false;
						}
					 
				}		
			}
		} //EINDE Validatie doel, virus, werkman buiten muren onder


		
		//VALIDATIE isWerkmanOmringt ------------------------------------------------------------------------------------------------------------------------------

		int aantalMurenRondWerkman = 0;
		Veld werkman= null;
		
		if (getAantalWerkmannen() == 1) { //ENKEL ALS EEN WERKMAN AANWEZIG IS
			werkman = getHuidigePositieWerkman();
			int x_Werkman = werkman.getPositieX();
			int y_Werkman = werkman.getPositieY();


			if((x_Werkman < 9 && x_Werkman > 0) && (y_Werkman < 9 && y_Werkman > 0)) {
				//controleert muren boven, onder, links, rechts
				if(velden[x_Werkman + 1][y_Werkman].isMuur()) {aantalMurenRondWerkman += 1;}//controleert muur onder
				if(velden[x_Werkman][y_Werkman - 1].isMuur()) {aantalMurenRondWerkman += 1;}//controleert muur links
				if(velden[x_Werkman - 1][y_Werkman].isMuur()) {aantalMurenRondWerkman += 1;}//controleert muur boven
				if(velden[x_Werkman][y_Werkman + 1].isMuur()) {aantalMurenRondWerkman += 1;}//controleert muur rechts
			}

			if (aantalMurenRondWerkman == 4) {
				String message = "WerkmanIsOmringt";
				showErrorMessage(message);
				correct = false;
				} 
		}//EINDE isWerkmanOmringt
		
		//VALIDATIE staatKistInHoek ------------------------------------------------------------------------------------------------------------------------------
		Veld kist = null;
		int kistInHoek = 0;
		boolean staatKistInHoekCheckVolledig = false;	
		
			for(Veld[] fields : velden){
				if (staatKistInHoekCheckVolledig == true){
					break;
				}
				for(Veld veld: fields) 		{
					if (staatKistInHoekCheckVolledig == true){
						break;
					}
						if(veld.getVeldType() == VeldType.KIST) {
							kist = veld;
							int x_Kist = kist.getPositieX();
							int y_Kist = kist.getPositieY();
							
							if((x_Kist < 9 && x_Kist > 0) && (y_Kist < 9 && y_Kist > 0)) {
								
								//checkt linker-bovenhoek
								if(velden[x_Kist - 1][y_Kist + 1].isMuur() && velden[x_Kist][y_Kist + 1].isMuur() && velden[x_Kist - 1][y_Kist].isMuur())	{kistInHoek += 1;}
								//checkt rechter-bovenhoek
								if(velden[x_Kist + 1][y_Kist + 1].isMuur() && velden[x_Kist][y_Kist + 1].isMuur() && velden[x_Kist + 1][y_Kist].isMuur())	{kistInHoek += 1;}
								//checkt linker-onderhoek
								if(velden[x_Kist][y_Kist - 1].isMuur() && velden[x_Kist - 1][y_Kist - 1].isMuur() && velden[x_Kist - 1][y_Kist].isMuur())	{kistInHoek += 1;}
								//checkt rechter-onderhoek
								if(velden[x_Kist][y_Kist - 1].isMuur() && velden[x_Kist + 1][y_Kist - 1].isMuur() && velden[x_Kist + 1][y_Kist].isMuur())	{kistInHoek += 1;}
							}

							
							//kist staat in een hoek als aan 1 van bovenstaande voorwaarden is voldaan
							if(kistInHoek >= 1) {
								staatKistInHoekCheckVolledig = true;
								String message = "KistIsOmringt";
								showErrorMessage(message);
								correct = false;
							}
						}
					}
				}
			 //EINDE staatKistInHoek
		
		return correct; // EINDE VALIDATIE SPELBORD ************************************
	}
		
}
		

	
