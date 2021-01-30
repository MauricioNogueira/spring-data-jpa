package br.com.alura.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.repository.FuncionarioRepository;
import br.com.alura.springdata.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamicoService {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatorioFuncionarioDinamicoService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		System.out.print("Digite um nome: ");
		String nome = scanner.nextLine();
		
		if (nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.print("Digite um cpf: ");
		String cpf = scanner.nextLine();
		
		if (cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.print("Digite o salário: ");
		double salario = scanner.nextDouble();
		scanner.nextLine();
		
		if (salario == 0) {
			salario = 0;
		}
		
		System.out.print("Digite a data de contratação: ");
		String data = scanner.nextLine();
		
		LocalDate dataContratacao;
		
		if (data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> lista = this.funcionarioRepository.findAll(Specification.where(
				SpecificationFuncionario.nome(nome)
				.or(SpecificationFuncionario.cpf(cpf))
				.or(SpecificationFuncionario.salario(salario))
				.or(SpecificationFuncionario.dataContratacao(dataContratacao))
		));
		
		lista.forEach(System.out::println);
	}
}
