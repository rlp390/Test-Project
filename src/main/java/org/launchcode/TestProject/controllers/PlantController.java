package org.launchcode.TestProject.controllers;

import org.launchcode.TestProject.models.PlantNotes;
import org.launchcode.TestProject.models.Plants;
import org.launchcode.TestProject.models.data.PlantNotesRepository;
import org.launchcode.TestProject.models.data.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@Controller
@RequestMapping("plants")
public class PlantController {

    @Autowired
    private PlantNotesRepository plantNotesRepository;

    @Autowired
    private PlantRepository plantRepository;

    @RequestMapping("")
    public String plants(Model model) {
        model.addAttribute("title", "El Loggo de Planto!");
        model.addAttribute("plants", plantRepository.findAll());
        model.addAttribute("plantNotes", plantNotesRepository.findAll());

        return "plants/index";
    }

    @GetMapping("add")
    public String addPlantHome(Model model) {
        model.addAttribute("title", "El Addo de Planto Page!");
        model.addAttribute("plantName", "Enter plant name");
        model.addAttribute("plantDescription", "Enter plant description");
        model.addAttribute("plantLocation", "Enter plant location");
        model.addAttribute("plantImg", "Enter plant image URL");
        return "plants/add";
    }

    @PostMapping("add")
    public String processAddPlant(Model model, @RequestParam String plantName, @RequestParam String plantLocation, @RequestParam String plantDescription, @RequestParam String plantImg) {
        Plants newPlant = new Plants(plantName,plantLocation, plantDescription, plantImg);
        plantRepository.save(newPlant);
        model.addAttribute("title", "El Loggo de Planto!");
        model.addAttribute("plants", plantRepository.findAll());
        model.addAttribute("plantNotes", plantNotesRepository.findAll());

        return "redirect:../plants";
    }

    @GetMapping("addnote/{plantId}")
    public String addPlantNoteHome(Model model, @PathVariable int plantId) {
        Optional plant = plantRepository.findById(plantId);
        Plants foundPlant = (Plants) plant.get();
        model.addAttribute("plant",foundPlant);
        model.addAttribute("plantNotes", plantNotesRepository.findAll());
        model.addAttribute("title", "El Addo de Planto Noto Page!");
        model.addAttribute("plantNote", "Enter new note");
        model.addAttribute("plantId", plantId);

        return "plants/note";
    }

    @PostMapping("addnote")
    public String processAddPlantNote(Model model, @RequestParam int plantId, @RequestParam String plantNote) {
        PlantNotes newNote = new PlantNotes(plantNote, plantId);
        plantNotesRepository.save(newNote);
        model.addAttribute("title", "El Loggo de Planto!");
        model.addAttribute("plants", plantRepository.findAll());
        model.addAttribute("plantNotes", plantNotesRepository.findAll());

        return "redirect:../plants";
    }

    @GetMapping("edit")
    public String editPlantsHome(Model model) {
        model.addAttribute("title", "El Edito de Planto!");
        model.addAttribute("plants", plantRepository.findAll());

        return "plants/edit";
    }

    @PostMapping("edit")
    public String processEditPlants(Model model, @RequestParam int plantId) {
        Optional plant = plantRepository.findById(plantId);
        Plants foundPlant = (Plants) plant.get();

        model.addAttribute("title", "El Updatio de Planto!");
        model.addAttribute("plant", foundPlant);

        return "/plants/updatePlant";
    }

    @PostMapping("updatePlant")
    public String processPlantUpdate(Model model, @ModelAttribute @Valid Plants plant, @RequestParam int plantId) {
        Optional optional = plantRepository.findById(plantId);
        Plants persisted = (Plants) optional.get();

        persisted.setPlantDescription(plant.getPlantDescription());
        persisted.setPlantImageURL(plant.getPlantImageURL());
        persisted.setPlantLocation(plant.getPlantLocation());
        persisted.setPlantName(plant.getPlantName());

        plantRepository.save(persisted);

        model.addAttribute("title", "El Loggo de Planto!");
        model.addAttribute("plants", plantRepository.findAll());
        model.addAttribute("plantNotes", plantNotesRepository.findAll());

        return "redirect:../plants/edit";
    }

}
