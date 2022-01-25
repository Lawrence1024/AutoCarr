package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//paladin class
public class RectPiece extends Piece{
    private Rectangle base;
    RectPiece(double s,double h, String id,MainGame mG,PlayField pF,int tN,double[] pos,Deck pD){
        super(s,h,id,mG,pF,tN,pos,pD);
        setName("Paladin");
        setHealth(getHealth()+250);
        setMaxHealth((int)getHealth());
        setAtkSpd(0.70);
        setDamage(getDamage()+30);
        setRange(1);
        setRangeFactor(0.5);
        base=new Rectangle(0,0,getSize(),getSize());
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
    RectPiece(int r, int l, double s,double h, String id,MainGame mG,PlayField pF,int tN,double[] pos,Deck pD){
        super(r,l,s,h,id,mG,pF,tN,pos,pD);
        setName("Paladin");
        setHealth(getHealth()+250);
        setMaxHealth((int)getHealth());
        setAtkSpd(0.70);
        setDamage(getDamage()+30);
        setRange(1);
        setRangeFactor(0.5);
        base=new Rectangle(0,0,getSize(),getSize());
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
