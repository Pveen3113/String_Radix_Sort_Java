package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Function to get the largest number of characters in a string from stringData array
    public static int getMaxCharacters(List<String> stringArr, int size) {
        //Initializing the length of first string in array as the largest number of characters in a string
        int maxCharac = stringArr.get(0).length();
        for (int i = 1; i < size; i++)
            if (stringArr.get(i).length() > maxCharac)
                maxCharac = stringArr.get(i).length();
        return maxCharac;
    }

    // radixSort is the method that performs sorting based on the placing of the character
    public static void radixSort(List<String> stringArr, int size, int p) {
        //Initialize empty array of size 26 to group strings from A - Z according to their placing
        ArrayList<String>[] sorted = new ArrayList[26];
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        //Initialize empty ArrayList in every elements in sorted array
        for (int i = 0; i < 26; i++)
            sorted[i] = new ArrayList<String>();

        //Sort the characters based on their placing and store them in sorted array according to their
        //alphabet group
        //Index 0 in sorted array depicts alphabet A
        //Index 1 in sorted array depicts alphabet B and etc
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (stringArr.get(i).charAt(p) == alphabet[j]) {
                    sorted[j].add(stringArr.get(i));
                }
            }
        }
        //Copy the sorted string to original string array based on the alphabet of a specific placing
        int index = 0;
        stringArr.clear();
        for (int i = 0; i < 26; i++) {
            //To get the sorted string for a particular alphabet in the output array
            int length = sorted[i].size();
            for (int j = 0; j < length; j++) {
                stringArr.add(index, sorted[i].get(j));
                index++;
            }
        }

    }

    public static void main(String[] args) {
        //To store the strings in sampleStrings
        List<String> sampleStrings = new ArrayList<String>();
        //ENTER THE TEXTFILE THAT CONTAINS STRING HERE OR
        //CHANGE THE TEXTFILE NAME
        File textFile = new File("sample_text.txt");
        try {
            Scanner myReader = new Scanner(textFile);
            while(myReader.hasNextLine()){
                sampleStrings.add(myReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Compute the size of sampleString to use later in the program
        int size = sampleStrings.size();
        //Set the starting time for the execution of the radixSort algorithm
        long startTime = System.currentTimeMillis();
        //Create a for loop to call radixSort function on every character placing in the string
        for (int i = getMaxCharacters(sampleStrings, size) - 1; i >= 0; i--) {
            radixSort(sampleStrings, size, i);
        }
        //Set the ending time for the execution of the radixSort algorithm
        long endTime = System.currentTimeMillis();
        //Calculate total execution time
        long elapsed = endTime - startTime;

        //Write all the sorted strings to a textfile
        try {
            FileWriter sortedStrings = new FileWriter("sorted_string.txt");
            for(int i = 0; i < size; i++){
                sortedStrings.write(sampleStrings.get(i)+"\n");
            }
            sortedStrings.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //Display the sorted string
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(sampleStrings.toString());
        System.gc();
        //Display the execution time
        System.out.println("This program took " + elapsed+ " miliseconds to execute");
        //Calculate the total memory allocated
        System.out.println("The total memory required "+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/(1024 * 1024) +"MB");
    }
}

