package com.example.dictionaryapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class YourWordsFragment extends Fragment {
    private FragmentListener listener;

    public YourWordsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_words, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Button btn = (Button)view.findViewById(R.id.btn_detail);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(listener!=null)
//                    listener.onItemClick(value);
//            }
//        });

        ListView yourwordsList =   view.findViewById(R.id.yourwordsList);
        final YourWordsAdapter adapter = new YourWordsAdapter(getActivity(),getListOfWords());
        yourwordsList.setAdapter(adapter);

        adapter.setOnItemClick(new ListItemListener() {
            @Override
            public void onItemClick(int position) {
                if(listener!=null)
                    listener.onItemClick(String.valueOf(adapter.getItem(position)));
            }
        });

        adapter.setOnItemDeleteClick(new ListItemListener() {
            @Override
            public void onItemClick(int position) {
                String value = String.valueOf(adapter.getItem(position));
                Toast.makeText(getContext(),value + " del roi nha ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setOnFragmentListener(FragmentListener listener){
        this.listener = listener;
    }

    String[] getListOfWords() {
        String[] source = new String[]{
                "a","b","c","d","e","f","g"
                ,"h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
        };
        return source;
    }
}
