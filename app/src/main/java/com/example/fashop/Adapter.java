    package com.example.fashop;

    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.bumptech.glide.Glide;
    import com.bumptech.glide.load.engine.DiskCacheStrategy;
    import com.bumptech.glide.request.RequestOptions;
    import com.squareup.picasso.Picasso;

    import java.util.List;

    import androidx.annotation.NonNull;
    import androidx.cardview.widget.CardView;
    import androidx.constraintlayout.widget.ConstraintLayout;
    import androidx.recyclerview.widget.RecyclerView;

    import static androidx.core.content.ContextCompat.startActivity;


    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private List<ProductModel> productModelList;
        Context context;

        public Adapter(List<ProductModel>productModelList, Context context){
            this.productModelList=productModelList;
            this.context=context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_product_grid, viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);



            Glide.with(this.context)
              .load(productModelList.get(i)
              .getProductImage()).apply(options)
              .into(viewHolder.ImgproductImage);
//            Picasso.get().
//                    load(productModelList.get(i).getProductImage())
//                    .placeholder(R.drawable.logo)
//                    .into(viewHolder.ImgproductImage);
            //viewHolder.ImgproductImage.setImageResource(productModelList.get(i).getProductImage());
            viewHolder.txtprice.setText(productModelList.get(i).getPrice());

            viewHolder.txtproductName.setText(productModelList.get(i).getProductName());
            viewHolder.txtlocation.setText(productModelList.get(i).getLocation());
            viewHolder.txtseller.setText(productModelList.get(i).getSeller());

            viewHolder.parentLayout.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    String productName = productModelList.get(i).getProductName();
                    Toast.makeText(context, productName + " is selected", Toast.LENGTH_SHORT).show();
                    view.getContext().startActivity(new Intent(view.getContext(), ProductDetailActivity.class));
                }
            });

        }

        @Override
        public int getItemCount() {
            return productModelList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView txtproductName, txtlocation, txtseller, txtprice;
            ImageView ImgproductImage;
            CardView parentLayout;
            public ViewHolder(@NonNull View itemView){
                super(itemView);
                ImgproductImage = itemView.findViewById(R.id.itemCardImage);
                txtproductName = itemView.findViewById(R.id.itemCardTitle);
                txtlocation = itemView.findViewById(R.id.location);
                txtseller = itemView.findViewById(R.id.seller);
                txtprice = itemView.findViewById(R.id.price);
                parentLayout = itemView.findViewById(R.id.cardProduct);
            }
        }
    }
