package net.openwebinars.springboot.restjwt.note.service;

import net.openwebinars.springboot.restjwt.dto.NotesGroupedByTagsDto;
import net.openwebinars.springboot.restjwt.note.model.Note;
import net.openwebinars.springboot.restjwt.note.repo.NoteRepository;
import net.openwebinars.springboot.restjwt.user.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteService noteService;


    @Test
    void notesGroupedByTagsDtoListNotesIsNotEmpty() {

        User user1 = new User();
        user1.setId(UUID.fromString("349b55cd-bf00-4a7f-995a-226149312079"));

        Note note1 = Note.builder()
                .id(1L)
                .title("Primera nota")
                .tags(List.of("tomorrow, tasks"))
                .author(user1.getId().toString())
                .build();

        Note note2 = Note.builder()
                .id(2L)
                .title("Segunda nota")
                .tags(List.of("tomorrow, tasks"))
                .author(user1.getId().toString())
                .build();

        Note note3 = Note.builder()
                .id(3L)
                .title("Tercera nota")
                .tags(List.of("yesterday, cooking"))
                .author(user1.getId().toString())
                .build();

        Note note4 = Note.builder()
                .id(4L)
                .title("Cuarta nota")
                .tags(List.of("to-do, cooking"))
                .author(user1.getId().toString())
                .build();

        List <Note> notes = List.of(note1, note2, note3, note4);
        Mockito.when(noteRepository.findByAuthor(user1.getId().toString())).thenReturn(notes);
        List<NotesGroupedByTagsDto> result = noteService.notesGroupedByTagsDtoList(user1.getId().toString());
        assertEquals(result.size(), 3);

    }


    @Test
    void notesGroupedByTagsDtoListNotesIsEmpty() {
        User user1 = new User();
        user1.setId(UUID.fromString("349b55cd-bf00-4a7f-995a-226149312079"));
        List <Note> notes = new ArrayList<>();
        Mockito.when(noteRepository.findByAuthor(user1.getId().toString())).thenReturn(notes);
        List<NotesGroupedByTagsDto> result = noteService.notesGroupedByTagsDtoList(user1.getId().toString());
        assertNull(result);
    }
}