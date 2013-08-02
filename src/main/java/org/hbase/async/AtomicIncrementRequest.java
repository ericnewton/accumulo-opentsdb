package org.hbase.async;

/**
 * This doesn't do an AtomicIncrementRequest, it just allows us to fake one using zookeeper.
 */
public class AtomicIncrementRequest {
  
  byte[] kind;

  public AtomicIncrementRequest(byte[] table, byte[] maxidRow, byte[] idFamily, byte[] kind) {
    this.kind = kind;
  }
  
  public byte[] key() {
    return null;
  }

  // added
  byte[] getKind() {
    return kind;
  }
}
