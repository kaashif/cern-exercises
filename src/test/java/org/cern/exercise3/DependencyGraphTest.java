package org.cern.exercise3;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.List;

@RunWith(JUnit4.class)
public class DependencyGraphTest {
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
        String expected = """
                - pkg1
                  - pkg2
                    - pkg3
                  - pkg3
                - pkg2
                  - pkg3
                - pkg3
                """;
        Assertions.assertEquals(expected, prettyPrinted);
    }
}
