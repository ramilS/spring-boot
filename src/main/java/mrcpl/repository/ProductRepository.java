package mrcpl.repository;

import io.vavr.collection.List;
import io.vavr.control.Option;
import mrcpl.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE :cursor is null or id > :cursor limit :max", nativeQuery = true)
    List<Product> findAll(int max, Option<Long> cursor);

    Option<Product> findByUrl(String url);

    boolean existsByUrl(String url);

    void deleteByUrl(String url);
}
