package vn.codegym.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.shoppingcart.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
}