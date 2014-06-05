package org.qosmiof2.script.cowkiller.data;

public enum GroundItems {

	Meat("Meat", 0), Leather("Leather", 0), Bones("Bones", 0);

	private int id;
	private String name;

	private GroundItems(String name, int id) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
