package org.qosmiof2.script.cowkiller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt4.Skills;
import org.qosmiof2.script.cowkiller.UI.Gui;
import org.qosmiof2.script.cowkiller.framework.Node;

@Manifest(description = "Kills cow for great experience!", name = "QCowKiller")
public class Main extends PollingScript<org.powerbot.script.rt6.ClientContext>
		implements PaintListener {

	public static ArrayList<Node> nodeList = new ArrayList<Node>();
	private int startExp, expGained;

	@Override
	public void start() {
		startExp = (ctx.skills.experience(Skills.MAGIC)
				+ ctx.skills.experience(Skills.ATTACK)
				+ ctx.skills.experience(Skills.STRENGTH) + ctx.skills
				.experience(Skills.DEFENSE));
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Gui(ctx);
			}

		});
	}

	@Override
	public void poll() {

		for (Node node : nodeList) {
			if (node.activate()) {
				node.execute();
			}
		}

	}

	@Override
	public void repaint(Graphics g1) {
		expGained = (ctx.skills.experience(Skills.MAGIC)
				+ ctx.skills.experience(Skills.ATTACK)
				+ ctx.skills.experience(Skills.STRENGTH) + ctx.skills
					.experience(Skills.DEFENSE)) - startExp;
		Graphics2D g = (Graphics2D) g1;
		g.setColor(new Color(0, 0, 0, 120));
		g.drawRect(5, 5, 100, 100);
		g.setColor(Color.WHITE);
		g.drawString("Exp gained: " + expGained, 10, 10);
	}
}
