package com.example.bubblelayout.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.bubblelayout.entity.MessageEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MESSAGE_ENTITY".
*/
public class MessageEntityDao extends AbstractDao<MessageEntity, Long> {

    public static final String TABLENAME = "MESSAGE_ENTITY";

    /**
     * Properties of entity MessageEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserId = new Property(1, Integer.class, "userId", false, "USER_ID");
        public final static Property ConversationType = new Property(2, String.class, "conversationType", false, "CONVERSATION_TYPE");
        public final static Property TargetId = new Property(3, Integer.class, "targetId", false, "TARGET_ID");
        public final static Property MessageId = new Property(4, Integer.class, "messageId", false, "MESSAGE_ID");
        public final static Property UId = new Property(5, String.class, "UId", false, "UID");
        public final static Property MessageDirection = new Property(6, Integer.class, "messageDirection", false, "MESSAGE_DIRECTION");
        public final static Property SenderUserId = new Property(7, Integer.class, "senderUserId", false, "SENDER_USER_ID");
        public final static Property ReceivedStatus = new Property(8, Integer.class, "receivedStatus", false, "RECEIVED_STATUS");
        public final static Property SentStatus = new Property(9, Integer.class, "sentStatus", false, "SENT_STATUS");
        public final static Property ReceivedTime = new Property(10, Long.class, "receivedTime", false, "RECEIVED_TIME");
        public final static Property SentTime = new Property(11, Long.class, "sentTime", false, "SENT_TIME");
        public final static Property ReadTime = new Property(12, Long.class, "readTime", false, "READ_TIME");
        public final static Property ObjectName = new Property(13, String.class, "objectName", false, "OBJECT_NAME");
        public final static Property Content = new Property(14, String.class, "content", false, "CONTENT");
        public final static Property Extra = new Property(15, String.class, "extra", false, "EXTRA");
        public final static Property IsSaved = new Property(16, boolean.class, "isSaved", false, "IS_SAVED");
        public final static Property MessageStatus = new Property(17, Integer.class, "messageStatus", false, "MESSAGE_STATUS");
    }


    public MessageEntityDao(DaoConfig config) {
        super(config);
    }
    
    public MessageEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MESSAGE_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USER_ID\" INTEGER," + // 1: userId
                "\"CONVERSATION_TYPE\" TEXT," + // 2: conversationType
                "\"TARGET_ID\" INTEGER," + // 3: targetId
                "\"MESSAGE_ID\" INTEGER," + // 4: messageId
                "\"UID\" TEXT UNIQUE ," + // 5: UId
                "\"MESSAGE_DIRECTION\" INTEGER," + // 6: messageDirection
                "\"SENDER_USER_ID\" INTEGER," + // 7: senderUserId
                "\"RECEIVED_STATUS\" INTEGER," + // 8: receivedStatus
                "\"SENT_STATUS\" INTEGER," + // 9: sentStatus
                "\"RECEIVED_TIME\" INTEGER," + // 10: receivedTime
                "\"SENT_TIME\" INTEGER," + // 11: sentTime
                "\"READ_TIME\" INTEGER," + // 12: readTime
                "\"OBJECT_NAME\" TEXT," + // 13: objectName
                "\"CONTENT\" TEXT," + // 14: content
                "\"EXTRA\" TEXT," + // 15: extra
                "\"IS_SAVED\" INTEGER NOT NULL ," + // 16: isSaved
                "\"MESSAGE_STATUS\" INTEGER);"); // 17: messageStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MESSAGE_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MessageEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        String conversationType = entity.getConversationType();
        if (conversationType != null) {
            stmt.bindString(3, conversationType);
        }
 
        Integer targetId = entity.getTargetId();
        if (targetId != null) {
            stmt.bindLong(4, targetId);
        }
 
        Integer messageId = entity.getMessageId();
        if (messageId != null) {
            stmt.bindLong(5, messageId);
        }
 
        String UId = entity.getUId();
        if (UId != null) {
            stmt.bindString(6, UId);
        }
 
        Integer messageDirection = entity.getMessageDirection();
        if (messageDirection != null) {
            stmt.bindLong(7, messageDirection);
        }
 
        Integer senderUserId = entity.getSenderUserId();
        if (senderUserId != null) {
            stmt.bindLong(8, senderUserId);
        }
 
        Integer receivedStatus = entity.getReceivedStatus();
        if (receivedStatus != null) {
            stmt.bindLong(9, receivedStatus);
        }
 
        Integer sentStatus = entity.getSentStatus();
        if (sentStatus != null) {
            stmt.bindLong(10, sentStatus);
        }
 
        Long receivedTime = entity.getReceivedTime();
        if (receivedTime != null) {
            stmt.bindLong(11, receivedTime);
        }
 
        Long sentTime = entity.getSentTime();
        if (sentTime != null) {
            stmt.bindLong(12, sentTime);
        }
 
        Long readTime = entity.getReadTime();
        if (readTime != null) {
            stmt.bindLong(13, readTime);
        }
 
        String objectName = entity.getObjectName();
        if (objectName != null) {
            stmt.bindString(14, objectName);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(15, content);
        }
 
        String extra = entity.getExtra();
        if (extra != null) {
            stmt.bindString(16, extra);
        }
        stmt.bindLong(17, entity.getIsSaved() ? 1L: 0L);
 
        Integer messageStatus = entity.getMessageStatus();
        if (messageStatus != null) {
            stmt.bindLong(18, messageStatus);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MessageEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        String conversationType = entity.getConversationType();
        if (conversationType != null) {
            stmt.bindString(3, conversationType);
        }
 
        Integer targetId = entity.getTargetId();
        if (targetId != null) {
            stmt.bindLong(4, targetId);
        }
 
        Integer messageId = entity.getMessageId();
        if (messageId != null) {
            stmt.bindLong(5, messageId);
        }
 
        String UId = entity.getUId();
        if (UId != null) {
            stmt.bindString(6, UId);
        }
 
        Integer messageDirection = entity.getMessageDirection();
        if (messageDirection != null) {
            stmt.bindLong(7, messageDirection);
        }
 
        Integer senderUserId = entity.getSenderUserId();
        if (senderUserId != null) {
            stmt.bindLong(8, senderUserId);
        }
 
        Integer receivedStatus = entity.getReceivedStatus();
        if (receivedStatus != null) {
            stmt.bindLong(9, receivedStatus);
        }
 
        Integer sentStatus = entity.getSentStatus();
        if (sentStatus != null) {
            stmt.bindLong(10, sentStatus);
        }
 
        Long receivedTime = entity.getReceivedTime();
        if (receivedTime != null) {
            stmt.bindLong(11, receivedTime);
        }
 
        Long sentTime = entity.getSentTime();
        if (sentTime != null) {
            stmt.bindLong(12, sentTime);
        }
 
        Long readTime = entity.getReadTime();
        if (readTime != null) {
            stmt.bindLong(13, readTime);
        }
 
        String objectName = entity.getObjectName();
        if (objectName != null) {
            stmt.bindString(14, objectName);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(15, content);
        }
 
        String extra = entity.getExtra();
        if (extra != null) {
            stmt.bindString(16, extra);
        }
        stmt.bindLong(17, entity.getIsSaved() ? 1L: 0L);
 
        Integer messageStatus = entity.getMessageStatus();
        if (messageStatus != null) {
            stmt.bindLong(18, messageStatus);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MessageEntity readEntity(Cursor cursor, int offset) {
        MessageEntity entity = new MessageEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // conversationType
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // targetId
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // messageId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // UId
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // messageDirection
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // senderUserId
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // receivedStatus
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // sentStatus
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10), // receivedTime
            cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11), // sentTime
            cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12), // readTime
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // objectName
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // content
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // extra
            cursor.getShort(offset + 16) != 0, // isSaved
            cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17) // messageStatus
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MessageEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setConversationType(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTargetId(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setMessageId(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setUId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMessageDirection(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setSenderUserId(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setReceivedStatus(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setSentStatus(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setReceivedTime(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setSentTime(cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11));
        entity.setReadTime(cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12));
        entity.setObjectName(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setContent(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setExtra(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setIsSaved(cursor.getShort(offset + 16) != 0);
        entity.setMessageStatus(cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MessageEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MessageEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MessageEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
