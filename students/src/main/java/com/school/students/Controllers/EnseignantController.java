package com.school.students.Controllers;

import com.school.students.Entities.Classe;
import com.school.students.Entities.Enseignant;
import com.school.students.Repositories.ClasseRepository;
import com.school.students.Repositories.EnseignantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/enseignant")

public class EnseignantController {
    @Autowired
    EnseignantRepository enseignantRepository;

    @GetMapping("/addenseignant")
    public String addClasse(Model model) {
        model.addAttribute("enseignant", new Enseignant());  // Ajouter un objet de modèle vide
        return "enseignant/ajout.html";
    }
    @PostMapping("/addenseignant")
    public String add(@ModelAttribute("enseignant") @Valid Enseignant enseignant, BindingResult result) {
        if (result.hasErrors()) {
            // S'il y a des erreurs de validation, retournez à la page appropriée avec les erreurs
            return "enseignant/ajout";
        }
        enseignantRepository.save(enseignant);
        return"redirect:showenseignant";  // Correction de l'URL de redirection**/

    }
    @GetMapping("/showenseignant")
    public String show(Model model) {
        List<Enseignant> enseignants= new ArrayList<>();
        enseignants=enseignantRepository.findAll();
        model.addAttribute("enseignants",enseignants);
        return "enseignant/affiche.html";
    }
    @GetMapping("delete/{id}")
    public String supprimer(@PathVariable("id")long id) {
        enseignantRepository.deleteById(id);
        return"redirect:../showenseignant";
    }
    @GetMapping("update/{id}")
    public String update(@PathVariable("id")long id, Model model) {
        enseignantRepository.findById(id);/*rien de fait*/
        model.addAttribute("e",enseignantRepository.findById(id));
        return "enseignant/mod.html";
    }
    @PostMapping("/update")
    public String modifier(Enseignant e) {
        enseignantRepository.save(e);
        return "redirect:showenseignant";
    }

}



