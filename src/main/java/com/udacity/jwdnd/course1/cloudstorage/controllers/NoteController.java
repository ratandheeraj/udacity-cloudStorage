package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserNoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/notes")
public class NoteController {

    private Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public String noteSubmit(
            @ModelAttribute("userNoteDto") UserNoteDTO userNoteDTO,
            Authentication authentication,
            Model model
    ) {
        String username = authentication.getPrincipal().toString();
        this.logger.error("Submitted Note: " + userNoteDTO.toString());
        Boolean isSuccess = this.noteService.insertOrUpdateNoteByUser(username, userNoteDTO);

        return "redirect:/result?isSuccess=" + isSuccess;
    }

    @GetMapping("/note")
    public String noteDeletion(
            @ModelAttribute("userNoteDto") UserNoteDTO userNoteDTO,
            @RequestParam(required = false, name = "noteId") Integer noteId,
            Authentication authentication,
            Model model
    ) {
        String username = authentication.getPrincipal().toString();
        this.logger.error(noteId.toString());
        Boolean isSuccess = this.noteService.deleteNoteByNoteId(noteId, username);
        return "redirect:/result?isSuccess=" + isSuccess;
    }
}