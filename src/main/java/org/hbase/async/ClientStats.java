package org.hbase.async;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Placeholder: doesn't do anything
 */
public class ClientStats {
  
  final private Number ZERO = new AtomicInteger(0);

  public Number rootLookups() {
    return ZERO;
  }

  public Number uncontendedMetaLookups() {
    return ZERO;
  }

  public Number contendedMetaLookups() {
    return ZERO;
  }

  public Number atomicIncrements() {
    return ZERO;
  }

  public Number deletes() {
    return ZERO;
  }

  public Number gets() {
    return ZERO;
  }

  public Number puts() {
    return ZERO;
  }

  public Number rowLocks() {
    return ZERO;
  }

  public Number scannersOpened() {
    return ZERO;
  }

  public Number scans() {
    return ZERO;
  }

  public Number numBatchedRpcSent() {
    return ZERO;
  }

  public Number flushes() {
    return ZERO;
  }

  public Number connectionsCreated() {
    return ZERO;
  }

  public Number noSuchRegionExceptions() {
    return ZERO;
  }

  public Number numRpcDelayedDueToNSRE() {
    return ZERO;
  }
  
}
