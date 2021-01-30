package br.com.alura.springdata.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.UnidadeTrabalho;
import br.com.alura.springdata.repository.FuncionarioRepository;
import br.com.alura.springdata.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	private final FuncionarioRepository funcionarioRepository;
	
	private Boolean isUnidade = true;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository, FuncionarioRepository funcionarioRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		this.isUnidade = true;
		
		while (this.isUnidade) {
			System.out.println("Qual opção da unidade você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			System.out.println("5 - Adicionar Funcionário");
			
			int action = scanner.nextInt();
			scanner.nextLine();
			
			switch(action) {
				case 0:
					this.isUnidade = false;
					break;
				case 1:
					salvar(scanner);
					break;
				case 2:
					atualizar(scanner);
					break;
				case 3:
					visualizar();
					break;
				case 4:
					deletar(scanner);
					break;
				case 5:
					adicionarFuncionario(scanner);
					break;
				default:
					System.out.println("Opção inválida");
					break;
			}
		}
	}

	private void deletar(Scanner scanner) {
		System.out.print("ID da unidade de trabalho: ");
		Long id = scanner.nextLong();
		
		Optional<UnidadeTrabalho> optional = this.unidadeTrabalhoRepository.findById(id);
		
		if (optional.isPresent()) {
			System.out.println("Unidade de trabalho foi excluído");
		} else {
			System.out.println("Não foi possível excluir a unidade de trabalho");
		}
	}

	private void visualizar() {
		Iterable<UnidadeTrabalho> lista = this.unidadeTrabalhoRepository.findAll();
		
		lista.forEach(unidade -> {
			System.out.println(unidade);
		});
	}

	private void atualizar(Scanner scanner) {
		System.out.print("Descrição: ");
		String novaDescricao = scanner.nextLine();
		
		System.out.print("Endereço: ");
		String novoEndereco = scanner.nextLine();
		
		System.out.print("ID da unidade de trabalho: ");
		Long id = scanner.nextLong();
		
		Optional<UnidadeTrabalho> optional = this.unidadeTrabalhoRepository.findById(id);
		
		if (optional.isPresent()) {
			UnidadeTrabalho unidadeTrabalho = optional.get();
			unidadeTrabalho.setDescricao(novaDescricao);
			unidadeTrabalho.setEndereco(novoEndereco);
			
			this.unidadeTrabalhoRepository.save(unidadeTrabalho);
			
			System.out.println("Unidade de trabalho foi atualizado");
		} else {
			System.out.println("Não foi possível atualizar a unidade de trabalho");
		}
	}

	private void salvar(Scanner scanner) {
		System.out.print("Descrição: ");
		String descricao = scanner.nextLine();
		
		System.out.print("Endereço: ");
		String endereco = scanner.nextLine();
		
		UnidadeTrabalho unidade = new UnidadeTrabalho(descricao, endereco);
		
		this.unidadeTrabalhoRepository.save(unidade);
		
		System.out.println("Unidade de trabalho salvo com sucesso");
	}
	
	private void adicionarFuncionario(Scanner scanner) {
		System.out.print("ID do funcionário: ");
		Long funcionarioId = scanner.nextLong();
		
		System.out.print("ID da unidade de trabalho: ");
		Long unidadeTrabalhoId = scanner.nextLong();
		
		Optional<Funcionario> optionalFuncionario = this.funcionarioRepository.findById(funcionarioId);
		Optional<UnidadeTrabalho> optionalUnidade = this.unidadeTrabalhoRepository.findById(unidadeTrabalhoId);
		
		if (optionalFuncionario.isPresent() && optionalUnidade.isPresent()) {
			Funcionario funcionario = optionalFuncionario.get();
			UnidadeTrabalho unidadeTrabalho = optionalUnidade.get();
			
			unidadeTrabalho.setFuncionario(funcionario);
			
			this.unidadeTrabalhoRepository.save(unidadeTrabalho);
			
			System.out.println("Funcionário foi adicionado na unidade de trabaho " + unidadeTrabalho.getDescricao());
		} else {
			System.out.println("Não foi possível adicionar o funcionário na unidade de trabalho");
		}
	}
}
