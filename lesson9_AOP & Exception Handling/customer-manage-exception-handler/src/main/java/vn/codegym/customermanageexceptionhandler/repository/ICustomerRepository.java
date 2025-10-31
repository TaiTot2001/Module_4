package vn.codegym.customermanageexceptionhandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.customermanageexceptionhandler.model.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}