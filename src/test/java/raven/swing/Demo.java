package raven.swing;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;
import raven.swing.animation.KeyFrames;

import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame {

    public Demo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(UIScale.scale(new Dimension(500, 300)));
        setLocationRelativeTo(null);
        ButtonGroup group = new ButtonGroup();
        AnimationIcon.AnimatedOption animatedOption = new AnimationIcon.AnimatedOption()
                .setScaleInterpolator(new KeyFrames(0f, 1f))
                .setRotateInterpolator(new KeyFrames(0f, 30f, 0f))
                .setDuration(1000);
        JToggleButton button1 = new JToggleButton("Monster", new AnimationIcon("raven/swing/monster.svg", animatedOption));
        JToggleButton button2 = new JToggleButton("Pug", new AnimationIcon("raven/swing/pug.svg", animatedOption));
        JToggleButton button3 = new JToggleButton("Fox", new AnimationIcon("raven/swing/fox.svg", animatedOption));
        JToggleButton button4 = new JToggleButton("Lemur", new AnimationIcon("raven/swing/lemur.svg", animatedOption));
        group.add(button1);
        group.add(button2);
        group.add(button3);
        group.add(button4);
        addStyle(button1, button2, button3, button4);
        setLayout(new MigLayout("al center center"));
        add(button1);
        add(button2);
        add(button3);
        add(button4);
    }

    private void addStyle(JToggleButton... button) {
        for (JToggleButton b : button) {
            b.putClientProperty(FlatClientProperties.STYLE, "" +
                    "background:null;" +
                    "selectedBackground:null;" +
                    "borderWidth:0;" +
                    "focusWidth:0;" +
                    "innerFocusWidth:0;");
        }
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new Demo().setVisible(true));
    }
}
