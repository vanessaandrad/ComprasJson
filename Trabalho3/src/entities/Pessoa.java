package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pessoa {
	protected String tipo;

	public Pessoa(String tipo) {
		this.tipo = tipo;
	}
	
	public static void visualizarProdutos() {
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\vanes\\git\\Trabalho-Compras-JSON\\3. TRABALHO POO - COMPRAS\\Estoque\\estoque.json"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void comprarProduto() {
		//nao pode comprar mais do que tiver no estoque
	}
}