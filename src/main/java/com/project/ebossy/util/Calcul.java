package com.project.ebossy.util;

import com.project.ebossy.model.Note;

import java.util.Map;

public class Calcul {

    public static double moyenneNote(Map<? extends Object, Note> noteMap){
        double sum = 0;
        double count = 0;

        for(Map.Entry<? extends Object, Note> entry : noteMap.entrySet()){
            Note note = entry.getValue() != null ? entry.getValue() : new Note();
            sum += note.getNote() != null ? note.getNote() : 0;
            count++;
        }

        if(count == 0) return 0;
        return sum/count;
    }
}
