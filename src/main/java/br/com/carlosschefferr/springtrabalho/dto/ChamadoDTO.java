package br.com.carlosschefferr.springtrabalho.dto;

import br.com.carlosschefferr.springtrabalho.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFechamento;
    @NotNull(message = "Informe a prioridade do chamado")
    private Integer prioridade;
    @NotNull(message = "Informe o status do chamado")
    private Integer status;
    @NotNull(message = "Informe o título do chamado")
    private String titulo;
    @NotNull(message = "O campo observações é obrigatório")
    private String observacoes;
    @NotNull(message = "Informe o técnico do chamado")
    private Long tecnico;
    @NotNull(message = "Informe o cliente do chamado")
    private Long cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Long getTecnico() {
        return tecnico;
    }

    public void setTecnico(Long tecnico) {
        this.tecnico = tecnico;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
