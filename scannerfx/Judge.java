/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannerfx;

import javafx.scene.paint.Color;

/**
 *
 * @author Roman
 */
public class Judge {

    public int[][][] colorGrid;
    
    public Judge(){
        colorGrid = new int[3][3][3];
        
        colorGrid[0][0][0] = -1;
        colorGrid[1][0][0] = 1;
        colorGrid[2][0][0] = 1;
        
        colorGrid[0][0][1] = 6;
        colorGrid[1][0][1] = 5;
        colorGrid[2][0][1] = 5;
        
        colorGrid[0][0][2] = 6;
        colorGrid[1][0][2] = 5;
        colorGrid[2][0][2] = 5;
        
        colorGrid[0][1][0] = 7;
        colorGrid[1][1][0] = 7;
        colorGrid[2][1][0] = 2;
        
        colorGrid[0][1][1] = 7;
        colorGrid[1][1][1] = 7;
        colorGrid[2][1][1] = 2;
        
        colorGrid[0][1][2] = 6;
        colorGrid[1][1][2] = 6;
        colorGrid[2][1][2] = 5;
        
        colorGrid[0][2][0] = 3;
        colorGrid[1][2][0] = 3;
        colorGrid[2][2][0] = 8;
        
        colorGrid[0][2][1] = 3;
        colorGrid[1][2][1] = 3;
        colorGrid[2][2][1] = 8;
        
        colorGrid[0][2][2] = 4;
        colorGrid[1][2][2] = 4;
        colorGrid[2][2][2] = 0;
    }
    
    private int processField(Color c){
        double red = c.getRed();
        double green = c.getGreen();
        double blue = c.getBlue();
        //System.out.print(red + " " + green + " " + blue + " ");
        int r;
        int g;
        int b;
        
        if(red < 0.2){
            r = 0;
        }else{
            if(red < 0.5){
                r = 1;
            }else{
                r = 2;
            }
        }
        if(green < 0.2){
            g = 0;
        }else{
            if(green < 0.5){
                g = 1;
            }else{
                g = 2;
            }
        }
        if(blue < 0.2){
            b = 0;
        }else{
            if(blue < 0.5){
                b = 1;
            }else{
                b = 2;
            }
        }
        
        if(r == 2 && b == 0){
            if(green > 0.3 && green < 0.7){
                g = 1;
            }
        }
        
        //System.out.println(r + " " + g + " " + b);
        int x = colorGrid[r][g][b];
        //System.out.println("Color " + c.toString() + " is " + Integer.toString(x));
        return x;
    }
    
    public int[][] processGrid(Color[][] grid){
        int[][] res = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                res[i][j] = processField(grid[i][j]);
            }
        }
        
        
        
        
        
        return res;
    }
}
