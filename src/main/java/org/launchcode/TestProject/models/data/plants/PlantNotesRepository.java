package org.launchcode.TestProject.models.data.plants;

import org.launchcode.TestProject.models.Plants.PlantNotes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantNotesRepository extends CrudRepository<PlantNotes, Integer> {
}
