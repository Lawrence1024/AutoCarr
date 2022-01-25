package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Box {
    //fields
    private double size;
    private int ID;
    private Pane body=new Pane();
    private Rectangle base;
    private Text IDDisplay;
    //constructor
    Box(double s,int coord,int id,double[] boxesCount){
        size=s;
        ID=id;
        body.setLayoutX(coord%(boxesCount[0])*s);
        body.setLayoutY((int)(coord/(boxesCount[0]))*s);
        base=new Rectangle(0,0,s,s);
        base.setFill(Color.BISQUE);
        base.setStroke(Color.BLACK);
        base.setStrokeWidth(size*0.0186);
        body.getChildren().add(base);
        if(id!=0) {
            IDDisplay = new Text(0, 35, id + "");
            IDDisplay.setFill(Color.BLUE);
            IDDisplay.setFont(new Font(40));
            body.getChildren().add(IDDisplay);
            IDDisplay.setVisible(false);
        }
    }
    //setter/getter

    public Pane getBody() {
        return body;
    }

    public int getID() {
        return ID;
    }

    public double getSize() {
        return size;
    }

    public Rectangle getBase(){return base;}

    //public methods
    public void glow(){
        base.setFill(Color.GREEN);
    }
    public void altGlow(){
        base.setFill(Color.RED);
    }
    public void notGlow(){
        base.setFill(Color.BISQUE);
    }
    //private methods
}
