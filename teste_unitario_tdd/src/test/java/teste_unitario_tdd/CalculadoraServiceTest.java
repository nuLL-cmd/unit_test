package teste_unitario_tdd;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import teste_unitario_tdd.exception.ExceptionDivisaoPorZero;
import teste_unitario_tdd.service.CalculadoraService;


/*
 * Classe de testes para exemplificar o tdd (Test drive develepment) que é uma prática dos testes unitários.
 * aplicada a uma calculadora simples
 * 
 * Obss: não sera implementado todas as quatro operações - É apenas para exemplificar.
 * */

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CalculadoraServiceTest {
	
	private CalculadoraService calculadoraService;
	
	@Before
	public void init() {
		
		calculadoraService = new CalculadoraService();
	}

	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void t1_testeSomaCalculadora() {

		// 1 - cenário

		int a = 3;
		int b = 5;

		// 2 - ação

		int result = calculadoraService.testeSomaCalculadora(a, b);

		Assert.assertEquals(8, result);

		// 3 - verificação

	}

	@Test
	public void t2_testeSubtraiCalculadora() {

		// 1 - cenário

		int a = 8;
		int b = 5;


		// 2 - ação

		int result = calculadoraService.testeSubtracaoCalculadora(a, b);

		Assert.assertEquals(3, result);

		// 3 - verificação

	}

	@Test
	public void t3_testeMultiplicaCalculadora() {

		// 1 - cenário

		int a = 8;
		int b = 5;

		// 2 - ação

		int result = calculadoraService.testeMultiplicaCalculadora(a, b);

		Assert.assertEquals(40, result);

		// 3 - verificação

	}

	@Test
	public void t3_testeDivideCalculadora() throws ExceptionDivisaoPorZero {

		// 1 - cenário

		int a = 10;
		int b = 2;


		// 2 - ação

		int result = calculadoraService.testeDivideCalculadora(a, b);

		Assert.assertEquals(5, result);

		// 3 - verificação

	}

	/*
	 * Exception usando expected na anotação @Test
	 * 
	 */

	@Test(expected = ExceptionDivisaoPorZero.class)
	public void t4_testeExceptionDivisaoPorZero() throws ExceptionDivisaoPorZero {

		// 1 - cenário

		int a = 8;
		int b = 0;


		// 2 - ação

		int result = calculadoraService.testeDivideCalculadora(a, b);

	}

	/*
	 * Exception usando try catch e o ErrorCollector
	 * 
	 */

	@Test
	public void t5_testeExceptionDivisaoPorZeroComErrorCollector() {

		// 1 - cenário

		int a = 8;
		int b = 0;

		// 2 - ação

		try {

			calculadoraService.testeDivideCalculadora(a, b);
			Assert.fail("Deveria ter lançado exceção aqui - divisão por zero!");
		} catch (ExceptionDivisaoPorZero e) {

			errorCollector.checkThat(e.getMessage(), equalTo("Impossível dividir por zero"));
		}

	}

	/*
	 * Exception usando try catch e o ErrorCollector
	 * 
	 */

	@Test
	public void t6_testeExceptionDivisaoPorZeroComExpectedException() throws ExceptionDivisaoPorZero {

		// 1 - cenário

		int a = 8;
		int b = 0;


		// 2 - ação

		expectedException.expect(ExceptionDivisaoPorZero.class);
		expectedException.expectMessage("Impossível dividir por zero");

		calculadoraService.testeDivideCalculadora(a, b);

	}

}
