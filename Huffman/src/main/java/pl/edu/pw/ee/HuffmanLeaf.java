package pl.edu.pw.ee;

public class HuffmanLeaf extends HuffmanNode {


    public HuffmanLeaf(char character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    private final Character character;

    public HuffmanLeaf(char character, int frequency) {
        super(frequency);
        this.character = character;

    }


}
