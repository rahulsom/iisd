package com.github.rahulsom.iisd.services;

import ihe.iti.xds_b._2007.DocumentRepositoryPortType;
import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryResponseType;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import javax.xml.ws.soap.MTOM;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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
        withDocumentResponse(
            body.getDocumentRequest()
                .stream()
                .map(i -> new RetrieveDocumentSetResponseType.DocumentResponse()
                    .withDocumentUniqueId(i.getDocumentUniqueId())
                    .withDocument(getData(Long.parseLong(i.getDocumentUniqueId())))
                    .withMimeType("text/plain")
                )
                .collect(Collectors.toList())

        )
        ;
  }

  private DataHandler getData(long message) {
    return new DataHandler(new DataSource() {
      @Override
      public InputStream getInputStream() throws IOException {
        return new InputStream() {
          int counter = 0;

          @Override
          public int read() throws IOException {
            if (counter++ < message)
              return 'a';
            else
              return -1;
          }
        };
      }

      @Override
      public OutputStream getOutputStream() throws IOException {
        return null;
      }

      @Override
      public String getContentType() {
        return "text/plain";
      }

      @Override
      public String getName() {
        return null;
      }
    });
  }

  @Override
  public Response<RegistryResponseType> documentRepositoryProvideAndRegisterDocumentSetBAsync(
      ProvideAndRegisterDocumentSetRequestType body) {
    return null;
  }

  @Override
  public Future<?> documentRepositoryProvideAndRegisterDocumentSetBAsync(
      ProvideAndRegisterDocumentSetRequestType body,
      AsyncHandler<RegistryResponseType> asyncHandler) {
    return null;
  }

  @Override
  public Response<RetrieveDocumentSetResponseType> documentRepositoryRetrieveDocumentSetAsync(
      RetrieveDocumentSetRequestType body) {
    return null;
  }

  @Override
  public Future<?> documentRepositoryRetrieveDocumentSetAsync(
      RetrieveDocumentSetRequestType body,
      AsyncHandler<RetrieveDocumentSetResponseType> asyncHandler) {
    return null;
  }
}
