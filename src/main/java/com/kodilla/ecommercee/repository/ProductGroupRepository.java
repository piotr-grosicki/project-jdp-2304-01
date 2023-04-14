package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductGroupRepository extends CrudRepository<ProductGroup,Long> {
    @Override
    ProductGroup save(ProductGroup productGroup);

    @Override
    Optional<ProductGroup> findById(Long id);

    @Override
    List<ProductGroup> findAll();
}
