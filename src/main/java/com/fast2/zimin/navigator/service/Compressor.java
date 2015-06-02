package com.fast2.zimin.navigator.service;

import sun.misc.BASE64Encoder;

import javax.xml.datatype.DatatypeFactory;
import java.io.*;
import java.util.Date;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * Created by ez2sarang on 15. 5. 14..
 */
public class Compressor {
    public static final byte[] LICENSE_PREFIX = { 13, 14, 12, 10, 15 };
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            String str = "H:\\Users\\Document\\Downloads\\asset\\postImage\\23\\34.jpg";
            // String str = "Compress byte array using Deflater example";
            // get bytes

            byte[] bytes = str.getBytes();

			/*
			 * To create an object of Deflater, use Deflater() Constructor of
			 * Deflater class. This will create a new compressor with default
			 * compression level.
			 */
            Deflater deflater = new Deflater();

			/*
			 * Set the input of compressor using, setInput(byte[] b) method of
			 * Deflater class.
			 */
            deflater.setInput(bytes);

			/*
			 * We are done with the input, so say finish using void finish()
			 * method of Deflater class. It ends the compression with the
			 * current contents of the input.
			 */
            deflater.finish();

			/*
			 * At this point, we are done with the input. Now we will have to
			 * create another byte array which can hold the compressed bytes.
			 */
            ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length);
            bos.write(LICENSE_PREFIX);

            byte[] buffer = new byte[1024];

			/*
			 * Use boolean finished() method of Deflater class to determine
			 * whether end of compressed data output stream reached.
			 */
            while(!deflater.finished())

            {
				/*
				 * use int deflate(byte[] buffer) method to fill the buffer with
				 * the compressed data. This method returns actual number of
				 * bytes compressed.
				 */
                int bytesCompressed = deflater.deflate(buffer);
                bos.write(buffer, 0, bytesCompressed);
            }
            try {
                // close the output stream
                bos.close();
            }
            catch(IOException ioe) {
                System.out.println("Error while closing the stream : " + ioe);
            }
            // get the compressed byte array from output stream
            byte[] compressedArray = bos.toByteArray();
            System.out.println("Byte array has been compressed!");
            System.out.println("Size of original array is:" + bytes.length);
            System.out.println("Size of compressed array is:" + compressedArray.length);
            try {
                System.out.println(String.format("Size of compressed text is:[%s]", new BASE64Encoder().encode(compressedArray)));
            } catch (Exception e) {
                e.printStackTrace();
            }

            ByteArrayInputStream in = new ByteArrayInputStream(compressedArray);
            in.skip(LICENSE_PREFIX.length);
            InflaterInputStream inflaterInputStream = new InflaterInputStream(in, new Inflater());
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inflaterInputStream, "UTF-8"));
                String thisLine = bufferedReader.readLine();
                System.out.println(thisLine);
            }
            catch(UnsupportedEncodingException e) {
                throw new Exception(e);
            }

            System.out.println(new Date(System.currentTimeMillis()+DatatypeFactory.newInstance().newDuration("P2D").getTimeInMillis(new Date())));


//            System.out.println(DatatypeFactory.newInstance().newDuration("P2D").addTo(new Date()));
        }
        catch(Exception e) {
            // throw new LicenseException(e);
        }

    }
    /**
     * Extract compressed EMF data from a ppt
     */
    /*public byte[] getData(){
        try {
            byte[] rawdata = getRawData();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream is = new ByteArrayInputStream( rawdata );
            Header header = new Header();
            header.read(rawdata, CHECKSUM_SIZE);
            is.skip(header.getSize() + CHECKSUM_SIZE);

            InflaterInputStream inflater = new InflaterInputStream( is );
            byte[] chunk = new byte[4096];
            int count;
            while ((count = inflater.read(chunk)) >=0 ) {
                out.write(chunk,0,count);
            }
            inflater.close();
            return out.toByteArray();
        } catch (IOException e){
            throw e;
        }
    }

    public void setData(byte[] data) throws IOException {
        byte[] compressed = compress(data, 0, data.length);

        Header header = new Header();
        header.wmfsize = data.length;
        //we don't have a EMF reader in java, have to set default image size  200x200
        header.bounds = new java.awt.Rectangle(0, 0, 200, 200);
        header.size = new java.awt.Dimension(header.bounds.width*Shape.EMU_PER_POINT, header.bounds.height*Shape.EMU_PER_POINT);
        header.zipsize = compressed.length;

        byte[] checksum = getChecksum(data);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(checksum);
        header.write(out);
        out.write(compressed);

        setRawData(out.toByteArray());
    }

    public int getType(){
        return Picture.EMF;
    }*/

    /**
     * EMF signature is <code>0x3D40</code>
     *
     * @return EMF signature (<code>0x3D40</code>)
     */
    public int getSignature(){
        return 0x3D40;
    }
}
