package pe.edu.cibertect.facebookdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.widget.ShareDialog;


public class MainActivity extends ActionBarActivity {

    CallbackManager callbackManager;

    ShareDialog shareDialog;

   /* private FacebookCallback<Sharer.Result> shareCallback=new FacebookCallback<Sharer.Result>() {
        @Override
        public void onSuccess(Sharer.Result result) {

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

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

       // shareDialog= new ShareDialog(this);
       //shareDialog.registerCallback(callbackManager,hareCallback);
        setContentView(R.layout.activity_main);
        actualizarUI();

    }

    private void actualizarUI() {
        Log.d("FacebookApp","actualizarUI");
        boolean habiliarBotones= AccessToken.getCurrentAccessToken()!=null;
        Profile profile = Profile.getCurrentProfile();

        if(habiliarBotones && profile!=null){
            Log.d("FacebookApp",""+profile.getFirstName());

        }else{
            Log.d("FacebookApp","Aun no autenticado");
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
        callbackManager.onActivityResult(requestCode,resultCode,data);

    }
}
