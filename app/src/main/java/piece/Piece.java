package piece;

import deplacement.Deplacement;
import echiquier.Position;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Piece implements IObservable {
    public enum CouleurPiece {
        BLANC,
        NOIR
    }

    private Deplacement m_deplacement;

    private ArrayList<IObserver> m_observateurs;

    private Position m_position;

    private CouleurPiece m_couleur;

    public Piece(Position p_position, Deplacement p_deplacement) {
        if (p_position.getY() > 3) {
            m_couleur = CouleurPiece.NOIR;
        } else {
            m_couleur = CouleurPiece.BLANC;
        }

        this.m_position = p_position;
        this.m_deplacement = p_deplacement;

        Subscribe(m_deplacement);
    }

    public void setPosition(Position p_position) {
        if(p_position != null){
            this.m_position = p_position;
        }
        Notify();
    }

    public CouleurPiece getCouleur() {
        return m_couleur;
    }

    public Position getPosition() {
        return m_position;
    }

    public Deplacement getDeplacement(){
        return m_deplacement;
    }

    public abstract char getRepresentation();

    public abstract int getForce();

    @Override
    public void Subscribe(IObserver p_observateur) {
        if(m_observateurs == null){
            m_observateurs = new ArrayList<>();
        }

        m_observateurs.add(p_observateur);
    }

    //TODO : Trouver une meilleure implementation de la methode update
    @Override
    public void Notify() {
        for(IObserver obersvateur : m_observateurs){
            obersvateur.update(m_position);
        }
    }

    @Override
    public void Unsubscribe(IObserver p_observateur) {
        m_observateurs.remove(p_observateur);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return  piece.getPosition().equals(getPosition()) &&
                piece.getCouleur() == getCouleur() &&
                piece.getDeplacement().equals(getDeplacement()) &&
                piece.getForce() == getForce() &&
                piece.getRepresentation() == getRepresentation();
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_position, m_couleur);
    }
}
