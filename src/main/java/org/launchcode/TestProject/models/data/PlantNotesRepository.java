package org.launchcode.TestProject.models.data;

import org.launchcode.TestProject.models.PlantNotes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantNotesRepository extends CrudRepository<PlantNotes, Integer> {
}
