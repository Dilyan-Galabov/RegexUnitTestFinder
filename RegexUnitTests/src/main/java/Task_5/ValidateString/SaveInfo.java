/*
 * SaveInfo.java
 *
 * created at 2018-07-25 by d.galabov <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package Task_5.ValidateString;

public class SaveInfo
{

    private int tests;
    private int failTests;
    private int errors;
    private int skippedTests;
    private double timeForExecution;
    private String nameOfClass;

     public int getTests()
    {
        return tests;
    }

    public int getFailTests()
    {
        return failTests;
    }

    public int getErrors()
    {
        return errors;
    }

    public int getSkippedTests()
    {
        return skippedTests;
    }

    public double getTimeForExecution()
    {
        return timeForExecution;
    }

    public String getNameOfClass()
    {
        return nameOfClass;
    }

    public SaveInfo(int tests, int failTests,int errors, int skippedTests, double timeForExecution, String nameOfClass)
    {
        this.tests=tests;
        this.failTests=failTests;
        this.errors=errors;
        this.skippedTests=skippedTests;
        this.timeForExecution = timeForExecution;
        this.nameOfClass = nameOfClass;
    }

    @Override
    public String toString()
    {
        return "Tests run: " + tests + ", Failures: " + failTests + ", Errors: " + errors + ", Skipped: " + skippedTests + ", Time elapsed: " + timeForExecution + ", Name of class: " + nameOfClass + "\n";
    }


}



