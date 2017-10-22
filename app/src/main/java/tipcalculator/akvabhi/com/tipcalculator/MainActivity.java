package tipcalculator.akvabhi.com.tipcalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity{
    private AlertDialog.Builder ad;
    private EditText enteredAmount;
    private SeekBar seekBar;
    private Button button;
    private TextView resultText;
    private TextView tipPercent;
    private int seekBarPercent;
    private float enteredBillAmount;
    private Button exitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredAmount = (EditText)findViewById(R.id.billAmountId);
        seekBar = (SeekBar)findViewById(R.id.seekbarId);
        button = (Button)findViewById(R.id.calculateButton);
        resultText = (TextView)findViewById(R.id.resultId);
        tipPercent = (TextView)findViewById(R.id.tipPercentId);
        exitbutton = (Button) findViewById(R.id.exitId);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
        calculate();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipPercent.setText(String.valueOf(seekBar.getProgress()) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            resultText.setText("");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            seekBarPercent = seekBar.getProgress();
            }
        });

        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad = new AlertDialog.Builder(MainActivity.this);
               // ad.setIcon(R.drawable.TipCalculatorAppIcon);
                ad.setTitle(getResources().getString(R.string.exitTitle));
                ad.setMessage(getResources().getString(R.string.exitMessage));
                ad.setCancelable(false);
                ad.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                });
                ad.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog dialog = ad.create();
                dialog.show();
            }
        });


    }
    public void calculate() {
        float TipAmount = 0.0f;
        if (!enteredAmount.getText().toString().equals(""))
        {
            enteredBillAmount = Float.parseFloat(enteredAmount.getText().toString());
            TipAmount = enteredBillAmount*seekBarPercent/100;
            if (seekBarPercent==0)
                Toast.makeText(this, "Tip not selected!!", Toast.LENGTH_LONG).show();
            resultText.setText("Tip/Total : " + String.format("%.2f",TipAmount)+"/"+String.format("%.2f",(TipAmount+enteredBillAmount)));
        }else
            Toast.makeText(MainActivity.this, "You Forgot to enter the amount", Toast.LENGTH_LONG).show();
    }


}
