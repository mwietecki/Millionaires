package org.example;

public class Player {
    private String name;
    private int money;
    private int lifebuoys;

    public Player(String name) {
        this.name = name;
        this.money = 0;
        this.lifebuoys = 3;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getLifebuoys() {
        return lifebuoys;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void useLifebuoy() {
        lifebuoys--;
    }

    public boolean hasLifebuoys() {
        return lifebuoys > 0;
    }
}
