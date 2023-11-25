import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import com.tuempresa.ventaautos.database.CarDatabaseHelper;
import com.tuempresa.ventaautos.model.Car;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private CarDatabaseHelper dbHelper;
    private CarDatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new CarDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);

        // Configurar el RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carAdapter = new CarAdapter(getAllCars());
        recyclerView.setAdapter(carAdapter);

        
        

        

        Button btnInsertSampleData = findViewById(R.id.btnInsertSampleData);
        btnInsertSampleData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertSampleData();
            }
        });

        // Insertar datos de muestra al iniciar la aplicación
        insertSampleData();
    }

    public void insertSampleData() {
        // Crea o abre la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Inserta algunos autos de muestra
        insertCar(db, "Toyota", "Corolla", 2020, 20000.00);
        insertCar(db, "Honda", "Civic", 2021, 22000.00);
        insertCar(db, "Ford", "Mustang", 2019, 35000.00);
        insertCar(db, "Chevrolet", "Camaro", 2022, 38000.00);
        insertCar(db, "Nissan", "Altima", 2020, 23000.00);

        // Cierra la base de datos
        db.close();
    }

    private void insertCar(SQLiteDatabase db, String brand, String name, int year, double price) {
        ContentValues values = new ContentValues();
        values.put(CarDatabaseHelper.COLUMN_BRAND, brand);
        values.put(CarDatabaseHelper.COLUMN_NAME, name);
        values.put(CarDatabaseHelper.COLUMN_YEAR, year);
        values.put(CarDatabaseHelper.COLUMN_PRICE, price);

        // Inserta el auto en la tabla
        db.insert(CarDatabaseHelper.TABLE_CARS, null, values);
    }

    // Resto del código...
}
