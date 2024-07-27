package one.digitalinovation.gof.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
