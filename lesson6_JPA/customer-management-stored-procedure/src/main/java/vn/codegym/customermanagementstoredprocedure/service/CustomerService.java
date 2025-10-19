package vn.codegym.customermanagementstoredprocedure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.customermanagementstoredprocedure.model.Customer;
import vn.codegym.customermanagementstoredprocedure.repository.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public boolean saveWithStoredProcedure(Customer customer) {
        return iCustomerRepository.saveWithStoredProcedure(customer);
    }
}