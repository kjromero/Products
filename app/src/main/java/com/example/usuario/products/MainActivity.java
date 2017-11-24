package com.example.usuario.products;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.usuario.products.activity.DetailActivity;
import com.example.usuario.products.adapters.ProductsAdapter;
import com.example.usuario.products.model.OnProductClickListener;
import com.example.usuario.products.model.Product;
import com.example.usuario.products.model.ResponseProducts;
import com.example.usuario.products.retrofit.ApiService;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements OnProductClickListener {

    /**
     * RecyclerView con la lista de los productos activos
     */
    @BindView(R.id.recycler_view_products)
    ListView recyclerViewProducts;

    @Inject
    ApiService apiService;

    /**
     * Lista de Productos
     */
    private List<Product> listProducts = new ArrayList<>();

    private ArrayList<Product> productsArray;

    /**
     * Diálogo que se está mostrando en este momento.
     */
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getApplicationComponent().inject(this);
        getProdutcs();

      //  recyclerViewProducts = (RecyclerView)findViewById(R.id.recycler_view_products);
    }


    private void getProdutcs() {
        dialog = ProgressDialog.show(this, "Consultando los productos", "Por favor espera...", true);


        Call<ResponseProducts> call = apiService.getProducts();
        call.enqueue(new retrofit2.Callback<ResponseProducts>() {
            @Override
            public void onResponse(Call<ResponseProducts> call, Response<ResponseProducts> response) {
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;

                    showProducts(response.body().getObjectProducts().getProducts());

                }
            }

            @Override
            public void onFailure(Call<ResponseProducts> call, Throwable t) {
                if (dialog != null) {
                    dialog.dismiss();

                }
            }
        });
    }

    public void showProducts(Product[] products){
        productsArray = new ArrayList<Product>();
        for (Product product : products){
               productsArray.add(product);
        }



        ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productsArray,this);

        recyclerViewProducts.setAdapter(adapter);
        //recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void goToProductDetailActivity(int position) {
        Intent goDetail = new Intent(MainActivity.this, DetailActivity.class);
        Product product = productsArray.get(position);
        goDetail.putExtra(DetailActivity.TITLE_PRODUCT,product.getName());
        goDetail.putExtra(DetailActivity.DESC_PRODUCT,product.getDescription());
        goDetail.putExtra(DetailActivity.SIZE_PRODUCT,product.getSize());
        goDetail.putExtra(DetailActivity.QUANTITY_PRODUCT,String.valueOf(product.getQuantity()));
        goDetail.putExtra(DetailActivity.ID_PRODUCT,String.valueOf(product.getId()));
        startActivity(goDetail);
    }

    @Override
    public void showQuantity(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.alert_dialog_quantity);
        Dialog dialog = builder.show();

        TextView txtQuantity = (TextView) dialog.findViewById(R.id.txt_quantity);

        txtQuantity.setText(String.valueOf(productsArray.get(position).getQuantity()));

    }
}
