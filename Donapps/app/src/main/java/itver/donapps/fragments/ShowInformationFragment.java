package itver.donapps.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import itver.donapps.R;

public class ShowInformationFragment extends Fragment {



    public ShowInformationFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_information, container, false);

        TextView title = (TextView) v.findViewById(R.id.textViewTitleOption);
        TextView content = (TextView) v.findViewById(R.id.textViewContentOption);
        Bundle info = getArguments();
        if(info!=null){
            String titleOption = info.getString("title");
            String contentOption = info.getString("content");
            title.setText(titleOption);
            content.setText(contentOption);
        }
        return v;
    }
}
