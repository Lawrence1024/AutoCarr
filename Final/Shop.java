package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.shape.Shape;

import java.util.Stack;


public class Shop {
    //fields
    private PlayField pF;
    private MainGame mG;
    private Player p1;
    private Player p2;
    private Deck d1;
    private Deck d2;
    private int teamnum1;
    private int teamnum2;
    private Pane body = new Pane();
    private Rectangle base;
    private Rectangle r,r1,a1,a2,a3,a4,a5;
    private Pane btn,btn1,u1,u2,u3,u4,u5;
    private StackPane sell;
    private int num1,num2,num3,num4,num5;
    String class1,class2,class3,class4,class5;
    private int counter=1;
    private int turn=0;
    private boolean check=false;
    private double [] screenDimensions;
    private double [] bodyDimensions;
    //constructor
    public Shop(){
        screenDimensions=GlobalVariable.screenDimensions;
        bodyDimensions=new double[]{screenDimensions[0]*1,screenDimensions[1]*0.16};
        base = new Rectangle(bodyDimensions[0],bodyDimensions[1]);
        base.setFill(Color.LIGHTGREEN);
        body.getChildren().add(base);
        a1=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/1.75);
        refreshShop();
        r=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/2);
        r.setFill(Color.TAN);
        r1=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/2);
        r1.setFill(Color.STEELBLUE);
        r1.setY(bodyDimensions[1]/2);
        Text txt=new Text(bodyDimensions[0]/100,bodyDimensions[1]/8,"Refresh");
        txt.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/100));
        txt.setFill(Color.WHITE);
        Text txt1=new Text(bodyDimensions[0]/100,bodyDimensions[1]/8+(bodyDimensions[1]/2),"Drag here to sell");
        txt1.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/100));
        txt1.setFill(Color.WHITE);
        StackPane gold=new StackPane();
        gold.setLayoutX(bodyDimensions[0]/100);
        gold.setLayoutY(bodyDimensions[1]/8);
        Circle c=new Circle(bodyDimensions[0]/175);
        c.setFill(Color.GOLD);
        Text g=new Text("G");
        g.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/175));
        g.setFill(Color.BLACK);
        gold.getChildren().addAll(c,g);
        Text num=new Text(bodyDimensions[0]/100+bodyDimensions[0]/80,bodyDimensions[1]/8+bodyDimensions[1]/10,"2");
        num.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/100));
        num.setFill(Color.WHITE);
        Text num1=new Text(bodyDimensions[0]/100+bodyDimensions[0]/80,(bodyDimensions[1]/8+(bodyDimensions[1]/2))+bodyDimensions[1]/10,"4");
        num1.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/100));
        num1.setFill(Color.WHITE);
        btn1= new Pane();
        btn= new Pane();
        btn.getChildren().addAll(r1,txt1);
        btn1.getChildren().addAll(r,txt,gold,num);
        body.getChildren().addAll(btn,btn1);
        actions();
    }
    //setter/getter
    public Pane getBody(){return body;}
    public double[] getBodyDimensions(){return bodyDimensions;}
    public void setBodyDimensions(double[] bD) {
        bodyDimensions = bD;
        base.setWidth(bD[0]);
        base.setHeight(bD[1]);
    }
    public void setPlayField(PlayField p){
        pF=p;
    }
    public void setMainGame(MainGame m){
        mG=m;
    }
    public void setPlayers(Player pl1,Player pl2){
        p1=pl1;
        p2=pl2;
        d1=p1.getDeck();
        d2=p2.getDeck();
        teamnum1=p1.getTeamNum();
        teamnum2=p2.getTeamNum();
    }

    public Pane getBtn() { return btn; }
    //methods
    public void actions(){
        r.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                if(turn%3!=2) {
                    if(turn%3==0&&p1.getMoney()-2>=0){
                        p1.setMoney(p1.getMoney()-2);
                        mG.getGoldDisplay().updateMoney(1);
                        if (check) {
                            body.getChildren().removeAll(btn, btn1, u1, u2, u3, u4, u5);
                        }
                        refreshShop();
                        body.getChildren().addAll(btn, btn1);
                        actions();
                    }else if(turn%3==1&&p2.getMoney()-2>=0){
                        p2.setMoney(p2.getMoney()-2);
                        mG.getGoldDisplay().updateMoney(2);
                        if (check) {
                            body.getChildren().removeAll(btn, btn1, u1, u2, u3, u4, u5);
                        }
                        refreshShop();
                        body.getChildren().addAll(btn, btn1);
                        actions();
                    }
                }
            }
        });
        r.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                r.setFill(Color.WHEAT);
            }
        });/*
        r1.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                r1.setFill(Color.SKYBLUE);
            }
        });
        r1.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                r1.setFill(Color.STEELBLUE);
            }
        });*/
        r.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                r.setFill(Color.TAN);
            }
        });
        //unit 1
        u1.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                a1.setFill(Color.PEACHPUFF);
            }
        });
        u1.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                a1.setFill(Color.BEIGE);
            }
        });
        u1.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                if(turn%3==0){
                    if(p1.getMoney()-num1>=0&&p1.getDeck().getSlots().size()>p1.getDeck().getPieces().size()){
                        body.getChildren().removeAll(u1);
                        p1.setMoney(p1.getMoney()-num1);
                        mG.getGoldDisplay().updateMoney(1);
                        if(class1=="Wizard"){
                            p1.getDeck().createWizard(num1);
                        }else if(class1=="Archer"){
                            p1.getDeck().createArcher(num1);
                        }else if(class1=="Paladin"){
                            p1.getDeck().createPaladin(num1);
                        }else if(class1=="Priest"){
                            p1.getDeck().createPriest(num1);
                        }else{
                            p1.getDeck().createBerserker(num1);
                        }
                        p1.getDeck().levelUp();
                    }
                }else if(turn%3==1){
                    if(p2.getMoney()-num1>=0&&p2.getDeck().getSlots().size()>p2.getDeck().getPieces().size()){
                        body.getChildren().removeAll(u1);
                        p2.setMoney(p2.getMoney()-num1);
                        mG.getGoldDisplay().updateMoney(2);
                        if(class1=="Wizard"){
                            p2.getDeck().createWizard(num1);
                        }else if(class1=="Archer"){
                            p2.getDeck().createArcher(num1);
                        }else if(class1=="Paladin"){
                            p2.getDeck().createPaladin(num1);
                        }else if(class1=="Priest"){
                            p2.getDeck().createPriest(num1);
                        }else{
                            p2.getDeck().createBerserker(num1);
                        }
                    }
                    p2.getDeck().levelUp();
                }
                actions();
            }
        });
        //unit 2
        u2.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                a2.setFill(Color.PEACHPUFF);
            }
        });
        u2.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                a2.setFill(Color.BEIGE);
            }
        });
        u2.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                if(turn%3==0){
                    if(p1.getMoney()-num2>=0&&p1.getDeck().getSlots().size()>p1.getDeck().getPieces().size()){
                        body.getChildren().removeAll(u2);
                        p1.setMoney(p1.getMoney()-num2);
                        mG.getGoldDisplay().updateMoney(1);
                        if(class2=="Wizard"){
                            p1.getDeck().createWizard(num2);
                        }else if(class2=="Archer"){
                            p1.getDeck().createArcher(num2);
                        }else if(class2=="Paladin"){
                            p1.getDeck().createPaladin(num2);
                        }else if(class2=="Priest"){
                            p1.getDeck().createPriest(num2);
                        }else{
                            p1.getDeck().createBerserker(num2);
                        }
                    }
                    p1.getDeck().levelUp();
                }else if(turn%3==1){
                    if(p2.getMoney()-num2>=0&&p2.getDeck().getSlots().size()>p2.getDeck().getPieces().size()){
                        body.getChildren().removeAll(u2);
                        p2.setMoney(p2.getMoney()-num2);
                        mG.getGoldDisplay().updateMoney(2);
                        if(class2=="Wizard"){
                            p2.getDeck().createWizard(num2);
                        }else if(class2=="Archer"){
                            p2.getDeck().createArcher(num2);
                        }else if(class2=="Paladin"){
                            p2.getDeck().createPaladin(num2);
                        }else if(class2=="Priest"){
                            p2.getDeck().createPriest(num2);
                        }else{
                            p2.getDeck().createBerserker(num2);
                        }
                    }
                    p2.getDeck().levelUp();
                }
                actions();
            }
        });
        //unit 3
        u3.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                a3.setFill(Color.PEACHPUFF);
            }
        });
        u3.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                a3.setFill(Color.BEIGE);
            }
        });
        u3.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                if(turn%3==0){
                    if(p1.getMoney()-num3>=0&&p1.getDeck().getSlots().size()>p1.getDeck().getPieces().size()){
                        body.getChildren().removeAll(u3);
                        p1.setMoney(p1.getMoney()-num3);
                        mG.getGoldDisplay().updateMoney(1);
                        if(class3=="Wizard"){
                            p1.getDeck().createWizard(num3);
                        }else if(class3=="Archer"){
                            p1.getDeck().createArcher(num3);
                        }else if(class3=="Paladin"){
                            p1.getDeck().createPaladin(num3);
                        }else if(class3=="Priest"){
                            p1.getDeck().createPriest(num3);
                        }else{
                            p1.getDeck().createBerserker(num3);
                        }
                        p1.getDeck().levelUp();
                    }
                }else if(turn%3==1){
                    if(p2.getMoney()-num3>=0&&p2.getDeck().getSlots().size()>p2.getDeck().getPieces().size()){
                        body.getChildren().removeAll(u3);
                        p2.setMoney(p2.getMoney()-num3);
                        mG.getGoldDisplay().updateMoney(2);
                        if(class3=="Wizard"){
                            p2.getDeck().createWizard(num3);
                        }else if(class3=="Archer"){
                            p2.getDeck().createArcher(num3);
                        }else if(class3=="Paladin"){
                            p2.getDeck().createPaladin(num3);
                        }else if(class3=="Priest"){
                            p2.getDeck().createPriest(num3);
                        }else{
                            p2.getDeck().createBerserker(num3);
                        }
                    }
                    p2.getDeck().levelUp();
                }
                actions();
            }
        });
        //unit 4
        u4.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                a4.setFill(Color.PEACHPUFF);
            }
        });
        u4.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                a4.setFill(Color.BEIGE);
            }
        });
        u4.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                if(turn%3==0){
                    if(p1.getMoney()-num4>=0&&p1.getDeck().getSlots().size()>p1.getDeck().getPieces().size()){
                        body.getChildren().removeAll(u4);
                        p1.setMoney(p1.getMoney()-num4);
                        mG.getGoldDisplay().updateMoney(1);
                        if(class4=="Wizard"){
                            p1.getDeck().createWizard(num4);
                        }else if(class4=="Archer"){
                            p1.getDeck().createArcher(num4);
                        }else if(class4=="Paladin"){
                            p1.getDeck().createPaladin(num4);
                        }else if(class4=="Priest"){
                            p1.getDeck().createPriest(num4);
                        }else{
                            p1.getDeck().createBerserker(num4);
                        }
                        p1.getDeck().levelUp();
                    }
                }else if(turn%3==1){
                    if(p2.getMoney()-num4>=0&&p2.getDeck().getSlots().size()>p2.getDeck().getPieces().size()){
                        body.getChildren().removeAll(u4);
                        p2.setMoney(p2.getMoney()-num4);
                        mG.getGoldDisplay().updateMoney(2);
                        if(class4=="Wizard"){
                            p2.getDeck().createWizard(num4);
                        }else if(class4=="Archer"){
                            p2.getDeck().createArcher(num4);
                        }else if(class4=="Paladin"){
                            p2.getDeck().createPaladin(num4);
                        }else if(class4=="Priest"){
                            p2.getDeck().createPriest(num4);
                        }else{
                            p2.getDeck().createBerserker(num4);
                        }
                        p2.getDeck().levelUp();
                    }
                }
                actions();
            }
        });
        //unit 5
        u5.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                a5.setFill(Color.PEACHPUFF);
            }
        });
        u5.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                a5.setFill(Color.BEIGE);
            }
        });
        u5.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                if(turn%3==0){
                    if(p1.getMoney()-num5>=0&&p1.getDeck().getSlots().size()>p1.getDeck().getPieces().size()){
                        body.getChildren().removeAll(u5);
                        p1.setMoney(p1.getMoney()-num5);
                        mG.getGoldDisplay().updateMoney(1);
                        if(class5=="Wizard"){
                            p1.getDeck().createWizard(num5);
                        }else if(class5=="Archer"){
                            p1.getDeck().createArcher(num5);
                        }else if(class5=="Paladin"){
                            p1.getDeck().createPaladin(num5);
                        }else if(class5=="Priest"){
                            p1.getDeck().createPriest(num5);
                        }else{
                            p1.getDeck().createBerserker(num5);
                        }
                        p1.getDeck().levelUp();
                    }
                }else if(turn%3==1){
                    if(p2.getMoney()-num5>=0&&p2.getDeck().getSlots().size()>p2.getDeck().getPieces().size()){
                        body.getChildren().removeAll(u5);
                        p2.setMoney(p2.getMoney()-num5);
                        mG.getGoldDisplay().updateMoney(2);
                        if(class5=="Wizard"){
                            p2.getDeck().createWizard(num5);
                        }else if(class5=="Archer"){
                            p2.getDeck().createArcher(num5);
                        }else if(class5=="Paladin"){
                            p2.getDeck().createPaladin(num5);
                        }else if(class5=="Priest"){
                            p2.getDeck().createPriest(num5);
                        }else{
                            p2.getDeck().createBerserker(num5);
                        }
                        p2.getDeck().levelUp();
                    }
                }
                actions();
            }
        });
    }
    public void refreshShop(){
        //unit 1
        u1=new Pane();
        a1=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]);
        a1.setX(bodyDimensions[0]/6);
        a1.setFill(Color.BEIGE);
        a1.setStroke(Color.BLACK);
        num1=rarityCalc();
        Rectangle b1=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/2.333);
        b1.setX(bodyDimensions[0]/6);
        b1.setY(bodyDimensions[1]/1.75);
        b1.setStroke(Color.BLACK);
        if(num1==1){
            b1.setFill(Color.GREY);
        }else if(num1==2){
            b1.setFill(Color.BLUE);
        }else if(num1==3){
            b1.setFill(Color.PURPLE);
        }else{
            b1.setFill(Color.YELLOW);
        }
        StackPane gold1=new StackPane();
        gold1.setLayoutX(bodyDimensions[0]/6+bodyDimensions[0]/8);
        gold1.setLayoutY(bodyDimensions[1]/1.6);
        Circle c1=new Circle(bodyDimensions[0]/175);
        c1.setFill(Color.GOLD);
        Text g1=new Text("G");
        g1.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/175));
        g1.setFill(Color.BLACK);
        gold1.getChildren().addAll(c1,g1);
        Text t1=new Text(bodyDimensions[0]/6+bodyDimensions[0]/7,bodyDimensions[1]/1.375,Integer.toString(num1));
        t1.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/100));
        t1.setFill(Color.BLACK);
        u1.getChildren().addAll(a1,b1,gold1,t1);
        class1=randomClass();
        Text n1=new Text(bodyDimensions[0]/6+bodyDimensions[0]/20,bodyDimensions[1]/1.375,class1);
        n1.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/90));
        n1.setFill(Color.BLACK);
        u1.getChildren().addAll(n1);
        counter++;
        //unit 2
        u2=new Pane();
        a2=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/1.75);
        a2.setX(bodyDimensions[0]/3);
        a2.setFill(Color.BEIGE);
        a2.setStroke(Color.BLACK);
        num2=rarityCalc();
        Rectangle b2=new Rectangle();
        b2.setX(bodyDimensions[0]/3);
        b2.setY(bodyDimensions[1]/1.75);
        b2.setWidth(bodyDimensions[0]/6);
        b2.setHeight(bodyDimensions[1]/2.333);
        b2.setStroke(Color.BLACK);
        if(num2==1){
            b2.setFill(Color.GREY);
        }else if(num2==2){
            b2.setFill(Color.BLUE);
        }else if(num2==3){
            b2.setFill(Color.PURPLE);
        }else{
            b2.setFill(Color.YELLOW);
        }
        StackPane gold2=new StackPane();
        gold2.setLayoutX(bodyDimensions[0]/3+bodyDimensions[0]/8);
        gold2.setLayoutY(bodyDimensions[1]/1.6);
        Circle c2=new Circle(bodyDimensions[0]/175);
        c2.setFill(Color.GOLD);
        Text g2=new Text("G");
        g2.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/175));
        g2.setFill(Color.BLACK);
        gold2.getChildren().addAll(c2,g2);
        Text t2=new Text(bodyDimensions[0]/3+bodyDimensions[0]/7,bodyDimensions[1]/1.375,Integer.toString(num2));
        t2.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/100));
        t2.setFill(Color.BLACK);
        u2.getChildren().addAll(a2,b2,gold2,t2);
        class2=randomClass();
        Text n2=new Text(bodyDimensions[0]/3+bodyDimensions[0]/20,bodyDimensions[1]/1.375,class2);
        n2.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/90));
        n2.setFill(Color.BLACK);
        u2.getChildren().addAll(n2);
        counter++;
        //unit 3
        u3=new Pane();
        a3=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/1.75);
        a3.setX(bodyDimensions[0]/2);
        a3.setFill(Color.BEIGE);
        a3.setStroke(Color.BLACK);
        num3=rarityCalc();
        Rectangle b3=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/2.333);
        b3.setX(bodyDimensions[0]/2);
        b3.setY(bodyDimensions[1]/1.75);
        b3.setStroke(Color.BLACK);
        if(num3==1){
            b3.setFill(Color.GREY);
        }else if(num3==2){
            b3.setFill(Color.BLUE);
        }else if(num3==3){
            b3.setFill(Color.PURPLE);
        }else{
            b3.setFill(Color.YELLOW);
        }
        StackPane gold3=new StackPane();
        gold3.setLayoutX(bodyDimensions[0]/2+bodyDimensions[0]/8);
        gold3.setLayoutY(bodyDimensions[1]/1.6);
        Circle c3=new Circle(bodyDimensions[0]/175);
        c3.setFill(Color.GOLD);
        Text g3=new Text("G");
        g3.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/175));
        g3.setFill(Color.BLACK);
        gold3.getChildren().addAll(c3,g3);
        Text t3=new Text(bodyDimensions[0]/2+bodyDimensions[0]/7,bodyDimensions[1]/1.375,Integer.toString(num3));
        t3.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/100));
        t3.setFill(Color.BLACK);
        u3.getChildren().addAll(a3,b3,gold3,t3);
        class3=randomClass();
        Text n3=new Text(bodyDimensions[0]/2+bodyDimensions[0]/20,bodyDimensions[1]/1.375,class3);
        n3.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/90));
        n3.setFill(Color.BLACK);
        u3.getChildren().addAll(n3);
        counter++;
        //unit 4
        u4=new Pane();
        a4=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/1.75);
        a4.setX(bodyDimensions[0]/1.5);
        a4.setFill(Color.BEIGE);
        a4.setStroke(Color.BLACK);
        num4=rarityCalc();
        Rectangle b4=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/2.333);
        b4.setX(bodyDimensions[0]/1.5);
        b4.setY(bodyDimensions[1]/1.75);
        b4.setStroke(Color.BLACK);
        if(num4==1){
            b4.setFill(Color.GREY);
        }else if(num4==2){
            b4.setFill(Color.BLUE);
        }else if(num4==3){
            b4.setFill(Color.PURPLE);
        }else{
            b4.setFill(Color.YELLOW);
        }
        StackPane gold4=new StackPane();
        gold4.setLayoutX(bodyDimensions[0]/1.5+bodyDimensions[0]/8);
        gold4.setLayoutY(bodyDimensions[1]/1.6);
        Circle c4=new Circle(bodyDimensions[0]/175);
        c4.setFill(Color.GOLD);
        Text g4=new Text("G");
        g4.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/175));
        g4.setFill(Color.BLACK);
        gold4.getChildren().addAll(c4,g4);
        Text t4=new Text(bodyDimensions[0]/1.5+bodyDimensions[0]/7,bodyDimensions[1]/1.375,Integer.toString(num4));
        t4.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/100));
        t4.setFill(Color.BLACK);
        u4.getChildren().addAll(a4,b4,gold4,t4);
        class4=randomClass();
        Text n4=new Text(bodyDimensions[0]/1.5+bodyDimensions[0]/20,bodyDimensions[1]/1.375,class4);
        n4.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/90));
        n4.setFill(Color.BLACK);
        u4.getChildren().addAll(n4);
        counter++;
        //unit 5
        u5=new Pane();
        a5=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/1.75);
        a5.setX(bodyDimensions[0]/1.2);
        a5.setFill(Color.BEIGE);
        a5.setStroke(Color.BLACK);
        num5=rarityCalc();
        Rectangle b5=new Rectangle(bodyDimensions[0]/6,bodyDimensions[1]/2.333);
        b5.setX(bodyDimensions[0]/1.2);
        b5.setY(bodyDimensions[1]/1.75);
        b5.setStroke(Color.BLACK);
        if(num5==1){
            b5.setFill(Color.GREY);
        }else if(num5==2){
            b5.setFill(Color.BLUE);
        }else if(num5==3){
            b5.setFill(Color.PURPLE);
        }else{
            b5.setFill(Color.YELLOW);
        }
        StackPane gold5=new StackPane();
        gold5.setLayoutX(bodyDimensions[0]/1.2+bodyDimensions[0]/8);
        gold5.setLayoutY(bodyDimensions[1]/1.6);
        Circle c5=new Circle(bodyDimensions[0]/175);
        c5.setFill(Color.GOLD);
        Text g5=new Text("G");
        g5.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/175));
        g5.setFill(Color.BLACK);
        gold5.getChildren().addAll(c5,g5);
        Text t5=new Text(bodyDimensions[0]/1.2+bodyDimensions[0]/7,bodyDimensions[1]/1.375,Integer.toString(num5));
        t5.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/100));
        t5.setFill(Color.BLACK);
        u5.getChildren().addAll(a5,b5,gold5,t5);
        class5=randomClass();
        Text n5=new Text(bodyDimensions[0]/1.2+bodyDimensions[0]/20,bodyDimensions[1]/1.375,class5);
        n5.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/90));
        n5.setFill(Color.BLACK);
        u5.getChildren().addAll(n5);
        body.getChildren().addAll(u5,u4,u3,u2,u1);
        check=true;
        counter=1;
    }
    public int rarityCalc(){
        int num=(int)(Math.random()*100);
        if(num<50){
            return 1;
        }else if(num>50&&num<85){
            return 2;
        }else if(num>85&&num<95){
            return 3;
        }else{
            return 4;
        }
    }
    public String randomClass(){
        int num =(int)(Math.random()*5);
        //0=wizard
        //1=archer
        //2=Paladin
        //3=Priest
        //4=berserker
        createClass(num);
        if(num==0){
            return "Wizard";
        }else if(num==1){
            return "Archer";
        }else if(num==2){
            return "Paladin";
        }else if(num==3){
            return "Priest";
        }else {
            return "Berserker";
        }
    }
    public void createClass(int num){
        if(counter==1){
            if(num==0){
                Circle c1=new Circle(bodyDimensions[0]/6+bodyDimensions[0]/12,bodyDimensions[1]/4,bodyDimensions[0]/55);
                if(turn%3==0) {
                    c1.setFill(Color.VIOLET);
                }else{
                    c1.setFill(Color.BROWN);
                }
                u1.getChildren().add(c1);
            }else if(num==1){
                Polygon t1=new Polygon(bodyDimensions[0]/6+bodyDimensions[0]/17.5,bodyDimensions[1]/2.5,
                        bodyDimensions[0]/6+bodyDimensions[0]/9,bodyDimensions[1]/2.5,
                        (bodyDimensions[0]/6+bodyDimensions[0]/9+bodyDimensions[0]/6+bodyDimensions[0]/17.5)/2,bodyDimensions[1]/70);
                if(turn%3==0) {
                    t1.setFill(Color.VIOLET);
                }else{
                    t1.setFill(Color.BROWN);
                }
                u1.getChildren().add(t1);
            }else if(num==2) {
                Rectangle e1 = new Rectangle(bodyDimensions[0] / 25, bodyDimensions[1] / 2.25);
                e1.setX(bodyDimensions[0] / 6 + bodyDimensions[0] / 15.75);
                e1.setY(bodyDimensions[1] / 20);
                if(turn%3==0) {
                    e1.setFill(Color.VIOLET);
                }else{
                    e1.setFill(Color.BROWN);
                }
                u1.getChildren().add(e1);
            }else if(num==3) {
                int s = 45;
                Polygon p1 = new Polygon(0.0, 0.0,
                        s, 0.0,
                        s + (0.309 * s), s * 0.951,
                        s / 2, s * 0.951 + s * 0.588,
                        0 - (0.309 * s), s * 0.951);
                p1.setLayoutX(bodyDimensions[0] / 6 + bodyDimensions[0] / 14);
                p1.setLayoutY(bodyDimensions[1] / 10);
                if(turn%3==0) {
                    p1.setFill(Color.VIOLET);
                }else{
                    p1.setFill(Color.BROWN);
                }
                u1.getChildren().add(p1);
            } else {
                int h = 43;
                Polygon h1 = new Polygon(0.0, 0.0,
                        h, 0.0,
                        h + (h / 2), h * 0.866,
                        h, h * 0.866 * 2,
                        0.0, h * 0.866 * 2,
                        0 - h / 2, h * 0.866);
                h1.setLayoutX(bodyDimensions[0] / 6 + bodyDimensions[0] / 14);
                h1.setLayoutY(bodyDimensions[1] / 15);
                if(turn%3==0) {
                    h1.setFill(Color.VIOLET);
                }else{
                    h1.setFill(Color.BROWN);
                }
                u1.getChildren().add(h1);
            }
        }else if(counter==2){
            if(num==0){
                Circle c1=new Circle(bodyDimensions[0]/3+bodyDimensions[0]/12,bodyDimensions[1]/4,bodyDimensions[0]/55);
                if(turn%3==0) {
                    c1.setFill(Color.VIOLET);
                }else{
                    c1.setFill(Color.BROWN);
                }
                u2.getChildren().add(c1);
            }else if(num==1){
                Polygon t1=new Polygon(bodyDimensions[0]/3+bodyDimensions[0]/17.5,bodyDimensions[1]/2.5,
                        bodyDimensions[0]/3+bodyDimensions[0]/9,bodyDimensions[1]/2.5,
                        (bodyDimensions[0]/3+bodyDimensions[0]/9+bodyDimensions[0]/3+bodyDimensions[0]/17.5)/2,bodyDimensions[1]/70);
                if(turn%3==0) {
                    t1.setFill(Color.VIOLET);
                }else{
                    t1.setFill(Color.BROWN);
                }
                u2.getChildren().add(t1);
            }else if(num==2) {
                Rectangle e1 = new Rectangle(bodyDimensions[0] / 25, bodyDimensions[1] / 2.25);
                e1.setX(bodyDimensions[0] / 3 + bodyDimensions[0] / 15.75);
                e1.setY(bodyDimensions[1] / 20);
                if(turn%3==0) {
                    e1.setFill(Color.VIOLET);
                }else{
                    e1.setFill(Color.BROWN);
                }
                u2.getChildren().add(e1);
            }else if(num==3) {
                int s = 45;
                Polygon p1 = new Polygon(0.0, 0.0,
                        s, 0.0,
                        s + (0.309 * s), s * 0.951,
                        s / 2, s * 0.951 + s * 0.588,
                        0 - (0.309 * s), s * 0.951);
                p1.setLayoutX(bodyDimensions[0] / 3 + bodyDimensions[0] / 14);
                p1.setLayoutY(bodyDimensions[1] / 10);
                if(turn%3==0) {
                    p1.setFill(Color.VIOLET);
                }else{
                    p1.setFill(Color.BROWN);
                }
                u2.getChildren().add(p1);
            } else {
                int h = 43;
                Polygon h1 = new Polygon(0.0, 0.0,
                        h, 0.0,
                        h + (h / 2), h * 0.866,
                        h, h * 0.866 * 2,
                        0.0, h * 0.866 * 2,
                        0 - h / 2, h * 0.866);
                h1.setLayoutX(bodyDimensions[0] / 3 + bodyDimensions[0] / 14);
                h1.setLayoutY(bodyDimensions[1] / 15);
                if(turn%3==0) {
                    h1.setFill(Color.VIOLET);
                }else{
                    h1.setFill(Color.BROWN);
                }
                u2.getChildren().add(h1);
            }
        }else if(counter==3) {
            if (num == 0) {
                Circle c1 = new Circle(bodyDimensions[0] / 2 + bodyDimensions[0] / 12, bodyDimensions[1] / 4, bodyDimensions[0] / 55);
                if(turn%3==0) {
                    c1.setFill(Color.VIOLET);
                }else{
                    c1.setFill(Color.BROWN);
                }
                u3.getChildren().add(c1);
            } else if (num == 1) {
                Polygon t1 = new Polygon(bodyDimensions[0] / 2 + bodyDimensions[0] / 17.5, bodyDimensions[1] / 2.5,
                        bodyDimensions[0] / 2 + bodyDimensions[0] / 9, bodyDimensions[1] / 2.5,
                        (bodyDimensions[0] / 2 + bodyDimensions[0] / 9 + bodyDimensions[0] / 2 + bodyDimensions[0] / 17.5) / 2, bodyDimensions[1] / 70);
                if(turn%3==0) {
                    t1.setFill(Color.VIOLET);
                }else{
                    t1.setFill(Color.BROWN);
                }
                u3.getChildren().add(t1);
            } else if (num == 2) {
                Rectangle e1 = new Rectangle(bodyDimensions[0] / 25, bodyDimensions[1] / 2.25);
                e1.setX(bodyDimensions[0] / 2 + bodyDimensions[0] / 15.75);
                e1.setY(bodyDimensions[1] / 20);
                if(turn%3==0) {
                    e1.setFill(Color.VIOLET);
                }else{
                    e1.setFill(Color.BROWN);
                }
                u3.getChildren().add(e1);
            } else if (num == 3) {
                int s = 45;
                Polygon p1 = new Polygon(0.0, 0.0,
                        s, 0.0,
                        s + (0.309 * s), s * 0.951,
                        s / 2, s * 0.951 + s * 0.588,
                        0 - (0.309 * s), s * 0.951);
                p1.setLayoutX(bodyDimensions[0] / 2 + bodyDimensions[0] / 14);
                p1.setLayoutY(bodyDimensions[1] / 10);
                if(turn%3==0) {
                    p1.setFill(Color.VIOLET);
                }else{
                    p1.setFill(Color.BROWN);
                }
                u3.getChildren().add(p1);
            } else {
                int h = 43;
                Polygon h1 = new Polygon(0.0, 0.0,
                        h, 0.0,
                        h + (h / 2), h * 0.866,
                        h, h * 0.866 * 2,
                        0.0, h * 0.866 * 2,
                        0 - h / 2, h * 0.866);
                h1.setLayoutX(bodyDimensions[0] / 2 + bodyDimensions[0] / 14);
                h1.setLayoutY(bodyDimensions[1] / 15);
                if(turn%3==0) {
                    h1.setFill(Color.VIOLET);
                }else{
                    h1.setFill(Color.BROWN);
                }
                u3.getChildren().add(h1);
            }
        }else if(counter==4){
            if(num==0){
                Circle c1=new Circle(bodyDimensions[0]/1.5+bodyDimensions[0]/12,bodyDimensions[1]/4,bodyDimensions[0]/55);
                if(turn%3==0) {
                    c1.setFill(Color.VIOLET);
                }else{
                    c1.setFill(Color.BROWN);
                }
                u4.getChildren().add(c1);
            }else if(num==1){
                Polygon t1=new Polygon(bodyDimensions[0]/1.5+bodyDimensions[0]/17.5,bodyDimensions[1]/2.5,
                        bodyDimensions[0]/1.5+bodyDimensions[0]/9,bodyDimensions[1]/2.5,
                        (bodyDimensions[0]/1.5+bodyDimensions[0]/9+bodyDimensions[0]/1.5+bodyDimensions[0]/17.5)/2,bodyDimensions[1]/70);
                if(turn%3==0) {
                    t1.setFill(Color.VIOLET);
                }else{
                    t1.setFill(Color.BROWN);
                }
                u4.getChildren().add(t1);
            }else if(num==2) {
                Rectangle e1 = new Rectangle(bodyDimensions[0] / 25, bodyDimensions[1] / 2.25);
                e1.setX(bodyDimensions[0] / 1.5 + bodyDimensions[0] / 15.75);
                e1.setY(bodyDimensions[1] / 20);
                if(turn%3==0) {
                    e1.setFill(Color.VIOLET);
                }else{
                    e1.setFill(Color.BROWN);
                }
                u4.getChildren().add(e1);
            }else if(num==3) {
                int s = 45;
                Polygon p1 = new Polygon(0.0, 0.0,
                        s, 0.0,
                        s + (0.309 * s), s * 0.951,
                        s / 2, s * 0.951 + s * 0.588,
                        0 - (0.309 * s), s * 0.951);
                p1.setLayoutX(bodyDimensions[0] / 1.5 + bodyDimensions[0] / 14);
                p1.setLayoutY(bodyDimensions[1] / 10);
                if(turn%3==0) {
                    p1.setFill(Color.VIOLET);
                }else{
                    p1.setFill(Color.BROWN);
                }
                u4.getChildren().add(p1);
            } else {
                int h = 43;
                Polygon h1 = new Polygon(0.0, 0.0,
                        h, 0.0,
                        h + (h / 2), h * 0.866,
                        h, h * 0.866 * 2,
                        0.0, h * 0.866 * 2,
                        0 - h / 2, h * 0.866);
                h1.setLayoutX(bodyDimensions[0] / 1.5 + bodyDimensions[0] / 14);
                h1.setLayoutY(bodyDimensions[1] / 15);
                if(turn%3==0) {
                    h1.setFill(Color.VIOLET);
                }else{
                    h1.setFill(Color.BROWN);
                }
                u4.getChildren().add(h1);
            }
        }else{
            if(num==0){
                Circle c1=new Circle(bodyDimensions[0]/1.2+bodyDimensions[0]/12,bodyDimensions[1]/4,bodyDimensions[0]/55);
                if(turn%3==0) {
                    c1.setFill(Color.VIOLET);
                }else{
                    c1.setFill(Color.BROWN);
                }
                u5.getChildren().add(c1);
            }else if(num==1){
                Polygon t1=new Polygon(bodyDimensions[0]/1.2+bodyDimensions[0]/17.5,bodyDimensions[1]/2.5,
                        bodyDimensions[0]/1.2+bodyDimensions[0]/9,bodyDimensions[1]/2.5,
                        (bodyDimensions[0]/1.2+bodyDimensions[0]/9+bodyDimensions[0]/1.2+bodyDimensions[0]/17.5)/2,bodyDimensions[1]/70);
                if(turn%3==0) {
                    t1.setFill(Color.VIOLET);
                }else{
                    t1.setFill(Color.BROWN);
                }
                u5.getChildren().add(t1);
            }else if(num==2) {
                Rectangle e1 = new Rectangle(bodyDimensions[0] / 25, bodyDimensions[1] / 2.25);
                e1.setX(bodyDimensions[0] / 1.2 + bodyDimensions[0] / 15.75);
                e1.setY(bodyDimensions[1] / 20);
                if(turn%3==0) {
                    e1.setFill(Color.VIOLET);
                }else{
                    e1.setFill(Color.BROWN);
                }
                u5.getChildren().add(e1);
            }else if(num==3) {
                int s = 45;
                Polygon p1 = new Polygon(0.0, 0.0,
                        s, 0.0,
                        s + (0.309 * s), s * 0.951,
                        s / 2, s * 0.951 + s * 0.588,
                        0 - (0.309 * s), s * 0.951);
                p1.setLayoutX(bodyDimensions[0] / 1.2 + bodyDimensions[0] / 14);
                p1.setLayoutY(bodyDimensions[1] / 10);
                if(turn%3==0) {
                    p1.setFill(Color.VIOLET);
                }else{
                    p1.setFill(Color.BROWN);
                }
                u5.getChildren().add(p1);
            } else {
                int h = 43;
                Polygon h1 = new Polygon(0.0, 0.0,
                        h, 0.0,
                        h + (h / 2), h * 0.866,
                        h, h * 0.866 * 2,
                        0.0, h * 0.866 * 2,
                        0 - h / 2, h * 0.866);
                h1.setLayoutX(bodyDimensions[0] / 1.2 + bodyDimensions[0] / 14);
                h1.setLayoutY(bodyDimensions[1] / 15);
                if(turn%3==0) {
                    h1.setFill(Color.VIOLET);
                }else{
                    h1.setFill(Color.BROWN);
                }
                u5.getChildren().add(h1);
            }
        }
    }
    public void nextPhase(){
        turn++;
        body.getChildren().removeAll(btn,btn1,u1,u2,u3,u4,u5);
        refreshShop();
        body.getChildren().addAll(btn,btn1);
        actions();
    }
    public void sellGlowOn(){
        r1.setFill(Color.SKYBLUE);
    }
    public void sellGlowOff(){
        r1.setFill(Color.STEELBLUE);
    }
}
