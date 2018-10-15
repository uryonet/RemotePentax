package com.uryonet.remotepentax.service.model;

import java.util.List;

public class Dirs {
    private String name;
    private List<String> files;

    public Dirs(String name, List<String> files) {
        this.name = name;
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public List<String> getFiles() {
        return files;
    }
}
