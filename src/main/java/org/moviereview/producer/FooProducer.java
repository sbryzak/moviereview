package org.moviereview.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import org.moviereview.action.Foo;

public class FooProducer
{
   public @Produces Foo getFoo() { return new Foo("bar"); }
}
