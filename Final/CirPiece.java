package sample;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//wizard class
public class CirPiece extends Piece {
    private Circle base;

    CirPiece(double s, double h, String id, MainGame mG, PlayField pF, int tN, double[] pos, Deck pD) {
        super(s, h, id, mG, pF, tN, pos, pD);
        setName("Wizard");
        setHealth(getHealth() + 50);
        setMaxHealth((int) getHealth());
        setAtkSpd(0.5);
        setDamage(getDamage() + 30);
        setRange(5);
        setRangeFactor(0.4);

        base = new Circle(0, 0, getSize());
        if (getTeamNum() == 1) {
            base.setFill(Color.VIOLET);
        } else if (getTeamNum() == 2) {
            base.setFill(Color.BROWN);
        }

        getBody().getChildren().add(base);
        getBody().setAlignment(getRarityBand(), Pos.TOP_CENTER);
        getBody().getChildren().add(getRarityBand());
        getBody().getChildren().add((getLevels()));
        getBody().setAlignment(getHealthBarRed(), Pos.BOTTOM_CENTER);
        getBody().getChildren().add(getHealthBarRed());
        setRangeBox(makeRangeBox());

    }

    //polymorphic constructor for level ups
    CirPiece(int r, int l, double s, double h, String id, MainGame mG, PlayField pF, int tN, double[] pos, Deck pD) {
        super(r, l, s, h, id, mG, pF, tN, pos, pD);
        setName("Wizard");
        setHealth(getHealth() + 50);
        setMaxHealth((int) getHealth());
        setAtkSpd(0.5);
        setDamage(getDamage() + 30);
        setRange(5);
        setRangeFactor(0.4);

        base = new Circle(0, 0, getSize());
        if (getTeamNum() == 1) {
            base.setFill(Color.VIOLET);
        } else if (getTeamNum() == 2) {
            base.setFill(Color.BROWN);
        }

        getBody().getChildren().add(base);
        getBody().setAlignment(getRarityBand(), Pos.TOP_CENTER);
        getBody().getChildren().add(getRarityBand());
        getBody().getChildren().add((getLevels()));
        getBody().setAlignment(getHealthBarRed(), Pos.BOTTOM_CENTER);
        getBody().getChildren().add(getHealthBarRed());
        setRangeBox(makeRangeBox());
    }
}
