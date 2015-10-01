package com.barlocalizer.services;

/**
 * Classe permettant de fabriquer des Dates.
 * 
 * @author Benjamin Martinelli
 */
public final class FabriqueDureeEnMillisecondes {

    /** */
    public static final int UNE_SECONDE_EN_MILLISECONDES = 1000;

    /** */
    public static final int UNE_MINUTE_EN_SECONDES = 60;

    /** */
    public static final int UNE_HEURE_EN_MINUTES = 60;

    /** */
    public static final int UN_JOUR_EN_HEURES = 24;

    /**
     * Constructeur privé.
     */
    private FabriqueDureeEnMillisecondes() {
    }

    /**
     * Transforme un nombre de jours en millisecondes.
     * 
     * @param nombreJours
     *            Nombre de jours
     * @return Les jours en millisecondes
     */
    public static int transformerJoursEnMillisecondes(final int nombreJours) {
        return nombreJours * transformerSecondesEnMillisecondes(UN_JOUR_EN_HEURES);
    }

    /**
     * Transforme un nombre d'heures en millisecondes.
     * 
     * @param nombreHeures
     *            Nombre d'heures
     * @return Les heures en millisecondes
     */
    public static int transformerHeuresEnMillisecondes(final int nombreHeures) {
        return nombreHeures * transformerSecondesEnMillisecondes(UNE_HEURE_EN_MINUTES);
    }

    /**
     * Transforme un nombre de minutes en millisecondes.
     * 
     * @param nombreMinutes
     *            Nombre de minutes
     * @return Les jours en millisecondes
     */
    public static int transformerMinutesEnMillisecondes(final int nombreMinutes) {
        return nombreMinutes * transformerSecondesEnMillisecondes(UNE_MINUTE_EN_SECONDES);
    }

    /**
     * Transforme un nombre de secondes en millisecondes.
     * 
     * @param nombreSecondes
     *            Nombre de secondes
     * @return Les secondes en millisecondes
     */
    public static int transformerSecondesEnMillisecondes(final int nombreSecondes) {
        return nombreSecondes * UNE_SECONDE_EN_MILLISECONDES;
    }

}
