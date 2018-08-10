package br.com.exercicioGft.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.exercicioGft.entities.Refeicao;

public class ExercicioController {

	public static ArrayList<Refeicao> objetos = new ArrayList<Refeicao>();

	public boolean initObject() {
		String[] manha = { "ovos", "torrada", "café", "" };
		String[] noite = { "carne", "batata", "vinho", "bolo" };

		Refeicao ref;
		for (int i = 0; i < manha.length; i++) {
			ref = new Refeicao();
			ref.setPeriodo(1); // manhã
			ref.setTipo_prato(i + 1);
			ref.setPrato(manha[i]);
			objetos.add(ref);

		}

		for (int i = 0; i < noite.length; i++) {
			ref = new Refeicao();
			ref.setPeriodo(2); // noite
			ref.setTipo_prato(i + 1);
			ref.setPrato(noite[i]);
			objetos.add(ref);

		}

		return true;

	}

	public String validaInput(String dados) throws Exception {
		try {
			initObject(); // Criando a lista que vai ser utilizada para comparação;

			int periodo = 0;
			String output = "";

			if (!dados.isEmpty()) {
				String[] input = dados.trim().split(",");
				/* Inicio validações */
				if (input[0].toUpperCase().equals("MANHÃ")) {
					periodo = 1;
				} else if (input[0].toUpperCase().equals("NOITE")) {
					periodo = 2;
				} else {
					return "Parâmetros período inválido, exemplo: manhã ou noite";
				}

				int[] itensOrdenados = new int[input.length - 1]; // Variavel para pegar os itens selecionados

				for (int c = 1; c < input.length; c++) {
					try {

						itensOrdenados[c - 1] = Integer.parseInt(input[c]);
					} catch (NumberFormatException e) {
						return "Por favor informe um número inteiro válido!";
					}
				}

				if (itensOrdenados.length == 0) {
					return "É preciso informar pelo menos um item!";

				}
				/* Fim validações */

				Arrays.sort(itensOrdenados);

				for (int in = 0; in < itensOrdenados.length; in++) {
					boolean encontrou = false;
					for (Refeicao refeicao : objetos) {
						if (refeicao.getPeriodo() == periodo) {
							if (itensOrdenados[in] == refeicao.getTipo_prato()) {
								if (!refeicao.getPrato().equals("")) {

									if (!output.contains(refeicao.getPrato())
											|| ((periodo == 1 && refeicao.getTipo_prato() == 3) // Se for manhã e Café
																								// permite repetir
													|| (periodo == 2 && refeicao.getTipo_prato() == 2))) { // Se for
																											// noite e
																											// batata
																											// pode
																											// repetir
										if (output.isEmpty()) {
											output += refeicao.getPrato();
										} else {
											output += "," + refeicao.getPrato();
										}
									}
									encontrou = true;
									break;
								} else {
									break;

								}
							}

						}

					}

					if (!encontrou) {
						if (output.isEmpty()) {
							output += "erro";
						} else {
							if(!output.contains("erro")) {
								output += ",erro";
								
							}
						}

					}

				}
			}

			return formataRetorno(output, periodo);
		} catch (Exception e) {
			throw e;
		}
	}

	public String formataRetorno(String texto, int periodo) { // Função criada para tratar a mensagem de retorno
																// com as repetições.
		Map<String, Integer> cont = new LinkedHashMap<String, Integer>();

		String retorno = "";
		for (String nome : texto.split(",")) {
			if (!cont.containsKey(nome))
				cont.put(nome, 0);
			cont.put(nome, cont.get(nome) + 1);
		}

		for (Map.Entry<String, Integer> entry : cont.entrySet()) {
			if (entry.getValue() > 1) {
				if (retorno.isEmpty()) {
					retorno += entry.getKey() + "(x" + entry.getValue() + ")";
				} else {
					retorno += ", " + entry.getKey() + "(x" + entry.getValue() + ")";
				}
			} else {
				if (retorno.isEmpty()) {
					retorno += entry.getKey();
				} else {
					retorno += ", " + entry.getKey();
				}

			}
		}
		return retorno;
	}
}
