package org.hbase.async;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {

  Map<String, String> settings = new HashMap<String, String>();
  
  public Config(String location) throws IOException {
  }
  
  public Config() {
  }
  
  public void overrideConfig(String key, String value) {
    settings.put(key, value);
  }

  public String getString(String key) {
    return settings.get(key);
  }

  
  
}
