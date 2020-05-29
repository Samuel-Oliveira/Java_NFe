package br.com.swconsultoria.nfe.ws;

import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 
 * @author Leonardo Wiest
 *
 */
public class RetryParameter {

    private RetryParameter() {

    }

    /**
     * 
     * @param stub
     *            Client connection
     * @param retry
     *            Connection retry
     */
    public static void populateRetry(Stub stub, Integer retry) {

        HttpMethodParams methodParams = new HttpMethodParams();

        methodParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(retry, retry != 0));

        stub._getServiceClient().getOptions().setProperty(HTTPConstants.HTTP_METHOD_PARAMS, methodParams);

    }

}
