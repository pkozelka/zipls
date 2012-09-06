# zipls

Simple tool for deep-listing zip files containing other zip files - useful for fast content comparison.

Inner zip files are displayed with "!" separator.

## Usage

    java -jar zipls <zipfile>

_There are currently no options supported_

## Purpose

- create listing for easy content searching
- in combination with sort, compare content of two versions of big EARs or other complex zip-based archives

## Sample output

    somedir/
    inner.zip
    inner.zip!inner/
    inner.zip!inner/innerfile2.txt
    inner.zip!inner/innerfile1.txt

## Build

There are no dependencies, so you can just use

    mvn clean package

The build is so trivial, that I provide a GNU Makefile so you can even avoid installing maven:

    make clean package

In either case, the resulting executable jar file is target/zipls.jar
