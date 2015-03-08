package com.github.rahulsom.iisds.test;

import ihe.iti.xds_b._2007.DocumentRepositoryPortType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-definition-beans-test.xml"})
public class HelloWorldServiceTest {

  @Autowired
  @Qualifier("repoClient")
  private DocumentRepositoryPortType repoClient;

  @Test
  public void helloWorldClientTest() throws ExecutionException, InterruptedException {

    RetrieveDocumentSetRequestType request = new RetrieveDocumentSetRequestType();
    RetrieveDocumentSetResponseType response = repoClient.documentRepositoryRetrieveDocumentSet(request);
    assert response.getRegistryResponse().getStatus().equals("SUCCESS");
  }

  @Test
  public void helloWorldClientTestBulk() throws ExecutionException, InterruptedException {

    RetrieveDocumentSetRequestType request = new RetrieveDocumentSetRequestType();
    ArrayList<Future> futures = new ArrayList<Future>();
    for (int i = 0; i < 200; i++) {
      futures.add(repoClient.documentRepositoryRetrieveDocumentSetAsync(request, new AsyncHandler<RetrieveDocumentSetResponseType>() {
        @Override
        public void handleResponse(Response<RetrieveDocumentSetResponseType> res) {
          RetrieveDocumentSetResponseType response = null;
          try {
            response = res.get();
          } catch (InterruptedException e) {
            e.printStackTrace();
          } catch (ExecutionException e) {
            e.printStackTrace();
          }
          assert response != null;
          assert response.getRegistryResponse().getStatus().equals("SUCCESS");
        }
      }));
    }

    for (int i = 0; i < futures.size(); i++) {
      futures.get(i).get();
    }

  }


  @Test
  public void helloWorldClientTest2() throws ExecutionException, InterruptedException {

    RetrieveDocumentSetRequestType request = new RetrieveDocumentSetRequestType();
    repoClient.documentRepositoryRetrieveDocumentSetAsync(request, new AsyncHandler<RetrieveDocumentSetResponseType>() {
      @Override
      public void handleResponse(Response<RetrieveDocumentSetResponseType> res) {
        RetrieveDocumentSetResponseType response = null;
        try {
          response = res.get();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
        assert response != null;
        assert response.getRegistryResponse().getStatus().equals("SUCCESS.2");
      }
    }).get();


  }

}
