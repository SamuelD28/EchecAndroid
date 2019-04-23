package piece;

import deplacement.Deplacement;
import deplacement.DeplacementTour;
import echiquier.Position;
import piece.Piece.CouleurPiece;

import static piece.Piece.CouleurPiece.*;

public class TestTour extends TestPiece {
    @Override
    public int getForceAttendu() {
        return 2;
    }

    @Override
    public char getRepresentationAttendu() {
        return 't';
    }

    @Override
    public Deplacement getDeplacementAttendu() {
        return new DeplacementTour(getPiece().getPosition());
    }

    @Override
    public Position getPositionAttendu() {
        return new Position(0,0);
    }

    @Override
    public CouleurPiece getCouleurAttendu() {
        return BLANC;
    }

    @Override
    public Piece getPiece() {
        return new Tour(new Position(0, 0));
    }
}
