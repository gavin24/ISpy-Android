package com.ackerman.j.gavin.ispy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Services.Impl.StoryServiceImpl;
import com.ackerman.j.gavin.ispy.Services.StoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavin.ackerman on 2016-11-11.
 */
public class NewsFeedActivity extends Activity {
    private StoryServiceImpl activateService;
    private boolean isBound = false;
    private StoryService stoService;

    ListView listView;
    Story newsItem;
    List<Story> newsItems;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed_activity);

        listView = (ListView) findViewById(R.id.listView);


        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    StoryServiceImpl service = new StoryServiceImpl();
                    newsItems = service.getAllStorys();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            thread.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        ArrayList<String> values = new ArrayList<String>();
        for(int x = 0 ;x < newsItems.size() ; x++)
        {
            values.add(newsItems.get(x).getName());
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                newsItem = newsItems.get(pos);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            StoryServiceImpl service = new StoryServiceImpl();
                            Story story = service.getStory(newsItem.getId());
                            service.deleteStory(story);
                            Intent i = new Intent(NewsFeedActivity.this,ViewStoryActivity.class);
                            i.putExtra("headline",story.getName());
                            i.putExtra("image",story.getImage());
                            i.putExtra("tag",story.getTag());
                            i.putExtra("text",story.getText());

                            startActivity(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();

                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                listView.setAdapter(adapter);
                return true;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                newsItem = newsItems.get(position);

                Toast.makeText(getApplicationContext(),
                        "Item ID :" + newsItem.getId() + "\n" +

                                "Name :" + newsItem.getName(), Toast.LENGTH_LONG)
                        .show();
            }

        });
    }


   

}
