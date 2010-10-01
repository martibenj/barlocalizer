package com.barlocalizer.threads;

import java.util.List;
import java.util.Random;

import android.widget.Toast;

import com.barlocalizer.screens.CountDownActivity;
import com.barlocalizer.services.FabriqueDureeEnMillisecondes;
import com.barlocalizer.services.FabriqueListeCheers;

/**
 * Thread permettant l'affichage d'un message de Cheer par période définie.
 * 
 * @author martinelli-b
 */
public class ThreadPopupCheers extends Thread {

    /** Suivi de l'activité du Thread. */
    private boolean run = false;

    /** . */
    private List<Integer> listCheers = null;

    /** . */
    private CountDownActivity countDownScreen = null;

    /** . */
    private static Random random = new Random();

    /** Délai d'affichage du message aléatoire en secondes. */
    private static final int DELAI_AFFICHAGE = 10;

    /**
     * @param countDownScreenInput
     *            Ecran de l'application
     */
    public ThreadPopupCheers(final CountDownActivity countDownScreenInput) {
        super();
        countDownScreen = countDownScreenInput;
        listCheers = FabriqueListeCheers.alimenterListeCheers();
    }

    /** Transmet le message à envoyer vers l'UI d'origine. */
    private Runnable addToast = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(
                    countDownScreen.getApplicationContext(),
                    countDownScreen.getApplicationContext().getString(
                            listCheers.get(random.nextInt(listCheers.size())).intValue()), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void run() {
        setRun(true);
        while (run) {
            countDownScreen.getHandler().post(addToast);
            try {
                Thread.sleep(FabriqueDureeEnMillisecondes.transformerSecondesEnMillisecondes(DELAI_AFFICHAGE));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return the run
     */
    public boolean isRun() {
        return run;
    }

    /**
     * @param runInput
     *            the run to set
     */
    public void setRun(final boolean runInput) {
        run = runInput;
    }

}
