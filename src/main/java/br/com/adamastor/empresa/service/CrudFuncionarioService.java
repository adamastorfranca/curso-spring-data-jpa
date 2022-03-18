package br.com.adamastor.empresa.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.adamastor.empresa.model.Cargo;
import br.com.adamastor.empresa.model.Funcionario;
import br.com.adamastor.empresa.model.UnidadeTrabalho;
import br.com.adamastor.empresa.repository.CargoRepository;
import br.com.adamastor.empresa.repository.FuncionarioRepository;
import br.com.adamastor.empresa.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {
	
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	
	public CrudFuncionarioService (FuncionarioRepository funcionarioRepository, 
			CargoRepository CargoRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = CargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("\nQUAL AÇÃO PARA FUNCIONÁRIO VOCÊ QUER EXECUTAR? ");		
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			System.out.println("0 - Voltar");
			
			int opcao = scanner.nextInt();
			
			switch (opcao) {
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
				system = false;
				break;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Nome: ");
		String nome = scanner.next();
		System.out.println("CPF: ");
		String cpf = scanner.next();
		System.out.println("Salário: ");
		BigDecimal salario = new BigDecimal(scanner.next());	
		System.out.println("Data de contração: ");
		String dataContratacao = scanner.next();
		System.out.println("CargoId: ");
        Integer cargoId = scanner.nextInt();
		
        List<UnidadeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
		
		System.out.println("Salvo!");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Informe o ID do cargo: ");
        Integer id = scanner.nextInt();

        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        BigDecimal salario = new BigDecimal(scanner.next());

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Alterado");
	}
	
	private void visualizar(Scanner scanner) {
		System.out.println("Qual página: ");
		Integer page = scanner.nextInt();
		Pageable pegeable = PageRequest.of(page, 2, Sort.by(Sort.Direction.ASC, "nome"));
		
		System.out.println("FUNCIONÁRIOS:");
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pegeable);
		
		System.out.println(funcionarios);
		System.out.println("Página atual " + (funcionarios.getNumber()+1));
		System.out.println("Total de elementos " + funcionarios.getTotalElements());
		funcionarios.forEach(System.out::println);
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Informe o ID do funcionário: ");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado!");
	}
	
	private List<UnidadeTrabalho> unidade(Scanner scanner){
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();
		
		while(isTrue) {
			System.out.println("Digite o unidadeId (Para sair digite 0)");
			Integer unidadeId = scanner.nextInt();
			
			if(unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
 			} else {
 				isTrue = false;
 			}
		}
		return unidades;
	}

}
