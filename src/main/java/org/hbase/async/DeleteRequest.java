package org.hbase.async;

import org.apache.accumulo.core.data.Mutation;

public class DeleteRequest extends HBaseRpc {
  
  final private byte[] table;
  final private byte[] key;
  final private byte[] family;
  final private byte[][] qualifiers;

  public DeleteRequest(byte[] table, byte[] key, byte[] family, byte[][] qualifiers) {
    this.table = table;
    this.key = key;
    this.family = family;
    this.qualifiers = qualifiers;
  }

  public DeleteRequest(byte[] table, byte[] key, byte[] family, byte[] qualifier) {
    this(table, key, family, new byte[][]{qualifier});
  }

  public DeleteRequest(byte[] table, byte[] key) {
    this.table = table;
    this.key = key;
    this.family = null;
    this.qualifiers = null;
  }

  public String getTable() {
    return new String(table);
  }

  public byte[] getKey() {
    return key;
  }

  public byte[] getFamily() {
    return family;
  }

  public byte[][] getQualifiers() {
    return qualifiers;
  }

  Mutation getDeleteMutation() {
    Mutation m = new Mutation(key);
    for (byte[] qualifier : qualifiers) {
      m.putDelete(family, qualifier);
    }
    return m;
  }
  
  boolean isDeleteRow() {
    return this.qualifiers == null;
  }

}
