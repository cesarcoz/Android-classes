package coz.cesar.clases.restclientexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private TextView lblResponse;
    private EditText txtUsername;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblResponse = (TextView) findViewById(R.id.lblResult);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
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

    public void callRestAPI(View view) {
        TaskGetRest task = new TaskGetRest();
        task.execute();
    }

    private class TaskGetRest extends AsyncTask<String, Integer, Response> {
        @Override
        protected Response doInBackground(String... params) {
            boolean result = true;
            Response response = new Response();
            HttpClient client = new DefaultHttpClient();
            String credentials = txtUsername.getText().toString()+":"+txtPassword.getText().toString();
            String b64Credentials = Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);
            HttpGet get = new HttpGet("http://10.0.2.2:8080/demorest/rest/hello/"+txtUsername.getText().toString());
            get.setHeader("Accept", "text/plain");
            get.setHeader("Authorization", "Basic "+b64Credentials);

            try {
                HttpResponse httpResponse = client.execute(get);
                String resultStr = EntityUtils.toString(httpResponse.getEntity());
                response.setResponse(resultStr);
                Log.d("RESPONSE:: ", "response: " + resultStr);
            } catch (IOException e) {
                e.printStackTrace();
                result = false;
            }

            response.setResult(result);
            return response;
        }

        @Override
        protected void onPostExecute(Response response) {
            if (response.isResult()) {
                lblResponse.setText(response.getResponse());
            }else{
                lblResponse.setText("Connection error");
            }
        }
    }
}
