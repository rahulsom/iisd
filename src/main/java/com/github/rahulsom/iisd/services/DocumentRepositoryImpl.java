package com.github.rahulsom.iisd.services;

import ihe.iti.xds_b._2007.DocumentRepositoryPortType;
import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryResponseType;

import javax.jws.WebService;

@WebService(endpointInterface = "ihe.iti.xds_b._2007.DocumentRepositoryPortType")
public class DocumentRepositoryImpl implements DocumentRepositoryPortType {

  @Override
  public RegistryResponseType documentRepositoryProvideAndRegisterDocumentSetB(ProvideAndRegisterDocumentSetRequestType body) {
    return null;
  }

  @Override
  public RetrieveDocumentSetResponseType documentRepositoryRetrieveDocumentSet(RetrieveDocumentSetRequestType body) {
    return new RetrieveDocumentSetResponseType().
        withRegistryResponse(new RegistryResponseType().withStatus("SUCCESS")).
        withDocumentResponse()
        ;
  }
}
