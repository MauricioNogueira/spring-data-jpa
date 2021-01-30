package br.com.alura.springdata.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.repository.CargoRepository;
import br.com.alura.springdata.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService implements CrudInterface {
	
	private Boolean isFuncionario = false;
	private FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner) {
		this.isFuncionario = true;
		
		while (this.isFuncionario) {
			System.out.println("Qual opção de funcionário você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			
			switch(action) {
				case 0:
					this.isFuncionario = false;
					break;
				case 1:
					salvar(scanner);
					break;
				case 2:
					atualizar(scanner);
					break;
				case 3:
					visualizar(scanner);
					break;
				case 4:
					deletar(scanner);
					break;
				default:
					System.out.println("Opção inválida");
					break;
			}
		}
	}
	
	private void deletar(Scanner scanner) {
		System.out.print("ID do funcionário: ");
		Long id = scanner.nextLong();
		
		Optional<Funcionario> optional = this.funcionarioRepository.findById(id);
		
		if (optional.isPresent()) {
			this.funcionarioRepository.delete(optional.get());
			
			System.out.println("Funcionário excluído com sucesso");
		} else {
			System.out.println("Não foi possível excluir o funcionário com ID " + id);
		}
	}

	private void visualizar(Scanner scanner) {
		System.out.println("Qual página deseja ver?");
		int paginaAtual = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(paginaAtual, 10, Sort.by(Sort.Direction.ASC, "nome"));
		
		Page<Funcionario> funcionarios = this.funcionarioRepository.findAll(pageable);
		
		System.out.println("Página " + funcionarios.getNumber() + " de " + funcionarios.getTotalPages());
		
		funcionarios.forEach(funcionario -> {
			System.out.println(funcionario);
		});
	}

	private void atualizar(Scanner scan) {
		boolean isValido = true;
		System.out.print("ID do funcionário: ");
		Long id = scan.nextLong();
		scan.nextLine();
		
		Optional<Funcionario> optional = this.funcionarioRepository.findById(id);
		
		if (optional.isPresent()) {
			System.out.print("Novo nome: ");
			String novoNome = scan.nextLine();
			
			System.out.print("Novo CPF: ");
			String novoCpf = scan.nextLine();
			
			System.out.print("Novo salário: ");
			double novoSalario = scan.nextDouble();
			
			System.out.print("ID do cargo: ");
			Long idCargo = scan.nextLong();
			
			Optional<Cargo> optionalCargo = this.cargoRepository.findById(idCargo);
			
			Cargo cargo = optionalCargo.orElse(null);
			
			if (cargo != null) {
				Funcionario funcionario = optional.get();
				funcionario.setNome(novoNome);
				funcionario.setCpf(novoCpf);
				funcionario.setSalario(novoSalario);
				funcionario.setCargo(cargo);
				
				this.funcionarioRepository.save(funcionario);
			} else {
				isValido = false;
			}
		} else {
			isValido = false;
		}
		
		if (isValido) {
			System.out.println("Funcionário atualizado com sucesso");
		} else {
			System.out.println("Não foi possível atualizar o funcionário");
		}
	}

	private void salvar(Scanner scan) {
		scan.nextLine();
		System.out.print("Nome: ");
		String nome = scan.nextLine();
		
		System.out.print("CPF: ");
		String cpf = scan.nextLine();
		
		System.out.print("Salário: ");
		double salario = scan.nextDouble();
		
		System.out.print("ID do cargo: ");
		Long id = scan.nextLong();
		Optional<Cargo> optional = this.cargoRepository.findById(id);
		
		if (optional.isPresent()) {
			Cargo cargo = optional.get();
			
			Funcionario funcionario = new Funcionario(nome, cpf, salario);
			funcionario.setCargo(cargo);
			this.funcionarioRepository.save(funcionario);
			
			System.out.println("Funcionário cadastrado com sucesso");
		} else {
			System.out.println("Não foi possível cadastro o funcionário");
		}
	}
}
