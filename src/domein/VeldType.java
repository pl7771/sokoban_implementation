package domein;

	/**
	 * enum VeldType omschrijft een vaste set van veldTypes
	 * elk type stemt overeen met een symbool die gebruikt kan worden bij het schrijven van een spelbord als String
	 * @author Gr86
	 *
	 */
	public enum VeldType {
	
		KIST("0"),
		WERKMAN("@"),
		DOEL("x"),
		MUUR("#"),
		LEEGVELD(" "),
		WERKMAN_DOEL("&"),
		KIST_DOEL("o");
	
		public final String symbol;
	
		private VeldType(String symbol) {
			this.symbol = symbol;
		}
		
		/**
		 * Overrides Object.toString()
		 * @return symbol
		 */
		public String toString() {
		return symbol;
		}
		
		/**
		 * Converteert een symbool naar zijn respectievelijk veldType
		 * @param symbol het symbool
		 * @return VeldType
		 */
		public static VeldType converteer(char symbol){
			switch(symbol) {
				case '0':{
					return VeldType.KIST;
				}
				case '@':{
					return VeldType.WERKMAN;
				}
				case 'x':{
					return VeldType.DOEL;
				}
				case '#':{
					return VeldType.MUUR;
				}
				case ' ':{
					return VeldType.LEEGVELD;
				}
				case '&':{
					return VeldType.WERKMAN_DOEL;
				}
				case 'o':{
					return VeldType.KIST_DOEL;
				}
				default: {
					return null;
				}
			}
		}

	
}