package raven.swing;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.AnimatedIcon;
import com.formdev.flatlaf.util.Animator;
import raven.swing.animation.Interpolator;
import raven.swing.animation.color.AnimationColorFilter;

import javax.swing.*;
import java.awt.*;

public class AnimationIcon implements AnimatedIcon {

    private final AnimatedOption animatedOption;
    private final FlatSVGIcon icon;
    private final float maxScale;

    public AnimationIcon(String name, AnimatedOption animatedOption) {
        this(name, 1f, animatedOption);
    }

    public AnimationIcon(String name, float scale, AnimatedOption animatedOption) {
        this.animatedOption = animatedOption;
        maxScale = animatedOption.scaleInterpolator == null ? 0f : animatedOption.scaleInterpolator.getMaxValue();
        icon = new FlatSVGIcon(name, scale);
    }

    @Override
    public void paintIconAnimated(Component component, Graphics graphics, int x, int y, float animatedValue) {
        Graphics2D g2 = (Graphics2D) graphics.create();
        float scale = 1f + getInterpolator(animatedOption.scaleInterpolator, animatedValue, 0);
        if (scale > 0) {
            int centerX = x + getIconWidth() / 2;
            int centerY = y + getIconHeight() / 2;
            x += ((getIconWidth() - icon.getIconWidth()) / 2);
            y += ((getIconHeight() - icon.getIconWidth()) / 2);
            g2.translate(centerX, centerY);
            g2.rotate(Math.toRadians(getInterpolator(animatedOption.rotateInterpolator, animatedValue, 0)));
            g2.scale(scale, scale);
            g2.translate(-centerX, -centerY);
            icon.paintIcon(component, g2, x, y);
        }
    }

    @Override
    public float getValue(Component component) {
        return ((AbstractButton) component).isSelected() ? 1 : 0;
    }

    @Override
    public int getAnimationDuration() {
        return animatedOption.duration;
    }

    @Override
    public Animator.Interpolator getAnimationInterpolator() {
        return animatedOption.interpolator != null ? animatedOption.interpolator : AnimatedIcon.super.getAnimationInterpolator();
    }

    @Override
    public int getIconWidth() {
        int width = icon.getIconWidth();
        if (maxScale > 0) {
            int scaleWidth = (int) (icon.getIconWidth() * maxScale);
            width += scaleWidth;
        }
        return width;
    }

    @Override
    public int getIconHeight() {
        int height = icon.getIconHeight();
        if (maxScale > 0) {
            int scaleHeight = (int) (icon.getIconHeight() * maxScale);
            height += scaleHeight;
        }
        return height;
    }

    protected float getInterpolator(Interpolator interpolator, float fraction, float isNull) {
        if (interpolator != null) {
            return interpolator.interpolate(fraction);
        } else {
            return isNull;
        }
    }


    public static class AnimatedOption {

        protected Animator.Interpolator interpolator;
        protected Interpolator scaleInterpolator;
        protected Interpolator rotateInterpolator;
        protected AnimationColorFilter colorFilter;
        protected int duration = 150;


        public AnimatedOption setInterpolator(Animator.Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public AnimatedOption setScaleInterpolator(Interpolator scaleInterpolator) {
            this.scaleInterpolator = scaleInterpolator;
            return this;
        }

        public AnimatedOption setRotateInterpolator(Interpolator rotateInterpolator) {
            this.rotateInterpolator = rotateInterpolator;
            return this;
        }

        public AnimatedOption setColorFilter(AnimationColorFilter colorFilter) {
            this.colorFilter = colorFilter;
            return this;
        }

        public AnimatedOption setDuration(int duration) {
            this.duration = duration;
            return this;
        }
    }
}
