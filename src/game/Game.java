package game;

import javax.swing.*;
import java.awt.*;

public class Game{


    public static void start(Panel p)  // ustawienie komponentow okna gry
    {
        if(p.s == p.s.KONIEC)
        {
            p.remove(p.points);
            p.remove(p.result);
            p.remove(p.restart);
            p.remove(p.exit);
            p.revalidate();
            p.repaint();
            if(p.pl == p.pl.PLAYER1)
            {
                p.pl = p.pl.PLAYER2;
                p.s = p.s.GRACZ2;
            }
            else
            {
                p.pl = p.pl.PLAYER1;
                p.s = p.s.GRACZ1;
            }
        }
        else
        {
            p.s = p.s.GRACZ1;
        }
        p.width = 7;
        p.gap = 7;
        p.length = 70;
        p.startY = (1080 - p.size * (p.length + p.gap))/2;
        p.startX = (1920 - p.size * (p.length + p.gap) + p.gap)/2;
        p.points1 = 0;
        p.points2 = 0;
        p.click = p.size*(p.size+1)*2;


        int iterator = 0;


        // alokacja pamieci dla tablicy dwuwymiarowej na obiekty Square i tablicy buttons
        p.buttons = new JButton[p.size*(p.size+1)*2];

        p.squares = new Square[p.size][p.size];
        for(int i = 0; i<p.size; i++)
            for(int j = 0; j<p.size; j++)
                p.squares[i][j] = new Square();



        int x = p.startX;
        int y = p.startY;
        x += p.gap;
        // stworzenie pierwszego wiersza przyciskow
        for(int i = 0; i<p.size; i++)
        {
            JButton b = new JButton();
            b.setSize(p.length, p.width);
            b.setLocation(x, y);
            b.addActionListener(p);
            x += (p.gap + p.length);
            p.add(b);
            p.squares[0][i].up = b;
            p.buttons[iterator] = b;
            iterator++;
        }

        // kolejne wiersze (bez ostatniego)
        y += p.gap + p.length;

        for(int i = 0; i<p.size-1; i++)
        {
            x = p.startX + p.gap;
            for(int j = 0; j<p.size; j++)
            {
                JButton b = new JButton();
                b.setSize(p.length, p.width);
                b.setLocation(x, y);
                b.addActionListener(p);
                x += (p.gap + p.length);
                p.add(b);
                p.squares[i][j].down = b;
                p.squares[i+1][j].up = b;
                p.buttons[iterator] = b;
                iterator++;
            }
            y += (p.length + p.gap);
        }

        //ostatni rzad poziomych
        x = p.startX + p.gap;
        for(int i = 0; i<p.size; i++)
        {
            JButton b = new JButton();
            b.setSize(p.length, p.width);
            b.setLocation(x, y);
            b.addActionListener(p);
            x += (p.gap + p.length);
            p.add(b);
            p.squares[p.size-1][i].down = b;
            p.buttons[iterator] = b;
            iterator++;
        }





        // poziome analogicznie
        x = p.startX;
        y = p.startY + p.gap;
        for(int i = 0; i<p.size; i++)
        {
            JButton b = new JButton();
            b.setSize(p.width, p.length);
            b.setLocation(x, y);
            b.addActionListener(p);
            y += (p.gap + p.length);
            p.add(b);
            p.squares[i][0].left = b;
            p.buttons[iterator] = b;
            iterator++;
        }

        x += p.gap + p.length;

        for(int i = 0; i<p.size-1; i++)
        {
            y = p.startY + p.gap;
            for(int j = 0; j<p.size; j++)
            {
                JButton b = new JButton();
                b.setSize(p.width, p.length);
                b.setLocation(x, y);
                b.addActionListener(p);
                y += (p.gap + p.length);
                p.add(b);
                p.squares[j][i].right = b;
                p.squares[j][i+1].left = b;
                p.buttons[iterator] = b;
                iterator++;
            }
            x += p.gap + p.length;
        }

        y = p.startY + p.gap;
        for(int i = 0; i<p.size; i++)
        {
            JButton b = new JButton();
            b.setSize(p.width, p.length);
            b.setLocation(x, y);
            b.addActionListener(p);
            y += (p.gap + p.length);
            p.add(b);
            p.squares[i][p.size-1].right = b;
            p.buttons[iterator] = b;
            iterator++;
        }


        // ustawienie etykiet wyswietlajacych nazwy graczy, punkty, oraz czyj ruch
        Font f = new Font("font", 1, 75);
        p.name1Label = new JLabel(p.name1);
        p.add(p.name1Label);
        p.name1Label.setSize(400, 100);
        p.name1Label.setForeground(p.color1);
        p.name1Label.setFont(f);
        p.name1Label.setLocation(50, 50);
        p.name1Label.setHorizontalAlignment(SwingConstants.CENTER);

        p.name2Label = new JLabel(p.name2);
        p.add(p.name2Label);
        p.name2Label.setSize(400, 100);
        p.name2Label.setForeground(p.color2);
        p.name2Label.setFont(f);
        p.name2Label.setLocation(1400, 50);
        p.name2Label.setHorizontalAlignment(SwingConstants.CENTER);

        p.points1Label = new JLabel("Punkty: 0");
        p.add(p.points1Label);
        p.points1Label.setSize(400, 100);
        p.points1Label.setForeground(p.color1);
        p.points1Label.setFont(f);
        p.points1Label.setLocation(50, 150);
        p.points1Label.setHorizontalAlignment(SwingConstants.CENTER);

        p.points2Label = new JLabel("Punkty: 0");
        p.add(p.points2Label);
        p.points2Label.setSize(400, 100);
        p.points2Label.setForeground(p.color2);
        p.points2Label.setFont(f);
        p.points2Label.setLocation(1400, 150);
        p.points2Label.setHorizontalAlignment(SwingConstants.CENTER);

        p.move = new JLabel();
        p.add(p.move);
        p.move.setSize(1920, 100);
        p.move.setFont(f);
        p.move.setLocation(0, p.startY + p.size*(p.length+p.gap) + 30);
        p.move.setHorizontalAlignment(SwingConstants.CENTER);
        if(p.s == p.s.GRACZ1) p.move.setText("Teraz: " + p.name1);
        else if(p.s == p.s.GRACZ2) p.move.setText("Teraz: " + p.name2);
    }

