package com.example.inventorymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditItemActivity extends AppCompatActivity {
    private EditText editName, editQuantity, editPrice;
    private Button btnUpdate, btnDelete;
    private FirebaseFirestore db;
    private String itemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_item);

        // Initialize UI elements
        editName = findViewById(R.id.edit_item_name);
        editQuantity = findViewById(R.id.edit_item_quantity);
        editPrice = findViewById(R.id.edit_item_price);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        db = FirebaseFirestore.getInstance();

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            itemId = intent.getStringExtra("itemId");
            editName.setText(intent.getStringExtra("itemName"));
            editQuantity.setText(String.valueOf(intent.getIntExtra("itemQuantity", 0)));
            editPrice.setText(String.valueOf(intent.getDoubleExtra("itemPrice", 0.0)));
        }

        // Update item in Firestore
        btnUpdate.setOnClickListener(v -> updateItem());

        // Delete item from Firestore
        btnDelete.setOnClickListener(v -> deleteItem());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void updateItem() {
        String name = editName.getText().toString().trim();
        String quantityStr = editQuantity.getText().toString().trim();
        String priceStr = editPrice.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(quantityStr) || TextUtils.isEmpty(priceStr)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityStr);
        double price = Double.parseDouble(priceStr);

        DocumentReference itemRef = db.collection("Inventory").document(itemId); // Changed from "items" to "Inventory"
        itemRef.update("name", name, "quantity", quantity, "price", price)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(EditItemActivity.this, "Item updated successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(EditItemActivity.this, "Update failed!", Toast.LENGTH_SHORT).show());
    }

    private void deleteItem() {
        DocumentReference itemRef = db.collection("Inventory").document(itemId); // Changed from "items" to "Inventory"
        itemRef.delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(EditItemActivity.this, "Item deleted successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(EditItemActivity.this, "Deletion failed!", Toast.LENGTH_SHORT).show());
    }
}