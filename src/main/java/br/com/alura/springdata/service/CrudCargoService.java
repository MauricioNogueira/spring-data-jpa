package br.com.alura.springdata.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private final CargoRepository cargoRepository;
	private Boolean isCargo = false;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		this.isCargo = true;
		
		while (this.isCargo) {
			System.out.println("Qual opção de cargo você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			scanner.nextLine();
			
			switch(action) {
				case 0:
					this.isCargo = false;
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
				default:
					System.out.println("Opção inválida");
					break;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Descriçao do cargo: ");
		String descricao = scanner.nextLine();
		
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		
		this.cargoRepository.save(cargo);
		
		System.out.println("Cargo salvo com sucesso");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.print("ID do cargo: ");
		Long id = scanner.nextLong();
		
		Optional<Cargo> optional = this.cargoRepository.findById(id);
		
		if (optional.isPresent()) {			
			System.out.print("Nova descrição do cargo: ");
			String novaDescricao = scanner.next();
			
			Cargo cargo = optional.get();
			cargo.setDescricao(novaDescricao);
			
			this.cargoRepository.save(cargo);
			
			System.out.println("Cargo atualizado com sucesso");
		} else {
			System.out.println("Cargo com este ID não foi encontrado");
		}
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = this.cargoRepository.findAll();
		
		cargos.forEach(cargo -> {
			System.out.println(cargo);
		});
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Qual ID do cargo deseja excluir?");
		Long id = scanner.nextLong();
		
		Optional<Cargo> optional = this.cargoRepository.findById(id);
		
		if (optional.isPresent()) {
			this.cargoRepository.delete(optional.get());
			
			System.out.println("Cargo " + optional.get().getDescricao() + " foi excluido do sistema");
		} else {
			System.out.println("Cargo com o ID " + id + " não foi encontrado");
		}
	}
}