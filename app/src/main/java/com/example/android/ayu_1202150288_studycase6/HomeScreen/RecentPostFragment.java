package com.example.android.ayu_1202150288_studycase6.HomeScreen;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.ayu_1202150288_studycase6.MainActivity;
import com.example.android.ayu_1202150288_studycase6.R;
import com.example.android.ayu_1202150288_studycase6.Adapter.postAdapter;
import com.example.android.ayu_1202150288_studycase6.Model.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RecentPostFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    ProgressDialog mProgressDialog;

    private ArrayList<Post> listPosts;
    //our database reference object
    DatabaseReference databaseFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recent_post_fragment, container, false);

        databaseFood = FirebaseDatabase.getInstance().getReference(MainActivity.table1);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle("Loading Data");
        mProgressDialog.setMessage("Please wait....");
        mProgressDialog.show();

        recyclerView = view.findViewById(R.id.recyclerView);

        listPosts = new ArrayList<>();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart(); //attaching value event listener
        databaseFood.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listPosts.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Post post = postSnapshot.getValue(Post.class);

                    listPosts.add(post);
                }
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

                postAdapter postList = new postAdapter(getContext(), listPosts);

                recyclerView.setAdapter(postList);
                mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
