package observer;

/**
 * Interface para uma classe cujo objetivo é notificar os Observers
 * Qualquer classe, se precisar, pode implementar um Subject
 * Padrão de Projeto Observer
 * @author Miguel
 *
 */
public interface Subject {

	/**
	 * Registra um novo observer
	 * @param observer
	 * 		observer a ser registrado
	 */
	public void register(Observer observer);
	
	/**
	 * Notifica todos os observers
	 */
	public void notifyObservers();
	
}
