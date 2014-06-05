package org.qosmiof2.script.cowkiller.jobs;

import org.powerbot.script.Filter;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;
import org.qosmiof2.script.cowkiller.data.Id;
import org.qosmiof2.script.cowkiller.framework.Node;

public class Attack extends Node {

	public Attack(ClientContext ctx) {
		super(ctx);
	}

	int[] id = Id.Cow.getId();

	private Filter<Npc> filter = new Filter<Npc>() {

		@Override
		public boolean accept(Npc cow) {
			if (!cow.inCombat() && cow.idle() && cow.inViewport()
					&& cow.valid()) {
				return true;
			}
			return false;
		}

	};

	@Override
	public boolean activate() {

		return ctx.players.local().animation() == -1
				&& ctx.players.local().idle()
				&& ctx.players.local().healthPercent() > 30;
	}

	@Override
	public void execute() {
		Npc cow = ctx.npcs.id(Id.Cow.getId()).select().select(filter).poll();

		if (ctx.movement.distance(cow, ctx.players.local().tile()) < 10) {
			if (cow.interact("Attack")) {
				// set status
			};
		} else {
			ctx.movement.step(cow);
		}

	}

}
