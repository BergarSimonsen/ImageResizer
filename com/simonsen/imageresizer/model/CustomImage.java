package com.simonsen.imageresizer.model;
import java.io.*;
import java.awt.image.BufferedImage;

public class CustomImage {
    private String fullName;
    private String name;
    private String extension;
    private BufferedImage image;
    private int height;
    private int width;

    public CustomImage( BufferedImage img, String fullName, String name, String extension ) {
	this.image = img;
	this.fullName = fullName;
	this.name = name;
	this.extension = extension;
	this.height = image.getHeight();
	this.width = image.getWidth();
    }

    public String getFullName() {
	return fullName;
    }

    public String getName() {
	return name;
    }

    public String getExtension() {
	return extension;
    }

    public BufferedImage getImage() {
	return image;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }
}
