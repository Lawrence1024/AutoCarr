package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {

    Scene scene1;
    Stage pStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        pStage=primaryStage;
        launchMenuAndGame(primaryStage);
    }

    public void showRules(){

    }

    public void launchMenuAndGame(Stage primaryStage){
        VBox buttons = new VBox();

        Button startGame = new Button();
        startGame.setText("Play!");
        startGame.setOnAction(e -> {
            primaryStage.setTitle("Auto Chess");
            Screen myScreen=Screen.getPrimary();
            Rectangle2D bounds= myScreen.getVisualBounds();
            GlobalVariable.screenDimensions=new double[]{bounds.getWidth(),bounds.getHeight()};
            MainGame root2 = new MainGame(new double[]{bounds.getWidth(),bounds.getHeight()},this);
            primaryStage.setScene(new Scene(root2.getWrapper(), 1000, 1000));
            primaryStage.setMaximized(true);
            primaryStage.show();
        });

        Button help = new Button();
        help.setText("Rules\n");
        help.setOnAction(e -> {
            primaryStage.setTitle("Rules");
            VBox root = new VBox();
            root.setStyle("-fx-background-color: #67bffe");

            Text rulesText1 = new Text();
            rulesText1.setFont(Font.font("Impact", FontWeight.BOLD, 30));
            rulesText1.setText("Pre-round basics:\n");
            rulesText1.setFill(Color.RED);
            rulesText1.setUnderline(true);

            Text rulesText2 = new Text();
            rulesText2.setFont(Font.font("Impact", 25));
            rulesText2.setFill(Color.RED);
            rulesText2.setText("" +
                    "At the beginning of the round, you get a base amount of 3 gold plus 1 for every 5 gold you have saved, capped at 5 extra gold.\n" +
                    "Uses for gold?: \n" +
                    "       Buy units to fight on the battlefield, clicking a unit in the shop will buy the unit, using you gold and putting that unit onto your deck\n" +
                    "       Reroll your shop to get new units available for purchase\n" +
                    "       Saving gold so you can make more the following round\n" +
                    "You can only place your units on your side of the board, Player 1 goes on the left side, while Player 2 goes on the right side. End your turn when you are finished, \nwhich will either hand your turn to the other player so they may do pre-round prep, or begin the battle phase.\n");

            Text rulesText3 = new Text();
            rulesText3.setFont(Font.font("Impact", FontWeight.BOLD, 30));
            rulesText3.setText("Battle Phase:\n");
            rulesText3.setFill(Color.BLUE);
            rulesText3.setUnderline(true);

            Text rulesText4 = new Text();
            rulesText4.setFont(Font.font("Impact",25));
            rulesText4.setFill(Color.BLUE);
            rulesText4.setText("Units will path find towards the closest enemy to them and begin to attack them. Once a unit begins their attacks on a unit, they do not switch targets until that unit is dead. \nThe battle phase ends after all units of one player dies. The player with fewer units left will take damage scaling with turn number, \namount of units, and tier of the units. If the losing player takes damage bringing them to 0 hp, they are out of the game. \n");

            Text rulesText5 = new Text();
            rulesText5.setFont(Font.font("Impact",FontWeight.BOLD,30));
            rulesText5.setFill(Color.GREEN);
            rulesText5.setText("Units:\n");
            rulesText5.setUnderline(true);

            Text rulesText6 = new Text();
            rulesText6.setFont(Font.font("Impact",25));
            rulesText6.setFill(Color.GREEN);
            rulesText6.setText("Units in the shop will have different costs, ranging from 1G - 4G, based on their rarity and class. Based on their class, units will have specialized areas in stats, including attack speed, \nattack damage, health, and range."+
                               "\nThe different units are:\n" +
                               "Paladin: High Health, Slow Attack Speed, High Damage, Melee Range - Note: You always start the game with one.\n"+
                               "Priest:  High Health, High Attack Speed, Medium HEALING, Medium Range - Note: Does not do damage, heals lowest health ally.\n"+
                               "Beserker: Low Health, High Attack Speed, Medium Damage, Melee Range - Note: Scales insanely well with rarity and level due to high attack speed.\n"+
                               "Archer: Low Health, Medium Attack Speed, Medium Damage, High Range - Note: Highest range, place near the back to abuse.\n"+
                               "Wizard: Low Health, Slow Attack Speed, High Damage, Medium Range - Note: Glass cannon type.\n"+
                               "\n"+
                               "A units rarity will increase its damage as well as health\n"+
                               "You can combine three units of the same unit of same rarity and level to level it up, giving it increased health, attack speed, and damage! The higher the level, the higher the stat boost.\n"+
                               "In order to do so, place the three duplicates onto the deck and it will automatically combine them!"

            );


            root.getChildren().addAll(rulesText1,rulesText2,rulesText3,rulesText4,rulesText5,rulesText6);
            Button menu = new Button();
            menu.setText("Back");
            menu.setOnAction(f -> {
                primaryStage.setTitle("Menu");
                primaryStage.setScene(scene1);
                primaryStage.setHeight(650);
                primaryStage.setWidth(650);
                primaryStage.setMaximized(false);
                primaryStage.show();
            });

            root.getChildren().add(menu);
            Scene scene = new Scene(root, 1000,1000);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();

        });


        buttons.setMargin(startGame, new Insets(250,275,10,275));
        buttons.setMargin(help, new Insets(0,273,10,273));
        buttons.getChildren().addAll(startGame, help);


        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #67bffe");
        root.setCenter(buttons);
        scene1 = new Scene(root, 600,600);


        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

//This is the edited Main file

    public static void main(String[] args) { launch(args); }
}
