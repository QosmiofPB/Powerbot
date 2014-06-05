package org.qosmiof2.script.cowkiller.data;

public enum Id {

	Cow(new int[] { 1, 1 });

	private int[] id;

	private Id(int[] id) {
		this.id = id;
	}

	public int[] getId() {
		return id;
	}
}
