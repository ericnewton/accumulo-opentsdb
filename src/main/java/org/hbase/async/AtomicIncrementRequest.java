package org.hbase.async;

/**
 * This doesn't do an AtomicIncrementRequest, it just allows us to fake one using zookeeper.
 */
public class AtomicIncrementRequest {

  final byte[] kind;
  final long count;

  public AtomicIncrementRequest(byte[] table, byte[] maxidRow, byte[] idFamily, byte[] kind, long count) {
    this.kind = kind;
    this.count = count;
  }

  public AtomicIncrementRequest(byte[] table, byte[] maxidRow, byte[] idFamily, byte[] kind) {
    this(table, maxidRow, idFamily, kind, 1);
  }

  public byte[] key() {
    return null;
  }

  // added
  byte[] getKind() {
    return kind;
  }

  long getCount() {
    return count;
  }
}
