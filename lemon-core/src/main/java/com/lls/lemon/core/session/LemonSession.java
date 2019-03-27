package com.lls.lemon.core.session;

import com.lls.lemon.core.exception.LemonArgumentException;
import com.lls.lemon.core.model.LemonAuth;

/************************************
 * LemonSession
 * @author liliangshan
 * @date 2019-03-27
 ************************************/
public abstract class LemonSession implements Session {

  private static final String DEFAULT_SESSION_SEPARATOR = "_";

  protected String getStoreKey(LemonAuth auth) {
    if (auth == null) {
      throw new LemonArgumentException("AUTH must not be null.");
    }

    Long authId = auth.getAuthId();
    if (authId == null) {
      throw new LemonArgumentException("AUTH ID must not be null.");
    }

    return String.valueOf(authId);
  }

  @Override
  public String createSessionId(LemonAuth auth) {
    if (auth == null) {
      throw new LemonArgumentException("auth must not be null.");
    }
    Long authId = auth.getAuthId();
    if (authId == null) {
      throw new LemonArgumentException("auth id must not be null.");
    }
    String version = auth.getVersion();
    if (version == null) {
      throw new LemonArgumentException("auth version must not be null.");
    }
    return String.valueOf(auth.getAuthId()).concat(DEFAULT_SESSION_SEPARATOR).concat(version);
  }

  private String[] parseTokenSessionId(String sessionId) {
    if (sessionId == null || !sessionId.contains(DEFAULT_SESSION_SEPARATOR)) {
      return null;
    }
    return sessionId.split(DEFAULT_SESSION_SEPARATOR);
  }

  @Override
  public String getValue(String sessionId, int index) {
    String[] values = this.parseTokenSessionId(sessionId);
    if (values.length < (index + 1)) {
      return null;
    }

    String value = values[index];
    if (value == null || value.trim().isEmpty()) {
      return null;
    }
    return value;
  }

}
