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

	    File file = new File("C:\\Users\\vanes\\git\\Trabalho-Compras-JSON\\3. TRABALHO POO - COMPRAS\\Estoque\\estoque.json");

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
	    JSONArray listaProdutosJSON;
	    File file = new File("C:\\Users\\vanes\\git\\Trabalho-Compras-JSON\\3. TRABALHO POO - COMPRAS\\Estoque\\estoque.json");

	    System.out.println("Digite o nome do produto a ser editado:");
	    String nome = scan.nextLine();
	    if (file.exists()) {
	        String conteudoArquivo = lerArquivoProdutos(file);
	        listaProdutosJSON = new JSONArray(conteudoArquivo);

	        for (int i = 0; i < listaProdutosJSON.length(); i++) {
	            JSONObject produtoJSON = listaProdutosJSON.getJSONObject(i);
	            if (produtoJSON.getString("nome").equals(nome)) {
	            	System.out.print("Digite o novo id do produto: ");
	                int novoId = scan.nextInt();
	                scan.nextLine();
	                System.out.print("Digite o novo nome do produto: ");
	                String novoNome = scan.nextLine();
	                System.out.print("Digite a nova descrição do produto: ");
	                String novaDescricao = scan.nextLine();
	                System.out.print("Digite o novo preço do produto: ");
	                double novoPreco = scan.nextDouble();
	                System.out.print("Digite a nova quantidade do produto: ");
	                int novaQuantidade = scan.nextInt();

	                // Atualize os atributos do produtoJSON
	                produtoJSON.put("id", novoId);
	                produtoJSON.put("nome", novoNome);
	                produtoJSON.put("descricao", novaDescricao);
	                produtoJSON.put("preco", novoPreco);
	                produtoJSON.put("quantidade", novaQuantidade);

	                System.out.println("Produto editado com sucesso!");
	                }
	        }

	        try (FileWriter writer = new FileWriter(file)) {
	            writer.write(listaProdutosJSON.toString(4));
	            System.out.println("Produto editado com sucesso!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("Arquivo não encontrado.");
	    }
	}

	/*
	public static void editarProduto() {
		Scanner scan = new Scanner(System.in);
		File file = new File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\produtos\\produtos.json");
		
		String conteudoArquivoJsonProdutos = lerArquivoProdutos(file);
		
		System.out.print("Digite o nome do produto que deseja editar: ");
		String nome = scan.nextLine();
		for()
		
	}
	*/
}