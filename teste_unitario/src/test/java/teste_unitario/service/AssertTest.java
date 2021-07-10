package teste_unitario.service;

import static org.junit.Assert.assertSame;

import org.junit.Assert;
import org.junit.Test;

import teste_unitario.entity.Usuario;

public class AssertTest {

	/*
	 * Classe de teste para os metodos da classe Assert do JUnit
	 * 
	 * */
	@Test
	public void test() {
		 
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals("Erro de comparação: ",1,2);
		Assert.assertTrue("value".equalsIgnoreCase("Value"));
		Assert.assertEquals(0.51, 0.51,0.01);
		Assert.assertEquals(Math.PI, 3.14,0.01);
		
		int i = 5;
		Integer i2 = 5;
		
		Assert.assertEquals(Integer.valueOf(i),i2);
		Assert.assertEquals(i2.intValue(), i);
		
		Usuario u1= new Usuario("Marco Aurelio");
		Usuario u2 = new Usuario("Marco Aurelio");
		Usuario u3 = new Usuario("Fernando capato");
		Usuario u4 = u3;
		Usuario u5 = null;
		
		
		
		Assert.assertNull(u5);
		Assert.assertEquals(u1,u2);
		Assert.assertSame(u4, u3);
		Assert.assertNotNull(u3);
	} 
}
