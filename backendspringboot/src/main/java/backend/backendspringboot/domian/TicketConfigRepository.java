package backend.backendspringboot.domian;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketConfigRepository extends CrudRepository<TicketConfiguration,Integer> {
}
