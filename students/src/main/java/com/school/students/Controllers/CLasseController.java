package com.school.students.Controllers;

import com.school.students.Entities.Classe;
import com.school.students.Entities.Etudiant;
import com.school.students.Repositories.ClasseRepository;
import com.school.students.Repositories.EtudiantRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("classes")

public class CLasseController {
    @Autowired
    ClasseRepository classeRepository;


    @GetMapping("/addclasse")
    public String addClasse(Model model) {
        model.addAttribute("classe", new Classe());  // Ajouter un objet de modèle vide
        return "classe/addclasse.html";
    }
    @PostMapping("/addclasse")
    public String add(@ModelAttribute("classe") @Valid Classe classe, BindingResult result)  {
        if (result.hasErrors()) {
            // S'il y a des erreurs de validation, retournez à la page appropriée avec les erreurs
            return "classe/addclasse";
        }
        classeRepository.save(classe);
        return"redirect:showclasse";  // Correction de l'URL de redirection
    }

    @GetMapping("/showclasse")
    public String show(Model model) {
        List<Classe> classes= new ArrayList<>();
        classes=classeRepository.findAll();
        model.addAttribute("classes",classes);
        return "classe/show.html";
    }
    @GetMapping("delete/{id}")
    public String supprimer(@PathVariable("id")long id) {
        classeRepository.deleteById(id);
        return"redirect:../showclasse";
    }
    @GetMapping("update/{id}")
    public String update(@PathVariable("id")long id, Model model) {
        classeRepository.findById(id);
        model.addAttribute("c",classeRepository.findById(id));
        return "classe/mod.html";
    }
    @PostMapping("/update")
    public String modifier(Classe c) {
        classeRepository.save(c);
        return "redirect:showclasse";
    }

}
