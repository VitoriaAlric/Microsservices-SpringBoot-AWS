package br.com.vigcfdev.aws_project01.controller;

import br.com.vigcfdev.aws_project01.enums.EventType;
import br.com.vigcfdev.aws_project01.model.Product;
import br.com.vigcfdev.aws_project01.repository.ProductRepository;
import br.com.vigcfdev.aws_project01.service.ProductPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductRepository productRepository;
    private ProductPublisher productPublisher;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductPublisher productPublisher) {
        this.productRepository = productRepository;
        this.productPublisher = productPublisher;
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<Product>(optionalProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product productCreated = productRepository.save(product);

        productPublisher.publishProductEvents(productCreated, EventType.PRODUCT_CREATED, "matilde");

        return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,
                                                 @PathVariable("id") long id) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            Product productUpdate = productRepository.save(product);

            productPublisher.publishProductEvents(productUpdate, EventType.PRODUCT_UPDATE, "doralice");


            return new ResponseEntity<Product>(productUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product productDeleted = optionalProduct.get();

            productRepository.delete(productDeleted);

            productPublisher.publishProductEvents(productDeleted, EventType.PRODUCT_DELETED, "hannah");

            return new ResponseEntity<Product>(productDeleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/bycode")
    public ResponseEntity<Product> findById(@RequestParam String code) {
        Optional<Product> optionalProduct = productRepository.findByCode(code);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<Product>(optionalProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}