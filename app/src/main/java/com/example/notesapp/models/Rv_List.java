package com.example.notesapp.models;

import java.util.ArrayList;
import java.util.List;

public class Rv_List {
    private List<Notes> notes;

    public Rv_List() {
        this.notes = new ArrayList<>();
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void addNotes(Notes note) {
       this.notes.add(note);
    }

    public void removeNotes(int index) {
        Notes note = notes.get(index);
        this.notes.remove(note);
    }

    public void updateNotes(int index, Notes note) {
        this.notes.set(index, note);
    }
}
