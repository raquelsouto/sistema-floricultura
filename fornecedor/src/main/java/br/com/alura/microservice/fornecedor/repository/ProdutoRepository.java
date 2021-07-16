package br.com.alura.microservice.fornecedor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.microservice.fornecedor.model.Produto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

	List<Produto> findByEstado(String estado);
	List<Produto> findByIdIn(List<Long> ids);
}
