package com.barlocalizer.threads;

import android.widget.TextView;

import com.barlocalizer.R;
import com.barlocalizer.screens.CountDownActivity;
import com.barlocalizer.services.CountDownService;
import com.barlocalizer.services.FabriqueDureeEnMillisecondes;

/**
 * Thread permettant le refreshissement du CountDown par période définie.
 * 
 * @author martinelli-b
 * 
 */
public class ThreadRefreshCountDown extends Thread {

    /** Suivi de l'activité du Thread. */
    private boolean run = false;

    /** . */
    private CountDownService countDownService = null;

    /** . */
    private CountDownActivity countDownScreen = null;

    /**
     * @param countDownScreenInput
     *            Ecran de l'application
     */
    public ThreadRefreshCountDown(final CountDownActivity countDownScreenInput) {
        super();
        countDownService = new CountDownService();
        countDownScreen = countDownScreenInput;
    }

    /** Le message de mise à jour à envoyer vers l'UI d'origine. */
    private Runnable putUpdateOrder = new Runnable() {
        @Override
        public void run() {
            ((TextView) countDownScreen.findViewById(R.id.countdown)).setText(countDownService.getCountDown());
        }
    };

    @Override
    public void run() {
        setRun(true);
        while (run) {
            countDownScreen.getHandler().post(putUpdateOrder);
            try {
                Thread.sleep(FabriqueDureeEnMillisecondes.UNE_SECONDE_EN_MILLISECONDES);
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
