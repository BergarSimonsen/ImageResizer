package com.simonsen.imageresizer.controller;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import com.simonsen.imageresizer.model.*;

public class ImageController {
    private final int scalePct;

    public ImageController( int scale ) {
	this.scalePct = scale;
    }

    public CustomImage[] resizeImages( CustomImage[] b ) throws IOException {
	CustomImage[] retval = new CustomImage[ b.length ];
	for( int i = 0; i < b.length; i++ ) {
	    retval[ i ] = getScaledImage( b[ i ] );
	}
	return retval;
    }

    public CustomImage[] readImages( File[] f ) throws IOException {
	CustomImage[] retval = new CustomImage[ f.length ];
	for( int i = 0; i < f.length; i++ ) {
	    retval[ i ] = createCustomImage( f[ i ] );
	}
	return retval;
    }

    public boolean isImage(String ex ) {
	if( ex.equalsIgnoreCase( "jpg" ) ||
	    ex.equalsIgnoreCase( "png" ) ) {
	    return true;
	} else {
	    return false;
	}
    }

    public CustomImage getScaledImage(CustomImage img) throws IOException {
	BufferedImage image = img.getImage();
	int imageWidth  = image.getWidth();
	int imageHeight = image.getHeight();
	int width = ( imageWidth / 100 ) * scalePct;
	int height = ( imageHeight / 100 ) * scalePct;

	double scaleX = (double)width/imageWidth;
	double scaleY = (double)height/imageHeight;
	AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
	AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

	return new CustomImage( bilinearScaleOp.filter( image, new BufferedImage(width, height, image.getType())), img.getFullName(), img.getName(), img.getExtension() );
    }

    public CustomImage createCustomImage( File f ) throws IOException {
	BufferedImage img = ImageIO.read( f );
	String fullName = f.getName();
	String filename = getFilename( fullName );
	String extension = getFileExtension( fullName );
	return new CustomImage( img, fullName, filename, extension );
    }

    public String getFileExtension( String filename ) {
	String retval = "";
	int i = filename.lastIndexOf( '.' );
	if( i > 0 ) retval = filename.substring( i + 1 );
	return retval;
    }

    public String getFilename( String fullName ) {
	String retval = "";
	int i = fullName.lastIndexOf( '.' );
	if( i > 0 ) retval = fullName.substring( 0, i - 1 );
	return retval;
    }
}
