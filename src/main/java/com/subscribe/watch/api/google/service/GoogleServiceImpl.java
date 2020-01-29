package com.subscribe.watch.api.google.service;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class GoogleServiceImpl implements GoogleService {

  private Logger log = LoggerFactory.getLogger(GoogleServiceImpl.class);
  private ExecutorService executorService;

  private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  public GoogleServiceImpl() {
    this.executorService = Executors.newFixedThreadPool(10);
  }

  /**
   * MethodRole Gmail오브젝트 생성 후 Http Request를 통해 사용자의 Gmail List 를 읽어 들인다.
   */
  @Override
  public void getGmailList(String accessToken) throws GeneralSecurityException, IOException {
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, createCredentials(accessToken))
        .setApplicationName(APPLICATION_NAME)
        .build();

    String user = "me";

    ListMessagesResponse listMessagesResponse = service.users().messages().list(user).execute();
    List<Message> messages = listMessagesResponse.getMessages();

    printMessageList(messages, user, service);

  }

  @Override
  public void listMessagesMatchingQuery(String accessToken, String query)
      throws GeneralSecurityException, IOException {
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, createCredentials(accessToken))
        .setApplicationName(APPLICATION_NAME)
        .build();

    String user = "me";

    ListMessagesResponse listMessagesResponse = service.users().messages().list(user).setQ(query)
        .execute();
    List<Message> messages = listMessagesResponse.getMessages();

    printMessageList(messages, user, service);
  }

  private void printMessageList(List<Message> messages, String user, Gmail service)
      throws IOException {
    if (messages.isEmpty()) {
      System.out.println("No messages found.");
    } else {
      System.out.println("Messages:");
      for (Message message : messages) {
        executorService.submit(new Runnable() {
          @Override
          public void run() {
            try {
              getMessage(service, user, message.getId());
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
//        getMessage(service, user, message.getId());
      }
    }
  }

  private Credential createCredentials(String accessToken) {
    Credential credential = new Credential(BearerToken.queryParameterAccessMethod());
    return credential.setAccessToken(accessToken);
  }

  private Message getMessage(Gmail service, String userId, String messageId)
      throws IOException {
    System.out.println(messageId);
    Message message = service.users().messages().get(userId, messageId)
        .execute(); // <- 주범 이 놈을 논 블로킹으로 만들어야함.

    log.info("Message snippet: {}", message.getSnippet());

    return message;
//    return null;
  }

}
