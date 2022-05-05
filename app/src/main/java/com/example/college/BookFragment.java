package com.example.college;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class BookFragment extends Fragment {
    View rootView;
    Context context;
    final Calendar myCalendar= Calendar.getInstance();
    EditText editDate,name,lname,/*contact*/age,whomToMeet,purpose,userType;
    Button book;
    String date ,department;
    StorageHelper storageHelper;

    public BookFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_book, container, false);
        context = rootView.getContext();
        storageHelper = new StorageHelper(context);
        Spinner spinner=rootView.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(context, R.array.department, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                department=  spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                //Toast.makeText(context,department,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });



        book=(Button)rootView.findViewById(R.id.idBtnBook);
        editDate=(EditText) rootView.findViewById(R.id.idEdtDate);
        name=(EditText) rootView.findViewById(R.id.idEdtName);
        lname=(EditText) rootView.findViewById(R.id.idLname);
        age=(EditText) rootView.findViewById(R.id.idEdtAge);
        //contact=(EditText) rootView.findViewById(R.id.idEdtMobile);

        whomToMeet=(EditText) rootView.findViewById(R.id.idEdtWhom);
        purpose=(EditText) rootView.findViewById(R.id.idEdtPurpose);
        userType=(EditText) rootView.findViewById(R.id.idEdtType);


        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookNow();
            }
        });
        return rootView;

    }




    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editDate.setText(dateFormat.format(myCalendar.getTime()));
        date =dateFormat.format(myCalendar.getTime());

    }



    public  void bookNow(){
        if(name.getText().toString().isEmpty()||lname.getText().toString().isEmpty()||/*contact.getText().toString().isEmpty()||*/age.getText().toString().isEmpty()||department.isEmpty()||whomToMeet.getText().toString().isEmpty()||purpose.getText().toString().isEmpty()||userType.getText().toString().isEmpty()||date.isEmpty()){
            Toast.makeText(context,"Please fill all the forms",Toast.LENGTH_SHORT).show();

        }

        else  if(department.equals("Select Department")){
            Log.i("Response",department);

            Toast.makeText(context,"Please select the department",Toast.LENGTH_SHORT).show();

        }
        else {
            Log.i("Response",department);

            String url = Const.URL+"create";
            RequestQueue requestQueue = Volley.newRequestQueue(context);

            HashMap<String, String> postData = new HashMap<String, String>();
            postData.put("LastName",lname.getText().toString());
            postData.put("FirstName", name.getText().toString());
            postData.put("Age", age.getText().toString());
            postData.put("Contact", storageHelper.getUserNumber());
            postData.put("Department", department);
            postData.put("whoAreYou", userType.getText().toString());
            postData.put("whomToVisit", whomToMeet.getText().toString());
            postData.put("whenToVisit", date);
            postData.put("Purpose", purpose.getText().toString());
            postData.put("statusCode", "0");

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(postData), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONObject json = new JSONObject(response.toString());
                        Log.i("Response",response.toString());
                        String message = json.getString("message");

                        if(message.equals("successfully Booked Your Appointment")){
                            //Toast.makeText(getContext(),"Successfully booked",Toast.LENGTH_SHORT).show();

                            new AlertDialog.Builder(context)
                                    .setTitle("Booked")
                                    .setMessage(message)

                                    // Specifying a listener allows you to take an action before dismissing the dialog.
                                    // The dialog is automatically dismissed when a dialog button is clicked.
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            name.setText("");
                                            lname.setText("");
                                            age.setText("");
                                            //contact.setText("");
                                            department="Select Department";
                                            userType.setText("");
                                            whomToMeet.setText("");
                                            purpose.setText("");
                                            editDate.setText("");
                                            dialog.dismiss();
                                            // Continue with delete operation
                                        }
                                    })

                                    // A null listener allows the button to dismiss the dialog and take no further action.
                                    //.setNegativeButton(android.R.string.no, null)
                                    .setIcon(getResources().getDrawable(R.drawable.ic_baseline_done_all_24))
                                    .setCancelable(false)
                                    .show();
                        }
                        else {
                            Toast.makeText(getContext(),"Try after sometimes",Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Response","errorrrr :"+error);
                    error.printStackTrace();
                }
            });

            requestQueue.add(jsonObjectRequest);



        }
    }
}