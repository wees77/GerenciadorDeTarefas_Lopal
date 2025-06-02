package br.dev.weslei.tarefas.ui;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FrameTarefa {
	
	private JLabel labelCodigo;
	private JLabel labelNome;
	private JLabel labelDesc;
	private JLabel labelFuncionario;
	
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtDesc;
	
	private JButton btnGravar;
	
	public FrameTarefa() {
		criarTela();
	}
	
	private void criarTela() {
		JFrame tela = new JFrame("Cadastro de tarefas");
		tela.setLayout(null);
		tela.setSize(400, 400);
		tela.setResizable(false);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container painel = tela.getContentPane();
		
		labelCodigo = new JLabel("Código");
		labelCodigo.setBounds(20, 20, 200, 30);
		txtCodigo = new JTextField();
		txtCodigo.setBounds(20, 50, 200, 30);
		txtCodigo.setEnabled(false);
		
		labelNome = new JLabel("Nome:");
		labelNome.setBounds(20, 85, 200, 30);
		txtNome = new JTextField();
		txtNome.setBounds(20, 115, 350, 30);
		
		labelDesc = new JLabel("Descrição:");
		labelDesc.setBounds(20, 150, 200, 30);
		txtDesc = new JTextField();
		txtDesc.setBounds(20, 180, 200, 30);
		
		labelFuncionario = new JLabel("Funcionário:");
		labelFuncionario.setBounds(20, 215, 200, 30);
		
		btnGravar = new JButton("Gravar");
		btnGravar.setBounds(20, 290, 100, 40);
		
		painel.add(labelCodigo);
		painel.add(txtCodigo);
		painel.add(labelNome);
		painel.add(txtNome);
		painel.add(labelDesc);
		painel.add(txtDesc);
		painel.add(labelFuncionario);
		tela.setVisible(true);
	}
}
