package com.epam.web.entity;

import java.util.List;

public class TargetDto {

    private List<Target> targets;

    public TargetDto(List<Target> targets) {
        this.targets = targets;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

}