    public static void gameButtons(Panel p, Object o) // dzialanie przyciskow w oknie gry
    {
        for(int i = 0; i<p.buttons.length; i++)
        {
            if(o == p.buttons[i])
            {
                p.buttons[i].setEnabled(false);
                p.click --;

                for(int j = 0; j<p.size; j++)
                {
                    for(int k = 0; k<p.size; k++)
                    {
                        if(o == p.squares[j][k].up)
                        {
                            p.squares[j][k].u = true;
                            p.squares[j][k].status();
                            if(p.squares[j][k].closed)
                            {
                                if(p.s == p.s.GRACZ1) p.points1++;
                                else p.points2++;
                            }
                        }
                        else if(o == p.squares[j][k].down)
                        {
                            p.squares[j][k].d = true;
                            p.squares[j][k].status();
                            if(p.squares[j][k].closed)
                            {
                                if(p.s == p.s.GRACZ1) p.points1++;
                                else p.points2++;
                            }
                        }
                        else if(o == p.squares[j][k].left)
                        {
                            p.squares[j][k].l = true;
                            p.squares[j][k].status();
                            if(p.squares[j][k].closed)
                            {
                                if(p.s == p.s.GRACZ1) p.points1++;
                                else p.points2++;
                            }
                        }
                        else if(o == p.squares[j][k].right)
                        {
                            p.squares[j][k].r = true;
                            p.squares[j][k].status();
                            if(p.squares[j][k].closed)
                            {
                                if(p.s == p.s.GRACZ1) p.points1++;
                                else p.points2++;
                            }
                        }
                    }
                }

                if(p.s == p.s.GRACZ1)
                {
                    p.s = p.s.GRACZ2;
                    p.buttons[i].setBackground(p.color1);
                }
                else if(p.s == p.s.GRACZ2)
                {
                    p.s = p.s.GRACZ1;
                    p.buttons[i].setBackground(p.color2);
                }

                p.points1Label.setText("Punkty: " + p.points1);
                p.points2Label.setText("Punkty: " + p.points2);

                if(p.s == p.s.GRACZ1) p.move.setText("Teraz: " + p.name1);
                else if(p.s == p.s.GRACZ2) p.move.setText("Teraz: " + p.name2);

            }
        }

        // wszystkie przyciski wykorzystane czyli koniec gry
        if(p.click == 0) {
            p.s = p.s.KONIEC;
            end(p);
        }
    }

