package practice4;

import javax.swing.*;
import java.awt.*;


public class MyJFrame extends JFrame {

    public MyJFrame() {
        super();

    }

    public MyJFrame(String title) {
        super(title);
    }

    public void frameInit() {
        super.frameInit();
        int width = 400;
        int height = 200;

        Toolkit tkt = Toolkit.getDefaultToolkit();
        Dimension screenDim = tkt.getScreenSize();

        double screenWidth = screenDim.getWidth();
        double screenHeight = screenDim.getHeight();

        setSize(width, height);
        setLocation((int) (screenWidth - width) / 2,
                (int) (screenHeight - height) / 2);

    }

}
