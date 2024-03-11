package raven.swing.animation.color;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.util.ColorFunctions;
import com.formdev.flatlaf.util.GrayFilter;

import java.awt.*;

public class GrayColorFilter implements AnimationColorFilter {

    private final boolean grayToColor;
    private final float percent;

    public GrayColorFilter() {
        this(true, 1f);
    }

    public GrayColorFilter(float percent) {
        this(true, percent);
    }

    public GrayColorFilter(boolean grayToColor) {
        this(grayToColor, 1f);
    }

    public GrayColorFilter(boolean grayToColor, float percent) {
        this.grayToColor = grayToColor;
        this.percent = percent;
    }

    @Override
    public Color filter(Color color, float fraction) {
        Color grayColor = new Color(GrayFilter.createDisabledIconFilter(FlatLaf.isLafDark()).filterRGB(0, 0, color.getRGB()));
        return ColorFunctions.mix(color, grayColor, grayToColor ? ((1f - percent) + fraction * percent) : 1f - (fraction * percent));
    }
}
