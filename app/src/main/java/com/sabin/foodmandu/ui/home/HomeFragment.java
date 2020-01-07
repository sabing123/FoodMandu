package com.sabin.foodmandu.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sabin.foodmandu.adapter.categoriesAdapter;
import com.sabin.foodmandu.R;
import com.sabin.foodmandu.model.Categories;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    CarouselView carouselView;
private RecyclerView categoryRecycleView;
    int[] sampleImages = {R.drawable.ad1, R.drawable.ad2};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_home, container,false);
      carouselView = view.findViewById(R.id.carouselView);
      categoryRecycleView = view.findViewById(R.id.CategoryrecyclerView);
      imageSlider();
      CategoryRecycle();



      return view;
    }

    private void CategoryRecycle() {
        
        List<Categories> categoriesList = new ArrayList<>();
        categoriesList.add(new Categories(R.drawable.resturants));
        categoriesList.add(new Categories(R.drawable.liquors));
        categoriesList.add(new Categories(R.drawable.bakeries));
        categoriesList.add(new Categories(R.drawable.referehment));
        categoriesList.add(new Categories(R.drawable.organic));

        categoriesAdapter categoriesAdapter = new categoriesAdapter(getActivity(), categoriesList);
        categoryRecycleView.setAdapter(categoriesAdapter);
        categoryRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

    }

    private void imageSlider() {

        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), sampleImages[position], Toast.LENGTH_SHORT).show();
            }
        });

    }
}