# RegexUnitTestFinder

Overview:

Regex Unit Test Finder it is used for searching unit tests information from given file name path with regular expression - number of tests, failure tests, errors, number of skipped tests, time elapsed and
full name of the class. The information can be sorted by direction(ascending or descending) and order by every of tests(number,failures,errors etc.).
And it will be saved in other file input from the user. Every line that doesn't match the regular expression it will be passed in log file using
log4j and for every start of the jar file(application) it will be creating a new log file.

Guide:
First of all we need to import log4j in our project.

1.Export Project -> Runnable Jar -> Finish
2. Open command prompt and start jar file -java -jar "jarName" [input_file] [order_by] [sort_direction] [output_file]. 
Or you can use java -jar "jarName" -help(for help information).

- input_file - file path to read information from.
- order_by - tests, failures, errors, class_name, time(default)
- sort_direction - ascending(default), descending
- output_file - file where information will be saved.




