package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Reactor extends AbstractActor {
    private int temperature;
    private boolean state;
    private int damage;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation offAnimation;

    private boolean isRunning;

    public Reactor() {
        this.temperature = 0;
        this.state = false;
        this.damage = 0;
        this.offAnimation = new Animation(
            "sprites/reactor.png");

        this.normalAnimation = new Animation(
            "sprites/reactor_on.png",
            80, 80, 0.1F,
            Animation.PlayMode.LOOP_PINGPONG);

        this.hotAnimation = new Animation(
            "sprites/reactor_hot.png",
            80, 80, 0.1F,
            Animation.PlayMode.LOOP_PINGPONG);


        this.brokenAnimation = new Animation(
            "sprites/reactor_broken.png",
            80, 80, 0.1F,
            Animation.PlayMode.LOOP_PINGPONG);
        //nastavenie animacie na normalny reaktor
        setAnimation(this.offAnimation);
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getDamage() {
        return this.damage;
    }

    public void increaseTemperature(int increment) {
        if (increment < 0 || !isRunning()) {
            return;
        }
        this.temperature = this.temperature + increment;
        if (this.damage == 100) {
            return;
        }
        updateAnimation();

//      animacia vybuchujuci reaktor
//        if (this.temperature >= 6000) {
//            setAnimation(this.brokenAnimation);
//        } else
//            //      animacia prehrievajuci reaktor
//            if (this.temperature >= 4000) {
//                setAnimation(this.hotAnimation);
//            } else
//                //      normalna animacia
//                if (this.temperature < 2000) {
//                    setAnimation(this.normalAnimation);
//                }

//     nastavevanie damageu
        if (this.temperature > 2000) {
            if (this.temperature > 6000) {
                this.damage = 100;
                this.state=false;
            } else {
                int damage = (this.temperature / 40) - 50;
                if (damage > this.damage) {
                    this.damage = damage;
                }
            }
        }

    }


    public void decreaseTempreature(int decrement) {
        if (decrement < 0 || !isRunning()) {
            return;
        }
        this.temperature = this.temperature - decrement;
        if (this.damage == 100) {
            return;
        }
        updateAnimation();
    }

    public void turnOn() {
        if (this.damage==100){
            return;
        }
        setAnimation(normalAnimation);
        this.state = true;
        getAnimation().play();
    }

    public void turnOff() {
        this.state = false;
        if (this.damage==100){
            return;
        }
        getAnimation().pause();
    }

    public boolean isRunning() {
        return this.state;
    }


    //nova metoda na aktualizaciu animacie
    public void updateAnimation() {
        if (this.temperature >= 6000) {
            setAnimation(this.brokenAnimation);
        } else
            //      animacia prehrievajuci reaktor
            if (this.temperature >= 4000) {
                setAnimation(this.hotAnimation);
            } else
                //      normalna animacia
                if (this.temperature < 4000) {
                    setAnimation(this.normalAnimation);
                }

    }

    //metoda na opravenie reaktora pomocou kladiva
    public void repairWith(Hammer hammer) {
        if (hammer == null) {
            return;
        }


//        if (this.damage>0) {
//            hammer.use();
////            hammer.getUsages();
//            if (this.damage == 100) {
//                return;
//            } else {
//                if (this.damage > 50) {
//                    this.damage = this.damage - 50;
//                } else this.damage = 0;
//            }
//        }
        if (this.damage == 0 || this.damage == 100) {
            return;
        }
        if (this.damage < 50) {
            this.damage = 0;
            this.temperature = 0;
            updateAnimation();
        } else {
            this.damage = this.damage - 50;
            this.temperature = 2000;
            updateAnimation();
        }
    }


}











