package com.example.usuario.products.model;

/**
 * Created by Usuario on 23/11/2017.
 */

public interface OnProductClickListener {
    /**
     * Abre el detalle del servicio en la posición dada
     * @param position
     */
    void goToProductDetailActivity(int position);

    /**
     * Se ejecuta cuando se da clic en el botón para relanzar un servicio en la lista de servicios activos
     * @param position
     */
    void showQuantity(int position);
}