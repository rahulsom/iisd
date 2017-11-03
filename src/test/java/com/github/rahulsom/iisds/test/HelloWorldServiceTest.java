package com.github.rahulsom.iisds.test;

import ihe.iti.xds_b._2007.DocumentRepositoryPortType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;
import org.apache.cxf.transport.http.netty.client.NettyHttpConduit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-definition-beans-test.xml"})
public class HelloWorldServiceTest {

  @Autowired
  @Qualifier("repoClient")
  private DocumentRepositoryPortType helloWorldClient;

  @Test
  public void helloWorldClientTest() throws ExecutionException, InterruptedException {

    RetrieveDocumentSetRequestType request = new RetrieveDocumentSetRequestType()
        .withDocumentRequest(new RetrieveDocumentSetRequestType.DocumentRequest()
            .withDocumentUniqueId("1000000")
        );

    helloWorldClient.documentRepositoryRetrieveDocumentSetAsync(request, new AsyncHandler<RetrieveDocumentSetResponseType>() {
      @Override
      public void handleResponse(Response<RetrieveDocumentSetResponseType> res) {
        try {
          RetrieveDocumentSetResponseType response = res.get();
          Map<String, Object> context = res.getContext();
          Assert.assertEquals(context.get("org.apache.cxf.transport.Conduit").getClass(), NettyHttpConduit.class);
          Assert.assertEquals(response.getRegistryResponse().getStatus(), "SUCCESS");
        } catch (Throwable t) {
          Assert.fail("Exception thrown");
        }
      }
    }).get();
  }

}
