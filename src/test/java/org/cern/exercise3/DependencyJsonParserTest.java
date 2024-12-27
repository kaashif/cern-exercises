package org.cern.exercise3;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4.class)
public class DependencyJsonParserTest {
    @Test
    public void parsesEmptyObject() {
        Assertions.assertEquals(Map.of(), DependencyJsonParser.parse("{}"));
    }

    @Test
    public void parsesPackageJson() throws IOException {
        // Given
        var json = new String(getClass().getResourceAsStream("/given-example.json").readAllBytes());

        // When
        Map<String, List<String>> parsed = DependencyJsonParser.parse(json);

        // Then
        var expected = Map.of(
            "pkg1", List.of("pkg2", "pkg3"),
            "pkg2", List.of("pkg3"),
            "pkg3", List.of()
        );

        Assertions.assertEquals(expected, parsed);
    }
}
