package br.com.alura.springdata.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.springdata.orm.Funcionario;

public class SpecificationFuncionario {
	
	public static Specification<Funcionario> nome(String nome) {
		return (root, CriteriaQuery, CriteriaBuilder) -> 
			CriteriaBuilder.like(root.get("nome"), "%"+nome+"%");
	}
	
	public static Specification<Funcionario> cpf(String cpf) {
		return (root, CriteriaQuery, CriteriaBuilder) -> 
			CriteriaBuilder.like(root.get("cpf"), "%"+cpf+"%");
	}
	
	public static Specification<Funcionario> salario(double salario) {
		return (root, CriteriaQuery, CriteriaBuilder) -> 
			CriteriaBuilder.greaterThan(root.get("salario"), salario);
	}
	
	public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
		return (root, CriteriaQuery, CriteriaBuilder) -> 
			CriteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao);
	}
}
