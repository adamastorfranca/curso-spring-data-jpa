package br.com.adamastor.empresa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.adamastor.empresa.model.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {

}
