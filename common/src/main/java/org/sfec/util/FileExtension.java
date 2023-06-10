package org.sfec.util;

public enum FileExtension {

    JPEG(".jpeg"),

    PNG(".png");

    private String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
