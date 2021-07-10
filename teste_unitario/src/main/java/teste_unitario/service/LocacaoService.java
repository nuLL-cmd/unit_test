package teste_unitario.service;



import java.util.Date;
import static teste_unitario.util.DataUtils.adicionarDias;

import teste_unitario.entity.Filme;
import teste_unitario.entity.Locacao;
import teste_unitario.entity.Usuario;
import teste_unitario.exception.FilmeSemEstoqueException;
import teste_unitario.exception.LocadoraException;
import teste_unitario.util.DataUtils;


@SuppressWarnings("unused")
public class LocacaoService {
	

	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {


		if(usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if(filme == null) {
			throw new LocadoraException("Filme vazio");
		}
		
		if(filme.getEstoque() == 0) {
			throw new FilmeSemEstoqueException("Filme não tem estoque");
		}
		
		
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	public static void main(String[] args) throws Exception {
		
		//cenario 
		
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Marco Aurelio");
		Filme filme = new Filme("De volta para o futuro",2,5.0);
		
		//a��o
		
		Locacao locacao= service.alugarFilme(usuario, filme);
		
		//verifica��o
		System.out.println(locacao.getValor() == 5.00);
		System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(),DataUtils.obterDataComDiferencaDias(1)));
		
	}
}