package br.dev.weslei.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;

import br.dev.weslei.tarefas.dao.FuncionarioDAO;
import br.dev.weslei.tarefas.dao.TarefaDAO;
import br.dev.weslei.tarefas.model.Funcionario;
import br.dev.weslei.tarefas.model.Status;
import br.dev.weslei.tarefas.model.Tarefa;

public class FrameTarefa {

    private JLabel labelTitulo;
    private JLabel labelDesc;
    private JLabel labelDataInicial;
    private JLabel labelPrazo;
    private JLabel labelDataConclusao;
    private JLabel labelStatus;
    private JLabel labelFuncionario;

    private JTextField txtTitulo;
    private JTextField txtDesc;
    private JTextField txtDataInicial;
    private JTextField txtPrazo;
    private JTextField txtDataConclusao;

    private JComboBox<Status> comboStatus;
    private JComboBox<Funcionario> comboFuncionarios;
    private List<Funcionario> funcionarios;

    private JButton btnSalvar;
    private JButton btnSair;

    private FrameListaTarefas frameListaTarefas;

    public FrameTarefa(FrameListaTarefas frameListaTarefas) {
        this.frameListaTarefas = frameListaTarefas;
        carregarFuncionarios();
        criarTela();
    }

    private void carregarFuncionarios() {
        FuncionarioDAO dao = new FuncionarioDAO(null);
        funcionarios = dao.exibirFuncionarios();
    }

    private void criarTela() {
        JDialog tela = new JDialog((JFrame) null, "Cadastro de Tarefa", true);
        tela.setLayout(null);
        tela.setSize(400, 600);
        tela.setResizable(false);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLocationRelativeTo(null);

        Container painel = tela.getContentPane();

        labelTitulo = new JLabel("Título:");
        labelTitulo.setBounds(20, 20, 200, 30);
        txtTitulo = new JTextField();
        txtTitulo.setBounds(20, 50, 350, 30);

        labelDesc = new JLabel("Descrição:");
        labelDesc.setBounds(20, 85, 200, 30);
        txtDesc = new JTextField();
        txtDesc.setBounds(20, 115, 350, 30);

        labelDataInicial = new JLabel("Data Inicial:");
        labelDataInicial.setBounds(20, 150, 200, 30);
        txtDataInicial = new JTextField();
        txtDataInicial.setBounds(20, 180, 350, 30);

        labelPrazo = new JLabel("Prazo:");
        labelPrazo.setBounds(20, 215, 200, 30);
        txtPrazo = new JTextField();
        txtPrazo.setBounds(20, 245, 350, 30);

        labelDataConclusao = new JLabel("Data conclusão:");
        labelDataConclusao.setBounds(20, 280, 200, 30);
        txtDataConclusao = new JTextField();
        txtDataConclusao.setBounds(20, 310, 350, 30);

        labelStatus = new JLabel("Status:");
        labelStatus.setBounds(20, 345, 200, 30);
        comboStatus = new JComboBox<>();
        comboStatus.setBounds(20, 375, 350, 30);
        for (Status s : Status.values()) {
            comboStatus.addItem(s);
        }

        labelFuncionario = new JLabel("Funcionário:");
        labelFuncionario.setBounds(20, 410, 200, 30);
        comboFuncionarios = new JComboBox<>();
        comboFuncionarios.setBounds(20, 440, 350, 30);

        for (Funcionario f : funcionarios) {
            comboFuncionarios.addItem(f);
        }

        comboFuncionarios.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Funcionario) {
                    setText(((Funcionario) value).getNome());
                }
                return this;
            }
        });

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(20, 500, 100, 40);

        btnSair = new JButton("Sair");
        btnSair.setBounds(130, 500, 100, 40);

        painel.add(labelTitulo);
        painel.add(txtTitulo);
        painel.add(labelDesc);
        painel.add(txtDesc);
        painel.add(labelDataInicial);
        painel.add(txtDataInicial);
        painel.add(labelPrazo);
        painel.add(txtPrazo);
        painel.add(labelDataConclusao);
        painel.add(txtDataConclusao);
        painel.add(labelStatus);
        painel.add(comboStatus);
        painel.add(labelFuncionario);
        painel.add(comboFuncionarios);
        painel.add(btnSalvar);
        painel.add(btnSair);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setTitulo(txtTitulo.getText());
                    tarefa.setDescricao(txtDesc.getText());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    LocalDate dataInicial = LocalDate.parse(txtDataInicial.getText(), formatter);
                    LocalDate prazo = LocalDate.parse(txtPrazo.getText(), formatter);
                    LocalDate dataConclusao = LocalDate.parse(txtDataConclusao.getText(), formatter);

                    tarefa.setDataInicial(dataInicial);
                    tarefa.setPrazo(prazo);
                    tarefa.setDataConclusao(dataConclusao);

                    tarefa.setStatus((Status) comboStatus.getSelectedItem());
                    tarefa.setResponsavel((Funcionario) comboFuncionarios.getSelectedItem());

                    TarefaDAO dao = new TarefaDAO(tarefa);
                    dao.gravar();

                    JOptionPane.showMessageDialog(tela,
                            tarefa.getTitulo() + " gravado com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);

                    limparFormulario();

                    if (frameListaTarefas != null) {
                        frameListaTarefas.atualizarLista();
                    }

                    tela.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(tela,
                        "Erro ao salvar tarefa: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(
                        tela,
                        "Confirma a saída?",
                        "Sair",
                        JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    tela.dispose();
                }
            }
        });

        tela.setVisible(true);
    }

    private void limparFormulario() {
        txtTitulo.setText(null);
        txtDesc.setText(null);
        txtDataInicial.setText(null);
        txtPrazo.setText(null);
        txtDataConclusao.setText(null);
        comboStatus.setSelectedIndex(0);
        comboFuncionarios.setSelectedIndex(0);
        txtTitulo.requestFocus();
    }
}

