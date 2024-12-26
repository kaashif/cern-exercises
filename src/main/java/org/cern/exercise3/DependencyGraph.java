package org.cern.exercise3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependencyGraph {
    private Map<String, Package> pkgsByName;

    /**
     * @return Fully resolved dependency graph from the dependency JSON file filePath.
     */
    public static DependencyGraph readFile(Path filePath) throws IOException {
        return new DependencyGraph(DependencyJsonParser.parse(Files.readString(filePath)));
    }

    /**
     * @return Pretty printed representation of the fully resolved dependency graph.
     */
    public static String prettyPrintFile(Path filePath) throws IOException {
        return readFile(filePath).toString();
    }

    public DependencyGraph(Map<String, List<String>> pkgDependencies) {
        pkgsByName = new HashMap<>();

        for (String name: pkgDependencies.keySet()) {
            pkgsByName.put(name, new Package(name));
        }
        for (var entry: pkgDependencies.entrySet()) {
            String pkgName = entry.getKey();
            for (String dependencyName: entry.getValue()) {
                pkgsByName.get(pkgName).addDependency(pkgsByName.get(dependencyName));
            }
        }
    }

    public Map<String, Package> getPackages() {
        return pkgsByName;
    }

    private void prettyPrint(StringBuilder stringBuilder, int indentLevel, Package pkg) {
        stringBuilder.append(" ".repeat(2 * indentLevel));
        stringBuilder.append("- ");
        stringBuilder.append(pkg.getName());
        stringBuilder.append("\n");

        for (Package dependency : pkg.getDependencies()) {
            prettyPrint(stringBuilder, indentLevel + 1, dependency);
        }
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        for (Package pkg : pkgsByName.values()) {
            prettyPrint(stringBuilder, 0, pkg);
        }

        return stringBuilder.toString();
    }
}
