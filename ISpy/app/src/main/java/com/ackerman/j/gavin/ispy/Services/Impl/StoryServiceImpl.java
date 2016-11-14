package com.ackerman.j.gavin.ispy.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.ispy.Config.Util.App;
import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Repositories.Impl.StoryRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.StoryRepository;
import com.ackerman.j.gavin.ispy.Services.StoryService;
import com.ackerman.j.gavin.ispy.Services.StoryService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class StoryServiceImpl extends Service implements StoryService {
    final private StoryRepository repository;

    private final IBinder localBinder = new StoryServiceLocalBinder();

    private static StoryServiceImpl service = null;

    public static StoryServiceImpl getInstance()
    {
        if(service == null)
            service = new StoryServiceImpl();
        return service;
    }

    public StoryServiceImpl()
    {
        repository = new StoryRepositoryImpl(App.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class StoryServiceLocalBinder extends Binder {
        public StoryServiceImpl getService() {
            return StoryServiceImpl.this;
        }
    }


    @Override
    public Story addStory(Story story) {
        try{
            return repository.save(story);
        }
        catch(Exception x)

        {
            x.printStackTrace();
        }
        return null;
    }
    @Override
    public Story deleteStory(Story story) {
        return repository.delete(story);
    }

    @Override
    public ArrayList<Story> getAllStorys() {
        try {
            ArrayList<Story> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Story>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllStorys() {
        repository.deleteAll();
    }
    @Override
    public Story updateStory(Story story) {
        return repository.update(story);
    }

    @Override
    public Story getStory(Long Id) {
        return repository.findById(Id);
    }
}
