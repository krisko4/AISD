package pl.edu.pw.ee;

import static java.lang.Math.min;

import static java.util.stream.Collectors.toList;

import java.util.*;

import pl.edu.pw.ee.services.PatternSearch;

public class DeterministicFiniteAutomatonTextSearch implements PatternSearch {

    private class Key {
        private final int state;
        private final char sign;

        public Key(int state, char sign) {
            this.state = state;
            this.sign = sign;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Key)) {
                return false;
            }
            Key key = (Key) o;
            return state == key.state && sign == key.sign;
        }

        @Override
        public int hashCode() {
            return Objects.hash(state, sign);
        }
    }

    public String getPattern() {
        return pattern;
    }

    private final String pattern;

    public boolean hasState(char key, int state) {
        return transMap.containsKey(new Key(state, key));
    }

    private Map<Key, Integer> transMap;

    public DeterministicFiniteAutomatonTextSearch(String pattern) {
        validateInput(pattern);
        this.pattern = pattern;
        buildTransitionMatrix();
    }

    @Override
    public int findFirst(String text) {
        List<Integer> indexes = find(text, true);
        return indexes.isEmpty() ? -1 : indexes.get(0);
    }

    private List<Integer> find(String text, boolean shouldFindFirst){
        validateInput(text);
        List<Integer> indexes = new ArrayList<>();
        int n = text.length();
        int acceptedState = pattern.length();
        int state = 0;
        for (int i = 0; i < n; i++) {
            Key key = new Key(state, text.charAt(i));
            if (!transMap.containsKey(key)) {
                state = 0;
                continue;
            }
            state = transMap.get(key);
            if (state == acceptedState) {
                int result = i - pattern.length() + 1;
                indexes.add(result);
                if(shouldFindFirst) break;
            }
        }
        return indexes;
    }

    @Override
    public int[] findAll(String text) {
        return find(text, false).stream().mapToInt(x->x).toArray();
    }

    private void validateInput(String txt) {
        if (txt == null) {
            throw new IllegalArgumentException("Input text cannot be null!");
        }
    }

    private void buildTransitionMatrix() {
        transMap = new HashMap<>();
        int m = pattern.length();
        List<Character> alphabet = getAlphabetOfPattern();
        for (int q = 0; q <= m; q++) {
            for (char sign : alphabet) {

                int k = min(m + 1, q + 2);
                k--;

                while (k > 0 && !isSuffixOfPattern(k, q, sign)) {
                    k--;
                }
                System.out.printf("sigma(%d, %c) = %d\n%n", q, sign, k);
                transMap.put(new Key(q, sign), k);
            }
        }
    }

    private List<Character> getAlphabetOfPattern() {
        return pattern.chars()
                .distinct()
                .mapToObj(c -> (char) c)
                .collect(toList());
    }

    private boolean isSuffixOfPattern(int kIndex, int qIndex, char sign) {
        boolean isSuffix = false;
        if (pattern.charAt(--kIndex) == sign) {
            isSuffix = true;

            while (kIndex > 0) {
                kIndex--;
                qIndex--;

                if (pattern.charAt(kIndex) != pattern.charAt(qIndex)) {
                    isSuffix = false;
                    break;
                }
            }
        }

        return isSuffix;
    }

}
