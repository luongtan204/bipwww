package fit.se.lan1.reposities;

import fit.se.lan1.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findProductByIdIs(String id);

    List<Product> findProductByNameContainingIgnoreCase(String name);


}
