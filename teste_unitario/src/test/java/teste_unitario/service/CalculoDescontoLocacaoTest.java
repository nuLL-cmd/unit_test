package teste_unitario.service;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import teste_unitario.entity.Filme;
import teste_unitario.entity.Locacao;
import teste_unitario.entity.Usuario;
import teste_unitario.exception.FilmeSemEstoqueException;
import teste_unitario.exception.LocadoraException;

@RunWith(Parameterized.class)
public class CalculoDescontoLocacaoTest {

	private LocacaoService service;

	private static String nameClass = "CalculoDescontoLocacaoTest.java";
	
	private static int count;
	
	private static Filme filme1 = new Filme("De volta para o futuro", 2, 5.0);
	private static Filme filme2 = new Filme("De volta para o futuro dois", 2, 5.0);
	private static Filme filme3 = new Filme("De volta para o futuro três", 2, 5.0);
	private static Filme filme4 = new Filme("De volta para o futuro quatro", 2, 5.0);
	private static Filme filme5 = new Filme("De volta para o futuro cinco", 2, 5.0);
	private static Filme filme6 = new Filme("De volta para o futuro seis", 2, 5.0);

	@Parameter(value = 0)
	public List<Filme> filmes;

	@Parameter(value = 1)
	public Double valorLocacao;

	@Parameter(value = 2)
	public String title;

	@Before
	public void before() {
		count++;
		System.out.println("Iniciou o teste: "+count);
		service = new LocacaoService();
	}

	@After
	public void after() {
		System.out.println("Finalizoui o teste: "+count);
		
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("===========================");
		System.out.println("Total de testes executados: " + count);
		System.out.println("");

	}

	
	
	@BeforeClass
	public static void beforeClass() {
		count = 0;
		System.out.println("Iniciou os testes na classe: "+nameClass);
		System.out.println("===========================");
		
	}

	@Parameters(name = "Teste - {2}")
	public static Collection<Object[]> getParametros() {

		return Arrays.asList(new Object[][] {
				{ Arrays.asList(filme1, filme2, filme3), 13.75, "3 filmes - 25% de desconto" },
				{ Arrays.asList(filme1, filme2, filme3, filme4), 16.25, "4 filmes - 50% de desconto" },
				{ Arrays.asList(filme1, filme2, filme3, filme4, filme5), 17.50, "5 filmes - 75% de desconto" },
				{ Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 17.50, "6 filmes - 100% de desconto" },

		});

	}

	@Test

	public void t1_deveCalcularValorLocacaoComDescontos() throws FilmeSemEstoqueException, LocadoraException {

		// 1 - cenário

		Usuario usuario = new Usuario("Marco Aurélio");

		// 2 - ação

		Locacao locacao = service.alugarFilme(usuario, filmes);

		// 3 - verificação

		MatcherAssert.assertThat(locacao.getValor(), is(valorLocacao));

	}

}
