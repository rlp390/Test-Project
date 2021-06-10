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
        return "plants/add";
    }

    @PostMapping("add")
    public String processAddPlant(Model model, @RequestParam String plantName, @RequestParam String plantLocation, @RequestParam String plantDescription) {
        Plants newPlant = new Plants(plantName,plantLocation, plantDescription);
        plantRepository.save(newPlant);
        model.addAttribute("title", "El Loggo de Planto!");
        model.addAttribute("plants", plantRepository.findAll());
        model.addAttribute("plantNotes", plantNotesRepository.findAll());

        return "plants/index";
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
}
