package com.example.bubblelayout.entity;

public class Conversation {
    public enum ConversationType {
        /**
         * ConversationType.PRIVATE	单聊	1
         * ConversationType.DISCUSSION	讨论组	2(已废弃)
         * ConversationType.GROUP	群聊	3
         * ConversationType.CHATROOM	聊天室	4
         * ConversationType.CUSTOMER_SERVICE	客服	5
         * ConversationType.SYSTEM	系统	6
         * ConversationType.APP_PUBLIC_SERVICE	应用公众服务	7
         * ConversationType.PUBLIC_SERVICE	公众服务平台	8
         */
        PRIVATE("PRIVATE"), DISCUSSION("DISCUSSION"), GROUP("GROUP"),
        CHATROOM("CHATROOM"), CUSTOMER_SERVICE("CUSTOMER_SERVICE"), SYSTEM("SYSTEM"),
        APP_PUBLIC_SERVICE("APP_PUBLIC_SERVICE"), PUBLIC_SERVIC("PUBLIC_SERVIC");
        public String value;

        ConversationType(String value) {
            this.value = value;
        }
    }

    public enum MessageDirection {
        //消息方向枚举，发送: 1, 接收: 2,
        SENT(1), RECEIVE(2);
        public int value;

        MessageDirection(int value) {
            this.value = value;
        }
    }

    public enum MessageStatus{
        MESSAGESENT(0),SENTSUCCESS(1);
        public int value;
        MessageStatus(int value) {
            this.value = value;
        }
    }
}
