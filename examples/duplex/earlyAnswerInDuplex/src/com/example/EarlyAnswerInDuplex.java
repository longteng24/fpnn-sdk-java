package com.example;


import com.fpnn.sdk.TCPClient;
import com.fpnn.sdk.proto.Answer;
import com.fpnn.sdk.proto.Quest;

import static java.lang.Thread.sleep;

public class EarlyAnswerInDuplex {

    static TCPClient client;

    public static void main(String[] args) {

        client = TCPClient.create("52.83.245.22", 13609);

        client.setQuestProcessor(new QuestProcessor(), "com.example.QuestProcessor");

        Quest quest = new Quest("duplex demo");
        quest.param("duplex method", "duplexQuest");
        try {
            Answer answer = client.sendQuest(quest);
            System.out.println("Answer: is error answer: " + answer.getErrorCode());
            for (Object obj : answer.getPayload().keySet()) {
                Object value = answer.getPayload().get(obj);
                System.out.println("--- key: " + obj + ", value: " + value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Wait 5 second for see all output.");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}