package com.github.rahulsom.iisd.services;

import javax.activation.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class VariableLengthDataSource implements DataSource {

  private static final String MESSAGE = "Lorem ipsum dolor sit amet, consectetur adipiscing " +
      "elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
      "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo " +
      "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore " +
      "eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa " +
      "qui officia deserunt mollit anim id est laborum. ";

  private long length;

  @SuppressWarnings("WeakerAccess")
  public VariableLengthDataSource(long length) {
    this.length = length;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return new InputStream() {
      long counter = 0;

      @Override
      public int read() throws IOException {
        if (counter < length)
          return MESSAGE.charAt((int) (counter++ % MESSAGE.length()));
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
}
