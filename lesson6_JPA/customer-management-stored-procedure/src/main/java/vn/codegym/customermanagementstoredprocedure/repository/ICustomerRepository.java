package vn.codegym.customermanagementstoredprocedure.repository;

import vn.codegym.customermanagementstoredprocedure.model.Customer;

public interface ICustomerRepository {
    boolean saveWithStoredProcedure(Customer customer);
}