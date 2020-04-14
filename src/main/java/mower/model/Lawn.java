package mower.model;

import java.util.ArrayList;
import java.util.List;

public class Lawn {

    private int width;
    private int height;
    private List<Mower> mowers = new ArrayList<>();

    public Lawn(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addMower(Mower mower) {
        this.mowers.add(mower);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Mower> getMowers() {
        return mowers;
    }

}
