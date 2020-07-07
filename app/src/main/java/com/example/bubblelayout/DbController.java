package com.example.bubblelayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bubblelayout.db.DaoMaster;
import com.example.bubblelayout.db.DaoSession;
import com.example.bubblelayout.db.StudentEntityDao;
import com.example.bubblelayout.db.TeacherEntityDao;

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



    public  void init(Context context){
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context, "person.db", null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        teacherEntityDao = mDaoSession.getTeacherEntityDao();
        studentEntityDao = mDaoSession.getStudentEntityDao();
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

    public void clearAll(){
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