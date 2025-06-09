package br.dev.weslei.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.dev.weslei.tarefas.factory.FileFactoryTarefa;
import br.dev.weslei.tarefas.model.Funcionario;
import br.dev.weslei.tarefas.model.Status;
import br.dev.weslei.tarefas.model.Tarefa;

public class TarefaDAO {
    private Tarefa tarefa;
    private FileFactoryTarefa ff = new FileFactoryTarefa();

    public TarefaDAO(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public void gravar() {
        try {
            BufferedWriter bw = ff.getBufferedWriter();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String linha = tarefa.getTitulo() + "," +
                           tarefa.getDescricao() + "," +
                           tarefa.getDataInicial().format(formatter) + "," +
                           tarefa.getPrazo().format(formatter) + "," +
                           tarefa.getDataConclusao().format(formatter) + "," +
                           tarefa.getStatus().name() + "," +
                           tarefa.getResponsavel().getNome();

            bw.write(linha);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            System.out.println("Erro ao gravar tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> exibirTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();

        try {
            BufferedReader br = ff.getBufferedReader();
            String linha;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");

                if (partes.length >= 7) {
                    Tarefa t = new Tarefa();
                    t.setTitulo(partes[0]);
                    t.setDescricao(partes[1]);
                    t.setDataInicial(LocalDate.parse(partes[2], formatter));
                    t.setPrazo(LocalDate.parse(partes[3], formatter));
                    t.setDataConclusao(LocalDate.parse(partes[4], formatter));
                    t.setStatus(Status.valueOf(partes[5]));

                    Funcionario f = new Funcionario();
                    f.setNome(partes[6]);
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
