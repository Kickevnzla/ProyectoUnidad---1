package logic;

import presentation.WinMinesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kickevnzla
 */
public class Game {
    public static void main(String ...args) {
        // Al inicializar el programa, este crea y muestra la ventana de juego
        WinMinesweeper w = new WinMinesweeper();
        w.setVisible(true);
    }
}
