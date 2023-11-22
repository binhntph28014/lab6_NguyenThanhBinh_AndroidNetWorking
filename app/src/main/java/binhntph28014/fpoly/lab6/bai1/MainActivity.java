package binhntph28014.fpoly.lab6.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import binhntph28014.fpoly.lab6.R;
import binhntph28014.fpoly.lab6.bai1.Constants;
import binhntph28014.fpoly.lab6.bai1.ConvertTemperatureTask;

public class MainActivity extends AppCompatActivity  {
    EditText edtFC;
    Button btnF, btnC;
    String strFC;
    TextView tvResult;
    int convertStyle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtFC = (EditText) findViewById(R.id.edtF_C);
        btnF = (Button) findViewById(R.id.btnF);
        btnC = (Button) findViewById(R.id.btnC);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invokeAsyncTask("Fahrenheit", Constants.F_TO_C_SOAP_ACTION, Constants.F_TO_C_METHOD_NAME);
                convertStyle = 1;

            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invokeAsyncTask("Celsius", Constants.C_TO_F_SOAP_ACTION, Constants.C_TO_F_METHOD_NAME);
                convertStyle = 0;
            }
        });
    }

    //create and execute asynctask to get response from W3school server
    private void invokeAsyncTask(String convertParams, String soapAction,
                                 String methodName) {
        new ConvertTemperatureTask(this, soapAction, methodName,
                convertParams).execute(edtFC.getText()
                .toString().trim());
    }
    //call back data from background thread and set to views
    public void callBackDataFromAsyncTask(String result) {
        try {
            double resultTemperature = Double.parseDouble(result); //parseString to double
            if (convertStyle == 0) {// C degree to F degree
                tvResult.setText(edtFC.getText().toString().trim() + " Độ C = " + String.format("%.2f", resultTemperature) + " độ F");
            } else {// F degree to C degree
                tvResult.setText(edtFC.getText().toString().trim() + " Độ F = " + String.format("%.2f", resultTemperature) + " độ C");
            }

        }catch (Exception exception){
            tvResult.setText("Chỉ nhập dưới dạng số");
        }
    }
}