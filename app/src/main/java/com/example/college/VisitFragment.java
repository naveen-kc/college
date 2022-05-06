package com.example.college;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class VisitFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<VisitList> list;
    Context context;
    VisitAdapter adapter;
    JSONArray users;
    StorageHelper storageHelper;
    RelativeLayout loading,noData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_visit, container, false);
        context = rootView.getContext();


        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.refreshLayoutt);
        recyclerView = rootView.findViewById(R.id.recycler);
        list = new ArrayList<>();
        loading = rootView.findViewById(R.id.loadingPanel);
        noData=rootView.findViewById(R.id.noData);
        storageHelper = new StorageHelper(context);
        getVisits();



        // Refresh  the layout
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        getVisits();

                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

        return rootView;
    }


    public void getVisits(){
        String number =storageHelper.getUserNumber();
        if(number.isEmpty()){
            number=storageHelper.getUserNumber();
        }
        String Url =Const.URL+"myorders?contact="+number;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("Response",response.toString());

                        try {


                            list = new ArrayList<>();
                            String data = response.toString();
                            JSONObject  jsonRootObject = new JSONObject(data);
                            JSONArray jsonArray = jsonRootObject.optJSONArray("result");
                            if(jsonArray.length()==0){
                                loading.setVisibility(View.GONE);
                                noData.setVisibility(View.VISIBLE);

                              //  Toast.makeText(context,"No Appointments so far",Toast.LENGTH_SHORT).show();
                            }

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);


                                list.add(new VisitList(
                                        jo.optString("Personid"),
                                        jo.optString("First_Name"),
                                        jo.optString("Last_Name"),
                                        jo.optString("Age"),
                                        jo.optString("Purpose"),
                                        jo.optString("statusCode"),
                                        jo.optString("WHO_ARE_YOU"),
                                        jo.optString("WHOM_TO_VISIT"),
                                        jo.optString("WHEN_TO_VISIT"),
                                        jo.optString("Department"),
                                        jo.optString("Contact")
                                ));

                            }

                            adapter = new VisitAdapter(context,list);
                            recyclerView.setAdapter(adapter);
                            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(manager);
                            loading.setVisibility(View.GONE);




                        }
                       catch (Exception e) {
                            Log.i("Response",e.toString());

                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i("Response",error.toString());

                        Toast.makeText(context,"Server Down,Please wait",Toast.LENGTH_SHORT).show();



                    }


                }) {
            /*@Override
            public byte[] getBody() throws AuthFailureError {
                HashMap<String, String> params2 = new HashMap<String, String>();
                params2.put("contact", storageHelper.getUserNumber());

                return new JSONObject(params2).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }*/

        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}