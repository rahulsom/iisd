package com.github.rahulsom.iisd.services;

import ihe.iti.xds_b._2007.DocumentRepositoryPortType;
import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryResponseType;

import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import javax.xml.ws.soap.MTOM;
import java.util.concurrent.Future;

@WebService(endpointInterface = "ihe.iti.xds_b._2007.DocumentRepositoryPortType")
@MTOM
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

  @Override
  public Response<RegistryResponseType> documentRepositoryProvideAndRegisterDocumentSetBAsync(ProvideAndRegisterDocumentSetRequestType body) {
    return null;
  }

  @Override
  public Future<?> documentRepositoryProvideAndRegisterDocumentSetBAsync(ProvideAndRegisterDocumentSetRequestType body, AsyncHandler<RegistryResponseType> asyncHandler) {
    return null;
  }

  @Override
  public Response<RetrieveDocumentSetResponseType> documentRepositoryRetrieveDocumentSetAsync(RetrieveDocumentSetRequestType body) {
    return null;
  }

  @Override
  public Future<?> documentRepositoryRetrieveDocumentSetAsync(RetrieveDocumentSetRequestType body, AsyncHandler<RetrieveDocumentSetResponseType> asyncHandler) {
    return null;
  }
}
