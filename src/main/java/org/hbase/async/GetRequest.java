package org.hbase.async;

public class GetRequest {
  
  final private byte[] table;
  final private byte[] key;
  private byte[] family = null;
  private byte[] qualifier = null;

  public GetRequest(byte[] table, byte[] key) {
    this.table = table;
    this.key = key;
  }

  public String getTable() {
    return new String(table);
  }

  public GetRequest qualifier(byte[] qualifier) {
    this.qualifier = qualifier;
    return this;
  }
  
  public GetRequest family(byte[] family) {
    this.family = family;
    return this;
  }
  
  byte[] getQualifier() {
    return qualifier;
  }
  
  byte[] getFamily() {
    return family;
  }
  
  public byte[] key() {
    return key;
  }

}