    public static void settings(Panel p) // ustaiwienie komponentow okna ustawien
    {
        Font f1 = new Font("Font", 1, 80);
        Font f2 = new Font("Font", 1, 50);
        Font f3 = new Font("Font", 1, 40);
        p.setLayout(null);
        p.s = p.s.USTAWIENIA;
        Color green = new Color(20, 200, 40);
        Color blue = Color.blue;
        Color purple = new Color(179,102,255);
        Color magenta = Color.magenta;
        Color red = Color.red;

        p.title = new JLabel("Dots and boxes");
        p.add(p.title);
        p.title.setFont(f1);
        p.title.setLocation(0, 20);
        p.title.setSize(1920, 100);
        p.title.setHorizontalAlignment(SwingConstants.CENTER);

        p.player1 = new JLabel("Gracz 1");
        p.add(p.player1);
        p.player1.setFont(f2);
        p.player1.setLocation(50, 200);
        p.player1.setSize(860, 70);
        p.player1.setHorizontalAlignment(SwingConstants.CENTER);
        p.player1.setForeground(green);

        p.player2 = new JLabel("Gracz 2");
        p.add(p.player2);
        p.player2.setFont(f2);
        p.player2.setLocation(1010, 200);
        p.player2.setSize(860, 70);
        p.player2.setHorizontalAlignment(SwingConstants.CENTER);
        p.player2.setForeground(blue);

        p.getName1 = new JTextField("Podaj nazwę");
        p.add(p.getName1);
        p.getName1.setFont(f2);
        p.getName1.setLocation(230, 300);
        p.getName1.setSize(500, 70);
        p.getName1.setHorizontalAlignment(SwingConstants.CENTER);
        p.getName1.addFocusListener(p);
        p.getName1.setForeground(green);

        p.getName2 = new JTextField("Podaj nazwę");
        p.add(p.getName2);
        p.getName2.setFont(f2);
        p.getName2.setLocation(1190, 300);
        p.getName2.setSize(500, 70);
        p.getName2.setHorizontalAlignment(SwingConstants.CENTER);
        p.getName2.addFocusListener(p);
        p.getName2.setForeground(blue);

        p.colors1 = new JButton[5];  // ustaiwienie kolorow dla gracza 1
        int y = 420;
        int x = 290;
        for(int i = 0; i<5; i++)
        {
            JButton b = new JButton();
            p.add(b);
            b.addActionListener(p);
            b.setLocation(x, y);
            x += 80;
            b.setSize(60,  60);
            p.colors1[i] = b;
        }
        p.colors1[0].setBackground(green);
        p.colors1[1].setBackground(blue);
        p.colors1[2].setBackground(purple);
        p.colors1[3].setBackground(magenta);
        p.colors1[4].setBackground(red);


        p.colors2 = new JButton[5];  // ustaiwienie kolorow dla gracza 2
        x = 1250;
        for(int i = 0; i<5; i++)
        {
            JButton b = new JButton();
            p.add(b);
            b.addActionListener(p);
            b.setLocation(x, y);
            x += 80;
            b.setSize(60,  60);
            p.colors2[i] = b;
        }
        p.colors2[0].setBackground(green);
        p.colors2[1].setBackground(blue);
        p.colors2[2].setBackground(purple);
        p.colors2[3].setBackground(magenta);
        p.colors2[4].setBackground(red);

        p.gameSize = new JLabel("Wybierz rozmar planszy");
        p.add(p.gameSize);
        p.gameSize.setSize(1920, 70);
        p.gameSize.setFont(f2);
        p.gameSize.setLocation(0, 550);
        p.gameSize.setHorizontalAlignment(SwingConstants.CENTER);

        p.sizes = new JButton[7];

        x = 345;
        y = 640;

        for(int i = 0; i<7; i++)  // wybor rozmiaru
        {
            JButton b = new JButton((i+4) + "x" + (i+4));
            p.add(b);
            b.setLocation(x, y);
            b.setSize(150, 80);
            b.setFont(f3);
            b.addActionListener(p);
            p.sizes[i] = b;
            x += 180;
        }

        p.sizes[0].setEnabled(false);


        p.play = new JButton("START");
        p.add(p.play);
        p.play.addActionListener(p);
        p.play.setFont(f2);
        p.play.setSize(250, 100);
        p.play.setLocation(835, 800);

        // domysle ustawienie wartosci - jesli gracze tego nie zrobili
        p.size = 4;
        p.color1 = green;
        p.color2 = blue;
        p.colors1[1].setEnabled(false);
        p.colors2[0].setEnabled(false);

    }

