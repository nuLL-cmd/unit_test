package teste_unitario.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import teste_unitario.entity.Filme;
import teste_unitario.entity.Locacao;
import teste_unitario.entity.Usuario;
import teste_unitario.exception.FilmeSemEstoqueException;
import teste_unitario.exception.LocadoraException;
import teste_unitario.util.DataUtils;

/*
 * A anotação @FixMethodOrder, recebe um parametro que sera usado para definir a ordem no qual os testes devem ser executados.
 * Porem se você usa o  Isolated (isolado) do prinicpio FIRST, onde cada metodo é indepndente e solado, nã há necessidade de usar
 * esta anotação. 
 * */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class LocacaoServiceTest {

	private LocacaoService service;
	private static int count;
	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();

	@Rule
	public ExpectedException exp = ExpectedException.none();

	/*
	 * A anotação @Before é usada para configurar execuções necessárias antes
	 * de cada teste, ou seja, se tiver 5 testes dentro da classe, o @Before sera executado antes de todos
	 * os 5 testes
	 * */
	@Before
	public void before() {	
		count ++;
		System.out.println("Iniciou o teste: "+count);
		service = new LocacaoService();

	}

	/*
	 * A anotação @After é usada para configurar execuções necessárias depois
	 * de cada teste, ou seja, se tiver 5 testes dentro da classe, o @After sera executado
	 * depois de cada teste
	 * */
	@After
	public void after() {
		System.out.println("Finalizou o teste: "+count);
	}
	
	
	/*
	 * A anotação @BeforeClass é usada para configurar execuções necessárias antes
	 * dos testes apneas uma vez, ou seja, se tiver 5 testes dentro da classe, o @BeforeClass sera executado antes de todos
	 * os 5 testes
	 * */
	@BeforeClass
	public static void beforeClass() {
		count = 0; 
		System.out.println("Iniciou os testes.");
		System.out.println("===========================");
		
	}
	
	/*
	 * A anotação @AfterClass é usada para configurar execuções necessárias para toda a classe
	 * apenas uma vez, ou seja, se tiver 5 testes dentro da classe, o @AfterClass sera executado
	 * ao final de todos os 5 testes
	 * */
	@AfterClass
	public static void AfterClass() {
		System.out.println("===========================");
		System.out.println("Total de testes executados: "+count);
		
	}
	

	/*
	 * Teste de validação de dados usando a classe ErrorCollector
	 */
	@Test
	public void t1_validateTests() throws FilmeSemEstoqueException, LocadoraException {

		// 1 - cenário

		Usuario usuario = new Usuario("Marco Aurelio");

		List<Filme> filmes = new ArrayList<>();
		filmes.add(new Filme("De volta para o futuro", 2, 5.0));
		filmes.add(new Filme("Batman do futuro", 2, 5.0));

		// 2 - ação

		Locacao locacao = service.alugarFilme(usuario, filmes);

		// 3 - verificação

		/*
		 * Assert.assertEquals(5.00, locacao.getValor(), 0.01);
		 * Assert.assertTrue(locacao.getValor() == 5.00);
		 * Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new
		 * Date())); Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(),
		 * DataUtils.obterDataComDiferencaDias(1)));
		 */

		errorCollector.checkThat(locacao.getValor(), CoreMatchers.is(5.0 * filmes.size()));
		errorCollector.checkThat("Esperado um valor true", DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
				CoreMatchers.is(true));
		errorCollector.checkThat("Esperado um valor true",
				DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				CoreMatchers.is(true));

	}

	 /*
	  * Tratamento de exceptions - Forma elegante
	  * Colocando no expected a classe de exce??o esperada, voc? garante a
	  * acertividade do teste quanto ao lan?amento de exceptions - teste feito para
	  * simular uma falha e o tratamento
	  * */

	@Test(expected = FilmeSemEstoqueException.class)
	public void t2_testLocacaoFilmeSemEstoque() throws FilmeSemEstoqueException, LocadoraException {

		// 1 - cenario

		Usuario usuario = new Usuario("Marco Aurelio");

		List<Filme> filmes = new ArrayList<>();
		filmes.add(new Filme("De volta para o futuro", 2, 5.0));
		filmes.add(new Filme("Batman do futuro", 0, 5.0));

		// 2 - ação

		Locacao locacao = service.alugarFilme(usuario, filmes);

	}

	/*
	 * Segunda forma - Te da um controle maior sobre o teste, o que a primeira forma
	 * No catch() será avaliado se a mensagem será a mesma da exception lançada, o
	 * que vier depois depois da exception ainda sera executado Teste criado para
	 * simular uma exception. O SEU SUCESSO ESTA NA FALHA.
	 * 
	 */

	@Test
	public void t3_testLocacaoFilmeSemEstoqueDois() {

		// 1 - cenário

		Usuario usuario = new Usuario("Eu sou o batman :D");
	

		List<Filme> filmes = new ArrayList<>();
		filmes.add(new Filme("De volta para o futuro", 2, 5.0));
		filmes.add(new Filme("Batman do futuro", 0, 5.0));

		// 2 - ação

		try {
			service.alugarFilme(usuario, filmes);
			Assert.fail("Deveria ter lançado uma exception");
		} catch (Exception e) {

			// 3 - verificação

			Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme não tem estoque"));
		}

	}

	/*
	 * Teste exception de filme sem estoque usando agora a classe ExpectedException
	 * passando antes do metodo que pode lançar a exception, a classe de exception
	 * que é esperada, e a mensagem.
	 */

	@Test
	public void t4_testLocacaoFilmeSemEstoqueTres() throws FilmeSemEstoqueException, LocadoraException {

		// cenario

		Usuario usuario = new Usuario("Marco Aurelio");
		
		List<Filme> filmes = new ArrayList<>();
		filmes.add(new Filme("De volta para o futuro", 2, 5.0));
		filmes.add(new Filme("Batman do futuro", 0, 5.0));
		// ação

		exp.expect(FilmeSemEstoqueException.class);
		exp.expectMessage("Filme não tem estoque");

		service.alugarFilme(usuario, filmes);

	}

	/*
	 * Teste exception de usuario vazio usando o ErrorCollector dentro de um try
	 * catch
	 */
	@Test
	public void t5_testLocacaoUserVazioErrorCollector() throws FilmeSemEstoqueException {

		// 1 - cenário

		List<Filme> filmes = new ArrayList<>();
		filmes.add(new Filme("De volta para o futuro", 2, 5.0));
		filmes.add(new Filme("Batman do futuro", 2, 5.0));

		try {

			// 2- ação
			service.alugarFilme(null, filmes);
			Assert.fail("Deveria lançar uma exception aqui!");

			// 3 - validação
		} catch (LocadoraException e) {
			
			errorCollector.checkThat(e.getMessage(), CoreMatchers.is(CoreMatchers.equalTo("Usuario vazio")));
		}

	}

	/*
	 * Teste exception de usuario vazio usando o Assert. assertThat dentro de um try
	 * catch
	 */
	@Test
	public void t6_testLocacaoUserVazioAssert() throws FilmeSemEstoqueException {

		// 1 - cenário

		List<Filme> filmes = new ArrayList<>();
		filmes.add(new Filme("De volta para o futuro", 2, 5.0));
		filmes.add(new Filme("Batman do futuro", 2, 5.0));

		try {

			// 2- ação
			service.alugarFilme(null, filmes);
			Assert.fail("Deveria lançar uma exception aqui!");

			// 3 - validação
		} catch (LocadoraException e) {
			
			Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuario vazio"));
		}

	}

	/*
	 * Teste exception de usuario vazio usando o ExpectedException.
	 */

	@Test
	public void t7_testFilmeVazioExpectedException() throws FilmeSemEstoqueException, LocadoraException {

		// 1 - cenário

		Usuario usuario = new Usuario("Eu sou o batman :D");

		/* 2 - ação / 3 - verificação */

		exp.expect(LocadoraException.class);
		exp.expectMessage("Lista de filmes não pode estar vazia");

		service.alugarFilme(usuario, null);
	}
}