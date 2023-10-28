package entities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Administrador extends Pessoa {

	public Administrador(String tipo) {
		super(tipo);
	}

	public static void adicionarProduto() {
		Scanner scan = new Scanner(System.in);

		System.out.print("Digite o id do produto: ");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.print("Digite o nome do produto: ");
		String nome = scan.nextLine();
		System.out.print("Digite a descrição do produto: ");
		String descricao = scan.nextLine();
		System.out.print("Digite o preço do produto: ");
		double preco = scan.nextDouble();
		System.out.print("Digite a quantidade do produto: ");
		int quantidade = scan.nextInt();

		Produto produto = new Produto(id, nome, descricao, preco, quantidade);
		JSONObject produtoJSON = new JSONObject(produto);

		JSONArray listaProdutosJSON;

		File file = new File("C:\\Users\\vanes\\git\\Trabalho-Compras-JSON\\3. TRABALHO POO - COMPRAS\\estoque.json");

		if (file.exists()) {
			String conteudoArquivo = lerArquivoProdutos(file);
			listaProdutosJSON = new JSONArray(conteudoArquivo);
		} else {
			listaProdutosJSON = new JSONArray();
		}

		listaProdutosJSON.put(produtoJSON);

		try (FileWriter writer = new FileWriter(file)) {
			writer.write(listaProdutosJSON.toString(4));
			System.out.println("Produto adicionado com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String lerArquivoProdutos(File file) {
		try (FileReader reader = new FileReader(file)) {
			StringBuilder dadosJSON = new StringBuilder();
			int character;
			while ((character = reader.read()) != -1) {
				dadosJSON.append((char) character);
			}
			return dadosJSON.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "[]";
		}
	}

	public static void editarProduto() {
		Scanner scan = new Scanner(System.in);
		File file = new File("C:\\Users\\vanes\\git\\Trabalho-Compras-JSON\\3. TRABALHO POO - COMPRAS\\estoque.json");

		String conteudoArquivoJsonProdutos = lerArquivoProdutos(file);
		JSONArray listaProdutosJSON = new JSONArray(conteudoArquivoJsonProdutos);
		Pessoa.visualizarProdutos();
		
		System.out.print("Digite o nome do produto que deseja editar: ");
		String nomeProdutoParaEditar = scan.nextLine();
		for (int i = 0; i < listaProdutosJSON.length(); i++) {
			JSONObject produtoJSON = listaProdutosJSON.getJSONObject(i);
			String nome = produtoJSON.getString("nome");
			
			if(nome.equalsIgnoreCase(nomeProdutoParaEditar)) {
				System.out.print("Digite o novo ID do produto: ");
				int id = scan.nextInt();
				produtoJSON.put("id", id);
				scan.nextLine();
				
				System.out.print("Digite a nova descrição do produto: ");
				String descricao = scan.nextLine();
				produtoJSON.put("descricao", descricao);
				
				System.out.print("Digite o novo preço do produto: ");
				double preco = scan.nextDouble();
				produtoJSON.put("preco", preco);
				
				System.out.print("Digite a nova quantidade do produto: ");
				int quantidade = scan.nextInt();
				produtoJSON.put("quantidade", quantidade);
				
				try(FileWriter writer = new FileWriter(file)){
					writer.write(listaProdutosJSON.toString(4));
					System.out.println("Produto editado com sucesso!");
				}catch(IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		System.out.println("Produto não encontrado!");
	}
}