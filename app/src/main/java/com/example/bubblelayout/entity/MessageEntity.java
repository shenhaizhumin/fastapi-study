package com.example.bubblelayout.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * conversationType	Conversation.ConversationType	会话类型
 * targetId	String	目标id（对方的id）
 * messageId	int	本地消息id
 * UId	String	服务端存储的消息id
 * messageDirection	MessageDirection	消息方向枚举，发送: 1, 接收: 2,
 * senderUserId	String	发送者id
 * receivedStatus	ReceivedStatus	接收状态
 * sentStatus	SentStatus	发送状态
 * receivedTime	long	接收时间
 * sentTime	long	发送时间
 * readTime	long	读取时间
 * objectName	String	消息对象名称（消息唯一标识）
 * content	MessageContent	消息内容
 * extra	String	拓展字段
 * readReceiptInfo	ReadReceiptInfo	消息回执信息（只有群组和讨论组里的消息才需要此属性，其它类型会话中本属性为 null）
 */

/**
 * 消息实体类
 */
@Entity
public class MessageEntity {
    @Id
    private Long id;
    private Integer userId;//当前用户id
    private String conversationType;
    private Integer targetId;
    private Integer messageId;
    @Unique
    private String UId;
    private Integer messageDirection;
    private Integer senderUserId;
    private Integer receivedStatus;
    private Integer sentStatus;
    private Long receivedTime;
    private Long sentTime;
    private Long readTime;
    private String objectName;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getConversationType() {
        return conversationType;
    }

    public void setConversationType(String conversationType) {
        this.conversationType = conversationType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public Integer getMessageDirection() {
        return messageDirection;
    }

    public void setMessageDirection(Integer messageDirection) {
        this.messageDirection = messageDirection;
    }

    public Integer getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Integer senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Integer getReceivedStatus() {
        return receivedStatus;
    }

    public void setReceivedStatus(Integer receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public Integer getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(Integer sentStatus) {
        this.sentStatus = sentStatus;
    }

    public Long getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Long receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Long getSentTime() {
        return sentTime;
    }

    public void setSentTime(Long sentTime) {
        this.sentTime = sentTime;
    }

    public Long getReadTime() {
        return readTime;
    }

    public void setReadTime(Long readTime) {
        this.readTime = readTime;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public boolean getIsSaved() {
        return this.isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    private String extra;
    private boolean isSaved;
    private Integer messageStatus;

    @Generated(hash = 128093744)
    public MessageEntity(Long id, Integer userId, String conversationType,
            Integer targetId, Integer messageId, String UId,
            Integer messageDirection, Integer senderUserId, Integer receivedStatus,
            Integer sentStatus, Long receivedTime, Long sentTime, Long readTime,
            String objectName, String content, String extra, boolean isSaved,
            Integer messageStatus) {
        this.id = id;
        this.userId = userId;
        this.conversationType = conversationType;
        this.targetId = targetId;
        this.messageId = messageId;
        this.UId = UId;
        this.messageDirection = messageDirection;
        this.senderUserId = senderUserId;
        this.receivedStatus = receivedStatus;
        this.sentStatus = sentStatus;
        this.receivedTime = receivedTime;
        this.sentTime = sentTime;
        this.readTime = readTime;
        this.objectName = objectName;
        this.content = content;
        this.extra = extra;
        this.isSaved = isSaved;
        this.messageStatus = messageStatus;
    }

    @Generated(hash = 1797882234)
    public MessageEntity() {
    }

}
