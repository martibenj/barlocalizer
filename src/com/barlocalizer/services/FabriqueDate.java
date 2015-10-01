package com.barlocalizer.services;

import java.util.Calendar;

/**
 * Classe permettant de fabriquer des Dates.
 * 
 * @author Benjamin Martinelli
 */
public final class FabriqueDate {

    /**
     * Constructeur priv�.
     */
    private FabriqueDate() {
    }

    /** Jour du Callaghan. */
    public static final int JOUR_SEMAINE_DEPART = Calendar.THURSDAY;

    /** Heure de d�part au Callaghan (oui bon, ok c'est optimiste mais quand m�me ! */
    public static final int HEURE_DEPART = 17;

    /**  */
    public static final int MINUTE_DEPART = 0;

    /**  */
    public static final int SECONDE_DEPART = 0;

    /** Heure de fin des Happy hours au Callaghan. */
    public static final int HEURE_HAPPY = 20;

    /**  */
    public static final int MINUTE_HAPPY = 30;

    /**  */
    public static final int SECONDE_HAPPY = 0;

    /**  */
    public static final int UNE_SEMAINE_EN_JOURS = 7;

    /**
     * Permet de Fabriquer un objet Calendar r�pr�sentant l'heure de d�part au Callaghan et d'ajuster cette date pour
     * qu'elle soit obligatoirement post�rieure � maintenant.
     * 
     * @return un objet Calendar � la date de d�part au Callaghan
     */
    public static Calendar getDateDepartPourCallaghan() {

        Calendar oCallaghanTime = Calendar.getInstance();

        int delta = Calendar.THURSDAY - oCallaghanTime.get(Calendar.DAY_OF_WEEK);

        if (delta < 0) {
            delta += UNE_SEMAINE_EN_JOURS;
        }

        oCallaghanTime.add(Calendar.DAY_OF_WEEK, delta);
        oCallaghanTime.set(Calendar.HOUR_OF_DAY, HEURE_DEPART);
        oCallaghanTime.set(Calendar.MINUTE, MINUTE_DEPART);
        oCallaghanTime.set(Calendar.SECOND, SECONDE_DEPART);

        return oCallaghanTime;

    }

    /**
     * Permet de Fabriquer un objet Calendar r�pr�sentant l'heure de fin des Happy Hours selon la constante
     * {@link HOUR_HAPPY}, {@link MINUTE_HAPPY} et {@link SECONDE_HAPPY}.
     * 
     * @return un objet Calendar � la date de fin des Happy Hours
     */
    public static Calendar getDateFinHappyHours() {
        Calendar finHappyHour = Calendar.getInstance();

        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
            finHappyHour.setTimeInMillis(getDateDepartPourCallaghan().getTimeInMillis());
        }

        finHappyHour.set(Calendar.HOUR_OF_DAY, HEURE_HAPPY);
        finHappyHour.set(Calendar.MINUTE, MINUTE_HAPPY);
        finHappyHour.set(Calendar.SECOND, SECONDE_HAPPY);
        return finHappyHour;
    }

    /**
     * Permet de Fabriquer un objet Calendar r�pr�sentant l'heure de d�part au Callaghan � partir de l'heure de fin des
     * Happy hours. On reste donc sur le m�me jour {@link HOUR_DEPART}, {@link MINUTE_DEPART} et {@link SECONDE_DEPART}.
     * 
     * @return un objet Calendar � ladate de d�part au Callaghan fabriqu� � partir de l'heure de fin des Happy Hours
     */
    public static Calendar getDateDepartPourCallaghanAPartirDeDateFinHappyHours() {
        Calendar departCallaghan = Calendar.getInstance();

        departCallaghan.setTimeInMillis(getDateFinHappyHours().getTimeInMillis());

        departCallaghan.set(Calendar.HOUR_OF_DAY, HEURE_DEPART);
        departCallaghan.set(Calendar.MINUTE, MINUTE_DEPART);
        departCallaghan.set(Calendar.SECOND, SECONDE_DEPART);
        return departCallaghan;
    }

}
