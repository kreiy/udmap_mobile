package com.example.yasmina.udmap_final;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.String;



/**
 * Created by yasmina on 16/02/18.
 */


public class MenuActivity extends Activity
{

    String [] itemsValue = new String[]{"emploi du temps","gestion des notes","actualites","map","coordonnees"};
    int[] listviewImage = new int[]{
            R.drawable.cours, R.drawable.news, R.drawable.map, R.drawable.coord,
    };

    String[] listviewShortDescription = new String[]{"Rendez vous sur la plateforme pour consulter votre emploi du temps","Retrouvez les informations sur votre note ","Retrouvez les informations qui sont a l'une pour l'Universite","Retrouvez les adresses de l'UDM","Retrouver les informations les coordonnees des personnes de l'UDM",};

    @Override   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 4; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", itemsValue[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        java.lang.String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.textView, R.id.listview_item_short_description};


        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.activity_listview, from, to);
        ListView listItemView = (ListView) findViewById(R.id.listView);
        listItemView.setAdapter(simpleAdapter);

    }
}
