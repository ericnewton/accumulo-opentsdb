package org.hbase.async;

public class AppendRequest {

  public AppendRequest(byte[] table, byte[] row, byte[] family, byte[] qualifier, byte[] value) {
    throw new UnsupportedOperationException();
  }
  
  public void setDurable(boolean durable) {
    throw new UnsupportedOperationException();
  }
}
