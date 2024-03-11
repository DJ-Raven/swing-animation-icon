package raven.swing.animation;

import com.formdev.flatlaf.util.Animator;

public interface Interpolator extends Animator.Interpolator {
    float getMaxValue();
}
