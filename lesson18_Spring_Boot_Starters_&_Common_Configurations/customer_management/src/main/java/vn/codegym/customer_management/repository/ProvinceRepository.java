package vn.codegym.customer_management.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.customer_management.model.Province;

@Repository
public interface ProvinceRepository extends CrudRepository<Province, Long> {
}