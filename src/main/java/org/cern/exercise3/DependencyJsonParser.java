package org.cern.exercise3;

import java.util.*;

/**
 * Parser only capable of parsing the subset of JSON needed for parsing package dependency files.
 */
public class DependencyJsonParser {
    final String input;
    int pos;

    public static Map<String, List<String>> parse(String input) {
        return new DependencyJsonParser(input).parseObject();
    }

    private DependencyJsonParser(String input) {
        this.input = input;
        this.pos = 0;
    }

    private void skipZeroOrMore(Set<Character> chars) {
        while (chars.contains(input.charAt(pos))) {
            pos++;
        }
    }

    private void skipWhitespace() {
        skipZeroOrMore(Set.of(' ', '\n', '\r'));
    }

    private void skipOne(char c) {
        if (input.charAt(pos) != c) {
            throw new IllegalArgumentException("expected " + c + " at " + pos + " got '" + input.charAt(c) + "'");
        }
        pos++;
    }

    private String parseString() {
        var stringBuilder = new StringBuilder();

        // Skip opening quote
        skipOne('"');

        char c;
        while ((c = input.charAt(pos)) != '"') {
            pos++;
            if (c == '\\') {
                if (input.charAt(pos) == '\"') {
                    stringBuilder.append('\"');
                    pos++;
                } else {
                    throw new IllegalArgumentException("escape sequence \\" + input.charAt(pos) + " unsupported");
                }
            } else {
                stringBuilder.append(c);
            }
        }

        // Skip closing quote
        skipOne('"');

        return stringBuilder.toString();
    }

    private List<String> parseList() {
        var result = new ArrayList<String>();
        skipOne('[');
        skipWhitespace();

        while (input.charAt(pos) != ']') {
            result.add(parseString());
            skipWhitespace();

            if (input.charAt(pos) != ']') {
                // If we're not the last item, then we expect a comma delimiter
                skipOne(',');
                skipWhitespace();
            }
        }

        skipOne(']');
        return result;
    }

    private Map<String, List<String>> parseObject() {
        skipOne('{');
        skipWhitespace();

        var result = new HashMap<String, List<String>>();

        while (input.charAt(pos) != '}') {
            String pkg = parseString();
            skipWhitespace();
            skipOne(':');
            skipWhitespace();
            List<String> dependencies = parseList();
            skipWhitespace();

            result.put(pkg, dependencies);

            if (input.charAt(pos) != '}') {
                // We're not the last item and need a comma delimiter
                skipOne(',');
                skipWhitespace();
            }
        }

        skipOne('}');
        return result;
    }
}
