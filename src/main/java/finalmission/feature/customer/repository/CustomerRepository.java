package finalmission.feature.customer.repository;

import finalmission.feature.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findById(Long id);

    boolean existsByEmailAndPassword(String email, String password);

    Optional<Customer> findByEmailAndPassword(String email, String password);
}
