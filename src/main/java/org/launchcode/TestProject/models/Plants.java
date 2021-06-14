package org.launchcode.TestProject.models;

import java.util.ArrayList;
import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Plants {

    public Plants() { }

    public Plants(String newName, String newLocation, String newDescription, String newURL) {
        this.plantName = newName;
        this.plantLocation = newLocation;
        this.plantDescription = newDescription;
        this.plantImageURL = newURL;
    }

    @Id
    @GeneratedValue
    private int plantId;

    @NotBlank
    @Size(min=3, max = 50, message="Name must be 3-50 characters and not blank.")
    private String plantName;

    @NotBlank
    @Size(min=3, max=50, message = "Location must be 3-50 characters and not blank.")
    private String plantLocation;

    @Size(max=500, message="Description must be 500 characters or less.")
    private String plantDescription;

    @NotBlank
    private String plantImageURL;

    @OneToMany
    @JoinColumn(name="plantId")
    private List<PlantNotes> plantNotes = new ArrayList<>();

    public int getPlantId() {
        return plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getPlantLocation() {
        return plantLocation;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public List<PlantNotes> getPlantNotes() {
        return plantNotes;
    }

    public String getPlantImageURL() { return plantImageURL; }

    public void setPlantName(String plantName) { this.plantName = plantName; }

    public void setPlantLocation(String plantLocation) { this.plantLocation = plantLocation; }

    public void setPlantDescription(String plantDescription) { this.plantDescription = plantDescription; }

    public void setPlantImageURL(String plantImageURL) { this.plantImageURL = plantImageURL; }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }
}
