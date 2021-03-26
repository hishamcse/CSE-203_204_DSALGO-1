package com.Hisham.Queue;

/*
 * Solution of Problem 2
 */

import java.util.Scanner;

public class StringStream {

    public String convertedString(String old_str) {
        char[] chars = old_str.toCharArray();
        StringBuilder new_String = new StringBuilder("String_new = ");
        CustomQueue<Character> temp = new CustomQueue<>();           // temporary queue to handle the characters

        int[] counter = new int[26];        // for counting the occurrence of any character. ('a' - 'z') = (97,122)

        for (char c : chars) {
            counter[c - 97]++;

            if (counter[c - 97] == 1) {
                temp.enqueue(c);
            }

            if (temp.length() != 0 && counter[temp.top() - 97] == 1) {
                new_String.append(temp.top());
            } else {
                while (temp.length() != 0) {
                    if (counter[temp.top() - 97] != 1) {
                        temp.dequeue();
                    } else {
                        new_String.append(temp.top());
                        break;
                    }
                }
                if (temp.length() == 0) {
                    new_String.append('#');
                }
            }
        }

        return new_String.toString();
    }

    public static void main(String[] args) {
        StringStream stream = new StringStream();
        System.out.println(stream.convertedString("abcabc"));
        System.out.println(stream.convertedString("aaaaa"));
        System.out.println(stream.convertedString("abadbc"));
        System.out.println(stream.convertedString("abacbad"));
        System.out.println(stream.convertedString("jyhrcwuengcbnuchctluxjgtxqtfvrebveewgasluuwooupcyxwgl"));

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(stream.convertedString(s));
    }
}