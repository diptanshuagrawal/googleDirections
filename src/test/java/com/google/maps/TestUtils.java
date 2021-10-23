
package com.google.maps;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestUtils {
  public static String retrieveBody(String filename) {
    InputStream input = TestUtils.class.getResourceAsStream(filename);
    try {
    	Scanner s = new java.util.Scanner(input, StandardCharsets.UTF_8.name());
      s.useDelimiter("\\A");
      String body = s.next();
      s.close();
      if (body == null || body.length() == 0) {
        throw new IllegalArgumentException(
            "filename '" + filename + "' resulted in null or empty body");
      }
      return body;
    }
    finally {}
  }

  public static Thread findLastThreadByName(String name) {
    ThreadGroup currentThreadGroup = Thread.currentThread().getThreadGroup();
    Thread[] threads = new Thread[1000];
    currentThreadGroup.enumerate(threads);
    Thread delayThread = null;
    for (Thread thread : threads) {
      if (thread == null) break;
      if (thread.getName().equals(name)) {
        delayThread = thread;
      }
    }
    return delayThread;
  }
}