package test.andy.hello;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class testJmeter extends AbstractJavaSamplerClient {
    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        boolean success = true;
        result.sampleStart();

        result.sampleEnd();
        result.setSuccessful(success);
        return result;
    }

    @Override
    public void setupTest(JavaSamplerContext context) {

    }

    @Override
    public void teardownTest(JavaSamplerContext context) {

    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments defaultParameters = new Arguments();
        defaultParameters.addArgument("test_p1", "test");
        return defaultParameters;
    }
}
