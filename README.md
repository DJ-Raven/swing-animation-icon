# Swing Animation icon

Swing animation icon custom from flatlaf `com.formdev.flatlaf.util.AnimatedIcon` and it work with svg file

<img src="https://github.com/DJ-Raven/swing-animation-icon/blob/main/screenshot/sample.jpg" width="350" alt="sample"/>

## Installation
This project library do not available in maven central. so you can install with the jar library
- Copy jar library file to the root project. exp : `library/swing-animation-icon-1.0.0.jar`
- Add this code to `pom.xml`
``` xml
<dependency>
    <groupId>raven.swing</groupId>
    <artifactId>swing-animation-icon</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>${basedir}/library/swing-animation-icon-1.0.0.jar</systemPath>
</dependency>
```
- Other library are use with this library
``` xml
<dependency>
  <groupId>com.formdev</groupId>
  <artifactId>flatlaf</artifactId>
  <version>3.4</version>
</dependency>

<dependency>
  <groupId>com.formdev</groupId>
  <artifactId>flatlaf-extras</artifactId>
  <version>3.4</version>
</dependency>
```
## Sample
``` java
// create animated option
AnimationIcon.AnimatedOption animatedOption = new AnimationIcon.AnimatedOption()
  .setScaleInterpolator(new KeyFrames(0f, 1f))
  .setRotateInterpolator(new KeyFrames(0f, 30f, 0f))
  .setDuration(1000);

// create animation icon
AnimationIcon animationIcon = new AnimationIcon("raven/swing/monster.svg", animatedOption);

// use with toggle button
JToggleButton button = new JToggleButton(animationIcon);

// use with checkbox
JCheckBox checkBox = new JCheckBox(animationIcon);

// use with radio button
JRadioButton radioButton = new JRadioButton(animationIcon);
```
## Sample custom with jbutton rollover
``` java
private class AnimationIconCustom extends AnimationIcon {

  public AnimationIconCustom(String name, float scale, AnimatedOption animatedOption) {
    super(name, scale, animatedOption);
  }

  @Override
  public float getValue(Component component) {
    JButton button=(JButton) component;
    return button.getModel().isRollover() ? 1f : 0f;
  }
}
```
