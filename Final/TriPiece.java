package sample;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

//archer class
public class TriPiece extends Piece{
    private Polygon base;
    TriPiece(double s,double h, String id,MainGame mG,PlayField pF,int tN,double[] pos,Deck pD){
        super(s,h,id,mG,pF,tN,pos,pD);
        setName("Archer");
        setHealth(getHealth()+60);
        setMaxHealth((int)getHealth());
        setAtkSpd(1);
        setDamage(getDamage()+15);
        setRange(7);
        setRangeFactor(.5);
        base=new Polygon();
        base.getPoints().addAll(
                0.0, 0.0,
                s, 0.0,
                s/2, s*0.866
        );
        if(getTeamNum()==1){
            base.setFill(Color.VIOLET);
        }else if(getTeamNum()==2){
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
    TriPiece(int r, int l, double s,double h, String id,MainGame mG,PlayField pF,int tN,double[] pos,Deck pD){
        super(r,l,s,h,id,mG,pF,tN,pos,pD);
        setName("Archer");
        setHealth(getHealth()+60);
        setMaxHealth((int)getHealth());
        setAtkSpd(1);
        setDamage(getDamage()+15);
        setRange(7);
        setRangeFactor(.50);
        base=new Polygon();
        base.getPoints().addAll(
                0.0, 0.0,
                s, 0.0,
                s/2, s*0.866
        );
        if(getTeamNum()==1){
            base.setFill(Color.VIOLET);
        }else if(getTeamNum()==2){
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
