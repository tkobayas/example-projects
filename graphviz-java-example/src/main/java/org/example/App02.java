package org.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

/**
 * Hello world!
 *
 */
public class App02 {

    public static void main(String[] args) throws IOException {
        try (InputStream dot = App02.class.getResourceAsStream("/example01.dot")) {
            MutableGraph g = new Parser().read(dot);
            Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File("example/ex4-1.png"));
        }
    }
}
