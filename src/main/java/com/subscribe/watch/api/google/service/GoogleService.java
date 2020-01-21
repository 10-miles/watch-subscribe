package com.subscribe.watch.api.google.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleService {
  void getGmailList(String accessToken) throws GeneralSecurityException, IOException;
  void listMessagesMatchingQuery(String accessToken, String query) throws GeneralSecurityException, IOException;
}
