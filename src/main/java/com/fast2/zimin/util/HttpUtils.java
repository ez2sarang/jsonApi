package com.fast2.zimin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtils {
    public interface ContentProxy {
        public String getRelativePath();
        public String getName();
        public boolean isText();
        public String getETag();
        public long getContentLength();
        public long getLastModified();
        public String getLastModifiedHttp();
        public boolean isMimeTypeKnown();
        public String getMimeType();
        public void setMimeType(String mimeType);
        public void push(OutputStream stream) throws IOException;
        public void push(OutputStream stream, long start, long end) throws IOException;
        public void push(Writer writer) throws IOException;
    }

    public static class ResponseContext {
        HttpServletRequest request;
        HttpServletResponse response;
        
        public ResponseContext(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
        }
        
    }
    
    public static abstract class AbstractContentProxy implements ContentProxy {
        protected final String relativePath;
        protected ServletContext servletContext;
        protected String mimeType;
        
        protected AbstractContentProxy(String relativePath) {
            this.relativePath = relativePath;
        }
        
        protected abstract InputStream getInputStream() throws IOException;
        
        protected abstract Reader getReader() throws IOException;

        @Override
        public String getRelativePath() {
            return relativePath;
        }

        @Override
        public boolean isText() {
            String contentType = getMimeType();
            return (contentType == null)
            || (contentType.startsWith("text"))
            || (contentType.endsWith("xml"))
            || (contentType.contains("/javascript"));
        }

        @Override
        public String getName() {
            if (relativePath == null) return null;
            int i = relativePath.lastIndexOf('/');            
            return i < 0 ? relativePath : relativePath.substring(i+1);
        }

        @Override
        public boolean isMimeTypeKnown() {
            return mimeType != null;
        }
        
        @Override
        public String getMimeType() {
            if (mimeType == null && servletContext != null) {
                mimeType = servletContext.getMimeType(getName());
            }
            return mimeType;
        }

        @Override
        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        @Override
        public String getLastModifiedHttp() {
            long lastMod = getLastModified();
            if (lastMod <= 0) return null;
                                                      // Tue, 15 Nov 1994 12:45:26 GMT
            SimpleDateFormat fmt = new SimpleDateFormat("EEE, d MMM yyyy H:mm:ss zzz");
            fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
            return fmt.format(new Date(lastMod));
        }

        @Override
        public void push(OutputStream stream) throws IOException {
            HttpUtils.copy(getInputStream(), stream);
        }

        @Override
        public void push(OutputStream stream, long start, long end) throws IOException {
            HttpUtils.copy(getInputStream(), stream, start, end);
        }

        @Override
        public void push(Writer writer) throws IOException {
            HttpUtils.copy(getReader(), writer);
        }

        public void setServletContext(ServletContext servletContext) {
            this.servletContext = servletContext;
        }
    }

    public static class FileContentProxy extends AbstractContentProxy {
        private File file;
        
        protected FileContentProxy(String relativePath) {
            super(relativePath);
        }
        
        public FileContentProxy(String relativePath, File file) {
            super(relativePath);
            this.file = file;
        }
        
        protected File getFile() {
            return file;
        }
        
        protected void setFile(File file) {
            this.file = file;
        }

        @Override
        public String getETag() {
            return null;
        }

        @Override
        public long getContentLength() {
            File file = getFile();
            return file != null && file.exists() ? file.length() : -1;
        }

        @Override
        public long getLastModified() {
            File file = getFile();
            return file != null ? file.lastModified() : 0;
        }

        @Override
        protected InputStream getInputStream() throws IOException {
            File file = getFile();
            if (file == null) throw new FileNotFoundException("No backing file for: " + getRelativePath());
            return new FileInputStream(getFile());
        }

        @Override
        protected Reader getReader() throws IOException {
            File file = getFile();
            if (file == null) throw new FileNotFoundException("No backing file for: " + getRelativePath());
            return new FileReader(getFile());
        }
    }    
    
    protected static class HttpErrorException extends Exception {
        private final int status;
        public HttpErrorException(int status) {
            this.status = status;
        }
        
        public int getStatus() {
            return status;
        }
    }
    
    protected static class Range {

        public long start;
        public long end;
        public long length;

        /**
         * Validate range.
         */
        public boolean validate() {
            if (end >= length)
                end = length - 1;
            return (start >= 0) && (end >= 0) && (start <= end) && (length > 0);
        }
    }
    protected static final ArrayList<Range> FULL = new ArrayList<Range>();
    protected static final String mimeSeparation = "OCTO_MIME_BOUNDARY";

    /**
     * Check if the if-match condition is satisfied.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param resourceAttributes File object
     * @return boolean true if the resource meets the specified condition,
     * and false if the condition is not satisfied, in which case request
     * processing is stopped
     */
    protected static boolean checkIfMatch(HttpServletRequest request,
                                 HttpServletResponse response,
                                 ContentProxy content)
        throws HttpErrorException {

        String eTag = content.getETag();
        String headerValue = request.getHeader("If-Match");
        if (headerValue != null) {
            if (headerValue.indexOf('*') == -1) {

                StringTokenizer commaTokenizer = new StringTokenizer
                    (headerValue, ",");
                boolean conditionSatisfied = false;

                while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
                    String currentToken = commaTokenizer.nextToken();
                    if (currentToken.trim().equals(eTag))
                        conditionSatisfied = true;
                }

                // If none of the given ETags match, 412 Precodition failed is
                // sent back
                if (!conditionSatisfied) {
                    throw new HttpErrorException(HttpServletResponse.SC_PRECONDITION_FAILED);
                }

            }
        }
        return true;

    }


    /**
     * Check if the if-modified-since condition is satisfied.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param resourceAttributes File object
     * @return boolean true if the resource meets the specified condition,
     * and false if the condition is not satisfied, in which case request
     * processing is stopped
     */
    protected static boolean checkIfModifiedSince(HttpServletRequest request,
            HttpServletResponse response, ContentProxy content) throws HttpErrorException {
        try {
            long headerValue = request.getDateHeader("If-Modified-Since");
            long lastModified = content.getLastModified();
            if (headerValue != -1) {

                // If an If-None-Match header has been specified, if modified since
                // is ignored.
                if ((request.getHeader("If-None-Match") == null)
                    && (lastModified < headerValue + 1000)) {
                    // The entity has not been modified since the date
                    // specified by the client. This is not an error case.
                    throw new HttpErrorException(HttpServletResponse.SC_NOT_MODIFIED);
                }
            }
        } catch (IllegalArgumentException illegalArgument) {
            return true;
        }
        return true;

    }


    /**
     * Check if the if-none-match condition is satisfied.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param resourceAttributes File object
     * @return boolean true if the resource meets the specified condition,
     * and false if the condition is not satisfied, in which case request
     * processing is stopped
     */
    protected static boolean checkIfNoneMatch(HttpServletRequest request,
                                     HttpServletResponse response,
                                     ContentProxy content)
        throws HttpErrorException {

        String eTag = content.getETag();
        String headerValue = request.getHeader("If-None-Match");
        if (headerValue != null) {

            boolean conditionSatisfied = false;

            if (!headerValue.equals("*")) {

                StringTokenizer commaTokenizer =
                    new StringTokenizer(headerValue, ",");

                while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
                    String currentToken = commaTokenizer.nextToken();
                    if (currentToken.trim().equals(eTag))
                        conditionSatisfied = true;
                }

            } else {
                conditionSatisfied = true;
            }

            if (conditionSatisfied) {

                // For GET and HEAD, we should respond with
                // 304 Not Modified.
                // For every other method, 412 Precondition Failed is sent
                // back.
                if ( ("GET".equals(request.getMethod()))
                     || ("HEAD".equals(request.getMethod())) ) {
                    throw new HttpErrorException(HttpServletResponse.SC_NOT_MODIFIED);
                }
                throw new HttpErrorException(HttpServletResponse.SC_PRECONDITION_FAILED);
            }
        }
        return true;

    }


    /**
     * Check if the if-unmodified-since condition is satisfied.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param resourceAttributes File object
     * @return boolean true if the resource meets the specified condition,
     * and false if the condition is not satisfied, in which case request
     * processing is stopped
     */
    protected static boolean checkIfUnmodifiedSince(HttpServletRequest request,
                                           HttpServletResponse response,
                                           ContentProxy content)
        throws HttpErrorException {
        try {
            long lastModified = content.getLastModified();
            long headerValue = request.getDateHeader("If-Unmodified-Since");
            if (headerValue != -1) {
                if ( lastModified >= (headerValue + 1000)) {
                    // The entity has not been modified since the date
                    // specified by the client. This is not an error case.
                    throw new HttpErrorException(HttpServletResponse.SC_PRECONDITION_FAILED);
                }
            }
        } catch(IllegalArgumentException illegalArgument) {
            return true;
        }
        return true;

    }
    /**
     * Check if the conditions specified in the optional If headers are
     * satisfied.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param content The resource information
     * @return boolean true if the resource meets all the specified conditions,
     * and false if any of the conditions is not satisfied, in which case
     * request processing is stopped
     */
    protected static boolean checkIfHeaders(HttpServletRequest request,
                                     HttpServletResponse response,
                                     ContentProxy content)
        throws HttpErrorException {

        return checkIfMatch(request, response, content)
            && checkIfModifiedSince(request, response, content)
            && checkIfNoneMatch(request, response, content)
            && checkIfUnmodifiedSince(request, response, content);

    }

    public static void serveContent(ResponseContext responseContext,
            ContentProxy content, boolean serveContent, boolean allowRetry) throws IOException {
        HttpServletResponse response = responseContext.response;
        try {
            sendContent(responseContext, content, serveContent);
        } catch (HttpErrorException ex) {
            if (ex.getStatus() == HttpServletResponse.SC_NOT_MODIFIED) {
                response.setHeader("ETag", content.getETag());
            }
            try {
                response.sendError(ex.getStatus());
            } catch (IOException e) {
                // we can no longer set the status, forget it
            }
        } catch (FileNotFoundException ex) {
            if (!allowRetry) {
                try {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, responseContext.request.getRequestURI());
                } catch (IOException e) {
                    // we can no longer set the status, forget it
                }
            } else {
                throw ex;
            }
        } catch (IOException ex) {
            // filter out client aborts
            if (ex.getClass().getName().indexOf("ClientAbortException") >= 0) {
                return;
            }
            if (ex instanceof SocketException && ex.getMessage().equals("Connection reset")) {
                return;
            }
            throw ex;            
        }
    }

    /**
     * Serve the specified resource, optionally including the data content.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param serveContent Should the content be included?
     *
     * @exception IOException if an input/output error occurs
     * @throws HttpErrorException 
     * @exception ServletException if a servlet-specified error occurs
     */
    protected static void sendContent(ResponseContext responseContext, 
            ContentProxy content, boolean serveContent)
        throws HttpErrorException, IOException {
        
        HttpServletRequest request = responseContext.request;
        HttpServletResponse response = responseContext.response;

        boolean useAcceptRanges = true;
        int outputBufferSize = 4096;
        
        // Identify the requested resource path
        //String path = content.name();

        // Checking If headers
        if (!checkIfHeaders(request, response, content)) {
           return;
        }
        // Find content type.
        String contentType = content.getMimeType();

        ArrayList<Range> ranges = null;
        long contentLength = -1L;

        if (useAcceptRanges) {
            // Accept ranges header
            response.setHeader("Accept-Ranges", "bytes");
        }
        // Parse range specifier
        ranges = parseRange(request, response, content);

        // ETag header
        response.setHeader("ETag", content.getETag());

        // Last-Modified header
        response.setHeader("Last-Modified",
                content.getLastModifiedHttp());

        // Get content length
        contentLength = content.getContentLength();
        // Special case for zero length files, which would cause a
        // (silent) ISE when setting the output buffer size
        if (contentLength == 0L) {
            serveContent = false;
        }

        ServletOutputStream ostream = null;
        PrintWriter writer = null;
        if (serveContent) {
            // Trying to retrieve the servlet output stream
            try {
                ostream = response.getOutputStream();
            } catch (IllegalStateException e) {
                // If it fails, we try to get a Writer instead if we're
                // trying to serve a text file
                if ( content.isText() ) {
                    writer = response.getWriter();
                    // Cannot reliably serve partial content with a Writer
                    ranges = FULL;
                } else {
                    throw e;
                }
            }

        }

        if ( ( ((ranges == null) || (ranges.isEmpty()))
                      && (request.getHeader("Range") == null) )
                || (ranges == FULL) ) {
            // Set the appropriate output headers
            if (contentType != null) {
                response.setContentType(contentType);
            }
            if ((contentLength >= 0) && (!serveContent || ostream != null)) {
                // Don't set a content length if something else has already
                // written to the response.
                if (contentLength < Integer.MAX_VALUE) {
                    response.setContentLength((int) contentLength);
                } else {
                    // Set the content-length as String to be able to use a
                    // long
                    response.setHeader("content-length",
                            "" + contentLength);
                }
            }

            // Copy the input stream to our output stream (if requested)
            if (serveContent) {
                try {
                    response.setBufferSize(outputBufferSize);
                } catch (IllegalStateException e) {
                    // Silent catch
                }
                if (ostream != null) {
                    content.push(ostream);
                } else {
                    content.push(writer);
                }
            }

        } else {
            if ((ranges == null) || (ranges.isEmpty()))
                return;
            // Partial content response.
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

            if (ranges.size() == 1) {
                Range range = ranges.get(0);
                response.addHeader("Content-Range", "bytes "
                                   + range.start
                                   + "-" + range.end + "/"
                                   + range.length);
                long length = range.end - range.start + 1;
                if (length < Integer.MAX_VALUE) {
                    response.setContentLength((int) length);
                } else {
                    // Set the content-length as String to be able to use a long
                    response.setHeader("content-length", "" + length);
                }

                if (contentType != null) {
                	response.setContentType(contentType);
                }

                if (serveContent) {
                    try {
                        response.setBufferSize(outputBufferSize);
                    } catch (IllegalStateException e) {
                        // Silent catch
                    }
                    if (ostream != null) {
                        content.push(ostream, range.start, range.end);
                    } else {
                        // we should not get here
                        throw new IllegalStateException();
                    }
                }

            } else {
                response.setContentType("multipart/byteranges; boundary=" + mimeSeparation);
                if (serveContent) {
                    try {
                        response.setBufferSize(outputBufferSize);
                    } catch (IllegalStateException e) {
                        // Silent catch
                    }
                    if (ostream != null) {
                        copy(content, ostream, ranges.iterator(), contentType);
                    } else {
                        // we should not get here
                        throw new IllegalStateException();
                    }
                }

            }

        }

    }


    /**
     * Parse the content-range header.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @return Range
     */
    protected static Range parseContentRange(HttpServletRequest request,
                                      HttpServletResponse response)
        throws IOException {

        // Retrieving the content-range header (if any is specified
        String rangeHeader = request.getHeader("Content-Range");

        if (rangeHeader == null)
            return null;

        // bytes is the only range unit supported
        if (!rangeHeader.startsWith("bytes")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        rangeHeader = rangeHeader.substring(6).trim();

        int dashPos = rangeHeader.indexOf('-');
        int slashPos = rangeHeader.indexOf('/');

        if (dashPos == -1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        if (slashPos == -1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        Range range = new Range();

        try {
            range.start = Long.parseLong(rangeHeader.substring(0, dashPos));
            range.end =
                Long.parseLong(rangeHeader.substring(dashPos + 1, slashPos));
            range.length = Long.parseLong
                (rangeHeader.substring(slashPos + 1, rangeHeader.length()));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        if (!range.validate()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        return range;

    }


    /**
     * Parse the range header.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @return Vector of ranges
     */
    protected static ArrayList<Range> parseRange(HttpServletRequest request,
            HttpServletResponse response,
            ContentProxy content) throws IOException {

        // Checking If-Range
        String headerValue = request.getHeader("If-Range");

        if (headerValue != null) {

            long headerValueTime = (-1L);
            try {
                headerValueTime = request.getDateHeader("If-Range");
            } catch (IllegalArgumentException e) {
                // Ignore
            }

            String eTag = content.getETag();
            long lastModified = content.getLastModified();

            if (headerValueTime == (-1L)) {

                // If the ETag the client gave does not match the entity
                // etag, then the entire entity is returned.
                if (!eTag.equals(headerValue.trim()))
                    return FULL;

            } else {

                // If the timestamp of the entity the client got is older than
                // the last modification date of the entity, the entire entity
                // is returned.
                if (lastModified > (headerValueTime + 1000))
                    return FULL;

            }

        }

        long fileLength = content.getContentLength();

        if (fileLength == 0)
            return null;

        // Retrieving the range header (if any is specified
        String rangeHeader = request.getHeader("Range");

        if (rangeHeader == null)
            return null;
        // bytes is the only range unit supported (and I don't see the point
        // of adding new ones).
        if (!rangeHeader.startsWith("bytes")) {
            response.addHeader("Content-Range", "bytes */" + fileLength);
            response.sendError
                (HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
            return null;
        }

        rangeHeader = rangeHeader.substring(6);

        // Vector which will contain all the ranges which are successfully
        // parsed.
        ArrayList<Range> result = new ArrayList<Range>();
        StringTokenizer commaTokenizer = new StringTokenizer(rangeHeader, ",");

        // Parsing the range list
        while (commaTokenizer.hasMoreTokens()) {
            String rangeDefinition = commaTokenizer.nextToken().trim();

            Range currentRange = new Range();
            currentRange.length = fileLength;

            int dashPos = rangeDefinition.indexOf('-');

            if (dashPos == -1) {
                response.addHeader("Content-Range", "bytes */" + fileLength);
                response.sendError
                    (HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                return null;
            }

            if (dashPos == 0) {

                try {
                    long offset = Long.parseLong(rangeDefinition);
                    currentRange.start = fileLength + offset;
                    currentRange.end = fileLength - 1;
                } catch (NumberFormatException e) {
                    response.addHeader("Content-Range",
                                       "bytes */" + fileLength);
                    response.sendError
                        (HttpServletResponse
                         .SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                    return null;
                }

            } else {

                try {
                    currentRange.start = Long.parseLong
                        (rangeDefinition.substring(0, dashPos));
                    if (dashPos < rangeDefinition.length() - 1)
                        currentRange.end = Long.parseLong
                            (rangeDefinition.substring
                             (dashPos + 1, rangeDefinition.length()));
                    else
                        currentRange.end = fileLength - 1;
                } catch (NumberFormatException e) {
                    response.addHeader("Content-Range",
                                       "bytes */" + fileLength);
                    response.sendError
                        (HttpServletResponse
                         .SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                    return null;
                }

            }

            if (!currentRange.validate()) {
                response.addHeader("Content-Range", "bytes */" + fileLength);
                response.sendError
                    (HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                return null;
            }

            result.add(currentRange);
        }

        return result;
    }

    /**
     * Copy the contents of the specified input stream to the specified
     * output stream, and ensure that both streams are closed before returning
     * (even in the face of an exception).
     *
     * @param cacheEntry The cache entry for the source resource
     * @param ostream The output stream to write to
     * @param ranges Enumeration of the ranges the client wanted to retrieve
     * @param contentType Content type of the resource
     * @exception IOException if an input/output error occurs
     */
    protected static void copy(ContentProxy content, ServletOutputStream ostream,
                      Iterator<Range> ranges, String contentType)
        throws IOException {

        IOException exception = null;

        while ( (exception == null) && (ranges.hasNext()) ) {

            Range currentRange = ranges.next();

            // Writing MIME header.
            ostream.println();
            ostream.println("--" + mimeSeparation);
            if (contentType != null)
                ostream.println("Content-Type: " + contentType);
            ostream.println("Content-Range: bytes " + currentRange.start
                           + "-" + currentRange.end + "/"
                           + currentRange.length);
            ostream.println();

            // Printing content
            try {
                content.push(ostream, currentRange.start, currentRange.end);
            } catch(IOException ex) {
                exception = ex;
            }
        }

        ostream.println();
        ostream.print("--" + mimeSeparation + "--");

        // Rethrow any exception that has occurred
        if (exception != null)
            throw exception;

    }
    
    public static void copy(InputStream in, OutputStream out) throws IOException {
        copy(in, out, 0, -1);
    }
    
    public static void copy(InputStream in, OutputStream out, long start, long end) throws IOException {
        byte[] buf = new byte[2048];

        long skipped = 0;
        if (start > 0) {
            try {
                skipped = in.skip(start);
            } catch (IOException ex) {
                // carry on
            }
        }
        if (skipped < start) {
            // fallback, read until we skip all that data
            try {
                while(skipped < start) {
                    int len = in.read(buf, 0, (int)Math.min(start - skipped, buf.length));
                    if (len < 0) break;
                }
            } finally {
                in.close();
            }
        }
        if (skipped < start) {
            throw new IOException("Cannot skip to position " + start);
        }
        
        long pos = start;
        try {
            while((end < 0) || (pos < end)) {
                int len = in.read(buf, 0, end < 0 ? buf.length : (int)Math.min(end - pos, buf.length));
                if (len < 0) break;
                out.write(buf, 0, len);
                pos += len;
            }
        } finally {
            in.close();
        }
    }
    
    public static void copy(Reader in, Writer out) throws IOException {
        try {
            char[] buf = new char[2048];
            while(true) {
                int len = in.read(buf, 0, buf.length);
                if (len < 0) break;
                out.write(buf, 0, len);
            }
        } finally {
            in.close();
        }        
    }
}
