package br.com.escconsulting.controllers;

import br.com.escconsulting.repositories.ProductRepository;
import br.com.escconsulting.domain.product.Product;
import br.com.escconsulting.domain.product.ProductRequestDTO;
import br.com.escconsulting.domain.product.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Validated ProductRequestDTO body){
        Product newProduct = new Product(body);

        this.repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> productList = new ArrayList<>();

        // Iterar sobre os elementos do Iterable e adicioná-los à lista
        for (Product product : this.repository.findAll()) {

            ProductResponseDTO ProductResponseDTO = new ProductResponseDTO(product.getId(), product.getName(), product.getPrice());

            productList.add(ProductResponseDTO);
        }

        return ResponseEntity.ok(productList);
    }
}
