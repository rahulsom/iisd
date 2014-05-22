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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/service-definition-beans-test.xml" })
public class HelloWorldServiceTest {

	@Autowired
	@Qualifier("repoClient")
	private DocumentRepositoryPortType helloWorldClient;

	@Test
	public void helloWorldClientTest() {

    RetrieveDocumentSetRequestType request = new RetrieveDocumentSetRequestType();
    RetrieveDocumentSetResponseType response = helloWorldClient.documentRepositoryRetrieveDocumentSet(request);
    assert response.getRegistryResponse().getStatus().equals("SUCCESS");
	}

}
