package ru.sbt.test.refactoring;

//import java.util.HashMap;
//import java.util.Map;

public class Tractor {
	private Position position = new Position(0,0);
	private Orientation orientation = Orientation.NORTH;

//	private final Map<Orientation, Orientation> map = new HashMap<>();
//
//	public Tractor() {
//		map.put(Orientation.NORTH, Orientation.EAST);
//		map.put(Orientation.EAST, Orientation.SOUTH);
//		map.put(Orientation.SOUTH, Orientation.WEST);
//		map.put(Orientation.WEST, Orientation.NORTH);
//	}

	public void move(String command) {
        if (command == "F") {
			moveForwards();
		} else if (command == "T") {
			turnClockwise();
		}
	}

    public void moveForwards() {
		switch (orientation) {
			case NORTH: position.incPosY(); break;
			case EAST: position.incPosX(); break;
			case SOUTH: position.decPosY(); break;
			case WEST: position.decPosX(); break;
		}

		if (position.isNotValid()) {
			throw new TractorInDitchException();
		}
	}

    public void turnClockwise() {
		switch (orientation) {
			case NORTH: orientation = Orientation.EAST; break;
			case EAST: orientation = Orientation.SOUTH; break;
			case SOUTH: orientation = Orientation.WEST; break;
			case WEST: orientation = Orientation.NORTH; break;
		}

//		orientation = map.get(orientation);
	}

	public int getPositionX() {
		return position.getX();
	}

	public int getPositionY() {
		return position.getY();
	}

	public Orientation getOrientation() {
		return orientation;
	}
}
