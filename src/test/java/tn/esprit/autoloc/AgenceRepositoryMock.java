package tn.esprit.autoloc;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.domain.Agence;

public interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}