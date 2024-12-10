package game;

        import javax.swing.*;

public class Frame extends JFrame {

    private JPanel panel;

    public Frame()
    {
        super("Dots and Boxes");
        setVisible(true);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new Panel();
        add(panel);
    }




}
