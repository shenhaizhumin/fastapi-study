package com.example.bubblelayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bubblelayout.db.ChatMessageEntityDao;
import com.example.bubblelayout.db.ConversationEntityDao;
import com.example.bubblelayout.db.DaoMaster;
import com.example.bubblelayout.db.DaoSession;
import com.example.bubblelayout.db.MessageEntityDao;
import com.example.bubblelayout.db.StudentEntityDao;
import com.example.bubblelayout.db.TeacherEntityDao;
import com.example.bubblelayout.db.UserEntityDao;
import com.example.bubblelayout.entity.ChatMessageEntity;
import com.example.bubblelayout.entity.ConversationEntity;
import com.example.bubblelayout.entity.MessageEntity;
import com.example.bubblelayout.entity.UserEntity;
import com.google.gson.Gson;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class DbController {
    /**
     * Helper
     */
    private DaoMaster.DevOpenHelper mHelper;//获取Helper对象
    /**
     * 数据库
     */
    private SQLiteDatabase db;
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * 上下文
     */
    private Context context;
    /**
     * dao
     */
    private TeacherEntityDao teacherEntityDao;
    private StudentEntityDao studentEntityDao;
    private ChatMessageEntityDao chatMessageEntityDao;
    private ConversationEntityDao conversationEntityDao;
    private MessageEntityDao messageEntityDao;
    private UserEntityDao userEntityDao;

    private static DbController mDbController;

    /**
     * 获取单例
     */
    public static DbController getInstance() {
        if (mDbController == null) {
            synchronized (DbController.class) {
                if (mDbController == null) {
                    mDbController = new DbController();
                }
            }
        }
        return mDbController;
    }


    public void init(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context, "person.db", null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        teacherEntityDao = mDaoSession.getTeacherEntityDao();
        studentEntityDao = mDaoSession.getStudentEntityDao();
        chatMessageEntityDao = mDaoSession.getChatMessageEntityDao();
        conversationEntityDao = mDaoSession.getConversationEntityDao();
        messageEntityDao = mDaoSession.getMessageEntityDao();
        userEntityDao = mDaoSession.getUserEntityDao();
    }

    /**
     * 根据id获取用户信息
     */
    public UserEntity getUserById(Integer userId) {
        return userEntityDao.queryBuilder().where(UserEntityDao.Properties.Id.eq(userId.longValue())).build().unique();
    }

    public void saveUsers(List<UserEntity> users) {
        userEntityDao.insertOrReplaceInTx(users);
    }


    /**
     * 获取最新的会话列表
     *
     * @return
     */
    public List<ConversationEntity> getLatestConversationList(Integer userId) {
        return conversationEntityDao.queryBuilder().where(ConversationEntityDao.Properties.UserId.eq(userId))
                .orderAsc(ConversationEntityDao.Properties.ReceivedTime).build().list();
    }

    public ConversationEntity getConversationEntity(Integer userId, Integer friendId) {
        return conversationEntityDao.queryBuilder().where(ConversationEntityDao.Properties.UserId.eq(userId), ConversationEntityDao.Properties.FriendId.eq(friendId))
                .build().unique();
    }

    /**
     * 根据当前用户id和朋友id 获取本地消息记录
     * objectName :消息唯一标识
     *
     * @return
     */
    public List<MessageEntity> getMessageList(String objectName) {
        QueryBuilder<MessageEntity> query = messageEntityDao.queryBuilder();
        return query.where(MessageEntityDao.Properties.ObjectName.eq(objectName))
                .orderAsc(MessageEntityDao.Properties.SentTime).build().list();
//        return query
//                .where(MessageEntityDao.Properties.UserId.eq(userId),
//                        query.or(MessageEntityDao.Properties.SenderUserId.eq(friendId), MessageEntityDao.Properties.TargetId.eq(friendId)))
//                .orderAsc(MessageEntityDao.Properties.SentTime).build().list();
    }

    /**
     * 更新会话信息
     */
    public void updateConversation(ConversationEntity conversationEntity) {
        conversationEntityDao.update(conversationEntity);
        Log.e("DbController", "updateConversation");
    }

    /**
     * 新增会话信息
     * @return
     */
    public long insertConversation(ConversationEntity conversationEntity) {
        if (conversationEntity.getObjectName()==null || conversationEntity.getObjectName().isEmpty())
            return -1;
        if (conversationEntityDao.queryBuilder().where(ConversationEntityDao.Properties.ObjectName.eq(conversationEntity.getObjectName())).build().list().size() == 0)
        {
            Log.e("DbController", "insertConversation:" + new Gson().toJson(conversationEntity));
            return conversationEntityDao.insertOrReplace(conversationEntity);
        }

        return -1;
    }

    /**
     * 根据friendId查询conversationEntity
     * @param friendId
     * @return
     */
    public ConversationEntity getConversation(Long friendId){
        return conversationEntityDao.queryBuilder().where(ConversationEntityDao.Properties.FriendId.eq(friendId.intValue())).build().unique();
    }

    public void insertMessage(MessageEntity messageEntity) {
        if (messageEntityDao.queryBuilder().where(MessageEntityDao.Properties.UId.eq(messageEntity.getUId())).build().list().size() == 0) {
            messageEntity.setId(null);
            messageEntityDao.insert(messageEntity);
            Log.e("DbController", "insertMsg:" + new Gson().toJson(messageEntity));
        }

    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, "person.db", null);
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, "person.db", null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }

    /**
     * 会自动判定是插入还是替换
     *
     * @param teacherEntity
     */
    public void insertOrReplace(TeacherEntity teacherEntity) {
        teacherEntityDao.insertOrReplace(teacherEntity);
    }

    public void clearAll() {
        teacherEntityDao.deleteAll();
    }

    /**
     * 插入一条记录，表里面要没有与之相同的记录
     *
     * @param teacherEntity
     */
    public long insert(TeacherEntity teacherEntity) {
        return teacherEntityDao.insert(teacherEntity);
    }

    public long insertChatMsg(ChatMessageEntity chatMessageEntity) {
//        return chatMessageEntityDao.insert(chatMessageEntity);
        return chatMessageEntityDao.insertOrReplace(chatMessageEntity);
    }

    /**
     * 查询单聊所有消息
     *
     * @param user_id
     * @param friend_id
     * @return
     */
    public List<ChatMessageEntity> findChatMsgById(Integer user_id, Integer friend_id) {
        return chatMessageEntityDao.queryBuilder().where(ChatMessageEntityDao.Properties.Friend_id.eq(friend_id), ChatMessageEntityDao.Properties.User_id.eq(user_id)).orderAsc(ChatMessageEntityDao.Properties.Post_date).build().list();
    }

    /**
     * 查询用户所有消息
     *
     * @param user_id
     * @return
     */
    public List<ChatMessageEntity> findChatMsgById(Integer user_id) {
        return chatMessageEntityDao.queryBuilder().where(ChatMessageEntityDao.Properties.User_id.eq(user_id)).orderDesc(ChatMessageEntityDao.Properties.Post_date).build().list();
    }

    /**
     * 查询所有单聊记录的最新记录
     *
     * @param friendIds
     * @return
     */
    public List<ChatMessageEntity> findUserLatestMsg(List<Integer> friendIds) {
        List<ChatMessageEntity> list = new ArrayList<>();
        for (int i = 0; i < friendIds.size(); i++) {
            int friend_id = friendIds.get(i);
            List<ChatMessageEntity> temp = chatMessageEntityDao.queryBuilder().where(ChatMessageEntityDao.Properties.Friend_id.eq(friend_id)).orderDesc(ChatMessageEntityDao.Properties.Post_date).build().list();
            if (temp.size() > 0) {
                list.add(temp.get(0));
            }
        }
        return list;
    }

