package deplacement;

import echiquier.Position;
import piece.IObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Classe permettant de créer une logique de déplacement
 * sur un échelle de positions.
 *
 * @author Samuel Dubé
 */
public abstract class Deplacement implements IObserver {

    private ArrayList<Position> m_positionsDisponibles = new ArrayList<>();

    public Deplacement(Position p_pointOrigine) {
        calculerPossibilites(p_pointOrigine);
    }

    /**
     * Getter pour obtenir la liste d'incrémentations du déplacement
     *
     * @return La liste d'incrémentations
     */
    public abstract Incrementation[] getIncrementations();

    /**
     * Getter pour obtenir le nombre de pas maximum du déplacement
     *
     * @return Nombre de pas maximum
     */
    public abstract int getPasMaximum();

    /**
     * Getter pour obtenir les positions disponibles pour deplacements
     *
     * @return Liste de positions disponibles
     */
    public Position[] getPositionsDisponibles() {
        return m_positionsDisponibles.toArray(new Position[0]);
    }

    /**
     * Methode pour retirer des possibiles de position disponible
     * pour deplacements
     *
     * @param p_positions Possibilites a retirer
     */
    public void retirerPositions(Position... p_positions) {
        for (Position position : p_positions) {
            m_positionsDisponibles.remove(position);
        }
    }

    /**
     * Methode pour ajouter des possibilites de positions disponible
     * pour deplacement.
     *
     * @param p_positions Possibilite a ajouter
     */
    public void ajouterPositions(Position... p_positions) {
        for (Position position : p_positions) {
            if(!m_positionsDisponibles.contains(position)){
                m_positionsDisponibles.add(position);
            }
        }
    }

    /**
     * Méthode qui calcule toutes les possibilités de déplacements
     * pour chaques pas.
     *
     * @param p_pointOrigine Position le point d'origine des possibilités
     * @return Liste de positions où un déplacement est possible
     */
    private void calculerPossibilites(Position p_pointOrigine) {
        ArrayList<Position> possibilites = new ArrayList<>();

        for (int sequence = 1; sequence <= getPasMaximum(); sequence++) {
            for (Incrementation incrementation : getIncrementations()) {

                int coordonneX = p_pointOrigine.getX() + incrementation.getIncrementationX() * sequence;
                int coordonneY = p_pointOrigine.getY() + incrementation.getIncrementationY() * sequence;

                if (Position.estDansLesLimites(coordonneX, coordonneY)) {
                    possibilites.add(new Position(coordonneX, coordonneY));
                }
            }
        }

        m_positionsDisponibles = possibilites;
    }

    @Override
    public void update() {
        calculerPossibilites(null);
    }

    //TODO: Temporaire, trouver une meilleure implementations
    @Override
    public void update(Object p_nouvelleValeur) {
        if(p_nouvelleValeur instanceof Position){
            Position pointOrigine = (Position)p_nouvelleValeur;
            calculerPossibilites(pointOrigine);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPasMaximum(), Arrays.hashCode(getIncrementations()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Deplacement)) {
            return false;
        }

        Deplacement o = (Deplacement) obj;
        return o.getPasMaximum() == getPasMaximum() &&
                Arrays.equals(o.getIncrementations(), getIncrementations());
    }
}
