package com.example.khmer_musical_instrument;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khmer_musical_instrument.Adapter.MusicalAdapter;
import com.example.khmer_musical_instrument.Adapter.MusicalTypeAdapter;
import com.example.khmer_musical_instrument.Callback.OnRecyclerClickListener;
import com.example.khmer_musical_instrument.Class.ConstantField;
import com.example.khmer_musical_instrument.Class.Musical;
import com.example.khmer_musical_instrument.Class.MusicalType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements OnRecyclerClickListener {

    private FirebaseFirestore db;
    private RecyclerView rvItem;
    private List<MusicalType> musicalTypeList = new ArrayList<>();
    private List<Musical> musicalList;
    MusicalTypeAdapter musicalTypeAdapter;
    private void initView(View view) {
        rvItem = view.findViewById(R.id.rv_main);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        buildItemList(new FirebaseCallbackListItem() {
            @Override
            public void onCallback(List<MusicalType> musicalTypeList)
            {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                musicalTypeAdapter = new MusicalTypeAdapter(musicalTypeList,getActivity(),FirstFragment.this);
                rvItem.setAdapter(musicalTypeAdapter);
                rvItem.setLayoutManager(layoutManager);
            }
        });
    }

    public void buildItemList(final FirebaseCallbackListItem firebaseCallbackList) {
        CollectionReference docRef = db.collection(ConstantField.TYPE_OF_MUSICAL_INSTRUMENT);
        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        MusicalType musicalType = document.toObject(MusicalType.class);
                        buildSubItemList(musicalType, new FirebaseCallbackListSubItem() {
                            @Override
                            public void onCallback(MusicalType musicalType, List<Musical> musicalList) {
                                MusicalType type = new MusicalType(musicalType.getType_id(),musicalType.getType_name(),musicalList);
                                musicalTypeList.add(type);
                                firebaseCallbackList.onCallback(musicalTypeList);
                            }
                        });
                    }
                }
            }
        });
    }

    private void  buildSubItemList(final MusicalType musicalType, final FirebaseCallbackListSubItem firebaseCallbackListSubItem)
    {
        db = FirebaseFirestore.getInstance();
        Query docRef = db.collection(ConstantField.MUSICAL_INSTRUMENT).whereEqualTo(ConstantField.TYPE_ID,musicalType.getType_id()).whereEqualTo(ConstantField.STATUS,true);
        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    musicalList = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        Musical musical = document.toObject(Musical.class);
                        Musical item = new Musical(musical.getType_id(), musical.getId(), musical.getName(), musical.getImage(), musical.getDesc(),musical.isStatus());
                        musicalList.add(item);
                    }
                    firebaseCallbackListSubItem.onCallback(musicalType,musicalList);
                }
                else {
                    Log.e("xxxxxxxr", task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void onItemClick(Musical musical) {
        Toast.makeText(getActivity(), musical.getId()+" - "+musical.getName(), Toast.LENGTH_LONG).show();
    }

    public interface FirebaseCallbackListItem{
        void onCallback(List<MusicalType> musicalTypeList);
    }
    public interface FirebaseCallbackListSubItem{
        void onCallback(MusicalType musicalType,List<Musical> musicalList);
    }
}

