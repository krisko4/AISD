# AISD
Algorithms &amp; data structures

## Huffman algorithm

![1200px-Huffman_tree_2 svg](https://user-images.githubusercontent.com/80395610/152004649-dc7e7a91-bbf6-4e8f-93ae-a30d92101087.png)

This is an implementation of Huffman encoding algorithm. The program encodes all _.txt_ and decodes all _.bin_ files in given directory. During encoding, dictionary binary file is created in order to save the _Huffman tree_ which can be used to decode encoded file. Without valid dictionary file, decoding will fail - each file must be first encoded by the algorithm.
