package org.qosmiof2.script.cowkiller.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.qosmiof2.script.cowkiller.QCowKiller;
import org.qosmiof2.script.cowkiller.data.GroundItems;
import org.qosmiof2.script.cowkiller.jobs.Attack;
import org.qosmiof2.script.cowkiller.jobs.Eat;
import org.qosmiof2.script.cowkiller.jobs.Loot;

public class Gui extends ClientAccessor {

	/* Frame */
	private JFrame frame;

	/* JMenu items */
	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem troublesMenuItem = new JMenuItem("Troubles");
	private JMenu mnAbout = new JMenu("About");
	private JMenuItem aboutMenuItem = new JMenuItem("About");
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem exitMenuItem = new JMenuItem("Exit");
	private JMenu helpMenu = new JMenu("Help");
	private JMenuItem eatingMenuItem = new JMenuItem("Eating");
	private JMenuItem lootingMenuItem = new JMenuItem("Looting");

	/* Labels */
	private JLabel percentLabel = new JLabel("%");
	private JLabel selectPercentLabel = new JLabel(
			"<html>Please select at what<br>percent level you<br>you want script to eat.</html>");
	private JLabel lootLabel = new JLabel(
			"<html>Please select what<br>you want script to loot.</html>");

	/* Spinner */
	private SpinnerNumberModel numModel = new SpinnerNumberModel(1.0, 1.0,
			99.0, 1.0);
	private JSpinner spinner = new JSpinner(numModel);

	/* Combo boxes */
	private JComboBox<GroundItems> lootComboBox = new JComboBox<GroundItems>(
			GroundItems.values());
	private JCheckBox foodCheckBox = new JCheckBox("Eat food");
	private JCheckBox lootCheckBox = new JCheckBox("Loot");

	/* Panels */
	private JPanel panel = new JPanel();
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();

	/* Button */
	private JButton startButton = new JButton("Start");

	/* Border */
	private final Border lowered = BorderFactory
			.createEtchedBorder(EtchedBorder.LOWERED);
	private final Border titledBorder = BorderFactory.createTitledBorder(
			lowered, "Food");
	private final Border lootBorder = BorderFactory.createTitledBorder(lowered,
			"Loot");

	public Gui(ClientContext ctx) {
		super(ctx);
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("QCow Killer");
		frame.setLocation(100, 100);
		frame.setSize(306, 260);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		frame.add(panel);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createHorizontalGlue());

		panel.add(foodCheckBox);
		panel.add(lootCheckBox);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(startButton);

		/* Titled border */
		panel1.setMaximumSize(new Dimension(500, 70));
		panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel1.setBorder(titledBorder);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));

		panel2.setMaximumSize(new Dimension(500, 70));
		panel2.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel2.setBorder(lootBorder);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

		/* Button */
		startButton.setMaximumSize(new Dimension(500, 25));

		/* food panel */
		panel1.add(Box.createHorizontalGlue());
		panel1.add(Box.createRigidArea(new Dimension(10, 0)));
		panel1.add(selectPercentLabel);
		panel1.add(Box.createRigidArea(new Dimension(10, 0)));
		panel1.add(spinner);
		panel1.add(Box.createRigidArea(new Dimension(2, 0)));
		panel1.add(percentLabel);

		// Spinner
		spinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		spinner.setMaximumSize(new Dimension(200, 20));

		/* loot panel */
		panel2.add(Box.createHorizontalGlue());
		panel2.add(lootLabel);
		panel2.add(Box.createRigidArea(new Dimension(50, 0)));
		panel2.add(lootComboBox);

		// loot combobox
		lootComboBox.setPreferredSize(new Dimension(100, 25));

		/* Menu */
		frame.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		fileMenu.add(exitMenuItem);
		menuBar.add(helpMenu);
		helpMenu.add(eatingMenuItem);
		helpMenu.add(lootingMenuItem);
		helpMenu.add(troublesMenuItem);
		menuBar.add(mnAbout);
		mnAbout.add(aboutMenuItem);

		/* Action listeners */
		aboutMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Made by Qosmiof2\n"
						+ "Released on x.x.x\n"
						+ "For further information contact me. \n"
						+ "Skype: Qosmiof2\n" + "Send me a PM. \n", "About",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});

		foodCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (spinner.isEnabled()) {
					spinner.setEnabled(false);
					selectPercentLabel.setEnabled(false);
					percentLabel.setEnabled(false);
				} else {
					spinner.setEnabled(true);
					selectPercentLabel.setEnabled(true);
					percentLabel.setEnabled(true);
				}

			}

		});

		lootCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lootComboBox.isEnabled()) {
					lootComboBox.setEnabled(false);
					lootLabel.setEnabled(false);
				} else {
					lootComboBox.setEnabled(true);
					lootLabel.setEnabled(true);
				}

			}

		});

		eatingMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame,
						"Script will eat any food you have in backpack. \n"
								+ "(cooked ofcourse)", "Help",
						JOptionPane.INFORMATION_MESSAGE);

			}

		});

		lootingMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame,
						"Select what item you want script to loot. \n"
								+ "multiple items coming soon!", "Help",
						JOptionPane.INFORMATION_MESSAGE);

			}

		});

		troublesMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame,
						"Are you experiencing any troubles? \n"
								+ "- Yes? Contact me or post on my thread.\n"
								+ "Contact info is in ABOUT tab. ", "Help",
						JOptionPane.INFORMATION_MESSAGE);

			}

		});

		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}

		});

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (lootCheckBox.isSelected()) {
					QCowKiller.nodeList.add(new Loot(ctx, Gui.this));
				}
				if (foodCheckBox.isSelected()) {
					QCowKiller.nodeList.add(new Eat(ctx, Gui.this));
				}
				QCowKiller.nodeList.add(new Attack(ctx));
			}

		});

		/* Focus */
		foodCheckBox.setFocusable(false);
		lootCheckBox.setFocusable(false);
		lootComboBox.setFocusable(false);
		startButton.setFocusable(false);
		spinner.setFocusable(false);

		/* Enabling */
		lootComboBox.setEnabled(false);
		lootLabel.setEnabled(false);
		selectPercentLabel.setEnabled(false);
		spinner.setEnabled(false);
		percentLabel.setEnabled(false);

	}

	public double getPercent() {
		double percent = (double) spinner.getValue();
		return percent;
	}

	public String getName() {
		String name = lootComboBox.getSelectedItem().toString();
		return name;
	}
}
