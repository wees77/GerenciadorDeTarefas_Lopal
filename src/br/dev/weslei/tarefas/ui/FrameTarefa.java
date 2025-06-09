package br.dev.weslei.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.dev.weslei.tarefas.dao.FuncionarioDAO;
import br.dev.weslei.tarefas.dao.TarefaDAO;
import br.dev.weslei.tarefas.model.Funcionario;
import br.dev.weslei.tarefas.model.Tarefa;
import br.dev.weslei.tarefas.utils.Utils;

public class FrameTarefa {

    private JLabel labelCodigo;
    private JLabel labelNome;
    private JLabel labelDesc;
    private JLabel labelFuncionario;

    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtDesc;

    private JComboBox<Funcionario> comboFuncionarios;
    private List<Funcionario> funcionarios;

    private JButton btnSalvar;
    private JButton btnSair;

    private FrameListaTarefas frameListaTarefas;  // Referência para atualizar lista

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
        tela.setSize(400, 400);
        tela.setResizable(false);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLocationRelativeTo(null);

        Container painel = tela.getContentPane();

        labelCodigo = new JLabel("Código:");
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
        txtDesc.setBounds(20, 180, 350, 30);

        labelFuncionario = new JLabel("Funcionário:");
        labelFuncionario.setBounds(20, 215, 200, 30);

        comboFuncionarios = new JComboBox<>();
        comboFuncionarios.setBounds(20, 245, 350, 30);

        for (Funcionario f : funcionarios) {
            comboFuncionarios.addItem(f);
        }

        // Renderizar só o nome do funcionário no combobox
        comboFuncionarios.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Funcionario) {
                    setText(((Funcionario) value).getNome());
                }
                return this;
            }
        });

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(20, 290, 100, 40);

        btnSair = new JButton("Sair");
        btnSair.setBounds(130, 290, 100, 40);

        painel.add(labelCodigo);
        painel.add(txtCodigo);
        painel.add(labelNome);
        painel.add(txtNome);
        painel.add(labelDesc);
        painel.add(txtDesc);
        painel.add(labelFuncionario);
        painel.add(comboFuncionarios);
        painel.add(btnSalvar);
        painel.add(btnSair);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarefa tarefa = new Tarefa();
                tarefa.setCodigo(Utils.gerarTDSequencial());
                tarefa.setTitulo(txtNome.getText());
                tarefa.setDescricao(txtDesc.getText());
                tarefa.setResponsavel((Funcionario) comboFuncionarios.getSelectedItem());

                TarefaDAO dao = new TarefaDAO(tarefa);
                dao.gravar();

                JOptionPane.showMessageDialog(tela,
                        txtNome.getText() + " gravado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                limparFormulario();

                // Atualiza a lista de tarefas na tela principal
                if (frameListaTarefas != null) {
                    frameListaTarefas.atualizarLista();
                }

                // Fecha o diálogo após salvar (opcional)
                tela.dispose();
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
        txtNome.setText(null);
        txtDesc.setText(null);
        comboFuncionarios.setSelectedIndex(0);
        txtNome.requestFocus();
    }
}
