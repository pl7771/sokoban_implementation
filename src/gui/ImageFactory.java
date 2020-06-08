package gui;

import java.util.HashMap;
import java.util.Map;
import static gui.ImageFactory.SoortImage.*;
import javafx.scene.image.Image;

public class ImageFactory {
	
	public enum SoortImage {
		KIST,
		WERKMAN_LINKS,
		WERKMAN_BOVEN,
		WERKMAN_RECHTS,
		WERKMAN_BENEDEN,
		DOEL,
		MUUR,
		LEEGVELD,
		WERKMAN_DOEL,
		KIST_DOEL;
	}

	private static final Map<SoortImage, Image> mapImage = new HashMap<>() {
		{
			put(MUUR, new Image(getClass().getResourceAsStream("/images/Muur71.png")));
			put(KIST, new Image(getClass().getResourceAsStream("/images/Kist71.png")));
			put(DOEL, new Image(getClass().getResourceAsStream("/images/target71.png")));
			put(WERKMAN_LINKS, new Image(getClass().getResourceAsStream("/images/werkmanLinks.png")));
			put(WERKMAN_BOVEN, new Image(getClass().getResourceAsStream("/images/werkmanBoven.png")));
			put(WERKMAN_RECHTS, new Image(getClass().getResourceAsStream("/images/werkmanRechts.png")));
			put(WERKMAN_BENEDEN, new Image(getClass().getResourceAsStream("/images/werkmanBeneden.png")));
			put(WERKMAN_DOEL, new Image(getClass().getResourceAsStream("/images/WerkmanOpDoel.png")));
			put(KIST_DOEL, new Image(getClass().getResourceAsStream("/images/KistOpDoel.png")));
			put(LEEGVELD, new Image(getClass().getResourceAsStream("/images/gras71.jpg")));
		}
	};

	public static Image createImage(SoortImage soort) {
		return mapImage.get(soort);
	}

}
