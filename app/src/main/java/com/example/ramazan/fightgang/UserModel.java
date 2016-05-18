package com.example.ramazan.fightgang;

/**
 * Created by ramazan on 13.05.2016.
 */
public class UserModel {

    int id;
    String alias;
    int exp;
    int level;
    int expNet;
    int hits;
    int stamina;
    int maxStamina;

    public UserModel() {
    }

    public UserModel(int id, String alias, int exp, int level, int expNet, int hits, int stamina, int maxStamina) {
        this.id = id;
        this.alias = alias;
        this.exp = exp;
        this.level = level;
        this.expNet = expNet;
        this.hits = hits;
        this.stamina = stamina;
        this.maxStamina = maxStamina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpNet() {
        return expNet;
    }

    public void setExpNet(int expNet) {
        this.expNet = expNet;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }
}
