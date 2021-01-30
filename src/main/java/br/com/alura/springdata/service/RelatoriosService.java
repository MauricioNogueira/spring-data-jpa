package br.com.alura.springdata.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.FuncionarioProjecao;
import br.com.alura.springdata.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private Boolean menu = true;
	
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		
		while(menu) {
			System.out.println("Qual ação do relatório deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar funcionários pelo nome");
			System.out.println("2 - Busca funcionário por nome, data contratacao e salário maior");
			System.out.println("3 - Busca funcionários data contratação");
			System.out.println("4 - Pesquisa funcionário salário");
			
			int action = scanner.nextInt();
			scanner.nextLine();
			
			switch (action) {
			case 0:
				menu = false;
				break;
			case 1:
				buscar(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorDate(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			case 4:
				funcionarioSalario();
				break;
			default:
				System.out.println("Opção inválida");
				break;
			}
		}
	}

	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.print("Qual data de contratação deseja pesquisar: ");
		String data = scanner.nextLine();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> funcionarios = this.funcionarioRepository.findDataContratacaoMaior(localDate);
		
		funcionarios.forEach(System.out::println);
	}

	private void buscaFuncionarioNomeSalarioMaiorDate(Scanner scanner) {
		System.out.print("Qual nome deseja buscar: ");
		String nome = scanner.nextLine();
		
		System.out.print("Qual data de contratação deseja pesquisar: ");
		String data = scanner.nextLine();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.print("Qual salário deseja pesquisar: ");
		double salario = scanner.nextDouble();
		
		List<Funcionario> funcionarios = this.funcionarioRepository.findByNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		
		System.out.println(funcionarios.size());
		
		funcionarios.forEach(System.out::println);
	}

	private void buscar(Scanner scanner) {
		System.out.println("Qual o nome para busca: ");
		String nome = scanner.nextLine();
		
		List<Funcionario> funcionarios = this.funcionarioRepository.findByNome(nome);
		
		funcionarios.forEach(funcionario -> {
			System.out.println(funcionario);
		});
	}
	
	private void funcionarioSalario() {
		List<FuncionarioProjecao> lista = this.funcionarioRepository.findFuncionarioSalario();
		
		lista.forEach(funcionario -> {
			System.out.println("Funcionário id: " + funcionario.getId() + " | Nome: " + funcionario.getNome()
			+ " | salário: " + funcionario.getSalario());
		});
	}
}
