package com.example.bubblelayout;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.ToOne;

import com.example.bubblelayout.db.DaoSession;
import com.example.bubblelayout.db.StudentEntityDao;
import com.example.bubblelayout.db.TeacherEntityDao;
import com.example.bubblelayout.db.HeadDao;

@Entity
public class TeacherEntity {

    @Id
    private String id;

    private String name;

    private String headId;

    @ToOne(joinProperty = "headId")
    private Head head;

    @ToMany(referencedJoinProperty = "teachId")
    private List<StudentEntity> studentEntities;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 334881771)
    private transient TeacherEntityDao myDao;

    @Generated(hash = 950436863)
    public TeacherEntity(String id, String name, String headId) {
        this.id = id;
        this.name = name;
        this.headId = headId;
    }

    @Generated(hash = 979429349)
    public TeacherEntity() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadId() {
        return this.headId;
    }

    public void setHeadId(String headId) {
        this.headId = headId;
    }

    @Generated(hash = 279260509)
    private transient String head__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1699346720)
    public Head getHead() {
        String __key = this.headId;
        if (head__resolvedKey == null || head__resolvedKey != __key) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HeadDao targetDao = daoSession.getHeadDao();
            Head headNew = targetDao.load(__key);
            synchronized (this) {
                head = headNew;
                head__resolvedKey = __key;
            }
        }
        return head;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2118579511)
    public void setHead(Head head) {
        synchronized (this) {
            this.head = head;
            headId = head == null ? null : head.getId();
            head__resolvedKey = headId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 179149142)
    public List<StudentEntity> getStudentEntities() {
        if (studentEntities == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentEntityDao targetDao = daoSession.getStudentEntityDao();
            List<StudentEntity> studentEntitiesNew = targetDao
                    ._queryTeacherEntity_StudentEntities(id);
            synchronized (this) {
                if (studentEntities == null) {
                    studentEntities = studentEntitiesNew;
                }
            }
        }
        return studentEntities;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 606246683)
    public synchronized void resetStudentEntities() {
        studentEntities = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 498803510)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeacherEntityDao() : null;
    }

  
}
