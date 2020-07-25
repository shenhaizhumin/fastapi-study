package com.example.bubblelayout.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.bubblelayout.entity.UserEntity;
import com.example.bubblelayout.Head;
import com.example.bubblelayout.StudentEntity;
import com.example.bubblelayout.TeacherEntity;
import com.example.bubblelayout.entity.ChatMessageEntity;
import com.example.bubblelayout.entity.ConversationEntity;
import com.example.bubblelayout.entity.MessageEntity;

import com.example.bubblelayout.db.UserEntityDao;
import com.example.bubblelayout.db.HeadDao;
import com.example.bubblelayout.db.StudentEntityDao;
import com.example.bubblelayout.db.TeacherEntityDao;
import com.example.bubblelayout.db.ChatMessageEntityDao;
import com.example.bubblelayout.db.ConversationEntityDao;
import com.example.bubblelayout.db.MessageEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userEntityDaoConfig;
    private final DaoConfig headDaoConfig;
    private final DaoConfig studentEntityDaoConfig;
    private final DaoConfig teacherEntityDaoConfig;
    private final DaoConfig chatMessageEntityDaoConfig;
    private final DaoConfig conversationEntityDaoConfig;
    private final DaoConfig messageEntityDaoConfig;

    private final UserEntityDao userEntityDao;
    private final HeadDao headDao;
    private final StudentEntityDao studentEntityDao;
    private final TeacherEntityDao teacherEntityDao;
    private final ChatMessageEntityDao chatMessageEntityDao;
    private final ConversationEntityDao conversationEntityDao;
    private final MessageEntityDao messageEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userEntityDaoConfig = daoConfigMap.get(UserEntityDao.class).clone();
        userEntityDaoConfig.initIdentityScope(type);

        headDaoConfig = daoConfigMap.get(HeadDao.class).clone();
        headDaoConfig.initIdentityScope(type);

        studentEntityDaoConfig = daoConfigMap.get(StudentEntityDao.class).clone();
        studentEntityDaoConfig.initIdentityScope(type);

        teacherEntityDaoConfig = daoConfigMap.get(TeacherEntityDao.class).clone();
        teacherEntityDaoConfig.initIdentityScope(type);

        chatMessageEntityDaoConfig = daoConfigMap.get(ChatMessageEntityDao.class).clone();
        chatMessageEntityDaoConfig.initIdentityScope(type);

        conversationEntityDaoConfig = daoConfigMap.get(ConversationEntityDao.class).clone();
        conversationEntityDaoConfig.initIdentityScope(type);

        messageEntityDaoConfig = daoConfigMap.get(MessageEntityDao.class).clone();
        messageEntityDaoConfig.initIdentityScope(type);

        userEntityDao = new UserEntityDao(userEntityDaoConfig, this);
        headDao = new HeadDao(headDaoConfig, this);
        studentEntityDao = new StudentEntityDao(studentEntityDaoConfig, this);
        teacherEntityDao = new TeacherEntityDao(teacherEntityDaoConfig, this);
        chatMessageEntityDao = new ChatMessageEntityDao(chatMessageEntityDaoConfig, this);
        conversationEntityDao = new ConversationEntityDao(conversationEntityDaoConfig, this);
        messageEntityDao = new MessageEntityDao(messageEntityDaoConfig, this);

        registerDao(UserEntity.class, userEntityDao);
        registerDao(Head.class, headDao);
        registerDao(StudentEntity.class, studentEntityDao);
        registerDao(TeacherEntity.class, teacherEntityDao);
        registerDao(ChatMessageEntity.class, chatMessageEntityDao);
        registerDao(ConversationEntity.class, conversationEntityDao);
        registerDao(MessageEntity.class, messageEntityDao);
    }
    
    public void clear() {
        userEntityDaoConfig.clearIdentityScope();
        headDaoConfig.clearIdentityScope();
        studentEntityDaoConfig.clearIdentityScope();
        teacherEntityDaoConfig.clearIdentityScope();
        chatMessageEntityDaoConfig.clearIdentityScope();
        conversationEntityDaoConfig.clearIdentityScope();
        messageEntityDaoConfig.clearIdentityScope();
    }

    public UserEntityDao getUserEntityDao() {
        return userEntityDao;
    }

    public HeadDao getHeadDao() {
        return headDao;
    }

    public StudentEntityDao getStudentEntityDao() {
        return studentEntityDao;
    }

    public TeacherEntityDao getTeacherEntityDao() {
        return teacherEntityDao;
    }

    public ChatMessageEntityDao getChatMessageEntityDao() {
        return chatMessageEntityDao;
    }

    public ConversationEntityDao getConversationEntityDao() {
        return conversationEntityDao;
    }

    public MessageEntityDao getMessageEntityDao() {
        return messageEntityDao;
    }

}
