package com.subscribe.watch.api.google.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleService {
  public void getGmailList(String accessToken) throws GeneralSecurityException, IOException;
}
