package com.example.evolverss.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class Channel {


    @Element(name = "title")
    private String title;
    @ElementList(inline = true, name = "item")
    private List<Item> items;

    public Channel() {
    }

    public Channel(String title, List<Item> items) {
        this.title = title;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
