package com.heroes.lesh.kidneys.FragmentsFoodRules.FragmentsFoodRulesFoods;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heroes.lesh.kidneys.R;
import com.squareup.picasso.Picasso;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodRulesFoodFragment extends Fragment {
    //Variables globales
    private View view;
    private Bundle bundle;
    private Unbinder unbinder;
    @BindView(R.id.floatingFoodRulesFood)
    FloatingActionButton floatingFoodRulesFood;
    @BindView(R.id.imageViewFoodRulesFood)
    ImageView imageViewFoodRulesFood;
    @BindView(R.id.textViewNameFoodRulesFood)
    TextView textViewNameFoodRulesFood;
    @BindView(R.id.textViewDescriptionFoodRulesFood)
    TextView textViewDescriptionFoodRulesFood;
    @BindView(R.id.textViewCategoryFoodRulesFood)
    TextView textViewCategoryFoodRulesFood;
    @BindView(R.id.textViewPorcionFoodRulesFood)
    TextView textViewPorcionFoodRulesFood;
    @BindView(R.id.textViewSodioFoodRulesFood)
    TextView textViewSodioFoodRulesFood;
    @BindView(R.id.textViewPotasioFoodRulesFood)
    TextView textViewPotasioFoodRulesFood;
    @BindView(R.id.textViewFosforoFoodRulesFood)
    TextView textViewFosforoFoodRulesFood;
    @BindView(R.id.layoutFoodRulesFoods)
    LinearLayout layoutFoodRulesFoods;
    @BindDrawable(R.drawable.loading)
    Drawable loadingImage;
    @BindDrawable(R.drawable.error)
    Drawable errorImage;
    @BindDrawable(R.drawable.ic_harmful)
    Drawable ic_harmful;
    @BindDrawable(R.drawable.ic_healthy)
    Drawable ic_healthy;
    @BindDrawable(R.drawable.ic_medium)
    Drawable ic_medium;
    @BindString(R.string.muyrecomendado)
    String muyrecomendado;
    @BindString(R.string.pocorecomendado)
    String pocorecomendado;
    @BindString(R.string.norecomendado)
    String norecomendado;
    @BindString(R.string.contiene)
    String contiene;
    @BindColor(R.color.colorVerde)
    int colorVerde;
    @BindColor(R.color.colorMarron)
    int colorMarron;
    @BindColor(R.color.colorRojo)
    int colorRojo;
    private TextView txtToast;
    private ImageView imgToast;
    private Toast toast;
    private LayoutInflater layoutInflater;
    private View viewToast;

    public FoodRulesFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_food_rules_food, container, false);
        unbinder = ButterKnife.bind(this, view);
        inicializarToast();
        bundle = getArguments();
        if (bundle != null) {
            Picasso.get().load(bundle.getString("Image")).fit().placeholder(loadingImage).error(errorImage).into(imageViewFoodRulesFood);
            textViewNameFoodRulesFood.setText(bundle.getString("Name"));
            textViewDescriptionFoodRulesFood.append("\n" + bundle.getString("Description"));
            textViewCategoryFoodRulesFood.append("\n" + bundle.getString("Category"));
            switch (bundle.getString("HealthRangeName")) {
                case "Healthy":
                    floatingFoodRulesFood.setImageDrawable(ic_healthy);
                    textViewNameFoodRulesFood.setBackgroundColor(colorVerde);
                    layoutFoodRulesFoods.setBackgroundColor(colorVerde);
                    break;
                case "Medium":
                    floatingFoodRulesFood.setImageDrawable(ic_medium);
                    textViewNameFoodRulesFood.setBackgroundColor(colorMarron);
                    layoutFoodRulesFoods.setBackgroundColor(colorMarron);
                    break;
                case "Harmful":
                    floatingFoodRulesFood.setImageDrawable(ic_harmful);
                    textViewNameFoodRulesFood.setBackgroundColor(colorRojo);
                    layoutFoodRulesFoods.setBackgroundColor(colorRojo);
                    break;
            }
            textViewPorcionFoodRulesFood.append(" "+bundle.getString("Porcion") + " " + contiene + ":");
            textViewSodioFoodRulesFood.append("\n" + bundle.getString("Sodio"));
            textViewPotasioFoodRulesFood.append("\n" + bundle.getString("Potasio"));
            textViewFosforoFoodRulesFood.append("\n" + bundle.getString("Fosforo"));
        }
            floatingFoodRulesFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bundle != null) {
                        switch (bundle.getString("HealthRangeName")) {
                            case "Healthy":
                                changeToast(muyrecomendado, ic_healthy);
                                toast.show();
                                break;
                            case "Medium":
                                changeToast(pocorecomendado, ic_medium);
                                toast.show();
                                break;
                            case "Harmful":
                                changeToast(norecomendado, ic_harmful);
                                toast.show();
                                break;
                        }
                    }
                }
            });
        return view;
    }

    //Metodo para inicializar el toast
    private void inicializarToast() {
        layoutInflater = getActivity().getLayoutInflater();
        viewToast = layoutInflater.inflate(R.layout.custom_toast, null);
        txtToast = viewToast.findViewById(R.id.txtToast);
        imgToast = viewToast.findViewById(R.id.imgToast);
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(viewToast);
    }

    private void changeToast(String text, Drawable imgToastIcono) {
        txtToast.setText(text);
        imgToast.setImageDrawable(imgToastIcono);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
