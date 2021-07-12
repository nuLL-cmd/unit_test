package teste_unitario_tdd.service;

import teste_unitario_tdd.exception.ExceptionDivisaoPorZero;

public class CalculadoraService {
	

	
	public int testeSomaCalculadora(int a, int b) {
		
		return a+b;
	}

	public int testeSubtracaoCalculadora(int a, int b) {
		
		return a-b;
	}

	public int testeMultiplicaCalculadora(int a, int b) {
	
		return a*b;
	}

	public int testeDivideCalculadora(int a, int b) throws ExceptionDivisaoPorZero {
		if (b == 0) {
			throw new ExceptionDivisaoPorZero("Impossível dividir por zero");
		}
		return a / b;
	}

}
