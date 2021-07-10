package teste_unitario.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static teste_unitario.util.DataUtils.adicionarDias;

import teste_unitario.entity.Filme;
import teste_unitario.entity.Locacao;
import teste_unitario.entity.Usuario;
import teste_unitario.exception.FilmeSemEstoqueException;
import teste_unitario.exception.LocadoraException;
import teste_unitario.util.DataUtils;

@SuppressWarnings("unused")
public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
		
		Double valorTotal = 0.0;

		if (filmes != null) {

			if (filmes.size() != 0) {
				
				for (Filme filme : filmes) {
					if (filme.getEstoque() == 0)
						throw new FilmeSemEstoqueException("Filme não tem estoque");

					if (filme.getPrecoLocacao() != 5.0) 
						throw new LocadoraException("Preço do filme esta diferente do cadastrado");
					else 	
						valorTotal += filme.getPrecoLocacao();
				}
				
			}else
				
				throw new LocadoraException("Lista de filmes não pode estar vazia");
			
		} else

			throw new LocadoraException("Lista de filmes não pode estar vazia");
		


		if (usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		locacao.setValor(valorTotal);

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar

		return locacao;
	}

	public static void main(String[] args) throws Exception {

		// cenario

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Marco Aurelio");
		
		List<Filme> filmes = new ArrayList<>();
		
		filmes.add(new Filme("De volta para o futuro", 2, 5.0));
		filmes.add(new Filme("Batman do futuro", 2, 5.0));

		// a��o

		Locacao locacao = service.alugarFilme(usuario, filmes);

		// verifica��o
		System.out.println(locacao.getValor() == 5.00);
		System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

	}
}