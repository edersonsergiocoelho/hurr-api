package br.com.escconsulting.repository;

import br.com.escconsulting.domain.product.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
