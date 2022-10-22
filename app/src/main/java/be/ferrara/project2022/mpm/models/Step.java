package be.ferrara.project2022.mpm.models;

import java.io.Serializable;
import java.util.UUID;

public class Step implements Serializable {
    private String mName;
    private int mPoints;
    private java.util.UUID mId;

public Step() {
    mName = "";
    mId= UUID.randomUUID();
}
public Step(UUID id){
    mId = id;
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

    public UUID getId() { return mId; }

    public void setPoints(int mPoints) {
        this.mPoints = mPoints;
    }
}
