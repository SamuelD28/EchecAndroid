package com.samdube.echec.utils;

/**
 * Interface permettant de rendre une classe observable
 * par un observateur
 *
 * @author Samuel Dube
 */
public interface IObservable {
    /**
     * Methode pour ajouter un observateur
     * a la liste d'observateur de la classe
     *
     * @param p_observateur Observateur a ajouter
     */
    void Subscribe(IObserver p_observateur);

    /**
     * Notifie les observateurs d'un changement
     */
    void Notify();

    /**
     * Retirer un observateur de la liste
     * d"observateur de la classe
     *
     * @param p_observateur Observateur a retirer
     */
    void Unsubscribe(IObserver p_observateur);
}
