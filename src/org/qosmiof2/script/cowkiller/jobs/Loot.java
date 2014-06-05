package org.qosmiof2.script.cowkiller.jobs;

import org.powerbot.script.Filter;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GroundItem;
import org.qosmiof2.script.cowkiller.UI.Gui;
import org.qosmiof2.script.cowkiller.framework.Node;

public class Loot extends Node {

	private int x, y;
	private final String itemName;

	public Loot(ClientContext ctx, Gui gui) {
		super(ctx);
		itemName = gui.getName();
	}

	private Filter<GroundItem> filter = new Filter<GroundItem>() {

		@Override
		public boolean accept(GroundItem item) {
			if (item.inViewport() && item.valid()
					&& item.name().equals(itemName)) {
				return true;
			}
			return false;
		}

	};

	@Override
	public boolean activate() {
		x = Random.nextInt(1, 5);
		y = Random.nextInt(1, 5);

		// ^For safety reasons (so it doesnt loot everytime)^
		return !ctx.players.local().inCombat()
				&& !ctx.players.local().inMotion()
				&& ctx.backpack.select().count() != 28 && x == y;
	}

	@Override
	public void execute() {
		GroundItem lootItem = ctx.groundItems.select().select(filter).first()
				.nearest().poll();

		if (lootItem.interact("Take")) {
			// set status
		}

	}

}
