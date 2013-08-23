package org.hbase.async;

import org.apache.accumulo.core.data.Mutation;
import org.apache.accumulo.core.data.Value;
import org.apache.hadoop.io.Text;

public class PutRequest extends HBaseRpc {
  
  private final byte[] table;
  private final byte[] row;
  private final byte[] family;
  private final byte[][] qualifiers;
  private final byte[][] values;

  public PutRequest(byte[] table, byte[] row, byte[] family, byte[][] qualifiers, byte[][] values) {
    super();
    if (qualifiers.length != values.length)
      throw new IllegalArgumentException("qualifiers length != values length");
    this.table = table;
    this.row = row;
    this.family = family;
    this.qualifiers = qualifiers;
    this.values = values;
  }

  public PutRequest(byte[] table, byte[] row, byte[] family, byte[] qualifier, byte[] value) {
    this(table, row, family, new byte[][]{qualifier}, new byte[][]{value});
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

  byte[][] getQualifiers() {
    return qualifiers;
  }

  byte[][] getValues() {
    return values;
  }

  String getTable() {
    return new String(table);
  }

  Mutation getMutation() {
    Mutation m = new Mutation(row);
    for (int i = 0; i < qualifiers.length; i++) {
      m.put(new Text(family), new Text(qualifiers[i]), new Value(values[i]));
    }
    return m;
  }
  
}
