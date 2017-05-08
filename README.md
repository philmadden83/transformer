# Translator
<p>
    Translator is a library to take the data from one object and construct a new object.
</p>

### Motivation
<p>
    RESTful APIs
</p>

### Usage

```java
    Transformer transformer = new Transformer(new ClasspathScanningContext(TestConfiguration.class));
    transformer.transform(objectA).into(objectB);
```