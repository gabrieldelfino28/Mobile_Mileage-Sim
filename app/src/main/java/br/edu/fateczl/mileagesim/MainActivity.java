package br.edu.fateczl.mileagesim;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputConsumoMedio;
    private EditText inputLitros;
    private Button btnCalc;
    private TextView outputAutonomia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inputConsumoMedio = findViewById(R.id.inputConsumo);
        inputConsumoMedio.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        inputLitros = findViewById(R.id.inputLitros);
        inputLitros.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        outputAutonomia = findViewById(R.id.outputAutonomia);
        outputAutonomia.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(op -> ComputeMileages());
    }

    private void ComputeMileages() {
        //15 Km/l 15 Km por 1 Litro
        //60 l no tanque, logo 15 * 60 = 900km, portanto 900000 metros são percorridos até o combustível acabar
        try {
            int consumoMedio = Integer.parseInt(inputConsumoMedio.getText().toString());
            int litrosTanque = Integer.parseInt(inputLitros.getText().toString());
            int mileage = (consumoMedio * litrosTanque) * 100;
            String calc = String.format(getString(R.string.output_autonomia), mileage);
            outputAutonomia.setText(calc);
        }catch (Exception e) {
            outputAutonomia.setText(getString(R.string.erro));
        }
    }
}