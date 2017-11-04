package com.github.rahulsom.iisds.test;

import ihe.iti.xds_b._2007.DocumentRepositoryPortType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;
import org.apache.cxf.transport.http.netty.client.NettyHttpConduit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-definition-beans-test.xml"})
public class HelloWorldServiceTest {

  private static final String CONDUIT = "org.apache.cxf.transport.Conduit";

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  @Qualifier("repoClient")
  private DocumentRepositoryPortType helloWorldClient;

  @Test
  public void helloWorldClientTest() throws ExecutionException, InterruptedException {

    long length = 1000000;

    RetrieveDocumentSetRequestType request = new RetrieveDocumentSetRequestType()
        .withDocumentRequest(new RetrieveDocumentSetRequestType.DocumentRequest()
            .withDocumentUniqueId(Long.toString(length))
        );

    helloWorldClient.documentRepositoryRetrieveDocumentSetAsync(request, response -> {
      try {
        RetrieveDocumentSetResponseType retrieveResponse = response.get();
        Map<String, Object> context = response.getContext();
        assertEquals(context.get(CONDUIT).getClass(), NettyHttpConduit.class);
        assertEquals(retrieveResponse.getRegistryResponse().getStatus(), "SUCCESS");
      } catch (Throwable t) {
        fail("Exception thrown");
      }
    }).get();
  }

}
