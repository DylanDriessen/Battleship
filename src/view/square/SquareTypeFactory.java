package view.square;

import model.enums.ButtonType;

/**
 * @author Wouter
 */
public class SquareTypeFactory {

	public static SquareType create(ButtonType buttonType) {
		//Could be expanded in the future
		return SquareType.valueOf(buttonType.toString().toUpperCase());
	}
	
}
