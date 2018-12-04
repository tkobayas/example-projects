package com.sample;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class MyJavaSampler extends AbstractJavaSamplerClient {

    public SampleResult runTest(JavaSamplerContext arg0) {

        SampleResult result = new SampleResult();
        boolean success = true;

        result.sampleStart();

        // Write your test code here.
        System.out.println("hey! this = " + this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //

        result.sampleEnd();

        result.setSuccessful(success);

        return result;
    }

}
