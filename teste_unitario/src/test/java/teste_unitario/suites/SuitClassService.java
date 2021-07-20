package teste_unitario.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import teste_unitario.service.CalculoDescontoLocacaoTest;
import teste_unitario.service.LocacaoServiceTest;


/**
 * @author Marco Aurélio
 * @date 20/07/2021
 * 
 * Classe suite para os testes.
 * Esta classe informa para o JUnit que a mesma é uma suite de testes
 * ou seja, através dela todas as classes passadas como paramêtro serão executadas.
 * 
 * O ponot negativo desse modo de execução, é que esta classe também entra no escopo de teste,
 * ou seja, quando o deploy começar, todos os testes serão iniciados, e quando a vez do suite chegar
 * os testes dentro dele serão novamente executados, gerando uma duplicata de execuções de testes.
 * 
 */
//@RunWith(value = Suite.class)
@SuiteClasses(value = {
    CalculoDescontoLocacaoTest.class,
    LocacaoServiceTest.class
})
public class SuitClassService{

    //TODO NADA HA FAZER AQUI
}