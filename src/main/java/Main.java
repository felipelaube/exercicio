import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.com.exercicioGft.controller.ExercicioController;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			ExercicioController exercicio = new ExercicioController();
			System.out.println("Entrada:");
			String output = exercicio.validaInput(reader.readLine());

			System.out.println("Saída: " + output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
