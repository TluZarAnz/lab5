package tools;

import utils.StudyGroup;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class StringTool {
    /**
     *Удобный сканер
     * @return
     */
    public static String Input(){
        return new Scanner(System.in).nextLine();
    }

    /**
     * Выводит элементы коллекции
     * @param hashMap
     */
    public void PrintLabWorkSet(Stack<StudyGroup> hashMap){
        Iterator<StudyGroup> iterator = hashMap.stream().iterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next().toString());
        }
    }}