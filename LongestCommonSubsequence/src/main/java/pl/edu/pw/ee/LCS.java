package pl.edu.pw.ee;

public abstract class LCS {

    public LCS(String topStr, String leftStr) {
        if (topStr == null || leftStr == null) {
            throw new IllegalArgumentException("Provided constructor params cannot be null");
        }
        if (topStr.equals("") || leftStr.equals("")) {
            throw new IllegalArgumentException("Providing an empty string value as constructor param is not allowed.");
        }
    }

}
