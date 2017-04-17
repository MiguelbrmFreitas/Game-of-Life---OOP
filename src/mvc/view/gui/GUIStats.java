package mvc.view.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mvc.controller.GameController;
import observer.Observer;

/**
 * Atualiza as estatísticas na tela da GUI
 * 
 * @autor Miguel
 */


@SuppressWarnings("serial")
public class GUIStats extends JPanel implements Observer {
	
	private GameController controller;
	private JLabel text;
	
	public GUIStats(GameController controller) {
		super();
		this.controller = controller;
		text = new JLabel(controller.getStatistics());
		text.setVisible(false);
		add(text);
		setVisible(true);
	}

	@Override
	public void update() {
		text.setText(controller.getStatistics());
	}
	
}