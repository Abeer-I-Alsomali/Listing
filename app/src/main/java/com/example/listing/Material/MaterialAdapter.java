package com.example.listing.Material;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listing.Plan.PlanAdapter;
import com.example.listing.PrcButtonClicked;
import com.example.listing.R;
import com.example.listing.databinding.LoadItemCardBinding;
import com.example.listing.models.Material2;
import com.example.listing.notes.pictureMode;
import com.fasterxml.jackson.core.Base64Variants;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;


public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>  {

    public interface loadClick{
        public void loadButtonClicked(int pos);
    }

    public interface  unloadClick{
        public void unloadButtonClick(int pos);
    }

    public interface foundClick{
        public void foundButtonClick(int pos);
    }

public interface addClick{
        public void addButtonClick(int pos);
    }

    public interface cameraClick{
        public void cameraButtonClick(int pos);
    }

    public static ArrayList<Material> materialList;
    loadClick loadListener;
    unloadClick unloadListener;
    addClick addListener;
    foundClick foundListener;
    cameraClick cameraListener;

    private static Context contexts;
    public static Boolean isLoad = true;
    PrcButtonClicked prcButtonListener;
    PlanAdapter planAdapter;

    public MaterialAdapter(ArrayList<Material> materialList){
        this.materialList = materialList;
    }

    public void setLoadListener(loadClick loadListener) {
        this.loadListener = loadListener;
    }

    public void setUnloadListener(unloadClick unloadListener) { this.unloadListener = unloadListener; }

    public void setFoundListener(foundClick foundListener){
        this.foundListener = foundListener;
    }

    public void setAddListener(addClick addListener){
        this.addListener = addListener;
    }


    public void setCameraListener(cameraClick cameraListener){
        this.cameraListener = cameraListener;
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*
        For Load card
         */

        LoadItemCardBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.load_item_card, parent, false);


//        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_item_card, parent, false);
//        MaterialViewHolder request = new MaterialViewHolder(card, loadListener, unloadListener, foundListener,
//                cameraListener, prcListener);


        /*
        FOR ASSIGN
         */
//        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.assign_card, parent, false);
//        MaterialViewHolder request = new MaterialViewHolder(card, addListener);


        contexts = parent.getContext();

        return new MaterialAdapter.MaterialViewHolder(binding);
    }
    public void filterList(ArrayList<Material> materialList){
        this.materialList = materialList;
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
//        final Material material = materialList.get(position);
//        notifyDataSetChanged();

        final Material material = materialList.get(position);
        holder.bind(material);

//        holder.itemRowBinding.setClicklisten(this::PrcButtonClicked);

//        notifyDataSetChanged();
    }

