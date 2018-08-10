import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.exercicioGft.controller.ExercicioController;

public class TestExercicioController {
	public ExercicioController exercicio = new ExercicioController();

	@Test
	public void testSucesso() throws Exception{
		String test = "noite,1,2,3,4";
		String result = exercicio.validaInput(test);
		
		assertEquals("carne, batata, vinho, bolo", result);
	}

	@Test
	public void testInputError() throws Exception{
		String test = "tarde,1,2,3,4";
		String result = exercicio.validaInput(test);
		
		assertEquals("Parâmetros período inválido, exemplo: manhã ou noite", result);
	}

	@Test
	public void testNumberFormatterError() throws Exception{
		String test = "manhã,a,2,3,4";
		String result = exercicio.validaInput(test);
		
		assertEquals("Por favor informe um número inteiro válido!", result);
	}
}
