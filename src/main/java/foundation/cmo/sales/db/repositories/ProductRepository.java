package foundation.cmo.sales.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import foundation.cmo.sales.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String>{

}
