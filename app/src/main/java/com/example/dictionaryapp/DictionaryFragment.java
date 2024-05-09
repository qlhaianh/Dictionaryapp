package com.example.dictionaryapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class DictionaryFragment extends Fragment {
    private FragmentListener listener;
    ArrayAdapter<String> adapter;
    ListView dicList;

    public DictionaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Button btn = (Button)view.findViewById(R.id.btn_detail);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(listener!=null)
//                listener.onItemClick(value);
//            }
//        });

        dicList = view.findViewById(R.id.dictionaryList);
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,getListOfWords());
        dicList.setAdapter(adapter);
        dicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listener!=null)
                    listener.onItemClick(getListOfWords() [position]);
            }
        });

    }

    public void resetDataSource(String[] source)
    {
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,source);
        dicList.setAdapter(adapter);
    }

    public void filterValue(String value){
        // adapter.getFilter().filter(value);
        int size = adapter.getCount();
        for(int i=0;i<size;i++)
        {
            if(adapter.getItem(i).startsWith(value))
            {
                dicList.setSelection(i);
                break;
            }
        }
    }
    String[] getListOfWords() {
        String[] source = new String[]{
                "a","b","c","d","e","f","g"
                ,"h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
        };
        return source;
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
}
