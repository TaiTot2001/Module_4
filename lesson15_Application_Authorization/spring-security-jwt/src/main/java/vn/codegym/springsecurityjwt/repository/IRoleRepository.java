package vn.codegym.springsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.springsecurityjwt.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}