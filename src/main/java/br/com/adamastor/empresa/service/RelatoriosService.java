package br.com.adamastor.empresa.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.adamastor.empresa.model.Funcionario;
import br.com.adamastor.empresa.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("\nQUAL RELATÓRIO VOCÊ QUER EXECUTAR? ");		
			System.out.println("1 - Busca de funcionário por nome");		
			System.out.println("2 - Busca de funcionário por CPF");
			System.out.println("3 - Busca de funcionários por salário");
			System.out.println("4 - Busca de funcionários por cargo");
			System.out.println("5 - Busca de funcionários por nome, salário e data de contratação");
			System.out.println("6 - Busca de funcionários por Unidade de trabalho");
			System.out.println("7 - Busca de funcionários por data de contratação maoir que...");
			System.out.println("8 - Busca de funcionário por nome com paginação");
			System.out.println("0 - Voltar");
			
			int opcao = scanner.nextInt();
			
			switch (opcao) {
			case 1:
				buscarPorNome(scanner);
				break;
			case 2:
				buscarPorCpf(scanner);
				break;
			case 3:
				buscarPorSalario(scanner);
				break;
			case 4:
				buscarPorCargo(scanner);
				break;
			case 5:
				buscarPorNomeSalarioDataContratacao(scanner);
				break;
			case 6:
				buscarPorUnidadeTrabalho(scanner);
				break;
			case 7:
				buscarPorDataContratacaoMaiorQue(scanner);
				break;
			case 8:
				buscarPorNomeComPaginacao(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void buscarPorNome(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		List<Funcionario> resultado = funcionarioRepository.findByNome(nome);
		resultado.forEach(System.out::println);
	}
	
	private void buscarPorSalario(Scanner scanner) {
		System.out.println("Salário: ");
		BigDecimal salario = new BigDecimal(scanner.next());
		List<Funcionario> resultado = funcionarioRepository.findBySalario(salario);
		resultado.forEach(System.out::println);
	}
	
	private void buscarPorCpf(Scanner scanner) {
		System.out.println("CPF: ");
		String cpf = scanner.next();
		List<Funcionario> resultado = funcionarioRepository.findByCpf(cpf);
		resultado.forEach(System.out::println);
	}
	
	private void buscarPorCargo(Scanner scanner) {
		System.out.println("Descrição: ");
		String cargo = scanner.next();
		List<Funcionario> resultado = funcionarioRepository.findByCargoDescricao(cargo);
		resultado.forEach(System.out::println);
	}
	
	private void buscarPorNomeSalarioDataContratacao(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		System.out.println("Salário: ");
		BigDecimal salario = new BigDecimal(scanner.next());
		System.out.println("Data de contratação: ");
		LocalDate dataContratacao = LocalDate.parse(scanner.next(), formatter);
		List<Funcionario> resultado = funcionarioRepository.findNomeSalarioDataContratacao(nome, salario, dataContratacao);
		resultado.forEach(System.out::println);
	}
	
	private void buscarPorUnidadeTrabalho(Scanner scanner) {
		System.out.println("Descrição: ");
		String descricao = scanner.next();
		List<Funcionario> resultado = funcionarioRepository.findByUnidadeTrabalhos_Descricao(descricao);
		resultado.forEach(System.out::println);
	}
	
	private void buscarPorDataContratacaoMaiorQue(Scanner scanner) {
		System.out.println("Data de contratação: ");
		LocalDate dataContratacao = LocalDate.parse(scanner.next(), formatter);
		List<Funcionario> resultado = funcionarioRepository.findDataContratacaoMaior(dataContratacao);
		resultado.forEach(System.out::println);
	}
	
	private void buscarPorNomeComPaginacao(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		
		System.out.println("Qual página: ");
		Integer page = scanner.nextInt();
		Pageable pageable = PageRequest.of(page, 2, Sort.unsorted());
		
		Page<Funcionario> resultado = funcionarioRepository.findByNome(nome, pageable);
		System.out.println(resultado);
		System.out.println("Página atual " + (resultado.getNumber()+1));
		System.out.println("Total de elementos " + resultado.getTotalElements());
		resultado.forEach(System.out::println);
	}
}
