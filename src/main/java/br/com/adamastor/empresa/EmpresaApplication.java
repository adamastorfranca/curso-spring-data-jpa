package br.com.adamastor.empresa;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.adamastor.empresa.service.CrudCargoService;
import br.com.adamastor.empresa.service.CrudFuncionarioService;
import br.com.adamastor.empresa.service.CrudUnidadeTrabalhoService;
import br.com.adamastor.empresa.service.RelatorioFuncionarioDinamico;
import br.com.adamastor.empresa.service.RelatoriosService;

@SpringBootApplication
public class EmpresaApplication implements CommandLineRunner{

	private final CrudCargoService crudCargoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
	private Boolean system = true;
	
	public EmpresaApplication(CrudFuncionarioService crudFuncionarioService, 
			CrudCargoService crudCargoService,
			CrudUnidadeTrabalhoService crudUnidadeTrabalhoService,
			RelatoriosService relatoriosService,
			RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudCargoService = crudCargoService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.relatoriosService = relatoriosService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EmpresaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("\nQUAL AÇÃO VOCÊ QUER EXECUTAR? ");
			System.out.println("1 - Funcionario");
			System.out.println("2 - Cargo");
			System.out.println("3 - Unidade de trabalho");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatório dinâmico");
			System.out.println("0 - Sair");
			
			int opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				crudFuncionarioService.inicial(scanner);
				break;
			case 2:
				crudCargoService.inicial(scanner);
				break;
			case 3:
				crudUnidadeTrabalhoService.inicial(scanner);
				break;	
			case 4:
				relatoriosService.inicial(scanner);
				break;
			case 5:
				relatorioFuncionarioDinamico.inicial(scanner);
				break;
			default:
				system = false;
				break;
			}	
		}
		scanner.close();	
	}

}
