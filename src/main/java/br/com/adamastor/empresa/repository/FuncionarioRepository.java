package br.com.adamastor.empresa.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.adamastor.empresa.model.Funcionario;
import br.com.adamastor.empresa.model.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, 
												JpaSpecificationExecutor<Funcionario>{

	//Derived query
	List<Funcionario> findByNome(String nome);
	Page<Funcionario> findByNome(String nome, Pageable pageable);
	List<Funcionario> findBySalario(BigDecimal salario);
	List<Funcionario> findByCpf(String cpf);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
										+ "AND f.salario = :salario "
										+ "AND f.dataContratacao = :dataContratacao")
	List<Funcionario> findNomeSalarioDataContratacao(String nome, BigDecimal salario, LocalDate dataContratacao);
	
	@Query("SELECT f FROM Funcionario f JOIN f.cargo c WHERE c.descricao = :descricao")
	List<Funcionario> findByCargoDescricao(String descricao);
		
	@Query("SELECT f FROM Funcionario f JOIN f.unidadeTrabalhos u WHERE u.descricao = :descricao")
	List<Funcionario> findByUnidadeTrabalhos_Descricao(String descricao);
	
	@Query(value = "SELECT * FROM funcionarios  AS f WHERE f.data_contratacao >= :dataContratacao", nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate dataContratacao);
	
	@Query(value = "SELECT id, nome, salario FROM funcionarios", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}
