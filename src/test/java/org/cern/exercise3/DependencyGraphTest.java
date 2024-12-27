package org.cern.exercise3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class DependencyGraphTest {
    final String givenPrettyPrintOutput = """
            - pkg1
              - pkg2
                - pkg3
              - pkg3
            - pkg2
              - pkg3
            - pkg3
            """;
    final Map<String, List<String>> givenDependencies = Map.of(
        "pkg1", List.of("pkg2", "pkg3"),
        "pkg2", List.of("pkg3"),
        "pkg3", List.of()
    );

    @Test
    public void resolvesEmptyGraph() {
        Assertions.assertEquals(Map.of(), new DependencyGraph(Map.of()).getPackages());
    }

    @Test
    public void resolvesGivenGraphCorrectly() {
        var pkgsByName = new DependencyGraph(givenDependencies).getPackages();

        for (String name: givenDependencies.keySet()) {
            Assertions.assertTrue(pkgsByName.containsKey(name));

            // Ensure the given dependencies match the ones in the resolved graph.
            Assertions.assertEquals(
                givenDependencies.get(name),
                pkgsByName.get(name).getDependencies().stream().map(Package::getName).toList()
            );
        }
    }

    @Test
    public void prettyPrintsGraph() {
        Assertions.assertEquals(
            givenPrettyPrintOutput,
            new DependencyGraph(givenDependencies).toString()
        );
    }

    @Test
    public void prettyPrintsFile() throws URISyntaxException, IOException {
        // When
        var prettyPrinted = DependencyGraph.prettyPrintFile(Path.of(getClass().getResource("/given-example.json").toURI()));

        // Then
        Assertions.assertEquals(givenPrettyPrintOutput, prettyPrinted);
    }
}
