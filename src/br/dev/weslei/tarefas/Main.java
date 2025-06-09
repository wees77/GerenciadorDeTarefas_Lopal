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
import br.dev.weslei.tarefas.ui.FrameFuncionario;
import br.dev.weslei.tarefas.ui.FrameInicio;
import br.dev.weslei.tarefas.ui.FrameListaFuncionario;

public class Main {
	
	private static String path = "C:\\Users\\wees_\\Tarefa\\tarefas.txt";

	public static void main(String[] args) {
		
		new FrameInicio();
		//new FrameListaFuncionario();
		//new FrameFuncionario();
		
	
	}

}
