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
public class JsonParserTest {
    @Test
    public void parsesEmptyObject() {
        Assertions.assertEquals(Map.of(), new JsonParser("{}").parseObject());
    }
    @Test
    public void parsesPackageJson() throws IOException {
        // Given
        var json = new String(getClass().getResourceAsStream("/given-example.json").readAllBytes());

        // When
        Map<String, List<String>> parsedDependencies = new JsonParser(json).parseObject();

        // Then
        var expected = new HashMap<String, List<String>>() {{
            put("pkg1", List.of("pkg2", "pkg3"));
            put("pkg2", List.of("pkg3"));
            put("pkg3", List.of());
        }};

        Assertions.assertEquals(expected, parsedDependencies);
    }
}
