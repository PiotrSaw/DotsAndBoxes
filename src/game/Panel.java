package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Panel extends JPanel implements ActionListener, FocusListener {


    public Square[][] squares;

    // ustawienia gry
    public String name1;
    public String name2;
    public Color color1;
    public Color color2;
    public int points1;
    public int points2;
    public int size;  // rozmiar kwadratowej planszy (ilosc kwadratow)
    public int width;  //rozmiary przyciskow
    public int length; //rozmiary przyciskow
    public int gap; // przerwa miedzy przyciskami
    public int startX;
    public int startY;
    public int click;
    public Status s;
    public StartPlayer pl;
    // elementy gry
    public JButton[] buttons;
    public JLabel name1Label;
    public JLabel name2Label;
    public JLabel points1Label;
    public JLabel points2Label;
    public JLabel move;

    //elementy ustawien
    public JLabel title;
    public JLabel player1;
    public JLabel player2;
    public JTextField getName1;
    public JTextField getName2;
    public JButton play;
    public JButton[] colors1;
    public JButton[] colors2;
    public JLabel gameSize;
    public JButton[] sizes;

    //ekran koncowy
    public JLabel result;
    public JLabel points;
    public JButton restart;
    public JButton exit;



    public Panel()
    {
        pl = pl.PLAYER1;
        Game.settings(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();

        if(s == s.USTAWIENIA)
        {
            Game.settingsButtons(this, o);
        }
        else if(s == s.GRACZ1 || s == s.GRACZ2)
        {
            Game.gameButtons(this, o);
        }
        else
        {
            Game.endButtons(this, o);
        }




    }


    @Override
    public void focusGained(FocusEvent e) {
        Object o = e.getSource();
        if(o == getName1) getName1.selectAll();
        else if(o == getName2) getName2.selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}

