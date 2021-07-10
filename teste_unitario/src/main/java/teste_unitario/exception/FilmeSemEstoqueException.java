package teste_unitario.exception;

public class FilmeSemEstoqueException extends Exception {

	/**
	 * Classe de exception personalizada para filme sem estoque.
	 */
	private static final long serialVersionUID = 1L;

	
	public FilmeSemEstoqueException(String message) {
		super(message);
	}
}
