package com.main;

import java.awt.*;

class big_rock extends Rock{
    big_rock(int x, int y){
        super(x, y);
        type = "big";
        poly = new Polygon();
        poly.addPoint(75, 105);
        poly.addPoint(50, 100);
        poly.addPoint(22, 75);
        poly.addPoint(10, 87);
        poly.addPoint(-12, 107);
        poly.addPoint(-35, 87);
        poly.addPoint(-37, 75);
        poly.addPoint(-40, 50);
        poly.addPoint(-50, 25);
        poly.addPoint(-70, 12);
        poly.addPoint(-87, -15);
        poly.addPoint(-65, -37);
        poly.addPoint(-40, -60);
        poly.addPoint(-30, -80);
        poly.addPoint(20, -87);
        poly.addPoint(63, -65);
        poly.addPoint(86, -60);
        poly.addPoint(130, -50);
        poly.addPoint(125, -10);
        poly.addPoint(137, 20);
        poly.addPoint(117, 63);
        poly.addPoint(97, 75);
        vpoly = new Polygon();
        vpoly.addPoint(75, 105);
        vpoly.addPoint(50, 100);
        vpoly.addPoint(22, 75);
        vpoly.addPoint(10, 87);
        vpoly.addPoint(-12, 107);
        vpoly.addPoint(-35, 87);
        vpoly.addPoint(-37, 75);
        vpoly.addPoint(-40, 50);
        vpoly.addPoint(-50, 25);
        vpoly.addPoint(-70, 12);
        vpoly.addPoint(-87, -15);
        vpoly.addPoint(-65, -37);
        vpoly.addPoint(-40, -60);
        vpoly.addPoint(-30, -80);
        vpoly.addPoint(20, -87);
        vpoly.addPoint(63, -65);
        vpoly.addPoint(86, -60);
        vpoly.addPoint(130, -50);
        vpoly.addPoint(125, -10);
        vpoly.addPoint(137, 20);
        vpoly.addPoint(117, 63);
        vpoly.addPoint(97, 75);
        rspeed = .001f;
        durability = 6;
    }
}
class med_rock extends Rock{
    med_rock(int x, int y){
        super(x, y);
        type = "medium";
        poly = new Polygon();
        poly.addPoint(38, 0);
        poly.addPoint(26, 20);
        poly.addPoint(6, 28);
        poly.addPoint(-26, 28);
        poly.addPoint(-38, 0);
        poly.addPoint(-24, -36);
        poly.addPoint(18, -18);
        vpoly = new Polygon();
        vpoly.addPoint(38, 0);
        vpoly.addPoint(26, 20);
        vpoly.addPoint(6, 28);
        vpoly.addPoint(-26, 28);
        vpoly.addPoint(-38, 0);
        vpoly.addPoint(-24, -36);
        vpoly.addPoint(18, -18);
        rspeed = 0.001f;
        durability = 3;
    }
}
class small_rock extends Rock{
    small_rock(int x, int y){
        super(x, y);
        type = "small";
        poly = new Polygon();
        poly.addPoint(20 ,10);
        poly.addPoint(20 ,4);
        poly.addPoint(26 ,0);
        poly.addPoint(16 ,-4);
        poly.addPoint(12,-12);
        poly.addPoint(2,-6);
        poly.addPoint(-4,-2);
        poly.addPoint(-14,0);
        poly.addPoint(-10,6);
        vpoly = new Polygon();
        vpoly.addPoint(20 ,10);
        vpoly.addPoint(20 ,4);
        vpoly.addPoint(26 ,0);
        vpoly.addPoint(16 ,-4);
        vpoly.addPoint(12,-12);
        vpoly.addPoint(2,-6);
        vpoly.addPoint(-4,-2);
        vpoly.addPoint(-14,0);
        vpoly.addPoint(-10,6);
        rspeed = 0.006f;
        durability = 1;
    }
}

public class Rock extends VectorSprite {
    int dt = 0, md = 500; //distanced travelled, maximum distance
    int durability;
    String type;
    boolean active = true;
    float a, s;

    //Random rock variables
    static int rmin = 75, rmax = 150;

    Rock(int x, int y) {
        super(x, y);
        type = "default";
        rspeed = 0.0f;
        durability = 5;
        if(Game.ship != null) a = (float)Math.atan(((y - Game.ship.y) / (x - Game.ship.x))) + (float)(x >= Game.ship.x ? Math.PI : 0);
        else a = (float)Math.atan(((y - Start.ship.y) / (x - Start.ship.x))) + (float)(x >= Start.ship.x ? Math.PI : 0);
        s = .5f;
    }

    Rock(int x, int y, int n){
        super(x, y);
        double angle_step = Math.PI * 2 / n;
        poly = new Polygon();
        vpoly = new Polygon();
        for(int i = 0; i < n; i++){
            double angle = (angle_step * i) + (Game.r.nextDouble() - 0.5) * angle_step * 0.25;
            double radius = rmin + Game.r.nextDouble() * (rmax - rmin);
            poly.addPoint((int)(Math.cos(angle) * radius), (int)(Math.sin(angle) * radius));
            vpoly.addPoint((int)(Math.cos(angle) * radius), (int)(Math.sin(angle) * radius));
        }
        //poly.translate(x, y);
        type = "random";
        rspeed = .001f;
        durability = 100;
        if(Game.ship != null) a = (float)Math.atan(((y - Game.ship.y) / (x - Game.ship.x))) + (float)(x >= Game.ship.x ? Math.PI : 0);
        else a = (float)Math.atan(((y - Start.ship.y) / (x - Start.ship.x))) + (float)(x >= Start.ship.x ? Math.PI : 0);
        s = .5f;
    }

    boolean update_rock(){
        super.update();
        x += Math.cos(a) * s;
        y += Math.sin(a) * s;
        dt += Math.abs(Math.cos(a) * s) + Math.abs(Math.sin(a) * s);
        for(Bullet b : Game.bullets) if(hitbox().contains(b.hitbox())) {
            durability--;
            b.active = false;
            break;
        }
        if(active && Game.ship != null && Game.ship.hitbox().intersects(hitbox())) {
            UI.life--;
            Game.ship.reset();
            md = 0;
        }
        active = !(durability <= 0) && !(dt >= md); //don't let rock split if it needs to be removed for distance
        Rectangle rect = hitbox();
        if(!active && !(dt >= md)) {
            UI.score++;
            switch (type) {
                case "big":
                    Game.rocks.add(new med_rock(rect.x + (rect.width / 4), rect.y + (rect.height / 4)));
                    Game.rocks.add(new med_rock(rect.x + rect.width - (rect.width / 4), rect.y + rect.height + (rect.height / 4)));
                    return false;
                case "medium":
                    Game.rocks.add(new small_rock(rect.x + (rect.width / 4), rect.y + (rect.height / 4)));
                    Game.rocks.add(new small_rock(rect.x + rect.width - (rect.width / 4), rect.y + rect.height + (rect.height / 4)));
                    return false;
            }
        }
        return true;
    }

}
