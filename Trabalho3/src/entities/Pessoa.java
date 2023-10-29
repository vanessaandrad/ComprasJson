package entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Pessoa {
	protected String tipo;

	public Pessoa(String tipo) {
		this.tipo = tipo;
	}

	public static void visualizarProdutos() {
		String caminhoArquivoProdutos = "C:\\Users\\vanes\\git\\Trabalho-Compras-JSON\\3. TRABALHO POO - COMPRAS\\estoque.json";

		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoProdutos))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				System.out.println(linha);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}