package com.example.bubblelayout.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 会话
 * <p>
 * conversationType	ConversationType	会话类型
 * targetId	String	目标 id
 * conversationTitle	String	会话标题
 * portraitUrl	int	头像 Url
 * unreadMessageCount	String	未读消息数
 * isTop	boolean	是否置顶
 * receivedStatus	Message.ReceivedStatus	接收到的消息的状态
 * sentStatus	Message.SentStatus	发送出的消息的状态
 * receivedTime	long	消息接收时间
 * sentTime	long	最后消息发送时间
 * objectName	String	消息对象名称
 * senderUserId	String	发送消息的用户 Id
 * senderUserName	String	发送消息的用户名称
 * latestMessageId	int	会话最近一条消息 Id
 * latestMessage	MessageContent	本会话最后一条消息
 * draft	String	文字消息草稿
 * notificationStatus	ConversationNotificationStatus	通知消息状态
 * mentionedCount	mentionedCount	本会话最后一条 @ 消息的id
 */

/**
 * 会话 实体类
 */
@Entity
public class ConversationEntity {
    @Id
    private Long id;
    private Integer userId;
    private Integer friendId;
    private String conversationType; //会话类型
    private Integer targetId; //目标 id
    private String conversationTitle; //会话标题
    private String portraitUrl; //头像 Url
    private Integer unreadMessageCount;//未读消息数
    private Boolean isTop;//是否置顶
    private Boolean receivedStatus;//发送出的消息的状态
    private String sentStatus; //发送出的消息的状态

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

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
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

    public String getConversationTitle() {
        return conversationTitle;
    }

    public void setConversationTitle(String conversationTitle) {
        this.conversationTitle = conversationTitle;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public Integer getUnreadMessageCount() {
        return unreadMessageCount;
    }

    public void setUnreadMessageCount(Integer unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Boolean getReceivedStatus() {
        return receivedStatus;
    }

    public void setReceivedStatus(Boolean receivedStatus) {
        this.receivedStatus = receivedStatus;
    }

    public String getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(String sentStatus) {
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

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Integer senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    public Integer getLatestMessageId() {
        return latestMessageId;
    }

    public void setLatestMessageId(Integer latestMessageId) {
        this.latestMessageId = latestMessageId;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public Boolean getIsTop() {
        return this.isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    private Long receivedTime;//消息接收时间
    private Long sentTime; //最后消息发送时间
    private String objectName;//消息对象名称
    private Integer senderUserId; //发送消息的用户 Id
    private String senderUserName;//发送消息的用户名称
    private Integer latestMessageId;
    private String latestMessage; //最后一条消息
    private String draft; //草稿

    @Generated(hash = 788335479)
    public ConversationEntity(Long id, Integer userId, Integer friendId,
            String conversationType, Integer targetId, String conversationTitle,
            String portraitUrl, Integer unreadMessageCount, Boolean isTop,
            Boolean receivedStatus, String sentStatus, Long receivedTime,
            Long sentTime, String objectName, Integer senderUserId,
            String senderUserName, Integer latestMessageId, String latestMessage,
            String draft) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.conversationType = conversationType;
        this.targetId = targetId;
        this.conversationTitle = conversationTitle;
        this.portraitUrl = portraitUrl;
        this.unreadMessageCount = unreadMessageCount;
        this.isTop = isTop;
        this.receivedStatus = receivedStatus;
        this.sentStatus = sentStatus;
        this.receivedTime = receivedTime;
        this.sentTime = sentTime;
        this.objectName = objectName;
        this.senderUserId = senderUserId;
        this.senderUserName = senderUserName;
        this.latestMessageId = latestMessageId;
        this.latestMessage = latestMessage;
        this.draft = draft;
    }

    @Generated(hash = 2044044276)
    public ConversationEntity() {
    }
}
