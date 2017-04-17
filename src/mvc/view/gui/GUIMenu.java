package mvc.view.gui;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import mvc.controller.GameController;
import mvc.model.InvalidStrategyException;
import mvc.model.Strategies;

/**
 * Define o comportamento e a implementação de um Menu
 * 
 * @author Miguel
 *
 */

@SuppressWarnings("serial")
public class GUIMenu extends JPanel implements ActionListener {

	private GameController controller;
	
	private String[] strategies = { "Conway", "High Life", "Seeds", "Day Night", "Life Without Death", "Bolha" };
	
	private JButton nextGen;
	private JButton animate;
	private JComboBox<String> strategyBox;
	private JButton statistics;
	private JButton killEveryone;
	private JButton exit;
	
	private Timer timer; // Timer para eviter o bloqueio do Event Dispatch Thread durante a animacao
	private int displayedGenerations, generations;
	
	public GUIMenu(GameController controller) {
		super();
		this.controller = controller;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
		setNextGenButton();
		setStrategyBox();
		setAnimationButton();
		setStatisticsButton();
		setKillAllButton();
		setExitButton();
	}
	
	private void setNextGenButton(){
		nextGen = new JButton("Next generation");
		nextGen.addActionListener(this);		
		add(nextGen);
	}
	
	private void setStrategyBox(){
		strategyBox = new JComboBox<String>(strategies);
		strategyBox.addActionListener(this);
		add(strategyBox);
	}

	
	private void setAnimationButton(){		
		animate = new JButton("Animate");
		animate.addActionListener(this);
		add(animate);
	}
	
	private void setStatisticsButton(){
		statistics = new JButton("Statistics");
		statistics.addActionListener(this);
		add(statistics);
	}
	
	private void setKillAllButton(){
		killEveryone = new JButton("Kill Them All!");
		killEveryone.addActionListener(this);
		add(killEveryone);
	}
	
	private void setExitButton(){
		exit = new JButton("Exit");
		exit.addActionListener(this);
		add(exit);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if ( event.getSource() == nextGen ) {
			nextGeneration();
		} else if ( event.getSource() == strategyBox ) {
			changeStrategy();
		} else if ( event.getSource() == animate ) {
			animate();
		} else if ( event.getSource() == statistics ) {
			displayStatistics();
		} else if ( event.getSource() == killEveryone ) {
			killAllCells();
		} else if ( event.getSource() == exit ) {
			exit();
		} else if ( event.getSource() == timer ) {
			animationTimer();
		}
	}

	public void animationTimer() {
		nextGeneration();
		displayedGenerations++;
		if ((displayedGenerations == generations) || (controller.numberOfAliveCells() == 0)) {
			if (controller.numberOfAliveCells() == 0) {
				JOptionPane.showMessageDialog(null, "Sua populacao morreu!");
			}
			timer.stop();
		}
	}

	public void killAllCells() {
		controller.killAllCells();
	}

	public void displayStatistics() {
		JOptionPane.showMessageDialog(null, controller.getStatistics());
	}

	public void animate() {
		try {
			generations = Integer.parseInt(JOptionPane.showInputDialog("Quantas gerações para calcular? "));
			displayedGenerations = 0;
			timer = new Timer(200, this);
			timer.setRepeats(true);
			timer.start();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Você deve escolher um número válido de gerações.");
			animate();
		}
	}

	public void nextGeneration() {
		controller.nextGeneration();
	}

	public void changeStrategy() {
		int strategy = strategyBox.getSelectedIndex() + 1;
		try {
			controller.changeStrategy(Strategies.getStrategy(strategy));
		} catch (InvalidStrategyException e) {
			JOptionPane.showMessageDialog(null, "Estrategia invalida!");
		}
	}
	
	public void exit() {
		controller.exit();
	}
	
}
 