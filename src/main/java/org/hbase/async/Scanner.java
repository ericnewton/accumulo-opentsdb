package org.hbase.async;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.apache.accumulo.core.Constants;
import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.IteratorSetting;
import org.apache.accumulo.core.client.TableNotFoundException;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Range;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.iterators.user.RegExFilter;
import org.apache.accumulo.core.util.PeekingIterator;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Logger;

import com.stumbleupon.async.Deferred;

public class Scanner {
  
  private Logger log = Logger.getLogger(Scanner.class);
  
  private final Connector conn;
  private final byte[] table;
  private org.apache.accumulo.core.client.Scanner scanner;
  private byte[] start;
  private byte[] end;
  private byte[] family;
  private byte[] qualifier;
  private int maxRows = Integer.MAX_VALUE;
  private String regexp;
  private Charset charSet;
  private PeekingIterator<Entry<Key, Value>> iterator = null;
  private int rows = 0;
  
  public Scanner(byte[] table, Connector conn) {
    this.table = table;
    this.conn = conn;
  }

  // Only ever returns zero or one row
  public Deferred<ArrayList<ArrayList<KeyValue>>> nextRows() {
    ArrayList<ArrayList<KeyValue>> result = new ArrayList<ArrayList<KeyValue>>();
    ArrayList<KeyValue> arow = new ArrayList<KeyValue>();
    log.warn("scanning " + s(table) + " start " + s(start) + " end " + s(end) + " family " + s(family) + " qualifier " + s(qualifier) + " regexp " + regexp);
    if (rows >= maxRows)
      return Deferred.fromResult(null);
    if (iterator == null) {
      try {
        scanner = conn.createScanner(new String(table), Constants.NO_AUTHS);
      } catch (TableNotFoundException e) {
        log.error(e, e);
        Deferred.fromError(e);
      }
      if (start != null || end != null)
        scanner.setRange(new Range(
            start == null ?  null : new Text(start), 
            end == null  ? null : new Text(end)
            )
        );
      if (family != null) {
        if (qualifier != null) {
          scanner.fetchColumn(new Text(family), new Text(qualifier));
        } else {
          scanner.fetchColumnFamily(new Text(family));
        }
      }
      if (regexp != null) {
        IteratorSetting iter = new IteratorSetting(30, RegExFilter.class);
        RegExFilter.setEncoding(iter, charSet.toString());
        RegExFilter.setRegexs(iter, regexp, null, null, null, true);
      }
      iterator = new PeekingIterator<Entry<Key, Value>>(scanner.iterator());
    }
    if (iterator.hasNext()) {
      KeyValue last;
      arow.add(last = new KeyValue(iterator.next()));
      Text lastRow = new Text(last.key());
      while (iterator.hasNext() && iterator.peek().getKey().getRow().equals(lastRow)) {
        arow.add(new KeyValue(iterator.next()));
      }
      result.add(arow);
      rows++;
    } else {
      return Deferred.fromResult(null);
    }
    log.info("result " + result);
    return Deferred.fromResult(result);
  }

  private String s(byte[] b) {
    if (b == null)
      return null;
    return new String(b);
  }

  public Deferred<Object> close() {
    iterator = null;
    if (scanner != null)
      scanner.close();
    return Deferred.fromResult(null);
  }

  public void setStartKey(byte[] start_row) {
    this.start = start_row;
  }

  public void setStopKey(byte[] end_row) {
    this.end = end_row;
  }

  public void setFamily(byte[] idFamily) {
    this.family = idFamily;
  }

  public void setQualifier(byte[] kind) {
    this.qualifier = kind;
  }

  public void setMaxNumRows(int i) {
    this.maxRows = i;
  }

  public void setKeyRegexp(String regexp, Charset charset) {
    this.regexp = regexp;
    this.charSet = charset;
  }

}
