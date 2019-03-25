package com.questionpro;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseMain {

    private boolean executeQuery(String command, String tableName, String columns){
        switch (command){
            case "MAKE" :
                //createTable();
                break;
            case "PUSH" : break;
                //insertRecord();
            case "PULL" : break;
                //fetchRecord();
        }
        return true;
    }

    private boolean validateQuery(String query){
        Pattern pattern = Pattern.compile("(MAKE|PUSH|PULL)\\s([A-Za-z_]+)\\s(\\[.*\\])");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find())
        {
            return executeQuery(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return false;
    }

    public static void main(String[] args) {
        while(true) {
            System.out.println("\n\nEnter Your Query:");
            Scanner scanner = new Scanner(System.in);
            String query = scanner.nextLine();
            DatabaseMain databaseMain = new DatabaseMain();
            if (databaseMain.validateQuery(query)) {
                System.out.println("Successfully Completed!");
            } else {
                System.out.println("Syntax Error!");
            }
        }
    }
}
