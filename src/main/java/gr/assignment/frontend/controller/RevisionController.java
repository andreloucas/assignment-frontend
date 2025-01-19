package gr.assignment.frontend.controller;

import gr.assignment.frontend.dto.RevisionDto;
import gr.assignment.frontend.service.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class RevisionController {
    private final RevisionService revisionService;

    @GetMapping("/revisions")
    public String showRevisions(Model model) {
        List<RevisionDto> revisions = revisionService.findAllRevisions();
        model.addAttribute("revisions", revisions);
        return "revisions";
    }

    @GetMapping("/revisions/resource/{resourceId}")
    public String findResourceRevisionByResourceId(@PathVariable Long resourceId, Model model) {
        List<RevisionDto> revisions = revisionService.findRevisionsByResourceId(resourceId);
        model.addAttribute("revisions", revisions);
        return "resourceRevision";
    }
}
