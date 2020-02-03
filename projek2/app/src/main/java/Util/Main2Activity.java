package Util;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.projek2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class Main2Activity extends AppCompatActivity {

    String nim,nama,jk,peminatan,alamat,agama,email;
    EditText txt_nim,txt_nama,txt_;
    int success;
    Adapter adapter;

    private static final String TAG = MainActivity.class.getSimpleName();

    private static String url_insert     = Server.URL + "input.php";

    public static final String TAG_NIM      = "nim";
    public static final String TAG_NAMA     = "nama";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    @BindView(R.id.jenis)
    RadioGroup jenis;
    @BindView(R.id.laki)
    RadioButton laki;
    @BindView(R.id.perem)
    RadioButton perem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_biodata);
    }

    public void kembali(View v){
        Intent i = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(i);
    }
    private void kosong(){
        txt_nim.setText(null);
        txt_nama.setText(null);
    }


    StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {

        @Override
        public void onResponse(String response) {
            Log.d(TAG, "Response: " + response.toString());

            try {
                JSONObject jObj = new JSONObject(response);
                success = jObj.getInt(TAG_SUCCESS);

                // Cek error node pada json
                if (success == 1) {
                    Log.d("Add", jObj.toString());

                    kosong();

                    Toast.makeText(Main2Activity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    ((BaseAdapter)adapter).notifyDataSetChanged();

                } else {
                    Toast.makeText(Main2Activity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                // JSON error
                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "Error: " + error.getMessage());
            Toast.makeText(Main2Activity.this, error.getMessage(), Toast.LENGTH_LONG).show();
        }
    }) {

        @Override
        protected Map<String, String> getParams() {
            // Posting parameters ke post url
            Map<String, String> params = new HashMap<String, String>();
            // jika id kosong maka simpan, jika id ada nilainya maka update
            params.put("nim", nim);
            params.put("nama", nama);
//                int selectedId = jenis.getCheckedRadioButtonId();
//                if (selectedId == laki.getId()){
//                    params.put("laki", laki.getText().toString());
//                }else{
//                    params.put("perem", perem.getText().toString());
//                }

            Intent a = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(a);
            return params;
        }

    };

}
