package vn.codegym.thymeleaf_customerdatamanagement.service;

import vn.codegym.thymeleaf_customerdatamanagement.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(int id);

    void update(int id, Customer customer);

    void remove(int id);
}