package br.com.adamastor.empresa.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.adamastor.empresa.model.Cargo;
import br.com.adamastor.empresa.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private final CargoRepository cargoRepository;
	private Boolean system = true;
	
	public CrudCargoService (CargoRepository CargoRepository) {
		this.cargoRepository = CargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("\nQUAL AÇÃO PARA CARGO VOCÊ QUER EXECUTAR? ");		
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
				visualizar();
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
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo!");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Informe o ID do cargo: ");
		int id = scanner.nextInt();
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		
		cargoRepository.save(cargo);
		
		System.out.println("Salvo!");
	}
	
	private void visualizar() {
		System.out.println("CARGOS:");
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(System.out::println);
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Informe o ID do cargo: ");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Deletado!");
	}

}
