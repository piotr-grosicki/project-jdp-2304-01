package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductGroupRepository extends CrudRepository<ProductGroup,Long> {
}
