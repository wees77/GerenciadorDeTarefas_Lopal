package br.dev.weslei.tarefas.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.dev.weslei.tarefas.dao.TarefaDAO;
import br.dev.weslei.tarefas.model.Tarefa;

public class FrameListaTarefas {
    private JLabel labelTitulo;
    private JTable tableTarefas;
    private JScrollPane scrollTarefas;
    private JButton btnNovo;
    private JButton btnExcluir;
    private JButton btnAlterar;
    private JButton btnVoltar;
    private JFrame telaAnterior;

    private DefaultTableModel tableModel;

    private Font fontTitulo = new Font("Arial", Font.BOLD, 26);

    public FrameListaTarefas(JFrame telaAnterior) {
        this.telaAnterior = telaAnterior;
    	criarTela();
    }

    private void criarTela() {
    	JFrame tela = new JFrame();
    	tela.setTitle("Cadastro de Tarefas");
        tela.setSize(600, 600);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setResizable(false);
        tela.setLayout(null);
        tela.setLocationRelativeTo(null);

        Container painel = tela.getContentPane();

        labelTitulo = new JLabel("Cadastro de Tarefas");
        labelTitulo.setBounds(10, 20, 500, 30);
        labelTitulo.setFont(fontTitulo);

        btnNovo = new JButton("Nova Tarefa");
        btnNovo.setBounds(10, 380, 150, 40);
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(170, 380, 150, 40);
        
        // Define colunas da tabela
        String[] colunas = { "Código", "Titulo", "Descrição", "Responsável" };
        tableModel = new DefaultTableModel(colunas, 0); // 0 linhas inicialmente

        tableTarefas = new JTable(tableModel);
        scrollTarefas = new JScrollPane(tableTarefas);
        scrollTarefas.setBounds(10, 70, 560, 300);

        painel.add(labelTitulo);
        painel.add(scrollTarefas);
        painel.add(btnNovo);
        painel.add(btnVoltar);

        // Carrega as tarefas iniciais
        carregarTabela();

        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Passa esta instância para poder atualizar a lista depois
                new FrameTarefa(FrameListaTarefas.this);
            }
        });
        
        btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameInicio();
				tela.dispose();
				telaAnterior.setVisible(true);
			}
		});

        tela.setVisible(true);
    }

    // Método que carrega as tarefas no modelo da tabela
    private void carregarTabela() {
        tableModel.setRowCount(0); // limpa a tabela

        TarefaDAO dao = new TarefaDAO(null);
        List<Tarefa> tarefas = dao.exibirTarefas();

        for (Tarefa t : tarefas) {
            Object[] linha = { t.getCodigo(), t.getTitulo(), t.getDescricao(), t.getResponsavel().getNome() };
            tableModel.addRow(linha);
        }
    }

    // Método público para atualizar a tabela após inserir uma tarefa
    public void atualizarLista() {
        carregarTabela();
        tableModel.fireTableDataChanged();
    }
}
