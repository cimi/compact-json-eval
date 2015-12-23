
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
* [BSON](http://bsonspec.org/) - binary JSON encoding used by MongoDB
* [MessagePack](http://msgpack.org/index.html) - variable length binary encoding with no symbol table


All encoders are being used through Jackson `ObjectMapper`s _without type awareness_ (i.e. the original JSON is being parsed 
into a `Map<String, Object>`) which may impact the performance of the encoder.

A type aware evaluation will be provided.

## Compressors

* identity - leaves the content as is
* [gzip](https://en.wikipedia.org/wiki/Gzip)
* [DEFLATE](https://en.wikipedia.org/wiki/DEFLATE)

## Sample output:

```
test.json -> 25 MB
test.json.gz -> 1.4 MB
test.json.zz -> 1.4 MB
test.json.smile -> 6.1 MB
test.json.smile.gz -> 961.9 kB
test.json.smile.zz -> 961.9 kB
test.json.bson -> 27 MB
test.json.bson.gz -> 2.5 MB
test.json.bson.zz -> 2.5 MB
test.json.msgpack -> 19.9 MB
test.json.msgpack.gz -> 1.5 MB
test.json.msgpack.zz -> 1.5 MB
------
```
