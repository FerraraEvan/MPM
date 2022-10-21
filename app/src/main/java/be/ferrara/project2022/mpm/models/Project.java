package be.ferrara.project2022.mpm.models;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Project implements Serializable {
    private String mName;
    private  String mDescription;
    private List<Step> mStepList=new ArrayList<>();
    private int mTotalPoint;
    private java.util.UUID mId;

    public Project(){
        mName = "Project";
        mDescription = "Description";
        mId= UUID.randomUUID();
        mTotalPoint = 0;
    }

    public UUID getId() {
        return mId;
    }

    public int getTotalPoint() {
        for (Step step : mStepList) {
            mTotalPoint+=step.getPoints();
        }
            return mTotalPoint;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public List<Step> getStepList() {
        return mStepList;
    }

    public void addStep(Step step){mStepList.add(step);}
}
