package br.dev.weslei.tarefas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.dev.weslei.tarefas.model.Funcionario;
import br.dev.weslei.tarefas.model.Tarefa;

public class Main {
	
	// Detererminando o caminho do arquivo que será lido e gravado
	private static String path = "C:\\Users\\25133277\\Tarefa\\Tarefas.txt";

	public static void main(String[] args) {
		
		Tarefa t = new Tarefa("Pagar o fornecedor");
		
		gravarArquivo();
		lerArquivo();

	}
	
	public static void gravarArquivo() {
		FileWriter file = null;
		BufferedWriter writer = null;
		
		try {
			
			file = new FileWriter(path, true);
			writer = new BufferedWriter(file);
			
			writer.write("Essa é a última linha, por enquanto!!!\n");
			writer.flush();
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
		}

	}

	private static void lerArquivo() {
		
		// Abrir o arquivo para leitura
		FileReader file = null;
		BufferedReader buffer = null;
		try {
			file = new FileReader(path);
			buffer = new BufferedReader(file);
			
			String line = buffer.readLine();
			while(line !=null) {
				System.out.println(line);
				line = buffer.readLine();
			}
			
			
		} catch (FileNotFoundException erro) {
			System.out.println("Arquivo não encontrado!");
			System.out.println(erro.getMessage());
		} catch (IOException erro) {
			System.out.println("Voçê não pode ler o arquivo!");
			System.out.println(erro.getMessage());
		} catch (Exception erro) {
			System.out.println("Erro genérico!");
			System.out.println(erro.getMessage());
		}
	}

}
