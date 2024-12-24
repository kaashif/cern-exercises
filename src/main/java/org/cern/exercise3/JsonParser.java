package org.cern.exercise3;

import java.util.*;

/**
 * Parser for the subset of JSON required for the exercise.
 */
public class JsonParser {
    String input;
    int pos;

    public JsonParser(String input) {
        this.input = input;
        this.pos = 0;
    }

    void skipZeroOrMore(Set<Character> chars) {
        while (chars.contains(input.charAt(pos))) {
            pos++;
        }
    }

    void skipWhitespace() {
        skipZeroOrMore(Set.of(' ', '\n', '\r'));
    }

    void skipOne(char c) {
        if (input.charAt(pos) != c) {
            throw new IllegalArgumentException("expected " + c + " at " + pos + " got '" + input.charAt(c) + "'");
        }
        pos++;
    }

    String parseString() {
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

    List<String> parseList() {
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

    Map<String, List<String>> parseObject() {
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
