package com.mostafa.fci.retrofitlibrary;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "products", strict = false)
public class Products {
    @ElementList(inline = true)
    public List<XMLFlower> flowers;

}
