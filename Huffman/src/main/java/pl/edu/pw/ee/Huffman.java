package pl.edu.pw.ee;

import com.igormaznitsa.jbbp.utils.JBBPUtils;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class Huffman {

    private int[] generateCharacterCountTable(String text) {
        int[] characterCountTable = new int[128];
        for (Character character : text.toCharArray()) {
            int ascii = (int) character;
            if (ascii > 127) {
                throw new IllegalArgumentException("Provided character : " + character + " is not accepted. Only ASCII signs can be encoded.");
            }
            characterCountTable[ascii] += 1;
        }
        return characterCountTable;
    }

    private HuffmanNode buildHuffmanTree(int[] characterCountTable) {
        Heap<HuffmanNode> priorityQueue = new Heap<>();
        for (int i = 0; i < characterCountTable.length; i++) {
            if (characterCountTable[i] > 0) {
                priorityQueue.put(new HuffmanLeaf((char) i, characterCountTable[i]));
            }
        }
        while (priorityQueue.size() > 1) {
            priorityQueue.put(new HuffmanNode(priorityQueue.pop(), priorityQueue.pop()));
        }
        return priorityQueue.pop();
    }

    private String addMissingBitsBinaryInformation(String binaryString) {
        StringBuilder binaryStringBuilder = new StringBuilder(binaryString);
        int binaryStringLength = binaryString.length();
        while (binaryStringBuilder.length() % 8 != 0) {
            binaryStringBuilder.append("0");
        }
        int missingBits = binaryStringBuilder.length() - binaryStringLength;
        byte[] missingBitBytes = {(byte) missingBits};
        String missingBitsBinary = JBBPUtils.bin2str(missingBitBytes);
        binaryStringBuilder.append(missingBitsBinary);
        return binaryStringBuilder.toString();
    }

    private String encodeTree(HuffmanNode node) {
        String encodedTree = encodeTree(node, "");
        return addMissingBitsBinaryInformation(encodedTree);
    }

    private String encodeTree(HuffmanNode node, String code) {
        if (node == null) {
            return code;
        }
        if (node instanceof HuffmanLeaf) {
            char character = ((HuffmanLeaf) node).getCharacter();
            byte[] characterBytes = {(byte) character};
            code += "1" + JBBPUtils.bin2str(characterBytes);
            return code;
        }
        code += "0";
        code = encodeTree(node.getLeft(), code);
        code = encodeTree(node.getRight(), code);
        return code;
    }

    private String[] generateDictionary(HuffmanNode root) {
        String[] dictionary = new String[128];
        generateDictionary(root, "", dictionary);
        return dictionary;
    }

    private void generateDictionary(HuffmanNode node, String code, String[] dictionary) {
        if (node == null) {
            return;
        }
        if (node instanceof HuffmanLeaf) {
            char character = ((HuffmanLeaf) node).getCharacter();
            dictionary[character] = code;
            return;
        }
        generateDictionary(node.getLeft(), code.concat("0"), dictionary);
        generateDictionary(node.getRight(), code.concat("1"), dictionary);
    }

    private String encode(String text, String[] dictionary) {
        StringBuilder encodedString = new StringBuilder();
        for (char character : text.toCharArray()) {
            encodedString.append(dictionary[character]);
        }
        return addMissingBitsBinaryInformation(encodedString.toString());
    }


    private HuffmanNode decodeTree(String text) {
        HuffmanTree huffmanTree = new HuffmanTree();
        String[] characters = text.split("");
        for (int i = 0; i < characters.length; i++) {
            if (characters[i].equals("1")) {
                StringBuilder binarySign = new StringBuilder();
                int j = i + 8;
                while (i < j) {
                    i++;
                    binarySign.append(characters[i]);
                }
                byte[] bytes = JBBPUtils.str2bin(binarySign.toString());
                char sign = (char) bytes[0];
                huffmanTree.put(new HuffmanLeaf(sign));
                continue;
            }
            huffmanTree.put(new HuffmanNode());
        }
        return huffmanTree.getRoot();
    }

    private String decode(String text, HuffmanNode root) {
        StringBuilder encodedString = new StringBuilder();
        HuffmanNode node = root;
        for (char character : text.toCharArray()) {
            node = character == '0' ? node.getLeft() : node.getRight();
            if (node == null) {
                throw new IllegalStateException("Unable to decode file. Decoded file contains invalid data.");
            }
            if (node instanceof HuffmanLeaf) {
                encodedString.append(((HuffmanLeaf) node).getCharacter());
                node = root;
            }
        }
        return encodedString.toString();
    }

    private void generateEncodedFiles(File file, byte[] convertedText, byte[] convertedDictionary) throws IOException {
        String fileName = FileUtils.getFileNameFromFile(file);
        FileUtils.generateOutputFile(file.getParent() + "/" + fileName + ".bin", convertedText);
        FileUtils.generateOutputFile(file.getParent() + "/" + fileName + "_dict.bin", convertedDictionary);
    }

    private void generateDecodedFile(File file, byte[] convertedText) throws IOException {
        String fileName = FileUtils.getFileNameFromFile(file);
        FileUtils.generateOutputFile(file.getParent() + "/" + fileName + ".txt", convertedText);
    }


    private int encodeFile(File file) throws IOException {
        String content = Files.readString(Path.of(file.getAbsolutePath()));
        if (content.length() == 0) {
            throw new IllegalArgumentException("Unable to encode empty file: " + file.getName());
        }
        int[] characterCountTable = generateCharacterCountTable(content);
        HuffmanNode root = buildHuffmanTree(characterCountTable);
        String[] dictionary = generateDictionary(root);
        String encodedTree = encodeTree(root);
        String encodedText = encode(content, dictionary);
        byte[] convertedText = JBBPUtils.str2bin(encodedText);
        byte[] convertedDictionary = JBBPUtils.str2bin(encodedTree);
        generateEncodedFiles(file, convertedText, convertedDictionary);
        return encodedText.length();
    }

    private String getBinaryString(byte[] binaryTextBytes) {
        int redundantBitsLength = binaryTextBytes[binaryTextBytes.length - 1];
        if (redundantBitsLength < 0 || redundantBitsLength > 7) {
            throw new IllegalStateException("Binary version of dictionary is invalid.");
        }
        String text = JBBPUtils.bin2str(binaryTextBytes);
        return text.substring(0, text.length() - redundantBitsLength - 8);
    }

    private String getFileContent(File file) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Path.of(file.getAbsolutePath()));
        if (fileBytes.length == 0) {
            throw new IllegalArgumentException("Unable to decode empty file: " + file.getName());
        }
        return getBinaryString(fileBytes);
    }

    private int decodeFile(File file, File dictionaryFile) throws IOException {
        String binaryTextString = getFileContent(file);
        String binaryDictionaryString = getFileContent(dictionaryFile);
        HuffmanNode root = decodeTree(binaryDictionaryString);
        String result = decode(binaryTextString, root);
        byte[] resultBytes = result.getBytes();
        generateDecodedFile(file, resultBytes);
        return result.length();
    }


    public int huffman(String pathToRootDir, boolean compress) throws IOException {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path to root directory cannot be null");
        }
        File folder = new File(pathToRootDir);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null || listOfFiles.length == 0) {
            throw new IllegalArgumentException("No files found in provided directory");
        }
        int result = 0;
        for (File file : listOfFiles) {
            if (compress) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println("Encoding file: " + file.getName());
                    int encodingResult = encodeFile(file);
                    System.out.println("Number of bits in encoded file: " + encodingResult);
                    result += encodingResult;
                }
                continue;
            }
            if (file.isFile() && file.getName().endsWith(".bin") && !file.getName().endsWith("_dict.bin")) {
                File dictionary = null;
                String fileName = FileUtils.getFileNameFromFile(file);
                for (File dictionaryFile : listOfFiles) {
                    if (dictionaryFile.isFile() && dictionaryFile.getName().equals(fileName + "_dict.bin")) {
                        dictionary = dictionaryFile;
                        break;
                    }
                }
                if (dictionary == null) {
                    throw new FileNotFoundException("File: " + file.getName() + " is missing dictionary file.");
                }
                System.out.println("Decoding file: " + file.getName());
                int decodingResult = decodeFile(file, dictionary);
                System.out.println("Number of characters in decoded file: " + decodingResult);
                result += decodingResult;
            }
        }
        return result;
    }


}
