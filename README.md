# eztemplate

>I have a document that I manually fill in on a regular basis - print it out, fill in the necessary blanks, and then finally give it to the intended person. This project facilitates my use-case for generating a PDF document based on a template and a set dictionary.

## Overview
This project comprises of 3 modules that provide a way for the user to generate a PDF document from a template and a dictionary. The modules:

1. eztemplate-core: API level library for template generation.
2. eztemplate-pdf: A PDF implementation for template generation using [iText](http://itextsupport.com/apidocs/itext7/latest/).
3. ezpdf: A simple UI that allows to user to specify a template and a dictionary to generate a translated PDF document.

## ezpdf

![alt text](ezpdf/ezpdf_screenshot.png "Ez Pdf")

Ez Pdf is a simple standalone, thick-client application that runs on the user's local machine. Application data is saved in `$HOME/.ezpdf` which only includes a log file. Prebuilt application artifacts on Dropbox can be found below and support Mac, Windows and Linux.

1. Mac [ezpdf-0.2.dmg](https://www.dropbox.com/s/ku7yf916omhytmt/ezpdf-0.2.dmg?dl=0)
2. Linux/Windows [ezpdf-0.2.tar](https://www.dropbox.com/s/vdausj52rrvuisl/ezpdf-0.2.tar?dl=0)
3. Linux/Windows [ezpdf-0.2.zip](https://www.dropbox.com/s/5cjl3la3pjdfnf4/ezpdf-0.2.zip?dl=0)

### Common Use Case

1. Create a text file template document with placeholders - i.e. `${tenantName}`
2. Create a properties dictionary file that maps placeholders to their value - i.e. `tenantName=Peter Parker`
3. Use `ezpdf` and pass in template and dictionary to generate the finalized document

Note: In the template, you can specify `<%newpage%>` where you want a break break to occur.

Sample input files are included with this project.

1. lease.txt - Example template text file.
2. lease_empty.properites - Dictionary that fills in the lease above with empty blank lines - essentially the form for you to manually write in the values.
3. lease.properties - Dictionary that fills in the lease above with sample values.

## Building
Gradle builds the application and uses the following plugins to build distributable artifacts.

1. [Gradle](https://docs.gradle.org/current/userguide/distribution_plugin.html) `distribution`
2. [Spring](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-gradle-plugin.html) `spring-boot`
3. [crotwell](https://github.com/crotwell) [MacApp Bundle](https://github.com/crotwell/gradle-macappbundle)
4. Image icons for `ezpdf` are provided by [xinhstudio](https://www.iconfinder.com/xinhstudio).

```
gradle clean build
```

Navigate to `eztemplate/ezpdf/build/distributions` where there will be the following files for running **ezpdf** in Mac/Unix/Windows environments:

1. `dmg` file to install and run in Mac OSX
2. `tar` file packaged with the Unix and Windows runscripts along with all necessary dependencies
3. `zip` file which contains the same contents as the `tar` file