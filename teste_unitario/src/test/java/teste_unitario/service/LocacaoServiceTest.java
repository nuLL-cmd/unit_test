package teste_unitario.service;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;


import teste_unitario.entity.Filme;
import teste_unitario.entity.Locacao;
import teste_unitario.entity.Usuario;
import teste_unitario.exception.FilmeSemEstoqueException;
import teste_unitario.exception.LocadoraException;
import teste_unitario.util.DataUtils;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();

	@Rule
	public ExpectedException exp = ExpectedException.none();
	
	
	/*
	 * Teste de validação de dados usando a classe ErrorCollector
	 * */
	@Test
	public void firstTest() throws FilmeSemEstoqueException, LocadoraException {

		// 1 - cenário

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Marco Aurelio");
		Filme filme = new Filme("De volta para o futuro", 2, 5.0);

		// 2 - ação

		Locacao locacao = service.alugarFilme(usuario, filme);

		// 3 - verificação

		/*
		 * Assert.assertEquals(5.00, locacao.getValor(), 0.01);
		 * Assert.assertTrue(locacao.getValor() == 5.00);
		 * Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new
		 * Date())); Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(),
		 * DataUtils.obterDataComDiferencaDias(1)));
		 */

		errorCollector.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
		errorCollector.checkThat("Esperado um valor true", DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
				CoreMatchers.is(true));
		errorCollector.checkThat("Esperado um valor true",
				DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				CoreMatchers.is(true));

	}

	// Tratamento de exceptions - Forma elegante
	// Colocando no expected a classe de exce??o esperada, voc? garante a
	// acertividade do teste quanto ao lan?amento de exceptions - teste feito para simular uma falha e o tratamento

	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacaoFilmeSemEstoque() throws FilmeSemEstoqueException, LocadoraException {

		// 1 - cenario

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Marco Aurelio");
		Filme filme = new Filme("De volta para o futuro", 0, 4.0);

		// 2 - ação

		Locacao locacao = service.alugarFilme(usuario, filme);


	}
	

	/* 
	 * Segunda forma - Te da um controle maior sobre o teste, o que a primeira forma
	 * No catch() será avaliado se a mensagem será a mesma da exception lançada, 
	 * o que vier depois depois da exception ainda sera executado
	 * Teste criado para simular uma exception.
	 * O SEU SUCESSO ESTA NA FALHA.
	 * 
	 */
	
	@Test
	public void testLocacaoFilmeSemEstoqueDois() {

		// 1 - cenario

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Eu sou o batman :D");
		Filme filme = new Filme("Filme do Pelé", 0, 4.0);

		// 2 - ação

		try {
			service.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter lançado uma exception");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

				// 3 - verificação
			
			Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme não tem estoque"));
		}
		
		System.out.println("Forma Robusta");
	}

	
	/*
	 * Teste exception de filme sem estoque usando agora a classe ExpectedException passando
	 * antes do metodo que pode lançar a exception, a classe de exception que é esperada, e a mensagem.
	 */
	
	@Test
	public void testLocacaoFilmeSemEstoqueTres() throws FilmeSemEstoqueException, LocadoraException {

		// cenario

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Marco Aurelio");
		Filme filme = new Filme("De volta para o futuro", 0, 5.0);

		// açao

		exp.expect(FilmeSemEstoqueException.class);
		exp.expectMessage("Filme não tem estoque");
		
		service.alugarFilme(usuario, filme);
		
	}
	
	
	/*
	 * Teste exception de usuario vazio usando
	 *  o ErrorCollector dentro de um try catch
	 */
	@Test
	public void testLocacaoUserVazioErrorCollector() throws FilmeSemEstoqueException{
		
		// 1 - cenario
		
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme exemplo",2,4.0);
		
		try {
			
			// 2- ação
			service.alugarFilme(null, filme);
			Assert.fail("Deveria lançar uma exception aqui!");
			
			// 3 - validação
		}catch (LocadoraException e) {
			e.printStackTrace();
			errorCollector.checkThat(e.getMessage(), CoreMatchers.is(CoreMatchers.equalTo("Usuario vazio")));
		}
		
	}
	
	/*
	 * Teste exception de usuario vazio usando
	 *  o Assert. assertThat dentro de um try catch
	 */
	@Test
	public void testLocacaoUserVazioAssert() throws FilmeSemEstoqueException{
		
		// 1 - cenario
		
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme exemplo",2,4.0);
		
		try {
			
			// 2- ação
			service.alugarFilme(null, filme);
			Assert.fail("Deveria lançar uma exception aqui!");
			
			// 3 - validação
		}catch (LocadoraException e) {
			e.printStackTrace();
			Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuario vazio"));
		}
		
	}
	
	/*
	 * Teste exception de usuario vazio usando
	 *  o ExpectedException.
	 */
	
	@Test
	public void testFilmeVazioExpectedException() throws FilmeSemEstoqueException, LocadoraException {
		
		// 1 - cenario

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Eu sou o batman :D");
		
		/* 2 - ação / 3 - verificação */
		
		exp.expect(LocadoraException.class);
		exp.expectMessage("Filme vazio");
		
		service.alugarFilme(usuario, null);
	}
}