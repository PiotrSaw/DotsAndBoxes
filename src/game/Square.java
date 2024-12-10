package game;

import javax.swing.*;

public class Square {

    public JButton left;
    public JButton right;
    public JButton up;
    public JButton down;

    public boolean l;
    public boolean r;
    public boolean u;
    public boolean d;

    public boolean closed;

    public Square(){
        l = false;
        r = false;
        u = false;
        d = false;
        closed = false;
    }

    public void status(){
        if(l && r &&  u && d) closed = true;
    }


}
