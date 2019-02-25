package rodriguezfernandez.carlos.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent=getIntent();
        String message="Order:"+intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView=findViewById(R.id.order_textview);
        textView.setText(message);
        //Recupero el spinner desde R.
        Spinner spinner=findViewById(R.id.label_spinner);
        if(spinner!=null){
            //Si existe el spinner, le añadimos como oyente esta misma activity (que tiene los metodos onItemSelected y onNothingSelected
            spinner.setOnItemSelectedListener(this);
        }
        //Crear el adaptador para poner los datos en el spinner.
        //Adaptador par aun array, se le pasan charsecuqence, se crea desde Recurso pasando contexto, array que va a rellenar y layout.
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.labels_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner!=null){
            spinner.setAdapter(adapter);
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked=((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.sameday:
                if (checked) {
                    displayToast(getString(R.string.same_day_messenger_service));
                }
                break;
            case R.id.nextday:
                if (checked) {
                    displayToast(getString(R.string.next_day_ground_delivery));
                }
                break;
            case R.id.pickup:
                if (checked) {
                    displayToast(getString(R.string.pick_up));
                }
                break;
            default:
                //nada
                break;
        }
    }
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    //El metodo recibe una vista adaptador, una vista, una posicion
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Metemos en una cadena
        String spinnerLabel=adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
