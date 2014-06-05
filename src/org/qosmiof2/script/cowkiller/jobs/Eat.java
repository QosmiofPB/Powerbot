package org.qosmiof2.script.cowkiller.jobs;

import org.powerbot.script.Filter;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;
import org.qosmiof2.script.cowkiller.framework.Node;
import org.qosmiof2.script.cowkiller.ui.Gui;

public class Eat extends Node {

	private final double percent;

	public Eat(ClientContext ctx, Gui gui) {
		super(ctx);
		percent = gui.getPercent();
	}

	private Filter<Item> filter = new Filter<Item>() {

		@Override
		public boolean accept(Item food) {
			if (food.actions().equals("Eat")) {
				return true;
			}
			return false;
		}

	};

	@Override
	public boolean activate() {
		return ctx.players.local().healthPercent() <= percent
				&& !ctx.players.local().inMotion();
	}

	@Override
	public void execute() {
		Item food = ctx.backpack.select().select(filter).shuffle().poll();

		if (food.interact("Eat")) {
			// set status
		}

	}

}
