package mvc.view.gui;

import javax.swing.JButton;

@SuppressWarnings("serial")
/**
 * Define o comportamento de uma célula
 * @author Miguel
 *
 */
public class GUICell extends JButton {
	public int i, j;
	
	public GUICell(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
