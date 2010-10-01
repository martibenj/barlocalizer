package com.barlocalizer.services;

import java.util.Calendar;

/**
 * CountDown Calculation Service.
 * 
 * @author Pierre-Marie Dhaussy
 * @author Benjamin Martinelli
 * @author Gael "La Grosse" Lire
 */
public class CountDownService {

    /**  */
    public static final String ESPACE = " ";

    /** Heure de d�part au Callaghan. */
    private Calendar oCallaghanTime = null;

    /** Heure de fin des Happy Hours. */
    private Calendar finHappyHour = null;

    /** Heure de d�part au Callaghan d�fini � partir de l'heure des Happy Hours. */
    private Calendar oCallaghanTimeAPartirDeDateHappyHour = null;

    /**
     * Constructeur. Initialise les dates qui seront utilis�e lors du refresh du countdown
     */
    public CountDownService() {
        oCallaghanTime = FabriqueDate.getDateDepartPourCallaghan();
        finHappyHour = FabriqueDate.getDateFinHappyHours();
        oCallaghanTimeAPartirDeDateHappyHour = FabriqueDate.getDateDepartPourCallaghanAPartirDeDateFinHappyHours();
    }

    /**
     * Calcul le temps restant.
     * 
     * @return Le temps restant format� sous forme de String
     */
    public String getCountDown() {
        StringBuilder countDown = new StringBuilder();
        Calendar now = Calendar.getInstance();

        Calendar tempsLimite = null;
        if (now.after(oCallaghanTimeAPartirDeDateHappyHour) && now.before(finHappyHour)) {
            tempsLimite = finHappyHour;
        } else if (now.before(oCallaghanTime)) {
            tempsLimite = oCallaghanTime;
        }

        long tempsRestant = tempsLimite.getTimeInMillis() - now.getTimeInMillis();

        long vieilleTeteDeLire = tempsRestant / FabriqueDureeEnMillisecondes.transformerSecondesEnMillisecondes(1);
        long nbSec = vieilleTeteDeLire % FabriqueDureeEnMillisecondes.UNE_MINUTE_EN_SECONDES;

        vieilleTeteDeLire = (vieilleTeteDeLire - nbSec) / FabriqueDureeEnMillisecondes.UNE_MINUTE_EN_SECONDES;
        long nbMin = vieilleTeteDeLire % FabriqueDureeEnMillisecondes.UNE_HEURE_EN_MINUTES;

        vieilleTeteDeLire = (vieilleTeteDeLire - nbMin) / FabriqueDureeEnMillisecondes.UNE_HEURE_EN_MINUTES;
        long nbHeure = vieilleTeteDeLire % FabriqueDureeEnMillisecondes.UN_JOUR_EN_HEURES;

        vieilleTeteDeLire = (vieilleTeteDeLire - nbHeure) / FabriqueDureeEnMillisecondes.UN_JOUR_EN_HEURES;
        long nbJour = vieilleTeteDeLire;

        countDown.append(formatText(nbJour, "jour"));
        countDown.append(formatText(nbHeure, "heure"));
        countDown.append(formatText(nbMin, "minute"));
        countDown.append(formatText(nbSec, "seconde"));
        return countDown.toString();
    }

    /**
     * Formatte le texte pour �tre lisible par un homme n'�tant pas trop en �tat d'�bri�t� avanc�e.
     * 
     * @param amountTime
     *            La dur�e � formater
     * @param wordTime
     *            Le litt�ral correspondant � la dur�e (jour/minute...)
     * @return Le texte format�
     */
    public String formatText(final long amountTime, final String wordTime) {
        StringBuilder text = new StringBuilder();
        if (0 < amountTime) {
            text.append(amountTime);
            text.append(ESPACE);
            text.append(wordTime);
            if (1 < amountTime) {
                text.append("s");
            }
            text.append(ESPACE);
        }
        return text.toString();
    }
}
