package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Piece {
    //fields
    private StackPane body;
    private double size;
    private Box parentBox;
    private double health;
    private double maxHealth;
    private String ID;
    private double[] overallPosition;
    boolean isDragging=false;
    private MainGame mainGame;
    private PlayField playField;
    private int teamNum;
    private int cost;
    private int level = 1;
    private Text levels;
    private boolean isDead;
    private int rarity;
    private Rectangle rarityBand;
    private double damage;
    private double dps;
    private double atkSpd;
    private String name;
    private double range;
    private Piece target;
    private Timeline tL;
    private boolean onField=false;
    private Deck parentDeck;
    private Text healthPoints;
    private Timeline attackPacing;
    private int timerCounter;
    private StackPane healthBarRed;
    private Rectangle healthBarGreen;
    private Rectangle rangeBox;
    private double rangeFactor;
    private Pane sell;

    private static ArrayList<Piece> allPiece;

    //constructor
    Piece(double s,double h, String id,MainGame mG,PlayField pF,int tN,double[] pos,Deck pD){
        mainGame=mG;
        playField=pF;
        parentDeck=pD;
        size=s;
        teamNum=tN;
        body=new StackPane();
        body.setPrefSize(s,s);
        parentBox=null;
        body.addEventHandler(MouseEvent.MOUSE_DRAGGED,event -> dragPiece(event));
        body.addEventHandler(MouseEvent.MOUSE_RELEASED,event -> releasePiece(event));
        health=h;
        ID=id;
        body.setLayoutX(pos[0]);
        body.setLayoutY(pos[1]);
        overallPosition=findPosition();
        maxHealth = h;
        isDead = false;

        levels = new Text(Integer.toString(level));

        rarityBand = new Rectangle(s/2,10);
        createRarityBand();


        healthBarGreen = new Rectangle();
        healthBarGreen.setWidth(s);
        healthBarGreen.setHeight(15);
        healthBarGreen.setFill(Color.GREEN);

        healthBarRed = new StackPane();
        healthBarRed.setMaxSize(s,15);
        healthBarRed.setStyle("-fx-background-color:RED");
        healthBarRed.setAlignment(healthBarGreen, Pos.CENTER_LEFT);
        healthBarRed.getChildren().add(healthBarGreen);

        experienceBoost();

    }
    Piece(int r, int l, double s,double h, String id,MainGame mG,PlayField pF,int tN,double[] pos,Deck pD){
        mainGame=mG;
        playField=pF;
        parentDeck=pD;
        size=s;
        teamNum=tN;
        body=new StackPane();
        body.setPrefSize(s,s);
        parentBox=null;
        body.addEventHandler(MouseEvent.MOUSE_DRAGGED,event -> dragPiece(event));
        body.addEventHandler(MouseEvent.MOUSE_RELEASED,event -> releasePiece(event));
        health=h;
        ID=id;
        level = l;
        body.setLayoutX(pos[0]);
        body.setLayoutY(pos[1]);
        overallPosition=findPosition();
        this.rarity=r;
        maxHealth = h;

        isDead = false;
        levels = new Text(Integer.toString(level));

        rarityBand = new Rectangle(s/2,10);
        createRarityBand();


        healthBarGreen = new Rectangle();
        healthBarGreen.setWidth(s);
        healthBarGreen.setHeight(15);
        healthBarGreen.setFill(Color.GREEN);

        healthBarRed = new StackPane();
        healthBarRed.setMaxSize(s,15);
        healthBarRed.setStyle("-fx-background-color:RED");
        healthBarRed.setAlignment(healthBarGreen, Pos.CENTER_LEFT);
        healthBarRed.getChildren().add(healthBarGreen);
        
        experienceBoost();
    }
    //setter/getter


    public double getMaxHealth() { return maxHealth; }
    public double getRangeFactor() { return rangeFactor; }
    public void setRangeFactor(double rangeFactor) { this.rangeFactor = rangeFactor; }
    public Rectangle getRarityBand() { return rarityBand; }
    public void setRarityBand(Rectangle rarityBand) { this.rarityBand = rarityBand; }
    public Rectangle getRangeBox(){return rangeBox;}
    public void setRangeBox(Rectangle rB){rangeBox=rB;}
    public double getSize(){ return size; }
    public StackPane getBody(){ return body; }
    public void setBody(StackPane sP){ body=sP; }
    public Box getParentBox(){ return parentBox; }
    public void setParentBox(Box pB){parentBox=pB;}
    public void setID(String ID) { this.ID = ID; }
    public void setCost(int cost) { this.cost = cost; }
    public double getRange() { return range; }
    public void setRange(double range) { this.range = range; }
    public String getID() { return ID; }
    public double getHealth() { return health; }
    public void setHealth(double health) { this.health = health; }
    public Text getLevels() { return levels; }
    public void setLevels(Text levels) { this.levels = levels; }
    public int getTeamNum() { return teamNum; }
    public int getCost(){return cost;}
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public boolean isDead() { return isDead; }
    public void setDead(boolean dead) { isDead = dead; }
    public int getLevel(){return level;}
    public void setLevel(int lev){level = lev;}
    public int getRarity(){return rarity;}
    public double getDamage(){return damage;}
    public void setDamage(double damage){this.damage = damage; }
    public double getAtkSpd(){return atkSpd;}
    public void setAtkSpd(double atkSpd){this.atkSpd = atkSpd;}
    public Text getHealthPoints(){return healthPoints;}
    public void setHealthPoints(Text t){healthPoints=t;}
    public Timeline getAttackPacing(){return attackPacing;}
    public void setMaxHealth(int mH){maxHealth=mH;}
    public double getDps() { return dps; }
    public void setDps(double dps) { this.dps = dps; }
    public StackPane getHealthBarRed() { return healthBarRed; }
    public void setHealthBarRed(StackPane healthBarRed) { this.healthBarRed = healthBarRed; }
    public Rectangle getHealthBarGreen() { return healthBarGreen; }
    public void setHealthBarGreen(Rectangle healthBarGreen) { this.healthBarGreen = healthBarGreen; }
    public double[] getOverallPosition() { return overallPosition; }
    public void setOverallPosition(double[] oP){overallPosition=oP;}
    public boolean getOnField(){ return onField; }
    public void setOnField(boolean onField) { this.onField = onField; }
    public Timeline getTL() { return tL; }
    public void setTL(Timeline tL) { this.tL = tL; }
    public PlayField getPlayField() { return playField; }
    public void setPlayField(PlayField playField) { this.playField = playField; }
    public int getTimerCounter() { return timerCounter; }
    public void setTimerCounter(int timerCounter) { this.timerCounter = timerCounter; }
    public Piece getTarget() { return target; }
    public void setTarget(Piece target) { this.target = target; }

    public void setAttackPacing(Timeline attackPacing) { this.attackPacing = attackPacing; }

    //public methods
    protected void dragPiece(MouseEvent e){
        body.setLayoutX(e.getSceneX()-(size/2));
        body.setLayoutY(e.getSceneY()-(size/2));
        mainGame.getShop().sellGlowOn();
        for (Box b:playField.getBoxes()) {
            b.notGlow();
        }
        parentDeck.notGlow();
        if(body.getLayoutY()>playField.getBodyDimensions()[1]){
            parentDeck.glow();
        }else{
            Box tempParent=findClosestBox();
            tempParent.glow();
            ArrayList<Box>temp=playField.getColumn();
            for(Box b:temp){
                b.altGlow();
            }
        }
    }

    protected Box findClosestBox(){
        double minDiff=Double.MAX_VALUE;
        int boxCount=0;
        if(getTeamNum()==1){
            for(int i=0;i<playField.getp2Boxes().size();i++){
                Box b=playField.getp2Boxes().get(i);
                double centerX=getBody().getLayoutX()+(size/2);
                double centerY=getBody().getLayoutY()+(size/2);
                double centerBoxX=b.getBody().getLayoutX()+(b.getSize()/2)+mainGame.gDisplay.getBodyDimensions()[0];
                double centerBoxY=b.getBody().getLayoutY()+(b.getSize()/2);
                double diffX=centerX-centerBoxX;
                double diffY=centerY-centerBoxY;
                double diff=Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2));
                if(diff<minDiff){
                    minDiff=diff;
                    boxCount=i;
                }
            }
            return playField.getp2Boxes().get(boxCount);
        }else {
            for(int i=0;i<playField.getp1Boxes().size();i++){
                Box b=playField.getp1Boxes().get(i);
                double centerX=getBody().getLayoutX()+(size/2);
                double centerY=getBody().getLayoutY()+(size/2);
                double centerBoxX=b.getBody().getLayoutX()+(b.getSize()/2)+mainGame.gDisplay.getBodyDimensions()[0];
                double centerBoxY=b.getBody().getLayoutY()+(b.getSize()/2);
                double diffX=centerX-centerBoxX;
                double diffY=centerY-centerBoxY;
                double diff=Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY,2));
                if(diff<minDiff){
                    minDiff=diff;
                    boxCount=i;
                }
            }
            return playField.getp1Boxes().get(boxCount);
        }
    }

    protected void releasePiece(MouseEvent e) {
        isDragging = false;
        mainGame.getShop().sellGlowOff();
        sell = mainGame.getShop().getBody();
        if((body.getLayoutY()>sell.getLayoutY()&&body.getLayoutY()<sell.getLayoutY()+sell.getHeight())&&(body.getLayoutX()<sell.getLayoutX()+sell.getWidth())){
            int index=parentDeck.getPiecesInDock().indexOf(this);
            mainGame.getWrapper().getChildren().remove(this.body);
            parentDeck.getPieces().remove(this);
            if(!onField) {
                parentDeck.movePiecesLeft(index);
                parentDeck.getPiecesInDock().remove(this);
            }
            parentDeck.refundPlayer((rarity+1)*level,teamNum);
            parentDeck.notGlow();
        }else if (body.getLayoutY() > playField.getBodyDimensions()[1]) {
            if (onField) {
                parentDeck.fieldToDeck(this);
                playField.getPieces().remove(this);
                parentDeck.levelUp();
            } else {
                parentDeck.deckToDeck(this);
            }
            onField = false;
            parentDeck.notGlow();
            ArrayList<Box> temp = playField.getColumn();
            for (Box b : temp) {
                b.notGlow();
            }
        }else if(body.getLayoutY()<=playField.getBodyDimensions()[1]){
            parentBox=findClosestBox();
            if(onField){
                parentDeck.fieldToField(this);
            }else{
                parentDeck.deckToField(this);
                playField.getPieces().add(this);
            }
            parentBox=findClosestBox();
            onField=true;
            reposition();
            parentBox.notGlow();
            ArrayList<Box>temp=playField.getColumn();
            for(Box b:temp){
                b.notGlow();
            }
        }
    }
    protected  double[] findPosition(){
        //double xCord=parentBox.getBody().getLayoutX()+mainGame.gDisplay.getBodyDimensions()[0]+(getSize()/2);
        //double yCord=parentBox.getBody().getLayoutY()+(getSize()/2);
        //return new double[]{xCord,yCord};
        return new double[]{getBody().getLayoutX(),getBody().getLayoutY()};
    }

    protected Piece findClosestEnemy(ArrayList<Piece> allPieces){
        double minDiff=Double.MAX_VALUE;
        int boxCount=-1;
        for(int i=0;i<allPieces.size();i++){
            Piece p=allPieces.get(i);
            if(p!=this&&p.getTeamNum()!=getTeamNum()) {
                double centerX = getBody().getLayoutX() + (size / 2);
                double centerY = getBody().getLayoutY() + (size / 2);
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
        if(boxCount==-1){
            return null;
        }
        return allPieces.get(boxCount);
    }
    protected void startFight(ArrayList<Piece> allPieces){
        target=findClosestEnemy(allPieces);
        if(target==null){
            playField.endRound();
        }else{
            moveUpClose(target);

            //   if(inRange(target)) {
            attackPacing = new Timeline(new KeyFrame(Duration.millis(40), ae -> doAttack()));
            attackPacing.setCycleCount(Animation.INDEFINITE);
            //      attackPacing.play();
            //  }

        }
    }

    protected void moveUpClose(Piece target){
        tL=new Timeline(new KeyFrame(Duration.millis(30),ae->movePiece()));
        tL.setCycleCount(Animation.INDEFINITE);
        tL.play();
    }

    protected void movePiece(){
        setOverallPosition(findPosition());
        target.setOverallPosition(target.findPosition());

        double otherX=target.getOverallPosition()[0];
        double otherY=target.getOverallPosition()[1];
        double x=getOverallPosition()[0];
        double y=getOverallPosition()[1];
        double diffX=otherX-x;
        double diffY=otherY-y;
        diffX=diffX>0? diffX+size+(parentBox.getSize()*0.46502976):diffX-size-(parentBox.getSize()*0.46502976);

        double[] movement =new double[]{(diffX/50/2),(diffY/50/2)};
        if(movement[0]<2 && movement[0]>0) movement[0]=1.5;
        if(movement[0]>-2 && movement[0]<0) movement[0]=-1.5;
        if(movement[0]==0) movement[0]=0;
        if(movement[1]<2 && movement[1]>0) movement[1]=2;
        if(movement[1]>-2 && movement[1]<0) movement[1]=-2;
        if(movement[1]==0) movement[1]=0;
        if(diffX<9 && diffX>-9) movement[0]=0;
        if(diffY<3 && diffY>-3) movement[1]=0;

        if(!getRangeBox().getBoundsInParent().intersects(target.getBody().getBoundsInParent())) {
            // System.out.println(this);
            getBody().setLayoutX(getBody().getLayoutX() + movement[0]);
            getBody().setLayoutY(getBody().getLayoutY() + movement[1]);
            getRangeBox().setLayoutX(getBody().getLayoutX()-(size * (rangeFactor*(range-1))));
            getRangeBox().setLayoutY(getBody().getLayoutY()-(size * (rangeFactor*(range-1))));
        }

        if (getRangeBox().getBoundsInParent().intersects(target.getBody().getBoundsInParent())) {
            tL.stop();
            //  System.out.print(this);
            attackPacing.play();
        }
    }

    protected void reposition(){
        if(parentBox!=null) {
            body.setLayoutX(parentBox.getBody().getLayoutX() + mainGame.gDisplay.getBodyDimensions()[0] + (getSize() / 2));
            body.setLayoutY(parentBox.getBody().getLayoutY() + (getSize() / 2));
            rangeBox.setLayoutX(-10000);
            rangeBox.setLayoutY(-10000);
            overallPosition = findPosition();
        }
    }

    protected void doAttack(){

        boolean alive=true;
        if(health<=0) {
            alive = false;
            //System.out.println("I have " + health + " and am part of " + teamNum + ", my opponent has " + target.getHealth());
            calculateHealthBar();
            attackPacing.stop();
            //target.getAttackPacing().stop();
        }
        if(alive) {
            if (timerCounter < (int) (60 - (30 * atkSpd))) timerCounter++;
            else {
                timerCounter = 0;
                if (target.getHealth() == 0) attackPacing.stop();
                //if (inRange(target)) {
                boolean dead = dealDamage(target);
                calculateHealthBar();
                if(dead) {

                    target.setDead();
                    // System.out.println(target);

                    attackPacing.stop();
                    playField.findNewFight(this);
                    //  System.out.println(target);
                }
                //System.out.println("I have " + health + " and I did " + damage + " to my target, " + teamNum );

                //}
            }
        }
    }


    /*protected boolean inRange(Piece enemy){
       // while(target!=null) {
            if (getRangeBox().getBoundsInParent().intersects(target.getBody().getBoundsInParent())) {
                return true;
            }
       // }
        return false;
    }*/

    protected boolean dealDamage(Piece enemy){
        enemy.setHealth(enemy.getHealth()-damage);
        enemy.calculateHealthBar();
        if(enemy.getHealth()<=0) return true;
        else return false;
    }

    protected void calculateHealthBar(){
        if(health > 0) {
            healthBarGreen.setWidth(((health) / maxHealth) * size);
        }
        else if(health <= 0){
            healthBarGreen.setWidth(0);
        }
    }
    protected void restoreHealth(){
        setHealth(maxHealth);
        calculateHealthBar();
    }

    protected void createRarityBand(){
        if(rarity == 0){
            rarityBand.setFill(Color.WHITE);
        }
        else if(rarity == 1){
            rarityBand.setFill(Color.BLUE);
            health+=20;
            maxHealth+=20;
            damage+=10;
        }
        else if(rarity == 2){
            rarityBand.setFill(Color.PURPLE);
            health+=40;
            maxHealth+=40;
            damage+=20;
        }
        else if(rarity == 3){
            rarityBand.setFill(Color.GOLD);
            health+=100;
            maxHealth+=100;
            damage+=40;
        }
    }

    public void setDead(){
        if(isDead==false) {
            //System.out.println(this + "is dead");

            isDead = true;
            mainGame.getWrapper().getChildren().remove(this.getBody());
            playField.getPieces().remove(this);
            playField.getDeadPieces().add(this);
            mainGame.getWinRound().checkIfWinner();
        }
//        playField.getBody().getChildren().remove(this);
    }

    public void revive(){
        //System.out.println("reviving " + this);
        playField.getPieces().add(this);
        restoreHealth();
        mainGame.getWrapper().getChildren().add(this.getBody());
        isDead = false;
    }

    public Rectangle makeRangeBox(){
        // System.out.println("Hi i'm team " + teamNum+ " with "+ range + " " + size + "and a " + this);
        Rectangle rB = new Rectangle (0,0,size*getRange(),size*getRange());
        //System.out.println("Rectangle with range " + getRange()+ " created");
        rB.setFill(Color.RED);
        return rB;
    }
    protected void experienceBoost(){
        maxHealth+=Math.pow(5,level);
        health=maxHealth;
        damage+=Math.pow(3,level);
        atkSpd+=level*0.05;
    }
    //private methods

}
