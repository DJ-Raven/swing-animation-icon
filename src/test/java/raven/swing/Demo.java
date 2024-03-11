package raven.swing;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;
import raven.swing.animation.EasingInterpolator;
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
                .setInterpolator(EasingInterpolator.EASE_OUT_BOUNCE)
                .setDuration(1000);
        JCheckBox button1 = new JCheckBox("Monster", new AnimationIcon("raven/swing/monster.svg", animatedOption));
        JCheckBox button2 = new JCheckBox("Pug", new AnimationIcon("raven/swing/pug.svg", animatedOption));
        group.add(button1);
        group.add(button2);
        setLayout(new MigLayout("al center center"));
        add(button1);
        add(button2);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 18));
        EventQueue.invokeLater(() -> new Demo().setVisible(true));
    }
}
