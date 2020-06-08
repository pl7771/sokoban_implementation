package domein;

/**
 * enum Richting omschrijft een vaste set richtingen
 * die gekozen kunnen worden door de gebruiker bij de verplaatsing van zijn werkman
 * deze richtingen zijn LINKS, RECHTS, BOVEN, BENEDEN en SLUITEN
 * @author Gr86
 *
 */
public enum Richting {
	LINKS("links"), RECHTS("rechts"), BOVEN("boven"), BENEDEN("beneden"), SLUITEN("sluiten");
	

	public final String richting;
	
	private Richting(String richting) {
		this.richting = richting;
	}
	
	/**
	 * Geef de richting terug als String
	 * @return de richting
	 */
	public String getRichting() {
		return richting;
	}
}
