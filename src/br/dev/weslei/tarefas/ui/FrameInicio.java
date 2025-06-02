package br.dev.weslei.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameInicio {
	
	private JButton btnFuncionario;
	private JButton btnTarefas;
	private JLabel labelTitulo;
	
	
	public FrameInicio() {
		criarTela();
	}
	
	private void criarTela() {
		JFrame tela = new JFrame("Tela Inicial");
		tela.setSize(400, 400);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.setLayout(null);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container painel = tela.getContentPane();
		
		// Tela Inicial
		labelTitulo = new JLabel("Selecione a opção desejada");
		btnFuncionario = new JButton("Funcionários");
		btnTarefas = new JButton("Tarefas");
		
		// Botões
		btnFuncionario.setBounds(40, 120, 300, 90);
		btnTarefas.setBounds(40, 230, 300, 90);
		labelTitulo.setBounds(110, 20, 300, 90);
		
	
		painel.add(labelTitulo);
		painel.add(btnFuncionario);
		painel.add(btnTarefas);
		
		btnFuncionario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameListaFuncionario();
				
			}
		});
		
		btnTarefas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameTarefa();
				
			}
		});
		tela.setVisible(true);
	
		
	}

}
