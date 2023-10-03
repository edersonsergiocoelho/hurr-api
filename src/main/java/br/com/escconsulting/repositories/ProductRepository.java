package br.com.escconsulting.repositories;

import br.com.escconsulting.domain.product.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
