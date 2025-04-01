package com.example.inventorymanagement;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;
import java.util.UUID;

public class AddItemActivity extends AppCompatActivity {
    private EditText itemName, itemQuantity, itemPrice;
    private FirebaseFirestore db; // Declare db as a class variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_item);

        db = FirebaseFirestore.getInstance(); // Initialize db

        itemName = findViewById(R.id.itemName);
        itemQuantity = findViewById(R.id.itemQuantity);
        itemPrice = findViewById(R.id.itemPrice);
        Button saveItemButton = findViewById(R.id.saveItemButton);

        saveItemButton.setOnClickListener(v -> addItemToFirestore());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addItemToFirestore() {
        try {
            String name = itemName.getText().toString();
            String quantityStr = itemQuantity.getText().toString();
            String priceStr = itemPrice.getText().toString();
            String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

            if (name.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);
            String itemId = UUID.randomUUID().toString(); // Generate a unique ID for the item

            Item item = new Item(itemId, name, quantity, price, userId); // Create the item object

            db.collection("Inventory").document(itemId).set(item)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Failed to add item", Toast.LENGTH_SHORT).show());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}