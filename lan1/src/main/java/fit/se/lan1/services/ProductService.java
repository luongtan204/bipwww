package fit.se.lan1.services;

import fit.se.lan1.entities.Product;
import fit.se.lan1.reposities.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Optional<Product> findById(String id){
        return productRepository.findById(id);
    }
    public Product add(Product product){
        return productRepository.save(product);
    }
    public void delete(String id){
        productRepository.deleteById(id);
    }

}
