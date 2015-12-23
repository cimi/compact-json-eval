
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
* [zip](https://en.wikipedia.org/wiki/Zip)

## Sample output:

| File                                               | Size       |
| -------------------------------------------------- | ---------- |
| test-large.json                                    | 25 MB      |
| test-large.json.gz                                 | 1.4 MB     |
| test-large.json.zz                                 | 1.4 MB     |
| test-large.json.zip                                | 1.4 MB     |
| test-large.json.smile                              | 6.1 MB     |
| test-large.json.smile.gz                           | 961.9 kB   |
| test-large.json.smile.zz                           | 961.9 kB   |
| test-large.json.smile.zip                          | 969.8 kB   |
| test-large.json.bson                               | 27 MB      |
| test-large.json.bson.gz                            | 2.5 MB     |
| test-large.json.bson.zz                            | 2.5 MB     |
| test-large.json.bson.zip                           | 2.5 MB     |
| test-large.json.msgpack                            | 19.9 MB    |
| test-large.json.msgpack.gz                         | 1.5 MB     |
| test-large.json.msgpack.zz                         | 1.5 MB     |
| test-large.json.msgpack.zip                        | 1.5 MB     |

| File                                               | Size       |
| -------------------------------------------------- | ---------- |
| test-medium.json                                   | 2 MB       |
| test-medium.json.gz                                | 28.7 kB    |
| test-medium.json.zz                                | 28.7 kB    |
| test-medium.json.zip                               | 39 kB      |
| test-medium.json.smile                             | 925.3 kB   |
| test-medium.json.smile.gz                          | 27.3 kB    |
| test-medium.json.smile.zz                          | 27.3 kB    |
| test-medium.json.smile.zip                         | 30 kB      |
| test-medium.json.bson                              | 2.1 MB     |
| test-medium.json.bson.gz                           | 99.9 kB    |
| test-medium.json.bson.zz                           | 99.9 kB    |
| test-medium.json.bson.zip                          | 109.4 kB   |
| test-medium.json.msgpack                           | 1.6 MB     |
| test-medium.json.msgpack.gz                        | 26.9 kB    |
| test-medium.json.msgpack.zz                        | 26.9 kB    |
| test-medium.json.msgpack.zip                       | 37.6 kB    |
