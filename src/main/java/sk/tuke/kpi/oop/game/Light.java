package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Light extends AbstractActor {
    private boolean isOn;
    private Animation lightOff;
    private Animation lightOn;

    public Light() {
        this.isOn = false;
        this.lightOff = new Animation("sprites/light_off.png");
        this.lightOn = new Animation("sprites/light_on.png");
        setAnimation(lightOff);
    }

    public void toggle() {
        if (this.isOn) {
            this.isOn = false;
            setAnimation(lightOff);
        } else {
            this.isOn = true;
            setAnimation(lightOn);
        }

    }
}
