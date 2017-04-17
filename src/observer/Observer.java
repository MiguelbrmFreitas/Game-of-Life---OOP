package observer;

/**
 * Interface (classe abstrata) para definir um observador
 * Qualquer classe, se precisar, pode implementar um observador
 * Define um �nico m�todo de update(), que pode ser chamado depois de ser notificado pelo Subject
 * Padr�o de Projeto Observer
 * @author Miguel
 *
 */
public interface Observer {
	
	/**
	 * Atualiza o objeto que implementar o observer
	 */
	public void update();
	
}
