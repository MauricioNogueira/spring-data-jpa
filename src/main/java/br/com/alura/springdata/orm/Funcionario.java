package br.com.alura.springdata.orm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private double salario;
	private LocalDate dataContratacao = LocalDate.now();
	
	@ManyToOne
	@JoinColumn(name = "cargo_id", referencedColumnName = "id", nullable = false)
	private Cargo cargo;
	
	@ManyToMany(mappedBy = "funcionarios")
	private List<UnidadeTrabalho> unidades;
	
	@Deprecated
	public Funcionario() {}
	
	public Funcionario(String nome, String cpf, double salario) {
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public double getSalario() {
		return salario;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public LocalDate getDataContratacao() {
		return dataContratacao;
	}
	
	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public void setUnidadeTrabalho(UnidadeTrabalho unidadeTrabalho) {
		this.unidades.add(unidadeTrabalho);
	}
	
	public List<UnidadeTrabalho> getUnidades() {
		return this.unidades;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", salario=" + salario
				+ ", dataContratacao=" + dataContratacao + ", cargo=" + cargo.getDescricao() + "]";
	}
}
