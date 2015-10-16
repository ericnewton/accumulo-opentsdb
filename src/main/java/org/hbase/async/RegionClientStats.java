package org.hbase.async;

public class RegionClientStats {

  public long rpcsSent() {
    return 0;
  }

  public long inflightRPCs() {
    return 0;
  }

  public long pendingRPCs() {
    return 0;
  }

  public long pendingBatchedRPCs() {
    return 0;
  }

  public boolean isDead() {
    return false;
  }

  public long rpcID() {
    return 0;
  }

  public String remoteEndpoint() {
    return "";
  }

  public long rpcResponsesTimedout() {
    return 0;
  }

  public long rpcsTimedout() {
    return 0;
  }

  public long rpcResponsesUnknown() {
    return 0;
  }

  public long inflightBreached() {
    return 0;
  }

  public long pendingBreached() {
    return 0;
  }

  public long writesBlocked() {
    return 0;
  }

  public long regionClients() {
    return 0;
  }

  public long idleConnectionsClosed() {
    return 0;
  }

}
