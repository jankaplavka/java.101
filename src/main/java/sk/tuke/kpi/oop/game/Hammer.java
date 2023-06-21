package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {
    private int usages;

    //
//konstruktor
    public Hammer() {
        this.usages = 1;
        Animation animation = new Animation("sprites/hammer.png");
        setAnimation(animation);
    }

    public int getUsages() {
        return (this.usages);
    }

//metoda na puzitie kladiva
public void use(){
        this.usages--;
        if (this.usages==0){
            getScene().removeActor(this);
    }

}

}
