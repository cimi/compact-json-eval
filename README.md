
## Usage

```
mvn package
java -jar target/json-compression-test-1.0-SNAPSHOT-jar-with-dependencies.jar <target directory>
```

All the json files in the target directory are processed with all the available encoders and compressors, in all combinations.

The path to the directory can be either absolute or relative/

## Encoders

* identity - leaves the content as is
* [Smile](http://wiki.fasterxml.com/SmileFormat) - variable length binary encoding with symbol table

## Compressors

* identity - leaves the content as is
* gzip

## Sample output:

```
test.json -> 25 MB
test.json.gz -> 1.4 MB
test.smile -> 6.1 MB
test.smile.gz -> 961.9 kB
------
```
