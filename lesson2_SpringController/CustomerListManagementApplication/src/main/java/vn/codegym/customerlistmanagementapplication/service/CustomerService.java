package vn.codegym.customerlistmanagementapplication.service;

import vn.codegym.customerlistmanagementapplication.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
}