/*
 * SaveInformation.java
 *
 * created at 2018-07-30 by d.galabov <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */

package Task_5.ValidateString;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class SaveInfoManager
{
    static
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }

    private static Comparator<SaveInfo> sortAscTests = (s1, s2) -> Integer.compare(s1.getTests(), s2.getTests());
    private static Comparator<SaveInfo> sortAscFailures = (s1, s2) -> Integer.compare(s1.getFailTests(), s2.getFailTests());
    private static Comparator<SaveInfo> sortAscErrors = (s1, s2) -> Integer.compare(s1.getErrors(), s2.getErrors());
    private static Comparator<SaveInfo> sortAscSkipped = (s1, s2) -> Integer.compare(s1.getSkippedTests(), s2.getSkippedTests());
    private static Comparator<SaveInfo> sortAscTimeElapsed = (s1, s2) -> Double.compare(s1.getTimeForExecution(), s2.getTimeForExecution());
    private static Comparator<SaveInfo> sortAscByClass = (s1, s2) -> s1.getNameOfClass().compareTo(s2.getNameOfClass());
    private static List<SaveInfo> listInfo = new ArrayList<>();
    private static Scanner scan = null;
    private static final Logger logger = Logger.getLogger(SaveInfoManager.class);


    public static void addToObject(SaveInfo info)
    {
        listInfo.add(info);
    }


    /**
     * Check if the filepath is correct.
     * @param fileName - file name
     * @return null if filepath doesn't found; otherwise filePath
     */
    private static File readPath(String fileName)
    {
        scan = new Scanner(System.in);
        if (fileName != null)
        {
            File filePath = new File(fileName);
            while (!filePath.isFile())
            {
                System.out.printf("Wrong file name \"%s\"! Enter another: ", fileName);
                fileName = scan.nextLine();
                filePath = new File(fileName);
            }
            return filePath;
        }
        return null;
    }


    /**
     *  Sorting information
     * @param sortDirection - sort direction
     * @param orderBy - order by
     */
    private static void SortInfo(String sortDirection, String orderBy)
    {

        if (orderBy != null && sortDirection != null)
        {
            if (sortDirection.equalsIgnoreCase("asc"))
            {
                sortAscending(orderBy);
            }
            else if (sortDirection.equalsIgnoreCase("desc"))
            {
                sortDescending(orderBy);
            }

        }

    }


    private static void sortDescending(String orderBy)
    {
        switch (orderBy)
        {
            case "tests":
                listInfo.sort(sortAscTests.reversed());
                break;
            case "failures":
                listInfo.sort(sortAscFailures.reversed());
                break;
            case "errors":
                listInfo.sort(sortAscErrors.reversed());
                break;
            case "skipped":
                listInfo.sort(sortAscSkipped.reversed());
                break;
            case "class_name":
                listInfo.sort(sortAscByClass.reversed());
                break;
            case "time":
                listInfo.sort(sortAscTimeElapsed);
                break;
            default:
                System.out.println("Wrong sort direction!");
                break;
        }
    }


    private static void sortAscending(String orderBy)
    {
        switch (orderBy)
        {
            case "tests":
                listInfo.sort(sortAscTests);
                break;
            case "failures":
                listInfo.sort(sortAscFailures);
                break;
            case "errors":
                listInfo.sort(sortAscErrors);
                break;
            case "skipped":
                listInfo.sort(sortAscSkipped);
                break;
            case "class_name":
                listInfo.sort(sortAscByClass);
                break;
            case "time":
                listInfo.sort(sortAscTimeElapsed);
                break;
            default:
                System.out.println("Wrong sort direction!");
                break;
        }
    }


    /**
     * Save data from file by sort direction and orderby to output file
     * @param inputFile - input file
     * @param sortDirection - sort direction
     * @param orderBy - order by
     * @param outputFile - output file
     * @throws IOException - if I/O exception occurs
     */
    public static void saveInFile(String inputFile, String sortDirection, String orderBy, String outputFile) throws IOException
    {

        String regex = "(?:Tests run[:]\\s([\\d]+)[,])(?:\\sFailures[:]\\s([\\d]+)[,])(?:\\sErrors[:]\\s([\\d]+)[,])(?:\\sSkipped[:]\\s([\\d]+)[,])(?:\\sTime elapsed[:]\\s([\\d.]+)[\\ssec|min|h]+)(?:[a-z-.\\s]+)([a-zA-Z.]+)";
        Pattern pattern = Pattern.compile(regex);
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try
        {
            String extension = "";
            String path = "";
            File filePath = readPath(inputFile);
            File filePathTwo = readPath(outputFile);

            if (filePath != null && filePathTwo != null)
            {
                int getExtension = filePathTwo.getAbsolutePath().lastIndexOf(".");
                if (getExtension > 0)
                {
                    extension = filePathTwo.getAbsolutePath().substring(getExtension);
                    path = filePathTwo.getAbsolutePath().substring(0, getExtension);
                }

                int counter = 1;
                while (filePathTwo.exists())
                {
                    filePathTwo = new File(path + counter + extension);
                    counter++;
                }

                fis = new FileInputStream(filePath);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);

                fos = new FileOutputStream(filePathTwo);
                osw = new OutputStreamWriter(fos);
                bw = new BufferedWriter(osw);

                String line;
                while ((line = br.readLine()) != null)
                {
                    Matcher m = pattern.matcher(line);
                    if (m.find())
                    {
                        SaveInfo si = new SaveInfo(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)), Double.parseDouble(m.group(5)), m.group(6));
                        listInfo.add(si);
                    }
                    else
                    {
                        logger.info(line);
                    }
                }

                SortInfo(sortDirection, orderBy);

                StringBuilder sb = new StringBuilder();
                for (SaveInfo sv : listInfo)
                {
                    sb.append(sv.toString());
                    sb.append(System.lineSeparator());
                }
                bw.write(sb.toString());
                System.out.println("Successfully saved!");

            }

        }

        finally
        {
            close(bw);
            close(osw);
            close(fos);
            close(br);
            close(isr);
            close(fis);
        }

    }


    public static void close(Closeable out)
    {

        if (out != null)
        {
            try
            {
                out.close();
            }
            catch (Exception e)
            {
                // NOOP
            }
        }

    }

}
