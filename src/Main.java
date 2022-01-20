package Huffman;

import java.util.Arrays;

import Huffman.Node;
import Huffman.PriorityQue;

public class Main {

    public static void main(String[] args) {

        String string1 = "Hello World";
        //String string1 = "Peter Piper picked a peck of pickled peppers";
        //String string1 = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?";
        //String string1 = "She sells seashells by the seashore";
        //String string1 = "Fuzzy Wuzzy was a bear. Fuzzy Wuzzy had no hair. Fuzzy Wuzzy wasn’t fuzzy, was he?";
        //String string1 = " aaaaaaaaaaeeeeeeeeeeeeeeellllllllllllsssttttppppppppppppp";
        System.out.println(string1);
        int length = string1.length();
        // System.out.println("word length: " + length);  //for testing

        char letters[] = new char[length];//creates char and frequency arrays
        int frequency[] = new int[length];

        for (int i = 0; i<length; i++){//inputs char array and inputs frequencies to 1
            frequency[i] = 1;
            letters[i] = string1.charAt(i);
        }

        for (int i = 0; i<length; i++){//counts frequency, changed to 0 when repeated
            for(int j = i+1; j<length;j++){
                if (frequency[i] > 0 && string1.charAt(i) == string1.charAt(j) ){
                    frequency[i]++;
                    frequency[j]=0;
                }
            }
        }


        PriorityQue phrase = new PriorityQue();//priority que largest at tail
        // head=> smallest - medium - largest <=tail

        for (int i=0;i<length;i++){
            int freq = frequency[i];
            if (freq > 0){
                char temp = string1.charAt(i);
                phrase.insert(temp, freq, null, null);//insert each item into the que without duplicates

            }
        }

  //      phrase.printSize(); //for testing
  //      phrase.print(); // for testing // print que: frequency and character

        char[] huffmanTableChars = new char[phrase.currentSize];
        huffmanTableChars = phrase.fillArrayFromQueChar(huffmanTableChars);//creates a char array without duplicates

        phrase.formTree();

  //      phrase.printSize(); //for testing
  //       phrase.print(); // for testing // prints que with 1 item left
   //     phrase.printTree();//for testing // prints tree recursivly: character, frequency, binary

        int[] code = new int[huffmanTableChars.length];
        code = phrase.fillCodeArray(phrase.head, code, huffmanTableChars, 0);// make code table recurcivly

        for (int i=0;i<code.length;i++){//print code table
            System.out.println(huffmanTableChars[i] + ", " + code[i]);
        }
    }
}
