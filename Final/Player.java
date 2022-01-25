package sample;


import java.util.ArrayList;

public class Player {
    //fields
    PlayField playField;
    MainGame mainGame;
    Deck deck;
    String name;
    int teamNum;
    int money;
    int HP;

    //constructor
    Player(String n, int tN, int m,int hp, PlayField pF, MainGame mG){
        playField=pF;
        mainGame=mG;
        name=n;
        teamNum=tN;
        money=m;
        HP=hp;
        createDeck();
    }
    //setter/getter

    public Deck getDeck() {
        return deck;
    }

    public PlayField getPlayField() {
        return playField;
    }

    public int getMoney() { return money; }

    public void setMoney(int money) { this.money = money; }

    public MainGame getMainGame() {
        return mainGame;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public String getName(){return name;}

    public int getHP() { return HP; }

    public void setHP(int HP) { this.HP = HP; }

    //public methods
    public void appendDeck(){
        mainGame.getWrapper().getChildren().add(deck.getBody());
    }
    public void generateInterest(){
        if(money>5){
            money++;
        }
        if(money>10){
            money++;
        }
        if(money>15){
            money++;
        }
        if(money>20){
            money++;
        }
        if(money>25){
            money++;
        }
    }
    //private methods
    private void createDeck(){
        deck=new Deck(this);
        deck.getBody().relocate(0,playField.getBodyDimensions()[1]);
        if(deck.getPieces().size()>0)deck.movePieces();
    }
}
