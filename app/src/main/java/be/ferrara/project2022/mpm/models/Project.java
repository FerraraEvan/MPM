package be.ferrara.project2022.mpm.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project implements Serializable {
    private String mName;
    private  String mDescription;
    private final List<Step> mStepList=new ArrayList<>();
    private java.util.UUID mId;
    private int mTotal=0;

    public Project(){
        mName = "Project";
        mDescription = "Description";
        mId= UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public int getTotalPoint() {
        if (mStepList.size() != 0)
            return ((mTotal / mStepList.size() * 2));
        else
            return 0;
    }

    public void setTotal(int mTotal) {
        this.mTotal +=mTotal;
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
