package com.example.usuario.products.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.usuario.products.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.txt_title_product)
    TextView txtTitleProduct;

    @BindView(R.id.txt_des_product)
    TextView txtDescProduct;

    @BindView(R.id.txt_id_product)
    TextView txtIdProduct;

    @BindView(R.id.txt_size_product)
    TextView txtSizeProduct;

    @BindView(R.id.txt_quantity)
    TextView txtQueantity;

    @BindView(R.id.img_detail)
    ImageView imgDetail;


    public static final String TITLE_PRODUCT = "TITLE";

    public static final String DESC_PRODUCT = "DESC";

    public static final String SIZE_PRODUCT = "SIZE";

    public static final String QUANTITY_PRODUCT = "QUANTITY";

    public static final String ID_PRODUCT = "ID";

    private static final int CAMERA_REQUEST = 1888;

    private static final int MY_REQUEST_PERMISSION = 121;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        String title = intent.getStringExtra(TITLE_PRODUCT);
        String desc = intent.getStringExtra(DESC_PRODUCT);
        String size = intent.getStringExtra(SIZE_PRODUCT);
        String quantity = intent.getStringExtra(QUANTITY_PRODUCT);
        String id = intent.getStringExtra(ID_PRODUCT);

        txtTitleProduct.setText(title);
        txtDescProduct.setText(desc);
        txtSizeProduct.setText(size);
        txtQueantity.setText(quantity);
        txtIdProduct.setText(id);

        imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions();
            }
        });
    }

    private void requestPermissions(){
        if (ContextCompat.checkSelfPermission(DetailActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED  ) {
            ActivityCompat.requestPermissions(DetailActivity.this,
                    new String[]{Manifest.permission.CAMERA}, MY_REQUEST_PERMISSION);
        } else {
            goCameraActivity();
        }
    }

    public void goCameraActivity(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgDetail.setImageBitmap(photo);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_REQUEST_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goCameraActivity();
                }
                return;
            }
        }
    }

}
