package vn.codegym.customerprovincemanagement.repository;

import org.springframework.data.repository.CrudRepository;
import vn.codegym.customerprovincemanagement.model.Province;

public interface IProvinceRepository extends CrudRepository<Province, Long> {
}