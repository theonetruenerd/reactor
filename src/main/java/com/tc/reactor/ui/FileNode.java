package com.tc.reactor.ui;

public class FileNode {
    public final String fileName;
    public final String filePath;

    public FileNode(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return fileName; // Display only the file name in the TreeView
    }
}