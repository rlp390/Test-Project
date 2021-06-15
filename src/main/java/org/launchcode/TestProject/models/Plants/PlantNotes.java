package org.launchcode.TestProject.models.Plants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class PlantNotes {

    public PlantNotes() {}

    public PlantNotes(String newNote, int newPlantId) {
        this.note = newNote;
        this.plantId = newPlantId;
    }

    @Id
    @GeneratedValue
    private int id;

    private int plantId;

    @NotBlank
    @Size(max=500, message="Note must be 500 characters or less.")
    private String note;

    public int getPlantId() {
        return plantId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() { return id; }
}
