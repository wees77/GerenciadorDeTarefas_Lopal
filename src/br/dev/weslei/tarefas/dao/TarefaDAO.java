package br.dev.weslei.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.dev.weslei.tarefas.factory.FileFactory;
import br.dev.weslei.tarefas.factory.FileFactoryTarefa;
import br.dev.weslei.tarefas.model.Funcionario;
import br.dev.weslei.tarefas.model.Tarefa;

public class TarefaDAO {
	private Tarefa tarefa;
	private FileFactoryTarefa ff = new FileFactoryTarefa();
	
	// Metodo construtor
	public TarefaDAO (Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	public void gravar() {
		
		try {
			BufferedWriter bw = ff.getBufferedWriter();
			
			bw.write(tarefa.getCodigo() + "," + tarefa.getTitulo() + "," + tarefa.getDescricao() + "," + tarefa.getResponsavel().getNome());
			bw.newLine();
			bw.flush();
			
		} catch (IOException e) {
			System.out.println("Erro ao gravvar tarefa" + e.getMessage());
		}
		
	}
	
	public List<Tarefa> exibirTarefas () {
		
		List<Tarefa> tarefas = new ArrayList<>();
		
		try {
			BufferedReader br = ff.getBufferedReader();
			String linha;
		       while ((linha = br.readLine()) != null) {
	                String[] partes = linha.split(",");
	                if (partes.length >= 4) {
	                    Tarefa t = new Tarefa();
	                    t.setCodigo(partes[0]);
	                    t.setTitulo(partes[1]);
	                    t.setDescricao(partes[2]);

	                    Funcionario f = new Funcionario();
	                    f.setNome(partes[3]); // Apenas o nome será mostrado

	                    t.setResponsavel(f);
	                    tarefas.add(t);
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Erro ao ler tarefas: " + e.getMessage());
	        }

	        return tarefas;
	    }
}
