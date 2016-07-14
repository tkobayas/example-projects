package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyReaderContext;
import org.jboss.resteasy.spi.interception.MessageBodyReaderInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyWriterContext;
import org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@ServerInterceptor
public class LoggingInterceptor implements MessageBodyReaderInterceptor, MessageBodyWriterInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    public Object read(MessageBodyReaderContext context) throws IOException, WebApplicationException {

        log.info("********* LoggingInterceptor.read() *********");

        InputStream is = context.getInputStream();
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer);
        context.setInputStream(IOUtils.toInputStream(writer.toString()));
        log.info(writer.toString());

        return context.proceed();
    }

    public void write(MessageBodyWriterContext context) throws IOException, WebApplicationException {

        log.info("********* LoggingInterceptor.write() *********");

        OutputStream old = context.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        context.setOutputStream(baos);
        context.proceed();
        log.info(baos.toString());
        baos.writeTo(old);
        context.setOutputStream(old);
    }
}