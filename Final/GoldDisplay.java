package sample;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class GoldDisplay {
    //fields
    private Pane body = new Pane();
    private double [] screenDimensions;
    private double [] bodyDimensions;
    private Rectangle base;
    private Player player1,player2;
    private Text num,num1,hp,hp1;
    //constructor
    public GoldDisplay(){
        screenDimensions=GlobalVariable.screenDimensions;
        bodyDimensions=new double[]{screenDimensions[0]*0.1,screenDimensions[1]*0.7};
        base = new Rectangle(bodyDimensions[0],bodyDimensions[1]);
        base.setFill(Color.LIGHTBLUE);
        body.getChildren().add(base);
        Text p1=new Text(bodyDimensions[0]/12,bodyDimensions[1]/25,"Player 1");
        p1.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/5.5));
        p1.setFill(Color.MEDIUMSLATEBLUE);
        p1.setStroke(Color.SEAGREEN);
        p1.setStrokeWidth(2);
        hp=new Text(bodyDimensions[0]/12,bodyDimensions[1]/10,"HP: 100");
        hp.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/6.5));
        hp.setFill(Color.RED);
        StackPane gold=new StackPane();
        gold.setLayoutX(bodyDimensions[0]/12);
        gold.setLayoutY(bodyDimensions[1]/8);
        Circle c=new Circle(bodyDimensions[0]/13);
        c.setFill(Color.GOLD);
        Text g=new Text("G");
        g.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/13));
        g.setFill(Color.BLACK);
        gold.getChildren().addAll(c,g);
        num=new Text(bodyDimensions[0]/12+bodyDimensions[0]/4,bodyDimensions[1]/6.1,"100");
        num.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/6.5));
        num.setFill(Color.BLACK);
        //p2
        Text p2=new Text(bodyDimensions[0]/12,bodyDimensions[1]/4,"Player 2");
        p2.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/5.5));
        p2.setFill(Color.MEDIUMSLATEBLUE);
        p2.setStroke(Color.SEAGREEN);
        p2.setStrokeWidth(2);
        hp1=new Text(bodyDimensions[0]/12,bodyDimensions[1]/3.2,"HP: 100");
        hp1.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/6.5));
        hp1.setFill(Color.RED);
        StackPane gold1=new StackPane();
        gold1.setLayoutX(bodyDimensions[0]/12);
        gold1.setLayoutY(bodyDimensions[1]/3);
        Circle c1=new Circle(bodyDimensions[0]/13);
        c1.setFill(Color.GOLD);
        Text g1=new Text("G");
        g1.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/13));
        g1.setFill(Color.BLACK);
        gold1.getChildren().addAll(c1,g1);
        num1=new Text(bodyDimensions[0]/12+bodyDimensions[0]/4,bodyDimensions[1]/2.7,"100");
        num1.setFont(Font.font("Verdana", FontWeight.BOLD, bodyDimensions[0]/6.5));
        num1.setFill(Color.BLACK);
        body.getChildren().addAll(p1,hp,gold,num,p2,hp1,gold1,num1);
    }
    //setter/getter
    public Pane getBody(){return body;}
    public double[] getBodyDimensions(){return bodyDimensions;}
    public void setBodyDimensions(double[] bD) {
        bodyDimensions = bD;
        base.setWidth(bD[0]);
        base.setHeight(bD[1]);
    }
    public void setPlayers(Player pl1,Player pl2){
        player1=pl1;
        player2=pl2;
        createPlayerInfo();
    }
    //methods

    public void createPlayerInfo(){
        num.setText(Integer.toString(player1.getMoney()));
        num1.setText(Integer.toString(player2.getMoney()));
        hp.setText("HP: "+Integer.toString(player1.getHP()));
        hp1.setText("HP: "+Integer.toString(player1.getHP()));
    }

    public void updateMoney(int n){
        if(n==1){
            num.setText(Integer.toString(player1.getMoney()));
        }else{
            num1.setText(Integer.toString(player2.getMoney()));
        }
    }
    public void updateHP(int n){
        if(n==1){
            hp.setText("HP: "+Integer.toString(player1.getHP()));
        }else{
            hp1.setText("HP: "+Integer.toString(player2.getHP()));
        }
    }
}
