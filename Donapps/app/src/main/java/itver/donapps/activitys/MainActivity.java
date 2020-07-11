
package itver.donapps.activitys;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import itver.donapps.R;
import itver.donapps.models.SliderAdapter;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener

{
    //UI ELEMENTS
    private ViewPager viewPagerSlider;
    private Button buttonLogin;
    private Button buttonRegister;
    private TextView[]dots;

    //ADAPTER
    private SliderAdapter sliderAdapter;
    private LinearLayout dotsLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents(){
        viewPagerSlider = (ViewPager) findViewById(R.id.viewPagerSlider);
        dotsLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        viewPagerSlider.setAdapter(sliderAdapter);
        viewPagerSlider.addOnPageChangeListener(this);
        addDotsOnViewPager(0);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonLogin.setEnabled(false);
        buttonLogin.setVisibility(View.INVISIBLE);
        buttonRegister.setEnabled(false);
        buttonRegister.setVisibility(View.INVISIBLE);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }



    private void login(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void register(){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void addDotsOnViewPager(int position){
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i< dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparent));
            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0 )
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            addDotsOnViewPager(position);
            if(position < 3){
                buttonLogin.setEnabled(false);
                buttonLogin.setVisibility(View.INVISIBLE);
                buttonRegister.setEnabled(false);
                buttonRegister.setVisibility(View.INVISIBLE);
            }else{
                buttonLogin.setEnabled(true);
                buttonLogin.setVisibility(View.VISIBLE);
                buttonRegister.setEnabled(true);
                buttonRegister.setVisibility(View.VISIBLE);
            }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
