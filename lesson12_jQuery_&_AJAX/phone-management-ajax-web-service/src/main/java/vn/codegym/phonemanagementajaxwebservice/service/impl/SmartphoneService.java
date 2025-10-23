package vn.codegym.phonemanagementajaxwebservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.phonemanagementajaxwebservice.model.Smartphone;
import vn.codegym.phonemanagementajaxwebservice.repository.ISmartphoneRepository;
import vn.codegym.phonemanagementajaxwebservice.service.ISmartphoneService;

import java.util.Optional;

@Service
public class SmartphoneService implements ISmartphoneService {
    @Autowired
    private ISmartphoneRepository iSmartphoneRepository;

    @Override
    public Iterable<Smartphone> findAll() {
        return iSmartphoneRepository.findAll();
    }

    @Override
    public Optional<Smartphone> findById(Long id) {
        return iSmartphoneRepository.findById(id);
    }

    @Override
    public Smartphone save(Smartphone smartPhone) {
        return iSmartphoneRepository.save(smartPhone);
    }

    @Override
    public void remove(Long id) {
        iSmartphoneRepository.deleteById(id);
    }
}