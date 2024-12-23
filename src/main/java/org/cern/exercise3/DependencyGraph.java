package org.cern.exercise3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DependencyGraph {
    private Map<String, List<String>> packageDependencies;

    public static DependencyGraph readFile(String fileName) throws IOException {
        return new DependencyGraph(new JsonParser(Files.readString(Path.of(fileName))).parseObject());
    }

    public static String prettyPrintFile(String fileName) throws IOException {
        return readFile(fileName).toString();
    }

    public DependencyGraph(Map<String, List<String>> packageDependencies) {
        this.packageDependencies = packageDependencies;
    }

    private void prettyPrint(StringBuilder stringBuilder, int indentLevel, String pkg) {
        stringBuilder.append(" ".repeat(2 * indentLevel));
        stringBuilder.append("- ");
        stringBuilder.append(pkg);
        stringBuilder.append("\n");

        for (String dependency : packageDependencies.get(pkg)) {
            prettyPrint(stringBuilder, indentLevel + 1, dependency);
        }
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        for (String pkg : packageDependencies.keySet()) {
            prettyPrint(stringBuilder, 0, pkg);
        }

        return stringBuilder.toString();
    }
}
