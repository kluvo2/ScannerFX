/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannerfx;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Roman
 */
public class ScannerFX extends Application {
    
    public GridPane resultGrid;
    public VBox root;
    public HBox pictures;
    public ImageView picture;
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Process a new picture");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Processing");
                TextInputDialog dialog = new TextInputDialog("Enter file");
                dialog.setTitle("Processing a file");
                dialog.setHeaderText("Processing a file");
                dialog.setContentText("Please enter file name:");

                // Traditional way to get the response value.
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    processImage(result.get());
                }

                
            }
        });
        
        root = new VBox();
        pictures = new HBox();
        resultGrid = new GridPane();
        picture = new ImageView(new Image("/images/1.jpg"));
        pictures.getChildren().addAll(resultGrid, picture);
        root.getChildren().addAll(btn,pictures);
        
        Scene scene = new Scene(root, 900, 550);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public Color partyColor(int i){
        switch(i){
            case 0: return Color.WHITE;
            case 1: return Color.RED;
            case 2: return Color.ORANGE;
            case 3: return Color.GREENYELLOW;
            case 4: return Color.CYAN;
            case 5: return Color.DEEPPINK;
            case 6: return Color.DARKBLUE;
            case 7: return Color.DARKOLIVEGREEN;
            case 8: return Color.YELLOW;
            default: return Color.BLACK;
        }
    }
    
    public void drawResults(int[][] results){
        resultGrid.getChildren().removeAll();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Rectangle square=new Rectangle();
                square.setHeight(100); square.setWidth(100);
                square.setFill(partyColor(results[i][j]));
                resultGrid.add(square, j, i);
            }
        }
        //root.getChildren().add(resultGrid);
    }
    
    public void processImage(String fname){
        String file = "/images/" + fname + ".jpg";
        Image grid = new Image(file, 400, 400, false, false);
        Color[][] result = new Color[4][4];
        int x = 20;
        int y = 20;
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                double red = 0;
                double green = 0;
                double blue = 0;
                for(int ix = 0; ix < 60; ix++){
                    for(int iy = 0; iy < 60; iy++){
                        Color c = grid.getPixelReader().getColor(ix + x, iy + y);
                        red += c.getRed();
                        green += c.getGreen();
                        blue += c.getBlue();
                    }
                }
                //System.out.println("Field " + Integer.toString(i) + Integer.toString(j) + " scanned.");
                red = red/3600;
                green = green/3600;
                blue = blue/3600;
                result[i][j] = new Color(red, green, blue, 0);
                x += 100;
            }
            x = 30;
            y += 100;
        }
        
        Judge judge = new Judge();
        int res[][] = judge.processGrid(result);
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(Integer.toString(res[i][j]) + " ");
            }  
            System.out.println("");
        }   
        picture.setImage(grid);
        drawResults(res);
        
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);    

    }
    
}
