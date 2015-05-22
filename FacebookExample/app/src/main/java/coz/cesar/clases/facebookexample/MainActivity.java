package coz.cesar.clases.facebookexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


@ContentView(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    private static final String PERMISO = "publish_actions";

    CallbackManager callbackManager;
    @InjectView(R.id.txtSaludo)
    TextView txtSaludo;
    @InjectView(R.id.profilePic)
    ProfilePictureView profilePic;
    ShareDialog shareDialog;

    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onSuccess(Sharer.Result result) {
            Log.d("FacebookApp","shareCallback.onSuccess");
            if (result.getPostId()!=null){
                Toast.makeText(MainActivity.this,"Post publicado"+result.getPostId(),Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Log.d("FacebookApp", "shareCallback.onCancel");
        }

        @Override
        public void onError(FacebookException e) {
            Log.d("FacebookApp","shareCallback.onError");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("FacebookApp", "onSuccess");
                actualizarUI();

            }

            @Override
            public void onCancel() {
                Log.d("FacebookApp", "onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.d("FacebookApp", "onError");
            }
        });

        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager,shareCallback);

        actualizarUI();
    }

    private void actualizarUI() {
        Log.d("FacebookApp", "actualizarUI");
        boolean habiliarBotones = AccessToken.getCurrentAccessToken() != null;
        Profile profile = Profile.getCurrentProfile();

        if (habiliarBotones && profile != null) {
            Log.d("FacebookApp", "" + profile.getFirstName());
            txtSaludo.setText(getString(R.string.saludo, profile.getFirstName()));
            profilePic.setProfileId(profile.getId());
        } else {
            Log.d("FacebookApp", "Aun no autenticado");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    public void makePost(View view) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            boolean permisos = accessToken.getPermissions().contains(PERMISO);
            if (permisos) {
                Profile profile = Profile.getCurrentProfile();
                ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                        .setContentTitle("Gran titular de mi post")
                        .setContentDescription("Esta es una pequeña descripcion, meng.")
                        .setContentUrl(Uri.parse("www.google.com"))
                        .build();
                shareDialog.show(shareLinkContent);
                //ShareApi.share(shareLinkContent,shareCallback);

            } else {
                LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList(PERMISO));
            }
        }
    }

}
