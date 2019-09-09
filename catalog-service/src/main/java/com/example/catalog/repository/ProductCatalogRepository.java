package com.example.catalog.repository;


import com.example.catalog.entity.ProductCatalog;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;


@Transactional
public interface ProductCatalogRepository  extends  CrudRepository<ProductCatalog,Long>{

}
