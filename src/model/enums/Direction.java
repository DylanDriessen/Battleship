package model.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Direction {
	EAST(1, 0, "West"), SOUTH(0, -1, "North"), WEST(-1, 0, "East"), NORTH(0, 1, "SOUTH");

	private int x, y;
	private String opposite;
	private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	Direction(int x, int y, String opposite) {
		this.x = x;
		this.y = y;
		this.opposite = opposite;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public static Direction randomDirection() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
	public static Direction oppositeDirection(Direction dir) {
		return Direction.valueOf(dir.opposite.toUpperCase());
	}
}
