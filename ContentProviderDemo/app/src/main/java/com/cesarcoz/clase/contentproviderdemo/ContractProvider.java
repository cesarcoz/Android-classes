package com.cesarcoz.clase.contentproviderdemo;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Administrator on 25/04/2015.
 */
public class ContractProvider implements BaseColumns{

    public static final String TABLE =  "main";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_FREQUENCY = "frequency";
    public static final String COLUMN_LOCALE = "locale";
    public static final String AUTHORITY = "mycontentproviders";

    private static final String URI = "content://com.cesarcoz.clase.contentproviderdemo."+AUTHORITY+"/main";

    public static  final Uri CONTENT_URI = Uri.parse(URI);
}
