package practice4;


import javax.swing.*;

public class TopLevelWindows {
    public static void main(String[] args) {

//        JFrame frame = new JFrame("The Frame");
//
//        frame.setSize(300, 300);
//        frame.setLocation(100, 100);

        JWindow window = new JWindow();

        window.setSize(300, 300);
        window.setLocation(500, 100);

//        frame.setVisible(true);
        window.setVisible(true);
    }
}