//    /**
//     * 更新数据
//     *
//     * @param personInfor
//     */
//    public void update(PersonInfor personInfor) {
//        PersonInfor mOldPersonInfor = personInforDao.queryBuilder().where(PersonInforDao.Properties.Id.eq(personInfor.getId())).build().unique();//拿到之前的记录
//        if (mOldPersonInfor != null) {
//            mOldPersonInfor.setName("张三");
//            personInforDao.update(mOldPersonInfor);
//        }
//    }

    /**
     * 按条件查询数据
     */
    public List<TeacherEntity> searchByWhere(String wherecluse) {
        List<TeacherEntity> personInfors = (List<TeacherEntity>) teacherEntityDao.queryBuilder().where(TeacherEntityDao.Properties.Name.eq(wherecluse)).build().unique();
        return personInfors;
    }

    /**
     * 查询所有数据
     */
    public List<TeacherEntity> searchAll() {
        List<TeacherEntity> personInfors = teacherEntityDao.queryBuilder().list();
        return personInfors;
    }

    /**
     * 删除数据
     */
    public void delete(String wherecluse) {
        teacherEntityDao.queryBuilder().where(TeacherEntityDao.Properties.Name.eq(wherecluse)).buildDelete().executeDeleteWithoutDetachingEntities();
    }
}