    public static void settingsButtons(Panel p, Object o) // dzialanie przyciskow w oknie ustawien
    {
        Color green = new Color(20, 200, 40);
        Color blue = Color.blue;
        Color purple = new Color(179,102,255);
        Color magenta = Color.magenta;
        Color red = Color.red;

        for(int i = 0; i<p.colors1.length; i++)
        {
            if(o == p.colors1[i])
            {
                for(int j = 0; j<p.colors1.length; j++)
                {
                    p.colors2[j].setEnabled(true);
                }
                p.colors2[i].setEnabled(false);

                switch(i)
                {
                    case 0:
                        p.player1.setForeground(green);
                        p.getName1.setForeground(green);
                        p.color1 = green;
                        break;
                    case 1:
                        p.player1.setForeground(blue);
                        p.getName1.setForeground(blue);
                        p.color1 = blue;
                        break;
                    case 2:
                        p.player1.setForeground(purple);
                        p.getName1.setForeground(purple);
                        p.color1 = purple;
                        break;
                    case 3:
                        p.player1.setForeground(magenta);
                        p.getName1.setForeground(magenta);
                        p.color1 = magenta;
                        break;
                    case 4:
                        p.player1.setForeground(red);
                        p.getName1.setForeground(red);
                        p.color1 = red;
                        break;
                }
            }
        }

        for(int i = 0; i<p.colors2.length; i++)
        {
            if(o == p.colors2[i])
            {
                for(int j = 0; j<p.colors2.length; j++)
                {
                    p.colors1[j].setEnabled(true);
                }
                p.colors1[i].setEnabled(false);

                switch(i)
                {
                    case 0:
                        p.player2.setForeground(green);
                        p.getName2.setForeground(green);
                        p.color2 = green;
                        break;
                    case 1:
                        p.player2.setForeground(blue);
                        p.getName2.setForeground(blue);
                        p.color2 = blue;
                        break;
                    case 2:
                        p.player2.setForeground(purple);
                        p.getName2.setForeground(purple);
                        p.color2 = purple;
                        break;
                    case 3:
                        p.player2.setForeground(magenta);
                        p.getName2.setForeground(magenta);
                        p.color2 = magenta;
                        break;
                    case 4:
                        p.player2.setForeground(red);
                        p.getName2.setForeground(red);
                        p.color2 = red;
                        break;
                }
            }
        }

        for(int i = 0; i<p.sizes.length; i++)
        {
            if(o == p.sizes[i])
            {
                p.size = i + 4;
                for(int j = 0; j<p.sizes.length; j++) p.sizes[j].setEnabled(true);
                p.sizes[i].setEnabled(false);
            }
        }

        if(o == p.play)
        {
            for(int i = 0; i<p.sizes.length; i++)
            {
                p.remove(p.sizes[i]);
            }

            for(int i = 0; i<p.colors1.length; i++)
            {
                p.remove(p.colors1[i]);
                p.remove(p.colors2[i]);
            }


            p.name1 = p.getName1.getText();
            p.name2 = p.getName2.getText();
            p.remove(p.title);
            p.remove(p.player1);
            p.remove(p.player2);
            p.remove(p.getName1);
            p.remove(p.getName2);
            p.remove(p.gameSize);
            p.remove(p.play);

            p.revalidate();
            p.repaint();

            start(p);
        }

    }

    public static void end(Panel p) // ustawienie komponentow okna koncowego
    {
        for(int i = 0; i<(p.size*(p.size+1)*2); i++) p.remove(p.buttons[i]);
        p.remove(p.name1Label);
        p.remove(p.name2Label);
        p.remove(p.points1Label);
        p.remove(p.points2Label);
        p.remove(p.move);
        p.revalidate();
        p.repaint();

        p.result = new JLabel();
        p.points = new JLabel();
        p.restart = new JButton("Zagraj ponownie");
        p.exit = new JButton("Wyjscie");
        Font f1 = new Font("Font", 1, 90);
        Font f2 = new Font("Font", 1, 50);

        p.result.setLocation(0, 100);
        p.result.setSize(1920, 150);
        p.result.setFont(f1);
        p.result.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(p.result);

        p.points.setLocation(0, 350);
        p.points.setSize(1920, 150);
        p.points.setFont(f1);
        p.points.setHorizontalAlignment(SwingConstants.CENTER);
        p.add(p.points);



        if(p.points1 > p.points2)
        {
            p.result.setText("Wygrywa: " + p.name1);
            p.points.setText(p.points1 + " : " + p.points2);
        }
        else if(p.points1 < p.points2)
        {
            p.result.setText("Wygrywa: " + p.name2);
            p.points.setText(p.points2 + " : " + p.points1);
        }
        else
        {
            p.result.setText("REMIS");
            p.points.setText(p.points1 + " : " + p.points2);
        }

        p.restart.setLocation(560, 700);
        p.restart.setFont(f2);
        p.restart.setSize(800, 100);
        p.restart.addActionListener(p);
        p.add(p.restart);

        p.exit.setLocation(560, 850);
        p.exit.setFont(f2);
        p.exit.setSize(800, 100);
        p.exit.addActionListener(p);
        p.add(p.exit);
    }

    public static void endButtons(Panel p, Object o)    // dzialanie przyciskow w oknie koncowym
    {
        if(o == p.restart)
        {
            start(p);
        }
        else if(o == p.exit)
        {
            System.exit(0);
        }
    }

}
