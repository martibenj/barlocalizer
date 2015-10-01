package com.barlocalizer.services;

import java.util.ArrayList;
import java.util.List;

import com.barlocalizer.R;

/**
 * Fabrique la liste des messages Cheers.
 * 
 * @author martinelli-b
 * 
 */
public final class FabriqueListeCheers {

    /**
     * Constructeur masqué AHAH!
     */
    private FabriqueListeCheers() {
    }

    /**
     * Alimente une liste de String de messages Cheers.
     * 
     * @return Une liste de String de messages Cheers
     */
    public static List<Integer> alimenterListeCheers() {
        List<Integer> listeCheers = new ArrayList<Integer>();
        listeCheers.add(R.string.cheers1);
        listeCheers.add(R.string.cheers2);
        listeCheers.add(R.string.cheers3);
        listeCheers.add(R.string.cheers4);
        listeCheers.add(R.string.cheers5);
        listeCheers.add(R.string.cheers6);
        return listeCheers;
    }
}
