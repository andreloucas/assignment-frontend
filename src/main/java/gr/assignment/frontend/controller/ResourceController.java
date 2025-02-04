package gr.assignment.frontend.controller;

import gr.assignment.frontend.dto.ResourceDto;
import gr.assignment.frontend.exceptions.NotFoundException;
import gr.assignment.frontend.exceptions.ValidateErrorException;
import gr.assignment.frontend.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/resource")
    private String showForm(Model model) {
        model.addAttribute("resource", new ResourceDto());
        return "resource";
    }

    @PostMapping("/resource")
    private String createResource(@ModelAttribute ResourceDto resource, Model model) throws IOException {
        try {
            resourceService.createResource(resource);
        } catch (ValidateErrorException e) {
            model.addAttribute("resource", resource);
            model.addAttribute("message", "Please fill all fields");
            return "resource";
        }
        return "redirect:/resources";
    }

    @GetMapping("/resources")
    private String showResources(Model model) {
        List<ResourceDto> resources = resourceService.findAll();
        model.addAttribute("resources", resources);
        return "resources";
    }

    @PostMapping("/deleteResource/{resourceId}")
    private String deleteResource(@PathVariable Long resourceId) {
        try {
            resourceService.deleteResource(resourceId);
            return "redirect:/resources";
        } catch (NotFoundException e) {
            return "error";
        }
    }

    @GetMapping("/updateResource/{resourceId}")
    public String editResourceForm(@PathVariable Long resourceId, Model model) {
        try {
            ResourceDto resource = resourceService.findById(resourceId);
            model.addAttribute("resource", resource);
            return "updateResource";
        } catch (NotFoundException e) {
            return "error";
        }
    }

    @PostMapping("/updateResource/{resourceId}")
    public String editResource(@PathVariable Long resourceId, @ModelAttribute("resource") ResourceDto resourceDto, Model model) {
        try {
            resourceService.updateResource(resourceId, resourceDto);
            return "redirect:/resources";
        } catch (ValidateErrorException e) {
            model.addAttribute("message", "Please fill all fields");
            return "resource";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/resource/download/{resourceId}")
    public byte[] downloadFile(@PathVariable Long resourceId) {
        return resourceService.downloadFile(resourceId);
    }
}


