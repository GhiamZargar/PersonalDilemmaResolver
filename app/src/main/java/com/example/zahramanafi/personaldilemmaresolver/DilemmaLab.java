package com.example.zahramanafi.personaldilemmaresolver;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Shorai-4 on 2017-10-19.
 */

public class DilemmaLab {

    private static DilemmaLab sDilemmaLab;

    private List<Dilemma> mDilemmas;


    public static DilemmaLab get(Context context) {
        if (sDilemmaLab == null) {
            sDilemmaLab = new DilemmaLab(context);
        }
        return sDilemmaLab;
    }
    private DilemmaLab(Context context) {
        mDilemmas = new ArrayList<>();

       /* for (int i = 0; i < 100; i++) {
            Dilemma dilemma = new Dilemma();
            dilemma.setTitle("Your dilemma #" + i);
            dilemma.setSolved(i % 2 == 0); // Every other one
            mDilemmas.add(dilemma);
        }*/

    }

    public void addDilemma(Dilemma d) {
        mDilemmas.add(d);
    }


    public List<Dilemma> getDilemmas() {
        return mDilemmas;
    }
    public Dilemma getdilemma(UUID id) {
        for (Dilemma dilemma : mDilemmas) {
            if (dilemma.getId().equals(id)) {
                return dilemma;
            }
        }
        return null;
    }

}
