package com.cesarcoz.clase.contentproviderdemo;

import android.app.ListActivity;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Administrator on 25/04/2015.
 */
public class QueryContentProvider extends ListActivity {

    String[] mProyection = {
            UserDictionary.Words._ID,
            UserDictionary.Words.WORD,
            UserDictionary.Words.LOCALE
    };
    String mSelection = null;
    String[] mSelectedArgs = {""};
    int[] mWordListItems = {
            R.id.txtId,
            R.id.txtWord,
            R.id.txtLocale
    };

    SimpleCursorAdapter simpleCursorAdapter;

    void getWordsProvider() {
        //Cursor cursor = getContentResolver().query(UserDictionary.Words.CONTENT_URI, mProyection, null, null, null);
        Cursor cursor = getContentResolver().query(ContractProvider.CONTENT_URI, mProyection, null, null, null);
        if (cursor == null) {
            Log.d("getWordsProvider", "Error querying content provider");
        } else if (cursor.getCount() < 1) {
            Log.d("getWordsProvider", "No words from provider");
        } else {
            simpleCursorAdapter = new SimpleCursorAdapter(getApplicationContext(),
                    R.layout.rowcursor, cursor, mProyection, mWordListItems, 0);
            getListView().setAdapter(simpleCursorAdapter);
        }

        StringBuilder words = new StringBuilder();
        int index = cursor.getColumnIndex(UserDictionary.Words.WORD);
        if (cursor != null){
            while (cursor.moveToNext()){
                words.append(cursor.getString(index));
                words.append("");
            }
        }
        Log.d("words",words.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insertWord();
        getWordsProvider();
    }

    private void insertWord() {
        Uri uri;
        ContentValues contentValues = new ContentValues();

        contentValues.put(ContractProvider.COLUMN_LOCALE,"en_US");
        contentValues.put(ContractProvider.COLUMN_WORD,"insert");
        contentValues.put(ContractProvider.COLUMN_FREQUENCY,100);

        uri = getContentResolver().insert(ContractProvider.CONTENT_URI,contentValues);
        Log.d("new uri::",""+ ContentUris.parseId(uri));
     }
}
