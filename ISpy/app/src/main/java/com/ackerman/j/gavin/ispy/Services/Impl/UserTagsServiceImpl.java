package com.ackerman.j.gavin.ispy.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.ispy.Config.Util.App;
import com.ackerman.j.gavin.ispy.Domain.UserTags;
import com.ackerman.j.gavin.ispy.Repositories.Impl.UserTagsRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.Impl.UserTagsRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.UserTagsRepository;
import com.ackerman.j.gavin.ispy.Repositories.UserTagsRepository;
import com.ackerman.j.gavin.ispy.Services.UserTagsService;
import com.ackerman.j.gavin.ispy.Services.UserTagsService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-08-14.
 */
public class UserTagsServiceImpl extends Service implements UserTagsService {
    final private UserTagsRepository repository;

    private final IBinder localBinder = new UserTagsServiceLocalBinder();

    private static UserTagsServiceImpl service = null;

    public static UserTagsServiceImpl getInstance()
    {
        if(service == null)
            service = new UserTagsServiceImpl();
        return service;
    }

    public UserTagsServiceImpl()
    {
        repository = new UserTagsRepositoryImpl(App.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class UserTagsServiceLocalBinder extends Binder {
        public UserTagsServiceImpl getService() {
            return UserTagsServiceImpl.this;
        }
    }


    @Override
    public UserTags addUserTags(UserTags userTags) {
        try{
            return repository.save(userTags);
        }
        catch(Exception x)

        {
            x.printStackTrace();
        }
        return null;
    }
    @Override
    public UserTags deleteUserTags(UserTags userTags) {
        return repository.delete(userTags);
    }

    @Override
    public ArrayList<UserTags> getAllUserTags() {
        try {
            ArrayList<UserTags> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<UserTags>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllUserTags() {
        repository.deleteAll();
    }
    @Override
    public UserTags updateUserTags(UserTags userTags) {
        return repository.update(userTags);
    }

    @Override
    public UserTags getUserTags(Long Id) {
        return repository.findById(Id);
    }
}
