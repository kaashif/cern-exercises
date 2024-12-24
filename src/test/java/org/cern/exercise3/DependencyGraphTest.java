package org.cern.exercise3;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

@RunWith(JUnit4.class)
public class DependencyGraphTest {
    String givenPrettyPrintOutput = """
            - pkg1
              - pkg2
                - pkg3
              - pkg3
            - pkg2
              - pkg3
            - pkg3
            """;
    @Test
    public void prettyPrintsGraph() {
        // Given
        var dependencies = new HashMap<String, List<String>>() {{
            put("pkg1", List.of("pkg2", "pkg3"));
            put("pkg2", List.of("pkg3"));
            put("pkg3", List.of());
        }};

        // When
        var prettyPrinted = new DependencyGraph(dependencies).toString();

        // Then
        Assertions.assertEquals(givenPrettyPrintOutput, prettyPrinted);
    }

    @Test
    public void prettyPrintsFile() throws URISyntaxException, IOException {
        // When
        var prettyPrinted = DependencyGraph.prettyPrintFile(Path.of(getClass().getResource("/given-example.json").toURI()).toString());

        // Then
        Assertions.assertEquals(givenPrettyPrintOutput, prettyPrinted);
    }
}
