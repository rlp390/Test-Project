package org.launchcode.TestProject.models.data;

import org.launchcode.TestProject.models.Plants;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends CrudRepository<Plants, Integer> {
}
