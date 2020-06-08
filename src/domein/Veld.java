package domein;

	/**
	 * Veld bevat de attributen en het kenmerkend gedrag van een veld
	 * 
	 * @author Gr86
	 */
public class Veld {
	
	//ATTRIBUTEN
	private int positieX;
	private int positieY;
	private VeldType veldType;
	
	//CONSTRUCTOR
	
	/**
	 * Constructor van Veld met paramaters VeldType veldType, int positieX, int positieY en boolean isDoel
	 * @param veldType veldType van Veld
	 * @param positieX positieX van Veld
	 * @param positieY positieY van Veld
	 * @param isDoel Veld is al dan niet van VeldType.DOEL
	 */
	public Veld (VeldType veldType, int positieX, int positieY, boolean isDoel) {
		this.veldType = veldType;
		this.positieX = positieX;
		this.positieY = positieY;
	}

	//SETTERS & GETTERS
	/**
	 * Getter voor positieX
	 * @return positieX
	 */
	public int getPositieX() {
		return positieX;
	}
	
	/**
	 * Getter voor positieY
	 * @return positieY
	 */
	public int getPositieY() {
		return positieY;
	}
	
	/**
	 * Setter voor positieX
	 * @param positieX positieX van het veld
	 */
	public void setPositieX(int positieX) {
		this.positieX = positieX;
	}
	
	/**
	 * Setter voor positieY van het veld
	 * @param positieY positieY van het veld
	 */
	public void setPositieY(int positieY) {
		this.positieY = positieY;
	}
	
	/**
	 * Getter voor veldType
	 * @return veldType
	 */
	public VeldType getVeldType() {
		return veldType;
	}
	
	/**
	 * Setter voor veldType
	 * @param veldType het veldType van veld
	 */
	public void setVeldType(VeldType veldType) {
		this.veldType = veldType;
	}
	
	/**
	 * Retourneert true als getVeldType() == VeldType.DOEL
	 * @return boolean
	 */
	public boolean isDoel() {
		return this.getVeldType() == VeldType.DOEL;
	}
	
	//extra methoden voor verplaatsen werkman
	
	/**
	 * Retourneert true als getVeldType() == VeldType.WERKMAN
	 * @return boolean
	 */
	public boolean bevatWerkman() {
		return this.getVeldType() == VeldType.WERKMAN;
	}
	
	/**
	 * Retourneert true als getVeldType() == VeldType.KIST
	 * @return boolean
	 */
	public boolean bevatKist() {
		return this.getVeldType() == VeldType.KIST;
	}
	
	/**
	 * Setter om veldType in te stellen op VeldType.WERKMAN
	 */
	public void setWerkman() {
		this.setVeldType(VeldType.WERKMAN);
	}
	
	/**
	 * Setter om veldType in te stellen op VeldType.KIST
	 */
	public void setKist() {
		this.setVeldType(VeldType.KIST);
	}
	
	/**
	 * Retourneert true als getVeldType() == VeldType.MUUR
	 * @return boolean
	 */
	public boolean isMuur() {
		return this.getVeldType() == VeldType.MUUR;
	}
	
	/**
	 * Setter om veldType in te stellen op VeldType.LEEGVELD
	 */
	public void setLeegVeld() {
		this.setVeldType(VeldType.LEEGVELD);
	}
	
	/**
	 * Retourneert true als getVeldType() == VeldType.LEEGVELD
	 * @return boolean
	 */
	public boolean isLeeg() {
		return this.getVeldType() == VeldType.LEEGVELD;
	}
	
	/**
	 * Retourneert true als getVeldType() == VeldType.WERKMAN_DOEL
	 * @return boolean
	 */
	public boolean isWerkmanopDoel() {
		return this.getVeldType() == VeldType.WERKMAN_DOEL;
	}
	
	/**
	 * Retourneert true als getVeldType() == VeldType.KIST_DOEL
	 * @return boolean
	 */
	public boolean isKistopDoel() {
		return this.getVeldType() == VeldType.KIST_DOEL;
	}
	
	/**
	 * Setter om veldType in te stellen op VeldType.WERKMAN_DOEL
	 */
	public void setWerkmanopDoel() {
		this.setVeldType(VeldType.WERKMAN_DOEL);
	}
	
	/**
	 * Setter om veldType in te stellen op VeldType.KIST_DOEL
	 */
	public void setKistopDoel() {
		this.setVeldType(VeldType.KIST_DOEL);
	}
	
	
	
	
	
}