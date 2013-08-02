package org.hbase.async;

import org.apache.accumulo.core.data.Mutation;
import org.apache.accumulo.core.data.Value;
import org.apache.hadoop.io.Text;

public class PutRequest extends HBaseRpc {
  
  private final byte[] table;
  private final byte[] row;
  private final byte[] family;
  private final byte[] qualifier;
  private final byte[] value;

  public PutRequest(byte[] table, byte[] row, byte[] family, byte[] qualifier, byte[] value) {
    super();
    this.table = table;
    this.row = row;
    this.family = family;
    this.qualifier = qualifier;
    this.value = value;
  }

  public void setBufferable(boolean b) {
  }

  public void setDurable(boolean b) {
  }
  
  public byte[] key() {
    return row;
  }

  byte[] getRow() {
    return row;
  }

  byte[] getFamily() {
    return family;
  }

  byte[] getQualifier() {
    return qualifier;
  }

  byte[] getValue() {
    return value;
  }

  String getTable() {
    return new String(table);
  }

  Mutation getMutation() {
    Mutation m = new Mutation(row);
    m.put(new Text(family), new Text(qualifier), new Value(value));
    return m;
  }
  
}
