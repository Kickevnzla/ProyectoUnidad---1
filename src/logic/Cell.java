/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import presentation.WinMinesweeper;

/**
 *
 * @author kickevnzla
 */

public class Cell extends JButton {
    private boolean hidden;
    private boolean mine;
    private boolean flag;

    private int coordX, coordY; // Coordenadas de la celda
    
    // Constructor parametrizado de la celda
    public Cell(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.hidden = true;
        this.flag = false;
        this.mine = false;
        this.setMinimumSize(new Dimension(20,20));
        this.setBackground(new Color(191,191,191));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                cellActionPerformed(e);
                }});
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    
    public void setMine() {
        assert hidden;
        mine = true;
    }
    
    public boolean hasMine() {
        return mine;
    }
    
    public void Reveal() {
        hidden = false;
        setIcon(new ImageIcon(getClass().getResource("/presentation_images/mine.png")));
    }
    
    public boolean isFlagged() {
        return flag;
    }
    
    public boolean isEmpty() {
        if (AdjacentMines() == 0 && !hasMine()) return true;
        return false;
    }
    
    public boolean isHidden() {
        return hidden;
    }
    
    public int AdjacentMines() {
        int count = 0;
        
        for (int i = -1; i <= 1; i++) {
            if (coordX + i >= 0 && coordX + i < WinMinesweeper.rows) {
                for (int j = -1; j <= 1; j++) {
                    if (coordY + j >= 0 && coordY + j < WinMinesweeper.columns && WinMinesweeper.cells[coordX + i][coordY + j].hasMine()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    public void FindEmptyCells() {
        for (int i = -1; i <= 1; i++) {
            if (coordX + i >= 0 && coordX + i < WinMinesweeper.rows) {
                for (int j = -1; j <= 1; j++) {
                    if (coordY + j >= 0 && coordY + j < WinMinesweeper.columns) {
                        if (!WinMinesweeper.cells[coordX + i][coordY + j].hasMine() && WinMinesweeper.cells[coordX + i][coordY + j].isHidden()) {
                            WinMinesweeper.cells[coordX + i][coordY + j].Click();
                        }
                    }
                }
            }
        }
    }
    
    private void OpenAllMines() {
        for (int i = 0; i < WinMinesweeper.rows; i++) {
            for (int j = 0; j < WinMinesweeper.columns; j++) {
                if (WinMinesweeper.cells[i][j].hasMine()) {
                    WinMinesweeper.cells[i][j].Reveal();
                    WinMinesweeper.cells[i][j].setBackground(new Color(191,191,191));
                    WinMinesweeper.cells[i][j].setBorder(BorderFactory.createLineBorder(new Color(140,140,140)));
                }
            }
        }
        WinMinesweeper.gameEnded = true;
    }
    
    public void Click() {
        if (hidden && !WinMinesweeper.gameEnded) {
            this.setBackground(new Color(191,191,191));
            this.setBorder(BorderFactory.createLineBorder(new Color(140,140,140)));
            
            if (hasMine()) {
                
                OpenAllMines();
                this.setBackground(new Color(231,13,0));
                WinMinesweeper.btnFace.setIcon(new ImageIcon(getClass().getResource("/presentation_images/sad-face-icon.png")));
                
            } else if (isFlagged()) {
                
                this.setIcon(new ImageIcon(getClass().getResource("/presentation_images/flag-icon.png")));
                
            } else if (AdjacentMines() == 0){
                
                //FindEmptyCells();
                
            } else if (AdjacentMines() != 0){
                if (AdjacentMines() == 1) this.setIcon(new ImageIcon(getClass().getResource("/presentation_images/number-one.png")));
                if (AdjacentMines() == 2) this.setIcon(new ImageIcon(getClass().getResource("/presentation_images/number-two.png")));
                if (AdjacentMines() == 3) this.setIcon(new ImageIcon(getClass().getResource("/presentation_images/number-three.png")));
                if (AdjacentMines() == 4) this.setIcon(new ImageIcon(getClass().getResource("/presentation_images/number-four.png")));
                if (AdjacentMines() == 5) this.setIcon(new ImageIcon(getClass().getResource("/presentation_images/number-five.png")));
                if (AdjacentMines() == 6) this.setIcon(new ImageIcon(getClass().getResource("/presentation_images/number-six.png")));
                if (AdjacentMines() == 7) this.setIcon(new ImageIcon(getClass().getResource("/presentation_images/number-seven.png")));
                if (AdjacentMines() == 8) this.setIcon(new ImageIcon(getClass().getResource("/presentation_images/number-eight.png")));
            }
            hidden = false;
        }
    }

    private void cellActionPerformed(ActionEvent e) {
        Click();
        int count = 0;
        int withoutMines = WinMinesweeper.rows * WinMinesweeper.columns - WinMinesweeper.mines;
        
        for (int i = 0; i < WinMinesweeper.rows; i++) {
            for (int j = 0; j < WinMinesweeper.columns; j++) {
                if (!WinMinesweeper.cells[i][j].isHidden()) {
                    count++;
                }
            }
        }
        if (count == withoutMines) {
            WinMinesweeper.btnFace.setIcon(new ImageIcon(getClass().getResource("/presentation_images/swag-face-icon.png")));
        }
    }
}
