package com.example.usuario.products.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.usuario.products.model.OnProductClickListener;
import com.example.usuario.products.R;
import com.example.usuario.products.model.Product;

import java.util.ArrayList;
import java.util.List;



//public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
public class ProductsAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Product> items;
    protected OnProductClickListener listener;

    public ProductsAdapter (Activity activity, ArrayList<Product> items,OnProductClickListener listener) {
        this.activity = activity;
        this.items = items;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Product> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.card_product, null);
        }


        Product product = items.get(position);

        if (product.isActive()){
            TextView textTitle = (TextView)v.findViewById(R.id.txt_title_product);
            textTitle.setText(product.getName());

            TextView textId = (TextView)v.findViewById(R.id.txt_id_product);
            textId.setText(String.valueOf(product.getId()));

            TextView textSize = (TextView)v.findViewById(R.id.txt_size_product);
            textSize.setText(product.getSize());

            TextView textDes = (TextView)v.findViewById(R.id.txt_des_product);
            textDes.setText(product.getDescription());

            Button btnQuantity = (Button)v.findViewById(R.id.btn_quantity);


            LinearLayout lCardProductItem = (LinearLayout)v.findViewById(R.id.l_card_product);

            btnQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.showQuantity(position);
                }
            });

            lCardProductItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.goToProductDetailActivity(position);
                }
            });
        }

        return v;
    }


/*
    /**BaseAdapter


     /**
     * El contexto de la aplicación
     */
    private Context context;

    /**
     * La lista de productos
     */
    private List<Product> products;

    /**
     * El listener que se llamará cada vez que se hace un clic sobre una tarjeta
     */
    private OnProductClickListener onActiveTaskClickListener;


    /**
     * Constructor que inicializa las variables
     */
  /*  public ProductsAdapter(Context context, List<Product> products, OnProductClickListener onActiveTaskClickListener) {
        this.context = context;
        this.products = products;
        this.onActiveTaskClickListener = onActiveTaskClickListener;

     //   setHasStableIds(true);
    }

    /**
     * Crea el view holder para cada uno de los items
     *
     * @param parent
     * @param viewType
     * @return
     */
 /*   @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onActiveTaskClickListener);
        return viewHolder;
    }

    /**
     * Inicializa los campos de un item
     *
     * @param viewHolder
     * @param position
     */
 /*   @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Product product = products.get(position);


        viewHolder.txtTitleProduct.setText(product.getName());
        viewHolder.txtDescProduct.setText(product.getDescription());
        viewHolder.txtIdProduct.setText(product.getId());
        viewHolder.txtSizeProduct.setText(product.getSize());

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        //@BindView(R.id.txt_title_product)
        TextView txtTitleProduct;

        @BindView(R.id.txt_des_product)
        TextView txtDescProduct;

        @BindView(R.id.txt_id_product)
        TextView txtIdProduct;

        @BindView(R.id.txt_size_product)
        TextView txtSizeProduct;

        @BindView(R.id.btn_quantity)
        Button btnQuantity;

        @BindView(R.id.l_card_product)
        LinearLayout lCardProductItem;


        OnProductClickListener listener;

        public ViewHolder(View itemView, OnProductClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;

            txtTitleProduct = (TextView) itemView.findViewById(R.id.txt_title_product);
        }

        @OnClick(R.id.l_card_product)
        public void showTaskDetail() {
            listener.goToProductDetailActivity(getAdapterPosition());
        }

        @OnClick(R.id.btn_quantity)
        public void showQuantity() {
            listener.showQuantity(getAdapterPosition());
        }
    }*/
}
