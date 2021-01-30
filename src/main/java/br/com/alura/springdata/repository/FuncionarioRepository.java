package br.com.alura.springdata.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {
	List<Funcionario> findByNome(String nome);
	
	@Query("SELECT F FROM Funcionario F WHERE F.nome = :nome AND F.salario >= :salario AND F.dataContratacao = :dataContratacao")
	List<Funcionario> findByNomeSalarioMaiorDataContratacao(String nome, double salario, LocalDate dataContratacao);
	
	@Query(value = "SELECT * FROM funcionario f where f.data_contratacao >= :data", nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionario f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}
