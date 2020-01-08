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

import com.sabin.foodmandu.URL.url;
import com.sabin.foodmandu.adapter.categoriesAdapter;
import com.sabin.foodmandu.R;
import com.sabin.foodmandu.api.super7API;
import com.sabin.foodmandu.model.Categories;
import com.sabin.foodmandu.model.super7;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.graphics.Color.RED;

public class HomeFragment extends Fragment {
    CarouselView carouselView;
    private TextView tvSuper7;
private RecyclerView categoryRecycleView;
    int[] sampleImages = {R.drawable.ad1, R.drawable.ad2};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_home, container,false);
     //advertisement
      carouselView = view.findViewById(R.id.carouselView);
        imageSlider();

        //category
      categoryRecycleView = view.findViewById(R.id.CategoryrecyclerView);
        CategoryRecycle();

        //super7
      tvSuper7 = view.findViewById(R.id.tvsuper7);
      super7();

      return view;
    }

    private void super7() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        super7API super7API = retrofit.create(super7API.class);

        Call<List<super7>> ListCall = super7API.getSuper7();
        super7API super7API1 = url.getInstance().create(super7API.class);

        ListCall.enqueue(new Callback<List<super7>>() {

            @Override
            public void onResponse(Call<List<super7>> call, Response<List<super7>> response) {
                if(!response.isSuccessful()){
                    tvSuper7.setText(" Code : " + response.code());
                    return;
                }
                List<super7> super7List = response.body();

                for (super7 super7 : super7List ){
                    String content = " ";
                    content +=  super7.getImage() + "\n";
                    content +=  (super7.getName()+ "\n") ;
                    content +=  super7.getLocation()+ "\n";

                    tvSuper7.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<super7>> call, Throwable t) {
    tvSuper7.setText("Error" + t.getMessage());
            }
        });




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