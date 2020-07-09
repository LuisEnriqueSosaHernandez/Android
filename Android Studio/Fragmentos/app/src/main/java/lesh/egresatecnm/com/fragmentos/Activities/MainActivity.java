package lesh.egresatecnm.com.fragmentos.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lesh.egresatecnm.com.fragmentos.Fragments.DataFragment;
import lesh.egresatecnm.com.fragmentos.Fragments.DetailsFragment;
import lesh.egresatecnm.com.fragmentos.R;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener{
    private boolean isMultiPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMultiPanel();
    }

    @Override
    public void sendData(String text) {
        if (isMultiPanel) {
        //LLamar al metodo renderizar texto de el details fragments
        //pasandole el texto que recibimos por parametro en este mismo metodo
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        detailsFragment.RenderText(text);
    }else{
            Intent intent=new Intent(this,DetailsActivity.class);

            intent.putExtra("text",text);
            startActivity(intent);
    }
    }
    private void setMultiPanel(){
        isMultiPanel=(getSupportFragmentManager().findFragmentById(R.id.detailsFragment)!=null);
    }
}
