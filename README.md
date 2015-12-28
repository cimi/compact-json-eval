
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

```
{
  "test-large.json": {
    "": {
      "zip": "1.4 MB",
      "zz": "1.4 MB",
      "": "25 MB",
      "gz": "1.4 MB"
    },
    "bson": {
      "zip": "2.5 MB",
      "zz": "2.5 MB",
      "": "27 MB",
      "gz": "2.5 MB"
    },
    "msgpack": {
      "zip": "1.5 MB",
      "zz": "1.5 MB",
      "": "19.9 MB",
      "gz": "1.5 MB"
    },
    "smile": {
      "zip": "969.8 kB",
      "zz": "961.9 kB",
      "": "6.1 MB",
      "gz": "961.9 kB"
    }
  },
  "test-medium.json": {
    "": {
      "zip": "39 kB",
      "zz": "28.7 kB",
      "": "2 MB",
      "gz": "28.7 kB"
    },
    "bson": {
      "zip": "109.4 kB",
      "zz": "99.9 kB",
      "": "2.1 MB",
      "gz": "99.9 kB"
    },
    "msgpack": {
      "zip": "37.6 kB",
      "zz": "26.9 kB",
      "": "1.6 MB",
      "gz": "26.9 kB"
    },
    "smile": {
      "zip": "30 kB",
      "zz": "27.3 kB",
      "": "925.3 kB",
      "gz": "27.3 kB"
    }
  }
}
```
