package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.ArrayList;

//priest class
public class PentPiece extends Piece {
    private Polygon base;
    PentPiece(double s,double h, String id,MainGame mG,PlayField pF,int tN,double[] pos,Deck pD){
        super(s,h,id,mG,pF,tN,pos,pD);
        setName("Priest");
        setHealth(getHealth()+150);
        setMaxHealth((int)getHealth());
        setAtkSpd(1.25);
        setDamage(getDamage()+7);
        setRange(5);
        setRangeFactor(0.43);
        base=new Polygon();
        base.getPoints().addAll(
                0.0, 0.0,
                s, 0.0,
                s+(0.309*s), s*0.951,
                s/2, s*0.951 + s*0.588,
                0-(0.309*s), s*0.951
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
    PentPiece(int r,int l,double s,double h, String id,MainGame mG,PlayField pF,int tN,double[] pos,Deck pD){
        super(r,l,s,h,id,mG,pF,tN,pos,pD);
        setName("Priest");
        setHealth(getHealth()+150);
        setMaxHealth((int)getHealth());
        setAtkSpd(1.25);
        setDamage(getDamage()+7);
        setRange(5);
        setRangeFactor(0.43);
        base=new Polygon();
        base.getPoints().addAll(
                0.0, 0.0,
                s, 0.0,
                s+(0.309*s), s*0.951,
                s/2, s*0.951 + s*0.588,
                0-(0.309*s), s*0.951
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

    @Override
    protected Piece findClosestEnemy(ArrayList<Piece> allPieces){
        double minDiff=Double.MAX_VALUE;
        double lowestHP = Double.MAX_VALUE;
        int boxCount=-1;
        for(int i=0;i<allPieces.size();i++){
            Piece p=allPieces.get(i);
            if(p!=this&&p.getTeamNum()==getTeamNum()) {
                if((p.getHealth() / p.getMaxHealth()) < lowestHP){
                    lowestHP=(p.getHealth()/p.getMaxHealth());
                    if(lowestHP!=1)boxCount = i;
                }
            }
        }
        if(boxCount==-1){
            for(int i=0;i<allPieces.size();i++) {
                Piece p=allPieces.get(i);
                if(p!=this&&p.getTeamNum()==getTeamNum()) {
                    double centerX = getBody().getLayoutX() + (getSize() / 2);
                    double centerY = getBody().getLayoutY() + (getSize() / 2);
                    double centerBoxX = p.getBody().getLayoutX() + (p.getSize() / 2);
                    double centerBoxY = p.getBody().getLayoutY() + (p.getSize() / 2);
                    double diffX = centerX - centerBoxX;
                    double diffY = centerY - centerBoxY;
                    double diff = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
                    if (diff < minDiff) {
                        minDiff = diff;
                        boxCount = i;
                    }
                }
            }
        }
        if(boxCount==-1){
            return null;
        }
        return allPieces.get(boxCount);
    }
    @Override
    protected void doAttack(){
        boolean alive=true;
        if(getHealth()<=0) {
            alive = false;
            calculateHealthBar();
            getAttackPacing().stop();        }
        if(alive) {
            if (getTimerCounter() < (int) (60 - (30 * getAtkSpd()))) setTimerCounter(getTimerCounter()+1);
            else {
                setTimerCounter(0);
                if (getTarget().getHealth() == 0) getAttackPacing().stop();
                boolean fullHealth = healAlly();
                calculateHealthBar();
                if(fullHealth || getTarget().getHealth()<=0) {
                    getAttackPacing().stop();
                    getPlayField().findNewFight(this);
                }
            }
        }
    }
    @Override
    protected void startFight(ArrayList<Piece> allPieces){
        setTarget(findClosestEnemy(allPieces));
        if(getTarget()==null){
            if(allPieces.indexOf(this)!=-1){
                setTarget(this);
                setAttackPacing(new Timeline(new KeyFrame(Duration.millis(40), ae -> doAttack())));
                getAttackPacing().setCycleCount(Animation.INDEFINITE);
                if(getTarget()==this)getAttackPacing().play();

            }else{
                getPlayField().endRound();
            }
        }else{
            if(getTarget()!=this){ moveUpClose(getTarget());}
            setAttackPacing(new Timeline(new KeyFrame(Duration.millis(40), ae -> doAttack())));
            getAttackPacing().setCycleCount(Animation.INDEFINITE);
            if(getTarget()==this)getAttackPacing().play();
        }
    }

    private boolean healAlly(){
        if(getTarget().getHealth()<=0)return true;
        getTarget().setHealth(getTarget().getHealth()+getDamage());
        if(getTarget().getHealth()>getTarget().getMaxHealth()) {
            getTarget().setHealth(getTarget().getMaxHealth());
        }
        getTarget().calculateHealthBar();
        if(getTarget().getHealth()>=getTarget().getMaxHealth()) {
            return true;
        }
        else return false;
    }

}