//    private void setAnimation(CardView viewToAnimate, int position){
//        Animation animation = animationUtils.loadAnimation(mContext)
//    }

    @Override
    public int getItemCount() {
        return materialList.size();
    }

    public static class MaterialViewHolder extends RecyclerView.ViewHolder{
        //private final ImageButton imgv;
        TextView materialName, textDriver, textVehicle, textStatus, textQuan, textPlanNum;
        Button loadButton, unloadButton, addButton, foundButton, prcButton;
        Material material;
        ImageView materialImage, camerabut, locButton;
        LoadItemCardBinding itemRowBinding;

        public MaterialViewHolder(LoadItemCardBinding itemRowBinding){
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Material2 material2){

            itemRowBinding.setVariable(3, getAdapterPosition());
            itemRowBinding.setMat(material2);
            itemRowBinding.executePendingBindings();
        }



        /*
        LOAD material view holder
         */
        public MaterialViewHolder(@NonNull View itemView, final loadClick loadListener, final unloadClick unloadListener, final foundClick foundListener, final cameraClick cameraListener) {

            super(itemView);

                materialName = (TextView) itemView.findViewById(R.id.material_name);
                materialImage = (ImageView) itemView.findViewById(R.id.material_img_card);
                textQuan = (TextView) itemView.findViewById(R.id.status_tv);
                textStatus = (TextView) itemView.findViewById(R.id.loadstat);
                loadButton = (Button) itemView.findViewById(R.id.load_button);
                unloadButton = (Button) itemView.findViewById(R.id.unload_button);
                foundButton = (Button) itemView.findViewById(R.id.found_button);
                locButton = (ImageView) itemView.findViewById(R.id.location_btn);
                camerabut = (ImageView) itemView.findViewById(R.id.comment_btn);
                prcButton = (Button) itemView.findViewById(R.id.process_button);





        /*
        Set load onclick listener
         */
                loadButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
//                        materialList.get(getAdapterPosition()).setLoaded(true);
//                        materialList.get(getAdapterPosition()).setFound(true);
//                        materialList.get(getAdapterPosition()).setPrc(false);

//                       loadListener.loadButtonClicked(getAdapterPosition());
//                        Log.i("click","load button clicked");
                       loadListener.loadButtonClicked(getAdapterPosition());


                    }

                });
        /*
        Set Unload onclick listener
         */
                unloadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        unloadListener.unloadButtonClick(getAdapterPosition());
                    }
                });
        /*
        Set found onclick listener
         */
                foundButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        foundListener.foundButtonClick(getAdapterPosition());
                    }
                });


            camerabut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cameraListener.cameraButtonClick(getAdapterPosition());
                }
            });

//            prcButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    prcListener.PrcButtonClicked(getAdapterPosition());
//                }
//            });
        }

        void bind(Material material) {

            this.material = material;
            locButton.setOnClickListener(view -> {
                        Uri location = Uri.parse("geo:0,0?q=27.776278,48.875420");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                        contexts.startActivity(mapIntent);
                    }
            );


        /*
        FOR LOADING Setting the status text for loading (for loading).
         */
       // if(isLoad){
            if (material.getLoaded()) {
                textStatus.setText("Loaded");
                textStatus.setBackground(ContextCompat.getDrawable(contexts, R.drawable.green_border));
            } else if (material.getPrc()) {
                textStatus.setText("Processing");
                textStatus.setBackground(ContextCompat.getDrawable(contexts, R.drawable.yellow_border));
            }else if (material.getFound()) {
                textStatus.setText("Not Loaded");
                textStatus.setBackground(ContextCompat.getDrawable(contexts, R.drawable.red_border));
            }  else {
                textStatus.setText("Not Found");
                textStatus.setBackground(ContextCompat.getDrawable(contexts, R.drawable.red_border));
          //  }
        }
        /*
        for ASSIGN status
         */
        if(!isLoad) {
            textDriver.setText(material.getDriver());
            textVehicle.setText(material.getVehicle());
           if(material.getDriver().isEmpty() && material.getVehicle().isEmpty()){
                textStatus.setText("Not Assigned");
                textStatus.setBackground(ContextCompat.getDrawable(contexts, R.drawable.red_border));

            } else{
                textStatus.setText("Assigned");
                textStatus.setBackground(ContextCompat.getDrawable(contexts, R.drawable.green_border));
            }

        }
        /*
        For both
         */
            Drawable image = null;
            if(material.getMaterial().length() > 100) {
                ByteArrayInputStream stream = new ByteArrayInputStream(Base64Variants.getDefaultVariant().decode(material.getMaterial()));
                image = BitmapDrawable.createFromStream(
                       stream, "");
                materialImage.setBackground(image);
            }
            else
                materialImage.setImageBitmap(material.getBmpImage());


            Drawable finalImage = image;
            materialImage.setOnClickListener(view -> {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                pictureMode myFragment = new pictureMode(finalImage);
                activity.getSupportFragmentManager().beginTransaction().add(myFragment,"Picture").commit();


            }
);
            materialName.setText(material.getName());
            textQuan.setText(material.getQuan());


        }

    }




}


