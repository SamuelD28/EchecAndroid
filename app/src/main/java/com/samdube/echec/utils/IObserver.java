package com.samdube.echec.utils;

/**
 * Interface permettant de rendre la classe
 * capable d'observer une class Observable
 *
 * @author Samuel Dube
 */
public interface IObserver {
    /**
     * Method performer lorsque des changements sont
     * signaler
     */
    void update();
}
