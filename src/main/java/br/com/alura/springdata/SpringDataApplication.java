package br.com.alura.springdata;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.springdata.service.CrudCargoService;
import br.com.alura.springdata.service.CrudFuncionarioService;
import br.com.alura.springdata.service.CrudUnidadeTrabalhoService;
import br.com.alura.springdata.service.RelatorioFuncionarioDinamicoService;
import br.com.alura.springdata.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamicoService relatorioFuncionarioDinamicoService;
	
	private Boolean sistema = true;
	
	public SpringDataApplication(CrudCargoService crudCargoService, 
			CrudFuncionarioService crudFuncionarioService, 
			CrudUnidadeTrabalhoService crudUnidadeTrabalhoService, 
			RelatoriosService relatoriosService, RelatorioFuncionarioDinamicoService relatorioFuncionarioDinamicoService) {
		this.crudCargoService = crudCargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.relatoriosService = relatoriosService;
		this.relatorioFuncionarioDinamicoService = relatorioFuncionarioDinamicoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		while(this.sistema) {
			System.out.println("Qual ação você quer executar?");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatórios de funcionários");
			System.out.println("5 - Relatório dinamico");
			System.out.println("0 - Sair");
			
			int action = scan.nextInt();
			scan.nextLine();
			
			switch(action) {
				case 0:
					this.sistema = false;
					break;
				case 1:
					this.crudCargoService.inicial(scan);
					break;
				case 2:
					this.crudFuncionarioService.inicial(scan);
					break;
				case 3:
					this.crudUnidadeTrabalhoService.inicial(scan);
				case 4:
					this.relatoriosService.inicial(scan);
					break;
				case 5:
					this.relatorioFuncionarioDinamicoService.inicial(scan);
					break;
				default:
					System.out.println("Opçao inválida");
					break;
			}
		}
		
	}
}
