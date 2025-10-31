package vn.codegym.customermanageexceptionhandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import vn.codegym.customermanageexceptionhandler.exception.DuplicateEmailException;
import vn.codegym.customermanageexceptionhandler.model.Customer;
import vn.codegym.customermanageexceptionhandler.repository.ICustomerRepository;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public void save(Customer customer) throws DuplicateEmailException {
        try {
            iCustomerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public Customer findById(Long id) {
        return iCustomerRepository.findById(id).get();
    }

    @Override
    public void remove(Long id) {
        iCustomerRepository.deleteById(id);
    }
}