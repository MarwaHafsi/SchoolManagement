package com.school.students.Controllers;
import com.school.students.Entities.Classe;
import com.school.students.Entities.Etudiant;
import com.school.students.Repositories.ClasseRepository;
import com.school.students.Repositories.EtudiantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("etudiants")
public class EtudiantController {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    ClasseRepository classeRepository;
    @GetMapping("/sited")
    public String site(){
        return "site.html";
    }
    @GetMapping("/add")
    public String add(Model model){
        List<Classe> classes = classeRepository.findAll();
        model.addAttribute("etudiant", new Etudiant()); // Ajoutez un nouvel objet Etudiant pour le formulaire
        model.addAttribute("classes", classes);
        model.addAttribute("status", Arrays.asList("Nouveau", "Redoublant")); // Ajout des statuts
        return "add.html";
    }
    @PostMapping("/add")
    public String add(
            @ModelAttribute("etudiant") @Valid Etudiant etudiant,
            @RequestParam("image") MultipartFile imageFile,
            BindingResult bindingResult,
            Model model
    ) {
        // Logique pour traiter l'upload d'image
        if (!imageFile.isEmpty()) {
            try {
                // Utilisez le nom réel du fichier téléchargé
                String originalFileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
                etudiant.setImageFileName(originalFileName);

                byte[] bytes = imageFile.getBytes();
                // Assurez-vous que le dossier "uploads" existe
                Path path = Paths.get("uploads/" + originalFileName);
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Autre logique de traitement de l'étudiant
        if ("Nouveau".equals(etudiant.getStatus())) {
            etudiant.setProprieteNouvelEtudiant("Valeur par défaut pour un nouvel étudiant");
        } else if ("Redoublant".equals(etudiant.getStatus())) {
            etudiant.setProprieteEtudiantRedoublant("Valeur par défaut pour un étudiant redoublant");
        }

        etudiantRepository.save(etudiant);
        return "redirect:show";
    }


    @GetMapping("/show")
    public String show(Model model) {
        List<Etudiant> etudiants=new ArrayList<Etudiant>();
        etudiants=etudiantRepository.findAll();
        model.addAttribute("etudiants",etudiants);
        return "show.html";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") long id){
       etudiantRepository.deleteById(id);
        return"redirect:../show";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        // Récupérez l'étudiant à mettre à jour depuis la base de données
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        // Récupérez la liste des classes
        List<Classe> classes = classeRepository.findAll();
        model.addAttribute("statuts", Arrays.asList("Nouveau", "Redoublant")); // Ajout des statuts


        // Ajoutez les objets au modèle
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("classes", classes);
        model.addAttribute("status", Arrays.asList("Nouveau", "Redoublant")); // Ajout des statuts


        return "modif.html";
    }
    @PostMapping ("update/{id}")
    public String updateStudent(@PathVariable("id") Long id,
                                @ModelAttribute("etudiant") Etudiant updatedEtudiant,@RequestParam("image") MultipartFile imageFile) {
        // Mettez à jour l'étudiant dans la base de données
        Etudiant existingEtudiant = etudiantRepository.findById(id).orElse(null);
        if (existingEtudiant != null) {
            existingEtudiant.setNom(updatedEtudiant.getNom());
            existingEtudiant.setPrenom(updatedEtudiant.getPrenom());
            existingEtudiant.setEmail(updatedEtudiant.getEmail());
            existingEtudiant.setAdresse(updatedEtudiant.getAdresse());
            existingEtudiant.setNumTel(updatedEtudiant.getNumTel());
            existingEtudiant.setClasse(updatedEtudiant.getClasse());
            existingEtudiant.setStatus(updatedEtudiant.getStatus());
            etudiantRepository.save(existingEtudiant);
            // Vérifiez si un nouveau fichier image est téléchargé
            if (!imageFile.isEmpty()) {
                // Générez un nom de fichier unique ou utilisez une stratégie pour éviter les conflits
                String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

                // Enregistrez le fichier dans le répertoire d'uploads
                saveImage(fileName, imageFile);

                // Mettez à jour le nom du fichier dans l'entité
                existingEtudiant.setImageFileName(fileName);
            }
            etudiantRepository.save(existingEtudiant);

        }

        return "redirect:/etudiants/show";
    }
    // Méthode pour enregistrer l'image dans le répertoire d'uploads
    private void saveImage(String fileName, MultipartFile imageFile) {
        try {
            // Remplacez "your_upload_directory" par le chemin absolu de votre répertoire d'uploads
            Path uploadDirectory = Paths.get("uploads");
            Files.copy(imageFile.getInputStream(), uploadDirectory.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            // Gérez les erreurs liées à l'enregistrement de l'image
            e.printStackTrace();
        }
    }

}
