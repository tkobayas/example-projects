package org.example;

import java.io.File;
import java.io.IOException;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Attributes.attr;

import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;

/**
 * Hello world!
 *
 */
public class App01 {

    public static void main(String[] args) throws IOException {
        Graph g = graph("example1").directed()
                                   .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                                   .nodeAttr().with(Font.name("arial"))
                                   .linkAttr().with("class", "link-class")
                                   .with(
                                         node("a").with(Color.RED).link(node("b")),
                                         node("b").link(
                                                        to(node("c")).with(attr("weight", 5), Style.DASHED)));
        Graphviz.fromGraph(g).height(100).render(Format.PNG).toFile(new File("example/ex1.png"));
    }
}
