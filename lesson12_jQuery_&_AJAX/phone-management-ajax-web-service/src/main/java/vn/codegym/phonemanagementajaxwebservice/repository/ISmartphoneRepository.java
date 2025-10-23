package vn.codegym.phonemanagementajaxwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.phonemanagementajaxwebservice.model.Smartphone;

@Repository
public interface ISmartphoneRepository extends JpaRepository<Smartphone, Long> {
}