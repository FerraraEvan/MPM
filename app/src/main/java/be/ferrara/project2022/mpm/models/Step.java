package be.ferrara.project2022.mpm.models;

import java.io.Serializable;

public class Step implements Serializable {
    private String mName;
    private int mPoints;

public Step() {
    mName = "";
    mPoints = 0;
}
    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getPoints() {
        return mPoints;
    }
}
