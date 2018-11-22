/*
 * ValidateStringAPI.java
 *
 * created at 2018-07-17 by d.galabov <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package Task_5.ValidateString;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ValidateStringMain
{

    public static void main(String[] args)
    {

        Scanner scan = null;
        String inputFile = "";
        String orderBy = "";
        String sortDirection = "";
        String outputFile = "";
        List<String> orderByList = new ArrayList<String>(Arrays.asList("tests", "failures", "errors", "class_name", "time"));
        List<String> sortDirectionList = new ArrayList<String>(Arrays.asList("asc", "desc"));

        try
        {
            if (args.length > 0 && args.length < 5)
            {

                if (args.length == 4)
                {
                    inputFile = args[0];
                    orderBy = args[1];
                    sortDirection = args[2];
                    outputFile = args[3];
                    if (!sortDirectionList.contains(sortDirection))
                    {
                        System.out.println("Wrong sort direction! It can be: " + sortDirectionList.toString());
                        System.out.println("Type help for more info");
                    }
                    else if (!orderByList.contains(orderBy))
                    {
                        System.out.println("Wrong order by. You must choose from the following list: " + orderByList.toString());
                        System.out.println("Type help for more info");
                    }
                    else
                    {
                        SaveInfoManager.saveInFile(inputFile, sortDirection, orderBy, outputFile);
                    }

                }
                else if(args.length == 3)
                {
                    inputFile = args[0];
                    orderBy = args[1];
                    outputFile = args[2];
                    if (!orderByList.contains(orderBy))
                    {
                        System.out.println("Wrong order by. You must choose from the following list: " + orderByList.toString());
                        System.out.println("Type help for more info");
                    }
                    else if(orderByList.contains(orderBy))
                    {
                        SaveInfoManager.saveInFile(inputFile, "asc", orderBy, outputFile);
                    }
                    else if(sortDirectionList.contains(orderBy))
                    {
                         SaveInfoManager.saveInFile(inputFile, orderBy, "time", outputFile);
                    }
                    else if(!sortDirection.contains(orderBy))
                    {
                        System.out.println("Wrong sort direction! It can be: " + sortDirectionList.toString());
                        System.out.println("Type help for more info");
                    }
                }
                else if(args.length == 2)
                {
                    inputFile = args[0];
                    outputFile = args[1];
                    SaveInfoManager.saveInFile(inputFile, "asc", "time", outputFile);
                }
                else if(args.length == 1)
                {
                    if(args[0].equals("help"))
                    {
                        System.out.println("Help Info: input_file, order_by, sort_direction, output_file");
                        System.out.println("input_file - file to read information from!");
                        System.out.println("order_by - tests, failures, errors, class_name, time(default)");
                        System.out.println("sort_direction - asc(default), desc");
                        System.out.println("output_file - file where information will be saved");
                    }
                    else
                    {
                        System.out.println("Wrong parameter.");
                    }
                }
                else
                {
                    System.out.println("Missed parameteres");
                    System.out.println("Type help for more info");
                }
            }

            else
            {
                System.out.println("No arguments");
                System.out.println("Type help for more info!");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            SaveInfoManager.close(scan);
        }

    }

}
