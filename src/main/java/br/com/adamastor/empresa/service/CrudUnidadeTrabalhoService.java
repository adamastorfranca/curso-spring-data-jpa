package br.com.adamastor.empresa.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.adamastor.empresa.model.UnidadeTrabalho;
import br.com.adamastor.empresa.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	private Boolean system = true;
	
	public CrudUnidadeTrabalhoService (UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("\nQUAL AÇÃO PARA UNIDADE DE TRABALHO VOCÊ QUER EXECUTAR? ");		
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
		System.out.println("Descrição da unidade de trabalho: ");
		String descricao = scanner.next();
		System.out.println("Endereço: ");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidade = new UnidadeTrabalho();
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		unidadeTrabalhoRepository.save(unidade);
		
		System.out.println("Salvo!");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Informe o ID: ");
		int id = scanner.nextInt();
		System.out.println("Descrição da unidade de trabalho: ");
		String descricao = scanner.next();
		System.out.println("Endereço: ");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidade = new UnidadeTrabalho();
		unidade.setId(id);
		unidade.setDescricao(descricao);
		unidade.setEndereco(endereco);
		unidadeTrabalhoRepository.save(unidade);
		
		System.out.println("Salvo!");
	}
	
	private void visualizar() {
		System.out.println("UNIDADES DE TRABALHO:");
		Iterable<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
		unidades.forEach(System.out::println);
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Informe o ID: ");
		int id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado!");
	}

}
