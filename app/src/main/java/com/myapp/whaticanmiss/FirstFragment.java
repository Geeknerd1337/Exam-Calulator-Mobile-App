package com.myapp.whaticanmiss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {


    EditText currPoints;
    EditText totalPoints;
    EditText testPoints;
    EditText minimumGrade;
    Float currentMinPoints;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
        currPoints = fragmentFirstLayout.findViewById(R.id.currentPointsField);
        totalPoints = fragmentFirstLayout.findViewById(R.id.totalPointsField);
        testPoints = fragmentFirstLayout.findViewById(R.id.examPointsField);
        minimumGrade = fragmentFirstLayout.findViewById(R.id.minimumGradeField);
        currentMinPoints = 0f;
        // Inflate the layout for this fragment
        return fragmentFirstLayout;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.calculate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currPoints.getText().toString().isEmpty() && !totalPoints.getText().toString().isEmpty() && !testPoints.getText().toString().isEmpty() && !minimumGrade.getText().toString().isEmpty()) {
                    //String s = GetMinumumRequiredGrade().toString();
                    Float minimumPoints = GetMinumumRequiredGrade();
                    Float totalTestPoints = Float.parseFloat(totalPoints.getText().toString());
                    //Toast myToast = Toast.makeText(getActivity(), s.toString(), Toast.LENGTH_SHORT);
                    //myToast.show();
                    FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(minimumPoints,GetMaxMissedPoints(),totalTestPoints);


                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(action);



                }
            }
        });

    }

    private String CalculateCurrentGrade(){
        String finalString = "Score Input not Found";
        
        if(!currPoints.getText().toString().isEmpty() && !totalPoints.getText().toString().isEmpty()) {
            float curr = Float.parseFloat(currPoints.getText().toString());
            float total = Float.parseFloat(totalPoints.getText().toString());
            float percentage = ((curr / total) * 100.0f);
            String percentageString = String.format("%.2f", percentage);
            finalString = percentageString + "%";
        }

        return finalString;
    }

    private Float GetMinumumRequiredGrade(){
        float currPointsF = Float.parseFloat(currPoints.getText().toString());
        float totalPointsF = Float.parseFloat(totalPoints.getText().toString());
        float examPointsF = Float.parseFloat(testPoints.getText().toString());
        float minGradeF = Float.parseFloat(minimumGrade.getText().toString());


        /*
        The equation
        X = points you can afford to lose
        (currPointsF + x)/(totalPointsF + examPointsF) = minGradeF/100
        X = ((minGradeF/100) * (totalPointsF + examPointsF)) - currPointsF);
         */
        return (((minGradeF/100) * (totalPointsF + examPointsF)) - currPointsF);

    }

    private Float GetMaxMissedPoints(){
        float currPointsF = Float.parseFloat(currPoints.getText().toString());
        float totalPointsF = Float.parseFloat(totalPoints.getText().toString());
        float examPointsF = Float.parseFloat(testPoints.getText().toString());
        float minGradeF = Float.parseFloat(minimumGrade.getText().toString());


        /*
        The equation
        X = points you can afford to lose
        (currPointsF + x)/(totalPointsF + examPointsF) = minGradeF/100
        X = ((minGradeF/100) * (totalPointsF + examPointsF)) - currPointsF);
         */
        return examPointsF -(((minGradeF/100) * (totalPointsF + examPointsF)) - currPointsF);

    }
}
