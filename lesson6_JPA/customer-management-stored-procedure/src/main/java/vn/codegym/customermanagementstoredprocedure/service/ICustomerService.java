package vn.codegym.customermanagementstoredprocedure.service;

import vn.codegym.customermanagementstoredprocedure.model.Customer;

public interface ICustomerService {
    boolean saveWithStoredProcedure(Customer customer);
}