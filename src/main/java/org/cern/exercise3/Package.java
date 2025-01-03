package org.cern.exercise3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Package {
    private String name;
    private List<Package> dependencies;

    public Package(String name) {
        this.name = name;
        this.dependencies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Collection<Package> getDependencies() {
        return dependencies;
    }

    public void addDependency(Package pkg) {
        dependencies.add(pkg);
    }
}
