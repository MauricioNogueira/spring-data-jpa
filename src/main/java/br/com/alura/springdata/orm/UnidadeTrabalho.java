package br.com.alura.springdata.orm;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class UnidadeTrabalho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private String endereco;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "funcionario_unidade",
			joinColumns = @JoinColumn(name = "unidade_id"),
			inverseJoinColumns = @JoinColumn(name = "funcionario_id")
	)
	private List<Funcionario> funcionarios;
	
	@Deprecated
	public UnidadeTrabalho() {}
	
	public UnidadeTrabalho(String descricao, String endereco) {
		this.descricao = descricao;
		this.endereco = endereco;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionarios.add(funcionario);
	}
	
	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	@Override
	public String toString() {
		return "UnidadeTrabalho [id=" + id + ", descricao=" + descricao + ", endereco=" + endereco + ", funcionarios="
				+ funcionarios + "]";
	}
}
