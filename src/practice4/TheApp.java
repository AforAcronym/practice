package practice4;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TheApp {

    static int minimizeCounter = 0;
    static int focusCounter = 0;
    static String title = "My App Window";
    static String titleFormat = "%s\tmin: %d\tfoc: %d";

    public static void main(String[] args) {
        final MyJFrame mjf = new MyJFrame(title);

        mjf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                super.windowIconified(e);
                mjf.setTitle(String.format(titleFormat, title, ++minimizeCounter, focusCounter));
            }
        });

        mjf.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                super.windowLostFocus(e);
                mjf.setTitle(String.format(titleFormat, title, minimizeCounter, ++focusCounter));
            }
        });

        mjf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.exit(0);
            }
        });

        mjf.setVisible(true);


    }
}
