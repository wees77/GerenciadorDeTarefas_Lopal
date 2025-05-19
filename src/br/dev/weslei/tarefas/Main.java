package br.dev.weslei.tarefas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.dev.weslei.tarefas.dao.FuncionarioDAO;
import br.dev.weslei.tarefas.model.Funcionario;
import br.dev.weslei.tarefas.model.Tarefa;

public class Main {
	
	private static String path = "C:\\Users\\25133277\\Tarefa\\Tarefas.txt";

	public static void main(String[] args) {
		
		List<String> frutas = new ArrayList<>();
		List<Funcionario> funcionarios = new ArrayList<>();
		List<Double> numeros = new ArrayList<>();
		
		frutas.add("Pera");
		frutas.add("Melância");
		frutas.add("Banana da terra");
		frutas.add("Mamão papaya");
		frutas.add("Figo");
		
		numeros.add(5.5);
		numeros.add(6.6);
		
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCodigo(4);
		funcionario.setNome("Bucho de Cadela Prenha");
		funcionario.setMatricula("909090");
		funcionario.setEmail("buchodecadela@gmail.com");
		
		Funcionario funcionario2 = new Funcionario();
		funcionario2.setCodigo(5);
		funcionario2.setNome("Minamino");
		funcionario2.setMatricula("1010101");
		funcionario2.setEmail("minamino@gmail.com");
		
		funcionarios.addAll(List.of(funcionario, funcionario2));
		
		
		for (Funcionario f : funcionarios) {
			System.out.println(f.getNome() + " - " + f.getEmail());
		}

//		FuncionarioDAO dao =new FuncionarioDAO(funcionario);
//		dao.gravar();
//		
//		System.out.println(funcionario.toString());
		
		

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
