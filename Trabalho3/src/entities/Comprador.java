package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Comprador extends Pessoa {

	public Comprador(String tipo) {
		super(tipo);
	}

	public static void comprarProduto() {
		Scanner scan = new Scanner(System.in);
		JSONArray carrinho = new JSONArray();

		System.out.print("Digite o ID do produto que deseja comprar: ");
		int idDesejado = scan.nextInt();
		System.out.print("Digite a quantidade desejada: ");
		int quantidadeDesejada = scan.nextInt();

		File estoqueFile = new File(
				"C:\\Users\\vanes\\git\\Trabalho-Compras-JSON\\3. TRABALHO POO - COMPRAS\\estoque.json");
		String dadosJSON = Administrador.lerArquivoProdutos(estoqueFile);

		JSONArray listaProdutosJSON = new JSONArray(dadosJSON);

		for (int i = 0; i < listaProdutosJSON.length(); i++) {
			JSONObject produtoJSON = listaProdutosJSON.getJSONObject(i);
			int idProduto = produtoJSON.getInt("id");
			int quantidadeDisponivel = produtoJSON.getInt("quantidade");

			if (idProduto == idDesejado) {
				if (quantidadeDesejada <= quantidadeDisponivel) {
					carrinho.put(produtoJSON);
					System.out.println("Produto adicionado ao carrinho de compras!");
				} else {
					System.out.println("Quantidade desejada maior do que a disponível.");
				}
			}
		}

		try (FileWriter writer = new FileWriter(
				new File("C:\\Users\\vanes\\git\\Trabalho-Compras-JSON\\3. TRABALHO POO - COMPRAS\\carrinho.json"))) {
			writer.write(carrinho.toString(4));
			System.out.println("Carrinho salvo com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}